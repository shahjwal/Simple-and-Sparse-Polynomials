package polynomial;

import java.util.Objects;

/**
 * This is the SparsePolynomial class which represents a polynomial with terms
 * that have only non-zero coefficients. This class supports functionalities such as
 * adding terms, adding polynomials, multiplying polynomials, calculating the derivative,
 * getting coefficients, evaluating the polynomial at a value, generating a string
 * representation, and checking equality with another polynomial. The polynomial is
 * represented using a linked list, initialized through a separate PolynomialNode class.
 */
public class SparsePolynomial extends AbstractPolynomial {

  private PolynomialNode head;

  /**
   * Adds a SimplePolynomial to this SparsePolynomial. Each term from the simple
   * polynomial is added to the sparse polynomial by traversing the nodes.
   *
   * @param other the SimplePolynomial to add.
   * @return the resulting polynomial after the addition.
   */
  @Override
  protected Polynomial addSimple(SimplePolynomial other) {
    Polynomial additionPolynomial = new SparsePolynomial();
    for (int power = 0; power <= other.getDegree(); power++) {
      if (other.getCoefficient(power) != 0) {
        additionPolynomial.addTerm(other.getCoefficient(power), power);
      }
    }
    PolynomialNode tempHead = this.head;
    while (tempHead != null) {
      additionPolynomial.addTerm(tempHead.getCoefficient(), tempHead.getPower());
      tempHead = tempHead.getNext();
    }
    return additionPolynomial;
  }

  /**
   * Adds two sparse polynomials. It traverses both polynomials and also combines
   * the terms with matching powers.
   *
   * @param other the SparsePolynomial to add.
   * @return the resulting polynomial after the addition.
   */
  @Override
  protected Polynomial addSparse(SparsePolynomial other) {
    Polynomial additionPolynomial = new SparsePolynomial();
    if (this.head == null) {
      return other;
    } else if (other.head == null) {
      return this;
    } else {
      PolynomialNode thisPolynomialHead = this.head;
      while (thisPolynomialHead != null) {
        additionPolynomial.addTerm(thisPolynomialHead.getCoefficient(),
                thisPolynomialHead.getPower());
        thisPolynomialHead = thisPolynomialHead.getNext();
      }
      PolynomialNode thatPolynomialHead = other.head;
      while (thatPolynomialHead != null) {
        additionPolynomial.addTerm(thatPolynomialHead.getCoefficient(),
                thatPolynomialHead.getPower());
        thatPolynomialHead = thatPolynomialHead.getNext();
      }
    }
    return additionPolynomial;
  }

  /**
   * Multiplies this SparsePolynomial with a SimplePolynomial. Each term of the
   * simple polynomial is multiplied by each term of the sparse polynomial.
   *
   * @param other the SimplePolynomial to multiply.
   * @return the resulting polynomial after the multiplication.
   */
  @Override
  protected Polynomial multiplySimple(SimplePolynomial other) {
    Polynomial multiplyPolynomial = new SparsePolynomial();
    PolynomialNode tempHead = this.head;
    while (tempHead != null) {
      for (int power = 0; power <= other.getDegree(); power++) {
        int newCoefficient = other.getCoefficient(power) * tempHead.getCoefficient();
        int newPower = power + tempHead.getPower();
        multiplyPolynomial.addTerm(newCoefficient, newPower);
      }
      tempHead = tempHead.getNext();
    }
    return multiplyPolynomial;
  }

