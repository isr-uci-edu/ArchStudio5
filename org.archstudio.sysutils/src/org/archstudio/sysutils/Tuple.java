package org.archstudio.sysutils;


public class Tuple{

	static final boolean equalz(Object o1, Object o2){
		return o1 == null ? o2 == null : o1.equals(o2);
	}

	static final Object[] cloneArray(Object[] o, int index, int count){
		Object[] o2 = new Object[count];
		System.arraycopy(o, index, o2, 0, count);
		return o2;
	}

	Object[] elements;
	int offset;
	int count;

	boolean hashComputed = false;
	int hash;

	private Tuple(Object[] elements, int offset, int count){
		this.elements = elements;
		this.offset = offset;
		this.count = count;
	}

	public Tuple(Object o1){
		this(new Object[]{o1}, 0, 1);
	}

	public Tuple(Object o1, Object o2){
		this(new Object[]{o1, o2}, 0, 2);
	}

	public Tuple(Object o1, Object o2, Object o3){
		this(new Object[]{o1, o2, o3}, 0, 3);
	}

	public Tuple(Object... elements){
		// although generic, this is much slower than the other constructors
		this(cloneArray(elements, 0, elements.length), 0, elements.length);
	}

	@Override
	public int hashCode(){
		if(!hashComputed){
			int h = 1;
			int c = count;
			int o = offset;
			Object es[] = elements;
			while(c-- > 0){
				Object e = es[o++];
				h = 31 * h + (e == null ? 0 : e.hashCode());
			}
			hash = h;
			hashComputed = true;
		}
		return hash;
	}

	@Override
	public boolean equals(Object obj){
		if(obj == this){
			return true;
		}
		if(obj instanceof Tuple){
			Tuple tObj = (Tuple)obj;
			int c = count;
			if(c == tObj.count){
				int o1 = offset;
				int o2 = tObj.offset;
				Object es1[] = elements;
				Object es2[] = tObj.elements;
				while(c-- > 0){
					if(!equalz(es1[o1++], es2[o2++])){
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	public int getElementCount(){
		return count;
	}

	public Object getElement(int index){
		if(index < 0){
			throw new IndexOutOfBoundsException();
		}
		if(index >= count){
			throw new IndexOutOfBoundsException();
		}
		return elements[offset + index];
	}

	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("Tuple{");
		int c = count;
		int o = offset;
		Object es[] = elements;
		while(c-- > 0){
			Object e = es[o++];
			sb.append(e);
			if(c > 0){
				sb.append(", ");
			}
		}
		sb.append("}");
		return sb.toString();
	}

	public boolean startsWith(Object... prefix){
		int c = prefix.length;
		if(c <= elements.length){
			int o1 = offset;
			int o2 = 0;
			Object es1[] = elements;
			Object es2[] = prefix;
			while(c-- > 0){
				if(!equalz(es1[o1++], es2[o2++])){
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public boolean endsWith(Object... suffix){
		int c = suffix.length;
		if(c <= elements.length){
			int o1 = offset - 1;
			int o2 = c - 1;
			Object es1[] = elements;
			Object es2[] = suffix;
			while(c-- > 0){
				if(!equalz(es1[o1--], es2[o2--])){
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public Tuple subTuple(int beginIndex){
		return subTuple(beginIndex, count);
	}

	public Tuple subTuple(int beginIndex, int endIndex) throws IndexOutOfBoundsException{
		if(beginIndex < 0){
			throw new IndexOutOfBoundsException();
		}
		if(endIndex > count){
			throw new IndexOutOfBoundsException();
		}
		if(beginIndex > endIndex){
			throw new IndexOutOfBoundsException();
		}
		return beginIndex == 0 && endIndex == count ? this : new Tuple(elements, offset + beginIndex, endIndex - beginIndex);
	}
}
