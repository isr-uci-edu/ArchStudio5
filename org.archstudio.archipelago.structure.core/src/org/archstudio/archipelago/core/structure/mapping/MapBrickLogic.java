package org.archstudio.archipelago.core.structure.mapping;

import static org.archstudio.sysutils.SystemUtils.castOrNull;

import java.awt.Dimension;
import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoConstants;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.structure.StructureEditorSupport;
import org.archstudio.archipelago.structure.core.Activator;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableFontData;
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
import org.archstudio.bna.logics.information.HighlightLogic;
import org.archstudio.bna.things.shapes.RectangleThing;
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
public class MapBrickLogic extends AbstractXADLToBNAPathLogic<RectangleThing> implements IPropertyChangeListener {
	protected final Services services;
	protected final Dimension defaultSize;
	protected final String defaultColorPref;
	protected final String defaultFontPref;
	protected final int defaultCount;

	protected RGB defaultColor;
	protected FontData defaultFont;
	protected int defaultLineWidth;
	protected MirrorValueLogic mvl;

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
			public void updateBNA(ObjRef objRef, String xadlPath, XArchADTModelEvent evt, RectangleThing rootThing) {

				updateSubstructure(objRef, xadlPath, evt, rootThing);
			}
		});

		Activator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
		org.archstudio.archipelago.core.Activator.getDefault().getPreferenceStore().addPropertyChangeListener(this);

		loadPreferences();
		mvl = getBNAWorld().getThingLogicManager().addThingLogic(MirrorValueLogic.class);
	}

	protected void loadPreferences() {
		defaultColor = PreferenceConverter.getColor(Activator.getDefault().getPreferenceStore(), defaultColorPref);
		defaultFont = PreferenceConverter.getFontData(Activator.getDefault().getPreferenceStore(), defaultFontPref);
		defaultLineWidth = org.archstudio.archipelago.core.Activator.getDefault().getPreferenceStore()
				.getInt(ArchipelagoConstants.PREF_LINE_WIDTH);
	}

	@Override
	public void destroy() {
		Activator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		org.archstudio.archipelago.core.Activator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		super.destroy();
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		loadPreferences();

		for (RectangleThing thing : getAddedThings()) {
			if (event.getProperty().equals(defaultColorPref)) {
				RGB oldColor = toRGB(event.getOldValue());
				if (thing.getColor().equals(oldColor)) {
					thing.setColor(defaultColor);
				}
			}

			((IHasMutableFontData) Assemblies.TEXT_KEY.get(thing, getBNAModel())).setFontName(defaultFont.getName());
			((IHasMutableFontData) Assemblies.TEXT_KEY.get(thing, getBNAModel())).setFontSize(defaultFont.getHeight());
			((IHasMutableFontData) Assemblies.TEXT_KEY.get(thing, getBNAModel())).setFontStyle(FontStyle
					.fromSWT(defaultFont.getStyle()));

			thing.setLineWidth(defaultLineWidth);
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
	protected RectangleThing addThing(List<ObjRef> relLineageRefs, ObjRef objRef) {

		Point newPointSpot = ArchipelagoUtils.getNewThingSpot(getBNAWorld().getBNAModel());

		RectangleThing thing = Assemblies.addWorld(getBNAWorld(), null,
				Assemblies.createRectangle(getBNAWorld(), null, null));
		thing.setBoundingBox(new Rectangle(newPointSpot.x, newPointSpot.y, defaultSize.width, defaultSize.height));
		thing.setColor(defaultColor);
		thing.setGradientFilled(true);
		thing.setCount(defaultCount);
		thing.setLineWidth(defaultLineWidth);

		((IHasMutableFontData) Assemblies.TEXT_KEY.get(thing, getBNAModel())).setFontName(defaultFont.getName());
		((IHasMutableFontData) Assemblies.TEXT_KEY.get(thing, getBNAModel())).setFontSize(defaultFont.getHeight());
		((IHasMutableFontData) Assemblies.TEXT_KEY.get(thing, getBNAModel())).setFontStyle(FontStyle
				.fromSWT(defaultFont.getStyle()));

		mvl.mirrorValue(thing, IHasColor.COLOR_KEY, thing, IHasSecondaryColor.SECONDARY_COLOR_KEY,
				new Function<RGB, RGB>() {

					@Override
					@Nullable
					public RGB apply(@Nullable RGB input) {
						return BNAUtils.adjustBrightness(input, 1.5f);
					}
				});

		UserEditableUtils.addEditableQualities(thing, IHasMutableSelected.USER_MAY_SELECT,
				IHasMutableSize.USER_MAY_RESIZE, IRelativeMovable.USER_MAY_MOVE, HighlightLogic.USER_MAY_HIGHLIGHT,
				IHasColor.USER_MAY_COPY_COLOR, IHasMutableColor.USER_MAY_EDIT_COLOR);
		UserEditableUtils.addEditableQualities(Assemblies.TEXT_KEY.get(thing, getBNAModel()),
				IHasMutableText.USER_MAY_EDIT_TEXT);

		return thing;
	}

	protected void updateSubstructure(ObjRef objRef, String xadlPath, XArchADTModelEvent evt, RectangleThing rootThing) {

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
