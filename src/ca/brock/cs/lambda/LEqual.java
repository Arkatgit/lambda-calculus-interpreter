package ca.brock.cs.lambda;

public class LEqual extends Term{
    private Term left;
    private Term right;

    public static final int precedence = 10;

    public LEqual(Term l, Term r)
    {
        left = l;
        right = r;
    }

    @Override
    public String toStringPrec(int prec)
    {
        return left.toStringPrec(prec) + " <= " + right.toStringPrec(prec);
    }
}
