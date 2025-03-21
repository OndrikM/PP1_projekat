
package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext());}
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"=" 		{ return new_symbol(sym.EQUAL, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }

"break"    { return new_symbol(sym.BREAK, yytext());}
"class"    { return new_symbol(sym.CLASS, yytext());}
"else"     { return new_symbol(sym.ELSE, yytext());}
"const"    { return new_symbol(sym.CONST, yytext());}
"if"       { return new_symbol(sym.IF, yytext());}
"new"      { return new_symbol(sym.NEW, yytext());}
"read"     { return new_symbol(sym.READ, yytext());}
"extends"  { return new_symbol(sym.EXTENDS, yytext());}
"continue" { return new_symbol(sym.CONTINUE, yytext());}
"for"      { return new_symbol(sym.FOR, yytext());}
"static"   { return new_symbol(sym.STATIC, yytext());}
"namespace" { return new_symbol(sym.NAMESPACE, yytext());}

"-"  { return new_symbol(sym.SUB, yytext());}
"*"  { return new_symbol(sym.MUL, yytext());}
"/"  { return new_symbol(sym.DIV, yytext());}
"%"  { return new_symbol(sym.MOD, yytext());}
"==" { return new_symbol(sym.EQUALEQUAL, yytext());}
"!=" { return new_symbol(sym.NOTEQUAL, yytext());}
">"  { return new_symbol(sym.GREATER, yytext());}
">=" { return new_symbol(sym.GREATEREQUAL, yytext());}
"<"  { return new_symbol(sym.LESS, yytext());}
"<=" { return new_symbol(sym.LESSEQUAL, yytext());}
"&&" { return new_symbol(sym.AND, yytext());}
"||" { return new_symbol(sym.OR, yytext());}
"++" { return new_symbol(sym.INC, yytext());}
"--" { return new_symbol(sym.DEC, yytext());}
":"  { return new_symbol(sym.DOTS, yytext());}
"."  { return new_symbol(sym.DOT, yytext());}
"["  { return new_symbol(sym.LSQUARE, yytext());}
"]"  { return new_symbol(sym.RSQUARE, yytext());}
"=>" { return new_symbol(sym.POINTER, yytext());}
"#"  { return new_symbol(sym.HASH, yytext());}
"@" { return new_symbol(sym.MAX, yytext());}

"//" {yybegin(COMMENT);}
<COMMENT> . {yybegin(COMMENT);}
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

'[ -~]' {return new_symbol (sym.CHAR, yytext()); }
"true"  {return new_symbol (sym.BOOL, yytext()); }
"false" {return new_symbol (sym.BOOL, yytext()); }
[0-9]+  { return new_symbol(sym.NUMBER, new Integer (yytext())); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{return new_symbol (sym.IDENT, yytext()); }

. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }


















