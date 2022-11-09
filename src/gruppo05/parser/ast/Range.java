package gruppo05.parser.ast;

import gruppo05.visitors.Visitor;


public class Range extends BinaryOp
{
    public Range(Exp left, Exp right)
    {
    	super(left,right);
    }
    
    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitRange(left, right);
    }
}
