package io.github.portlek.equilibrium;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsTrue;

final class EquilibriumTest {

  @Test
  void control() {
    final var equals = Equilibrium.fromString("==");
    final var notEquals = Equilibrium.fromString("!=");
    final var bigger = Equilibrium.fromString(">");
    final var biggerOrEquals = Equilibrium.fromString(">=");
    final var less = Equilibrium.fromString("<");
    final var lessOfEquals = Equilibrium.fromString("<=");
    final var instanceOf = Equilibrium.fromString("is");
    final var notInstanceOf = Equilibrium.fromString("is not");
    final var nothing = Equilibrium.fromString("asd");
    new Assertion<>(
      "Objects are not equal!",
      equals.control("test", "test"),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Objects are equal!",
      notEquals.control("test", "test-2"),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Left is not bigger than right!",
      bigger.control(1, 0),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Left is not bigger than right!",
      bigger.control(1.1d, 1.0d),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Left is not bigger or equals to the right number!",
      biggerOrEquals.control(1, 0),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Left is not bigger or equals to the right number!",
      biggerOrEquals.control(1.1d, 1.0d),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Left is not less than right!",
      less.control(0, 1),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Left is not less than right!",
      less.control(1.0d, 1.1d),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Left is not less or equals to the right number!",
      lessOfEquals.control(0, 1),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Left is not less or equals to the right number!",
      lessOfEquals.control(1.0d, 1.1d),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Right is not assignable from left object!",
      instanceOf.control(EquilibriumTest.TestImpl.class, EquilibriumTest.ITest.class),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Right is assignable from left object!",
      notInstanceOf.control(EquilibriumTest.AnotherTestImpl.class, EquilibriumTest.ITest.class),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Just nothing!",
      nothing.control("test", "test"),
      new IsNot<>(new IsTrue())
    ).affirm();
  }

  @Test
  void fromString() {
    final var equals = Equilibrium.fromString("==");
    final var notEquals = Equilibrium.fromString("!=");
    final var bigger = Equilibrium.fromString(">");
    final var biggerOrEquals = Equilibrium.fromString(">=");
    final var less = Equilibrium.fromString("<");
    final var lessOfEquals = Equilibrium.fromString("<=");
    final var instanceOf = Equilibrium.fromString("is");
    final var notInstanceOf = Equilibrium.fromString("is not");
    final var nothing = Equilibrium.fromString("asd");
    new Assertion<>(
      "Couldn't parse as equals!",
      equals,
      new IsEqual<>(Equilibrium.EQUALS)
    ).affirm();
    new Assertion<>(
      "Couldn't parse as notEquals!",
      notEquals,
      new IsEqual<>(Equilibrium.NOT_EQUALS)
    ).affirm();
    new Assertion<>(
      "Couldn't parse as bigger!",
      bigger,
      new IsEqual<>(Equilibrium.BIGGER)
    ).affirm();
    new Assertion<>(
      "Couldn't parse as biggerOrEquals!",
      biggerOrEquals,
      new IsEqual<>(Equilibrium.BIGGER_AND_EQUALS)
    ).affirm();
    new Assertion<>(
      "Couldn't parse as less!",
      less,
      new IsEqual<>(Equilibrium.LESS)
    ).affirm();
    new Assertion<>(
      "Couldn't parse as lessOfEquals!",
      lessOfEquals,
      new IsEqual<>(Equilibrium.LESS_OR_EQUALS)
    ).affirm();
    new Assertion<>(
      "Couldn't parse as instanceOf!",
      instanceOf,
      new IsEqual<>(Equilibrium.INSTANCE_OF)
    ).affirm();
    new Assertion<>(
      "Couldn't parse as notInstanceOf!",
      notInstanceOf,
      new IsEqual<>(Equilibrium.NOT_INSTANCE_OF)
    ).affirm();
    new Assertion<>(
      "Couldn't parse as nothing!",
      nothing,
      new IsEqual<>(Equilibrium.NOTHING)
    ).affirm();
  }

  private interface AnotherITest {

  }

  private interface ITest {

  }

  private static final class AnotherTestImpl implements EquilibriumTest.AnotherITest {

  }

  private static final class TestImpl implements EquilibriumTest.ITest {

  }
}
