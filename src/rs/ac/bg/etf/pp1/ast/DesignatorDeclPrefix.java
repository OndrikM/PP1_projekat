// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class DesignatorDeclPrefix extends Designator {

    private String outerName;
    private String name;
    private IdentExprOptionList IdentExprOptionList;

    public DesignatorDeclPrefix (String outerName, String name, IdentExprOptionList IdentExprOptionList) {
        this.outerName=outerName;
        this.name=name;
        this.IdentExprOptionList=IdentExprOptionList;
        if(IdentExprOptionList!=null) IdentExprOptionList.setParent(this);
    }

    public String getOuterName() {
        return outerName;
    }

    public void setOuterName(String outerName) {
        this.outerName=outerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public IdentExprOptionList getIdentExprOptionList() {
        return IdentExprOptionList;
    }

    public void setIdentExprOptionList(IdentExprOptionList IdentExprOptionList) {
        this.IdentExprOptionList=IdentExprOptionList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentExprOptionList!=null) IdentExprOptionList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentExprOptionList!=null) IdentExprOptionList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentExprOptionList!=null) IdentExprOptionList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorDeclPrefix(\n");

        buffer.append(" "+tab+outerName);
        buffer.append("\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        if(IdentExprOptionList!=null)
            buffer.append(IdentExprOptionList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorDeclPrefix]");
        return buffer.toString();
    }
}
