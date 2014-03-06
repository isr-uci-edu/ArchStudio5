package org.archstudio.bna.facets;

import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IHasMutableHorizontalAlignment extends IHasHorizontalAlignment {

	public void setHorizontalAlignment(HorizontalAlignment h);
}
