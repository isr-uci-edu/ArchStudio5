package org.archstudio.resources.core;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.archstudio.resources.IResources;
import org.archstudio.swtutils.OverlayImageIcon;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * Myx brick: "Resources Impl"
 * 
 * @see org.archstudio.resources.core.ResourcesMyxComponentStub
 * @generated
 */
public class ResourcesMyxComponent extends org.archstudio.resources.core.ResourcesMyxComponentStub {
	private final Object lock = new Object();

	class ResourcesProxy implements InvocationHandler {

		IResources resources = ResourcesMyxComponent.this;

		List<Runnable> toExec = new ArrayList<Runnable>();

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			synchronized (lock) {
				final Method fMethod = method;
				final Object[] fArgs = args;
				if (void.class.equals(method.getReturnType())) {
					if (display == null) {
						toExec.add(new Runnable() {

							@Override
							public void run() {
								try {
									fMethod.invoke(resources, fArgs);
								}
								catch (Throwable t) {
									t.printStackTrace();
								}
							}
						});
						return null;
					}
					else if (Display.getCurrent() == null) {
						display.asyncExec(new Runnable() {

							@Override
							public void run() {
								try {
									fMethod.invoke(resources, fArgs);
								}
								catch (Throwable t) {
									t.printStackTrace();
								}
							}
						});
						return null;
					}
				}
				if (Display.getCurrent() == null) {
					SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
				}
				if (display == null) {
					setDisplay(Display.getCurrent());
					for (Runnable runnable : toExec) {
						runnable.run();
					}
					toExec.clear();
				}
				return method.invoke(resources, args);
			}
		}
	}

	protected Display display = null;
	protected FontRegistry fontRegistry = null;
	protected ImageRegistry imageRegistry = null;
	protected ColorRegistry colorRegistry = null;

	protected void setDisplay(Display display) {
		synchronized (lock) {
			if (this.display != null) {
				this.display = null;

				imageRegistry.dispose();

				fontRegistry = null;
				imageRegistry = null;
				colorRegistry = null;
			}
			if (display != null) {
				this.display = display;

				fontRegistry = new FontRegistry(display);
				imageRegistry = new ImageRegistry(display);
				colorRegistry = new ColorRegistry(display);

				createColor(COLOR_ARCHSTUDIO, RGB_ARCHSTUDIO_MAIN);
				createColor(COLOR_BANNER_BRIGHT, RGB_BANNER_BRIGHT);
				createColor(COLOR_BANNER_DARK, RGB_BANNER_DARK);

				display.disposeExec(new Runnable() {

					@Override
					public void run() {
						setDisplay(null);
					}
				});
			}
			lock.notifyAll();
		}
	}

	public ResourcesMyxComponent() {
	}

	protected void checkDevice() {
		if (Display.getCurrent() == null) {
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
		}
		synchronized (lock) {
			if (this.display == null) {
				setDisplay(Display.getDefault());
			}
		}
	}

	@Override
	public Image getPlatformImage(String symbolicName) {
		checkDevice();
		return PlatformUI.getWorkbench().getSharedImages().getImage(symbolicName);
	}

	@Override
	public ImageDescriptor getPlatformImageDescriptor(String symbolicName) {
		checkDevice();
		return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(symbolicName);
	}

	@Override
	public Font getPlatformFont(String symbolicName) {
		checkDevice();
		return JFaceResources.getFont(symbolicName);
	}

	@Override
	public Font getBoldPlatformFont(String symbolicName) {
		checkDevice();
		FontRegistry fr = JFaceResources.getFontRegistry();
		return fr.getBold(symbolicName);
	}

	@Override
	public Font getItalicPlatformFont(String symbolicName) {
		checkDevice();
		FontRegistry fr = JFaceResources.getFontRegistry();
		return fr.getItalic(symbolicName);
	}

	@Override
	public Font getFont(String symbolicName) {
		checkDevice();
		return fontRegistry.get(symbolicName);
	}

	@Override
	public void createFont(String symbolicName, FontData[] fontData) {
		checkDevice();
		fontRegistry.put(symbolicName, fontData);
	}

	@Override
	public void createDerivedFont(String newSymbolicName, FontData[] existingFontData, int newHeight, int newStyle) {
		checkDevice();
		FontData[] fds = existingFontData;
		FontData[] nfds = new FontData[fds.length];
		for (int i = 0; i < fds.length; i++) {
			int h = newHeight;
			if (newHeight == 0) {
				h = fds[i].getHeight();
			}
			int s = newStyle;
			if (newStyle == 0) {
				s = fds[i].getStyle();
			}
			nfds[i] = new FontData(fds[i].getName(), h, s);
		}
		createFont(newSymbolicName, nfds);
	}

	//	this approach does not work with plugins

	//	public void createImage(String symbolicName, String url){
	//		createImage(symbolicName, url, null);
	//	}

	//	public void createImage(String symbolicName, String url, Class resourceClass){
	//		if(imageRegistry.get(symbolicName) != null) return;
	//		try{
	//			InputStream is = SystemUtils.openURL(url, resourceClass);
	//			ImageData id = new ImageData(is);
	//			ImageDescriptor desc = ImageDescriptor.createFromImageData(id);
	//			Image img = desc.createImage();
	//			imageRegistry.put(symbolicName, img);
	//			is.close();
	//		}
	//		catch(Exception e){
	//			e.printStackTrace();
	//		}
	//	}

	@Override
	public void createImage(String symbolicName, byte[] bytes) {
		createImage(symbolicName, new ByteArrayInputStream(bytes));
	}

	private void createImage(String symbolicName, InputStream is) {
		checkDevice();
		if (imageRegistry.get(symbolicName) != null) {
			return;
		}
		try {
			ImageData id = new ImageData(is);
			ImageDescriptor desc = ImageDescriptor.createFromImageData(id);
			Image img = desc.createImage();
			createImage(symbolicName, img);
			is.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createImage(String symbolicName, Image img) {
		checkDevice();
		if (imageRegistry.get(symbolicName) != null) {
			return;
		}
		try {
			imageRegistry.put(symbolicName, img);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createImage(String symbolicName, ImageDescriptor imageDescriptor) {
		createImage(symbolicName, imageDescriptor.createImage());
	}

	@Override
	public void createOverlayImage(String symbolicName, Image base, Image[] overlays, int[] overlayPositions) {
		checkDevice();
		if (imageRegistry.get(symbolicName) != null) {
			return;
		}
		OverlayImageIcon oii = new OverlayImageIcon(base, overlays, overlayPositions);
		imageRegistry.put(symbolicName, oii);
	}

	@Override
	public Image getImage(String symbolicName) {
		checkDevice();
		return imageRegistry.get(symbolicName);
	}

	@Override
	public ImageDescriptor getImageDescriptor(String symbolicName) {
		checkDevice();
		return imageRegistry.getDescriptor(symbolicName);
	}

	@Override
	public void createColor(String symbolicName, RGB colorData) {
		checkDevice();
		if (colorRegistry.get(symbolicName) != null) {
			return;
		}
		colorRegistry.put(symbolicName, colorData);
	}

	@Override
	public Color getColor(String symbolicName) {
		checkDevice();
		return colorRegistry.get(symbolicName);
	}
}