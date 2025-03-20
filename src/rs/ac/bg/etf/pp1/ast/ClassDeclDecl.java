// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclDecl extends ClassDecl {

    private String I1;
    private ExtendsOption ExtendsOption;
    private StaticList StaticList;
    private StaticInitializers StaticInitializers;
    private VarDeclList VarDeclList;
    private ClassMethodDeclOption ClassMethodDeclOption;

    public ClassDeclDecl (String I1, ExtendsOption ExtendsOption, StaticList StaticList, StaticInitializers StaticInitializers, VarDeclList VarDeclList, ClassMethodDeclOption ClassMethodDeclOption) {
        this.I1=I1;
        this.ExtendsOption=ExtendsOption;
        if(ExtendsOption!=null) ExtendsOption.setParent(this);
        this.StaticList=StaticList;
        if(StaticList!=null) StaticList.setParent(this);
        this.StaticInitializers=StaticInitializers;
        if(StaticInitializers!=null) StaticInitializers.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.ClassMethodDeclOption=ClassMethodDeclOption;
        if(ClassMethodDeclOption!=null) ClassMethodDeclOption.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public ExtendsOption getExtendsOption() {
        return ExtendsOption;
    }

    public void setExtendsOption(ExtendsOption ExtendsOption) {
        this.ExtendsOption=ExtendsOption;
    }

    public StaticList getStaticList() {
        return StaticList;
    }

    public void setStaticList(StaticList StaticList) {
        this.StaticList=StaticList;
    }

    public StaticInitializers getStaticInitializers() {
        return StaticInitializers;
    }

    public void setStaticInitializers(StaticInitializers StaticInitializers) {
        this.StaticInitializers=StaticInitializers;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public ClassMethodDeclOption getClassMethodDeclOption() {
        return ClassMethodDeclOption;
    }

    public void setClassMethodDeclOption(ClassMethodDeclOption ClassMethodDeclOption) {
        this.ClassMethodDeclOption=ClassMethodDeclOption;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExtendsOption!=null) ExtendsOption.accept(visitor);
        if(StaticList!=null) StaticList.accept(visitor);
        if(StaticInitializers!=null) StaticInitializers.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(ClassMethodDeclOption!=null) ClassMethodDeclOption.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExtendsOption!=null) ExtendsOption.traverseTopDown(visitor);
        if(StaticList!=null) StaticList.traverseTopDown(visitor);
        if(StaticInitializers!=null) StaticInitializers.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(ClassMethodDeclOption!=null) ClassMethodDeclOption.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExtendsOption!=null) ExtendsOption.traverseBottomUp(visitor);
        if(StaticList!=null) StaticList.traverseBottomUp(visitor);
        if(StaticInitializers!=null) StaticInitializers.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(ClassMethodDeclOption!=null) ClassMethodDeclOption.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclDecl(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(ExtendsOption!=null)
            buffer.append(ExtendsOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StaticList!=null)
            buffer.append(StaticList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StaticInitializers!=null)
            buffer.append(StaticInitializers.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassMethodDeclOption!=null)
            buffer.append(ClassMethodDeclOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclDecl]");
        return buffer.toString();
    }
}
