// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class StaticInitializersListMore extends StaticInitializersList {

    private StaticInitializersList StaticInitializersList;
    private StaticInitializer StaticInitializer;

    public StaticInitializersListMore (StaticInitializersList StaticInitializersList, StaticInitializer StaticInitializer) {
        this.StaticInitializersList=StaticInitializersList;
        if(StaticInitializersList!=null) StaticInitializersList.setParent(this);
        this.StaticInitializer=StaticInitializer;
        if(StaticInitializer!=null) StaticInitializer.setParent(this);
    }

    public StaticInitializersList getStaticInitializersList() {
        return StaticInitializersList;
    }

    public void setStaticInitializersList(StaticInitializersList StaticInitializersList) {
        this.StaticInitializersList=StaticInitializersList;
    }

    public StaticInitializer getStaticInitializer() {
        return StaticInitializer;
    }

    public void setStaticInitializer(StaticInitializer StaticInitializer) {
        this.StaticInitializer=StaticInitializer;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StaticInitializersList!=null) StaticInitializersList.accept(visitor);
        if(StaticInitializer!=null) StaticInitializer.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StaticInitializersList!=null) StaticInitializersList.traverseTopDown(visitor);
        if(StaticInitializer!=null) StaticInitializer.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StaticInitializersList!=null) StaticInitializersList.traverseBottomUp(visitor);
        if(StaticInitializer!=null) StaticInitializer.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StaticInitializersListMore(\n");

        if(StaticInitializersList!=null)
            buffer.append(StaticInitializersList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StaticInitializer!=null)
            buffer.append(StaticInitializer.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StaticInitializersListMore]");
        return buffer.toString();
    }
}
