package gruppo05.visitors.evaluation;

import static java.util.Objects.hash;

public class BoundsValue implements Value
{
	private final RangeValue range;
	
	public BoundsValue(Value range)
	{
        this.range = (RangeValue) range;
    }
	
	@Override
	public String toString() 
	{
		return "<<" + range.getStart() + ", " + range.getEnd() + ">>";
	}
	
	@Override
    public PairValue toProd()
    {
		return new PairValue(range.getStart(),range.getEnd());
    }
	
	@Override
    public int hashCode()
    {
		return hash(range.getStart(),range.getEnd());
    }
	
	@Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof BoundsValue))
            return false;
        var op = (BoundsValue) obj;
        return range.getStart().equals(op.range.getStart()) && range.getEnd().equals(op.range.getEnd());
    }
}
