package org.archstudio.bna.things.swt;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.SWT;

@NonNullByDefault
public class SWTScrollBarThing extends SWTScrollBarThingBase {

	public static enum Type {
		HORIZONTAL(SWT.HORIZONTAL), VERTICAL(SWT.VERTICAL);

		final int swtStyle;

		private Type(int swtStyle) {
			this.swtStyle = swtStyle;
		}

		public int getSwtStyle() {
			return swtStyle;
		}

	}

	public SWTScrollBarThing(@Nullable Object id) {
		super(id);
	}

}
