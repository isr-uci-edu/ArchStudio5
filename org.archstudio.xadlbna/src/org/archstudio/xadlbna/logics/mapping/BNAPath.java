package org.archstudio.xadlbna.logics.mapping;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingRefKey;

import com.google.common.collect.Lists;

public class BNAPath {

	public static BNAPath create(IThingRefKey<?>... thingRefKeys) {
		return new BNAPath(Arrays.asList(thingRefKeys));
	}

	public static IThing resolve(IBNAModel bnaModel, IThing rootThing, BNAPath targetThingPath) {
		for (Iterator<IThingRefKey<?>> i = targetThingPath.keyPath.iterator(); rootThing != null && i.hasNext();) {
			IThingRefKey<?> thingRefKey = i.next();
			rootThing = thingRefKey.get(rootThing, bnaModel);
		}
		return rootThing;
	}

	private final List<IThingRefKey<?>> keyPath;

	protected BNAPath(List<IThingRefKey<?>> keyPath) {
		this.keyPath = Lists.newArrayList(keyPath);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (keyPath == null ? 0 : keyPath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BNAPath other = (BNAPath) obj;
		if (keyPath == null) {
			if (other.keyPath != null) {
				return false;
			}
		}
		else if (!keyPath.equals(other.keyPath)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "BNAPath [keyPath=" + keyPath + "]";
	}

	//	private static final String[] getPartNames(IThing thing) {
	//		List<String> partList = new ArrayList<String>();
	//		do {
	//			String part = AssemblyUtils.getPartName(thing);
	//			if (part == null) {
	//				break;
	//			}
	//			partList.add(part);
	//			IAssembly assembly = AssemblyUtils.getAssemblyWithPart(thing);
	//			thing = assembly != null ? assembly.getRootThing() : null;
	//		}
	//		while (thing != null);
	//		return partList.toArray(new String[partList.size()]);
	//	}
	//
	//	private final String[] partNames;
	//	private final int partNameOffset;
	//
	//	private final int pathLength;
	//
	//	private final Object propertyName;
	//
	//	private final int hashCode;
	//
	//	public BNAPath(String[] partNames, int partNameOffset, int pathLength, Object propertyName) {
	//		this.partNames = partNames;
	//		this.partNameOffset = partNameOffset;
	//		this.pathLength = pathLength;
	//		this.propertyName = propertyName;
	//		this.hashCode = Arrays.asList(partNames).subList(partNameOffset, partNameOffset + pathLength).hashCode()
	//				^ propertyName.hashCode();
	//	}
	//
	//	@Override
	//	public int hashCode() {
	//		return hashCode;
	//	}
	//
	//	@Override
	//	public boolean equals(Object obj) {
	//		if (obj == this) {
	//			return true;
	//		}
	//		if (obj instanceof BNAPath) {
	//			BNAPath o = (BNAPath) obj;
	//			if (pathLength == o.pathLength && propertyName.equals(o.propertyName)) {
	//				return Arrays.asList(partNames).subList(partNameOffset, partNameOffset + pathLength)
	//						.equals(Arrays.asList(o.partNames).subList(o.partNameOffset, o.partNameOffset + o.pathLength));
	//			}
	//		}
	//		return false;
	//	}
	//
	//	public BNAPath(String[] partNames, Object propertyName) {
	//		this(partNames, 0, partNames.length, propertyName);
	//	}
	//
	//	public BNAPath(IThing thing, Object propertyName) {
	//		this(getPartNames(thing), propertyName);
	//	}
	//
	//	public int getLength() {
	//		return pathLength;
	//	}
	//
	//	public String getPartName(int index) {
	//		if (index < 0 || index >= pathLength) {
	//			throw new IndexOutOfBoundsException();
	//		}
	//		return partNames[partNameOffset + index];
	//	}
	//
	//	public Object getPropertyName() {
	//		return propertyName;
	//	}
	//
	//	public boolean hasPropertyPartNames(Object propertyName, String... partNames) {
	//		return BNAUtils.nulleq(this.propertyName, propertyName) && hasPartNames(partNames);
	//	}
	//
	//	public boolean hasPartNames(String... partNames) {
	//		if (partNames.length == pathLength) {
	//			int iP = partNameOffset;
	//			int iPP = 0;
	//			int c = pathLength;
	//			while (c-- > 0) {
	//				String partName = partNames[iP++];
	//				String partsPropertyName = partNames[iPP++];
	//				if (!partName.equals(partsPropertyName)) {
	//					return false;
	//				}
	//			}
	//			return true;
	//		}
	//		return false;
	//	}
	//
	//	@Override
	//	public String toString() {
	//		return "" + Arrays.asList(partNames) + ":" + propertyName;
	//	}
	//
	//	public static IThing resolve(IBNAModel model, IThing thing, BNAPath thingPath) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
}
