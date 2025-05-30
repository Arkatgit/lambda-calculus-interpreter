package ca.brock.cs.lambda.parser;

import ca.brock.cs.lambda.types.FType;
import ca.brock.cs.lambda.types.TVar;
import ca.brock.cs.lambda.types.Type;
import ca.brock.cs.lambda.types.Unifier;

import java.util.Map;

public class Constant extends Term {
    private String value;
    private final InfixOperator operator;

    // Constructor for normal constants
    public Constant(String value) {
        this.value = value;
        this.operator = InfixOperator.fromSymbol(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toStringPrec(int prec) {
        if (operator != null) {
            return operator.getSymbol(); // Use operator symbol if it's an infix operator
        }
        return value;
    }

    public boolean isInfixOperator() {
        return operator != null;
    }

    public boolean canBeUsedAsSection() {
        return isInfixOperator() && operator.canBeUsedAsSection();
    }

    public int getArity() {
        return isInfixOperator() ? operator.getArity() : 0;
    }

    @Override
    protected Type computeType(Map<String, Type> env, Unifier unifier) {
        if (value.equals("True") || value.equals("False")) {
            return new ca.brock.cs.lambda.types.Constant("Bool");
        }

        switch (value) {
            case "+":
            case "-":
            case "*":
                return new FType(new ca.brock.cs.lambda.types.Constant("Int"), new FType(new ca.brock.cs.lambda.types.Constant("Int"), new ca.brock.cs.lambda.types.Constant("Int")));
            case "and":
            case "or":
                return new FType(new ca.brock.cs.lambda.types.Constant("Bool"), new FType(new ca.brock.cs.lambda.types.Constant("Bool"), new ca.brock.cs.lambda.types.Constant("Bool")));
            case "=":
            case "<=":
                TVar a = TVar.fresh();
                return new FType(a, new FType(a, new ca.brock.cs.lambda.types.Constant("Bool")));
            case "not":
                return new FType(new ca.brock.cs.lambda.types.Constant("Bool"), new ca.brock.cs.lambda.types.Constant("Bool"));
            default:
                return TVar.fresh();
        }
    }

}