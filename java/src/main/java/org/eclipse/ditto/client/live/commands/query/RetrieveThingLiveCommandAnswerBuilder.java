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
package org.eclipse.ditto.client.live.commands.query;

import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.eclipse.ditto.client.live.commands.base.LiveCommandAnswerBuilder;
import org.eclipse.ditto.client.live.commands.base.LiveCommandResponseFactory;
import org.eclipse.ditto.json.JsonField;
import org.eclipse.ditto.model.things.Thing;
import org.eclipse.ditto.signals.commands.things.ThingErrorResponse;
import org.eclipse.ditto.signals.commands.things.query.RetrieveThing;
import org.eclipse.ditto.signals.commands.things.query.RetrieveThingResponse;

/**
 * LiveCommandAnswer builder for producing {@code CommandResponse}s for {@link RetrieveThing} commands.
 *
 * @since 2.0.0
 */
public interface RetrieveThingLiveCommandAnswerBuilder extends
        LiveCommandAnswerBuilder.QueryCommandResponseStep<RetrieveThingLiveCommandAnswerBuilder.ResponseFactory> {

    /**
     * Factory for {@code CommandResponse}s to {@link RetrieveThing} command.
     */
    @ParametersAreNonnullByDefault
    interface ResponseFactory extends LiveCommandResponseFactory {

        /**
         * Creates a success response containing the retrieved value for the {@link RetrieveThing} command.
         *
         * @param thing the value of the requested Thing.
         * @param predicate a predicate determining which fields from the provided Thing should be included in the
         * response.
         * @return the response.
         * @throws NullPointerException if any argument is {@code null}.
         */
        @Nonnull
        RetrieveThingResponse retrieved(Thing thing, Predicate<JsonField> predicate);

        /**
         * Creates a success response containing the retrieved value for the {@link RetrieveThing} command.
         *
         * @param thing the value of the requested Thing.
         * @return the response.
         * @throws NullPointerException if {@code thing} is {@code null}.
         */
        @Nonnull
        RetrieveThingResponse retrieved(Thing thing);

        /**
         * Creates an error response specifying that the requested Thing does not exist or the requesting user does not
         * have enough permission to retrieve it.
         *
         * @return the response.
         */
        @Nonnull
        ThingErrorResponse thingNotAccessibleError();
    }

}
