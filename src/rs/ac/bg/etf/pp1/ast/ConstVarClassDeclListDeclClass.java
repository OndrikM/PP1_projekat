// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class ConstVarClassDeclListDeclClass extends ConstVarClassDeclList {

    private ConstVarClassDeclList ConstVarClassDeclList;
    private ClassDecl ClassDecl;

    public ConstVarClassDeclListDeclClass (ConstVarClassDeclList ConstVarClassDeclList, ClassDecl ClassDecl) {
        this.ConstVarClassDeclList=ConstVarClassDeclList;
        if(ConstVarClassDeclList!=null) ConstVarClassDeclList.setParent(this);
        this.ClassDecl=ClassDecl;
        if(ClassDecl!=null) ClassDecl.setParent(this);
    }

    public ConstVarClassDeclList getConstVarClassDeclList() {
        return ConstVarClassDeclList;
    }

    public void setConstVarClassDeclList(ConstVarClassDeclList ConstVarClassDeclList) {
        this.ConstVarClassDeclList=ConstVarClassDeclList;
    }

    public ClassDecl getClassDecl() {
        return ClassDecl;
    }

    public void setClassDecl(ClassDecl ClassDecl) {
        this.ClassDecl=ClassDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstVarClassDeclList!=null) ConstVarClassDeclList.accept(visitor);
        if(ClassDecl!=null) ClassDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstVarClassDeclList!=null) ConstVarClassDeclList.traverseTopDown(visitor);
        if(ClassDecl!=null) ClassDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstVarClassDeclList!=null) ConstVarClassDeclList.traverseBottomUp(visitor);
        if(ClassDecl!=null) ClassDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstVarClassDeclListDeclClass(\n");

        if(ConstVarClassDeclList!=null)
            buffer.append(ConstVarClassDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassDecl!=null)
            buffer.append(ClassDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstVarClassDeclListDeclClass]");
        return buffer.toString();
    }
}
