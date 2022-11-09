package gruppo05.visitors.evaluation;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RangeIterator implements Iterator<IntValue>{

	private int start;
	private int next;
	private final int end;
	
	public RangeIterator(Value start, Value end)
	{
		this.start = start.toInt();
		this.next = start.toInt();
		this.end = end.toInt();
	}

	@Override
	public boolean hasNext() {
		if(start < end)
			return (next < end);
		
		else if(start >= end) 
        	return (next > end);
		
        return false;
	}

	@Override
	public IntValue next() {
		if(!hasNext())
			throw new NoSuchElementException();
		
		if(start < end)
			return new IntValue (next++);
		
		else if(start >= end)
			return new IntValue (next--);
		
		return new IntValue (next);
	}
}

