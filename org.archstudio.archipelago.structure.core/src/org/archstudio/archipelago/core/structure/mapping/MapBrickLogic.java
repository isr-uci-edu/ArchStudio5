package org.archstudio.archipelago.core.structure.mapping;

import static org.archstudio.sysutils.SystemUtils.castOrNull;

import java.awt.Dimension;
import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.structure.ArchipelagoStructureConstants;
import org.archstudio.archipelago.core.structure.StructureEditorSupport;
import org.archstudio.archipelago.structure.core.Activator;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasCount;
import org.archstudio.bna.facets.IHasFontData;
import org.archstudio.bna.facets.IHasGradientFill;
import org.archstudio.bna.facets.IHasLineWidth;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasMutableWorld;
import org.archstudio.bna.facets.IHasSecondaryColor;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.things.glass.RectangleGlassThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.myx.fw.Services;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl.bna.facets.IHasXArchID;
import org.archstudio.xadl.bna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.base.Function;

/**
 * Maps xADL Bricks (i.e., Components or Connectors) to BNA Rectangle Assemblies.
 */
public class MapBrickLogic extends AbstractXADLToBNAPathLogic<RectangleGlassThing> implements IPropertyChangeListener {
	protected final Services services;
	protected final Dimension defaultSize;
	protected final String defaultColorPref;
	protected final String defaultFontPref;
	protected final int defaultCount;
	protected MirrorValueLogic mvl = null;

	public MapBrickLogic(Services services, IXArchADT xarch, ObjRef rootObjRef, String objRefPath,
			Dimension defaultSize, String defaultColorPref, int defaultCount, String defaultFontPref) {
		super(xarch, rootObjRef, objRefPath);
		this.services = services;
		this.defaultSize = defaultSize;
		this.defaultColorPref = defaultColorPref;
		this.defaultCount = defaultCount;
		this.defaultFontPref = defaultFontPref;
	}

	@Override
	public void init() {
		super.init();
		syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(Assemblies.TEXT_KEY), IHasText.TEXT_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, true);
		addBNAUpdater(new IBNAUpdater() {

			@Override
			public void updateBNA(ObjRef objRef, String xadlPath, XArchADTModelEvent evt, RectangleGlassThing rootThing) {

				updateSubstructure(objRef, xadlPath, evt, rootThing);
			}
		});

		Activator.getDefault().getPreferenceStore().addPropertyChangeListener(this);

