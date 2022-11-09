package gruppo05.parser.ast;

import static java.util.Objects.requireNonNull;
import gruppo05.visitors.Visitor;


public class ForIn implements Stmt{
	private final VarIdent id;
    private final Exp e;
    private final Block forBlock;

    public ForIn(VarIdent id, Exp e, Block forBlock) {
        this.id = requireNonNull(id);
        this.e = requireNonNull(e);
        this.forBlock = requireNonNull(forBlock);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + id + "," + e + "," + forBlock + ")";
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitFor(id, e, forBlock);
    }
}
