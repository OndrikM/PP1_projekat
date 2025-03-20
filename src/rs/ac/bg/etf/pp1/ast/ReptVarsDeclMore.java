// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class ReptVarsDeclMore extends ReptVars {

    private ReptVars ReptVars;
    private String varName;
    private SquareOption SquareOption;

    public ReptVarsDeclMore (ReptVars ReptVars, String varName, SquareOption SquareOption) {
        this.ReptVars=ReptVars;
        if(ReptVars!=null) ReptVars.setParent(this);
        this.varName=varName;
        this.SquareOption=SquareOption;
        if(SquareOption!=null) SquareOption.setParent(this);
    }

    public ReptVars getReptVars() {
        return ReptVars;
    }

    public void setReptVars(ReptVars ReptVars) {
        this.ReptVars=ReptVars;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
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
        if(ReptVars!=null) ReptVars.accept(visitor);
        if(SquareOption!=null) SquareOption.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ReptVars!=null) ReptVars.traverseTopDown(visitor);
        if(SquareOption!=null) SquareOption.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ReptVars!=null) ReptVars.traverseBottomUp(visitor);
        if(SquareOption!=null) SquareOption.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReptVarsDeclMore(\n");

        if(ReptVars!=null)
            buffer.append(ReptVars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(SquareOption!=null)
            buffer.append(SquareOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReptVarsDeclMore]");
        return buffer.toString();
    }
}
