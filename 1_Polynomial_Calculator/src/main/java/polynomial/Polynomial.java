package polynomial;

import java.util.*;

public class Polynomial {
    private List<Monomial> polynomial;

    public Polynomial() {
        polynomial = new ArrayList<Monomial>();
    }

    /**
     * Constructor of polynomial with a linked list of strings taken as parameter. Each string of the linked list
     * contains a match group of the polynomial regex pattern. The coefficient and the exponent are extracted from the
     * string using the set pattern: ex. 2x^4.
     * @param p
     */
    public Polynomial(List<String> p) {
        polynomial = new ArrayList<Monomial>();
        for(String str : p) {
            int index = 0;
            // Create coefficient and exponent strings.
            String coeff = "";
            String exp = "";

            // Insert the coefficient's sign if it's negative, skip if positive, do nothing if no sign is present i.e.
            // positive.
            if (str.charAt(index) == '-') {
                coeff += "-";
                index++;
            }
            else if (str.charAt(index) == '+')
                index++;

            // For monomials with 1 coefficient, insert one, else find coefficient
            if(str.charAt(index) == 'x')
                coeff += "1";
            else {
                while (index < str.length() && Character.isDigit(str.charAt(index))) {
                    coeff += str.charAt(index);
                    index++;
                }
            }
            float coefficient = Float.parseFloat(coeff);    // Get coefficient value

            // If monomial is a simple number, read exponent as 0
            if(index <= str.length() - 1) {
                if(index == str.length() - 1)
                    exp += "1";

                else {
                    index++; // Skip 'x'

                    // If monomial has exponent > 1, read exponent value, else read 1 as exponent
                    if (str.charAt(index) == '^') {
                        index++;    // Skip '^'
                        while (index < str.length() && Character.isDigit(str.charAt(index))) {
                            exp += str.charAt(index);
                            index++;
                        }
                    }
                }
            }
            else
                exp += "0";
            int exponent = Integer.parseInt(exp);

            Monomial toInsert = new Monomial(coefficient, exponent);
            polynomial.add(toInsert);
        }
    }

    /**
     * Adds new monomial to the polynomial by giving its coefficient and exponent.
     * @param coefficient The coefficient of the monomial.
     * @param exponent The exponent of the monomial.
     */
    public void addMonomial(float coefficient, int exponent) {
        Monomial toInsert = new Monomial(coefficient, exponent);
        polynomial.add(toInsert);
    }

    /**
     * Adds new given monomial to polynomial.
     * @param monomial Monomial
     */
    public void addMonomial(Monomial monomial) {
        polynomial.add(monomial);
    }

    /**
     * Sorts the monomials in descending order with respect to their exponent
     */
    private void sortMonomialsDesc() {
        polynomial.sort(new Comparator<Monomial>() {
            public int compare(Monomial o1, Monomial o2) {
                int mon1 = o1.getExponent();
                int mon2 = o2.getExponent();

                if(mon1 > mon2)
                    return -1;
                else if(mon1 < mon2)
                    return 1;
                else return 0;
            }
        });
    }

    /**
     * Sorts the polynomial, so that monomials with equal exponents are
     */
    private void sumEqualExponent() {
        sortMonomialsDesc();
        int index = 0;

        while(index < polynomial.size() - 1) {
            Monomial mon1 = polynomial.get(index);
            Monomial mon2 = polynomial.get(index + 1);

            if(mon1.hasEqualExponent(mon2)) {
                mon1.setCoefficient(mon1.getCoefficient() + mon2.getCoefficient());
                polynomial.remove(mon2);
                index--;
            }

            index++;
        }
    }

    /**
     * Eliminates monomials with coefficient = 0 from the polynomial
     */
    private void eliminateZeroCoefficient() {
        Iterator<Monomial> iter = polynomial.iterator();

        while(iter.hasNext()) {
            Monomial monomial = iter.next();

            if(monomial.getCoefficient() == 0)
                iter.remove();
        }
    }

    /**
     * Sums equal exponent elements and eliminates monomials with zero coefficient
     */
    public void simplifyPolynomial() {
        sumEqualExponent();
        eliminateZeroCoefficient();
    }

    /**
     * Finds monomial in the list with exponent equal to the exponent of the monomial given as parameter.
     * @param m Monomial
     * @return Monomial with equal exponent if found, else null
     */
    public Monomial findMonomialWithExponent(Monomial m) {
        for(Monomial monomial : polynomial) {
            if (monomial.hasEqualExponent(m))
                return monomial;
        }
        return null;
    }

    /**
     * Constructs the string version of the polynomial. This will be of the form: 2x^5 + 3x^3 - 5x + 10 for example.
     * @return String
     */
    public String toString() {
        String str = "";
        boolean first = true;
        for(Monomial monomial : polynomial) {
            float coeff = monomial.getCoefficient();
            int exp = monomial.getExponent();

            // If coefficient is > 0 and it's not the first monomial, place "+" in string.
            // If coefficient < 0, place "-" in string.
            if(coeff > 0) {
                if(first)
                    first = false;
                else
                    str += "+ ";
            }
            else {
                str += "- ";
                first = false;
            }

            // Place coefficient in the string: - if coefficient != 1 or -1, place the value
            //                                  - if coefficient = 1 or -1 and exponent = 0, place 1
            if (coeff != 1 && coeff != -1)
                str += String.valueOf(Math.abs(coeff));
            else if(exp == 0)
                str += "1";


            // If exponent != 0, place an 'x' in the string, if exponent != 1 => place exponent's value in the string
            // Placed space between the monomials here, so that the first term doesn't follow a space.
            if(exp != 0) {
                str += "x";

                if(exp != 1)
                    str += "^" + String.valueOf(exp);
            }
            str += " ";
        }

        return str;
    }

    /**
     * Return degree of sorted polynomial
     * @return degree
     */
    public int degree() {
        return polynomial.get(0).getExponent();
    }

    /**
     * Return leading monomial (with highest exponent) of sorted polynomial
     * @return Monomial
     */
    public Monomial lead() {
        return polynomial.get(0);
    }

    public boolean isNull() {
        return polynomial.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polynomial)) return false;
        Polynomial that = (Polynomial) o;
        return Objects.equals(getPolynomial(), that.getPolynomial());
    }

    public void setPolynomial(List<Monomial> polynomial) {
        this.polynomial = polynomial;
    }

    public List<Monomial> getPolynomial() {
        return polynomial;
    }
}
