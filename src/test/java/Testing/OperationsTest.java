package Testing;
import org.Polynomial.DivisionResults;
import org.Polynomial.Polynomial;
import org.Polynomial.operations.Operations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class OperationsTest {

    @Test
    public void testAdd() {
        Polynomial p1 = new Polynomial();
        p1.addMonomial(4, -1);
        p1.addMonomial(2, 3);
        p1.addMonomial(1, -2);
        p1.addMonomial(0, 5);

        Polynomial p2 = new Polynomial();
        p2.addMonomial(3, -5);
        p2.addMonomial(2, -2);
        p2.addMonomial(1, 4);
        p2.addMonomial(0, 1);

        Operations operations = new Operations();
        Polynomial result = operations.add(p1, p2);

        Polynomial expectedResult = new Polynomial();
        expectedResult.addMonomial(4, -1);
        expectedResult.addMonomial(3, -5);
        expectedResult.addMonomial(2, 1);
        expectedResult.addMonomial(1, 2);
        expectedResult.addMonomial(0, 6);

        Assertions.assertEquals(expectedResult.getMonomials(), result.getMonomials());
    }

    @Test
    public void testSubtract() {
        Polynomial p1 = new Polynomial();
        p1.addMonomial(4, -1);
        p1.addMonomial(2, 3);
        p1.addMonomial(1, -2);
        p1.addMonomial(0, 5);

        Polynomial p2 = new Polynomial();
        p2.addMonomial(3, -5);
        p2.addMonomial(2, -2);
        p2.addMonomial(1, 4);
        p2.addMonomial(0, 1);

        Operations operations = new Operations();
        Polynomial result = operations.subtract(p1, p2);

        Polynomial expectedResult = new Polynomial();
        expectedResult.addMonomial(4, -1);
        expectedResult.addMonomial(3, 5);
        expectedResult.addMonomial(2, 5);
        expectedResult.addMonomial(1, -6);
        expectedResult.addMonomial(0, 4);

        Assertions.assertEquals(expectedResult.getMonomials(), result.getMonomials());
    }

    @Test
    public void testMultiply() {
        Polynomial p1 = new Polynomial();
        p1.addMonomial(4, -1);
        p1.addMonomial(2, 3);
        p1.addMonomial(1, -2);
        p1.addMonomial(0, 5);

        Polynomial p2 = new Polynomial();
        p2.addMonomial(3, -5);
        p2.addMonomial(2, -2);
        p2.addMonomial(1, 4);
        p2.addMonomial(0, 1);

        Operations operations = new Operations();
        Polynomial result = operations.multiply(p1, p2);

        Polynomial expectedResult = new Polynomial();
        expectedResult.addMonomial(7, 5);
        expectedResult.addMonomial(6, 2);
        expectedResult.addMonomial(5, -19);
        expectedResult.addMonomial(4, 3);
        expectedResult.addMonomial(3, -9);
        expectedResult.addMonomial(2, -15);
        expectedResult.addMonomial(1, 18);
        expectedResult.addMonomial(0, 5);

        Assertions.assertEquals(expectedResult.getMonomials(), result.getMonomials());
    }

    @Test
    public void testDivide() {
        Polynomial p1 = new Polynomial();
        p1.addMonomial(4, 3);
        p1.addMonomial(3, 4);
        p1.addMonomial(2, -2);
        p1.addMonomial(1, 5);
        p1.addMonomial(0, -1);

        Polynomial p2 = new Polynomial();
        p2.addMonomial(2, 1);
        p2.addMonomial(0, -3);

        Operations operations = new Operations();
        DivisionResults divisionResults = operations.divide(p1, p2);

        Polynomial quotient = divisionResults.getQuotient();
        Polynomial remainder = divisionResults.getRemainder();

        Polynomial expectedQuotient = new Polynomial();
        expectedQuotient.addMonomial(2, 3);
        expectedQuotient.addMonomial(1, 4);
        expectedQuotient.addMonomial(0, 7);

        Polynomial expectedRemainder = new Polynomial();
        expectedRemainder.addMonomial(1, 17);
        expectedRemainder.addMonomial(0, 20);


        Assertions.assertEquals(expectedQuotient.getMonomials(), quotient.getMonomials());
        Assertions.assertEquals(expectedRemainder.getMonomials(), remainder.getMonomials());

        Polynomial p3 = new Polynomial();
        p3.addMonomial(2, 3);
        p3.addMonomial(1, 2);

        Polynomial p4 = new Polynomial();
        p4.addMonomial(3, 4);
        p4.addMonomial(2, 1);


        DivisionResults divisionResults2 = operations.divide(p3, p4);

        Polynomial expectedQuotient2 = new Polynomial();
        Assertions.assertEquals(expectedQuotient2.getMonomials(), divisionResults2.getQuotient().getMonomials());
        Assertions.assertEquals(p3.getMonomials(), divisionResults2.getRemainder().getMonomials());
    }

    @Test
    public void testDerive() {
        Polynomial p = new Polynomial();
        p.addMonomial(4, -1);
        p.addMonomial(2, 3);
        p.addMonomial(1, -2);
        p.addMonomial(0, 5);

        Operations operations = new Operations();
        Polynomial result = operations.derive(p);

        Polynomial expectedResult = new Polynomial();
        expectedResult.addMonomial(3, -4);
        expectedResult.addMonomial(1, 6);
        expectedResult.addMonomial(0, -2);
        expectedResult.addMonomial(-1, 0);

        Assertions.assertEquals(expectedResult.getMonomials(), result.getMonomials());
    }

    @Test
    public void testIntegrate() {
        Polynomial p = new Polynomial();
        p.addMonomial(4, -1);
        p.addMonomial(2, 3);
        p.addMonomial(1, -2);
        p.addMonomial(0, 5);

        Operations operations = new Operations();
        Polynomial result = operations.integrate(p);

        Polynomial expectedResult = new Polynomial();
        expectedResult.addMonomial(5, -0.2);
        expectedResult.addMonomial(3, 1);
        expectedResult.addMonomial(2, -1);
        expectedResult.addMonomial(1, 5);

        Assertions.assertEquals(expectedResult.getMonomials(), result.getMonomials());
    }


    @Test
    public void testParsePolynomial() {
        Operations operations = new Operations();

        String validPolynomial = "3x^2 + 2x^1 - 5x^0";
        Polynomial parsedPolynomial = operations.parsePolynomial(validPolynomial);
        Polynomial expectedPolynomial = new Polynomial();
        expectedPolynomial.addMonomial(2, 3);
        expectedPolynomial.addMonomial(1, 2);
        expectedPolynomial.addMonomial(0, -5);
        Assertions.assertEquals(expectedPolynomial.getMonomials(), parsedPolynomial.getMonomials());

        String invalidPolynomial1 = "3x^a + 2x^1 - 5x^0";
        Assertions.assertThrows(IllegalArgumentException.class, () -> operations.parsePolynomial(invalidPolynomial1));

        String invalidPolynomial2 = "3x^2 + ax^1 - 5x^0";
        Assertions.assertThrows(IllegalArgumentException.class, () -> operations.parsePolynomial(invalidPolynomial2));
    }

    @Test
    public void testToString() {
        Operations operations = new Operations();

        Polynomial polynomial1 = new Polynomial();
        polynomial1.addMonomial(2, 3);
        polynomial1.addMonomial(1, 2);
        polynomial1.addMonomial(0, 5);
        Assertions.assertEquals("3.0x^2+2.0x+5.0", operations.toString(polynomial1));

        Polynomial polynomial2 = new Polynomial();
        polynomial2.addMonomial(2, -3);
        polynomial2.addMonomial(1, -2);
        polynomial2.addMonomial(0, -5);
        Assertions.assertEquals("-3.0x^2-2.0x-5.0", operations.toString(polynomial2));

        Polynomial polynomial3 = new Polynomial();
        polynomial3.addMonomial(2, 0);
        polynomial3.addMonomial(1, 0);
        polynomial3.addMonomial(0, 0);
        Assertions.assertEquals("0", operations.toString(polynomial3));
    }
}





