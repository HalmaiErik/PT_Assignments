package polynomialTest;

import org.junit.jupiter.api.Test;
import polynomial.Polynomial;
import polynomial.Operations;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    @Test
    void testDerivative() {
        List<String> list1 = new LinkedList<String>();
        list1.add("4x^3");
        list1.add("-x^2");
        list1.add("-x");
        list1.add("3");
        Polynomial a = new Polynomial(list1);
        Polynomial b = Operations.derivative(a);

        Polynomial c = new Polynomial();
        c.addMonomial(12, 2);
        c.addMonomial(-2, 1);
        c.addMonomial(-1, 0);

        assertEquals(c, b);
    }

    @Test
    void testIntegration() {
        List<String> list1 = new LinkedList<String>();
        list1.add("10x^4");
        list1.add("-9x^2");
        list1.add("-2x");
        list1.add("3");
        Polynomial a = new Polynomial(list1);
        Polynomial b = Operations.integration(a);

        Polynomial c = new Polynomial();
        c.addMonomial(2, 5);
        c.addMonomial(-3, 3);
        c.addMonomial(-1, 2);
        c.addMonomial(3, 1);

        assertEquals(c, b);
    }

    @Test
    void testAddition() {
        List<String> list1 = new LinkedList<String>();
        list1.add("10x^4");
        list1.add("-9x^2");
        list1.add("-2x");
        list1.add("3");
        Polynomial a = new Polynomial(list1);

        List<String> list2 = new LinkedList<String>();
        list2.add("-10x^4");
        list2.add("5x^7");
        list2.add("3x");
        list2.add("9");
        Polynomial b = new Polynomial(list2);

        List<String> list3 = new LinkedList<String>();
        list3.add("5x^7");
        list3.add("-9x^2");
        list3.add("x");
        list3.add("12");
        Polynomial c = new Polynomial(list3);

        Polynomial addition = Operations.additionOrSubtraction(a, b, '+');
        assertEquals(c, addition);
    }

    @Test
    void testSubtraction() {

        List<String> list1 = new LinkedList<String>();
        list1.add("10x^4");
        list1.add("-9x^2");
        list1.add("-2x");
        list1.add("3");
        Polynomial a = new Polynomial(list1);

        List<String> list2 = new LinkedList<String>();
        list2.add("-10x^4");
        list2.add("5x^7");
        list2.add("3x");
        list2.add("9");
        Polynomial b = new Polynomial(list2);

        List<String> list3 = new LinkedList<String>();
        list3.add("-5x^7");
        list3.add("20x^4");
        list3.add("-9x^2");
        list3.add("-5x");
        list3.add("-6");
        Polynomial c = new Polynomial(list3);

        Polynomial addition = Operations.additionOrSubtraction(a, b, '-');
        assertEquals(c, addition);
    }

    @Test
    void testMultiplication() {
        List<String> list1 = new LinkedList<String>();
        list1.add("10x^2");
        list1.add("-9x^3");
        list1.add("3");
        Polynomial a = new Polynomial(list1);

        List<String> list2 = new LinkedList<String>();
        list2.add("-2x^3");
        list2.add("5x");
        Polynomial b = new Polynomial(list2);

        List<String> list3 = new LinkedList<String>();
        list3.add("18x^6");
        list3.add("-20x^5");
        list3.add("-45x^4");
        list3.add("44x^3");
        list3.add("15x");
        Polynomial c = new Polynomial(list3);

        Polynomial multiplication = Operations.multiplication(a, b);
        assertEquals(c, multiplication);
    }

    @Test
    void testDivision() {
        List<String> list1 = new LinkedList<String>();
        list1.add("x^2");
        list1.add("-3x");
        list1.add("-10");
        Polynomial a = new Polynomial(list1);

        List<String> list2 = new LinkedList<String>();
        list2.add("x");
        list2.add("2");
        Polynomial b = new Polynomial(list2);

        List<String> list3 = new LinkedList<String>();
        list3.add("x");
        list3.add("-5");
        Polynomial c = new Polynomial(list3);

        List<Polynomial> division = Operations.division(a, b);
        assertEquals(c, division.get(0));
    }
}