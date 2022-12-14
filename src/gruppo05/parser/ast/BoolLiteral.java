package gruppo05.parser.ast;

import gruppo05.visitors.Visitor;

public class BoolLiteral extends PrimLiteral<Boolean> {

	public BoolLiteral(boolean b) {
		super(b);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitBoolLiteral(value);
	}

}
