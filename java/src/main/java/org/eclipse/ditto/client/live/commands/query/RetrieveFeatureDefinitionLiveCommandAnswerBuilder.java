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

import org.eclipse.ditto.client.live.commands.base.LiveCommandAnswerBuilder;
import org.eclipse.ditto.client.live.commands.base.LiveCommandResponseFactory;
import org.eclipse.ditto.model.things.FeatureDefinition;
import org.eclipse.ditto.signals.commands.things.ThingErrorResponse;
import org.eclipse.ditto.signals.commands.things.query.RetrieveFeatureDefinition;
import org.eclipse.ditto.signals.commands.things.query.RetrieveFeatureDefinitionResponse;
import org.eclipse.ditto.utils.jsr305.annotations.AllValuesAreNonnullByDefault;

/**
 * LiveCommandAnswer builder for producing {@code CommandResponse}s for {@link RetrieveFeatureDefinition} commands.
 *
 * @since 2.0.0
 */
public interface RetrieveFeatureDefinitionLiveCommandAnswerBuilder extends
        LiveCommandAnswerBuilder.QueryCommandResponseStep<RetrieveFeatureDefinitionLiveCommandAnswerBuilder.ResponseFactory> {

    /**
     * Factory for {@code CommandResponse}s to {@link RetrieveFeatureDefinition} command.
     */
    @AllValuesAreNonnullByDefault
    interface ResponseFactory extends LiveCommandResponseFactory {

        /**
         * Creates a {@link RetrieveFeatureDefinitionResponse} containing the retrieved value for the {@link
         * RetrieveFeatureDefinition} command.
         *
         * @param featureDefinition the value of the requested Feature Definition
         * @return a response containing the requested value
         * @throws NullPointerException if {@code featureDefinition} is {@code null}
         */
        RetrieveFeatureDefinitionResponse retrieved(FeatureDefinition featureDefinition);

        /**
         * Creates a {@link ThingErrorResponse} specifying that no Feature Definition exist or the requesting user does
         * not have enough permission to retrieve it.
         *
         * @return the error response
         */
        ThingErrorResponse featureDefinitionNotAccessibleError();

    }

}
