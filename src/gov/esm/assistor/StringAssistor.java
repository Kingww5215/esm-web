package gov.esm.assistor;

import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * @author XueLiang
 * @date 2014年11月27日
 */
public class StringAssistor {
	private static final String empty = "";

	public static <T> String join(Collection<T> c, String separator) {
		if (c == null || c.size() < 1) {
			return empty;
		}
		if (separator == null) {
			separator = empty;
		}
		int capacity = (c.size() << 3);
		capacity += (c.size() - 1) * separator.length();
		StringBuilder cup = new StringBuilder(capacity);
		Iterator<T> it = c.iterator();
		int i = 0;
		while (it.hasNext()) {
			T t = it.next();
			cup.append(t == null ? empty : it.next().toString());
			if (++i < c.size()) {
				cup.append(separator);
			}
		}
		return cup.toString();
	}

	/**
	 * @param id
	 * @param c
	 * @return
	 */
	public static String join(int[] numbers, String separator) {
		if (numbers == null || numbers.length < 1) {
			return empty;
		}
		if (separator == null) {
			separator = empty;
		}
		int capacity = (numbers.length << 4);
		capacity += (numbers.length - 1) * separator.length();
		StringBuilder cup = new StringBuilder(capacity);
		int i = 0;
		for (; i < numbers.length - 1; i++) {
			cup.append(numbers[i]).append(separator);
		}
		cup.append(numbers[i]);
		return cup.toString();
	}
}
