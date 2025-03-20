// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class ReptConstsDeclMore extends ReptConsts {

    private ReptConsts ReptConsts;
    private String constName;
    private ConstOption ConstOption;

    public ReptConstsDeclMore (ReptConsts ReptConsts, String constName, ConstOption ConstOption) {
        this.ReptConsts=ReptConsts;
        if(ReptConsts!=null) ReptConsts.setParent(this);
        this.constName=constName;
        this.ConstOption=ConstOption;
        if(ConstOption!=null) ConstOption.setParent(this);
    }

    public ReptConsts getReptConsts() {
        return ReptConsts;
    }

    public void setReptConsts(ReptConsts ReptConsts) {
        this.ReptConsts=ReptConsts;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public ConstOption getConstOption() {
        return ConstOption;
    }

    public void setConstOption(ConstOption ConstOption) {
        this.ConstOption=ConstOption;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ReptConsts!=null) ReptConsts.accept(visitor);
        if(ConstOption!=null) ConstOption.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ReptConsts!=null) ReptConsts.traverseTopDown(visitor);
        if(ConstOption!=null) ConstOption.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ReptConsts!=null) ReptConsts.traverseBottomUp(visitor);
        if(ConstOption!=null) ConstOption.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReptConstsDeclMore(\n");

        if(ReptConsts!=null)
            buffer.append(ReptConsts.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        if(ConstOption!=null)
            buffer.append(ConstOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReptConstsDeclMore]");
        return buffer.toString();
    }
}