  /**
   * Multiplies two SparsePolynomials. Each term from one polynomial is multiplied
   * with every term from the other polynomial.
   *
   * @param other the SparsePolynomial to multiply.
   * @return the resulting polynomial after the multiplication.
   */
  @Override
  protected Polynomial multiplySparse(SparsePolynomial other) {
    Polynomial multiplyPolynomial = new SparsePolynomial();
    if (this.head == null) {
      return multiplyPolynomial;
    } else if (other.head == null) {
      return multiplyPolynomial;
    } else {
      PolynomialNode thisPolynomialHead = this.head;
      while (thisPolynomialHead != null) {
        PolynomialNode thatPolynomialHead = other.head;
        while (thatPolynomialHead != null) {
          int newCoefficient = thisPolynomialHead.getCoefficient()
                  * thatPolynomialHead.getCoefficient();
          int newPower = thatPolynomialHead.getPower() + thisPolynomialHead.getPower();
          multiplyPolynomial.addTerm(newCoefficient, newPower);
          thatPolynomialHead = thatPolynomialHead.getNext();
        }
        thisPolynomialHead = thisPolynomialHead.getNext();
      }
    }
    return multiplyPolynomial;
  }

  /**
   * Compares this SparsePolynomial with a SimplePolynomial by checking if all terms
   * are identical in both polynomials.
   *
   * @param other the SimplePolynomial to compare.
   * @return true if the polynomials are equal, otherwise false.
   */
  @Override
  protected boolean compareSimple(SimplePolynomial other) {
    PolynomialNode tempHead = this.head;
    for (int power = other.getDegree(); power >= 0; power--) {
      int otherCoefficient = other.getCoefficient(power);
      if (otherCoefficient == 0) {
        continue;
      }
      if (tempHead == null || tempHead.getPower() != power
              || tempHead.getCoefficient() != otherCoefficient) {
        return false;
      }
      tempHead = tempHead.getNext();
    }
    return tempHead == null;
  }

  /**
   * Compares two SparsePolynomials by traversing their terms and checking for equality
   * of the coefficients and powers.
   *
   * @param other the SparsePolynomial to compare.
   * @return true if the polynomials are equal, otherwise false.
   */
  @Override
  protected boolean compareSparse(SparsePolynomial other) {
    PolynomialNode thisTempHead = this.head;
    PolynomialNode otherTempHead = other.head;
    while (thisTempHead != null && otherTempHead != null) {
      if (thisTempHead.getCoefficient() != otherTempHead.getCoefficient()
              || thisTempHead.getPower() != otherTempHead.getPower()) {
        return false;
      }
      thisTempHead = thisTempHead.getNext();
      otherTempHead = otherTempHead.getNext();
    }
    return thisTempHead == null && otherTempHead == null;
  }

  /**
   * Compares the terms of this polynomial with another polynomial. Calls the appropriate
   * comparison method based on the type of the other polynomial.
   *
   * @param other the polynomial to compare.
   * @return true if the polynomials are equal, otherwise false.
   */
  @Override
  protected boolean compareTerms(Polynomial other) {
    if (other instanceof AbstractPolynomial) {
      return ((AbstractPolynomial) other).compareSparse(this);
    }
    return false;
  }

  /**
   * Generates a hash code for this SparsePolynomial based on its terms, using
   * a combination of the coefficients and powers.
   *
   * @return the generated hash code.
   */
  @Override
  protected int generateHash() {
    int hash = 1;
    PolynomialNode currentNode = this.head;
    while (currentNode != null) {
      Integer coefficient = currentNode.getCoefficient();
      Integer power = currentNode.getPower();
      hash = hash + coefficient.hashCode();
      hash = hash + power.hashCode();
      currentNode = currentNode.getNext();
    }
    return hash;
  }

  /**
   * Default constructor for SparsePolynomial, initializes an empty polynomial.
   */
  public SparsePolynomial() {
    this.head = null;
  }

  /**
   * Adds another polynomial to this SparsePolynomial.
   *
   * @param other the polynomial to add.
   * @return the resulting polynomial after the addition.
   */
  @Override
  public Polynomial add(Polynomial other) {
    if (other instanceof AbstractPolynomial) {
      return ((AbstractPolynomial) other).addSparse(this);
    }
    return new SparsePolynomial();
  }

