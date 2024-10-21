package polynomial;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This is the simple polynomial class with represents a polynomial in the form of array list
 * where polynomial with degree N having N+1 terms. This class has functionalities such as adding
 * a term to the polynomial, adding a polynomial to this polynomial, multiplying a polynomial
 * with this polynomial, derivative, evaluate, get coefficient, string conversion, equality check
 * with different polynomial, comparing with another polynomial.
 */
public class SimplePolynomial extends AbstractPolynomial {

  private final List<Integer> polynomial;

  /**
   * Private helper method to remove leading zeros from the polynomial representation.
   * It iterates from the highest degree downwards, removing terms with zero coefficients
   * to keep the polynomial concise.
   */
  private void trimLeadingZeros() {
    int i = polynomial.size() - 1;
    while (i >= 0 && polynomial.get(i) == 0) {
      polynomial.remove(i);
      i--;
    }
  }

  /**
   * Adds two polynomials of type `SimplePolynomial`. This method iterates through
   * both polynomials' terms and adds the coefficients of corresponding powers.
   *
   * @param other the other SimplePolynomial to add to this polynomial.
   * @return a new SimplePolynomial representing the sum of the two polynomials.
   */
  @Override
  protected Polynomial addSimple(SimplePolynomial other) {
    Polynomial additionPolynomial = new SimplePolynomial();
    for (int thisPower = 0; thisPower <= this.getDegree(); thisPower++) {
      int thisCoefficient = this.getCoefficient(thisPower);
      if (thisCoefficient != 0) {
        additionPolynomial.addTerm(thisCoefficient, thisPower);
      }
    }
    for (int otherPower = 0; otherPower <= other.getDegree(); otherPower++) {
      int otherCoefficient = other.getCoefficient(otherPower);
      if (otherCoefficient != 0) {
        additionPolynomial.addTerm(otherCoefficient, otherPower);
      }
    }
    return additionPolynomial;
  }

  /**
   * Adds a `SparsePolynomial` to this `SimplePolynomial`. It delegates the
   * addition process to the `SparsePolynomial`'s `addSimple` method, ensuring
   * that terms from both polynomials are added correctly.
   *
   * @param other the SparsePolynomial to add to this polynomial.
   * @return a new Polynomial representing the sum of both.
   */
  @Override
  protected Polynomial addSparse(SparsePolynomial other) {
    return other.addSimple(this);
  }

  /**
   * Multiplies two `SimplePolynomial` objects by multiplying each term of
   * this polynomial with every term of the other polynomial. The product of
   * terms with different powers is added together.
   *
   * @param other the other SimplePolynomial to multiply with this polynomial.
   * @return a new SimplePolynomial representing the product of the two polynomials.
   */
  @Override
  protected Polynomial multiplySimple(SimplePolynomial other) {
    Polynomial multiplyPolynomial = new SimplePolynomial();
    for (int thisPower = 0; thisPower <= this.getDegree(); thisPower++) {
      int thisCoefficient = this.getCoefficient(thisPower);
      if (thisCoefficient == 0) {
        continue;
      }
      for (int otherPower = 0; otherPower <= other.getDegree(); otherPower++) {
        int otherCoefficient = other.getCoefficient(otherPower);
        if (otherCoefficient == 0) {
          continue;
        }
        int newCoefficient = thisCoefficient * otherCoefficient;
        int newPower = thisPower + otherPower;
        multiplyPolynomial.addTerm(newCoefficient, newPower);
      }
    }
    return multiplyPolynomial;
  }

  /**
   * Multiplies a `SparsePolynomial` with this `SimplePolynomial`. This method
   * delegates the multiplication process to the `SparsePolynomial`, ensuring that
   * the multiplication between different types of polynomials is handled efficiently.
   *
   * @param other the SparsePolynomial to multiply with this polynomial.
   * @return a new Polynomial representing the product of both.
   */
  @Override
  protected Polynomial multiplySparse(SparsePolynomial other) {
    return other.multiplySimple(this);
  }

