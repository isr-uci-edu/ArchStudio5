package org.archstudio.prolog.archstudio;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.archstudio.archlight.ArchlightTest;
import org.archstudio.prolog.engine.MostGeneralUnifierEngine;
import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.ProofEngine;
import org.archstudio.prolog.engine.SingleThreadProofEngine;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.parser.PrologParser;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.archstudio.prolog.xadl.PrologUtils;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Myx brick: "PrologMyxComponent"
 * 
 * @see org.archstudio.prolog.archstudio.PrologMyxComponentStub
 * @generated
 */
public class PrologMyxComponent extends org.archstudio.prolog.archstudio.PrologMyxComponentStub {

	private class TestData {
		String prolog;
		String goalname;

		public TestData(String prolog, String goalname) {
			super();
			this.prolog = prolog;
			this.goalname = goalname;
		}
	}

	String TOOL_ID = "Prolog";
	Map<String, TestData> testData = Maps.newHashMap();

	@Override
	public void begin() {
		super.begin();
		reloadTests();
	}

	@Override
	public String getToolID() {
		return TOOL_ID;
	}

	@Override
	public void reloadTests() {

		testData.clear();

		// scan plugins for prolog tests
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		if (reg != null) {
			// The Extension Registry can be null if we're running outside of Eclipse
			for (IConfigurationElement configurationElement : reg
					.getConfigurationElementsFor("org.archstudio.prolog.archstudio.archlighttest")) {
				String id = configurationElement.getAttribute("id");
				String name = configurationElement.getAttribute("name");
				String description = configurationElement.getAttribute("description");
				String goalname = configurationElement.getAttribute("goal");
				String prolog = "";
				if (configurationElement.getChildren("Prolog").length > 0) {
					prolog = configurationElement.getChildren("Prolog")[0].getValue();
				}
				testData.put(id, new TestData(prolog, goalname));
				tests.addTests(Lists.newArrayList(new ArchlightTest(id, TOOL_ID, name, description)));
			}
		}
	}

	@Override
	public void runTests(ObjRef documentRootRef, Collection<String> testUIDs) {
		try {

			ProofContext proofContext = new ProofContext();
			UnificationEngine unifier = new MostGeneralUnifierEngine();
			ProofEngine proofEngine = new SingleThreadProofEngine();
			proofContext.add(PrologUtils.getFacts(null, XArchADTProxy.proxy(xarch, documentRootRef)));

			URI uri = xarch.getURI(documentRootRef);
			IResource resource = null;
			if ("platform".equalsIgnoreCase(uri.scheme())) {
				if (uri.segmentCount() > 1 && uri.segment(0).equals("resource")) {
					List<String> pathSegs = Lists.newArrayList(uri.segmentsList());
					IPath path = new Path(Joiner.on("/").join(pathSegs));
					resource = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
				}
			}
			if ("file".equalsIgnoreCase(uri.scheme())) {
				for (IFile file : ResourcesPlugin.getWorkspace().getRoot()
						.findFilesForLocationURI(new java.net.URI(uri.toString()))) {
					resource = file;
					break;
				}
			}
			if (resource != null) {
				for (IMarker marker : resource.findMarkers(IMarker.PROBLEM, false, IResource.DEPTH_INFINITE)) {
					if (this.getClass().getName().equals(marker.getAttribute(IMarker.SOURCE_ID))) {
						marker.delete();
					}
				}

				for (String uid : testUIDs) {
					TestData td = testData.get(uid);
					if (td != null) {
						ProofContext context = new ProofContext(proofContext);
						context.add(Iterables.filter(PrologParser.parseTerms(context, td.prolog), ComplexTerm.class));
						VariableTerm vid = new VariableTerm("ARCHLIGHT_FAILURE_ID");
						VariableTerm vdesc = new VariableTerm("ARCHLIGHT_FAILURE_DESCRIPTION");
						ComplexTerm goal = new ComplexTerm(td.goalname, vid, vdesc);
						for (Map<VariableTerm, Term> variables : proofEngine.execute(context, unifier, goal)) {

							Term idTerm = variables.get(vid);
							String id = ((ConstantTerm) idTerm).getValue().toString();
							Term descTerm = variables.get(vdesc);
							String desc = descTerm != null ? ((ConstantTerm) descTerm).getValue().toString() : "null";

							IMarker marker = resource.createMarker(IMarker.PROBLEM);
							marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
							marker.setAttribute(IMarker.MESSAGE, desc);
							marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
							marker.setAttribute(IMarker.LOCATION, id);
							marker.setAttribute(IMarker.SOURCE_ID, this.getClass().getName());
						}
					}
				}
			}
		}
		catch (Exception e) {
			Platform.getLog(Activator.getContext().getBundle()).log(
					new Status(IStatus.ERROR, Activator.getContext().getBundle().getSymbolicName(), TOOL_ID
							+ " Archlight Tool: " + e.getMessage(), e));
			throw new RuntimeException(e);
		}
	}
}