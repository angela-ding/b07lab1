import java.io.*;
import java.util.*;

public class Polynomial {
    private double[] coefficients;
    private int[] exponents;

    public Polynomial() {
        this.coefficients = new double[]{0};
        this.exponents = new int[]{0};
    }

    public Polynomial(double[] coefficients, int[] exponents) {
        this.coefficients = coefficients.clone();
        this.exponents = exponents.clone();
    }

    // constructor that takes file as an argument
    public Polynomial(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        br.close();
        parsePolynomial(line);
    }

    // Add two polynomials
    public Polynomial add(Polynomial other) {
        Map<Integer, Double> resultMap = new TreeMap<>();
        for (int i = 0; i < this.coefficients.length; i++) {
            resultMap.put(this.exponents[i], this.coefficients[i]);
        }
        for (int i = 0; i < other.coefficients.length; i++) {
            resultMap.put(other.exponents[i], resultMap.getOrDefault(other.exponents[i], 0.0) + other.coefficients[i]);
        }
        double[] resultCoefficients = new double[resultMap.size()];
        int[] resultExponents = new int[resultMap.size()];
        int index = 0;
        for (Map.Entry<Integer, Double> entry : resultMap.entrySet()) {
            resultExponents[index] = entry.getKey();
            resultCoefficients[index] = entry.getValue();
            index++;
        }
        return new Polynomial(resultCoefficients, resultExponents);
    }

    // Method to multiply two polynomials
    public Polynomial multiply(Polynomial other) {
        Map<Integer, Double> resultMap = new TreeMap<>();
        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < other.coefficients.length; j++) {
                int exp = this.exponents[i] + other.exponents[j];
                double coeff = this.coefficients[i] * other.coefficients[j];
                resultMap.put(exp, resultMap.getOrDefault(exp, 0.0) + coeff);
            }
        }
        double[] resultCoefficients = new double[resultMap.size()];
        int[] resultExponents = new int[resultMap.size()];
        int index = 0;
        for (Map.Entry<Integer, Double> entry : resultMap.entrySet()) {
            resultExponents[index] = entry.getKey();
            resultCoefficients[index] = entry.getValue();
            index++;
        }
        return new Polynomial(resultCoefficients, resultExponents);
    }

    // Evaluate polynomial at a given point
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < this.coefficients.length; i++) {
            result += this.coefficients[i] * Math.pow(x, this.exponents[i]);
        }
        return result;
    }

    // If given value is a root of the polynomial
    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }

    // Save polynomial to a file
    public void saveToFile(String fileName) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] != 0) {
                if (coefficients[i] > 0 && i > 0) {
                    sb.append("+");
                }
                if (exponents[i] == 0) {
                    sb.append(coefficients[i]);
                } else if (exponents[i] == 1) {
                    sb.append(coefficients[i]).append("x");
                } else {
                    sb.append(coefficients[i]).append("x").append(exponents[i]);
                }
            }
        }
        bw.write(sb.toString());
        bw.close();
    }

    // Helper method to parse polynomial from string
    private void parsePolynomial(String polynomialString) {
        String[] terms = polynomialString.split("(?=[+-])");
        List<Double> coeffList = new ArrayList<>();
        List<Integer> expList = new ArrayList<>();

        for (String term : terms) {
            term = term.trim();
            if (term.contains("x")) {
                String[] parts = term.split("x");
                double coeff = parts[0].isEmpty() || parts[0].equals("+") ? 1 : parts[0].equals("-") ? -1 : Double.parseDouble(parts[0]);
                int exp = parts.length == 1 ? 1 : Integer.parseInt(parts[1]);
                coeffList.add(coeff);
                expList.add(exp);
            } else {
                coeffList.add(Double.parseDouble(term));
                expList.add(0);
            }
        }
        coefficients = coeffList.stream().mapToDouble(Double::doubleValue).toArray();
        exponents = expList.stream().mapToInt(Integer::intValue).toArray();
    }
}
