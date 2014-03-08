package org.archstudio.utils.bna.dot;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.things.AbstractRelativeMovableReferencePointThing;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.graphlayout.GraphLayoutConstants;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.sysutils.NativeProcess;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

public class ExportImportDot extends AbstractThingLogic implements IBNAMenuListener {

	public ExportImportDot(IBNAWorld world) {
		super(world);
	}

	private static class Node {
		IThing thing;
		LoadingCache<Node, AtomicInteger> forward = CacheBuilder.newBuilder().build(
				new CacheLoader<Node, AtomicInteger>() {
					@Override
					public AtomicInteger load(Node key) throws Exception {
						return new AtomicInteger();
					}
				});
		LoadingCache<Node, AtomicInteger> reverse = CacheBuilder.newBuilder().build(
				new CacheLoader<Node, AtomicInteger>() {
					@Override
					public AtomicInteger load(Node key) throws Exception {
						return new AtomicInteger();
					}
				});

		public Node(IThing node) {
			this.thing = node;
		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append(thing.getID()).append("->[");
			for (Node n : forward.asMap().keySet()) {
				sb.append(n.thing.getID()).append(",");
			}
			sb.append("]");
			return sb.toString();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (thing == null ? 0 : thing.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Node other = (Node) obj;
			if (thing == null) {
				if (other.thing != null) {
					return false;
				}
			}
			else if (!thing.equals(other.thing)) {
				return false;
			}
			return true;
		}
	}

	@Override
	synchronized public void fillMenu(final IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu) {
		menu.add(new Action("Layout using Dot") {
			@Override
			public void run() {
				try {
					layout(view, Sets.newHashSet(BNAUtils.getSelectedThings(view.getBNAWorld().getBNAModel())));
				}
				catch (Exception e) {
					e.printStackTrace();
					MessageDialog.openError(view.getBNAUI().getComposite().getShell(), "Error", e.getMessage());
				}
			}
		});
	}

