//package crossing;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class TestCase {
//
//	@Test
//	public void cross1() throws Exception {
//		var wolf = new Animal(1, "Волк");
//		var goat = new Animal(2, "Коза");
//		var cabbage = new Animal(3, "Капуста");
//
//		wolf.victims.add(goat);
//		goat.victims.add(cabbage);
//		goat.predators.add(wolf);
//		cabbage.predators.add(goat);
//
//		List<Animal> list = new ArrayList<>();
//		list.add(wolf);
//		list.add(goat);
//		list.add(cabbage);
//
//		var root = new SourceCrossingStateState(list, 1);
//		var finalStates = root.travers();
//
//		var solutions = printSolutions(finalStates);
//		Assert.assertEquals(1, solutions.size());
//		var solution = solutions.get(0);
//
//		Assert.assertEquals(solution.get(0).level, 1);
//		Assert.assertEquals(solution.get(0).candidates.iterator().next().id, 2);
//
//		Assert.assertEquals(solution.get(1).level, 2);
//		Assert.assertEquals(solution.get(1).candidates.size(), 0);
//
//		Assert.assertEquals(solution.get(2).level, 3);
//		Assert.assertEquals(solution.get(2).candidates.iterator().next().id, 1);
//
//		Assert.assertEquals(solution.get(3).level, 4);
//		Assert.assertEquals(solution.get(3).candidates.iterator().next().id, 2);
//
//		Assert.assertEquals(solution.get(4).level, 5);
//		Assert.assertEquals(solution.get(4).candidates.iterator().next().id, 3);
//
//		Assert.assertEquals(solution.get(5).level, 6);
//		Assert.assertEquals(solution.get(5).candidates.size(), 0);
//
//		Assert.assertEquals(solution.get(6).level, 7);
//		Assert.assertEquals(solution.get(6).candidates.iterator().next().id, 2);
//	}
//
//	@Test
//	public void cross2() throws Exception {
//		var wolf = new Animal(1, "Волк");
//		var goat = new Animal(2, "Коза");
//		var cabbage = new Animal(3, "Капуста");
//		var grass = new Animal(4, "Зелень");
//		var neutral = new Animal(5, "Нейтрал");
//
//		wolf.victims.add(goat);
//		//goat.victims.add(cabbage);
//		goat.predators.add(wolf);
//		goat.predators.add(wolf);
//
//		grass.predators.add(goat);
//
//		List<Animal> list = new ArrayList<>();
//		list.add(wolf);
//		list.add(goat);
//		list.add(cabbage);
//		list.add(grass);
//		list.add(neutral);
//
//		var root = new SourceCrossingStateState(list, 2);
//		var finalStates = root.travers();
//		Assert.assertEquals(4, finalStates.size());
//
//		printSolutions(finalStates);
//	}
//
//	private static List<List<AbstractCrossingState>> printSolutions(List<AbstractCrossingState> finalStates) {
//		List<List<AbstractCrossingState>> result = new ArrayList<>();
//		if (finalStates != null && !finalStates.isEmpty()) {
//			System.out.println("Всего решений: " + finalStates.size());
//			for (int i = 1; i <= finalStates.size(); i++) {
//				System.out.println("Решение №" + i + ":");
//				var solution = getSolution(finalStates.get(i - 1));
//				result.add(solution);
//
//				for (var state : solution)
//					System.out.println(state.level + "." + state.candidates + " -> " + state);
//			}
//			System.out.println();
//		} else
//			System.out.println("Решения нет!");
//
//		System.out.println("К-во просмотренных вариантов: " + AbstractCrossingState.VARIANTS_NUMBER);
//		return result;
//	}
//
//	private static List<AbstractCrossingState> getSolution(AbstractCrossingState finalState) {
//		List<AbstractCrossingState> solution = new ArrayList<>();
//
//		var state = finalState;
//		while (state != null) {
//			if (state.level != 0)
//				solution.add(state);
//
//			state = state.parent;
//		}
//		Collections.reverse(solution);
//
//		return solution;
//	}
//}
