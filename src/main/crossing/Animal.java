package crossing;

import java.util.HashSet;
import java.util.Set;

public class Animal extends Object {
	public String name;
	public int id;

	public final Set<Animal> predators = new HashSet<>();
	public final Set<Animal> victims = new HashSet<>();

	public Animal(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public boolean isVictim(Animal animal) {
		return victims.contains(animal);
	}

	public boolean isPredator(Animal animal) {
		return predators.contains(animal);
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Animal))
			return false;
		return (id == obj.hashCode());
	}

	@Override
	public String toString() {
		return name;
	}
}
