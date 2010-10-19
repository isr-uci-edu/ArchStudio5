package org.archstudio.bna.things.utility;

import org.archstudio.bna.AbstractThing;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasMutableAssemblyData;

public class AssemblyRootThing extends AbstractThing implements IThing, IHasMutableAssemblyData {

	public AssemblyRootThing() {
		this(BNAUtils.generateUID(NoThing.class.getName()));
	}

	public AssemblyRootThing(String id) {
		super(id);
		initProperties();
	}

	public Class<? extends IThingPeer> getPeerClass() {
		return NoThingPeer.class;
	}

	protected void initProperties() {
		setIndexValue(0);
	}

	public String getAssemblyKind() {
		return getProperty(ASSEMBLY_KIND_PROPERTY_NAME);
	}

	public void setAssemblyKind(String assemblyKind) {
		setProperty(ASSEMBLY_KIND_PROPERTY_NAME, assemblyKind);
	}

	public int getIndexValue() {
		return getProperty(INDEX_VALUE_PROPERTY_NAME);
	}

	public void setIndexValue(int indexValue) {
		setProperty(INDEX_VALUE_PROPERTY_NAME, indexValue);
	}
}
