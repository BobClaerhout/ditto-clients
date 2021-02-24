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

import java.time.Instant;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.ditto.client.live.commands.base.LiveCommandAnswer;
import org.eclipse.ditto.signals.commands.base.CommandResponse;
import org.eclipse.ditto.signals.commands.things.ThingErrorResponse;
import org.eclipse.ditto.signals.commands.things.exceptions.FeatureNotAccessibleException;
import org.eclipse.ditto.signals.commands.things.exceptions.FeatureNotModifiableException;
import org.eclipse.ditto.signals.commands.things.modify.ModifyFeatureResponse;
import org.eclipse.ditto.signals.events.base.Event;
import org.eclipse.ditto.signals.events.things.FeatureCreated;
import org.eclipse.ditto.signals.events.things.FeatureModified;

/**
 * A mutable builder with a fluent API for creating a {@link LiveCommandAnswer} for a {@link ModifyFeatureLiveCommand}.
 *
 * @since 2.0.0
 */
@ParametersAreNonnullByDefault
@NotThreadSafe
final class ModifyFeatureLiveCommandAnswerBuilderImpl
        extends
        AbstractLiveCommandAnswerBuilder<ModifyFeatureLiveCommand, ModifyFeatureLiveCommandAnswerBuilder.ResponseFactory,
                ModifyFeatureLiveCommandAnswerBuilder.EventFactory>
        implements ModifyFeatureLiveCommandAnswerBuilder {

    private ModifyFeatureLiveCommandAnswerBuilderImpl(final ModifyFeatureLiveCommand command) {
        super(command);
    }

    /**
     * Returns a new instance of {@code ModifyFeatureLiveCommandAnswerBuilderImpl}.
     *
     * @param command the command to build an answer for.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     */
    public static ModifyFeatureLiveCommandAnswerBuilderImpl newInstance(final ModifyFeatureLiveCommand command) {
        return new ModifyFeatureLiveCommandAnswerBuilderImpl(command);
    }

    @Override
    protected CommandResponse doCreateResponse(
            final Function<ResponseFactory, CommandResponse<?>> createResponseFunction) {
        return createResponseFunction.apply(new ResponseFactoryImpl());
    }

    @Override
    protected Event doCreateEvent(final Function<EventFactory, Event<?>> createEventFunction) {
        return createEventFunction.apply(new EventFactoryImpl());
    }

    @Immutable
    private final class ResponseFactoryImpl implements ResponseFactory {

        @Nonnull
        @Override
        public ModifyFeatureResponse created() {
            return ModifyFeatureResponse.created(command.getThingEntityId(), command.getFeature(),
                    command.getDittoHeaders());
        }

        @Nonnull
        @Override
        public ModifyFeatureResponse modified() {
            return ModifyFeatureResponse.modified(command.getThingEntityId(), command.getFeatureId(),
                    command.getDittoHeaders());
        }

        @Nonnull
        @Override
        public ThingErrorResponse featureNotAccessibleError() {
            return errorResponse(command.getThingEntityId(),
                    FeatureNotAccessibleException.newBuilder(command.getThingEntityId(), command.getFeatureId())
                            .dittoHeaders(command.getDittoHeaders())
                            .build());
        }

        @Nonnull
        @Override
        public ThingErrorResponse featureNotModifiableError() {
            return errorResponse(command.getThingEntityId(),
                    FeatureNotModifiableException.newBuilder(command.getThingEntityId(), command.getFeatureId())
                            .dittoHeaders(command.getDittoHeaders())
                            .build());
        }
    }

    @Immutable
    private final class EventFactoryImpl implements EventFactory {

        @Nonnull
        @Override
        public FeatureCreated created() {
            return FeatureCreated.of(command.getThingEntityId(), command.getFeature(), -1, Instant.now(),
                    command.getDittoHeaders(), null);
        }

        @Nonnull
        @Override
        public FeatureModified modified() {
            return FeatureModified.of(command.getThingEntityId(), command.getFeature(), -1, Instant.now(),
                    command.getDittoHeaders(), null);
        }
    }

}
