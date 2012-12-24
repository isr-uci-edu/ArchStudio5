package org.archstudio.swtutils;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.part.IPageSite;

public class SWTWidgetUtils {

	private SWTWidgetUtils() {
	}

	public static void makeWheelFriendly(ScrolledComposite sc, Composite contentComposite) {
		final ScrolledComposite scroll = sc;
		Listener l = new Listener() {

			@Override
			public void handleEvent(Event e) {
				scroll.setFocus();
			}
		};

		sc.addListener(SWT.Activate, l);
		sc.addListener(SWT.MouseDown, l);
		contentComposite.addListener(SWT.Activate, l);
		contentComposite.addListener(SWT.MouseDown, l);
	}

	public static void setupContextMenu(String name, Control c, IMenuFiller filler) {
		_setupContextMenu(name, c, null, filler);
	}

	public static void setupContextMenu(String name, Control c, IWorkbenchPartSite site, IMenuFiller filler) {
		_setupContextMenu(name, c, site, filler);
	}

	public static void setupContextMenu(String name, Control c, IPageSite site, IMenuFiller filler) {
		_setupContextMenu(name, c, site, filler);
	}

	private static void _setupContextMenu(String name, Control c, Object site, IMenuFiller filler) {
		MenuManager menuMgr = new MenuManager(name);
		menuMgr.setRemoveAllWhenShown(true);
		final IMenuFiller ffiller = filler;
		menuMgr.addMenuListener(new IMenuListener() {

			@Override
			public void menuAboutToShow(IMenuManager m) {
				ffiller.fillMenu(m);
			}
		});
		Menu contextMenu = menuMgr.createContextMenu(c);
		c.setMenu(contextMenu);
		if (site != null) {
			if (site instanceof IPageSite) {
				((IPageSite) site).registerContextMenu(name, menuMgr, ((IPageSite) site).getSelectionProvider());
			}
			else if (site instanceof IWorkbenchPartSite) {
				((IWorkbenchPartSite) site).registerContextMenu(name, menuMgr,
						((IWorkbenchPartSite) site).getSelectionProvider());
			}
		}
	}

	public static IAction createNoAction(String text) {
		IAction noAction = new Action(text, SWT.NONE) {

			@Override
			public void run() {
			};
		};
		noAction.setEnabled(false);
		return noAction;
	}

	public static void lighten(RGB rgb) {
		int red = rgb.red + 64;
		int green = rgb.green + 64;
		int blue = rgb.blue + 64;

		rgb.red = red <= 255 ? red : 255;
		rgb.green = green <= 255 ? green : 255;
		rgb.blue = blue <= 255 ? blue : 255;
	}

	public static void darken(RGB rgb) {
		int red = rgb.red - 64;
		int green = rgb.green - 64;
		int blue = rgb.blue - 64;

		rgb.red = red >= 0 ? red : 255;
		rgb.green = green >= 0 ? green : 255;
		rgb.blue = blue >= 0 ? blue : 255;
	}

	public static RGB lighter(RGB rgb) {
		RGB newRGB = new RGB(rgb.red, rgb.green, rgb.blue);
		lighten(newRGB);
		return newRGB;
	}

	public static RGB darker(RGB rgb) {
		RGB newRGB = new RGB(rgb.red, rgb.green, rgb.blue);
		darken(newRGB);
		return newRGB;
	}

	public static String rgbToHex(RGB rgb) {
		StringBuffer sb = new StringBuffer(6);
		String s = Integer.toHexString(rgb.red);
		if (s.length() == 1) {
			sb.append(0);
		}
		sb.append(s);

		s = Integer.toHexString(rgb.green);
		if (s.length() == 1) {
			sb.append(0);
		}
		sb.append(s);

		s = Integer.toHexString(rgb.blue);
		if (s.length() == 1) {
			sb.append(0);
		}
		sb.append(s);

		return sb.toString();
	}

	public static RGB hexToRGB(String hex) {
		if (hex == null) {
			return null;
		}
		if (hex.length() != 6) {
			return null;
		}
		try {
			return new RGB(Integer.parseInt(hex.substring(0, 2), 16), Integer.parseInt(hex.substring(2, 4), 16),
					Integer.parseInt(hex.substring(4, 6), 16));
		}
		catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
	}

	public static ImageDescriptor createColorSwatch(Display d, Color c, int w, int h, boolean selected) {
		return createColorSwatch(d, c, w, h, selected, false);
	}

