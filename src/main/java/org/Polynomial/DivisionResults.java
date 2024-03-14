package org.Polynomial;

public class DivisionResults {
    private Polynomial quotient;
    private Polynomial remainder;

    public DivisionResults(Polynomial quotient, Polynomial remainder)
    {
        this.quotient = quotient;
        this.remainder = remainder;
    }

    public Polynomial getQuotient() {
        return quotient;
    }

    public Polynomial getRemainder() {
        return remainder;
    }

}
