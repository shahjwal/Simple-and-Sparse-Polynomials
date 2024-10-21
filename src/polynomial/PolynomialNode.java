package polynomial;

/**
 * The PolynomialNode class represents a single term in a sparse polynomial.
 * Each node stores a coefficient, a power, and a reference to the next
 * node in the linked list. This class provides methods to get and set the coefficient,
 * get the power, and manage the pointer to the next node in the polynomial chain.
 */
class PolynomialNode {

  private int coefficient;
  private final int power;
  private PolynomialNode next;

  /**
   * Constructs a new PolynomialNode with the given coefficient and power.
   * Initializes the next node pointer to null.
   *
   * @param coefficient the coefficient of the term.
   * @param power       the power (exponent) of the term.
   */
  public PolynomialNode(int coefficient, int power) {
    this.coefficient = coefficient;
    this.power = power;
    this.next = null;
  }

  /**
   * Returns the coefficient of this polynomial term.
   * This is useful when performing operations on the polynomial,
   * such as addition or multiplication.
   *
   * @return the coefficient of the current node.
   */
  public int getCoefficient() {
    return coefficient;
  }

  /**
   * Updates the coefficient of this polynomial term to the given value.
   * This is useful when modifying the polynomial, such as adding terms or adjusting coefficients.
   *
   * @param coefficient the new coefficient for this node.
   */
  public void setCoefficient(int coefficient) {
    this.coefficient = coefficient;
  }

  /**
   * Returns the power of this polynomial term.
   * The power indicates the degree of the term (e.g., x^power).
   *
   * @return the power (exponent) of the current node.
   */
  public int getPower() {
    return power;
  }

  /**
   * Returns the next node in the linked list of terms.
   * This allows traversal through the polynomial's terms.
   *
   * @return the next PolynomialNode in the chain, or null if this is the last node.
   */
  public PolynomialNode getNext() {
    return next;
  }

  /**
   * Sets the reference to the next node in the linked list of terms.
   * This allows linking polynomial terms together in the sparse polynomial.
   *
   * @param next the PolynomialNode to be set as the next node in the chain.
   */
  public void setNext(PolynomialNode next) {
    this.next = next;
  }
}