	protected void layout(IBNAView view, Set<IThing> involvingThings) throws IOException, InvocationTargetException,
			InterruptedException {
		final IBNAModel model = view.getBNAWorld().getBNAModel();
		if (involvingThings.size() == 0) {
			involvingThings.addAll(model.getAllThings());
		}

		final LoadingCache<IThing, Node> nodes = CacheBuilder.newBuilder().build(new CacheLoader<IThing, Node>() {
			@Override
			public Node load(IThing key) throws Exception {
				return new Node(key);
			}
		});

		// extract nodes
		for (IThing t : model.getAllThings()) {
			if (isNodeOfInterest(model, t) && involvingThings.contains(t)) {
				nodes.getUnchecked(t);
			}
		}

		// extract edges
		StickPointLogic spl = view.getBNAWorld().getThingLogicManager().addThingLogic(StickPointLogic.class);
		for (IHasEndpoints t : Iterables.filter(model.getAllThings(), IHasEndpoints.class)) {
			IThing thing1 = getNodeFromEdgeEndpoint(model, t, IHasEndpoints.ENDPOINT_1_KEY, spl);
			IThing thing2 = getNodeFromEdgeEndpoint(model, t, IHasEndpoints.ENDPOINT_2_KEY, spl);
			if (thing1 != null && thing2 != null) {
				Node node1 = nodes.getIfPresent(thing1);
				Node node2 = nodes.getIfPresent(thing2);
				if (node1 != null && node2 != null) {
					int weight = getEdgeWeight(model, t, node1.thing, node2.thing);
					if (weight != 0) {
						node1.forward.getUnchecked(node2).addAndGet(weight);
						node2.reverse.getUnchecked(node1).addAndGet(-weight);
					}
				}
			}
		}

		// segment into subgroups
		final int minimumRequiredWeight = 2;
		final SetMultimap<Node, Node> rootsToNodes = HashMultimap.create();
		final Map<Node, Node> nodeToRoot = Maps.newHashMap();
		for (Node root : nodes.asMap().values()) {
			if (rootsToNodes.containsValue(root)) {
				// node has already been assigned a group
				continue;
			}

			// create a group
			rootsToNodes.put(root, root);
			boolean modified = true;
			while (modified) {
				modified = false;
				for (Node from : Lists.newArrayList(rootsToNodes.get(root))) {
					for (Entry<Node, AtomicInteger> edge : from.forward.asMap().entrySet()) {
						if (Math.abs(edge.getValue().get()) >= minimumRequiredWeight) {
							modified |= rootsToNodes.put(root, edge.getKey());
						}
					}
					for (Entry<Node, AtomicInteger> edge : from.reverse.asMap().entrySet()) {
						if (Math.abs(edge.getValue().get()) >= minimumRequiredWeight) {
							modified |= rootsToNodes.put(root, edge.getKey());
						}
					}
				}
			}
		}
		// record what root each node is part of
		for (Entry<Node, Collection<Node>> entry : rootsToNodes.asMap().entrySet()) {
			Node root = entry.getKey();
			nodeToRoot.put(root, root);
			for (Node includes : entry.getValue()) {
				nodeToRoot.put(includes, root);
			}
		}

		PlatformUI.getWorkbench().getProgressService().busyCursorWhile(new IRunnableWithProgress() {
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					SubMonitor m = SubMonitor
							.convert(monitor, "Laying out using Dot", rootsToNodes.keySet().size() + 1);
					StringBuffer cb = new StringBuffer();
					cb.append("digraph G { overlap=false; splines=false;\n");

					// layout individual groups
					Map<Node, Dimension> rootSizes = Maps.newHashMap();
					int spacing = 24;
					for (Entry<Node, Collection<Node>> rootToNodes : rootsToNodes.asMap().entrySet()) {

						// if canceled, cancel
						if (m.isCanceled()) {
							return;
						}

						// create dot input
						StringBuffer sb = new StringBuffer();
						sb.append("digraph G { overlap=false; splines=false;\n");
						cb.append("subgraph cluster_").append(rootToNodes.getKey().thing.getID()).append(" {\n");
						for (Node node : rootToNodes.getValue()) {
							sb.append(node.thing.getID()).append(getNodeProperties(node.thing, spacing)).append(";\n");
							cb.append(node.thing.getID()).append(getNodeProperties(node.thing, spacing)).append(";\n");
						}
						for (Node node : rootToNodes.getValue()) {
							for (Entry<Node, AtomicInteger> to : node.forward.asMap().entrySet()) {
								if (Math.abs(to.getValue().get()) >= minimumRequiredWeight) {
									sb.append(node.thing.getID()).append("->").append(to.getKey().thing.getID())
											.append(" [weight=").append(to.getValue().get()).append("];\n");
									cb.append(node.thing.getID()).append("->").append(to.getKey().thing.getID())
											.append(" [weight=").append(to.getValue().get()).append("];\n");
								}
							}
						}
						sb.append("}\n");
						cb.append("}\n");

						m.worked(1);
					}

					// calculate weights between root nodes
					LoadingCache<IThing, Node> rootNodes = CacheBuilder.newBuilder().build(
							new CacheLoader<IThing, Node>() {
								@Override
								public Node load(IThing key) throws Exception {
									return new Node(key);
								}
							});
					for (Node from : nodes.asMap().values()) {
						Node fromRoot = nodeToRoot.get(from);
						for (Entry<Node, AtomicInteger> edge : from.forward.asMap().entrySet()) {
							Node toRoot = nodeToRoot.get(edge.getKey());
							if (fromRoot == toRoot) {
								continue;
							}
							rootNodes.getUnchecked(fromRoot.thing).forward.getUnchecked(toRoot).addAndGet(
									edge.getValue().get());
						}
					}

					// layout subgroup master nodes
					float factor = 1;
					for (Dimension d : rootSizes.values()) {
						factor = Math.max(factor, d.width);
						factor = Math.max(factor, d.height);
					}
					factor = 1000;
					StringBuffer sb = new StringBuffer();
					sb.append("digraph G { overlap=false; splines=false;\n");
					for (Entry<Node, Dimension> entry : rootSizes.entrySet()) {
						Dimension size = entry.getValue();
						sb.append(entry.getKey().thing.getID()).append("[shape=box, fixedsize=true, width=")
								.append(size.width / factor).append(", height=").append(size.height / factor)
								.append("];\n");
					}
					for (Node root : rootNodes.asMap().values()) {
						for (Entry<Node, AtomicInteger> edge : root.forward.asMap().entrySet()) {
							sb.append(root.thing.getID()).append("->").append(edge.getKey().thing.getID())
									.append("[weight=").append(edge.getValue().get()).append("];\n");
							cb.append(root.thing.getID()).append("->").append(edge.getKey().thing.getID())
									.append("[weight=").append(edge.getValue().get()).append("];\n");
						}
					}
					sb.append("}\n");
					cb.append("}\n");

					// execute dot
					String result = runDot(org.archstudio.graphlayout.core.Activator.getDefault().getPreferenceStore(),
							cb.toString());
					final Map<Integer, Point> locations = Maps.newHashMap();
					float graphWidth = 0;
					float graphHeight = 0;
					BufferedReader br = new BufferedReader(new StringReader(result));
					String line;
					while ((line = br.readLine()) != null) {
						if (line.startsWith("graph")) {
							String[] fields = line.split("\\s+");
							float scale = Float.parseFloat(fields[1]);
							graphWidth = Float.parseFloat(fields[2]) * scale;
							graphHeight = Float.parseFloat(fields[3]) * scale;
						}
						if (line.startsWith("node")) {
							String[] fields = line.split("\\s+");
							int uid = Integer.parseInt(fields[1]);
							float width = Float.parseFloat(fields[4]);
							float height = Float.parseFloat(fields[5]);
							float x = Float.parseFloat(fields[2]) - width / 2;
							float y = Float.parseFloat(fields[3]) - height / 2;
							locations.put(
									uid,
									new Point(SystemUtils.round(x - graphWidth / 2), SystemUtils.round(y - graphHeight
											/ 2)));
						}
					}

					// update BNA model
					SWTWidgetUtils.async(Display.getDefault(), new Runnable() {
						@Override
						public void run() {
							for (IThing t : model.getAllThings()) {
								if (locations.containsKey(t.getID())) {
									if (t instanceof AbstractRelativeMovableReferencePointThing) {
										Point op = ((AbstractRelativeMovableReferencePointThing) t).getReferencePoint();
										Point np = locations.get(t.getID());
										((AbstractRelativeMovableReferencePointThing) t).moveRelative(new Point(np.x
												- op.x, np.y - op.y));
									}
								}
							}
						}
					});
				}
				catch (IOException e) {
					throw new InvocationTargetException(e);
				}
			};
		});
	}

	private Object getNodeProperties(IThing thing, int spacing) {
		StringBuffer sb = new StringBuffer();

		if (thing instanceof IHasBoundingBox) {
			Rectangle r = ((IHasBoundingBox) thing).getBoundingBox();
			sb.append("shape=box, fixedsize=true, width=").append(r.width + spacing).append(", ");
			sb.append("height=").append(r.height + spacing);
		}

		return sb.length() > 0 ? "[" + sb + "]" : "";
	}

	protected boolean isNodeOfInterest(IBNAModel model, IThing node) {
		return node instanceof IHasMutableBoundingBox && Assemblies.isRoot(node);
	}

	protected IThing getNodeFromEdgeEndpoint(IBNAModel model, IThing edge, IThingKey<Point> endpointKey,
			StickPointLogic spl) {
		return Assemblies.getRoot(model, model.getThing(spl.getStickyThingID(edge, endpointKey)));
	}

	protected int getEdgeWeight(IBNAModel model, IHasEndpoints edge, IThing node1, IThing node2) {
		return 1;
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

		//We have to flip Y so we don't get a mathematical (i.e. 0,0 at the lower-left corner)
		//coordinate system.
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
		}
		catch (IOException ioe) {
			throw new IOException("I/O Error Running Graphviz dot", ioe);
		}
	}

}
