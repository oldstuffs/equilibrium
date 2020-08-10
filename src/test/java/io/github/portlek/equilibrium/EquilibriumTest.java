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

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.IsTrue;

class EquilibriumTest {

    @Test
    void fromString() {
        final Equilibrium equals = Equilibrium.fromString("==");
        final Equilibrium notEquals = Equilibrium.fromString("!=");
        final Equilibrium bigger = Equilibrium.fromString(">");
        final Equilibrium biggerOrEquals = Equilibrium.fromString(">=");
        final Equilibrium less = Equilibrium.fromString("<");
        final Equilibrium lessOfEquals = Equilibrium.fromString("<=");
        final Equilibrium instanceOf = Equilibrium.fromString("is");
        final Equilibrium notInstanceOf = Equilibrium.fromString("is not");
        final Equilibrium nothing = Equilibrium.fromString("asd");

        MatcherAssert.assertThat(
            "Couldn't parse as equals!",
            equals,
            new IsEqual<>(Equilibrium.EQUALS)
        );
        MatcherAssert.assertThat(
            "Couldn't parse as notEquals!",
            notEquals,
            new IsEqual<>(Equilibrium.NOT_EQUALS)
        );
        MatcherAssert.assertThat(
            "Couldn't parse as bigger!",
            bigger,
            new IsEqual<>(Equilibrium.BIGGER)
        );
        MatcherAssert.assertThat(
            "Couldn't parse as biggerOrEquals!",
            biggerOrEquals,
            new IsEqual<>(Equilibrium.BIGGER_AND_EQUALS)
        );
        MatcherAssert.assertThat(
            "Couldn't parse as less!",
            less,
            new IsEqual<>(Equilibrium.LESS)
        );
        MatcherAssert.assertThat(
            "Couldn't parse as lessOfEquals!",
            lessOfEquals,
            new IsEqual<>(Equilibrium.LESS_OR_EQUALS)
        );
        MatcherAssert.assertThat(
            "Couldn't parse as instanceOf!",
            instanceOf,
            new IsEqual<>(Equilibrium.INSTANCE_OF)
        );
        MatcherAssert.assertThat(
            "Couldn't parse as notInstanceOf!",
            notInstanceOf,
            new IsEqual<>(Equilibrium.NOT_INSTANCE_OF)
        );
        MatcherAssert.assertThat(
            "Couldn't parse as nothing!",
            nothing,
            new IsEqual<>(Equilibrium.NOTHING)
        );
    }

    @Test
    void control() {
        final Equilibrium equals = Equilibrium.fromString("==");
        final Equilibrium notEquals = Equilibrium.fromString("!=");
        final Equilibrium bigger = Equilibrium.fromString(">");
        final Equilibrium biggerOrEquals = Equilibrium.fromString(">=");
        final Equilibrium less = Equilibrium.fromString("<");
        final Equilibrium lessOfEquals = Equilibrium.fromString("<=");
        final Equilibrium instanceOf = Equilibrium.fromString("is");
        final Equilibrium notInstanceOf = Equilibrium.fromString("is not");
        final Equilibrium nothing = Equilibrium.fromString("asd");

        MatcherAssert.assertThat(
            "Objects are not equal!",
            equals.control("test", "test"),
            new IsTrue()
        );
        MatcherAssert.assertThat(
            "Objects are equal!",
            notEquals.control("test", "test-2"),
            new IsTrue()
        );
        MatcherAssert.assertThat(
            "Left is not bigger than right!",
            bigger.control(1, 0),
            new IsTrue()
        );
        MatcherAssert.assertThat(
            "Left is not bigger than right!",
            bigger.control(1.1d, 1.0d),
            new IsTrue()
        );
        MatcherAssert.assertThat(
            "Left is not bigger or equals to the right number!",
            biggerOrEquals.control(1, 0),
            new IsTrue()
        );
        MatcherAssert.assertThat(
            "Left is not bigger or equals to the right number!",
            biggerOrEquals.control(1.1d, 1.0d),
            new IsTrue()
        );
        MatcherAssert.assertThat(
            "Left is not less than right!",
            less.control(0, 1),
            new IsTrue()
        );
        MatcherAssert.assertThat(
            "Left is not less than right!",
            less.control(1.0d, 1.1d),
            new IsTrue()
        );
        MatcherAssert.assertThat(
            "Left is not less or equals to the right number!",
            lessOfEquals.control(0, 1),
            new IsTrue()
        );
        MatcherAssert.assertThat(
            "Left is not less or equals to the right number!",
            lessOfEquals.control(1.0d, 1.1d),
            new IsTrue()
        );
        MatcherAssert.assertThat(
            "Right is not assignable from left object!",
            instanceOf.control(EquilibriumTest.TestImpl.class, EquilibriumTest.ITest.class),
            new IsTrue()
        );
        MatcherAssert.assertThat(
            "Right is assignable from left object!",
            notInstanceOf.control(EquilibriumTest.AnotherTestImpl.class, EquilibriumTest.ITest.class),
            new IsTrue()
        );
        MatcherAssert.assertThat(
            "Just nothing!",
            nothing.control("test", "test"),
            new IsNot<>(new IsTrue())
        );
    }

    private interface ITest {

    }

    private interface AnotherITest {

    }

    private static final class TestImpl implements EquilibriumTest.ITest {

    }

    private static final class AnotherTestImpl implements EquilibriumTest.AnotherITest {

    }

}
