package org.archstudio.utils.bna.dot;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.facets.IHasMutableMidpoints;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAMenuListener2;
import org.archstudio.bna.utils.BNAAction;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.archstudio.graphlayout.GraphLayoutConstants;
import org.archstudio.sysutils.NativeProcess;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class ExportImportDot extends AbstractThingLogic implements IBNAMenuListener2 {

  public ExportImportDot(IBNAWorld world) {
    super(world);
  }

  @Override
  public void fillMenu(final IBNAView view, ICoordinate location, ThingsAtLocation thingsAtLocation,
      IMenuManager menuManager) {
    BNAUtils.checkLock();
    if (thingsAtLocation.getViewAtLocation() != null) {
      menuManager.add(new BNAAction("Layout using Dot") {
        @Override
        public void runWithLock() {
          try {
            layout(view,
                Sets.newHashSet(BNAUtils.getSelectedThings(view.getBNAWorld().getBNAModel())));
          } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.openError(view.getBNAUI().getComposite().getShell(), "Error",
                e.getMessage());
          }
        }
      });
    }
  }

  protected void layout(IBNAView view, Set<IThing> involvingThings)
      throws IOException, InvocationTargetException, InterruptedException {
    final IBNAModel model = view.getBNAWorld().getBNAModel();
    if (involvingThings.size() == 0) {
      involvingThings.addAll(model.getAllThings());
    }

    double scale = 0.1;
    final Map<String, Node> nodes = Node.scanForNodes(world, involvingThings, scale);
    final Map<String, Edge> edges = Edge.scanForEdges(world, involvingThings, nodes);

    StringBuilder sb = new StringBuilder();
    sb.append("digraph G {\n");
    sb.append("overlap=false;\n");
    sb.append("scale=" + Node.SIMPLE_DECIMAL_FORMATTER.format(scale) + ";\n");
    sb.append("splines=line;\n");
    for (Node node : Edge.filterNodesByEdges(nodes.values(), edges.values())) {
      sb.append(node.toDotNode());
    }
    for (Edge edge : edges.values()) {
      sb.append(edge.toDotEdge());
    }
    sb.append("}\n");

    // Execute dot.
    String result = runDot(
        org.archstudio.graphlayout.core.Activator.getDefault().getPreferenceStore(), sb.toString());
    final Map<String, Point> locations = Maps.newHashMap();
    BufferedReader br = new BufferedReader(new StringReader(result));
    String line;
    while ((line = br.readLine()) != null) {
      if (line.startsWith("node")) {
        String[] fields = line.split("\\s+");
        String uid = fields[1];
        double x = Float.parseFloat(fields[2]) / scale;
        double y = Float.parseFloat(fields[3]) / scale;
        locations.put(uid, new Point(SystemUtils.round(x), SystemUtils.round(y)));
      }
    }

    // Update BNA model.
    for (Node node : nodes.values()) {
      String uid = Node.getUniqueId(node.getThing());
      Point p = locations.get(uid);
      if (p != null) {
        IThing thing = node.getThing();
        if (thing instanceof IHasMutableBoundingBox) {
          Rectangle r = ((IHasMutableBoundingBox) thing).getBoundingBox();
          r.x = p.x - r.width / 2;
          r.y = p.y - r.height / 2;
          ((IHasMutableBoundingBox) thing).setBoundingBox(r);
        }
      }
    }
    for (Edge edge : edges.values()) {
      IThing thing = edge.getThing();
      if (thing instanceof IHasMutableMidpoints) {
        ((IHasMutableMidpoints) thing).setMidpoints(new ArrayList<Point2D>());
      }
    }
  }

  protected String runDot(IPreferenceStore prefs, String toolInput) throws IOException {
    SystemUtils.OperatingSystem os = SystemUtils.guessOperatingSystem();
    String toolFilename = "dot";
    if (os.equals(SystemUtils.OperatingSystem.OS_WINDOWS)) {
      toolFilename = toolFilename + ".exe";
    }

    String graphvizPath = prefs.getString(GraphLayoutConstants.PREF_GRAPHVIZ_PATH);
    if (graphvizPath == null) {
      throw new IOException("Don't have path for GraphViz dot. Check Graph Layout Preferences.");
    }

    String dotPath = graphvizPath;
    if (!dotPath.endsWith(File.separator)) {
      dotPath += File.separator;
    }
    if (!dotPath.endsWith("bin" + File.separator)) {
      dotPath += "bin" + File.separator;
    }
    dotPath += toolFilename;

    File dotExecutable = new File(dotPath);
    if (!dotExecutable.exists()) {
      throw new IOException("Can't find GraphViz dot. Check Graph Layout Preferences.");
    }
    if (!dotExecutable.canRead()) {
      throw new IOException("No read permission on GraphViz dot.");
    }

    // We have to flip Y so we don't get a mathematical (i.e. 0,0 at the lower-left corner)
    // coordinate system.
    List<String> commandLineEltList = new ArrayList<String>();
    commandLineEltList.add(dotPath);
    commandLineEltList.add("-Tplain-ext");
    commandLineEltList.add("-y");

    try {
      String[] commandline = commandLineEltList.toArray(new String[0]);
      Process p = Runtime.getRuntime().exec(commandline);

      NativeProcess np = new NativeProcess(p, toolInput);
      np.start();
      np.waitForCompletion();

      String outputData = np.getStdout().trim();
      if (outputData.length() == 0) {
        // we got an error
        throw new IOException(np.getStderr().trim());
      }
      return outputData;
    } catch (IOException ioe) {
      System.err.println(toolInput);
      throw new IOException("I/O Error Running Graphviz dot", ioe);
    }
  }
}
