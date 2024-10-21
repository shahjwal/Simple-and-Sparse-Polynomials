package polynomial;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the abstract test class for Polynomial.
 */
public abstract class AbstractPolynomialTest {

  /**
   * This method signature is the factory pattern for child concrete classes.
   *
   * @return the object of the desired class.
   */
  protected abstract Polynomial returnObject();

  protected abstract Polynomial returnObjectDiff();

  private Polynomial polynomial;

  /**
   * This is the setup case for this test class.
   */
  @Before
  public void setUp() {
    polynomial = this.returnObject();
  }

  /**
   * Testing the constructor.
   */
  @Test
  public void TestConstructor() {
    polynomial = this.returnObject();
    assertEquals("0", polynomial.toString());
  }

  /**
   * Tests if we add a negative power term, it throws exception or not.
   */
  @Test(expected = IllegalArgumentException.class)
  public void Test1() {
    polynomial.addTerm(1, -1);
  }

  /**
   * Tests if it adds positive power term, it gets add or not.
   */
  @Test
  public void Test2() {
    polynomial.addTerm(1, 2);
    assertEquals("1x^2", polynomial.toString());
  }

  /**
   * Tests if we add a term with negative coefficient.
   */
  @Test
  public void Test3() {
    polynomial.addTerm(1, 2);
    polynomial.addTerm(-2, 3);
    assertEquals("-2x^3+1x^2", polynomial.toString());
  }

  /**
   * Adding a constant term.
   */
  @Test
  public void Test4() {
    polynomial.addTerm(1, 0);
    assertEquals("1", polynomial.toString());
  }

