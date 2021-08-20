package crossing;

import java.util.Comparator;

public class StateComparator implements Comparator<Animal> {
	@Override
	public int compare(Animal a, Animal b) {
		if (a.predators.size() + a.victims.size() == b.predators.size() + b.victims.size())
			return 0;
		return a.predators.size() + a.victims.size() > b.predators.size() + b.victims.size() ? 1 : -1;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof StateComparator);
	}
}
