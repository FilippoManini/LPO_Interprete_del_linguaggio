package gruppo05.parser;

public enum TokenType { 
	// symbols
	ASSIGN, MINUS, PLUS, TIMES, NOT, AND, EQ, NOT_EQ, START_PAIR, END_PAIR, STMT_SEP, EXP_SEP, OPEN_PAR, CLOSE_PAR, OPEN_BLOCK, CLOSE_BLOCK, RANGE_SEP, OPEN_RANGE, CLOSE_RANGE, 
	// keywords
	PRINT, VAR, BOOL, IF, ELSE, FST, SND, BOUNDS, FOR, IN,
	// non singleton categories
	SKIP, IDENT, NUM,   
	// end-of-file
	EOF, 	
}
//Added symbols: NOT_EQ, RANGE_SEP, OPEN_RANGE, CLOSE_RANGE, 
//Keywords: BOUNDS, FOR, IN