package polynomial;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for Simple Polynomial.
 */
public class SimplePolynomialTest extends AbstractPolynomialTest {

  private Polynomial simPolynomial = new SimplePolynomial();

  /**
   * This method is for returning an object of simple polynomial.
   *
   * @return object of this class.
   */
  @Override
  protected Polynomial returnObject() {
    return new SimplePolynomial();
  }

  /**
   * This method is for returning an object of Sparse polynomial.
   *
   * @return object of SparsePolynomial.
   */
  @Override
  protected Polynomial returnObjectDiff() {
    return new SparsePolynomial();
  }


  /**
   * Adding two big simple polynomials.
   */
  @Test(timeout = 1000)
  public void test72() {
    for (int i = 0; i < 100000; i++) {
      simPolynomial.addTerm(i + 1, i);
    }
    Polynomial poly2 = this.returnObject();
    for (int i = 0; i < 100000; i++) {
      poly2.addTerm(i - 1, i);
    }
    simPolynomial = simPolynomial.add(poly2);
    assertEquals(99999, simPolynomial.getDegree());
  }

  /**
   * Addition of two big simple polynomials.
   */
  @Test(timeout = 1000)
  public void test73() {
    simPolynomial.addTerm(1, 100000);
    Polynomial poly2 = this.returnObject();
    poly2.addTerm(2, 100000);
    simPolynomial = simPolynomial.add(poly2);
    assertEquals(100000, simPolynomial.getDegree());
  }

  /**
   * Multiplying two simple polynomials.
   */
  @Test(timeout = 3000)
  public void Test76() {
    for (int i = 0; i < 2000; i++) {
      simPolynomial.addTerm(i + 1, i);
    }
    Polynomial poly2 = this.returnObject();
    for (int i = 0; i < 2000; i++) {
      poly2.addTerm(i + 2, i);
    }
    simPolynomial = simPolynomial.multiply(poly2);
    assertEquals(2, simPolynomial.getCoefficient(0));
  }

  /**
   * Multiplying of two big simple polynomials.
   */
  @Test(timeout = 1000)
  public void test77() {
    simPolynomial.addTerm(1, 100000);
    simPolynomial.addTerm(1, 0);
    Polynomial poly2 = this.returnObject();
    poly2.addTerm(2, 100000);
    simPolynomial = simPolynomial.multiply(poly2);
    assertEquals(2, simPolynomial.getCoefficient(100000));
  }


}