  /**
   * Compares this `SimplePolynomial` with another `SimplePolynomial` by checking if
   * both have the same coefficients for corresponding powers.
   *
   * @param other the other SimplePolynomial to compare with.
   * @return true if both polynomials are equal, false otherwise.
   */
  @Override
  protected boolean compareSimple(SimplePolynomial other) {
    if (this.polynomial.size() != other.polynomial.size()) {
      return false;
    }
    for (int power = 0; power <= this.getDegree(); power++) {
      if (!Objects.equals(this.polynomial.get(power), other.polynomial.get(power))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Compares this `SimplePolynomial` with a `SparsePolynomial` by delegating the
   * comparison process to the `SparsePolynomial`, ensuring that both types of
   * polynomials can be compared correctly.
   *
   * @param other the SparsePolynomial to compare with.
   * @return true if both polynomials are equal, false otherwise.
   */
  @Override
  protected boolean compareSparse(SparsePolynomial other) {
    return other.compareSimple(this);
  }

  /**
   * Compares the terms of this polynomial with another polynomial. It handles comparisons
   * across different types of polynomials by delegating the comparison to their respective
   * methods.
   *
   * @param other the other polynomial to compare with.
   * @return true if both polynomials have identical terms, false otherwise.
   */
  @Override
  protected boolean compareTerms(Polynomial other) {
    if (other instanceof AbstractPolynomial) {
      return ((AbstractPolynomial) other).compareSimple(this);
    }
    return false;
  }

  /**
   * Generates a unique hash code for this polynomial based on its coefficients and powers.
   * The hash is computed only for non-zero terms.
   *
   * @return the generated hash code.
   */
  @Override
  protected int generateHash() {
    int hash = 1;
    for (int power = this.getDegree(); power >= 0; power--) {
      Integer coefficient = this.getCoefficient(power);
      Integer power2 = power;
      if (coefficient != 0) {
        hash = hash + coefficient.hashCode();
        hash = hash + power2.hashCode();
      }
    }
    return hash;
  }

  /**
   * Default constructor that initializes a zero polynomial.
   * It creates an empty list where coefficients of the polynomial will be stored.
   */
  public SimplePolynomial() {
    polynomial = new ArrayList<>();
  }

  /**
   * Adds another polynomial to this polynomial. The addition is handled depending on the
   * type of the other polynomial.
   *
   * @param other the other polynomial to be added.
   * @return a new Polynomial representing the sum of both polynomials.
   */
  @Override
  public Polynomial add(Polynomial other) {
    if (other instanceof AbstractPolynomial) {
      return ((AbstractPolynomial) other).addSimple(this);
    }
    return new SimplePolynomial();
  }

  /**
   * Multiplies this polynomial with another polynomial. The multiplication is handled
   * depending on the type of the other polynomial.
   *
   * @param other the other polynomial to multiply with.
   * @return a new Polynomial representing the product of both polynomials.
   */
  @Override
  public Polynomial multiply(Polynomial other) {
    if (other instanceof AbstractPolynomial) {
      return ((AbstractPolynomial) other).multiplySimple(this);
    }
    return new SimplePolynomial();
  }

  /**
   * Computes the derivative of this polynomial. Each term's coefficient is multiplied
   * by its power, and the power is reduced by 1.
   *
   * @return a new Polynomial representing the derivative of this polynomial.
   */
  @Override
  public Polynomial derivative() {
    Polynomial derivativePolynomial = new SimplePolynomial();
    for (int power = 1; power < this.polynomial.size(); power++) {
      if (this.polynomial.get(power) == 0) {
        continue;
      }
      int newCoefficient = this.polynomial.get(power) * power;
      int newPower = power - 1;
      derivativePolynomial.addTerm(newCoefficient, newPower);
    }
    return derivativePolynomial;
  }

  /**
   * Adds a term to the polynomial. If the term's power already exists, its coefficient
   * is updated by adding the new coefficient. If the coefficient becomes zero, leading
   * zeros are removed.
   *
   * @param coefficient the coefficient of the term to be added.
   * @param power       the power of the term to be added.
   * @throws IllegalArgumentException if the power is negative.
   */
  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Power cannot be negative.");
    }
    if (coefficient == 0) {
      return;
    }
    while (power >= polynomial.size()) {
      polynomial.add(0);
    }
    polynomial.set(power, polynomial.get(power) + coefficient);
    if (polynomial.get(power) == 0) {
      trimLeadingZeros();
    }
  }

  /**
   * Returns the degree of the polynomial, which is the highest power with a non-zero
   * coefficient. If the polynomial is zero, the degree is 0.
   *
   * @return the degree of the polynomial.
   */
  @Override
  public int getDegree() {
    return Math.max(0, polynomial.size() - 1);
  }

  /**
   * Converts the polynomial into a string representation. Each term is represented
   * in the form of `ax^b`, where `a` is the coefficient and `b` is the power.
   *
   * @return the string representation of the polynomial.
   */
  @Override
  public String toString() {
    if (this.polynomial.isEmpty()) {
      return "0";
    }
    StringBuilder stringPolynomial = new StringBuilder();
    for (int power = this.polynomial.size() - 1; power >= 0; power--) {
      int coefficient = this.polynomial.get(power);
      if (coefficient == 0) {
        continue;
      }
      if (coefficient < 0) {
        stringPolynomial.append("-");
      } else if (stringPolynomial.length() > 0) {
        stringPolynomial.append("+");
      }
      coefficient = Math.abs(coefficient);
      if (power == 0) {
        stringPolynomial.append(coefficient);
      } else {
        stringPolynomial.append(coefficient).append("x^").append(power);
      }
    }
    return stringPolynomial.toString();
  }

  /**
   * Evaluates the polynomial at a given value of `x` by calculating the sum of each
   * term's coefficient multiplied by `x` raised to the power of the term.
   *
   * @param x the value at which the polynomial is to be evaluated.
   * @return the result of the evaluation.
   */
  @Override
  public double evaluate(double x) {
    double ans = 0;
    for (int power = 0; power < polynomial.size(); power++) {
      int coefficient = polynomial.get(power);
      ans += coefficient * Math.pow(x, power);
    }
    return ans;
  }

  /**
   * Fetches the coefficient of the term with the specified power. If the power is
   * not present, it returns 0.
   *
   * @param power the power whose coefficient is sought.
   * @return the coefficient of the specified power, or 0 if not present.
   */
  @Override
  public int getCoefficient(int power) {
    if (power < 0) {
      return 0;
    }
    if (power >= polynomial.size()) {
      return 0;
    }
    return polynomial.get(power);
  }

}
