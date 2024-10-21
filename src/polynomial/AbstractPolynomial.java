package polynomial;

import java.util.Objects;

/**
 * The AbstractPolynomial class defines common behavior for all polynomial implementations.
 * This abstract class is implemented by concrete polynomial classes such as SimplePolynomial
 * and SparsePolynomial. It provides a set of abstract methods that must be implemented by
 * subclasses, as well as common functionality such as string conversion, equality checking,
 * and hash code generation.
 */
abstract class AbstractPolynomial implements Polynomial {

  /**
   * Adds the given SimplePolynomial to the current polynomial.
   * Must be implemented by subclasses.
   *
   * @param other the SimplePolynomial to be added.
   * @return the result of the addition as a new Polynomial.
   */
  protected abstract Polynomial addSimple(SimplePolynomial other);

  /**
   * Adds the given SparsePolynomial to the current polynomial.
   * Must be implemented by subclasses.
   *
   * @param other the SparsePolynomial to be added.
   * @return the result of the addition as a new Polynomial.
   */
  protected abstract Polynomial addSparse(SparsePolynomial other);

  /**
   * Multiplies the current polynomial by the given SimplePolynomial.
   * Must be implemented by subclasses.
   *
   * @param other the SimplePolynomial to multiply.
   * @return the result of the multiplication as a new Polynomial.
   */
  protected abstract Polynomial multiplySimple(SimplePolynomial other);

  /**
   * Multiplies the current polynomial by the given SparsePolynomial.
   * Must be implemented by subclasses.
   *
   * @param other the SparsePolynomial to multiply.
   * @return the result of the multiplication as a new Polynomial.
   */
  protected abstract Polynomial multiplySparse(SparsePolynomial other);

  /**
   * Compares the current polynomial with the given SimplePolynomial.
   * Must be implemented by subclasses to check if two polynomials are equal.
   *
   * @param other the SimplePolynomial to compare.
   * @return true if the polynomials are equal, false otherwise.
   */
  protected abstract boolean compareSimple(SimplePolynomial other);

  /**
   * Compares the current polynomial with the given SparsePolynomial.
   * Must be implemented by subclasses to check if two polynomials are equal.
   *
   * @param other the SparsePolynomial to compare.
   * @return true if the polynomials are equal, false otherwise.
   */
  protected abstract boolean compareSparse(SparsePolynomial other);

  /**
   * Compares the terms of the current polynomial with another polynomial.
   * Must be implemented by subclasses to check if the terms are equivalent.
   *
   * @param other the other polynomial to compare the terms.
   * @return true if the terms are equivalent, false otherwise.
   */
  protected abstract boolean compareTerms(Polynomial other);

  /**
   * Generates the hash code for the polynomial.
   * Must be implemented by subclasses to provide a unique hash code
   * based on the polynomial's terms.
   *
   * @return the generated hash code.
   */
  protected abstract int generateHash();

  /**
   * Compares the current polynomial with another object for equality.
   * The comparison checks if the other object is a Polynomial and has the same degree and terms.
   *
   * @param o the object to compare.
   * @return true if the polynomials are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Polynomial)) {
      return false;
    }
    Polynomial other = (Polynomial) o;
    if (!Objects.equals(other.getDegree(), this.getDegree())) {
      return false;
    }
    return this.compareTerms(other);
  }

  /**
   * Generates a hash code for the current polynomial.
   * The hash code is based on the terms of the polynomial.
   *
   * @return the generated hash code.
   */
  @Override
  public int hashCode() {
    return this.generateHash();
  }
}
