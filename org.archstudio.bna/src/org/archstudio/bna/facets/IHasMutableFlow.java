package org.archstudio.bna.facets;

import org.archstudio.swtutils.constants.Flow;

public interface IHasMutableFlow extends IHasFlow {

	public static final String USER_MAY_EDIT_FLOW = "UserMayEditFlow";

	public void setFlow(Flow flow);
}
