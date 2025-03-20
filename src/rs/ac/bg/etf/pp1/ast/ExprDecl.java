// generated with ast extension for cup
// version 0.8
// 7/1/2024 13:7:12


package rs.ac.bg.etf.pp1.ast;

public class ExprDecl extends Expr {

    private MinusOption MinusOption;
    private Term Term;
    private InsertMinus InsertMinus;
    private OptionAddop OptionAddop;

    public ExprDecl (MinusOption MinusOption, Term Term, InsertMinus InsertMinus, OptionAddop OptionAddop) {
        this.MinusOption=MinusOption;
        if(MinusOption!=null) MinusOption.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.InsertMinus=InsertMinus;
        if(InsertMinus!=null) InsertMinus.setParent(this);
        this.OptionAddop=OptionAddop;
        if(OptionAddop!=null) OptionAddop.setParent(this);
    }

    public MinusOption getMinusOption() {
        return MinusOption;
    }

    public void setMinusOption(MinusOption MinusOption) {
        this.MinusOption=MinusOption;
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public InsertMinus getInsertMinus() {
        return InsertMinus;
    }

    public void setInsertMinus(InsertMinus InsertMinus) {
        this.InsertMinus=InsertMinus;
    }

    public OptionAddop getOptionAddop() {
        return OptionAddop;
    }

    public void setOptionAddop(OptionAddop OptionAddop) {
        this.OptionAddop=OptionAddop;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MinusOption!=null) MinusOption.accept(visitor);
        if(Term!=null) Term.accept(visitor);
        if(InsertMinus!=null) InsertMinus.accept(visitor);
        if(OptionAddop!=null) OptionAddop.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MinusOption!=null) MinusOption.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(InsertMinus!=null) InsertMinus.traverseTopDown(visitor);
        if(OptionAddop!=null) OptionAddop.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MinusOption!=null) MinusOption.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(InsertMinus!=null) InsertMinus.traverseBottomUp(visitor);
        if(OptionAddop!=null) OptionAddop.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprDecl(\n");

        if(MinusOption!=null)
            buffer.append(MinusOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(InsertMinus!=null)
            buffer.append(InsertMinus.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionAddop!=null)
            buffer.append(OptionAddop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprDecl]");
        return buffer.toString();
    }
}