	public static ImageDescriptor createColorSwatch(Display d, Color c, int w, int h, boolean selected, boolean shadowed) {
		Image img = new Image(d, w, h);

		GC gc = new GC(img);
		if (shadowed) {
			gc.setBackground(d.getSystemColor(SWT.COLOR_GRAY));
			gc.fillRectangle(0, 0, w, h);
			gc.setBackground(d.getSystemColor(SWT.COLOR_DARK_GRAY));
			gc.fillRectangle(1, 1, w - 1, h - 1);
			w--;
			h--;
		}
		gc.setBackground(c);
		gc.fillRectangle(0, 0, w - 1, h - 1);
		gc.setForeground(d.getSystemColor(selected ? SWT.COLOR_RED : SWT.COLOR_BLACK));
		gc.drawRectangle(0, 0, w - 1, h - 1);
		gc.dispose();

		ImageDescriptor id = ImageDescriptor.createFromImageData(img.getImageData());
		img.dispose();
		return id;
	}

	public static ImageDescriptor createColorSwatch(Display d, Color[] c, int w, int h, boolean selected) {
		return createColorSwatch(d, c, w, h, selected, false);
	}

	public static ImageDescriptor createColorSwatch(Display d, Color[] c, int w, int h, boolean selected,
			boolean shadowed) {
		Image img = new Image(d, w, h);

		GC gc = new GC(img);
		if (shadowed) {
			gc.setBackground(d.getSystemColor(SWT.COLOR_GRAY));
			gc.fillRectangle(0, 0, w, h);
			gc.setBackground(d.getSystemColor(SWT.COLOR_DARK_GRAY));
			gc.fillRectangle(1, 1, w - 1, h - 1);
			w--;
			h--;
		}

		gc.setClipping(0, 0, w, h);
		int maxwh = w > h ? w : h;
		for (int i = 0; i < maxwh * 2; i++) {
			gc.setForeground(c[i % c.length]);
			gc.drawLine(0, i, i, 0);
		}
		gc.setForeground(d.getSystemColor(selected ? SWT.COLOR_RED : SWT.COLOR_BLACK));
		gc.drawRectangle(0, 0, w - 1, h - 1);
		gc.dispose();

		ImageDescriptor id = ImageDescriptor.createFromImageData(img.getImageData());
		img.dispose();
		return id;
	}

	public static Point calcPointOnLineAtDist(Point Point1, Point Point2, int Dist) {
		double A = Point2.x - Point1.x;
		double B = -(Point2.y - Point1.y); //negate for graphic coords
		if (A == 0) {
			if (B < 0) {
				return new Point(Point1.x, Point1.y + Dist);
			}
			else {
				return new Point(Point1.x, Point1.y - Dist);
			}
		}
		double angle = Math.atan(B / A);
		double a = Dist * Math.cos(angle);
		double b = Dist * Math.sin(angle);
		int ai = (int) Math.round(a);
		int bi = (int) Math.round(b);

		//System.out.println("B= " + B);
		//System.out.println("bist = " + b);
		if (A > 0) {
			return new Point(Point1.x + ai, Point1.y - bi);
		}
		else {
			return new Point(Point1.x - ai, Point1.y + bi);
		}
	}

	public static Point calcPointOnRay(Point Point1, double angle, int Dist) {
		double a = Dist * Math.cos(angle);
		int ai = (int) Math.round(a);
		double b = Dist * Math.sin(angle);
		int bi = (int) Math.round(b);
		return new Point(Point1.x + ai, Point1.y - bi);
	}

	public static void async(Widget w, Runnable r) {
		if (w == null || w.isDisposed()) {
			return;
		}
		async(w.getDisplay(), w, r);
	}

	public static void sync(Widget w, Runnable r) {
		if (w == null || w.isDisposed()) {
			return;
		}
		sync(w.getDisplay(), w, r);
	}

	public static void async(Viewer v, Runnable r) {
		if (v == null) {
			return;
		}
		async(v.getControl(), r);
	}

	public static void sync(Viewer v, Runnable r) {
		if (v == null) {
			return;
		}
		sync(v.getControl(), r);
	}

	public static void async(Display d, Runnable r) {
		async(d, null, r);
	}

	public static void sync(Display d, Runnable r) {
		sync(d, null, r);
	}

	public static void async(final Display d, final Widget w, final Runnable r) {
		if (d == null || d.isDisposed()) {
			return;
		}
		d.asyncExec(new Runnable() {

			@Override
			public void run() {
				if (!d.isDisposed() && (w == null || !w.isDisposed())) {
					r.run();
				}
			}
		});
	}

	public static void sync(final Display d, final Widget w, final Runnable r) {
		if (d == null || d.isDisposed()) {
			return;
		}
		d.syncExec(new Runnable() {

			@Override
			public void run() {
				if (!d.isDisposed() && (w == null || !w.isDisposed())) {
					r.run();
				}
			}
		});
	}

	public static <R extends Resource> R quietlyDispose(R r) {
		try {
			if (r != null) {
				r.dispose();
			}
		}
		catch (Exception e) {
		}
		return null;
	}

	public static <W extends Widget> W quietlyDispose(W d) {
		try {
			if (d != null) {
				d.dispose();
			}
		}
		catch (Exception e) {
		}
		return null;
	}
}
