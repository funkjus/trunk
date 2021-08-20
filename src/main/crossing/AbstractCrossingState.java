package crossing;

import java.util.*;

public abstract class AbstractCrossingState {
	protected static final Set<Animal> EMPTY_SET = Collections.emptySet();

	protected static final Set<AbstractCrossingState> ALL_STATES = new HashSet<>();

	protected static int VARIANTS_NUMBER = 0;

	protected final Set<Animal> localAnimals, otherAnimals;
	protected final AbstractCrossingState parent;
	protected final Set<Animal> candidates = new HashSet<>();
	protected final int level;
	private final int passengerNumber;

	// Only for root state
	public AbstractCrossingState(List<Animal> animals, int passengerNumber) {
		this.passengerNumber = passengerNumber;

		localAnimals = new HashSet<>(animals);
		otherAnimals = new HashSet<>();
		parent = null;
		level = 0;
		VARIANTS_NUMBER = 0;
		ALL_STATES.clear();
	}

	// Build the state based on parent state
	protected AbstractCrossingState(AbstractCrossingState parentState, Collection<Animal> candidateAnimals) {
		this.passengerNumber = parentState.passengerNumber;
		parent = parentState;
		candidates.addAll(candidateAnimals);
		level = parentState.level + 1;

		otherAnimals = new HashSet<>(parent.localAnimals);
		localAnimals = new HashSet<>(parent.otherAnimals);
		for (var candidate : candidateAnimals) {
			otherAnimals.remove(candidate);
			localAnimals.add(candidate);
		}

		VARIANTS_NUMBER++;
	}

	// Start traversing of decision tree
	public final List<AbstractCrossingState> travers() {
		List<AbstractCrossingState> correct = new ArrayList<>();
		if (isCompleted()) {
			correct.add((TargetCrossingStateState) this);
			return correct;
		}
		if (!isCorrect())
			return null;

		ALL_STATES.add(this);
		for (var d : getDecisions()) {
			var states = d.travers();
			if (states != null)
				correct.addAll(states);
		}

		return correct;
	}

	protected abstract boolean isTarget();

	// Generate all possible (correct and incorrect) decisions
	protected List<AbstractCrossingState> getDecisions() {
		List<AbstractCrossingState> list = new ArrayList<>();
		var animals = localAnimals.stream().filter(x -> !candidates.contains(x)).sorted(new StateComparator()).toArray(Animal[]::new);
		for (int i = passengerNumber; i > 0; i--)
			for (var comb : CombinationUtils.generate(animals, i))
				list.add(isTarget() ?
						new SourceCrossingStateState((TargetCrossingStateState) this, comb) :
						new TargetCrossingStateState((SourceCrossingStateState) this, comb));

		return list;
	}

	// Check completeness of the state
	protected boolean isCompleted() {
		return isTarget() && otherAnimals.isEmpty();
	}

	// Check correctness of the state
	protected boolean isCorrect() {
		if (ALL_STATES.contains(this))
			return false;

		for (var next : otherAnimals) {
			for (var tested : otherAnimals) {
				if (next.isVictim(tested) || next.isPredator(tested))
					return false;
			}
		}

		return true;
	}

	@Override
	public int hashCode() {
		return localAnimals.hashCode() * otherAnimals.hashCode() * (isTarget() ? 1 : -1);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof AbstractCrossingState))
			return false;
		AbstractCrossingState state = (AbstractCrossingState) obj;
		return state.isTarget() == isTarget() && state.otherAnimals.equals(otherAnimals) && state.localAnimals.equals(localAnimals);
	}
}
