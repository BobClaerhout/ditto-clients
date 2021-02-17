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
import org.eclipse.ditto.signals.commands.things.exceptions.FeaturePropertiesNotAccessibleException;
import org.eclipse.ditto.signals.commands.things.exceptions.FeaturePropertiesNotModifiableException;
import org.eclipse.ditto.signals.commands.things.modify.DeleteFeaturePropertiesResponse;
import org.eclipse.ditto.signals.events.base.Event;
import org.eclipse.ditto.signals.events.things.FeaturePropertiesDeleted;

/**
 * A mutable builder with a fluent API for creating a {@link LiveCommandAnswer} for a
 * {@link DeleteFeaturePropertiesLiveCommand}.
 *
 * @since 2.0.0
 */
@ParametersAreNonnullByDefault
@NotThreadSafe
final class DeleteFeaturePropertiesLiveCommandAnswerBuilderImpl
        extends AbstractLiveCommandAnswerBuilder<DeleteFeaturePropertiesLiveCommand,
        DeleteFeaturePropertiesLiveCommandAnswerBuilder.ResponseFactory,
        DeleteFeaturePropertiesLiveCommandAnswerBuilder.EventFactory>
        implements DeleteFeaturePropertiesLiveCommandAnswerBuilder {

    private DeleteFeaturePropertiesLiveCommandAnswerBuilderImpl(final DeleteFeaturePropertiesLiveCommand command) {
        super(command);
    }

    /**
     * Returns a new instance of {@code DeleteFeaturePropertiesLiveCommandAnswerBuilderImpl}.
     *
     * @param command the command to build an answer for.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     */
    public static DeleteFeaturePropertiesLiveCommandAnswerBuilderImpl newInstance(
            final DeleteFeaturePropertiesLiveCommand command) {
        return new DeleteFeaturePropertiesLiveCommandAnswerBuilderImpl(command);
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
        public DeleteFeaturePropertiesResponse deleted() {
            return DeleteFeaturePropertiesResponse.of(command.getThingEntityId(), command.getFeatureId(),
                    command.getDittoHeaders());
        }

        @Nonnull
        @Override
        public ThingErrorResponse featurePropertiesNotAccessibleError() {
            return errorResponse(command.getThingEntityId(),
                    FeaturePropertiesNotAccessibleException.newBuilder(command.getThingEntityId(),
                            command.getFeatureId())
                            .dittoHeaders(command.getDittoHeaders())
                            .build());
        }

        @Nonnull
        @Override
        public ThingErrorResponse featurePropertiesNotModifiableError() {
            return errorResponse(command.getThingEntityId(),
                    FeaturePropertiesNotModifiableException.newBuilder(command.getThingEntityId(),
                            command.getFeatureId())
                            .dittoHeaders(command.getDittoHeaders())
                            .build());
        }
    }

    private final class EventFactoryImpl implements EventFactory {

        @Nonnull
        @Override
        public FeaturePropertiesDeleted deleted() {
            return FeaturePropertiesDeleted.of(command.getThingEntityId(), command.getFeatureId(), -1,
                    Instant.now(), command.getDittoHeaders(), null);
        }
    }

}
