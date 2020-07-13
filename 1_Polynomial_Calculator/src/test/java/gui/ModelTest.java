package gui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import polynomial.Polynomial;

import java.util.LinkedList;
import java.util.List;

class ModelTest {

    @Test
    void testReadPolynomial() {
        String input = "2*x^3 - 3x^1 + 4 * x - 1";
        Model model = new Model();
        Polynomial a = new Polynomial(model.checkPolynomial(input));

        List<String> list = new LinkedList<String>();
        list.add("2x^3");
        list.add("-3x^1");
        list.add("4x");
        list.add("-1");
        Polynomial b = new Polynomial(list);

        assertEquals(b, a);
    }
}