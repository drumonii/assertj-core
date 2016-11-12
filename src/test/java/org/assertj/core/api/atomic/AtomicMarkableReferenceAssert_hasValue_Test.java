/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.core.api.atomic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.error.AtomicShouldContain.shouldContain;
import static org.assertj.core.error.ShouldBeMarked.shouldBeMarked;
import static org.assertj.core.error.ShouldBeMarked.shouldNotBeMarked;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import java.util.concurrent.atomic.AtomicMarkableReference;

import org.assertj.core.test.ExpectedException;
import org.junit.Rule;
import org.junit.Test;

public class AtomicMarkableReferenceAssert_hasValue_Test {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private String expectedValue = "expectedValue";

  @Test
  public void should_fail_when_atomicMarcableReference_is_null() throws Exception {
    thrown.expectAssertionError(actualIsNull());

    assertThat((AtomicMarkableReference<String>) null).hasValue(expectedValue);
  }

  @Test
  public void should_fail_if_expected_value_is_null_and_does_not_contain_expected_value() throws Exception {
    AtomicMarkableReference<String> actual = new AtomicMarkableReference<>("actual", true);
    thrown.expectAssertionError(shouldContain(actual, null).create());

    assertThat(actual).hasValue(null);
  }

  @Test
  public void should_fail_if_atomicMarkableReference_does_not_contain_expected_value() throws Exception {
    AtomicMarkableReference<String> actual = new AtomicMarkableReference<>("actual", true);

    thrown.expectAssertionError(shouldContain(actual, expectedValue).create());

    assertThat(actual).hasValue(expectedValue);
  }

  @Test
  public void should_pass_if_atomicMarkableReference_contains_expected_value() throws Exception {
    assertThat(new AtomicMarkableReference<>(expectedValue, true)).hasValue(expectedValue);
    assertThat(new AtomicMarkableReference<>(expectedValue, true)).hasValue(expectedValue);
  }

  @Test
  public void should_pass_if_atomicMarkableReference_contains_expected_value_and_is_marked() throws Exception {
    assertThat(new AtomicMarkableReference<>(expectedValue, true)).hasValue(expectedValue).isMarked();
  }

  @Test
  public void should_pass_if_atomicMarkableReference_contains_expected_value_and_is_not_marked() throws Exception {
    assertThat(new AtomicMarkableReference<>(expectedValue, false)).hasValue(expectedValue).isNotMarked();
  }

  @Test
  public void should_fail_if_atomicMarkableReference_contains_expected_value_and_is_not_marked() throws Exception {
    AtomicMarkableReference<String> actual = new AtomicMarkableReference<>(expectedValue, false);
    thrown.expectAssertionError(shouldBeMarked(actual).create());
    assertThat(actual).hasValue(expectedValue).isMarked();
  }

  @Test
  public void should_fail_if_atomicMarkableReference_contains_expected_value_and_is_marked() throws Exception {
    AtomicMarkableReference<String> actual = new AtomicMarkableReference<>(expectedValue, true);
    thrown.expectAssertionError(shouldNotBeMarked(actual).create());
    assertThat(actual).hasValue(expectedValue).isNotMarked().isMarked();
  }
}