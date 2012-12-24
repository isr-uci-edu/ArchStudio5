package org.archstudio.graphlayout.core.graphviz;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.archstudio.graphlayout.GraphLayoutConstants;
import org.archstudio.graphlayout.GraphLayoutException;
import org.archstudio.sysutils.NativeProcess;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.eclipse.jface.preference.IPreferenceStore;

public class DotLayoutEngine extends AbstractGraphvizLayoutEngine {

	public static final String LAYOUT_ENGINE_ID = "dot";
	public static final String LAYOUT_ENGINE_DESCRIPTION = "GraphViz DOT Engine";

	@Override
	public String getID() {
		return LAYOUT_ENGINE_ID;
	}

	@Override
	public String getDescription() {
		return LAYOUT_ENGINE_DESCRIPTION;
	}

	@Override
	protected String runLayoutTool(IXArchADT xarch, IPreferenceStore prefs, String toolInput)
			throws GraphLayoutException {
		SystemUtils.OperatingSystem os = SystemUtils.guessOperatingSystem();
		String toolFilename = "dot";
		if (os.equals(SystemUtils.OperatingSystem.OS_WINDOWS)) {
			toolFilename = toolFilename + ".exe";
		}

		String graphvizPath = prefs.getString(GraphLayoutConstants.PREF_GRAPHVIZ_PATH);
		if (graphvizPath == null) {
			throw new GraphLayoutException("Don't have path for GraphViz dot. Check Graph Layout Preferences.");
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
			throw new GraphLayoutException("Can't find GraphViz dot. Check Graph Layout Preferences.");
		}
		if (!dotExecutable.canRead()) {
			throw new GraphLayoutException("No read permission on GraphViz dot.");
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
			return outputData;
		}
		catch (IOException ioe) {
			throw new GraphLayoutException("I/O Error Running Graphviz dot", ioe);
		}
	}
}
