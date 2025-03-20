// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class StatementFor extends Statement {

    private ForOption1 ForOption1;
    private CondFactOption CondFactOption;
    private CondEnd CondEnd;
    private ForOption2 ForOption2;
    private ForStatement ForStatement;

    public StatementFor (ForOption1 ForOption1, CondFactOption CondFactOption, CondEnd CondEnd, ForOption2 ForOption2, ForStatement ForStatement) {
        this.ForOption1=ForOption1;
        if(ForOption1!=null) ForOption1.setParent(this);
        this.CondFactOption=CondFactOption;
        if(CondFactOption!=null) CondFactOption.setParent(this);
        this.CondEnd=CondEnd;
        if(CondEnd!=null) CondEnd.setParent(this);
        this.ForOption2=ForOption2;
        if(ForOption2!=null) ForOption2.setParent(this);
        this.ForStatement=ForStatement;
        if(ForStatement!=null) ForStatement.setParent(this);
    }

    public ForOption1 getForOption1() {
        return ForOption1;
    }

    public void setForOption1(ForOption1 ForOption1) {
        this.ForOption1=ForOption1;
    }

    public CondFactOption getCondFactOption() {
        return CondFactOption;
    }

    public void setCondFactOption(CondFactOption CondFactOption) {
        this.CondFactOption=CondFactOption;
    }

    public CondEnd getCondEnd() {
        return CondEnd;
    }

    public void setCondEnd(CondEnd CondEnd) {
        this.CondEnd=CondEnd;
    }

    public ForOption2 getForOption2() {
        return ForOption2;
    }

    public void setForOption2(ForOption2 ForOption2) {
        this.ForOption2=ForOption2;
    }

    public ForStatement getForStatement() {
        return ForStatement;
    }

    public void setForStatement(ForStatement ForStatement) {
        this.ForStatement=ForStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForOption1!=null) ForOption1.accept(visitor);
        if(CondFactOption!=null) CondFactOption.accept(visitor);
        if(CondEnd!=null) CondEnd.accept(visitor);
        if(ForOption2!=null) ForOption2.accept(visitor);
        if(ForStatement!=null) ForStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForOption1!=null) ForOption1.traverseTopDown(visitor);
        if(CondFactOption!=null) CondFactOption.traverseTopDown(visitor);
        if(CondEnd!=null) CondEnd.traverseTopDown(visitor);
        if(ForOption2!=null) ForOption2.traverseTopDown(visitor);
        if(ForStatement!=null) ForStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForOption1!=null) ForOption1.traverseBottomUp(visitor);
        if(CondFactOption!=null) CondFactOption.traverseBottomUp(visitor);
        if(CondEnd!=null) CondEnd.traverseBottomUp(visitor);
        if(ForOption2!=null) ForOption2.traverseBottomUp(visitor);
        if(ForStatement!=null) ForStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementFor(\n");

        if(ForOption1!=null)
            buffer.append(ForOption1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFactOption!=null)
            buffer.append(CondFactOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondEnd!=null)
            buffer.append(CondEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForOption2!=null)
            buffer.append(ForOption2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForStatement!=null)
            buffer.append(ForStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementFor]");
        return buffer.toString();
    }
}
