package org.archstudio.sysutils;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.InvalidMarkException;

public class ExpandableIntBuffer {

	private static IntBuffer newDirectBuffer(int size) {
		ByteBuffer buffer = ByteBuffer.allocateDirect(size * 4);
		buffer.order(ByteOrder.nativeOrder());
		IntBuffer intBuffer = buffer.asIntBuffer();
		intBuffer.rewind();
		intBuffer.mark();
		return intBuffer;
	}

	IntBuffer internalBuffer = newDirectBuffer(1024);

	private void ensureAdditionalCapacity(int additionalInts) {
		if (internalBuffer.remaining() < additionalInts) {
			IntBuffer newInternalBuffer = newDirectBuffer((1 + internalBuffer.capacity()) * 2 + additionalInts);
			int position = internalBuffer.position();
			try {
				internalBuffer.reset();
			}
			catch (InvalidMarkException ignored) {
			}
			int markPosition = internalBuffer.position();
			internalBuffer.rewind();
			newInternalBuffer.put(internalBuffer);
			newInternalBuffer.position(markPosition);
			newInternalBuffer.mark();
			newInternalBuffer.position(position);
			internalBuffer = newInternalBuffer;
		}
	}

	public IntBuffer getBackingBuffer() {
		return internalBuffer;
	}

	public final int capacity() {
		return internalBuffer.capacity();
	}

	public final int position() {
		return internalBuffer.position();
	}

	public final Buffer position(int newPosition) {
		return internalBuffer.position(newPosition);
	}

	public IntBuffer slice() {
		return internalBuffer.slice();
	}

	public final int limit() {
		return internalBuffer.limit();
	}

	public final Buffer limit(int newLimit) {
		return internalBuffer.limit(newLimit);
	}

	public IntBuffer duplicate() {
		return internalBuffer.duplicate();
	}

	public final Buffer mark() {
		return internalBuffer.mark();
	}

	public final Buffer reset() {
		return internalBuffer.reset();
	}

	public IntBuffer asReadOnlyBuffer() {
		return internalBuffer.asReadOnlyBuffer();
	}

	public final Buffer clear() {
		return internalBuffer.clear();
	}

	public int get() {
		return internalBuffer.get();
	}

	public final Buffer flip() {
		return internalBuffer.flip();
	}

	public IntBuffer put(int f) {
		ensureAdditionalCapacity(1);
		return internalBuffer.put(f);
	}

	public int get(int index) {
		return internalBuffer.get(index);
	}

	public final Buffer rewind() {
		return internalBuffer.rewind();
	}

	public IntBuffer put(int index, int f) {
		return internalBuffer.put(index, f);
	}

	public final int remaining() {
		return internalBuffer.remaining();
	}

	public final boolean hasRemaining() {
		return internalBuffer.hasRemaining();
	}

	public IntBuffer get(int[] dst, int offset, int length) {
		return internalBuffer.get(dst, offset, length);
	}

	public boolean isReadOnly() {
		return internalBuffer.isReadOnly();
	}

	public IntBuffer get(int[] dst) {
		return internalBuffer.get(dst);
	}

	public IntBuffer put(IntBuffer src) {
		ensureAdditionalCapacity(src.capacity());
		return internalBuffer.put(src);
	}

	public IntBuffer put(int[] src, int offset, int length) {
		ensureAdditionalCapacity(length);
		return internalBuffer.put(src, offset, length);
	}

	public final IntBuffer put(int[] src) {
		ensureAdditionalCapacity(src.length);
		return internalBuffer.put(src);
	}

	public final boolean hasArray() {
		return internalBuffer.hasArray();
	}

	public final int[] array() {
		return internalBuffer.array();
	}

	public final int arrayOffset() {
		return internalBuffer.arrayOffset();
	}

	public IntBuffer compact() {
		return internalBuffer.compact();
	}

	public boolean isDirect() {
		return internalBuffer.isDirect();
	}

	@Override
	public String toString() {
		return internalBuffer.toString();
	}

	@Override
	public int hashCode() {
		return internalBuffer.hashCode();
	}

	@Override
	public boolean equals(Object ob) {
		return internalBuffer.equals(ob);
	}

	public int compareTo(IntBuffer that) {
		return internalBuffer.compareTo(that);
	}

	public ByteOrder order() {
		return internalBuffer.order();
	}

}
