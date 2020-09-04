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
import java.util.function.BiFunction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public enum Equilibrium {

    EQUALS(Objects::equals, "=", "=="),
    NOT_EQUALS((leftObject, rightObject) ->
        !leftObject.equals(rightObject), "!="),
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
    INSTANCE_OF((leftObject, rightObject) -> {
        if (!(leftObject instanceof Class<?>) || !(rightObject instanceof Class<?>)) {
            return false;
        }
        return ((Class<?>) rightObject).isAssignableFrom((Class<?>) leftObject);
    }, "is", "instance of"),
    NOT_INSTANCE_OF((leftObject, rightObject) -> {
        if (!(leftObject instanceof Class<?>) || !(rightObject instanceof Class<?>)) {
            return false;
        }
        return !((Class<?>) rightObject).isAssignableFrom((Class<?>) leftObject);
    }, "isnt", "isn't", "isnot", "is not", "not instance of"),
    NOTHING((leftObject, rightObject) -> false);

    @NotNull
    private final List<String> operators;

    @NotNull
    private final BiFunction<Object, Object, Boolean> func;

    Equilibrium(final BiFunction<Object, Object, Boolean> func, @NotNull final String... operators) {
        this(Arrays.asList(operators), func);
    }

    @NotNull
    public static Equilibrium fromString(@NotNull final String operator) {
        return Arrays.stream(Equilibrium.values())
            .filter(equilibrium -> equilibrium.getOperators().contains(operator))
            .findFirst()
            .orElse(Equilibrium.NOTHING);
    }

    public boolean control(@NotNull final Object leftObject, @NotNull final Object rightObject) {
        return this.func.apply(leftObject, rightObject);
    }

    @NotNull
    public List<String> getOperators() {
        return Collections.unmodifiableList(this.operators);
    }

}
