public class Polynomial {
    private double[] coefficients;

    // No-argument constructor that sets the polynomial to zero
    public Polynomial() {
        this.coefficients = new double[]{0};
    }

    // Constructor that sets the coefficients accordingly
    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients.clone();
    }

    // Add two polynomials
    public Polynomial add(Polynomial other) {
        int maxLength = Math.max(this.coefficients.length, other.coefficients.length);
        double[] resultCoefficients = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            double thisCoeff = i < this.coefficients.length ? this.coefficients[i] : 0;
            double otherCoeff = i < other.coefficients.length ? other.coefficients[i] : 0;
            resultCoefficients[i] = thisCoeff + otherCoeff;
        }

        return new Polynomial(resultCoefficients);
    }

    // Evaluate polynomial
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < this.coefficients.length; i++) {
            result += this.coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    // Determine if value is a root
    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }
}