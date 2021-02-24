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

import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.eclipse.ditto.client.live.commands.base.LiveCommandAnswerBuilder;
import org.eclipse.ditto.client.live.commands.base.LiveCommandResponseFactory;
import org.eclipse.ditto.json.JsonField;
import org.eclipse.ditto.model.things.Thing;
import org.eclipse.ditto.signals.commands.things.query.RetrieveThings;
import org.eclipse.ditto.signals.commands.things.query.RetrieveThingsResponse;

/**
 * LiveCommandAnswer builder for producing {@code CommandResponse}s for {@link RetrieveThings} commands.
 *
 * @since 2.0.0
 */
public interface RetrieveThingsLiveCommandAnswerBuilder extends
        LiveCommandAnswerBuilder.QueryCommandResponseStep<RetrieveThingsLiveCommandAnswerBuilder.ResponseFactory> {

    /**
     * Factory for {@code CommandResponse}s to {@link RetrieveThings} command.
     */
    @ParametersAreNonnullByDefault
    interface ResponseFactory extends LiveCommandResponseFactory {

        /**
         * Creates a success response containing the retrieved value for the {@link RetrieveThings} command.
         *
         * @param things the value of the requested Things.
         * @param predicate a predicate determining which fields from the provided Things should be included in the
         * response.
         * @return the response.
         * @throws NullPointerException if any argument is {@code null}.
         */
        @Nonnull
        RetrieveThingsResponse retrieved(List<Thing> things, Predicate<JsonField> predicate);

        /**
         * Creates a success response containing the retrieved value for the {@link RetrieveThings} command.
         *
         * @param things the value of the requested Things.
         * @return the response.
         * @throws NullPointerException if {@code things} is {@code null}.
         */
        @Nonnull
        RetrieveThingsResponse retrieved(List<Thing> things);
    }

}
