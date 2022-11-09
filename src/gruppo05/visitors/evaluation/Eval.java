package gruppo05.visitors.evaluation;

import java.io.PrintWriter;

import gruppo05.environments.EnvironmentException;
import gruppo05.environments.GenEnvironment;
import gruppo05.parser.ast.Block;
import gruppo05.parser.ast.Exp;
import gruppo05.parser.ast.VarIdent;
import gruppo05.parser.ast.Stmt;
import gruppo05.parser.ast.StmtSeq;
import gruppo05.visitors.Visitor;
import static java.util.Objects.requireNonNull;


public class Eval implements Visitor<Value> {

	private final GenEnvironment<Value> env = new GenEnvironment<>();
	private final PrintWriter printWriter; // output stream used to print values

	public Eval() {
		printWriter = new PrintWriter(System.out, true);
	}

	public Eval(PrintWriter printWriter) {
		this.printWriter = requireNonNull(printWriter);
	}

	// dynamic semantics for programs; no value returned by the visitor
	@Override
	public Value visitProg(StmtSeq stmtSeq) {
		try {
			stmtSeq.accept(this);
			// possible runtime errors
			// EnvironmentException: undefined variable
		} catch (EnvironmentException e) {
			throw new EvaluatorException(e);
		}
		return null;
	}

	// dynamic semantics for statements; no value returned by the visitor
	@Override
	public Value visitAssignStmt(VarIdent ident, Exp exp) 
	{
		env.update(ident, exp.accept(this));
		return null;
	}

	@Override
	public Value visitPrintStmt(Exp exp) 
	{
		printWriter.println(exp.accept(this));
		return null;
	}

	@Override
	public Value visitVarStmt(VarIdent ident, Exp exp) 
	{
		env.dec(ident, exp.accept(this));
		return null;
	}

	@Override
	public Value visitIfStmt(Exp exp, Block thenBlock, Block elseBlock) 
	{
		if (exp.accept(this).toBool()) {
			thenBlock.accept(this);
		}
		else if (elseBlock != null) {
			elseBlock.accept(this);
		}
		return null;
	}

	/**/
	@Override
	public Value visitForInStmt(VarIdent ident, Exp exp, Block forBlock)
	{
		RangeValue range = exp.accept(this).toRange();
		env.enterScope();
		env.dec(ident, new IntValue(0)); 
		
		for(IntValue x : range)
		{
			env.update(ident, x);
			forBlock.accept(this);
		}

		env.exitScope();
		return null;
	}	
	
	@Override
	public Value visitBlock(StmtSeq stmtSeq) 
	{
		env.enterScope();
		stmtSeq.accept(this);
		env.exitScope();
		return null;
	}
	
	// dynamic semantics for sequences of statements
	// no value returned by the visitor
	@Override
	public Value visitSingleStmt(Stmt stmt) 
	{
		stmt.accept(this);
		return null;
	}

	@Override
	public Value visitMoreStmt(Stmt first, StmtSeq rest) 
	{
		first.accept(this);
		rest.accept(this);
		return null;
	}

	// dynamic semantics of expressions; a value is returned by the visitor
	@Override
	public IntValue visitAdd(Exp left, Exp right) 
	{
		return new IntValue(left.accept(this).toInt() + right.accept(this).toInt());
	}

	@Override
	public IntValue visitIntLiteral(int value) 
	{
		return new IntValue(value);
	}

	@Override
	public IntValue visitMul(Exp left, Exp right) 
	{
		return new IntValue(left.accept(this).toInt() * right.accept(this).toInt());
	}

	@Override
	public IntValue visitSign(Exp exp) 
	{
		return new IntValue(-exp.accept(this).toInt());
	}

	@Override
	public Value visitVarIdent(VarIdent id) 
	{
		return env.lookup(id);
	}

	@Override
	public BoolValue visitNot(Exp exp) 
	{
		return new BoolValue(!exp.accept(this).toBool());
	}

	@Override
	public BoolValue visitAnd(Exp left, Exp right) {
		return new BoolValue(left.accept(this).toBool() && right.accept(this).toBool());
	}

	@Override
	public BoolValue visitBoolLiteral(boolean value) 
	{
		return new BoolValue(value);
	}

	@Override
	public BoolValue visitEq(Exp left, Exp right) 
	{
		return new BoolValue( left.accept(this).equals(right.accept(this)) );
	}
	
	@Override /**/
	public BoolValue visitNot_Eq(Exp left, Exp right) 
	{
		return new BoolValue(!(left.accept(this).equals(right.accept(this))));
	}

	@Override
	public PairValue visitPairLit(Exp left, Exp right) 
	{
		return new PairValue(left.accept(this), right.accept(this));
	}

	@Override
	public Value visitFst(Exp exp) 
	{
		return exp.accept(this).toProd().getFstVal();
	}

	@Override
	public Value visitSnd(Exp exp) 
	{
		return exp.accept(this).toProd().getSndVal();
	}
	
	/**/
	@Override
	public RangeValue visitRange(Exp start, Exp end) {
		return new RangeValue(start.accept(this).toInt(), end.accept(this).toInt());
	}
	
	/**/
	@Override
	public BoundsValue visitBounds(Exp exp)
	{
		return new BoundsValue(exp.accept(this).toRange());
	}
}
