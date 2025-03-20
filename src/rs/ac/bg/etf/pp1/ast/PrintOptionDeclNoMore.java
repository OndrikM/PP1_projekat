// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class PrintOptionDeclNoMore extends PrintOption {

    public PrintOptionDeclNoMore () {
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
        buffer.append("PrintOptionDeclNoMore(\n");

        buffer.append(tab);
        buffer.append(") [PrintOptionDeclNoMore]");
        return buffer.toString();
    }
}
