package polynomial;

import java.util.ArrayList;

public class SimplePolynomial extends AbstractPolynomial {

  private final ArrayList<Integer> polynomial;

  private void trimLeadingZeros() {
    int i = polynomial.size() - 1;
    while (i >= 0 && polynomial.get(i) == 0) {
      polynomial.remove(i);
      i--;
    }
  }

  public SimplePolynomial() {
    polynomial = new ArrayList<>();
  }

  private boolean compareWithSimple(SimplePolynomial other) {
    if (this.polynomial.size() != other.polynomial.size()) {
      return false;
    }
    for (int power = 0; power < this.polynomial.size(); power++) {
      if (this.polynomial.get(power) != other.polynomial.get(power)) {
        return false;
      }
    }
    return true;
  }

  @Override
  protected boolean compareTerms(Polynomial other) {
    if (other instanceof SparsePolynomial) {
      return other.equals(this);
    } else if (other instanceof SimplePolynomial) {
      return this.compareWithSimple((SimplePolynomial) other);
    }
    return false;
  }

  @Override
  public Polynomial add(Polynomial other) {
    SimplePolynomial otherPolynomial = (SimplePolynomial) other;
    Polynomial additionPolynomial = new SimplePolynomial();
    for (int thisPower = 0; thisPower < this.polynomial.size(); thisPower++) {
      additionPolynomial.addTerm(this.polynomial.get(thisPower), thisPower);
    }
    for (int otherPower = 0; otherPower < otherPolynomial.polynomial.size(); otherPower++) {
      additionPolynomial.addTerm(otherPolynomial.polynomial.get(otherPower), otherPower);
    }
    return additionPolynomial;
  }

  @Override
  public Polynomial multiply(Polynomial other) {
    SimplePolynomial otherPolynomial = (SimplePolynomial) other;
    Polynomial multiplyPolynomial = new SimplePolynomial();
    for (int thisPower = 0; thisPower < this.polynomial.size(); thisPower++) {
      for (int otherPower = 0; otherPower < otherPolynomial.polynomial.size(); otherPower++) {
        int newCoefficient = this.polynomial.get(thisPower) * otherPolynomial.polynomial.get(otherPower);
        int newPower = thisPower + otherPower;
        multiplyPolynomial.addTerm(newCoefficient, newPower);
      }
    }
    return multiplyPolynomial;
  }

  @Override
  public Polynomial derivative() {
    Polynomial derivativePolynomial = new SimplePolynomial();
    for (int power = 1; power < this.polynomial.size(); power++) {
      int newCoefficient = this.polynomial.get(power) * power;
      int newPower = power - 1;
      derivativePolynomial.addTerm(newCoefficient, newPower);
    }
    return derivativePolynomial;
  }

  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Power cannot be negative.");
    }
    if (coefficient == 0) return;
    while (power >= polynomial.size()) {
      polynomial.add(0);
    }
    polynomial.set(power, polynomial.get(power) + coefficient);
    trimLeadingZeros();
  }

  @Override
  public int getDegree() {
    return Math.max(0, polynomial.size() - 1);
  }

  @Override
  public double evaluate(double x) {
    double ans = 0;
    for (int power = 0; power < polynomial.size(); power++) {
      int coefficient = polynomial.get(power);
      ans += coefficient * Math.pow(x, power);
    }
    return ans;
  }

  @Override
  public int getCoefficient(int power) {
    if (power < 0) return 0;
    if (power >= polynomial.size()) return 0;
    return polynomial.get(power);
  }

  @Override
  public String toString() {
    StringBuilder stringPolynomial = new StringBuilder();
    if (this.polynomial.isEmpty()) {
      return "0";
    }
    for (int power = this.polynomial.size() - 1; power >= 0; power--) {
      int coefficient = this.polynomial.get(power);
      if (coefficient != 0) {
        if (stringPolynomial.length() == 0) {
          stringPolynomial.append(convertString(coefficient, power, 1));
        } else {
          stringPolynomial.append(convertString(coefficient, power, 0));
        }
      }
    }
    return stringPolynomial.toString();
  }


}
