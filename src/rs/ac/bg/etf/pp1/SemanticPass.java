package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
import rs.etf.pp1.mj.runtime.Code;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class SemanticPass extends VisitorAdaptor {

    int printCallCount = 0;
    int varDeclCount = 0;
    Obj currentMethod = null;
    boolean returnFound = false;
    boolean errorDetected = false;
    int nVars;
    boolean hasMain=false;
    String currentNamespace = "";
    boolean inFor=false;
    int level=0;
    int currentMethodParsCount=0;
    Obj currentFuncCall=null;
    Struct currentDeclType= null;
    int currentArgsCount=0;
    Obj[] params=new Obj[10];

    Logger log = Logger.getLogger(getClass());

    public void report_error(String message, SyntaxNode info) {
        errorDetected = true;
        StringBuilder msg = new StringBuilder(message);
        int line = (info == null) ? 0: info.getLine();
        if (line != 0)
            msg.append (" na liniji ").append(line);
        log.error(msg.toString());
    }
    public void report_info(String message, SyntaxNode info) {
        StringBuilder msg = new StringBuilder(message);
        int line = (info == null) ? 0: info.getLine();
        if (line != 0)
            msg.append (" na liniji ").append(line);
        log.info(msg.toString());
    }
    //--------------------------------------------------------------------------------------------------
    public void visit(ProgName progName){
        progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
        Tab.insert(Obj.Type, "bool", new Struct(Struct.Bool));
        Tab.openScope();
    }

    public void visit(Program program){
        nVars = Tab.currentScope.getnVars();
        Tab.chainLocalSymbols(program.getProgName().obj);
        Tab.closeScope();
    }

    public void visit(NamespaceName namespace){
        currentNamespace= namespace.getName();
    }

    public void visit(NamespaceDecl namespaceDecl){
        currentNamespace="";
    }

//----------------------------------Deklaracija konstanti i promenjivih-------------------------------------------------
    public void visit(ConstDeclNum constDecl){
        Struct constType = constDecl.getType().struct;
        if(constType.getKind() != Struct.Int){
            report_error("Greska na liniji " + constDecl.getLine() + " : nekompatibilan tip za konstantu " + constDecl.getConstName(), null);
        }
        String constName=currentNamespace;
        if(!currentNamespace.isEmpty()){
            constName+="::";
        }
        constName+=constDecl.getConstName();
        Obj varTest= Tab.find(constName);
        if(varTest != Tab.noObj){
            report_error("Semanticka greska na liniji " + constDecl.getLine() + ": konstanta " + constName + " je vec deklarisana u ovom opsegu", null);
        }else{
            report_info("Deklarisana numericka konstanta " + constName, constDecl);
            Obj varNode =Tab.insert(Obj.Con, constName, constDecl.getType().struct);
            varNode.setLevel(0);
            varNode.setAdr(constDecl.getN1());
        }
        currentDeclType=null;
    }

    public void visit(ConstDeclChar constDecl){
        Struct constType = constDecl.getType().struct;
        if(constType.getKind() != Struct.Char){
            report_error("Greska na liniji " + constDecl.getLine() + " : nekompatibilan tip za konstantu " + constDecl.getConstName(), null);
        }
        String constName=currentNamespace;
        if(!currentNamespace.isEmpty()){
            constName+="::";
        }
        constName+=constDecl.getConstName();
        Obj varTest= Tab.find(constName);
        if(varTest != Tab.noObj){
            report_error("Semanticka greska na liniji " + constDecl.getLine() + ": konstanta " + constName + " je vec deklarisana u ovom opsegu", null);
        }else{
            report_info("Deklarisana karakter konstanta " + constName, constDecl);
            Obj varNode =Tab.insert(Obj.Con, constName, constDecl.getType().struct);
            varNode.setLevel(0);
            varNode.setAdr(constDecl.getC1().charAt(1));
        }
        currentDeclType=null;
    }

    public void visit(ConstDeclBool constDecl){
        Struct constType = constDecl.getType().struct;
        if(constType.getKind() != Struct.Bool){
            report_error("Greska na liniji " + constDecl.getLine() + " : nekompatibilan tip za konstantu " + constDecl.getConstName(), null);
        }
        String constName=currentNamespace;
        if(!currentNamespace.isEmpty()){
            constName+="::";
        }
        constName+=constDecl.getConstName();
        Obj varTest= Tab.find(constName);
        if(varTest != Tab.noObj){
            report_error("Semanticka greska na liniji " + constDecl.getLine() + ": konstanta " + constName + " je vec deklarisana u ovom opsegu", null);
        }else{
            report_info("Deklarisana bool konstanta " + constName, constDecl);
            Obj varNode =Tab.insert(Obj.Con, constName, constDecl.getType().struct);
            varNode.setLevel(0);
            if(constDecl.getB1().equals("true")) {
                varNode.setAdr(1);
            }else{
                varNode.setAdr(0);
            }
        }
        currentDeclType=null;
    }

    public void visit(ReptConstsDeclMore reptConsts){
        ConstOption constType = reptConsts.getConstOption();
        if(constType.obj.getType().getKind() != currentDeclType.getKind()){
            report_error("Greska na liniji " + reptConsts.getLine() + " : nekompatibilan tip za konstantu " + reptConsts.getConstName(), null);
        }
        String constName=currentNamespace;
        if(!currentNamespace.isEmpty()){
            constName+="::";
        }
        constName+=reptConsts.getConstName();
        Obj varTest= Tab.find(constName);
        if(varTest != Tab.noObj){
            report_error("Semanticka greska na liniji " + reptConsts.getLine() + ": konstanta " + constName + " je vec deklarisana u ovom opsegu", null);
        }else{
            if(constType.obj.getType().getKind() == Struct.Int){
                report_info("Deklarisana numericka konstanta " + constName, reptConsts);
            }else if(constType.obj.getType().getKind() == Struct.Char){
                report_info("Deklarisana karakter konstanta " + constName, reptConsts);
            }else if(constType.obj.getType().getKind() == Struct.Bool){
                report_info("Deklarisana bool konstanta " + constName, reptConsts);
            }
        }
        Obj varNode =Tab.insert(Obj.Con, constName, constType.obj.getType());
        varNode.setLevel(0);
        varNode.setAdr(constType.obj.getAdr());
    }

    public void visit(ConstOptionNum constOptionNum){
        constOptionNum.obj = new Obj(Obj.Con, "", new Struct(Struct.Int));
        constOptionNum.obj.setAdr(constOptionNum.getN1());
    }

    public void visit(ConstOptionChar constOptionChar){
        constOptionChar.obj = new Obj(Obj.Con, "", new Struct(Struct.Char));
        constOptionChar.obj.setAdr(constOptionChar.getC1().charAt(1));
    }

    public void visit(ConstOptionBool constOptionBool){
        constOptionBool.obj = new Obj(Obj.Con, "", new Struct(Struct.Bool));
        if(constOptionBool.getB1().equals("true")) {
            constOptionBool.obj.setAdr(1);
        }else{
            constOptionBool.obj.setAdr(0);
        }
    }

    public void visit(VarDeclNoArray vardecl){
        String varName = currentNamespace;
        if(!currentNamespace.isEmpty()){
            varName+="::";
        }
        varName+=vardecl.getVarName();
        Obj varTest = Tab.find(varName);
        if(varTest != Tab.noObj){
            report_error("Semanticka greska na liniji " + vardecl.getLine() + ": promenljiva " + varName + " je vec deklarisana u ovom opsegu", null);
        }else {
            if(level== 0 && currentMethod == null && currentNamespace.isEmpty()){
                report_info("Deklarisana je globalna promenljiva " + varName, vardecl);
            }else{
                report_info("Deklarisana je lokalna promenljiva " + varName, vardecl);
            }
            varDeclCount++;
            Obj varNode = Tab.insert(Obj.Var, varName, vardecl.getType().struct);
            varNode.setLevel(level);
        }
        currentDeclType=null;
    }

    public void visit(VarDeclArray varDeclNoArray){
        String varName = currentNamespace;
        if(!currentNamespace.isEmpty()){
            varName+="::";
        }
        varName+=varDeclNoArray.getVarName();
        Obj varTest = Tab.find(varName);
        if(varTest != Tab.noObj){
            report_error("Semanticka greska na liniji " + varDeclNoArray.getLine() + ": promenljiva " + varName + " je vec deklarisana u ovom opsegu", null);
        }else {
            if(level== 0 && currentMethod == null && currentNamespace.isEmpty()){
                report_info("Deklarisan je globalni niz " + varName, varDeclNoArray);
            }else{
                report_info("Deklarisan je lokalni niz " + varName, varDeclNoArray);
            }
            varDeclCount++;
            Obj varNode = Tab.insert(Obj.Var, varName, new Struct(Struct.Array, varDeclNoArray.getType().struct));
            varNode.setLevel(level);
        }
        currentDeclType=null;
    }

    public void visit(ReptVarsDeclMore reptVars){
        String varName = currentNamespace;
        if(!currentNamespace.isEmpty()){
            varName+="::";
        }
        varName+=reptVars.getVarName();
        Obj varTest = Tab.find(varName);
        if(varTest != Tab.noObj){
            report_error("Semanticka greska na liniji " + reptVars.getLine() + ": promenljiva " + varName + " je vec deklarisana u ovom opsegu", null);
        }
        if(reptVars.getSquareOption().struct!=null){
            if(level== 0 && currentMethod == null && currentNamespace.isEmpty()){
                report_info("Deklarisan je globalni niz " + varName, reptVars);
            }else{
                report_info("Deklarisan je lokalni niz " + varName, reptVars);
            }
        }else{
            if(level== 0 && currentMethod == null && currentNamespace.isEmpty()){
                report_info("Deklarisana je globalna promenljiva " + varName, reptVars);
            }else{
                report_info("Deklarisana je lokalna promenljiva " + varName, reptVars);
            }
        }

        Obj varNode;
        if(reptVars.getSquareOption().struct!=null){
            varNode=Tab.insert(Obj.Var, varName,reptVars.getSquareOption().struct);

        }else{
            varNode=Tab.insert(Obj.Var, varName, currentDeclType);
        }
        varNode.setLevel(level);
        varDeclCount++;
    }

    public void visit(SquareOptionDeclMore squareOptionDeclMore){
        squareOptionDeclMore.struct=new Struct(Struct.Array, currentDeclType);
    }


    public void visit(MethodDeclNameNoParams methodDeclName){
        String methodName = currentNamespace;
        if(!currentNamespace.isEmpty()){
            methodName+="::";
        }
        methodName+=methodDeclName.getMethodName();
        Obj methTest = Tab.find(methodName);
        if(methTest != Tab.noObj){
            report_error("Semanticka greska na liniji " + methodDeclName.getLine() + ": funkcija " + methodName + " je vec deklarisana u ovom opsegu", null);
        }
        if (currentNamespace.isEmpty() && methodDeclName.getType().struct.getKind()==0 && (methodDeclName.getMethodName().equals("main"))) {
            hasMain=true;
        }
        currentMethod = Tab.insert(Obj.Meth, methodName, methodDeclName.getType().struct);
        methodDeclName.obj = currentMethod;
        level++;
        Tab.openScope();
        report_info("Obradjuje se funkcija " + methodName, methodDeclName);
    }

    public void visit(MethodDeclNameParams methodDeclName){
        String methodName = currentNamespace;
        if(!currentNamespace.isEmpty()){
            methodName+="::";
        }
        methodName+=methodDeclName.getMethodName();
        Obj methTest = Tab.find(methodName);
        if(methTest != Tab.noObj){
            report_error("Semanticka greska na liniji " + methodDeclName.getLine() + ": funkcija " + methodName + " je vec deklarisana u ovom opsegu", null);
        }
        if (currentNamespace.isEmpty() && methodDeclName.getType().struct.getKind()==0 && (methodDeclName.getMethodName().equals("main"))) {
            report_error("Semanticka greska na liniji " + methodDeclName.getLine() + ": main funkcija ne sme imati argumente", null);
        }
        currentMethod = Tab.insert(Obj.Meth, methodName, methodDeclName.getType().struct);
        methodDeclName.obj = currentMethod;
        report_info("Obradjuje se funkcija " + methodName, methodDeclName);
        level++;
        Tab.openScope();
        Tab.insert(Obj.Var,params[currentMethodParsCount-1].getName(),params[currentMethodParsCount-1].getType());
        params[currentMethodParsCount-1]=null;
        for(int i=0;i<(currentMethodParsCount-1);i++){
            Tab.insert(Obj.Var, params[i].getName(), params[i].getType());
            params[i]=null;
        }
    }

    public void visit(MethodDecl methodDecl){
        if(!returnFound && currentMethod.getType() != Tab.noType){
            report_error("Semanticka greska na liniji " + methodDecl.getLine() + ": funkcija " + currentMethod.getName() + " nema return iskaz", null);
        }
        currentMethod.setLevel(currentMethodParsCount);
        Tab.chainLocalSymbols(currentMethod);
        level--;
        Tab.closeScope();

        returnFound = false;
        currentMethod = null;
        currentMethodParsCount=0;
    }

    public void visit(TypeDeclNoPrefix type){
        Obj typeNode = Tab.find(type.getTypeName());
        if(typeNode == Tab.noObj){
            report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola", null);
            type.struct = Tab.noType;
        }else{
            if(Obj.Type == typeNode.getKind()){
                type.struct = typeNode.getType();
            }else{
                report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip", type);
                type.struct = Tab.noType;
            }
        }
        currentDeclType=type.struct;
    }

    public void visit(TypeDeclVoid type){
        Obj typeNode=Tab.find("void");
        type.struct=typeNode.getType();
    }

    public void visit(StatementDesignator statementDesignator){
    }

    public void visit(DesignatorStatementAssign designatorStatementAssign){
        if(designatorStatementAssign.getDesignator().obj.getKind() != Obj.Var){
            report_error("Greska na liniji " + designatorStatementAssign.getLine() + " : ime " + designatorStatementAssign.getDesignator().obj.getName() + " nije promenljiva", null);
        }

        if(designatorStatementAssign.getExpr().struct.getKind()!=Struct.Array && designatorStatementAssign.getDesignator().obj.getType().getKind()==Struct.Array){
            if(!designatorStatementAssign.getExpr().struct.assignableTo(designatorStatementAssign.getDesignator().obj.getType().getElemType())){
                report_error("Greska na liniji " + designatorStatementAssign.getLine() + " : nekompatibilni tipovi u dodeli vrednosti", null);
            }
        }else if(designatorStatementAssign.getExpr().struct.getKind()==Struct.Array && designatorStatementAssign.getDesignator().obj.getType().getKind()!=Struct.Array){
            if(!designatorStatementAssign.getExpr().struct.getElemType().assignableTo(designatorStatementAssign.getDesignator().obj.getType())){
                report_error("Greska na liniji " + designatorStatementAssign.getLine() + " : nekompatibilni tipovi u dodeli vrednosti", null);
            }
        }
        else if(!designatorStatementAssign.getExpr().struct.assignableTo(designatorStatementAssign.getDesignator().obj.getType())){
            report_error("Greska na liniji " + designatorStatementAssign.getLine() + " : nekompatibilni tipovi u dodeli vrednosti", null);
        }

    }

    public void visit(DesignatorStatementInc designatorStatementInc){
        if(designatorStatementInc.getDesignator().obj.getKind() != Obj.Var){
            report_error("Greska na liniji " + designatorStatementInc.getLine() + " : ime " + designatorStatementInc.getDesignator().obj.getName() + " nije promenljiva", null);
        }

        if(designatorStatementInc.getDesignator().obj.getType().getKind() == Struct.Array){
            if(designatorStatementInc.getDesignator().obj.getType().getElemType().getKind() != Struct.Int){
                report_error("Greska na liniji " + designatorStatementInc.getLine() + " : element niza nije tipa int", null);
            }
        }else{
            if(designatorStatementInc.getDesignator().obj.getType().getKind() != Struct.Int){
                report_error("Greska na liniji " + designatorStatementInc.getLine() + " : promenjiva nije tipa int", null);
            }
        }
    }

    public void visit(DesignatorStatementDec designatorStatementDec){
        if(designatorStatementDec.getDesignator().obj.getKind() != Obj.Var){
            report_error("Greska na liniji " + designatorStatementDec.getLine() + " : ime " + designatorStatementDec.getDesignator().obj.getName() + " nije promenljiva", null);
        }
        if(designatorStatementDec.getDesignator().obj.getType().getKind() == Struct.Array){
            if(designatorStatementDec.getDesignator().obj.getType().getElemType().getKind() != Struct.Int){
                report_error("Greska na liniji " + designatorStatementDec.getLine() + " : element niza nije tipa int", null);
            }
        }else{
            if(designatorStatementDec.getDesignator().obj.getType().getKind() != Struct.Int){
                report_error("Greska na liniji " + designatorStatementDec.getLine() + " : promenjiva nije tipa int", null);
            }
        }
    }

    public void visit(DesignatorStatementActPars designatorStatementActPars){
        Obj func = designatorStatementActPars.getDesignator().obj;
        Obj test=Tab.find(func.getName());
        if(test.getKind()!=Obj.Meth){
            report_error("Greska na liniji " + designatorStatementActPars.getLine() + " : ime " + func.getName() + " nije funkcija", null);
        }
    }

    public void visit(DesignatorStatementNoParams designatorStatementNoParams){
        Obj func = designatorStatementNoParams.getDesignator().obj;
        Obj test=Tab.find(func.getName());
        if(test.getKind()!=Obj.Meth){
            report_error("Greska na liniji " + designatorStatementNoParams.getLine() + " : ime " + func.getName() + " nije funkcija", null);
        }
    }

    //"Visedimenzionalna dodela nizova"
    public void visit(DesignatorStatementArray dsa){
        if(dsa.getDesignator1().obj.getType().getKind() != Struct.Array){
            report_error("Greska na liniji " + dsa.getLine() + " : ime " + dsa.getDesignator().obj.getName() + " nije niz", null);
        }
        if(dsa.getDesignator1().obj.getType().getKind() != Struct.Array){
            report_error("Greska na liniji " + dsa.getLine() + " : ime " + dsa.getDesignator().obj.getName() + " nije niz", null);
        }

    }

    public void visit(DesignatorArrayListDeclMoreDesignator daldmd){
        if(daldmd.getDesignator().obj.getKind()!=Obj.Var){
            report_error("Greska na liniji " + daldmd.getLine() + " : ime " + daldmd.getDesignator().obj.getName() + " promenjiva +dopuna elementa niza", null);
        }
    }

    public void visit(StatementBreak statementBreak){
        if(!inFor){
            report_error("Greska na liniji " + statementBreak.getLine() + " : break se moze koristiti samo u for petlji", null);
        }
    }

    public void visit(StatementContinue statementContinue){
        if(!inFor){
            report_error("Greska na liniji " + statementContinue.getLine() + " : continue se moze koristiti samo u for petlji", null);
        }
    }

    public void visit(StatementRead statementRead){
        if(statementRead.getDesignator().obj.getKind() != Obj.Var){
            report_error("Greska na liniji " + statementRead.getLine() + " : ime " + statementRead.getDesignator().obj.getName() + " nije promenljiva", null);
        }

        if(statementRead.getDesignator().obj.getType().getKind() == Struct.Array){
            if(statementRead.getDesignator().obj.getType().getElemType().getKind() != Struct.Int &&
                    statementRead.getDesignator().obj.getType().getElemType().getKind() != Struct.Char &&
                    statementRead.getDesignator().obj.getType().getElemType().getKind() != Struct.Bool){
                report_error("Greska na liniji " + statementRead.getLine() + " : element niza nije tipa int, char ili bool", null);
            }
        }else{
            if(statementRead.getDesignator().obj.getType().getKind() != Struct.Int &&
                    statementRead.getDesignator().obj.getType().getKind() != Struct.Char &&
                    statementRead.getDesignator().obj.getType().getKind() != Struct.Bool){
                report_error("Greska na liniji " + statementRead.getLine() + " : promenjiva nije tipa int, char ili bool", null);
            }
        }

    }

    public void visit(StatementPrint statementPrint){
        printCallCount++;
        if(statementPrint.getExpr().struct.getKind() == Struct.Array){
            if(statementPrint.getExpr().struct.getElemType().getKind() != Struct.Int &&
                    statementPrint.getExpr().struct.getElemType().getKind() != Struct.Char &&
                    statementPrint.getExpr().struct.getElemType().getKind() != Struct.Bool){
                report_error("Greska na liniji " + statementPrint.getLine() + " : element niza nije tipa int, char ili bool", null);
            }
        }else if(statementPrint.getExpr().struct.getKind() != Struct.Int && statementPrint.getExpr().struct.getKind() != Struct.Char && statementPrint.getExpr().struct.getKind() != Struct.Bool){
            report_error("Greska na liniji " + statementPrint.getLine() + " : nekompatibilan tip u print iskazu!", null);
        }
    }

    public void visit(StatementReturnNoExpr returnNoExpr){
        returnFound = true;
        if(currentMethod.getType() != Tab.noType){
            report_error("Semanticka greska na liniji " + returnNoExpr.getLine() + ": return iskaz u funkciji koja nije void", null);
        }
    }
    //Potencijalni problem sa elementima niza u sledecoj visit metodi
    public void visit(StatementReturnExpr returnExpr){
        returnFound = true;
        if(!returnExpr.getExpr().struct.assignableTo(currentMethod.getType())){
            report_error("Semanticka greska na liniji " + returnExpr.getLine() + ": return iskaz se ne slaze sa povratnom vrednoscu funkcije", null);
        }
    }

    //public void visit(StatementIf statementIf){}

    //public void visit(StatementIfElse statementIfElse){}

    public void visit(StatementFor statementFor){
        inFor=false;
    }

    public void visit(CondEndDecl condEndDecl){
        inFor=true;
    }


    public void visit(FormParsDecl formParsDecl){
        String paramName;
        if(currentNamespace.isEmpty()){
            paramName=formParsDecl.getParName();
        }else{
            paramName=currentNamespace+"::"+formParsDecl.getParName();
        }
        Obj parNode=new Obj(Obj.Var, paramName, formParsDecl.getType().struct);
        parNode.setLevel(level+1);
        params[currentMethodParsCount++]=parNode;
    }

    public void visit(ReptParsDeclMore reptPars){
        String paramName;
        if(currentNamespace.isEmpty()){
            paramName=reptPars.getParName();
        }else{
            paramName=currentNamespace+"::"+reptPars.getParName();
        }
        Obj parNode=new Obj(Obj.Var, paramName, reptPars.getType().struct);
        parNode.setLevel(level+1);
        params[currentMethodParsCount++]=parNode;
    }

    public void visit(ActParsOne actParsOne){
        currentArgsCount++;
        SyntaxNode parent = actParsOne.getParent();
        if(parent instanceof DesignatorStatementActPars){
            Obj func= ((DesignatorStatementActPars) parent).getDesignator().obj;
            if(func.getLevel()!=currentArgsCount){
                report_error("Greska na liniji " + actParsOne.getLine() + " : neodgovarajuci broj argumenata u pozivu funkcije " + func.getName(), null);
            }
        }else if(parent instanceof FactorDesignatorParams){
            Obj func= ((FactorDesignatorParams) parent).getDesignator().obj;
            if(func.getLevel()!=currentArgsCount){
                report_error("Greska na liniji " + actParsOne.getLine() + " : neodgovarajuci broj argumenata u pozivu funkcije " + func.getName(), null);
            }
        }

        currentArgsCount=0;

    }

    public void visit(MoreActParsDeclMore moreActParsDeclMore){
        currentArgsCount++;
    }

    //------------------------------Pocetak dodela----------------------------------------------
    public void visit(ExprDecl expr){
        if(expr.getMinusOption() instanceof MinusOptionDecl){
            if(expr.getTerm().struct.getKind() != Struct.Int &&(expr.getTerm().struct.getKind() == Struct.Array && expr.getTerm().struct.getElemType().getKind()!=Struct.Int)){
                report_error("Greska na liniji " + expr.getLine() + " : nekompatibilni tipovi u izrazu za negaciju", null);
            }
        }
        expr.struct = expr.getTerm().struct;
    }

    public void visit(OptionAddopDeclMore optionAddopDeclMore){
        optionAddopDeclMore.struct = optionAddopDeclMore.getTerm().struct;
    }

    public void visit(Term term){
        term.struct = term.getFactor().struct;
    }

    public void visit(FactorDesignator factorDesignator){
        factorDesignator.struct = factorDesignator.getDesignator().obj.getType();
    }

    public void visit(FactorDesignatorNoParams factorDesignatorNoParams){
        factorDesignatorNoParams.struct = factorDesignatorNoParams.getDesignator().obj.getType();
        Obj func = factorDesignatorNoParams.getDesignator().obj;
        if(Obj.Meth == func.getKind()){
            report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + factorDesignatorNoParams.getLine(), null);
            factorDesignatorNoParams.struct = func.getType();
        }else{
            report_error("Greska na liniji " + factorDesignatorNoParams.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
            factorDesignatorNoParams.struct = Tab.noType;
        }
    }

    public void visit(FactorDesignatorParams factorDesignatorParams){
        factorDesignatorParams.struct = factorDesignatorParams.getDesignator().obj.getType();
        Obj func = factorDesignatorParams.getDesignator().obj;
        if(Obj.Meth == func.getKind()){
            report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + factorDesignatorParams.getLine(), null);
            factorDesignatorParams.struct = func.getType();
        }else{
            report_error("Greska na liniji " + factorDesignatorParams.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
            factorDesignatorParams.struct = Tab.noType;
        }
    }

    public void visit(FactorNumber factorNumber){factorNumber.struct = Tab.intType;}

    public void visit(FactorChar factorChar){
        factorChar.struct = Tab.charType;
    }

    public void visit(FactorBool factorBool){
        factorBool.struct = Tab.find("bool").getType();
    }

    public void visit(FactorNewExpr factorNewExpr){
        if(factorNewExpr.getExpr().struct.getKind() != Struct.Int && (factorNewExpr.getExpr().struct.getKind() == Struct.Array && factorNewExpr.getExpr().struct.getElemType().getKind()!=Struct.Int)){
            report_error("Greska na liniji " + factorNewExpr.getLine() + " : izraz u new iskazu mora biti tipa int", null);
        }
        factorNewExpr.struct = new Struct(Struct.Array, factorNewExpr.getType().struct);
    }

    public void visit(FactorNewActParams factorNewActParams){
    }

    public void visit(FactorExpr factorExpr){
        factorExpr.struct = factorExpr.getExpr().struct;
    }

    public void visit(DesignatorDeclNoPrefix designatorDeclNoPrefix){
        Obj obj;
        if(currentNamespace.isEmpty()){
            obj = Tab.find(designatorDeclNoPrefix.getName());
        }else{
            obj = Tab.find(currentNamespace+"::"+designatorDeclNoPrefix.getName());
        }
        if(obj == Tab.noObj){
            report_error("Greska na liniji " + designatorDeclNoPrefix.getLine() + " : ime " + designatorDeclNoPrefix.getName() + " nije deklarisano! ", null);
        }else{
            if(Obj.Con == obj.getKind()){
                report_info("Pristup konstanti " + designatorDeclNoPrefix.getName(), designatorDeclNoPrefix);
            }else if(Obj.Var == obj.getKind()){
                if(designatorDeclNoPrefix.getIdentExprOptionList().obj !=null){
                    report_info("Pristup elementu niza " + designatorDeclNoPrefix.getName(), designatorDeclNoPrefix);
                }else{
                    report_info("Pristup promenljivoj " + designatorDeclNoPrefix.getName(), designatorDeclNoPrefix);
                }
            }else if(Obj.Type == obj.getKind()){
                report_info("Pristup tipu " + designatorDeclNoPrefix.getName(), designatorDeclNoPrefix);
            }else if(Obj.Meth == obj.getKind()){
                report_info("Pristup funkciji " + designatorDeclNoPrefix.getName(), designatorDeclNoPrefix);
            }
        }
        designatorDeclNoPrefix.obj = obj;
    }

    public void visit(DesignatorDeclPrefix designatorDeclPrefix){
        Obj obj = Tab.find(designatorDeclPrefix.getOuterName()+"::"+designatorDeclPrefix.getName());
        if(obj == Tab.noObj){
            report_error("Greska na liniji " + designatorDeclPrefix.getLine() + " : ime " + designatorDeclPrefix.getName() + " iz namespace-a " + designatorDeclPrefix.getOuterName() + " nije deklarisano! ", null);
        }else{
            if(Obj.Con == obj.getKind()){
                report_info("Pristup konstanti " + obj.getName(), designatorDeclPrefix);
            }else if(Obj.Var == obj.getKind()){
                if(designatorDeclPrefix.getIdentExprOptionList().obj !=null) {
                    report_info("Pristup elementu niza " + obj.getName(), designatorDeclPrefix);
                }else{
                    report_info("Pristup promenljivoj " + obj.getName(), designatorDeclPrefix);
                }
            }else if(Obj.Type == obj.getKind()){
                report_info("Pristup tipu " + obj.getName(), designatorDeclPrefix);
            }else if(Obj.Meth == obj.getKind()){
                report_info("Pristup funkciji " + obj.getName(), designatorDeclPrefix);
            }
        }
        designatorDeclPrefix.obj = obj;
    }

    public void visit(IdentExprOptionDeclMore identExprOptionDeclMore){

        identExprOptionDeclMore.obj = identExprOptionDeclMore.getIdentExprOption().obj;

    }

    public void visit(IdentExprOptionExpr identExprOptionExpr){
        if(identExprOptionExpr.getExpr().struct.getKind() != Struct.Int && (identExprOptionExpr.getExpr().struct.getKind() == Struct.Array && identExprOptionExpr.getExpr().struct.getElemType().getKind()!=Struct.Int)){
            report_error("Greska na liniji " + identExprOptionExpr.getLine() + " : izraz u pristupu niza mora biti tipa int", null);
        }
        identExprOptionExpr.obj=new Obj(Obj.Elem,"",identExprOptionExpr.getExpr().struct);
    }


    public void visit(FactorDesignatorHash factorDesignatorHash){
        factorDesignatorHash.struct = factorDesignatorHash.getDesignator().obj.getType();
    }

    public void visit(FactorDesignatorMax factorDesignatorMax){
        factorDesignatorMax.struct=factorDesignatorMax.getDesignator().obj.getType();
    }




    public boolean passed(){
        if(!hasMain){
            log.error(new StringBuilder("Mora postojati ispravna main funkcija"));
        }
        return (!errorDetected && hasMain);
    }



}
