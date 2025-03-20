// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class StaticInitiliazer extends StaticInitializers {

    private StaticInitializersList StaticInitializersList;

    public StaticInitiliazer (StaticInitializersList StaticInitializersList) {
        this.StaticInitializersList=StaticInitializersList;
        if(StaticInitializersList!=null) StaticInitializersList.setParent(this);
    }

    public StaticInitializersList getStaticInitializersList() {
        return StaticInitializersList;
    }

    public void setStaticInitializersList(StaticInitializersList StaticInitializersList) {
        this.StaticInitializersList=StaticInitializersList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StaticInitializersList!=null) StaticInitializersList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StaticInitializersList!=null) StaticInitializersList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StaticInitializersList!=null) StaticInitializersList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StaticInitiliazer(\n");

        if(StaticInitializersList!=null)
            buffer.append(StaticInitializersList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StaticInitiliazer]");
        return buffer.toString();
    }
}
