package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.StatementPrint;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.CounterVisitor.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class CodeGenerator extends VisitorAdaptor{
    private int mainPc;
    public int getMainPc(){
        return mainPc;
    }

    //-------------------------Aritmeticke operacije-------------------------
    public void visit(MoreFactorsDeclMore factors){
        if(factors.getMulop() instanceof MulopMul){
            Code.put(Code.mul);
        }else if(factors.getMulop() instanceof MulopDiv){
            Code.put(Code.div);
        }else if(factors.getMulop() instanceof MulopMod) {
            Code.put(Code.rem);
        }
    }

    public void visit(OptionAddopDeclMore addop){
        if(addop.getAddop() instanceof AddopPlus){
            Code.put(Code.add);
        }else if(addop.getAddop() instanceof AddopMinus){
            Code.put(Code.sub);
        }
    }

    public void visit(DesignatorStatementInc designatorStatementInc){
        if(designatorStatementInc.getDesignator().obj.getType().getKind()==Struct.Array){
            Code.put(Code.dup2);
            if(designatorStatementInc.getDesignator().obj.getType().getElemType().getKind()==Struct.Int){
                Code.put(Code.aload);
            }else{
                Code.put(Code.baload);
            }
        }else {
            Code.load(designatorStatementInc.getDesignator().obj);
        }
        Code.loadConst(1);
        Code.put(Code.add);
        if(designatorStatementInc.getDesignator().obj.getType().getKind()==Struct.Array){
            Code.store(new Obj(Obj.Elem,"",designatorStatementInc.getDesignator().obj.getType().getElemType()));
        }else{
            Code.store(designatorStatementInc.getDesignator().obj);
        }
    }

    public void visit(DesignatorStatementDec designatorStatementDec){
        if(designatorStatementDec.getDesignator().obj.getType().getKind()==Struct.Array){
            Code.put(Code.dup2);
            if(designatorStatementDec.getDesignator().obj.getType().getElemType().getKind()==Struct.Int){
                Code.put(Code.aload);
            }else{
                Code.put(Code.baload);
            }
        }else {
            Code.load(designatorStatementDec.getDesignator().obj);
        }
        Code.loadConst(1);
        Code.put(Code.sub);
        if(designatorStatementDec.getDesignator().obj.getType().getKind()==Struct.Array){
            Code.store(new Obj(Obj.Elem,"",designatorStatementDec.getDesignator().obj.getType().getElemType()));
        }else{
            Code.store(designatorStatementDec.getDesignator().obj);
        }
    }


    public void visit(InsertMinusDecl insertMinusDecl){
        if(((ExprDecl)insertMinusDecl.getParent()).getMinusOption() instanceof MinusOptionDecl){
            Code.put(Code.neg);
        }
    }

    public void visit(FactorNumber factorNumber){
        Code.loadConst(factorNumber.getN1());
    }

    public void visit(FactorChar factorChar){
        Code.loadConst(factorChar.getC1().charAt(1));
    }

    public void visit(FactorBool factorBool){
        if(factorBool.getB1().equals("true")){
            Code.loadConst(1);
        }else{
            Code.loadConst(0);
        }
    }

    //-----------------------Nizovi------------------------------------------
    public void visit(FactorNewExpr factorNewExpr){
        Code.loadConst(2);
        Code.put(Code.mul);
        Code.put(Code.newarray);
        if(factorNewExpr.getType().struct == Tab.charType){
            Code.put(0);
        }else{
            Code.put(1);
        }
    }

    public void visit(FieldArrayDecl fieldArrayDecl){
        Designator designator=(Designator) fieldArrayDecl.getParent().getParent().getParent();
        if(designator instanceof  DesignatorDeclNoPrefix || designator instanceof DesignatorDeclPrefix){
            Code.load(designator.obj);
        }
    }

    //----------------------Funkcije i procedure-----------------------------
    public void visit(MethodDeclNameNoParams methodTypeName){
        if("main".equalsIgnoreCase(methodTypeName.getMethodName())){
            mainPc = Code.pc;
        }
        methodTypeName.obj.setAdr(Code.pc);
        // Collect arguments and local variables
        SyntaxNode methodNode = methodTypeName.getParent();

        VarCounter varCnt = new VarCounter();
        methodNode.traverseTopDown(varCnt);

        FormParamCounter fpCnt = new FormParamCounter();
        methodNode.traverseTopDown(fpCnt);

        // Generate the entry
        Code.put(Code.enter);
        Code.put(fpCnt.getCount());
        Code.put(fpCnt.getCount() + varCnt.getCount());
    }

    public void visit(MethodDeclNameParams methodTypeName){
        methodTypeName.obj.setAdr(Code.pc);
        // Collect arguments and local variables
        SyntaxNode methodNode = methodTypeName.getParent();

        VarCounter varCnt = new VarCounter();
        methodNode.traverseTopDown(varCnt);

        FormParamCounter fpCnt = new FormParamCounter();
        methodNode.traverseTopDown(fpCnt);

        // Generate the entry
        Code.put(Code.enter);
        Code.put(fpCnt.getCount());
        Code.put(fpCnt.getCount() + varCnt.getCount());
    }

    public void visit(MethodDecl methodDecl){
        Code.put(Code.exit);
        Code.put(Code.return_);
    }

    public void visit(FactorDesignatorParams factorDesignatorParams){
        Obj functionObj = factorDesignatorParams.getDesignator().obj;
        int offset = functionObj.getAdr() - Code.pc;
        Code.put(Code.call);
        Code.put2(offset);
    }

    public void visit(FactorDesignatorNoParams factorDesignatorNoParams){
        Obj functionObj = factorDesignatorNoParams.getDesignator().obj;
        int offset = functionObj.getAdr() - Code.pc;
        Code.put(Code.call);
        Code.put2(offset);
    }

    public void visit(DesignatorStatementActPars designatorStatementActPars){
        Obj functionObj = designatorStatementActPars.getDesignator().obj;
        int offset = functionObj.getAdr() - Code.pc;
        Code.put(Code.call);
        Code.put2(offset);
        if(designatorStatementActPars.getDesignator().obj.getType() != Tab.noType){
            Code.put(Code.pop);
        }
    }

    public void visit(DesignatorStatementNoParams designatorStatementNoParams){
        Obj functionObj = designatorStatementNoParams.getDesignator().obj;
        int offset = functionObj.getAdr() - Code.pc;
        Code.put(Code.call);
        Code.put2(offset);
        if(designatorStatementNoParams.getDesignator().obj.getType() != Tab.noType){
            Code.put(Code.pop);
        }
    }

    int i=0;
    Obj[] params=new Obj[10];

    public void visit(FormParsDecl formParsDecl){
        Obj param=new Obj(Obj.Var,formParsDecl.getParName(),formParsDecl.getType().struct);
        param.setLevel(1);
        param.setAdr(i);
        params[i++]=param;
    }

    public void visit(ReptParsDeclMore reptPars){
        Obj param=new Obj(Obj.Var,reptPars.getParName(),reptPars.getType().struct);
        param.setLevel(1);
        param.setAdr(i);
        params[i++]=param;
    }

    public void visit(StatementReturnExpr statementReturn){
        Code.put(Code.exit);
        Code.put(Code.return_);
    }

    public void visit(StatementReturnNoExpr statementReturn){
        Code.put(Code.exit);
        Code.put(Code.return_);
    }

    //-------------------------------For-----------------------------------------
    private LinkedList<Integer> forFixUpList = null;
    private Stack<LinkedList<Integer>> allLists = new Stack<LinkedList<Integer>>();
    int currentList = -1;
    private Stack<Integer> forFixUpStack = new Stack<Integer>();
    private Stack<Integer> continueStack = new Stack<>();
    private Stack<Integer> breakStack = new Stack<>();
    private void newForList() {
        currentList++;
        forFixUpList = new LinkedList<>();
        allLists.push(forFixUpList);
    }
    private void removeCurrentForList() {
        currentList--;
        forFixUpList = allLists.pop();
        if (allLists.size() > 0)
            forFixUpList = allLists.lastElement();
    }

    public void visit(ForOptionDecl1 opt) {
        newForList();

        this.forFixUpList.add(Code.pc);
    }

    public void visit(CondFactOptionDeclNoMore nofor) {
        Code.putJump(0);
        this.forFixUpStack.push(Code.pc - 2);
    }

    public void visit(CondFactOptionDeclMore forcond) {
        Code.loadConst(0);
        Code.putFalseJump(Code.gt, 0);
        this.forFixUpStack.add(Code.pc-2);


        Code.putJump(0);
        this.forFixUpStack.add(Code.pc-2);
    }

    public void visit(CondEndDecl end) {
        this.forFixUpList.add(Code.pc);

        this.continueStack.push(Code.pc);
    }

    public void visit(ForOptionDecl2 stmt)
    {
        Code.putJump(this.forFixUpList.removeFirst());

        Code.fixup(this.forFixUpStack.pop());
    }

    public void visit(ForStatementDecl stmt) {
        Code.putJump(this.forFixUpList.removeFirst());

        StatementFor st = (StatementFor) stmt.getParent();
        if (st.getCondFactOption() instanceof CondFactOptionDeclMore)
            Code.fixup(this.forFixUpStack.pop());


        while (this.breakStack.size() > 0)
            Code.fixup(this.breakStack.pop());

        removeCurrentForList();

        this.continueStack.pop();
    }

    public void visit(StatementContinue stmt) {
        if (this.continueStack.size()>0)
            Code.putJump(this.continueStack.lastElement());
        else {
            Code.put(Code.trap);
            System.out.println("Stek kod CONTINUE prazan!");
        }
    }

    public void visit(StatementBreak stmt) {
        Code.putJump(0);
        this.breakStack.push(Code.pc - 2);
    }

    //------------------------If/Else-----------------------------------------
    Stack<Integer> currentfixupStack = new Stack<Integer>();
    public int getRelOp(Relop op) {
        if (op instanceof RelopEqual) {
            return Code.eq;
        }
        if (op instanceof RelopNotEqual) {
            return Code.ne;
        }
        if (op instanceof RelopGreater) {
            return Code.gt;
        }
        if (op instanceof RelopGreaterEqual) {
            return Code.ge;
        }
        if (op instanceof RelopLess) {
            return Code.lt;
        }
        if (op instanceof RelopLessEqual) {
            return Code.le;
        }
        return -1; // error
    }

    public void visit(CondFactExprRelopExpr facts) {
        int relop = this.getRelOp(facts.getRelop());

        Code.putFalseJump(relop,0);
        int if_false = Code.pc - 2;
        Code.loadConst(1);
        Code.putJump(0);
        int if_true = Code.pc - 2;

        Code.fixup(if_false);
        Code.loadConst(0);
        Code.fixup(if_true);

    }

    public void visit(CondTermDeclMore terms) {
        Code.put(Code.mul);
    }

    public void visit(ConditionDeclMore terms) {
        Code.put(Code.add);
    }

    public void visit(IfStart ifstart) {
        Code.loadConst(0);
        Code.putFalseJump(Code.gt, 0);
        this.currentfixupStack.add(Code.pc-2);
    }

    public void visit(NoElseStmt noelse) {
        Code.fixup(this.currentfixupStack.pop());
    }

    public void visit(ElseStart es) {
        Code.putJump(0);
        Code.fixup(this.currentfixupStack.pop());
        this.currentfixupStack.add(Code.pc-2);
    }

    public void visit(ElseEnd end) {
        Code.fixup(this.currentfixupStack.pop());
    }

    //----------------------Designatori---------------------------------------
    public void visit(DesignatorStatementAssign designatorStatementAssign){
        Obj objectToStore=null;
        Designator testObj= (Designator)designatorStatementAssign.getDesignator();
        if(testObj instanceof DesignatorDeclNoPrefix){
            DesignatorDeclNoPrefix testObj2= (DesignatorDeclNoPrefix)testObj;
            if(testObj2.getIdentExprOptionList().obj!=null){
                objectToStore=new Obj(Obj.Elem,"",designatorStatementAssign.getDesignator().obj.getType().getElemType());
            }else{
                if(testObj2.obj.getType().getKind()==Struct.Array){
                }
                objectToStore=designatorStatementAssign.getDesignator().obj;
            }
        } else if (testObj instanceof DesignatorDeclPrefix) {
            DesignatorDeclPrefix testObj2= (DesignatorDeclPrefix)testObj;
            if(testObj2.getIdentExprOptionList().obj!=null){
                objectToStore=new Obj(Obj.Elem,"",designatorStatementAssign.getDesignator().obj.getType().getElemType());
            }else{
                objectToStore=designatorStatementAssign.getDesignator().obj;
            }
        }

        Code.store(objectToStore);
    }
    //mora da se doradi
    public void visit(FactorDesignator factorDesignator){
        if(factorDesignator.getDesignator().obj.getKind() != Obj.Meth){
            Obj objectToLoad=null;
            Designator testObj= (Designator)factorDesignator.getDesignator();
            if(testObj instanceof DesignatorDeclNoPrefix){
                DesignatorDeclNoPrefix testObj2= (DesignatorDeclNoPrefix)testObj;
                if(testObj2.getIdentExprOptionList().obj!=null){
                    objectToLoad=new Obj(Obj.Elem,"",factorDesignator.getDesignator().obj.getType().getElemType());
                    Code.put(Code.dup2);
                    Code.load(testObj2.obj);
                    Code.put(Code.arraylength);
                    Code.loadConst(2);
                    Code.put(Code.div);
                    Code.put(Code.add);
                    Code.put(Code.dup2);
                    Code.put(Code.aload);
                    Code.loadConst(1);
                    Code.put(Code.add);
                    Code.put(Code.astore);
                }else{
                    objectToLoad=factorDesignator.getDesignator().obj;
                }
            } else if (testObj instanceof DesignatorDeclPrefix) {
                DesignatorDeclPrefix testObj2= (DesignatorDeclPrefix)testObj;
                if(testObj2.getIdentExprOptionList().obj!=null){
                    objectToLoad=new Obj(Obj.Elem,"",factorDesignator.getDesignator().obj.getType().getElemType());
                }else{
                    objectToLoad=factorDesignator.getDesignator().obj;
                }
            }

            Code.load(objectToLoad);
        }
    }

    //--------------------------------Statementi----------------------------------
    public void visit(StatementPrint stm){
        Code.loadConst(5);
        Struct typeForPrint= stm.getExpr().struct;
        if(typeForPrint.getKind()==Struct.Array){
            if(typeForPrint.getElemType().getKind()==Struct.Int || typeForPrint.getElemType().getKind()==Struct.Bool) {
                Code.put(Code.print);
            }else{
                Code.put(Code.bprint);
            }
        }else{
            if(typeForPrint.getKind()==Struct.Int || typeForPrint.getKind()==Struct.Bool){
                Code.put(Code.print);
            }else{
                Code.put(Code.bprint);
            }
        }

    }

    public void visit(StatementRead statementRead){
        Code.loadConst(62);
        Code.loadConst(3);
        Code.put(Code.bprint);
        if(statementRead.getDesignator().obj.getType() == Tab.charType){
            Code.put(Code.bread);
        }else{
            Code.put(Code.read);
        }
        Obj objectToStore;
        if(statementRead.getDesignator().obj.getType().getKind()==Struct.Array) {
            objectToStore = new Obj(Obj.Elem, "", statementRead.getDesignator().obj.getType().getElemType());
        }else{
            objectToStore=statementRead.getDesignator().obj;
        }
        Code.store(objectToStore);
    }

    public void visit(FactorDesignatorHash factorDesignatorHash){
        Code.put(Code.dup2);
        Code.put(Code.pop);
        Code.put(Code.arraylength);
        Code.loadConst(2);
        Code.put(Code.div);
        Code.put(Code.add);
        Code.put(Code.aload);
    }

    public void visit(FactorDesignatorMax factorDesignatorMax){
        Code.load(factorDesignatorMax.getDesignator().obj);
        Code.loadConst(factorDesignatorMax.getN2());
        Code.put(Code.aload);
        Code.load(factorDesignatorMax.getDesignator().obj);
        Code.load(factorDesignatorMax.getDesignator().obj);
        Code.put(Code.arraylength);
        Code.loadConst(2);
        Code.put(Code.div);
        Code.loadConst(factorDesignatorMax.getN2());
        Code.put(Code.sub);
        Code.put(Code.aload);
        Code.put(Code.add);

    }


}
