package gruppo05.visitors.evaluation;

import static java.util.Objects.hash;

public class RangeValue implements Iterable<IntValue>, Value
{
	private final IntValue start;
    private final IntValue end;

    public RangeValue(int start, int end) {
        this.start = new IntValue(start);
        this.end = new IntValue(end);
    }

    public IntValue getStart() {
        return start;
    }

    public IntValue getEnd() {
        return end;
    }


    @Override
    public String toString() {
        return "[" + start + ":" + end + "]";
    }

    @Override
    public int hashCode() {
        return hash(start,end);
    }

    @Override
    public RangeValue toRange() {
        return this;
    }

    @Override
    public final boolean equals(Object obj) {
        if(this == obj) 
        	return true;
        
        if(!(obj instanceof RangeValue))
        	return false;
        
        var op = (RangeValue) obj;
        
        if(start.equals(end) && op.start.equals(op.end))
        	return true;
        return start.equals(op.start) && end.equals(op.end);
    }

	@Override
	public RangeIterator iterator() {
		return new RangeIterator(start,end);
	}

}


