package polynomial;

public class Monomial {
    private float coefficient;
    private int exponent;

    public Monomial(float coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    /**
     * Checks if the exponent of two monomials are equal or not.
     * @param monomial Monomial to check
     * @return boolean
     */
    public boolean hasEqualExponent(Monomial monomial) {
        return this.exponent == monomial.getExponent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Monomial)) return false;
        Monomial monomial = (Monomial) o;
        return Float.compare(monomial.getCoefficient(), getCoefficient()) == 0 &&
                getExponent() == monomial.getExponent();
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }
}
