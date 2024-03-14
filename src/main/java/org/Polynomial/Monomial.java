package org.Polynomial;

public class Monomial {
    private int degree;
    private double coefficient;

    public Monomial(int degree, double coefficient) {
        this.degree = degree;
        this.coefficient = coefficient;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Monomial other = (Monomial) obj;
        return degree == other.degree && Double.compare(other.coefficient, coefficient) == 0;
    }

    public int getDegree(){
        return degree;
    }
    public double getCoefficient(){
        return coefficient;
    }

}
