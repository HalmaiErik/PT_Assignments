package polynomial;

import util.OperationException;

import java.util.LinkedList;
import java.util.List;

public class Operations {
    /**
     * Method to calculate the derivative of a polynomial. The derivative is calculated monomial by monomial and added
     * to result polynomial.
     * @param p Polynomial
     * @return Derivative of polynomial p
     */
    public static Polynomial derivative(Polynomial p) {
        if(p == null)
            throw new OperationException("Enter polynomial.");

        p.simplifyPolynomial();

        Polynomial result = new Polynomial();
        List<Monomial> polynomial = p.getPolynomial();

        for (Monomial monomial : polynomial) {
            float newCoeff = monomial.getCoefficient() * monomial.getExponent();
            int newExp = monomial.getExponent() - 1;
            result.addMonomial(newCoeff, newExp);
        }

        result.simplifyPolynomial();
        return result;
    }

    /**
     * Method to calculate the integral of a polynomial. The integral is calculated monomial by monomial and added to
     * result polynomial.
     * @param p Polynomial
     * @return Integral of polynomial p
     */
    public static Polynomial integration(Polynomial p) {
        if(p == null)
            throw new OperationException("Enter polynomial.");

        p.simplifyPolynomial();

        Polynomial result = new Polynomial();
        List<Monomial> polynomial = p.getPolynomial();

        for(Monomial monomial : polynomial) {
            float newCoeff = monomial.getCoefficient() / (monomial.getExponent() + 1);
            int newExp = monomial.getExponent() + 1;
            result.addMonomial(newCoeff, newExp);
        }

        result.simplifyPolynomial();
        return result;
    }

    /**
     * Method to calculate the addition or subtraction between two polynomials. The desired operation can be chosen with
     * the op character: '+' for addition and '-' for subtraction. When choosing subtraction, the polynomial to be
     * subtracted will have its coefficients multiplied by -1.
     * @param p1 Polynomial
     * @param p2 Polynomial
     * @param op Operation
     * @return if op = '+' : p1 + p2, if op = '-' : p1 - p2
     */
    public static Polynomial additionOrSubtraction(Polynomial p1, Polynomial p2, char op) {
        if(p1 == null || p2 == null)
            throw new OperationException("Enter polynomial.");

        p1.simplifyPolynomial();
        p2.simplifyPolynomial();
        Polynomial result = new Polynomial();
        result.setPolynomial(p1.getPolynomial());

        List<Monomial> poly2 = p2.getPolynomial();

        // If subtraction => multiply p2's coefficients with (-1), then add
        if(op == '-') {
            for(Monomial monomial : poly2) {
                monomial.setCoefficient(monomial.getCoefficient() * (-1));
            }
        }

        for(Monomial monomial : poly2) {
            Monomial foundMon = result.findMonomialWithExponent(monomial);

            if(foundMon != null) {
                result.getPolynomial().remove(foundMon);
                result.addMonomial(monomial.getCoefficient() + foundMon.getCoefficient(), monomial.getExponent());
            }
            else {
                result.addMonomial(monomial.getCoefficient(), monomial.getExponent());
            }
        }

        result.simplifyPolynomial();
        return result;
    }

    /**
     * Method to calculate the multiplication of two polynomials by multiplying each monomial with each one from the
     * two polynomials (coefficients and exponents get multiplied).
     * @param p1 Polynomial
     * @param p2 Polynomial
     * @return p1 * p2
     */
    public static Polynomial multiplication(Polynomial p1, Polynomial p2) {
        if(p1 == null || p2 == null)
            throw new OperationException("Enter polynomial.");

        p1.simplifyPolynomial();
        p2.simplifyPolynomial();
        List<Monomial> poly1 = p1.getPolynomial();
        List<Monomial> poly2 = p2.getPolynomial();
        Polynomial result = new Polynomial();

        for (Monomial m1 : poly1) {
            for (Monomial m2 : poly2) {
                float newCoeff = m1.getCoefficient() * m2.getCoefficient();
                int newExp = m1.getExponent() + m2.getExponent();
                result.addMonomial(newCoeff, newExp);
            }
        }

        result.simplifyPolynomial();
        return result;
    }

    /**
     * Method to calculate division between two polynomials using long division algorithm. Returns a list of two
     * polynomials, first is the result of the division and second is the remainder.
     * @param numerator Polynomial
     * @param denominator Polynomial
     * @return List of two polynomials
     */
    public static List<Polynomial> division(Polynomial numerator, Polynomial denominator) {
        if(numerator == null || denominator == null)
            throw new OperationException("Enter polynomial.");;

        numerator.simplifyPolynomial();
        denominator.simplifyPolynomial();

        // Check if division by a polynomial = 0
        if(denominator.isNull())
            throw new OperationException("Enter non-zero denominator.");

        Polynomial q = new Polynomial();
        Polynomial r = new Polynomial();
        r.setPolynomial(numerator.getPolynomial());

        while(!r.isNull() && r.degree() >= denominator.degree()) {
            Polynomial t = new Polynomial();
            t.addMonomial(r.lead().getCoefficient() / denominator.lead().getCoefficient(),
                            r.lead().getExponent() - denominator.lead().getExponent());

            // q + t
            Polynomial add = Operations.additionOrSubtraction(q, t, '+');
            // q = q + t
            q.setPolynomial(add.getPolynomial());

            // t * d
            Polynomial mult = Operations.multiplication(t, denominator);
            // r - t * d
            Polynomial sub = Operations.additionOrSubtraction(r, mult, '-');
            // r = r - t * d
            r.setPolynomial(sub.getPolynomial());
        }

        List<Polynomial> result = new LinkedList<Polynomial>();
        result.add(q);
        result.add(r);

        return result;
    }
}
