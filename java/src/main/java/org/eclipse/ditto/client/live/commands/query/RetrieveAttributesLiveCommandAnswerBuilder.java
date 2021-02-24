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

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.eclipse.ditto.client.live.commands.base.LiveCommandAnswerBuilder;
import org.eclipse.ditto.client.live.commands.base.LiveCommandResponseFactory;
import org.eclipse.ditto.model.things.Attributes;
import org.eclipse.ditto.signals.commands.things.ThingErrorResponse;
import org.eclipse.ditto.signals.commands.things.query.RetrieveAttributes;
import org.eclipse.ditto.signals.commands.things.query.RetrieveAttributesResponse;

/**
 * LiveCommandAnswer builder for producing {@code CommandResponse}s for {@link RetrieveAttributes} commands.
 *
 * @since 2.0.0
 */
public interface RetrieveAttributesLiveCommandAnswerBuilder extends
        LiveCommandAnswerBuilder.QueryCommandResponseStep<RetrieveAttributesLiveCommandAnswerBuilder.ResponseFactory> {

    /**
     * Factory for {@code CommandResponse}s to {@link RetrieveAttributes} command.
     */
    @ParametersAreNonnullByDefault
    interface ResponseFactory extends LiveCommandResponseFactory {

        /**
         * Creates a {@link RetrieveAttributesResponse} containing the retrieved value for the {@link
         * RetrieveAttributes} command.
         *
         * @param attributes the value of the attributes.
         * @return the response.
         * @throws NullPointerException if {@code attributes} is {@code null}.
         */
        @Nonnull
        RetrieveAttributesResponse retrieved(Attributes attributes);

        /**
         * Creates a {@link ThingErrorResponse} specifying that no attribute exists or the requesting user does not have
         * enough permission to retrieve it.
         *
         * @return the response.
         */
        @Nonnull
        ThingErrorResponse attributesNotAccessibleError();
    }

}