  /**
   * Adding multiple terms.
   */
  @Test
  public void Test5() {
    polynomial.addTerm(1, 2);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 4);
    assertEquals("3x^4-2x^3+1x^2", polynomial.toString());
  }

  /**
   * Tests if we add a term with same power which exists in polynomial.
   */
  @Test
  public void Test6() {
    polynomial.addTerm(1, 2);
    polynomial.addTerm(2, 3);
    polynomial.addTerm(3, 3);
    assertEquals("5x^3+1x^2", polynomial.toString());
  }

  /**
   * Adding a term with zero coefficient.
   */
  @Test
  public void Test7() {
    polynomial.addTerm(0, 3);
    assertEquals("0", polynomial.toString());
  }

  /**
   * Tests if the zero term is removed or not.
   */
  @Test
  public void Test8() {
    polynomial.addTerm(3, 2);
    polynomial.addTerm(0, 2);
    assertEquals("3x^2", polynomial.toString());
  }

  /**
   * Checks if the adds function works or not.
   */
  @Test
  public void Test9() {
    Polynomial poly2 = this.returnObject();
    poly2.addTerm(1, 0);
    poly2.addTerm(-2, 3);
    poly2.addTerm(3, 4);

    polynomial.addTerm(2, 0);
    polynomial.addTerm(3, 1);
    polynomial.addTerm(4, 3);
    polynomial.addTerm(2, 4);

    polynomial = polynomial.add(poly2);
    assertEquals("5x^4+2x^3+3x^1+3", polynomial.toString());
  }

  /**
   * checks add function if we add the same polynomial to itself.
   */
  @Test
  public void Test10() {
    polynomial.addTerm(2, 2);
    polynomial.addTerm(2, 3);
    polynomial = polynomial.add(polynomial);
    assertEquals("4x^3+4x^2", polynomial.toString());
  }

  /**
   * Checks if we add a polynomial to empty polynomial.
   */
  @Test
  public void Test11() {
    Polynomial poly2 = this.returnObject();
    poly2.addTerm(1, 0);
    poly2.addTerm(-2, 3);
    poly2.addTerm(3, 4);
    polynomial = polynomial.add(poly2);
    assertEquals("3x^4-2x^3+1", polynomial.toString());
  }

  /**
   * Checks if we add empty polynomial to an existing polynomial.
   */
  @Test
  public void Test12() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 4);
    Polynomial poly2 = this.returnObject();
    polynomial = polynomial.add(poly2);
    assertEquals("3x^4-2x^3+1", polynomial.toString());
  }

  /**
   * Adding two polynomials with no common powers.
   */
  @Test
  public void Test13() {
    polynomial.addTerm(5, 2);
    polynomial.addTerm(3, 1);
    Polynomial poly2 = this.returnObject();
    poly2.addTerm(4, 3);
    poly2.addTerm(7, 0);
    polynomial = polynomial.add(poly2);
    assertEquals("4x^3+5x^2+3x^1+7", polynomial.toString());
  }

  /**
   * Checks if we add two empty polynomial.
   */
  @Test
  public void Test14() {
    Polynomial poly2 = this.returnObject();
    polynomial = polynomial.add(poly2);
    assertEquals("0", polynomial.toString());
  }

  /**
   * Checks if we add polynomials with same power terms.
   */
  @Test
  public void Test15() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 4);

    Polynomial poly2 = this.returnObject();
    poly2.addTerm(3, 0);
    poly2.addTerm(4, 3);
    poly2.addTerm(5, 4);

    polynomial = polynomial.add(poly2);
    assertEquals("8x^4+2x^3+4", polynomial.toString());
  }

  /**
   * Tests if we add polynomial with at least 1 common term.
   */
  @Test
  public void Test16() {
    polynomial.addTerm(5, 2);
    polynomial.addTerm(3, 1);
    Polynomial poly2 = this.returnObject();
    poly2.addTerm(4, 2);
    poly2.addTerm(7, 0);
    polynomial = polynomial.add(poly2);
    assertEquals("9x^2+3x^1+7", polynomial.toString());
  }

  /**
   * If we just add constant to existing polynomial.
   */
  @Test
  public void Test17() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 2);

    Polynomial poly2 = this.returnObject();
    poly2.addTerm(3, 0);

    polynomial = polynomial.add(poly2);
    assertEquals("-2x^3+3x^2+4", polynomial.toString());
  }

  /**
   * If we add multiple polynomials to existing polynomial.
   */
  @Test
  public void Test18() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 2);

    Polynomial poly2 = this.returnObject();
    poly2.addTerm(3, 0);
    poly2.addTerm(4, 3);
    poly2.addTerm(4, 2);
    poly2.addTerm(5, 4);

    Polynomial poly3 = this.returnObject();
    poly3.addTerm(3, 0);
    poly3.addTerm(7, 2);
    poly3.addTerm(7, 4);

    polynomial = polynomial.add(poly2);
    polynomial = polynomial.add(poly3);

    assertEquals("12x^4+2x^3+14x^2+7", polynomial.toString());
  }

  /**
   * Multiply with empty polynomial.
   */
  @Test
  public void Test19() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 2);
    Polynomial poly2 = this.returnObject();
    polynomial = polynomial.multiply(poly2);
    assertEquals("0", polynomial.toString());
  }

  /**
   * Multiply with 1.
   */
  @Test
  public void Test20() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 2);
    Polynomial poly2 = this.returnObject();
    poly2.addTerm(1, 0);
    polynomial = polynomial.multiply(poly2);
    assertEquals("-2x^3+3x^2+1", polynomial.toString());
  }

  /**
   * Multiply with itself.
   */
  @Test
  public void Test21() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 2);
    polynomial = polynomial.multiply(polynomial);
    assertEquals("4x^6-12x^5+9x^4-4x^3+6x^2+1", polynomial.toString());
  }

  /**
   * Multiply with a constant.
   */
  @Test
  public void Test22() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 2);
    Polynomial poly2 = this.returnObject();
    poly2.addTerm(3, 0);
    polynomial = polynomial.multiply(poly2);
    assertEquals("-6x^3+9x^2+3", polynomial.toString());
  }

  /**
   * Multiply with a regular polynomial.
   */
  @Test
  public void Test23() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 2);

    Polynomial poly2 = this.returnObject();
    poly2.addTerm(3, 0);
    poly2.addTerm(4, 3);
    poly2.addTerm(4, 2);
    poly2.addTerm(5, 4);

    polynomial = polynomial.multiply(poly2);
    assertEquals("-10x^7+7x^6+4x^5+17x^4-2x^3+13x^2+3", polynomial.toString());
  }

  /**
   * Running multiply function twice with 3 polynomials.
   */
  @Test
  public void Test24() {
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 2);

    Polynomial poly2 = this.returnObject();
    poly2.addTerm(3, 0);
    poly2.addTerm(4, 2);

    Polynomial poly3 = this.returnObject();
    poly3.addTerm(3, 0);
    poly3.addTerm(7, 2);

    polynomial = polynomial.multiply(poly2).multiply(poly3);
    assertEquals("-56x^7+84x^6-66x^5+99x^4-18x^3+27x^2", polynomial.toString());
  }

  /**
   * When we multiply with empty polynomial.
   */
  @Test
  public void Test25() {
    Polynomial poly2 = this.returnObject();
    poly2.addTerm(3, 0);
    poly2.addTerm(4, 3);
    poly2.addTerm(4, 2);
    poly2.addTerm(5, 4);

    polynomial = polynomial.multiply(poly2);
    assertEquals("0", polynomial.toString());
  }

  /**
   * Multiplying two empty polynomial.
   */
  @Test
  public void Test26() {
    Polynomial poly2 = this.returnObject();
    polynomial = polynomial.multiply(poly2);
    assertEquals("0", polynomial.toString());
  }

  /**
   * Checks if the derivative function works properly or not
   * Derivation of Kx^n = (K*n)x^n-1.
   */
  @Test
  public void Test27() {
    polynomial.addTerm(1, 2);
    polynomial.addTerm(4, 3);
    polynomial.addTerm(2, 1);
    polynomial.addTerm(4, 0);
    polynomial = polynomial.derivative();
    assertEquals("12x^2+2x^1+2", polynomial.toString());
  }

  /**
   * Checks if derivative function works with polynomial having negative terms.
   */
  @Test
  public void Test28() {
    polynomial.addTerm(-1, 2);
    polynomial.addTerm(4, 3);
    polynomial.addTerm(2, 0);
    polynomial = polynomial.derivative();
    assertEquals("12x^2-2x^1", polynomial.toString());
  }

  /**
   * Checks if derivative function returns zero if polynomial contains only constant term.
   */
  @Test
  public void Test29() {
    polynomial.addTerm(1, 0);
    polynomial = polynomial.derivative();
    assertEquals("0", polynomial.toString());
  }

  /**
   * Checks if the derivative function works on polynomial contains negative terms.
   */
  @Test
  public void Test30() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(-3, 4);
    polynomial = polynomial.derivative();
    assertEquals("-12x^3-6x^2", polynomial.toString());
  }

  /**
   * Checks if the derivative function works on empty polynomial or not.
   */
  @Test
  public void Test31() {
    polynomial = polynomial.derivative();
    assertEquals("0", polynomial.toString());
  }

  /**
   * Derivative of a linear polynomial.
   */
  @Test
  public void Test32() {
    polynomial.addTerm(4, 1);
    polynomial = polynomial.derivative();
    assertEquals("4", polynomial.toString());
  }

  /**
   * if we do derivative twice.
   */
  @Test
  public void Test33() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 2);
    polynomial = polynomial.derivative().derivative();
    assertEquals("-12x^1+6", polynomial.toString());
  }

  /**
   * Checks if the evaluate function works correctly or not by passing 1.
   */
  @Test
  public void Test34() {
    polynomial.addTerm(1, 2);
    polynomial.addTerm(4, 3);
    polynomial.addTerm(2, 1);
    polynomial.addTerm(4, 0);
    assertEquals(11.0, polynomial.evaluate(1), 0.00001);
  }

  /**
   * Checks the evaluate function if we pass 0.
   */
  @Test
  public void Test35() {
    polynomial.addTerm(1, 2);
    polynomial.addTerm(4, 3);
    polynomial.addTerm(2, 1);
    polynomial.addTerm(4, 0);
    assertEquals(4, polynomial.evaluate(0), 0.00001);
  }

  /**
   * Checks the evaluate function if we pass a negative integer.
   */
  @Test
  public void Test36() {
    polynomial.addTerm(1, 2);
    polynomial.addTerm(4, 3);
    polynomial.addTerm(2, 1);
    polynomial.addTerm(4, 0);
    assertEquals(-1, polynomial.evaluate(-1), 0.00001);
  }

  /**
   * Checks the evaluate function if we pass a positive floating number.
   */
  @Test
  public void Test37() {
    polynomial.addTerm(1, 2);
    polynomial.addTerm(4, 3);
    polynomial.addTerm(2, 1);
    polynomial.addTerm(4, 0);
    assertEquals(77.75, polynomial.evaluate(2.5), 0.00001);
  }

  /**
   * Checks the evaluate function if we pass another floating number.
   */
  @Test
  public void Test38() {
    polynomial.addTerm(1, 2);
    polynomial.addTerm(4, 3);
    polynomial.addTerm(2, 1);
    polynomial.addTerm(4, 0);
    assertEquals(4.103, polynomial.evaluate(0.05), 0.00001);
  }

  /**
   * Checks the evaluate function if we pass a negative floating number.
   */
  @Test
  public void Test39() {
    polynomial.addTerm(1, 2);
    polynomial.addTerm(4, 3);
    polynomial.addTerm(2, 1);
    polynomial.addTerm(4, 0);
    assertEquals(-57.25, polynomial.evaluate(-2.5), 0.00001);
  }

  /**
   * Checks the evaluate function if we pass another floating number.
   */
  @Test
  public void Test40() {
    polynomial.addTerm(1, 2);
    polynomial.addTerm(4, 3);
    polynomial.addTerm(2, 1);
    polynomial.addTerm(4, 0);
    assertEquals(3.902, polynomial.evaluate(-0.05), 0.00001);
  }

  /**
   * Checks if the polynomial returns correct degree.
   */
  @Test
  public void Test41() {
    polynomial.addTerm(1, 2);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 5);
    polynomial.addTerm(3, 4);
    assertEquals(5, polynomial.getDegree());
  }

  /**
   * Checks the degree if there are no terms in polynomial.
   */
  @Test
  public void Test42() {
    assertEquals(0, polynomial.getDegree());
  }

  /**
   * Tests the degree if there is only one constant term in polynomial.
   */
  @Test
  public void Test43() {
    polynomial.addTerm(4, 0);
    assertEquals(0, polynomial.getDegree());
  }

  /**
   * checks if the degree of the polynomial is proper or not after adding.
   */
  @Test
  public void Test44() {
    Polynomial poly2 = this.returnObject();
    poly2.addTerm(1, 0);
    poly2.addTerm(-2, 3);

    polynomial.addTerm(2, 0);
    polynomial.addTerm(3, 1);
    polynomial.addTerm(4, 3);
    polynomial.addTerm(2, 4);

    int degree = Math.max(polynomial.getDegree(), poly2.getDegree());
    polynomial = polynomial.add(poly2);
    assertEquals(4, degree);
  }

  /**
   * if we cancel out the term in adding, check the degree.
   */
  @Test
  public void Test45() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 2);

    Polynomial poly2 = this.returnObject();
    poly2.addTerm(3, 0);
    poly2.addTerm(2, 3);
    poly2.addTerm(4, 2);

    polynomial = polynomial.add(poly2);
    assertEquals(2, polynomial.getDegree());
  }

  /**
   * Checks if the degree returns true when we multiply two polynomials.
   */
  @Test
  public void Test46() {
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 2);

    Polynomial poly2 = this.returnObject();
    poly2.addTerm(3, 0);
    poly2.addTerm(4, 5);

    Polynomial poly3 = polynomial.multiply(poly2);
    int degree = poly3.getDegree();
    assertEquals(8, degree);
  }

  /**
   * Checks if the degree reduces by one after derivation.
   */
  @Test
  public void Test47() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 2);
    polynomial = polynomial.derivative();
    assertEquals(2, polynomial.getDegree());
  }

  /**
   * Checks if it returns right coefficient of the required power term.
   */
  @Test
  public void Test48() {
    polynomial.addTerm(1, 2);
    polynomial.addTerm(2, 3);
    polynomial.addTerm(3, 5);
    assertEquals(2, polynomial.getCoefficient(3));
  }

  /**
   * Checks the coefficient if the term of required power is not present.
   */
  @Test
  public void Test49() {
    polynomial.addTerm(1, 2);
    polynomial.addTerm(2, 3);
    polynomial.addTerm(3, 5);
    assertEquals(0, polynomial.getCoefficient(6));
  }

  /**
   * Checks the return if the coefficient of the required power term is negative.
   */
  @Test
  public void Test50() {
    polynomial.addTerm(1, 2);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 5);
    assertEquals(-2, polynomial.getCoefficient(3));
  }

  /**
   * Checks if the get coefficient method is called for a negative power.
   */
  @Test
  public void Test51() {
    polynomial.addTerm(1, 2);
    polynomial.addTerm(2, 3);
    polynomial.addTerm(3, 5);
    assertEquals(0, polynomial.getCoefficient(-1));
  }

  /**
   * Getting the coefficient of a constant term.
   */
  @Test
  public void Test52() {
    polynomial.addTerm(4, 3);
    polynomial.addTerm(3, 0);
    assertEquals(3, polynomial.getCoefficient(0));
  }

  /**
   * Checks if the toString method works correctly or not.
   */
  @Test
  public void Test53() {
    polynomial.addTerm(2, 0);
    polynomial.addTerm(3, 1);
    polynomial.addTerm(4, 3);
    polynomial.addTerm(2, 4);
    assertEquals("2x^4+4x^3+3x^1+2", polynomial.toString());
  }

  /**
   * Test toString method for empty polynomial.
   */
  @Test
  public void Test54() {
    assertEquals("0", polynomial.toString());
  }

  /**
   * Tests toString with a polynomial with negative coefficient.
   */
  @Test
  public void Test55() {
    polynomial.addTerm(2, 0);
    polynomial.addTerm(-3, 1);
    polynomial.addTerm(4, 3);
    polynomial.addTerm(2, 4);
    assertEquals("2x^4+4x^3-3x^1+2", polynomial.toString());
  }

  /**
   * Checks if the hashcode remains the same.
   */
  @Test
  public void test56() {
    Polynomial poly1 = this.returnObject();
    poly1.addTerm(3, 2);
    poly1.addTerm(2, 1);
    assertEquals(poly1.hashCode(), poly1.hashCode());
  }

  /**
   * Polynomials with equal terms will have same hashcode regardless of their type.
   */
  @Test
  public void Test57() {
    Polynomial poly1 = this.returnObject();
    poly1.addTerm(3, 2);
    poly1.addTerm(2, 1);

    Polynomial poly2 = this.returnObjectDiff();
    poly2.addTerm(2, 1);
    poly2.addTerm(3, 2);

    assertEquals(poly1, poly2);
    assertEquals(poly1.hashCode(), poly2.hashCode());
  }

  /**
   * Different hashcode.
   */
  @Test
  public void Test58() {
    Polynomial poly1 = this.returnObject();
    poly1.addTerm(3, 2);

    Polynomial poly2 = this.returnObject();
    poly2.addTerm(4, 2);

    assertNotEquals(poly1, poly2);
    assertNotEquals(poly1.hashCode(), poly2.hashCode());
  }

  /**
   * Tests the hashcode after changing the polynomial.
   */
  @Test
  public void Test59() {
    Polynomial poly1 = this.returnObject();
    poly1.addTerm(3, 2);
    int hashCodeBefore = poly1.hashCode();

    poly1.addTerm(2, 1);
    int hashCodeAfter = poly1.hashCode();
    assertNotEquals(hashCodeBefore, hashCodeAfter);
  }

  /**
   * Hashcode of equal polynomial of different type are the same.
   */
  @Test
  public void Test60() {
    Polynomial poly1 = this.returnObject();
    poly1.addTerm(3, 2);
    poly1.addTerm(2, 1);

    Polynomial poly2 = this.returnObjectDiff();
    poly2.addTerm(2, 1);
    poly2.addTerm(3, 2);

    assertEquals(poly1, poly2);
    assertEquals(poly1.hashCode(), poly2.hashCode());
  }

  /**
   * Hashcode of different polynomials of different types are different.
   */
  @Test
  public void Test61() {
    Polynomial poly1 = this.returnObject();
    poly1.addTerm(3, 2);
    poly1.addTerm(2, 1);

    Polynomial poly2 = this.returnObjectDiff();
    poly2.addTerm(3, 1);
    poly2.addTerm(3, 2);


    Polynomial poly3 = this.returnObjectDiff();
    poly3.addTerm(3, 2);
    poly3.addTerm(2, 1);

    Polynomial poly4 = this.returnObject();
    poly4.addTerm(3, 1);
    poly4.addTerm(3, 2);

    assertNotEquals(poly1, poly2);
    assertNotEquals(poly1.hashCode(), poly2.hashCode());
    assertNotEquals(poly3, poly4);
    assertNotEquals(poly3.hashCode(), poly4.hashCode());
  }

  /**
   * Hashcode of two empty polynomial of either same or different type are always equal.
   */
  @Test
  public void Test62() {
    Polynomial poly1 = this.returnObject();
    Polynomial poly2 = this.returnObject();
    Polynomial poly3 = this.returnObjectDiff();
    Polynomial poly4 = this.returnObjectDiff();
    assertEquals(poly1.hashCode(), poly2.hashCode());
    assertEquals(poly3.hashCode(), poly4.hashCode());
    assertEquals(poly1.hashCode(), poly3.hashCode());
  }

  /**
   * If the polynomials are of same terms and same types.
   */
  @Test
  public void Test64() {
    Polynomial poly1 = this.returnObject();
    Polynomial poly2 = this.returnObject();
    poly1.addTerm(3, 3);
    poly1.addTerm(2, 2);
    poly2.addTerm(3, 3);
    poly2.addTerm(2, 2);
    Polynomial poly3 = this.returnObjectDiff();
    Polynomial poly4 = this.returnObjectDiff();
    poly3.addTerm(3, 3);
    poly3.addTerm(2, 2);
    poly4.addTerm(3, 3);
    poly4.addTerm(2, 2);
    assertTrue(poly1.equals(poly2));
    assertTrue(poly3.equals(poly4));
  }

  /**
   * If the polynomials are of same terms and different types.
   */
  @Test
  public void Test65() {
    Polynomial poly1 = this.returnObject();
    Polynomial poly2 = this.returnObjectDiff();
    poly1.addTerm(3, 3);
    poly1.addTerm(2, 2);
    poly2.addTerm(3, 3);
    poly2.addTerm(2, 2);
    assertTrue(poly1.equals(poly2));
  }

  /**
   * Different polynomials of same type.
   */
  @Test
  public void Test66() {
    Polynomial poly1 = this.returnObject();
    Polynomial poly2 = this.returnObject();
    poly1.addTerm(3, 3);
    poly1.addTerm(2, 2);
    poly2.addTerm(4, 3);
    poly2.addTerm(2, 2);
    Polynomial poly3 = this.returnObjectDiff();
    Polynomial poly4 = this.returnObjectDiff();
    poly3.addTerm(3, 3);
    poly3.addTerm(2, 2);
    poly4.addTerm(4, 3);
    poly4.addTerm(2, 2);
    assertFalse(poly1.equals(poly2));
    assertFalse(poly3.equals(poly4));
  }

  /**
   * Different polynomials of different types.
   */
  @Test
  public void Test67() {
    Polynomial poly1 = this.returnObject();
    Polynomial poly2 = this.returnObjectDiff();
    poly1.addTerm(3, 3);
    poly1.addTerm(2, 2);
    poly2.addTerm(4, 3);
    poly2.addTerm(2, 2);
    Polynomial poly3 = this.returnObjectDiff();
    Polynomial poly4 = this.returnObject();
    poly3.addTerm(3, 3);
    poly3.addTerm(2, 2);
    poly4.addTerm(4, 3);
    poly4.addTerm(2, 2);
    assertFalse(poly1.equals(poly2));
    assertFalse(poly3.equals(poly4));
  }

  /**
   * Adding polynomials of two different types.
   */
  @Test
  public void Test68() {
    Polynomial poly1 = this.returnObject();
    Polynomial poly2 = this.returnObjectDiff();
    poly1.addTerm(3, 3);
    poly1.addTerm(2, 2);
    poly1.addTerm(1, 1);
    poly2.addTerm(4, 5);
    poly2.addTerm(4, 3);
    poly2.addTerm(2, 2);
    poly2.addTerm(3, 0);
    Polynomial poly3 = poly1.add(poly2);
    assertEquals("4x^5+7x^3+4x^2+1x^1+3", poly3.toString());
  }

  /**
   * Adding polynomial to a different type of zero polynomial.
   */
  @Test
  public void Test69() {
    Polynomial poly1 = this.returnObject();
    poly1.addTerm(3, 5);
    poly1.addTerm(2, 3);
    poly1.addTerm(1, 0);

    Polynomial poly2 = this.returnObjectDiff();
    Polynomial poly3 = poly1.add(poly2);

    Polynomial poly4 = this.returnObjectDiff();
    poly4.addTerm(3, 5);
    poly4.addTerm(2, 3);
    poly4.addTerm(1, 0);

    Polynomial poly5 = this.returnObject();
    Polynomial poly6 = poly4.add(poly5);

    assertEquals("3x^5+2x^3+1", poly3.toString());
    assertEquals("3x^5+2x^3+1", poly6.toString());
  }

  /**
   * Multiplying two polynomials of two different types.
   */
  @Test
  public void Test70() {
    Polynomial poly1 = this.returnObject();
    Polynomial poly2 = this.returnObjectDiff();
    poly1.addTerm(3, 3);
    poly1.addTerm(2, 2);
    poly1.addTerm(1, 1);
    poly2.addTerm(4, 5);
    poly2.addTerm(4, 3);
    poly2.addTerm(2, 2);
    poly2.addTerm(3, 0);
    Polynomial poly3 = poly1.multiply(poly2);
    assertEquals("12x^8+8x^7+16x^6+14x^5+8x^4+11x^3+6x^2+3x^1", poly3.toString());
  }


  /**
   * Multiplying polynomial with different type of zero polynomial.
   */
  @Test
  public void Test71() {
    Polynomial poly1 = this.returnObject();
    poly1.addTerm(3, 5);
    poly1.addTerm(2, 3);
    poly1.addTerm(1, 0);

    Polynomial poly2 = this.returnObjectDiff();
    Polynomial poly3 = poly1.multiply(poly2);

    Polynomial poly4 = this.returnObjectDiff();
    poly4.addTerm(3, 5);
    poly4.addTerm(2, 3);
    poly4.addTerm(1, 0);

    Polynomial poly5 = this.returnObject();
    Polynomial poly6 = poly4.multiply(poly5);

    assertEquals("0", poly3.toString());
    assertEquals("0", poly6.toString());
  }


  /**
   * Testing equals method is two objects are equal.
   */
  @Test
  public void Test79() {
    polynomial.addTerm(1, 2);
    Polynomial poly2 = polynomial;
    assertTrue(polynomial.equals(poly2));
  }

  /**
   * Testing polynomials of different types but same terms.
   */
  @Test
  public void Test80() {
    polynomial = this.returnObject();
    Polynomial poly2 = this.returnObjectDiff();
    polynomial.addTerm(1, 2);
    polynomial.addTerm(6, 5);
    poly2.addTerm(1, 2);
    poly2.addTerm(6, 5);
    assertTrue(polynomial.equals(poly2));
  }

  /**
   * Testing polynomials of same degree, both same and different type but different terms.
   */
  @Test
  public void Test81() {
    Polynomial poly1 = this.returnObject();
    Polynomial poly2 = this.returnObject();
    poly1.addTerm(1, 3);
    poly1.addTerm(2, 0);
    poly2.addTerm(3, 3);
    assertFalse(poly1.equals(poly2));

    Polynomial poly3 = this.returnObjectDiff();
    Polynomial poly4 = this.returnObjectDiff();
    poly3.addTerm(1, 3);
    poly3.addTerm(2, 0);
    poly4.addTerm(3, 3);
    assertFalse(poly3.equals(poly4));

    assertFalse(poly1.equals(poly4));
    assertFalse(poly2.equals(poly3));
  }

  /**
   * Testing polynomials of different degree, same and different type but different terms.
   */
  @Test
  public void Test82() {
    Polynomial poly1 = this.returnObject();
    Polynomial poly2 = this.returnObject();
    poly1.addTerm(1, 4);
    poly1.addTerm(2, 0);
    poly2.addTerm(3, 3);
    assertFalse(poly1.equals(poly2));

    Polynomial poly3 = this.returnObjectDiff();
    Polynomial poly4 = this.returnObjectDiff();
    poly3.addTerm(1, 5);
    poly3.addTerm(2, 0);
    poly4.addTerm(3, 3);
    assertFalse(poly3.equals(poly4));

    assertFalse(poly1.equals(poly4));
    assertFalse(poly2.equals(poly3));
  }

  /**
   * Checking the reflexivity, symmetric and transitivity property of equals method.
   */
  @Test
  public void Test83() {
    Polynomial poly1 = this.returnObject();
    poly1.addTerm(3, 4);
    poly1.addTerm(2, 2);
    poly1.addTerm(1, 0);

    assertTrue(poly1.equals(poly1));

    Polynomial poly2 = this.returnObjectDiff();
    poly2.addTerm(3, 4);
    poly2.addTerm(2, 2);
    poly2.addTerm(1, 0);

    assertTrue(poly1.equals(poly2));
    assertTrue(poly2.equals(poly1));

    Polynomial poly3 = this.returnObject();
    poly3.addTerm(3, 4);
    poly3.addTerm(2, 2);
    poly3.addTerm(1, 0);

    assertTrue(poly1.equals(poly2));
    assertTrue(poly2.equals(poly3));
    assertTrue(poly1.equals(poly3));
  }

  /**
   * Checks if we add empty polynomial to an existing polynomial of different type.
   */
  @Test
  public void Test84() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 4);
    Polynomial poly2 = this.returnObjectDiff();
    polynomial = polynomial.add(poly2);
    assertEquals("3x^4-2x^3+1", polynomial.toString());
  }

  /**
   * Adding two polynomials with no common powers of different type.
   */
  @Test
  public void Test85() {
    polynomial.addTerm(5, 2);
    polynomial.addTerm(3, 1);
    Polynomial poly2 = this.returnObjectDiff();
    poly2.addTerm(4, 3);
    poly2.addTerm(7, 0);
    polynomial = polynomial.add(poly2);
    assertEquals("4x^3+5x^2+3x^1+7", polynomial.toString());
  }

  /**
   * Checks if we add two empty polynomial of different types.
   */
  @Test
  public void Test86() {
    Polynomial poly2 = this.returnObjectDiff();
    polynomial = polynomial.add(poly2);
    assertEquals("0", polynomial.toString());
  }

  /**
   * Checks if we add polynomials with same power terms of different types.
   */
  @Test
  public void Test87() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 4);

    Polynomial poly2 = this.returnObject();
    poly2.addTerm(3, 0);
    poly2.addTerm(4, 3);
    poly2.addTerm(5, 4);

    polynomial = polynomial.add(poly2);
    assertEquals("8x^4+2x^3+4", polynomial.toString());
  }

  /**
   * Tests if we add polynomial with at least 1 common term of different types.
   */
  @Test
  public void Test88() {
    polynomial.addTerm(5, 2);
    polynomial.addTerm(3, 1);
    Polynomial poly2 = this.returnObject();
    poly2.addTerm(4, 2);
    poly2.addTerm(7, 0);
    polynomial = polynomial.add(poly2);
    assertEquals("9x^2+3x^1+7", polynomial.toString());
  }

  /**
   * If we just add constant to existing polynomial of different type.
   */
  @Test
  public void Test89() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 2);

    Polynomial poly2 = this.returnObject();
    poly2.addTerm(3, 0);

    polynomial = polynomial.add(poly2);
    assertEquals("-2x^3+3x^2+4", polynomial.toString());
  }

  /**
   * Multiply with empty polynomial of different type.
   */
  @Test
  public void Test90() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 2);
    Polynomial poly2 = this.returnObjectDiff();
    polynomial = polynomial.multiply(poly2);
    assertEquals("0", polynomial.toString());
  }

  /**
   * Multiply with 1 of different type.
   */
  @Test
  public void Test91() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 2);
    Polynomial poly2 = this.returnObjectDiff();
    poly2.addTerm(1, 0);
    polynomial = polynomial.multiply(poly2);
    assertEquals("-2x^3+3x^2+1", polynomial.toString());
  }

  /**
   * Multiply with itself of different type.
   */
  @Test
  public void Test92() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 2);
    polynomial = polynomial.multiply(polynomial);
    assertEquals("4x^6-12x^5+9x^4-4x^3+6x^2+1", polynomial.toString());
  }

  /**
   * Multiply with a constant.
   */
  @Test
  public void Test93() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 2);
    Polynomial poly2 = this.returnObjectDiff();
    poly2.addTerm(3, 0);
    polynomial = polynomial.multiply(poly2);
    assertEquals("-6x^3+9x^2+3", polynomial.toString());
  }

  /**
   * Multiply with a regular polynomial.
   */
  @Test
  public void Test94() {
    polynomial.addTerm(1, 0);
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(3, 2);

    Polynomial poly2 = this.returnObjectDiff();
    poly2.addTerm(3, 0);
    poly2.addTerm(4, 3);
    poly2.addTerm(4, 2);
    poly2.addTerm(5, 4);

    polynomial = polynomial.multiply(poly2);
    assertEquals("-10x^7+7x^6+4x^5+17x^4-2x^3+13x^2+3", polynomial.toString());
  }

  /**
   * When we multiply with empty polynomial of different type.
   */
  @Test
  public void Test96() {
    Polynomial poly2 = this.returnObjectDiff();
    poly2.addTerm(3, 0);
    poly2.addTerm(4, 3);
    poly2.addTerm(4, 2);
    poly2.addTerm(5, 4);

    polynomial = polynomial.multiply(poly2);
    assertEquals("0", polynomial.toString());
  }

  /**
   * Multiplying two empty polynomial of different type.
   */
  @Test
  public void Test97() {
    Polynomial poly2 = this.returnObjectDiff();
    polynomial = polynomial.multiply(poly2);
    assertEquals("0", polynomial.toString());
  }

  /**
   * Check the derivative function after adding polynomial of two different types.
   */
  @Test
  public void Test98() {
    polynomial.addTerm(2, 3);
    polynomial.addTerm(3, 4);
    polynomial.addTerm(4, 5);
    Polynomial poly2 = returnObjectDiff();
    poly2.addTerm(1, 2);
    poly2.addTerm(2, 5);
    poly2.addTerm(0, 4);
    Polynomial derivativePoly = polynomial.add(poly2).derivative();
    assertEquals("30x^4+12x^3+6x^2+2x^1", derivativePoly.toString());
  }

  /**
   * Check the degree after adding polynomial of two different types.
   */
  @Test
  public void Test99() {
    polynomial.addTerm(2, 3);
    polynomial.addTerm(3, 4);
    polynomial.addTerm(4, 5);
    Polynomial poly2 = returnObjectDiff();
    poly2.addTerm(1, 2);
    poly2.addTerm(2, 5);
    poly2.addTerm(0, 4);
    int degree = polynomial.add(poly2).getDegree();
    assertEquals(5, degree);
  }

  /**
   * Check the evaluate function after adding polynomial of two different types.
   */
  @Test
  public void Test100() {
    polynomial.addTerm(2, 3);
    polynomial.addTerm(3, 4);
    polynomial.addTerm(4, 5);
    Polynomial poly2 = returnObjectDiff();
    poly2.addTerm(1, 2);
    poly2.addTerm(2, 5);
    poly2.addTerm(0, 4);
    double answer = polynomial.add(poly2).evaluate(1.2);
    assertEquals(26.0467, answer, 0.0001);
  }

  /**
   * Check the derivative function after multiplying polynomial of two different types.
   */
  @Test
  public void Test101() {
    polynomial.addTerm(2, 3);
    polynomial.addTerm(3, 4);
    polynomial.addTerm(4, 5);
    Polynomial poly2 = returnObjectDiff();
    poly2.addTerm(1, 2);
    poly2.addTerm(2, 5);
    poly2.addTerm(0, 4);
    Polynomial derivativePoly = polynomial.multiply(poly2).derivative();
    assertEquals("80x^9+54x^8+32x^7+28x^6+18x^5+10x^4", derivativePoly.toString());
  }

  /**
   * Check the degree after multiplying polynomial of two different types.
   */
  @Test
  public void Test102() {
    polynomial.addTerm(2, 3);
    polynomial.addTerm(3, 4);
    polynomial.addTerm(4, 5);
    Polynomial poly2 = returnObjectDiff();
    poly2.addTerm(1, 2);
    poly2.addTerm(2, 5);
    poly2.addTerm(0, 4);
    int degree = polynomial.multiply(poly2).getDegree();
    assertEquals(10, degree);
  }

  /**
   * Check the evaluate function multiplying adding polynomial of two different types.
   */
  @Test
  public void Test103() {
    polynomial.addTerm(2, 3);
    polynomial.addTerm(3, 4);
    polynomial.addTerm(4, 5);
    Polynomial poly2 = returnObjectDiff();
    poly2.addTerm(1, 2);
    poly2.addTerm(2, 5);
    poly2.addTerm(0, 4);
    double answer = polynomial.multiply(poly2).evaluate(1.2);
    assertEquals(125.9591, answer, 0.0001);
  }
}
