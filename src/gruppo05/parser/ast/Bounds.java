package gruppo05.parser.ast;

import static java.util.Objects.requireNonNull;
import gruppo05.visitors.Visitor;

public class Bounds implements Exp {
    private final Exp exp;

    public Bounds(Exp exp) {
        this.exp = requireNonNull(exp);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + exp + ")";
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitBounds(exp);
    }

}
