// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class IdentExprOptionDeclNoMore extends IdentExprOptionList {

    public IdentExprOptionDeclNoMore () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IdentExprOptionDeclNoMore(\n");

        buffer.append(tab);
        buffer.append(") [IdentExprOptionDeclNoMore]");
        return buffer.toString();
    }
}
