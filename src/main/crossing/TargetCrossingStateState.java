package crossing;

import java.util.Collection;
import java.util.List;

public class TargetCrossingStateState extends AbstractCrossingState {

	protected TargetCrossingStateState(SourceCrossingStateState state, Collection<Animal> animals) {
		super(state, animals);
	}

	@Override
	protected boolean isTarget() {
		return true;
	}

	@Override
	protected List<AbstractCrossingState> getDecisions() {
		List<AbstractCrossingState> list = super.getDecisions();
		list.add(0, new SourceCrossingStateState(this, EMPTY_SET));

		return list;
	}

	public String toString() {
		return "Берег 2";
	}
}
