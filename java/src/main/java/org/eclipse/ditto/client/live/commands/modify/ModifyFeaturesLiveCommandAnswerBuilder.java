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
import org.eclipse.ditto.signals.commands.things.modify.ModifyFeatures;
import org.eclipse.ditto.signals.commands.things.modify.ModifyFeaturesResponse;
import org.eclipse.ditto.signals.events.things.FeaturesCreated;
import org.eclipse.ditto.signals.events.things.FeaturesModified;

/**
 * LiveCommandAnswer builder for producing {@code CommandResponse}s and {@code Event}s for {@link ModifyFeatures}
 * commands.
 *
 * @since 2.0.0
 */
public interface ModifyFeaturesLiveCommandAnswerBuilder extends
        LiveCommandAnswerBuilder.ModifyCommandResponseStep<ModifyFeaturesLiveCommandAnswerBuilder.ResponseFactory,
                ModifyFeaturesLiveCommandAnswerBuilder.EventFactory> {

    /**
     * Factory for {@code CommandResponse}s to {@link ModifyFeatures} command.
     */
    interface ResponseFactory extends LiveCommandResponseFactory {

        /**
         * Builds a "created"  {@link ModifyFeaturesResponse} using the values of the {@code Command}.
         *
         * @return the response.
         */
        @Nonnull
        ModifyFeaturesResponse created();

        /**
         * Builds a "modified"  {@link ModifyFeaturesResponse} using the values of the {@code Command}.
         *
         * @return the response.
         */
        @Nonnull
        ModifyFeaturesResponse modified();

        /**
         * Builds a {@link ThingErrorResponse} indicating that the features were not accessible.
         *
         * @return the response.
         * @see org.eclipse.ditto.signals.commands.things.exceptions.FeaturesNotAccessibleException
         * FeaturesNotAccessibleException
         */
        @Nonnull
        ThingErrorResponse featuresNotAccessibleError();

        /**
         * Builds a {@link ThingErrorResponse} indicating that the features were not modifiable.
         *
         * @return the response.
         * @see org.eclipse.ditto.signals.commands.things.exceptions.FeaturesNotModifiableException
         * FeaturesNotModifiableException
         */
        @Nonnull
        ThingErrorResponse featuresNotModifiableError();
    }

    /**
     * Factory for events triggered by {@link ModifyFeatures} command.
     */
    interface EventFactory extends LiveEventFactory {

        /**
         * Creates a {@link FeaturesCreated} event using the values of the {@code Command}.
         *
         * @return the event.
         */
        @Nonnull
        FeaturesCreated created();

        /**
         * Creates a {@link FeaturesModified} event using the values of the {@code Command}.
         *
         * @return the event.
         */
        @Nonnull
        FeaturesModified modified();
    }

}
