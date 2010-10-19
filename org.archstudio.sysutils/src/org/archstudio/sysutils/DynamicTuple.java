package org.archstudio.sysutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicTuple
	extends Tuple{

	public DynamicTuple(Object[] elements, int offset, int count){
		super(cloneArray(elements, offset, count), 0, count);
	}

	public DynamicTuple(Object... elements){
		super(elements);
	}

	@Override
	public int hashCode(){
		hashComputed = false;
		return super.hashCode();
	}

	public Object setElement(int index, Object element){
		if(index < 0){
			throw new IndexOutOfBoundsException();
		}
		if(index >= count){
			throw new IndexOutOfBoundsException();
		}
		Object oldElement = elements[offset + index];
		elements[offset + index] = element;
		return oldElement;
	}

	@Override
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
		return new DynamicTuple(elements, offset + beginIndex, endIndex - beginIndex);
	}

	public static void main(String[] args){

		for(int repeat = 0; repeat < 5; repeat++){

			final int iterations = 10000000;
			long startTime, ellapsedTime;
			String s1 = "&stuckToThingID&";
			String s2 = "boudingBox";
			boolean test;

			System.err.println();
			System.err.println("Test                           #" + (repeat + 1));

			{
				String[] staticKey = new String[]{s1, s2};
				startTime = System.nanoTime();
				for(int i = 0; i < iterations; i++){
					String[] key = new String[]{s1, s2};
					test = Arrays.equals(key, staticKey);
					test = staticKey[0].equals(s1);
				}
				ellapsedTime = System.nanoTime() - startTime;
				System.err.println("Array:                         " + (double)ellapsedTime / iterations);
			}

			{
				Tuple staticKey = new Tuple(new Object[]{s1, s2});
				DynamicTuple key = new DynamicTuple(s1, s2);
				startTime = System.nanoTime();
				for(int i = 0; i < iterations; i++){
					key.setElement(1, s2);
					test = key.equals(staticKey);
					test = staticKey.startsWith(s1);
				}
				ellapsedTime = System.nanoTime() - startTime;
				System.err.println("DynamicTuple:                  " + (double)ellapsedTime / iterations);
			}

			{
				Tuple staticKey = new Tuple(s1, s2);
				startTime = System.nanoTime();
				for(int i = 0; i < iterations; i++){
					Tuple key = new Tuple(s1, s2);
					test = key.equals(staticKey);
					test = staticKey.startsWith(s1);
				}
				ellapsedTime = System.nanoTime() - startTime;
				System.err.println("Tuple:                         " + (double)ellapsedTime / iterations);
			}

			{
				Tuple staticKey = new Tuple(new Object[]{s1, s2});
				startTime = System.nanoTime();
				for(int i = 0; i < iterations; i++){
					Tuple key = new Tuple(new Object[]{s1, s2});
					test = key.equals(staticKey);
					test = staticKey.startsWith(s1);
				}
				ellapsedTime = System.nanoTime() - startTime;
				System.err.println("Tuple (Object... constructor): " + (double)ellapsedTime / iterations);
			}

			{
				List<Object> staticKey = Arrays.asList(new Object[]{s1, s2});
				List<Object> key = new ArrayList<Object>(Arrays.asList(new Object[]{s1, s2}));
				startTime = System.nanoTime();
				for(int i = 0; i < iterations; i++){
					key.set(1, s2);
					test = key.equals(staticKey);
					test = staticKey.get(0).equals(s1);
				}
				ellapsedTime = System.nanoTime() - startTime;
				System.err.println("Dynamic ArrayList:             " + (double)ellapsedTime / iterations);
			}

			{
				List<Object> staticKey = Arrays.asList(new Object[]{s1, s2});
				startTime = System.nanoTime();
				for(int i = 0; i < iterations; i++){
					List<Object> key = Arrays.asList(new Object[]{s1, s2});
					test = key.equals(staticKey);
					test = staticKey.get(0).equals(s1);
				}
				ellapsedTime = System.nanoTime() - startTime;
				System.err.println("ArrayList:                     " + (double)ellapsedTime / iterations);
			}

			{
				String staticKey = s1 + s2;
				startTime = System.nanoTime();
				for(int i = 0; i < iterations; i++){
					String key = s1 + s2;
					test = key.equals(staticKey);
					test = staticKey.startsWith(s1);
				}
				ellapsedTime = System.nanoTime() - startTime;
				System.err.println("Concat:                        " + (double)ellapsedTime / iterations);
			}
		}
	}
}
