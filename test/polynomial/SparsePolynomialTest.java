package polynomial;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for Sparse polynomial.
 */
public class SparsePolynomialTest extends AbstractPolynomialTest {

  private Polynomial spaPolynomial = new SparsePolynomial();

  /**
   * This method is for returning an object of sparse polynomial.
   *
   * @return object of this class.
   */
  @Override
  protected Polynomial returnObject() {
    return new SparsePolynomial();
  }

  /**
   * This method is for returning an object of Simple polynomial.
   *
   * @return object of SimplePolynomial class.
   */
  @Override
  protected Polynomial returnObjectDiff() {
    return new SimplePolynomial();
  }

  /**
   * Addition of two big sparse polynomials.
   */
  @Test(timeout = 10)
  public void test75() {
    spaPolynomial.addTerm(1, 100000);
    Polynomial poly2 = this.returnObject();
    poly2.addTerm(2, 100000);
    spaPolynomial = spaPolynomial.add(poly2);
    assertEquals(100000, spaPolynomial.getDegree());
  }

  /**
   * Multiplying two big sparse polynomials.
   */
  @Test(timeout = 10)
  public void test78() {
    spaPolynomial.addTerm(1, 100000);
    spaPolynomial.addTerm(3, 0);
    Polynomial poly2 = this.returnObject();
    poly2.addTerm(2, 100000);
    spaPolynomial = spaPolynomial.multiply(poly2);
    assertEquals(6, spaPolynomial.getCoefficient(100000));
  }
}