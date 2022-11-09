package gruppo05.parser.ast;

import gruppo05.visitors.Visitor;

public interface AST {
	<T> T accept(Visitor<T> visitor);
}