  /**
   * Multiplies another polynomial with this SparsePolynomial.
   *
   * @param other the polynomial to multiply.
   * @return the resulting polynomial after the multiplication.
   */
  @Override
  public Polynomial multiply(Polynomial other) {
    if (other instanceof AbstractPolynomial) {
      return ((AbstractPolynomial) other).multiplySparse(this);
    } else {
      return new SparsePolynomial();
    }
  }

  /**
   * Computes the derivative of this SparsePolynomial by differentiating each term.
   *
   * @return the resulting polynomial after differentiation.
   */
  @Override
  public Polynomial derivative() {
    Polynomial derivativePolynomial = new SparsePolynomial();
    if (this.head == null) {
      return derivativePolynomial;
    }
    PolynomialNode tempHead = this.head;
    while (tempHead != null) {
      if (tempHead.getPower() > 0) {
        int newCoefficient = tempHead.getCoefficient() * tempHead.getPower();
        int newPower = tempHead.getPower() - 1;
        derivativePolynomial.addTerm(newCoefficient, newPower);
      }
      tempHead = tempHead.getNext();
    }
    return derivativePolynomial;
  }

  /**
   * Adds a term to the polynomial with the given coefficient and power.
   *
   * @param coefficient the coefficient of the term.
   * @param power       the power of the term.
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

    PolynomialNode newNode = new PolynomialNode(coefficient, power);

    if (this.head == null || this.head.getPower() < power) {
      newNode.setNext(this.head);
      this.head = newNode;
    } else {
      PolynomialNode current = this.head;
      PolynomialNode previous = null;
      while (current != null && current.getPower() > power) {
        previous = current;
        current = current.getNext();
      }
      if (current != null && Objects.equals(current.getPower(), power)) {
        current.setCoefficient(current.getCoefficient() + coefficient);

        if (current.getCoefficient() == 0) {
          if (previous == null) {
            this.head = current.getNext();
          } else {
            previous.setNext(current.getNext());
          }
        }
      } else {
        if (previous != null) {
          previous.setNext(newNode);
        }
        newNode.setNext(current);
      }
    }
  }

  /**
   * Returns the degree of the polynomial, which is the highest power of the terms.
   *
   * @return the degree of the polynomial.
   */
  @Override
  public int getDegree() {
    if (this.head == null) {
      return 0;
    }
    return this.head.getPower();
  }

  /**
   * Returns the string representation of the polynomial, with each term displayed
   * in decreasing order of power.
   *
   * @return the string representation of the polynomial.
   */
  @Override
  public String toString() {
    if (this.head == null) {
      return "0";
    }
    StringBuilder stringPolynomial = new StringBuilder();
    PolynomialNode tempHead = this.head;
    while (tempHead != null) {
      int coefficient = tempHead.getCoefficient();
      int power = tempHead.getPower();
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
      tempHead = tempHead.getNext();
    }
    return stringPolynomial.toString();
  }


  /**
   * Evaluates the polynomial by substituting the given value for the variable.
   *
   * @param x the value to substitute.
   * @return the result of the evaluation.
   */
  @Override
  public double evaluate(double x) {
    double ans = 0;
    PolynomialNode tempHead = this.head;
    while (tempHead != null) {
      ans += tempHead.getCoefficient() * Math.pow(x, tempHead.getPower());
      tempHead = tempHead.getNext();
    }
    return ans;
  }

  /**
   * Gets the coefficient of the term with the specified power.
   *
   * @param power the power of the term.
   * @return the coefficient of the term, or 0 if no such term exists.
   */
  @Override
  public int getCoefficient(int power) {
    if (power < 0) {
      return 0;
    }
    PolynomialNode tempHead = this.head;
    while (tempHead != null) {
      if (tempHead.getPower() < power) {
        return 0;
      }
      if (Objects.equals(tempHead.getPower(), power)) {
        return tempHead.getCoefficient();
      }
      tempHead = tempHead.getNext();
    }
    return 0;
  }

}
