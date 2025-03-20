// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class ClassMethodDeclOptionDeclMore extends ClassMethodDeclOption {

    private ClassMethodDeclOption ClassMethodDeclOption;
    private MethodDeclList MethodDeclList;

    public ClassMethodDeclOptionDeclMore (ClassMethodDeclOption ClassMethodDeclOption, MethodDeclList MethodDeclList) {
        this.ClassMethodDeclOption=ClassMethodDeclOption;
        if(ClassMethodDeclOption!=null) ClassMethodDeclOption.setParent(this);
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
    }

    public ClassMethodDeclOption getClassMethodDeclOption() {
        return ClassMethodDeclOption;
    }

    public void setClassMethodDeclOption(ClassMethodDeclOption ClassMethodDeclOption) {
        this.ClassMethodDeclOption=ClassMethodDeclOption;
    }

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassMethodDeclOption!=null) ClassMethodDeclOption.accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassMethodDeclOption!=null) ClassMethodDeclOption.traverseTopDown(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassMethodDeclOption!=null) ClassMethodDeclOption.traverseBottomUp(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassMethodDeclOptionDeclMore(\n");

        if(ClassMethodDeclOption!=null)
            buffer.append(ClassMethodDeclOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassMethodDeclOptionDeclMore]");
        return buffer.toString();
    }
}
