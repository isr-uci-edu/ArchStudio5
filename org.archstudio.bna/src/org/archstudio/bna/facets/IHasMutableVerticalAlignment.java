package org.archstudio.bna.facets;

import org.archstudio.swtutils.constants.VerticalAlignment;
import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IHasMutableVerticalAlignment extends IHasVerticalAlignment {

	public void setVerticalAlignment(VerticalAlignment verticalAlignment);

}
