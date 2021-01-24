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
  EQUALS(Objects::equals, "Objects.equals(%s, %s)", "=", "=="),
  /**
   * checks if the object not equals.
   */
  NOT_EQUALS((leftObject, rightObject) ->
    !leftObject.equals(rightObject), "!%s.equals(%s)", "!="),
  /**
   * checks if the number is bigger than the other.
   */
  BIGGER(Utilities::isBigger, "Utilities.isBigger(%s, %s)", ">"),
  /**
   * checks if the number is equal or bigger than the other.
   */
  BIGGER_AND_EQUALS(Utilities::isBiggerEquals, "Utilities.isBiggerEquals(%s, %s)", "=>", ">="),
  /**
   * checks if the number is lower than the other.
   */
  LESS(Utilities::isLess, "Utilities.isLess(%s, %s)", "<"),
  /**
   * checks if the number is equal or lower than the other.
   */
  LESS_OR_EQUALS(Utilities::isLessEquals, "Utilities.isLessEquals(%s, %s)", "<=", "=<"),
  /**
   * checks if the object instance of the other.
   */
  INSTANCE_OF(Utilities::instanceOf, "Utilities.instanceOf(%s, %s)", "is", "instance of"),
  /**
   * checks if the object not instance of the other.
   */
  NOT_INSTANCE_OF(Utilities::noInstanceOf, "Utilities.noInstanceOf(%s, %s)",
    "isnt", "isn't", "isnot", "is not", "not instance of"),
  /**
   * nothing.
   */
  NOTHING((leftObject, rightObject) -> false, "");

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
   * the to string.
   */
  @NotNull
  private final String toString;

  /**
   * ctor.
   *
   * @param operators the operators.
   * @param predicate the function.
   */
  Equilibrium(@NotNull final List<String> operators, @NotNull final BiPredicate<Object, Object> predicate,
              @NotNull final String toString) {
    this.operators = operators;
    this.predicate = predicate;
    this.toString = toString;
  }

  /**
   * ctor.
   *
   * @param predicate the function.
   * @param operators the operators.
   */
  Equilibrium(@NotNull final BiPredicate<Object, Object> predicate, @NotNull final String toString,
              @NotNull final String... operators) {
    this(Arrays.asList(operators), predicate, toString);
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
