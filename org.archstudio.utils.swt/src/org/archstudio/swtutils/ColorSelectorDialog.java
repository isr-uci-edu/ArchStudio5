package org.archstudio.swtutils;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ColorSelectorDialog extends Dialog {

	/*
	 * public static void main(String[] args){ Display display = new Display();
	 * 
	 * Shell shell = new Shell(display); shell.setText("Dialog Example"); shell.setSize(300, 200); shell.open();
	 * 
	 * ColorSelectorDialog csd = new ColorSelectorDialog(shell); System.err.println("opening"); RGB result =
	 * csd.open(new RGB(0,0,0)); System.err.println("opened done: " + result);
	 * 
	 * while (!shell.isDisposed()) { if (!display.readAndDispatch()) display.sleep(); } System.err.println("exit"); }
	 */

	public static final int NUM_SCHEME_COLORS = 16;

	protected ImageRegistry imageRegistry = null;
	protected ColorRegistry colorRegistry = null;
	private Shell shell = null;
	private RGB result = null;

	public ColorSelectorDialog(Shell parent, int style) {
		super(parent, style);
	}

	public ColorSelectorDialog(Shell parent) {
		this(parent, 0); // your default style bits go here (not the Shell's style bits)
	}

	protected void select(RGB rgb) {
		this.result = rgb;
		this.shell.dispose();
	}

	public RGB open(RGB initialValue) {
		Shell parent = getParent();

		this.shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);

		String text = getText();
		if (text == null || text.trim().equals("")) {
			text = "Select a Color";
		}
		shell.setText(text);

		// Your code goes here (widget creation, set result, etc).
		imageRegistry = new ImageRegistry(parent.getDisplay());
		colorRegistry = new ColorRegistry(parent.getDisplay());

		shell.setLayout(new FillLayout());

		Composite mainComposite = new Composite(shell, SWT.NONE);
		mainComposite.setLayout(new GridLayout(1, false));

		ColorSchemeLoader csl = ColorSchemeLoader.getInstance();
		ColorScheme[] colorSchemes = csl.getDefaultColorSchemes();
		if (colorSchemes != null && colorSchemes.length != 0) {
			createSchemeComposite(mainComposite, colorSchemes);
		}

		Composite cCustom = new Composite(mainComposite, SWT.NONE);
		//cCustom.setLayout(new GridLayout(2, false));
		//cCustom.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL |
		//	GridData.VERTICAL_ALIGN_FILL | GridData.GRAB_VERTICAL));
		cCustom.setLayout(new FillLayout(SWT.HORIZONTAL));

		createCustomComposite(cCustom, initialValue);
		createHexComposite(cCustom, initialValue);

		Composite cButtons = new Composite(mainComposite, SWT.NONE);
		cButtons.setLayoutData(new GridData(GridData.END, GridData.CENTER, true, false));
		cButtons.setLayout(new GridLayout(1, false));

		Button bCancel = new Button(cButtons, SWT.NONE);
		bCancel.setLayoutData(new GridData(GridData.END, GridData.CENTER, true, false));
		bCancel.setText("Cancel");
		bCancel.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				select(null);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		shell.pack();
		shell.open();
		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		//Clean up resources used
		if (imageRegistry != null) {
			imageRegistry.dispose();
		}

		return result;
	}

	protected void createSchemeComposite(final Composite parent, final ColorScheme[] colorSchemes) {
		String[] colorSchemeNames = new String[colorSchemes.length];
		for (int i = 0; i < colorSchemes.length; i++) {
			colorSchemeNames[i] = colorSchemes[i].getName();
		}
		Group gScheme = new Group(parent, SWT.NONE);
		gScheme.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
		gScheme.setLayout(new FillLayout());
		gScheme.setText("Select from Scheme");

		Composite cScheme = new Composite(gScheme, SWT.NONE);
		cScheme.setLayout(new GridLayout(1, false));

		Composite cSchemeSelector = new Composite(cScheme, SWT.NONE);
		cSchemeSelector.setLayout(new GridLayout(2, false));
		cSchemeSelector.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

		Label lSelectScheme = new Label(cSchemeSelector, SWT.NONE);
		lSelectScheme.setText("Scheme:");

		final Combo cbSelectScheme = new Combo(cSchemeSelector, SWT.READ_ONLY);
		cbSelectScheme.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
		cbSelectScheme.setItems(colorSchemeNames);
		cbSelectScheme.select(0);

		Composite cSchemeButtons = new Composite(cScheme, SWT.NONE);
		cSchemeButtons.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		cSchemeButtons.setLayout(new GridLayout(4, true));

		final Button[] bSchemeButtons = new Button[NUM_SCHEME_COLORS];
		for (int i = 0; i < NUM_SCHEME_COLORS; i++) {
			bSchemeButtons[i] = new Button(cSchemeButtons, SWT.FLAT);

			final Button fb = bSchemeButtons[i];
			bSchemeButtons[i].addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					Object data = fb.getData();
					if (data != null && data instanceof RGB) {
						select((RGB) data);
						return;
					}
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});
		}
		setupSchemeButtons(parent.getDisplay(), bSchemeButtons, colorSchemes[0], colorRegistry, imageRegistry);

		cbSelectScheme.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = cbSelectScheme.getSelectionIndex();
				ColorScheme selectedScheme = colorSchemes[index];
				setupSchemeButtons(parent.getDisplay(), bSchemeButtons, selectedScheme, colorRegistry, imageRegistry);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

	private static void setupSchemeButtons(Display d, Button[] buttons, ColorScheme colorScheme,
			ColorRegistry colorRegistry, ImageRegistry imageRegistry) {
		for (int s = 0; s < colorScheme.getNumSets(); s++) {
			for (int v = 0; v < colorScheme.getNumVariants(); v++) {
				String symbolicName = colorScheme.getName() + "$" + s + "$" + v;

				Color color = colorRegistry.get(symbolicName);
				if (color == null) {
					colorRegistry.put(symbolicName, colorScheme.getRGB(s, v));
					color = colorRegistry.get(symbolicName);
				}
				Image swatch = imageRegistry.get(symbolicName);
				if (swatch == null) {
					ImageDescriptor swatchDescriptor = SWTWidgetUtils.createColorSwatch(d, color, 16, 16, false);
					imageRegistry.put(symbolicName, swatchDescriptor);
					swatch = imageRegistry.get(symbolicName);
				}
				buttons[s * 4 + v].setImage(swatch);
				buttons[s * 4 + v].setData(color.getRGB());
				buttons[s * 4 + v].setToolTipText("#" + SWTWidgetUtils.rgbToHex(color.getRGB()));
			}
		}
	}

	protected void createHexComposite(final Composite parent, RGB initialColor) {
		final RGB finitialColor = initialColor == null ? new RGB(0, 0, 0) : initialColor;

		Group gHex = new Group(parent, SWT.NONE);
		gHex.setLayout(new FillLayout());
		gHex.setText("Select from Hex");

		Composite cHex = new Composite(gHex, SWT.NONE);
		cHex.setLayout(new GridLayout(1, false));

		Composite cHexSelection = new Composite(cHex, SWT.NONE);
		GridLayout gl = new GridLayout(3, false);
		cHexSelection.setLayout(gl);

		Label lHexSelection = new Label(cHexSelection, SWT.NONE);
		lHexSelection.setText("Hex #");

		final Text tHexSelection = new Text(cHexSelection, SWT.BORDER);
		tHexSelection.setTextLimit(6);
		tHexSelection.setText(SWTWidgetUtils.rgbToHex(finitialColor));

		final Button bHexSwatch = new Button(cHexSelection, SWT.FLAT);
		setupHexButton(parent.getDisplay(), bHexSwatch, finitialColor, colorRegistry, imageRegistry);
		bHexSwatch.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object data = bHexSwatch.getData();
				if (data != null && data instanceof RGB) {
					select((RGB) data);
					return;
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		tHexSelection.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				String text = tHexSelection.getText();
				RGB rgb = SWTWidgetUtils.hexToRGB(text);
				if (rgb == null) {
					rgb = new RGB(0, 0, 0);
				}
				setupHexButton(parent.getDisplay(), bHexSwatch, rgb, colorRegistry, imageRegistry);
			}
		});
	}

	private static void setupHexButton(Display d, Button b, RGB rgb, ColorRegistry colorRegistry,
			ImageRegistry imageRegistry) {
		String symbolicName = Integer.toHexString(rgb.red) + Integer.toHexString(rgb.green)
				+ Integer.toHexString(rgb.blue);
		Color color = colorRegistry.get(symbolicName);
		if (color == null) {
			colorRegistry.put(symbolicName, rgb);
			color = colorRegistry.get(symbolicName);
		}
		imageRegistry.remove("HEX_COLOR");

		ImageDescriptor swatchDescriptor = SWTWidgetUtils.createColorSwatch(d, color, 16, 16, false);
		imageRegistry.put("HEX_COLOR", swatchDescriptor);
		Image swatch = imageRegistry.get("HEX_COLOR");

		b.setData(rgb);
		b.setToolTipText("#" + SWTWidgetUtils.rgbToHex(rgb));
		b.setImage(swatch);
	}

	protected void createCustomComposite(final Composite parent, RGB initialColor) {
		final RGB finitialColor = initialColor == null ? new RGB(0, 0, 0) : initialColor;

		Group gCustom = new Group(parent, SWT.NONE);
		gCustom.setLayout(new FillLayout());
		gCustom.setText("Select Custom");

		Composite cCustom = new Composite(gCustom, SWT.NONE);
		cCustom.setLayout(new GridLayout(1, false));

		Button bCustom = new Button(cCustom, SWT.PUSH);
		bCustom.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, true));
		bCustom.setText("Custom Color...");
		bCustom.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ColorDialog cd = new ColorDialog(parent.getShell(), SWT.APPLICATION_MODAL);
				cd.setRGB(finitialColor);
				RGB result = cd.open();
				if (result != null) {
					select(result);
				}
			};

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

}
