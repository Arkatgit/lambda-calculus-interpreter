package ca.brock.cs.lambda;

public class Not extends Term {
    private Term operand;
    public static final int precedence = 4;
    public Not(Term op){
        operand = op;
    }

    @Override
    public String toStringPrec(int prec)
    {
        return  " not " + operand.toStringPrec(prec);
    }
}
