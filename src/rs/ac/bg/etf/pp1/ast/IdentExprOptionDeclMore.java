// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class IdentExprOptionDeclMore extends IdentExprOptionList {

    private IdentExprOptionList IdentExprOptionList;
    private IdentExprOption IdentExprOption;

    public IdentExprOptionDeclMore (IdentExprOptionList IdentExprOptionList, IdentExprOption IdentExprOption) {
        this.IdentExprOptionList=IdentExprOptionList;
        if(IdentExprOptionList!=null) IdentExprOptionList.setParent(this);
        this.IdentExprOption=IdentExprOption;
        if(IdentExprOption!=null) IdentExprOption.setParent(this);
    }

    public IdentExprOptionList getIdentExprOptionList() {
        return IdentExprOptionList;
    }

    public void setIdentExprOptionList(IdentExprOptionList IdentExprOptionList) {
        this.IdentExprOptionList=IdentExprOptionList;
    }

    public IdentExprOption getIdentExprOption() {
        return IdentExprOption;
    }

    public void setIdentExprOption(IdentExprOption IdentExprOption) {
        this.IdentExprOption=IdentExprOption;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentExprOptionList!=null) IdentExprOptionList.accept(visitor);
        if(IdentExprOption!=null) IdentExprOption.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentExprOptionList!=null) IdentExprOptionList.traverseTopDown(visitor);
        if(IdentExprOption!=null) IdentExprOption.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentExprOptionList!=null) IdentExprOptionList.traverseBottomUp(visitor);
        if(IdentExprOption!=null) IdentExprOption.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IdentExprOptionDeclMore(\n");

        if(IdentExprOptionList!=null)
            buffer.append(IdentExprOptionList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IdentExprOption!=null)
            buffer.append(IdentExprOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IdentExprOptionDeclMore]");
        return buffer.toString();
    }
}
