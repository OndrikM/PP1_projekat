// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class ActParsOne extends ActPars {

    private Expr Expr;
    private MoreActPars MoreActPars;

    public ActParsOne (Expr Expr, MoreActPars MoreActPars) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.MoreActPars=MoreActPars;
        if(MoreActPars!=null) MoreActPars.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public MoreActPars getMoreActPars() {
        return MoreActPars;
    }

    public void setMoreActPars(MoreActPars MoreActPars) {
        this.MoreActPars=MoreActPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(MoreActPars!=null) MoreActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(MoreActPars!=null) MoreActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(MoreActPars!=null) MoreActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActParsOne(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MoreActPars!=null)
            buffer.append(MoreActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActParsOne]");
        return buffer.toString();
    }
}
