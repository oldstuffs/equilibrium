/*
 * MIT License
 *
 * Copyright (c) 2020 Hasan Demirtaş
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.portlek.equilibrium;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import org.jetbrains.annotations.NotNull;

/**
 * a class that allows you to use Java operators.
 */
public enum Equilibrium {

  /**
   * checks if the object equals.
   */
  EQUALS(Objects::equals, "=", "=="),
  /**
   * checks if the object not equals.
   */
  NOT_EQUALS((leftObject, rightObject) ->
    !leftObject.equals(rightObject), "!="),
  /**
   * checks if the number is bigger than the other.
   */
  BIGGER((leftObject, rightObject) -> {
    if (!(leftObject instanceof Number) || !(rightObject instanceof Number)) {
      return false;
    }
    final Number left = (Number) leftObject;
    final Number right = (Number) rightObject;
    if (left instanceof Double) {
      return left.doubleValue() > right.doubleValue();
    }
    if (left instanceof Integer) {
      return left.intValue() > right.intValue();
    }
    if (left instanceof Long) {
      return left.longValue() > right.longValue();
    }
    if (left instanceof Float) {
      return left.floatValue() > right.floatValue();
    }
    if (left instanceof Short) {
      return (int) left.shortValue() > (int) right.shortValue();
    }
    if (left instanceof Byte) {
      return (int) left.byteValue() > (int) right.byteValue();
    }
    return false;
  }, ">"),
  /**
   * checks if the number is equal or bigger than the other.
   */
  BIGGER_AND_EQUALS((leftObject, rightObject) -> {
    if (!(leftObject instanceof Number) || !(rightObject instanceof Number)) {
      return false;
    }
    final Number left = (Number) leftObject;
    final Number right = (Number) rightObject;
    if (left instanceof Double) {
      return left.doubleValue() >= right.doubleValue();
    }
    if (left instanceof Integer) {
      return left.intValue() >= right.intValue();
    }
    if (left instanceof Long) {
      return left.longValue() >= right.longValue();
    }
    if (left instanceof Float) {
      return left.floatValue() >= right.floatValue();
    }
    if (left instanceof Short) {
      return (int) left.shortValue() >= (int) right.shortValue();
    }
    if (left instanceof Byte) {
      return (int) left.byteValue() >= (int) right.byteValue();
    }
    return false;
  }, "=>", ">="),
  /**
   * checks if the number is lower than the other.
   */
  LESS((leftObject, rightObject) -> {
    if (!(leftObject instanceof Number) || !(rightObject instanceof Number)) {
      return false;
    }
    final Number left = (Number) leftObject;
    final Number right = (Number) rightObject;
    if (left instanceof Double) {
      return left.doubleValue() < right.doubleValue();
    }
    if (left instanceof Integer) {
      return left.intValue() < right.intValue();
    }
    if (left instanceof Long) {
      return left.longValue() < right.longValue();
    }
    if (left instanceof Float) {
      return left.floatValue() < right.floatValue();
    }
    if (left instanceof Short) {
      return (int) left.shortValue() < (int) right.shortValue();
    }
    if (left instanceof Byte) {
      return (int) left.byteValue() < (int) right.byteValue();
    }
    return false;
  }, "<"),
  /**
   * checks if the number is equal or lower than the other.
   */
  LESS_OR_EQUALS((leftObject, rightObject) -> {
    if (!(leftObject instanceof Number) || !(rightObject instanceof Number)) {
      return false;
    }
    final Number left = (Number) leftObject;
    final Number right = (Number) rightObject;
    if (left instanceof Double) {
      return left.doubleValue() <= right.doubleValue();
    }
    if (left instanceof Integer) {
      return left.intValue() <= right.intValue();
    }
    if (left instanceof Long) {
      return left.longValue() <= right.longValue();
    }
    if (left instanceof Float) {
      return left.floatValue() <= right.floatValue();
    }
    if (left instanceof Short) {
      return (int) left.shortValue() <= (int) right.shortValue();
    }
    if (left instanceof Byte) {
      return (int) left.byteValue() <= (int) right.byteValue();
    }
    return false;
  }, "<=", "=<"),
  /**
   * checks if the object instance of the other.
   */
  INSTANCE_OF((leftObject, rightObject) -> {
    if (!(leftObject instanceof Class<?>) || !(rightObject instanceof Class<?>)) {
      return false;
    }
    return ((Class<?>) rightObject).isAssignableFrom((Class<?>) leftObject);
  }, "is", "instance of"),
  /**
   * checks if the object not instance of the other.
   */
  NOT_INSTANCE_OF((leftObject, rightObject) -> {
    if (!(leftObject instanceof Class<?>) || !(rightObject instanceof Class<?>)) {
      return false;
    }
    return !((Class<?>) rightObject).isAssignableFrom((Class<?>) leftObject);
  }, "isnt", "isn't", "isnot", "is not", "not instance of"),
  /**
   * nothing.
   */
  NOTHING((leftObject, rightObject) -> false);

  /**
   * the operators.
   */
  @NotNull
  private final List<String> operators;

  /**
   * the function.
   */
  @NotNull
  private final BiPredicate<Object, Object> predicate;

  /**
   * ctor.
   *
   * @param operators the operators.
   * @param predicate the function.
   */
  Equilibrium(@NotNull final List<String> operators, @NotNull final BiPredicate<Object, Object> predicate) {
    this.operators = operators;
    this.predicate = predicate;
  }

  /**
   * ctor.
   *
   * @param predicate the function.
   * @param operators the operators.
   */
  Equilibrium(@NotNull final BiPredicate<Object, Object> predicate, @NotNull final String... operators) {
    this(Arrays.asList(operators), predicate);
  }

  /**
   * obtains an {@code this} from the string.
   *
   * @param operator the operator to get.
   *
   * @return an {@code this} object instance.
   */
  @NotNull
  public static Equilibrium fromString(@NotNull final String operator) {
    return Arrays.stream(Equilibrium.values())
      .filter(equilibrium -> equilibrium.getOperators().contains(operator))
      .findFirst()
      .orElse(Equilibrium.NOTHING);
  }

  /**
   * controls the predicate.
   *
   * @param leftObject the left object to control.
   * @param rightObject the right object to control.
   *
   * @return {@code true} if the predicate returns {@code true}.
   */
  public boolean control(@NotNull final Object leftObject, @NotNull final Object rightObject) {
    return this.predicate.test(leftObject, rightObject);
  }

  /**
   * obtains the operators.
   *
   * @return the operator list.
   */
  @NotNull
  public List<String> getOperators() {
    return Collections.unmodifiableList(this.operators);
  }
}
