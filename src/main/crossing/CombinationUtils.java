package crossing;

import java.util.ArrayList;
import java.util.List;

public class CombinationUtils {

	private static void helper(List<int[]> combinations, int[] data, int start, int end, int index) {
		if (index == data.length) {
			int[] combination = data.clone();
			combinations.add(combination);
		} else if (start <= end) {
			data[index] = start;
			helper(combinations, data, start + 1, end, index + 1);
			helper(combinations, data, start + 1, end, index);
		}
	}

	public static <T> List<List<T>> generate(T[] data, int r) {
		List<int[]> combIndexes = new ArrayList<>();
		helper(combIndexes, new int[r], 0, data.length - 1, 0);

		List<List<T>> combinations = new ArrayList<>();
		for (var i : combIndexes) {
			List<T> list = new ArrayList<>();
			for (var d : i)
				list.add(data[d]);
			combinations.add(list);
		}
		return combinations;
	}
}
