package org.Polynomial.operations;
import org.Polynomial.DivisionResults;
import org.Polynomial.Monomial;
import org.Polynomial.Polynomial;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Operations {

    public Polynomial add(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry1 : p1.getMonomials().entrySet()) {
            int degree = entry1.getKey();
            Monomial monomial = entry1.getValue();
            Monomial sameDegree = p2.getMonomialbyDegree(degree);
            if (sameDegree != null) {
                double sumCoefficients = monomial.getCoefficient() + sameDegree.getCoefficient();
                result.addMonomial(degree, sumCoefficients);
                p2.removeMonomial(degree);

            } else {
                result.addMonomial(monomial.getDegree(), monomial.getCoefficient());
            }
        }
        for (Map.Entry<Integer, Monomial> entry2 : p2.getMonomials().entrySet()) {
            Monomial monomial = entry2.getValue();
            result.addMonomial(monomial.getDegree(), monomial.getCoefficient());


        }
        result.sortByDegree();
        return result;
    }

    public Polynomial subtract(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry1 : p1.getMonomials().entrySet()) {
            int degree = entry1.getKey();
            Monomial monomial = entry1.getValue();
            Monomial sameDegree = p2.getMonomialbyDegree(degree);
            if (sameDegree != null) {
                double diffCoefficients = monomial.getCoefficient() - sameDegree.getCoefficient();
                result.addMonomial(degree, diffCoefficients);
                p2.removeMonomial(degree);

            } else {
                result.addMonomial(monomial.getDegree(), monomial.getCoefficient());
            }
        }
        for (Map.Entry<Integer, Monomial> entry2 : p2.getMonomials().entrySet()) {
            Monomial monomial = entry2.getValue();
            result.addMonomial(monomial.getDegree(), -monomial.getCoefficient());

        }
        result.sortByDegree();
        return result;
    }

    public Polynomial multiply(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry1 : p1.getMonomials().entrySet()) {
            Monomial monomial1 = entry1.getValue();
            for (Map.Entry<Integer, Monomial> entry2 : p2.getMonomials().entrySet()) {
                Monomial monomial2 = entry2.getValue();
                int multiplyDegree = monomial1.getDegree() + monomial2.getDegree();
                double productOfCoefficients = monomial1.getCoefficient() * monomial2.getCoefficient();
                Monomial sameDegree = result.getMonomialbyDegree(multiplyDegree);
                if (sameDegree != null) {
                    double multiplyCoefficient = productOfCoefficients + sameDegree.getCoefficient();
                    result.addMonomial(multiplyDegree, multiplyCoefficient);
                } else {
                    result.addMonomial(multiplyDegree, productOfCoefficients);
                }

            }
        }
        result.sortByDegree();
        return result;
    }

    public DivisionResults divide(Polynomial p1, Polynomial p2) {
        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial();

        for (Map.Entry<Integer, Monomial> entry : p1.getMonomials().entrySet()) {
            Monomial monomial = entry.getValue();
            remainder.addMonomial(monomial.getDegree(), monomial.getCoefficient());
        }

        if (p1.getMaxDegree() < p2.getMaxDegree()) {
            return new DivisionResults(quotient, remainder);
        }

        remainder.sortByDegree();
        p2.sortByDegree();

        while (remainder.getMaxDegree() >= p2.getMaxDegree()) {
            int divisionDegree = remainder.getMaxDegree() - p2.getMaxDegree();
            double divisionCoefficient = remainder.getMonomialbyDegree(remainder.getMaxDegree()).getCoefficient() / p2.getMonomialbyDegree(p2.getMaxDegree()).getCoefficient();
            quotient.addMonomial(divisionDegree, divisionCoefficient);
            Polynomial toMultiply = new Polynomial();
            toMultiply.addMonomial(divisionDegree, divisionCoefficient);
            Polynomial multiplied = multiply(toMultiply, p2);
            remainder = subtract(remainder, multiplied);
        }

        quotient.sortByDegree();
        remainder.sortByDegree();

        return new DivisionResults(quotient, remainder);
    }


    public Polynomial derive(Polynomial p) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : p.getMonomials().entrySet()) {
            Monomial monomial = entry.getValue();
            int derivativeDegree = monomial.getDegree() - 1;
            double derivativeCoefficient = monomial.getCoefficient() * monomial.getDegree();
            result.addMonomial(derivativeDegree, derivativeCoefficient);
        }
        result.sortByDegree();
        return result;
    }

    public Polynomial integrate(Polynomial p) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : p.getMonomials().entrySet()) {
            Monomial monomial = entry.getValue();
            int primitiveDegree = monomial.getDegree() + 1;
            double primitiveCoefficient = monomial.getCoefficient() / (monomial.getDegree() + 1);
            result.addMonomial(primitiveDegree, primitiveCoefficient);
        }
        result.sortByDegree();
        return result;
    }
    public Polynomial parsePolynomial(String polynomial) {
        Polynomial result = new Polynomial();

        if (polynomial.isEmpty()) {
            throw new IllegalStateException("Polynomial is empty");
        }

        Pattern p = Pattern.compile("\\s*([+-]?\\s*[a-zA-Z0-9]*\\.?[a-zA-Z0-9]*)\\s*[a-zA-Z]\\s*\\^\\s*([a-zA-Z0-9]+)\\s*");


        Matcher m = p.matcher(polynomial);

        while (m.find()) {
            String term = m.group();
            if (!term.toLowerCase().contains("x")) {
                throw new IllegalArgumentException("Invalid term: " + term);
            }
            String coefficient = m.group(1).replaceAll("\\s+", "");
            String degree = m.group(2);
            double resultCoefficient;
            int resultDegree;

            if (coefficient.isEmpty()) {
                resultCoefficient = 1;
            } else if (coefficient.equals("+")) {
                resultCoefficient = 1;
            } else if (coefficient.equals("-")) {
                resultCoefficient = -1;
            } else {
                try {
                    resultCoefficient = Double.parseDouble(coefficient);

                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid coefficient: " + coefficient);
                }
            }

            try {
                resultDegree = Integer.parseInt(degree);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid degree: " + degree);
            }

            Monomial sameDegree = result.getMonomialbyDegree(resultDegree);

            if (sameDegree != null) {
                result.addMonomial(resultDegree, resultCoefficient + sameDegree.getCoefficient());
                if(resultCoefficient+sameDegree.getCoefficient()==0){
                    result.removeMonomial(resultDegree);
                }
            } else {
                result.addMonomial(resultDegree, resultCoefficient);
            }
        }

        result.sortByDegree();
        return result;
    }
    public String toString(Polynomial p) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirstElement = true;

        for (Map.Entry<Integer, Monomial> entry : p.getMonomials().entrySet()) {
            Monomial monomial = entry.getValue();
            int degree = monomial.getDegree();
            double coefficient = monomial.getCoefficient();

            if (coefficient == 0) {
                continue;
            }

            if (!isFirstElement) {
                if (coefficient > 0) {
                    stringBuilder.append("+");
                } else {
                    stringBuilder.append("-");
                }
            } else {

                isFirstElement = false;

                if (coefficient < 0) {
                    stringBuilder.append("-");
                }
            }

            if (Math.abs(coefficient) != 1 || degree == 0) {
                stringBuilder.append(Math.abs(coefficient));
            }

            if (degree > 0) {
                stringBuilder.append("x");

                if (degree > 1) {
                    stringBuilder.append("^").append(degree);
                }
            }
        }

        if (stringBuilder.length() == 0) {
            return "0";
        }

        return stringBuilder.toString();
    }


}






