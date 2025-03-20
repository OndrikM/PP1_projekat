// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class VarDeclNoArray extends VarDecl {

    private Type Type;
    private String varName;
    private ReptVars ReptVars;

    public VarDeclNoArray (Type Type, String varName, ReptVars ReptVars) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.varName=varName;
        this.ReptVars=ReptVars;
        if(ReptVars!=null) ReptVars.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public ReptVars getReptVars() {
        return ReptVars;
    }

    public void setReptVars(ReptVars ReptVars) {
        this.ReptVars=ReptVars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ReptVars!=null) ReptVars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ReptVars!=null) ReptVars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ReptVars!=null) ReptVars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclNoArray(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(ReptVars!=null)
            buffer.append(ReptVars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclNoArray]");
        return buffer.toString();
    }
}
