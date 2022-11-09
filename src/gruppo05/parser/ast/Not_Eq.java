package gruppo05.parser.ast;

import gruppo05.visitors.Visitor;

public class Not_Eq extends BinaryOp {
	public Not_Eq(Exp left, Exp right) {
		super(left, right);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitNot_Eq(left, right);
	}
}