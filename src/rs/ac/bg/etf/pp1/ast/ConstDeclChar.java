// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclChar extends ConstDecl {

    private Type Type;
    private String constName;
    private String C1;
    private ReptConsts ReptConsts;

    public ConstDeclChar (Type Type, String constName, String C1, ReptConsts ReptConsts) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.constName=constName;
        this.C1=C1;
        this.ReptConsts=ReptConsts;
        if(ReptConsts!=null) ReptConsts.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public String getC1() {
        return C1;
    }

    public void setC1(String C1) {
        this.C1=C1;
    }

    public ReptConsts getReptConsts() {
        return ReptConsts;
    }

    public void setReptConsts(ReptConsts ReptConsts) {
        this.ReptConsts=ReptConsts;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ReptConsts!=null) ReptConsts.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ReptConsts!=null) ReptConsts.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ReptConsts!=null) ReptConsts.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclChar(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        buffer.append(" "+tab+C1);
        buffer.append("\n");

        if(ReptConsts!=null)
            buffer.append(ReptConsts.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclChar]");
        return buffer.toString();
    }
}
