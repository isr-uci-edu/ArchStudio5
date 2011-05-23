package org.archstudio.archipelago.core;

/**
 * Myx brick: "Archipelago Impl"
 *
 * @see org.archstudio.archipelago.core.ArchipelagoMyxComponentStub
 * @generated
 */
public class ArchipelagoMyxComponent extends
		org.archstudio.archipelago.core.ArchipelagoMyxComponentStub {

	public static final String EDITOR_NAME = "Archipelago";
	public static final String ECLIPSE_EDITOR_ID = "org.archstudio.archipelago.core.ArchipelagoEditor";

	public static final String URL_BASE = "platform:/plugin/org.archstudio.archipelago.core/";

	public static final String IMAGE_ARCHIPELAGO_ICON = "archipelago:icon";
	public static final String URL_ARCHIPELAGO_ICON = URL_BASE
			+ "res/archipelago-icon-32.gif";

	public ArchipelagoMyxComponent() {
		super(EDITOR_NAME, ECLIPSE_EDITOR_ID, true);
	}

	boolean resourcesCreated = false;

	private synchronized void createResources() {
		if (!resourcesCreated) {
			try {
				resources.createImage(IMAGE_ARCHIPELAGO_ICON,
						EclipseUtils.getBytes(URL_ARCHIPELAGO_ICON));
			} catch (IOException ioe) {
				ioe.printStackTrace();
				throw new RuntimeException("This shouldn't happen.");
			}
			resourcesCreated = true;
		}
	}

	public Image getIcon() {
		createResources();
		return resources.getImage(IMAGE_ARCHIPELAGO_ICON);
	}

	public ILaunchData getLaunchData() {
		return new LaunchData(ECLIPSE_EDITOR_ID, EDITOR_NAME,
				"A graphical architecture editor", getIcon(),
				ILaunchData.LaunchType.EDITOR);
	}
}