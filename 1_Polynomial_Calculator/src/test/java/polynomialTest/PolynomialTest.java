package polynomialTest;

import org.junit.jupiter.api.Test;
import polynomial.Monomial;
import polynomial.Polynomial;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {
    @Test
    void testAddMonomial() {
        Polynomial a = new Polynomial();
        a.addMonomial(-2, 3);
        a.addMonomial(2, 1);
        a.addMonomial(-3,0);

        List<String> list = new LinkedList<String>();
        list.add("-2x^3");
        list.add("2x");
        list.add("-3");
        Polynomial b = new Polynomial(list);

        assertEquals(b, a);
    }

    @Test
    void testSimplifyPolynomial() {
        List<String> list1 = new LinkedList<String>();
        list1.add("2");
        list1.add("-2");
        list1.add("3");
        list1.add("x^3");
        list1.add("x^2");
        list1.add("3x^3");
        list1.add("x^5");
        list1.add("-x^5");
        Polynomial a = new Polynomial(list1);
        a.simplifyPolynomial();

        List<String> list2 = new LinkedList<String>();
        list2.add("4x^3");
        list2.add("x^2");
        list2.add("3");
        Polynomial b = new Polynomial(list2);

        assertEquals(b, a);
    }

    @Test
    void testFindMonomialWithExponent() {
        Polynomial a = new Polynomial();
        a.addMonomial(3, 2);
        a.addMonomial(2, 5);
        a.addMonomial(6, 0);

        Monomial b = new Monomial(6, 0);
        Monomial c = a.findMonomialWithExponent(b);
        assertEquals(b, c);
    }

    @Test
    void testToString() {
        List<String> list1 = new LinkedList<String>();
        list1.add("4x^3");
        list1.add("-x^2");
        list1.add("-x");
        list1.add("3");
        Polynomial a = new Polynomial(list1);
        String b = a.toString();

        String c = "4.0x^3 - x^2 - x + 3.0 ";
        assertEquals(c ,b);
    }
}