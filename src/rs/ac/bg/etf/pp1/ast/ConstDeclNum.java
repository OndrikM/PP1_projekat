// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclNum extends ConstDecl {

    private Type Type;
    private String constName;
    private Integer N1;
    private ReptConsts ReptConsts;

    public ConstDeclNum (Type Type, String constName, Integer N1, ReptConsts ReptConsts) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.constName=constName;
        this.N1=N1;
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

    public Integer getN1() {
        return N1;
    }

    public void setN1(Integer N1) {
        this.N1=N1;
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
        buffer.append("ConstDeclNum(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        buffer.append(" "+tab+N1);
        buffer.append("\n");

        if(ReptConsts!=null)
            buffer.append(ReptConsts.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclNum]");
        return buffer.toString();
    }
}
