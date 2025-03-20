// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class FormParsDecl extends FormPars {

    private Type Type;
    private String parName;
    private SquareOption SquareOption;
    private ReptPars ReptPars;

    public FormParsDecl (Type Type, String parName, SquareOption SquareOption, ReptPars ReptPars) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.parName=parName;
        this.SquareOption=SquareOption;
        if(SquareOption!=null) SquareOption.setParent(this);
        this.ReptPars=ReptPars;
        if(ReptPars!=null) ReptPars.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getParName() {
        return parName;
    }

    public void setParName(String parName) {
        this.parName=parName;
    }

    public SquareOption getSquareOption() {
        return SquareOption;
    }

    public void setSquareOption(SquareOption SquareOption) {
        this.SquareOption=SquareOption;
    }

    public ReptPars getReptPars() {
        return ReptPars;
    }

    public void setReptPars(ReptPars ReptPars) {
        this.ReptPars=ReptPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(SquareOption!=null) SquareOption.accept(visitor);
        if(ReptPars!=null) ReptPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(SquareOption!=null) SquareOption.traverseTopDown(visitor);
        if(ReptPars!=null) ReptPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(SquareOption!=null) SquareOption.traverseBottomUp(visitor);
        if(ReptPars!=null) ReptPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParsDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+parName);
        buffer.append("\n");

        if(SquareOption!=null)
            buffer.append(SquareOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ReptPars!=null)
            buffer.append(ReptPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParsDecl]");
        return buffer.toString();
    }
}
