package org.archstudio.prolog.archstudio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.archstudio.eclipse.ui.IFocusEditorListener;
import org.archstudio.myx.fw.IMyxBrick;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.ui.console.IPatternMatchListenerDelegate;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;

public class ObjRefPatternMatchListenerDelegate implements IPatternMatchListenerDelegate {

	static class Hyperlink implements IHyperlink {
		final ObjRef objRef;

		public Hyperlink(ObjRef objRef) {
			this.objRef = objRef;
		}

		@Override
		public void linkEntered() {
		}

		@Override
		public void linkExited() {
		}

		@Override
		public void linkActivated() {
			for (IEditorReference p : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.getEditorReferences()) {
				IEditorPart e = p.getEditor(false);
				if (e != null) {
					if (e instanceof IFocusEditorListener) {
						((IFocusEditorListener) e).focusEditor(new ObjRef[] { objRef });
					}
				}
			}
		}
	}

	TextConsole console = null;

	@Override
	public void connect(TextConsole console) {
		this.console = console;
	}

	@Override
	public void disconnect() {
		this.console = null;
	}

	@Override
	public void matchFound(PatternMatchEvent event) {
		try {
			String objRefText = console.getDocument().get(event.getOffset(), event.getLength());
			Matcher m = Pattern.compile("[0-9]+").matcher(objRefText);
			if (m.find()) {
				long uid = Long.parseLong(m.group(0));
				IMyxBrick brick = MyxRegistry.getSharedInstance().getBrick(PrologMyxComponent.class);
				if (brick != null) {
					IXArchADT xarch = SystemUtils.firstOrNull(MyxRegistry.getSharedInstance().getObjects(brick,
							IXArchADT.class));
					if (xarch != null) {
						ObjRef objRef = xarch.lookupObjRefUID(uid);
						if (objRef != null) {
							console.addHyperlink(new Hyperlink(objRef), event.getOffset(), event.getLength());
						}
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
