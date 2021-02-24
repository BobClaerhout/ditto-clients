/*
 * Copyright (c) 2021 Contributors to the Eclipse Foundation
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
package org.eclipse.ditto.client.live.commands.modify;

import javax.annotation.Nonnull;

import org.eclipse.ditto.client.live.commands.base.LiveCommandAnswerBuilder;
import org.eclipse.ditto.client.live.commands.base.LiveCommandResponseFactory;
import org.eclipse.ditto.client.live.commands.base.LiveEventFactory;
import org.eclipse.ditto.signals.commands.things.ThingErrorResponse;
import org.eclipse.ditto.signals.commands.things.modify.DeleteAttribute;
import org.eclipse.ditto.signals.commands.things.modify.DeleteAttributeResponse;
import org.eclipse.ditto.signals.events.things.AttributeDeleted;

/**
 * LiveCommandAnswer builder for producing {@code CommandResponse}s and {@code Event}s for {@link DeleteAttribute}
 * commands.
 *
 * @since 2.0.0
 */
public interface DeleteAttributeLiveCommandAnswerBuilder
        extends
        LiveCommandAnswerBuilder.ModifyCommandResponseStep<DeleteAttributeLiveCommandAnswerBuilder.ResponseFactory,
                DeleteAttributeLiveCommandAnswerBuilder.EventFactory> {

    /**
     * Factory for {@code CommandResponse}s to {@code DeleteAttribute} command.
     */
    interface ResponseFactory extends LiveCommandResponseFactory {

        /**
         * Builds a {@link DeleteAttributeResponse} using the values of the {@code Command}.
         *
         * @return the response.
         */
        @Nonnull
        DeleteAttributeResponse deleted();

        /**
         * Builds a {@link ThingErrorResponse} indicating that the attribute was not accessible.
         *
         * @return the response.
         * @see org.eclipse.ditto.signals.commands.things.exceptions.AttributeNotAccessibleException
         * AttributeNotAccessibleException
         */
        @Nonnull
        ThingErrorResponse attributeNotAccessibleError();

        /**
         * Builds a {@link ThingErrorResponse} indicating that the attribute was not modifiable.
         *
         * @return the response.
         * @see org.eclipse.ditto.signals.commands.things.exceptions.AttributeNotModifiableException
         * AttributeNotModifiableException
         */
        @Nonnull
        ThingErrorResponse attributeNotModifiableError();
    }

    /**
     * Factory for events triggered by {@link DeleteAttribute} command.
     */
    @SuppressWarnings("squid:S1609")
    interface EventFactory extends LiveEventFactory {

        /**
         * Creates a {@link AttributeDeleted} event using the values of the {@code Command}.
         *
         * @return the event.
         */
        @Nonnull
        AttributeDeleted deleted();
    }

}
