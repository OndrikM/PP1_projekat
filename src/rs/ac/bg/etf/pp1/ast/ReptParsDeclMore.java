// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class ReptParsDeclMore extends ReptPars {

    private ReptPars ReptPars;
    private Type Type;
    private String parName;
    private SquareOption SquareOption;

    public ReptParsDeclMore (ReptPars ReptPars, Type Type, String parName, SquareOption SquareOption) {
        this.ReptPars=ReptPars;
        if(ReptPars!=null) ReptPars.setParent(this);
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.parName=parName;
        this.SquareOption=SquareOption;
        if(SquareOption!=null) SquareOption.setParent(this);
    }

    public ReptPars getReptPars() {
        return ReptPars;
    }

    public void setReptPars(ReptPars ReptPars) {
        this.ReptPars=ReptPars;
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ReptPars!=null) ReptPars.accept(visitor);
        if(Type!=null) Type.accept(visitor);
        if(SquareOption!=null) SquareOption.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ReptPars!=null) ReptPars.traverseTopDown(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(SquareOption!=null) SquareOption.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ReptPars!=null) ReptPars.traverseBottomUp(visitor);
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(SquareOption!=null) SquareOption.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReptParsDeclMore(\n");

        if(ReptPars!=null)
            buffer.append(ReptPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

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

        buffer.append(tab);
        buffer.append(") [ReptParsDeclMore]");
        return buffer.toString();
    }
}
