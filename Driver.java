import java.io.*;
import java.util.*;

public class Driver {
    public static void main(String [] args) throws IOException{
        Polynomial p0 = new Polynomial();
        System.out.println("Polynomial p0 (should be 0): " + p0.evaluate(1)); // 0.0

        // with coefficients and exponents
        double[] coeffs1 = {6, -2, 5};
        int[] exps1 = {0, 1, 3};
        Polynomial p1 = new Polynomial(coeffs1, exps1);
        System.out.println("Polynomial p1 evaluated at -1 (should be 3): " + p1.evaluate(-1)); // 3.0

        // add two polynomials and evaluate at 1
        double[] coeffs2 = {1, 2, 3};
        int[] exps2 = {0, 1, 2};
        Polynomial p2 = new Polynomial(coeffs2, exps2);
        Polynomial p3 = p1.add(p2);
        System.out.println("Polynomial p3 (result of adding p1 and p2) evaluated at 1 (should be 13): " + p3.evaluate(1)); // Expected output: 15.0

        // multiply two polynomials and evaluate at 1
        Polynomial p4 = p1.multiply(p2);
        System.out.println("Polynomial p4 (result of multiplying p1 and p2) evaluated at 1 (should be 35): " + p4.evaluate(1)); // Expected output: 54.0

        // test has root method
        System.out.println("Does p1 have root at 1 (should be false): " + p1.hasRoot(1)); // Expected output: false
        System.out.println("Does p1 have root at -1 (should be false): " + p1.hasRoot(-1)); // Expected output: false

        // take polynomial from file
        File file = new File("polynomial.txt");
        Polynomial p5 = new Polynomial(file);
        System.out.println("Polynomial p5 (from file) evaluated at 1: " + p5.evaluate(1));

        // test saveToFile method
        p5.saveToFile("output_polynomial.txt");
    }
}