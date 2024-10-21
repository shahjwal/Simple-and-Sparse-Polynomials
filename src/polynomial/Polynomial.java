package polynomial;

/**
 * This interface represents all the operations offered by a polynomial. A
 * polynomial is defined here as a function of one variable. The polynomial is a
 * weighted sum of terms (the weights, called coefficients are whole numbers).
 */

public interface Polynomial {
  /**
   * This method is for the addition of two polynomials.
   *
   * @param other the other polynomial to be added
   * @return the resulting polynomial
   */
  Polynomial add(Polynomial other);

  /**
   * This method is for the multiplication of two polynomials.
   *
   * @param other the other polynomial to be multiplied
   * @return the resulting polynomial
   */
  Polynomial multiply(Polynomial other);


  /**
   * This method is for the derivation of the referred polynomial.
   *
   * @return the polynomial that is the first derivative of this polynomial
   */
  Polynomial derivative();

  /**
   * This method is to add a term in the referred polynomial.
   *
   * @param coefficient the coefficient of the term to be added
   * @param power       the power of the term to be added
   * @throws IllegalArgumentException if the power is negative
   */
  void addTerm(int coefficient, int power) throws IllegalArgumentException;

  /**
   * This method is to fetch the degree of the referred polynomial.
   *
   * @return the degree of this polynomial as a whole number
   */
  int getDegree();

  /**
   * This method is to return the polynomial in a string format.
   *
   * @return a string formatted according to the above specifications
   */
  String toString();

  /**
   * This method is for evaluating the referred polynomial with the passed value.
   *
   * @param x the value at which the polynomial is to be evaluated.
   * @return the value of the polynomial at x
   */
  double evaluate(double x);

  /**
   * This method is to fetch the coefficient of the referred polynomial.
   *
   * @param power the power whose coefficient is sought
   * @return the coefficient at the given power
   */
  int getCoefficient(int power);

}