		mvl = getBNAWorld().getThingLogicManager().addThingLogic(MirrorValueLogic.class);
	}

	@Override
	public void destroy() {
		Activator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		super.destroy();
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		RGB defaultColor = PreferenceConverter.getColor(Activator.getDefault().getPreferenceStore(), defaultColorPref);
		FontData defaultFont = PreferenceConverter.getFontData(Activator.getDefault().getPreferenceStore(),
				defaultFontPref);
		int defaultLineWidth = Activator.getDefault().getPreferenceStore()
				.getInt(ArchipelagoStructureConstants.PREF_LINE_WIDTH);

		for (RectangleGlassThing thing : getAddedThings()) {
			if (event.getProperty().equals(defaultColorPref)) {
				RGB oldColor = toRGB(event.getOldValue());
				if (Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).get(IHasColor.COLOR_KEY).equals(oldColor)) {
					Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasColor.COLOR_KEY, defaultColor);
				}
			}

			Assemblies.TEXT_KEY.get(thing, getBNAModel()).set(IHasFontData.FONT_NAME_KEY, defaultFont.getName());
			Assemblies.TEXT_KEY.get(thing, getBNAModel()).set(IHasFontData.FONT_SIZE_KEY, defaultFont.getHeight());
			Assemblies.TEXT_KEY.get(thing, getBNAModel()).set(IHasFontData.FONT_STYLE_KEY,
					FontStyle.fromSWT(defaultFont.getStyle()));
			Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasLineWidth.LINE_WIDTH_KEY, defaultLineWidth);
		}
	}

	private RGB toRGB(Object value) {
		if (value instanceof RGB) {
			return (RGB) value;
		}
		String[] rgbs = ((String) value).split(",");
		return new RGB(Integer.valueOf(rgbs[0]), Integer.valueOf(rgbs[1]), Integer.valueOf(rgbs[2]));
	}

	@Override
	protected RectangleGlassThing addThing(List<ObjRef> relLineageRefs, ObjRef objRef) {

		RGB defaultColor = PreferenceConverter.getColor(Activator.getDefault().getPreferenceStore(), defaultColorPref);
		FontData defaultFont = PreferenceConverter.getFontData(Activator.getDefault().getPreferenceStore(),
				defaultFontPref);
		int defaultLineWidth = Activator.getDefault().getPreferenceStore()
				.getInt(ArchipelagoStructureConstants.PREF_LINE_WIDTH);

		Point newPointSpot = ArchipelagoUtils.findOpenSpotForNewThing(getBNAWorld().getBNAModel());

		RectangleGlassThing thing = Assemblies.createRectangleWithWorld(getBNAWorld(), null, null);
		thing.setBoundingBox(new Rectangle(newPointSpot.x, newPointSpot.y, defaultSize.width, defaultSize.height));
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasColor.COLOR_KEY, defaultColor);
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasGradientFill.GRADIENT_FILLED_KEY, true);
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasCount.COUNT_KEY, defaultCount);
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasLineWidth.LINE_WIDTH_KEY, defaultLineWidth);
		Assemblies.TEXT_KEY.get(thing, getBNAModel()).set(IHasFontData.FONT_NAME_KEY, defaultFont.getName());
		Assemblies.TEXT_KEY.get(thing, getBNAModel()).set(IHasFontData.FONT_SIZE_KEY, defaultFont.getHeight());
		Assemblies.TEXT_KEY.get(thing, getBNAModel()).set(IHasFontData.FONT_STYLE_KEY,
				FontStyle.fromSWT(defaultFont.getStyle()));

		mvl.mirrorValue(Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()), IHasColor.COLOR_KEY,
				Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()), IHasSecondaryColor.SECONDARY_COLOR_KEY,
				new Function<RGB, RGB>() {

					@Override
					@Nullable
					public RGB apply(@Nullable RGB input) {
						return BNAUtils.adjustBrightness(input, 1.5f);
					}
				});

		UserEditableUtils.addEditableQualities(thing, IHasMutableSelected.USER_MAY_SELECT,
				IHasMutableSize.USER_MAY_RESIZE, IRelativeMovable.USER_MAY_MOVE);
		UserEditableUtils.addEditableQualities(Assemblies.TEXT_KEY.get(thing, getBNAModel()),
				IHasMutableText.USER_MAY_EDIT_TEXT);
		UserEditableUtils.addEditableQualities(Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()),
				IHasMutableColor.USER_MAY_EDIT_COLOR);

		return thing;
	}

	protected void updateSubstructure(ObjRef objRef, String xadlPath, XArchADTModelEvent evt,
			RectangleGlassThing rootThing) {

		IHasMutableWorld worldThing = castOrNull(
				BNAPath.resolve(getBNAModel(), rootThing, BNAPath.create(Assemblies.WORLD_KEY)), IHasMutableWorld.class);
		if (worldThing != null) {
			ObjRef innerStructureRef = null;

			ObjRef subStructureRef = (ObjRef) xarch.get(objRef, "subStructure");
			if (subStructureRef != null) {
				innerStructureRef = (ObjRef) xarch.get(subStructureRef, "innerStructureLink");
			}
			// If innerStructureRef is null, then we need to remove the world from the worldThing.
			// Otherwise, we need to add it and hook it up.
			if (innerStructureRef == null) {
				worldThing.remove(IHasWorld.WORLD_KEY);
				worldThing.set(IHasObjRef.OBJREF_KEY, null);
			}
			else {
				ObjRef documentRootRef = xarch.getDocumentRootRef(objRef);
				IBNAWorld internalWorld = StructureEditorSupport.setupWorld(services, xarch, documentRootRef,
						innerStructureRef);
				if (internalWorld != null) {
					worldThing.setWorld(internalWorld);
					worldThing.set(IHasObjRef.OBJREF_KEY, subStructureRef);
				}
			}
		}
	}

}
