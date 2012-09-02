package org.archstudio.bna.facets;

import java.awt.Insets;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IHasMutableLocalInsets extends IHasLocalInsets {

	public void setLocalInsets(Insets insets);

}
