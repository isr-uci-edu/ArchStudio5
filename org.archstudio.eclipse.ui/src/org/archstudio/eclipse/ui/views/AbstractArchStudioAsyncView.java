package org.archstudio.eclipse.ui.views;

import org.archstudio.myx.fw.IMyxBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.IMyxRegistryListener;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxRegistryEvent;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public abstract class AbstractArchStudioAsyncView<B extends IMyxBrick> extends ViewPart implements IMyxRegistryListener {

	protected B brick = null;
	private final Class<B> brickClass;
	private final IMyxName brickName;
	private Composite parent = null;
	private boolean parentCreated = false;

	private final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();

	public AbstractArchStudioAsyncView(Class<B> brickClass) {
		this.brickClass = brickClass;
		this.brickName = null;
		myxRegistry.addMyxRegistryListener(this);
	}

	public AbstractArchStudioAsyncView(IMyxName brickName) {
		this.brickClass = null;
		this.brickName = brickName;
		myxRegistry.addMyxRegistryListener(this);
	}

	@Override
	final public void createPartControl(Composite parent) {
		new Label(parent, SWT.NONE).setText("Initializing ArchStudio...");
		this.parent = parent;
		checkInitialization();
	}

	abstract public void createMyxPartControl(Composite parent);

	@Override
	final public void setFocus() {
		if (parentCreated) {
			setMyxFocus();
		}
	}

	abstract public void setMyxFocus();

	@Override
	@SuppressWarnings("unchecked")
	public void handleMyxRegistryEvent(MyxRegistryEvent evt) {
		if (evt.getEventType() == MyxRegistryEvent.EventType.BRICK_REGISTERED) {
			if (brickClass != null && brickClass.getName().equals(evt.getBrick().getClass().getName())) {
				this.brick = (B) evt.getBrick();
				SWTWidgetUtils.async(parent, new Runnable() {

					@Override
					public void run() {
						checkInitialization();
					}
				});
			}
			if (brickName != null && brickName.equals(evt.getBrick().getMyxBrickItems().getBrickName())) {
				this.brick = (B) evt.getBrick();
				SWTWidgetUtils.async(parent, new Runnable() {

					@Override
					public void run() {
						checkInitialization();
					}
				});
			}
		}
	}

	private void checkInitialization() {
		if (parent != null && !parent.isDisposed()) {
			if (brick == null) {
				brick = myxRegistry.getBrick(brickClass);
			}
			if (brick != null && !parentCreated) {
				parentCreated = true;
				for (Control control : parent.getChildren()) {
					control.dispose();
				}
				initializeMyxBrick();
				createMyxPartControl(parent);
				parent.layout();
			}
		}
	}

	protected void initializeMyxBrick() {
	}
}
