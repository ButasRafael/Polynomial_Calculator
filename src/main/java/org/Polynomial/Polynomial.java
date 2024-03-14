package org.Polynomial;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;


public class Polynomial {
    private Map<Integer,Monomial> monomials;



public Polynomial(){
    monomials = new LinkedHashMap<>();
}

    public void addMonomial(int degree, double coefficient) {
        if (coefficient != 0) {
            monomials.put(degree, new Monomial(degree, coefficient));
         }
    }
    public void removeMonomial(int degree) {
        monomials.remove(degree);
    }

    public void sortByDegree() {
        TreeMap<Integer, Monomial> sortedMonomials = new TreeMap<>(Comparator.reverseOrder());
        sortedMonomials.putAll(monomials);
        monomials.clear();
        monomials.putAll(sortedMonomials);
    }

    public int getMaxDegree() {
        if (monomials.isEmpty()) {
            return -1;
        }
        TreeMap<Integer, Monomial> sortedMonomials = new TreeMap<>(monomials);
        return sortedMonomials.lastKey();
    }
    public Map<Integer, Monomial> getMonomials() {
        return monomials;
    }

    public Monomial getMonomialbyDegree(int degree) {
        return monomials.get(degree);
    }

}
