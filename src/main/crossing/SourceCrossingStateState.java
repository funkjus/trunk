package crossing;

import java.util.Collection;
import java.util.List;

public class SourceCrossingStateState extends AbstractCrossingState {
	public SourceCrossingStateState(List<Animal> animals, int passengerNumber) {
		super(animals, passengerNumber);
	}

	protected SourceCrossingStateState(TargetCrossingStateState state, Collection<Animal> animals) {
		super(state, animals);
	}

	@Override
	protected boolean isTarget() {
		return false;
	}

	public String toString() {
		return "Берег 1";
	}
}
