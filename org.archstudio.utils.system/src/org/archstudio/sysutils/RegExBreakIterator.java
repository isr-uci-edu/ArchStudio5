package org.archstudio.sysutils;

import java.text.BreakIterator;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;

/**
 * Modified from a class by:
 *
 * @author Sergey Groznyh
 */
public class RegExBreakIterator extends BreakIterator {
	private Pattern prepattern, postpattern;
	private String text;
	private int[] breaks = new int[] { 0 };
	private int pos = 0;

	public RegExBreakIterator(String preregex, String postregex) {
		this.prepattern = preregex != null && preregex.length() > 0 ? Pattern.compile(preregex) : null;
		this.postpattern = postregex != null && postregex.length() > 0 ? Pattern.compile(postregex) : null;
	}

	/**
	 * Calculate break positions eagerly parallel to reading text.
	 */
	@Override
	public void setText(CharacterIterator ci) {
		StringBuffer sb = new StringBuffer(ci.getEndIndex() - ci.getBeginIndex());
		for (char c = ci.first(); c != CharacterIterator.DONE; c = ci.next()) {
			sb.append(c);
		}
		text = sb.toString();
		Set<Integer> indeces = Sets.newHashSet(0);
		if (prepattern != null) {
			for (Matcher m = prepattern.matcher(text); m.find();) {
				indeces.add(m.start());
			}
		}
		if (postpattern != null) {
			for (Matcher m = postpattern.matcher(text); m.find();) {
				indeces.add(m.end());
			}
		}
		indeces.add(text.length());
		List<Integer> sortedIndeces = Lists.newArrayList(indeces);
		Collections.sort(sortedIndeces);
		breaks = Ints.toArray(sortedIndeces);
	}

	@Override
	public CharacterIterator getText() {
		return new StringCharacterIterator(text);
	}

	@Override
	public int first() {
		return breaks[pos = 0];
	}

	@Override
	public int last() {
		return breaks[pos = breaks.length - 1];
	}

	@Override
	public int current() {
		return breaks[pos];
	}

	@Override
	public int next() {
		return pos == breaks.length - 1 ? DONE : breaks[++pos];
	}

	@Override
	public int previous() {
		return pos == 0 ? DONE : breaks[--pos];
	}

	@Override
	public int next(int n) {
		return checkhit(pos + n);
	}

	@Override
	public int following(int n) {
		return adjacent(n, 1);
	}

	@Override
	public int preceding(int n) {
		return adjacent(n, -1);
	}

	private int checkhit(int hit) {
		if (hit < 0 || hit >= breaks.length) {
			return DONE;
		}
		else {
			return breaks[pos = hit];
		}
	}

	private int adjacent(int n, int bias) {
		int hit = Arrays.binarySearch(breaks, n);
		int offset = hit < 0 ? bias < 0 ? -1 : -2 : 0;
		return checkhit(Math.abs(hit) + bias + offset);
	}
}
