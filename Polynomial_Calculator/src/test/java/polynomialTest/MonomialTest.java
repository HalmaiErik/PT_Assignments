package polynomialTest;

import org.junit.jupiter.api.Test;
import polynomial.Monomial;

import static org.junit.jupiter.api.Assertions.*;

class MonomialTest {

    @Test
    void testHasEqualExponent() {
        Monomial a = new Monomial(2, 3);
        Monomial b = new Monomial(5, 3);
        Monomial c = new Monomial(10, 4);

        assertTrue(a.hasEqualExponent(b));
        assertFalse(b.hasEqualExponent(c));
    }
}