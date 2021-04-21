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
import org.eclipse.ditto.signals.commands.things.modify.ModifyFeatureProperty;
import org.eclipse.ditto.signals.commands.things.modify.ModifyFeaturePropertyResponse;
import org.eclipse.ditto.signals.events.things.FeaturePropertyCreated;
import org.eclipse.ditto.signals.events.things.FeaturePropertyModified;

/**
 * LiveCommandAnswer builder for producing {@code CommandResponse}s and {@code Event}s for {@link ModifyFeatureProperty}
 * commands.
 *
 * @since 2.0.0
 */
public interface ModifyFeaturePropertyLiveCommandAnswerBuilder
        extends LiveCommandAnswerBuilder.ModifyCommandResponseStep<
        ModifyFeaturePropertyLiveCommandAnswerBuilder.ResponseFactory,
        ModifyFeaturePropertyLiveCommandAnswerBuilder.EventFactory> {

    /**
     * Factory for {@code CommandResponse}s to {@link ModifyFeatureProperty} command.
     */
    interface ResponseFactory extends LiveCommandResponseFactory {

        /**
         * Builds a "created"  {@link ModifyFeaturePropertyResponse} using the values of the {@code Command}.
         *
         * @return the response.
         */
        @Nonnull
        ModifyFeaturePropertyResponse created();

        /**
         * Builds a "modified"  {@link ModifyFeaturePropertyResponse} using the values of the {@code Command}.
         *
         * @return the response.
         */
        @Nonnull
        ModifyFeaturePropertyResponse modified();

        /**
         * Builds a {@link ThingErrorResponse} indicating that the feature property was not accessible.
         *
         * @return the response.
         * @see org.eclipse.ditto.signals.commands.things.exceptions.FeaturePropertyNotAccessibleException
         * FeaturePropertyNotAccessibleException
         */
        @Nonnull
        ThingErrorResponse featurePropertyNotAccessibleError();

        /**
         * Builds a {@link ThingErrorResponse} indicating that the feature property was not modifiable.
         *
         * @return the response.
         * @see org.eclipse.ditto.signals.commands.things.exceptions.FeaturePropertyNotModifiableException
         * FeaturePropertyNotModifiableException
         */
        @Nonnull
        ThingErrorResponse featurePropertyNotModifiableError();
    }

    /**
     * Factory for events triggered by {@link ModifyFeatureProperty} command.
     */
    interface EventFactory extends LiveEventFactory {

        /**
         * Creates a {@link FeaturePropertyCreated} event using the values of the {@code Command}.
         *
         * @return the event.
         */
        @Nonnull
        FeaturePropertyCreated created();

        /**
         * Creates a {@link FeaturePropertyModified} event using the values of the {@code Command}.
         *
         * @return the event.
         */
        @Nonnull
        FeaturePropertyModified modified();
    }

}
