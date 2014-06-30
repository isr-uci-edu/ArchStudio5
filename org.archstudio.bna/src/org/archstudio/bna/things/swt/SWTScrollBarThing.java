package org.archstudio.bna.things.swt;

import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.SWT;

import com.google.common.collect.Lists;

@NonNullByDefault
public class SWTScrollBarThing extends AbstractControlThing {

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

	public static final IThingKey<Type> TYPE_KEY = ThingKey.create(Lists.newArrayList("type", SWTScrollBarThing.class));
	public static final IThingKey<Integer> MIN_KEY = ThingKey
			.create(Lists.newArrayList("min", SWTScrollBarThing.class));
	public static final IThingKey<Integer> MAX_KEY = ThingKey
			.create(Lists.newArrayList("max", SWTScrollBarThing.class));
	public static final IThingKey<Integer> VALUE_KEY = ThingKey.create(Lists.newArrayList("value",
			SWTScrollBarThing.class));

	public SWTScrollBarThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setType(Type.HORIZONTAL);
		setMin(0);
		setMax(100);
		setValue(50);
		super.initProperties();
	}

	public Type getType() {
		return get(TYPE_KEY);
	}

	public void setType(Type type) {
		set(TYPE_KEY, type);
	}

	public int getMin() {
		return get(MIN_KEY);
	}

	public void setMin(int value) {
		set(MIN_KEY, value);
	}

	public int getMax() {
		return get(MAX_KEY);
	}

	public void setMax(int value) {
		set(MAX_KEY, value);
	}

	public int getValue() {
		return get(VALUE_KEY);
	}

	public void setValue(int value) {
		set(VALUE_KEY, value);
	}
}
