package org.archstudio.archipelago.core.structure;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.archstudio.myxgen.MyxGenBrick;
import org.archstudio.myxgen.MyxGenInterface;
import org.archstudio.myxgen.eclipse.extension.MyxGenWorkspaceExtensions;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl.bna.utils.XArchADTOperations;
import org.archstudio.xadl3.domain_3_0.Domain;
import org.archstudio.xadl3.domain_3_0.DomainExtension;
import org.archstudio.xadl3.domain_3_0.DomainType;
import org.archstudio.xadl3.domain_3_0.Domain_3_0Factory;
import org.archstudio.xadl3.domain_3_0.Domain_3_0Package;
import org.archstudio.xadl3.implementation_3_0.ImplementationExtension;
import org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package;
import org.archstudio.xadl3.lookupimplementation_3_0.LookupImplementation;
import org.archstudio.xadl3.lookupimplementation_3_0.Lookupimplementation_3_0Factory;
import org.archstudio.xadl3.lookupimplementation_3_0.Lookupimplementation_3_0Package;
import org.archstudio.xadl3.myxgen_3_0.MyxGen;
import org.archstudio.xadl3.myxgen_3_0.Myxgen_3_0Factory;
import org.archstudio.xadl3.myxgen_3_0.Myxgen_3_0Package;
import org.archstudio.xadl3.structure_3_0.Brick;
import org.archstudio.xadl3.structure_3_0.Direction;
import org.archstudio.xadl3.structure_3_0.Interface;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Factory;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

public class StructureAssignMyxGenLogic extends AbstractThingLogic implements IBNAMenuListener {

	protected final IXArchADT xarch;

	public StructureAssignMyxGenLogic(IBNAWorld world, IXArchADT xarch) {
		super(world);
		this.xarch = xarch;
	}

	@Override
	synchronized public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu) {
		IThing thing = Assemblies.getThingWithProperty(model, firstOrNull(things), IHasObjRef.OBJREF_KEY);
		if (thing != null) {
			final ObjRef objRef = thing.get(IHasObjRef.OBJREF_KEY);
			if (objRef != null) {
				if (XadlUtils.isBrick(xarch, objRef)) {
					MenuManager myxGenMenu = new MenuManager("Assign MyxGen Brick...");
					myxGenMenu.addMenuListener(new IMenuListener() {

						@Override
						public void menuAboutToShow(IMenuManager manager) {
							populateMenuWithMyxGenBricks(objRef, manager);
						}
					});
					menu.add(myxGenMenu);
					myxGenMenu.add(new Action("Place holder needed to show menu") {
						@Override
						public void run() {
						}
					});
				}
			}
		}
	}

	protected void populateMenuWithMyxGenBricks(final ObjRef objRef, IMenuManager manager) {
		manager.removeAll();
		Multimap<String, MyxGenBrick> allMyxGenBricks = MyxGenWorkspaceExtensions.getActiveMyxGenBricks();
		for (final Entry<String, Collection<MyxGenBrick>> e : SystemUtils.sortedByKey(allMyxGenBricks.asMap()
				.entrySet())) {
			IMenuManager projectMenu = new MenuManager(e.getKey());
			manager.add(projectMenu);
			List<MyxGenBrick> myxGenBricks = Lists.newArrayList(e.getValue());
			Collections.sort(myxGenBricks, new Comparator<MyxGenBrick>() {

				@Override
				public int compare(MyxGenBrick o1, MyxGenBrick o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			for (final MyxGenBrick myxGenBrick : myxGenBricks) {
				projectMenu.add(new Action(myxGenBrick.getName()) {

					@Override
					public void run() {
						assignMyxGenBrick(objRef, myxGenBrick);
					}
				});
			}
		}
	}

	protected void assignMyxGenBrick(ObjRef objRef, MyxGenBrick myxGenBrick) {
		final XArchADTOperations xarch = new XArchADTOperations(this.xarch);

		Domain_3_0Factory domainFactory = XArchADTProxy.proxy(xarch, Domain_3_0Package.eINSTANCE.getNsURI());
		Lookupimplementation_3_0Factory lookupFactory = XArchADTProxy.proxy(xarch,
				Lookupimplementation_3_0Package.eINSTANCE.getNsURI());
		Myxgen_3_0Factory myxGenFactory = XArchADTProxy.proxy(xarch, Myxgen_3_0Package.eINSTANCE.getNsURI());
		Structure_3_0Factory structureFactory = XArchADTProxy.proxy(xarch, Structure_3_0Package.eINSTANCE.getNsURI());

		Brick brick = XArchADTProxy.proxy(xarch, objRef);
		brick.setSubStructure(null);

		{
			ImplementationExtension impl = XadlUtils.createExt(xarch, brick,
					Implementation_3_0Package.Literals.IMPLEMENTATION_EXTENSION);

			MyxGen myxGen = myxGenFactory.createMyxGen();
			myxGen.setId(UIDGenerator.generateUID());
			myxGen.setBrickID(myxGenBrick.getId());

			impl.getImplementation().clear();
			impl.getImplementation().add(myxGen);
		}

		{
			Multimap<String, Interface> oldIfaces = Multimaps.index(brick.getInterface().iterator(),
					new Function<Interface, String>() {

						@Override
						public String apply(Interface iface) {
							LookupImplementation limpl = XArchADTProxy.proxy(xarch, XadlUtils.getImplementation(xarch,
									XArchADTProxy.unproxy(iface),
									Lookupimplementation_3_0Package.Literals.LOOKUP_IMPLEMENTATION));
							if (limpl != null) {
								String lookup = limpl.getLookup();
								return lookup != null ? lookup : "";
							}

							return "";
						}
					});
			List<Interface> newIfaces = Lists.newArrayList();

			MyxGenBrick currentMyxGenBrick = myxGenBrick;
			while (currentMyxGenBrick != null) {
				for (MyxGenInterface mif : currentMyxGenBrick.getInterfaces()) {

					Interface bif = SystemUtils.firstOrNull(oldIfaces.get(mif.getName()));
					if (bif == null) {
						bif = structureFactory.createInterface();
						bif.setId(UIDGenerator.generateUID());
						brick.getInterface().add(bif);
					}
					newIfaces.add(bif);

					bif.setName(mif.getName());
					bif.setDirection(Direction.getByName(mif.getDirection().name().toLowerCase()));

					ImplementationExtension impl = XadlUtils.createExt(xarch, bif,
							Implementation_3_0Package.Literals.IMPLEMENTATION_EXTENSION);

					LookupImplementation lookup = lookupFactory.createLookupImplementation();
					lookup.setId(UIDGenerator.generateUID());
					lookup.setLookup(mif.getId());

					impl.getImplementation().clear();
					impl.getImplementation().add(lookup);

					DomainExtension domainExt = XadlUtils.createExt(xarch, bif,
							Domain_3_0Package.Literals.DOMAIN_EXTENSION);
					Domain domain = domainFactory.createDomain();
					domain.setType(DomainType.getByName(mif.getDomain()));
					domainExt.setDomain(domain);
				}
				String parentBrickID = currentMyxGenBrick.getParentBrickId();
				currentMyxGenBrick = MyxGenWorkspaceExtensions.getActiveMyxGenBrick(parentBrickID);
			}

			List<Interface> doomedIfaces = Lists.newArrayList(oldIfaces.values());
			doomedIfaces.removeAll(newIfaces);
			brick.getInterface().removeAll(doomedIfaces);

			xarch.done("Assign MyxGen Brick");
		}
	}
}
