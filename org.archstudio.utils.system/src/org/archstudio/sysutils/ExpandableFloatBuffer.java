package org.archstudio.sysutils;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.InvalidMarkException;

public class ExpandableFloatBuffer {

	private static FloatBuffer newDirectBuffer(int size) {
		ByteBuffer buffer = ByteBuffer.allocateDirect(size * 4);
		buffer.order(ByteOrder.nativeOrder());
		FloatBuffer floatBuffer = buffer.asFloatBuffer();
		floatBuffer.rewind();
		floatBuffer.mark();
		return floatBuffer;
	}

	FloatBuffer internalBuffer = newDirectBuffer(1024);

	private void ensureAdditionalCapacity(int additionalFloats) {
		if (internalBuffer.remaining() < additionalFloats) {
			FloatBuffer newInternalBuffer = newDirectBuffer((1 + internalBuffer.capacity()) * 2 + additionalFloats);
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

	public FloatBuffer getBackingBuffer() {
		return internalBuffer;
	}

	public void trim() {
		float[] values = new float[internalBuffer.position()];
		internalBuffer.rewind();
		internalBuffer.get(values);
		FloatBuffer newInternalBuffer = newDirectBuffer(values.length);
		newInternalBuffer.rewind();
		newInternalBuffer.put(values);
		internalBuffer = newInternalBuffer;
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

	public FloatBuffer slice() {
		return internalBuffer.slice();
	}

	public final int limit() {
		return internalBuffer.limit();
	}

	public final Buffer limit(int newLimit) {
		return internalBuffer.limit(newLimit);
	}

	public FloatBuffer duplicate() {
		return internalBuffer.duplicate();
	}

	public final Buffer mark() {
		return internalBuffer.mark();
	}

	public final Buffer reset() {
		return internalBuffer.reset();
	}

	public FloatBuffer asReadOnlyBuffer() {
		return internalBuffer.asReadOnlyBuffer();
	}

	public final Buffer clear() {
		return internalBuffer.clear();
	}

	public float get() {
		return internalBuffer.get();
	}

	public final Buffer flip() {
		return internalBuffer.flip();
	}

	public FloatBuffer put(float f) {
		ensureAdditionalCapacity(1);
		return internalBuffer.put(f);
	}

	public float get(int index) {
		return internalBuffer.get(index);
	}

	public final Buffer rewind() {
		return internalBuffer.rewind();
	}

	public FloatBuffer put(int index, float f) {
		return internalBuffer.put(index, f);
	}

	public final int remaining() {
		return internalBuffer.remaining();
	}

	public final boolean hasRemaining() {
		return internalBuffer.hasRemaining();
	}

	public FloatBuffer get(float[] dst, int offset, int length) {
		return internalBuffer.get(dst, offset, length);
	}

	public boolean isReadOnly() {
		return internalBuffer.isReadOnly();
	}

	public FloatBuffer get(float[] dst) {
		return internalBuffer.get(dst);
	}

	public FloatBuffer put(FloatBuffer src) {
		ensureAdditionalCapacity(src.capacity());
		return internalBuffer.put(src);
	}

	public FloatBuffer put(float[] src, int offset, int length) {
		ensureAdditionalCapacity(length);
		return internalBuffer.put(src, offset, length);
	}

	public final FloatBuffer put(float[] src) {
		ensureAdditionalCapacity(src.length);
		return internalBuffer.put(src);
	}

	public final boolean hasArray() {
		return internalBuffer.hasArray();
	}

	public final float[] array() {
		return internalBuffer.array();
	}

	public final int arrayOffset() {
		return internalBuffer.arrayOffset();
	}

	public FloatBuffer compact() {
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

	public int compareTo(FloatBuffer that) {
		return internalBuffer.compareTo(that);
	}

	public ByteOrder order() {
		return internalBuffer.order();
	}

}
