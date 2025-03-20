// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class IdentExprOptionExpr extends IdentExprOption {

    private FieldArray FieldArray;
    private Expr Expr;

    public IdentExprOptionExpr (FieldArray FieldArray, Expr Expr) {
        this.FieldArray=FieldArray;
        if(FieldArray!=null) FieldArray.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public FieldArray getFieldArray() {
        return FieldArray;
    }

    public void setFieldArray(FieldArray FieldArray) {
        this.FieldArray=FieldArray;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FieldArray!=null) FieldArray.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FieldArray!=null) FieldArray.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FieldArray!=null) FieldArray.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IdentExprOptionExpr(\n");

        if(FieldArray!=null)
            buffer.append(FieldArray.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IdentExprOptionExpr]");
        return buffer.toString();
    }
}
