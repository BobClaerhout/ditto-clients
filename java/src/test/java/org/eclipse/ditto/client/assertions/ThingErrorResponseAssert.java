/*
 * Copyright (c) 2019 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.ditto.client.assertions;

import org.assertj.core.api.Assertions;
import org.eclipse.ditto.model.base.common.HttpStatus;
import org.eclipse.ditto.model.base.exceptions.DittoRuntimeException;
import org.eclipse.ditto.model.base.headers.DittoHeaders;
import org.eclipse.ditto.signals.commands.base.assertions.AbstractCommandResponseAssert;
import org.eclipse.ditto.signals.commands.things.ThingErrorResponse;

/**
 * An assert for {@link ThingErrorResponse}.
 */
public final class ThingErrorResponseAssert
        extends AbstractCommandResponseAssert<ThingErrorResponseAssert, ThingErrorResponse> {

    public ThingErrorResponseAssert(final ThingErrorResponse actual) {
        super(actual, ThingErrorResponseAssert.class);
    }

    public ThingErrorResponseAssert withType(final CharSequence expectedType) {
        return hasType(expectedType);
    }

    public ThingErrorResponseAssert withDittoHeaders(final DittoHeaders expectedDittoHeaders) {
        return hasDittoHeaders(expectedDittoHeaders);
    }

    public ThingErrorResponseAssert withStatus(final HttpStatus expectedStatus) {
        return hasStatus(expectedStatus);
    }

    public ThingErrorResponseAssert hasStatus(final HttpStatus expectedStatus) {
        return assertThatEquals(actual.getHttpStatus(), expectedStatus, "HTTP status");
    }

    public ThingErrorResponseAssert withDittoRuntimeException(
            final DittoRuntimeException expectedDittoRuntimeException) {

        return assertThatEquals(actual.getDittoRuntimeException(), expectedDittoRuntimeException,
                "DittoRuntimeException");
    }

    public ThingErrorResponseAssert withDittoRuntimeExceptionOfType(
            final Class<? extends DittoRuntimeException> expectedType) {

        isNotNull();
        final DittoRuntimeException dittoRuntimeException = actual.getDittoRuntimeException();
        Assertions.assertThat(dittoRuntimeException)
                .overridingErrorMessage(
                        "Expected DittoRuntimeException of ThingErrorResponse is of type\n<%s> but it " +
                                "was\n<%s>", expectedType.getSimpleName(),
                        dittoRuntimeException.getClass().getSimpleName())
                .isInstanceOf(expectedType);
        return myself;
    }

    private <T> ThingErrorResponseAssert assertThatEquals(final T actual, final T expected, final String propertyName) {
        isNotNull();
        Assertions.assertThat(actual)
                .overridingErrorMessage("Expected ThingErrorResponse to have %s " + "\n<%s> but it had\n<%s>",
                        propertyName, expected, actual)
                .isEqualTo(expected);
        return this;
    }

}
