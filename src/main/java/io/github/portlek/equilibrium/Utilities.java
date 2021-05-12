/*
 * MIT License
 *
 * Copyright (c) 2021 Hasan Demirtaş
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

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * a class that contains utility methods.
 */
public final class Utilities {

  /**
   * ctor.
   */
  private Utilities() {
  }

  /**
   * checks if the given {@code leftObject} is instance of {@code rightObject}.
   *
   * @param leftObject the left object to check.
   * @param rightObject the right object to check.
   *
   * @return {@code true} if the left object is instance of {@code rightObject}.
   */
  public static boolean instanceOf(@NotNull final Object leftObject, @NotNull final Object rightObject) {
    if (!(leftObject instanceof Class<?>) || !(rightObject instanceof Class<?>)) {
      return false;
    }
    return ((Class<?>) rightObject).isAssignableFrom((Class<?>) leftObject);
  }

  /**
   * checks if the given {@code leftObject} is not instance of {@code rightObject}.
   *
   * @param leftObject the left object to check.
   * @param rightObject the right object to check.
   *
   * @return {@code true} if the left object is not instance of {@code rightObject}.
   */
  public static boolean noInstanceOf(@NotNull final Object leftObject, @NotNull final Object rightObject) {
    return !Utilities.instanceOf(leftObject, rightObject);
  }

  /**
   * checks if the given {@code leftObject} is bigger than {@code rightObject}.
   *
   * @param leftObject the left object to check.
   * @param rightObject the right object to check.
   *
   * @return {@code true} if the left object is bigger than the right object.
   */
  public static boolean isBigger(@NotNull final Object leftObject, @NotNull final Object rightObject) {
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
  }

  /**
   * checks if the given {@code leftObject} is bigger equals than {@code rightObject}.
   *
   * @param leftObject the left object to check.
   * @param rightObject the right object to check.
   *
   * @return {@code true} if the left object is bigger equals than the right object.
   */
  public static boolean isBiggerEquals(@NotNull final Object leftObject, @NotNull final Object rightObject) {
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
  }

  /**
   * checks if the given {@code leftObject} is less than {@code rightObject}.
   *
   * @param leftObject the left object to check.
   * @param rightObject the right object to check.
   *
   * @return {@code true} if the left object is less than the right object.
   */
  public static boolean isLess(@NotNull final Object leftObject, @NotNull final Object rightObject) {
    return !Utilities.isBiggerEquals(leftObject, rightObject);
  }

  /**
   * checks if the given {@code leftObject} is less equals than {@code rightObject}.
   *
   * @param leftObject the left object to check.
   * @param rightObject the right object to check.
   *
   * @return {@code true} if the left object is less equals than the right object.
   */
  public static boolean isLessEquals(@NotNull final Object leftObject, @NotNull final Object rightObject) {
    return !Utilities.isBigger(leftObject, rightObject);
  }

  /**
   * checks if the given {@code leftObject} equals {@code rightObject}.
   *
   * @param leftObject the left object to check.
   * @param rightObject the right object to check.
   *
   * @return {@code true} if the left object equals {@code rightObject}.
   */
  public static boolean equals(@NotNull final Object leftObject, @NotNull final Object rightObject) {
    return Objects.equals(leftObject, rightObject);
  }

  /**
   * checks if the given {@code leftObject} not equals {@code rightObject}.
   *
   * @param leftObject the left object to check.
   * @param rightObject the right object to check.
   *
   * @return {@code true} if the left object not equals right object.
   */
  public static boolean notEqual(@NotNull final Object leftObject, @NotNull final Object rightObject) {
    return !Utilities.equals(leftObject, rightObject);
  }
}
