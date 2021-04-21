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
import org.eclipse.ditto.model.base.exceptions.DittoRuntimeException;
import org.eclipse.ditto.signals.commands.base.CommandResponse;
import org.eclipse.ditto.things.model.signals.commands.ThingErrorResponse;
import org.eclipse.ditto.things.model.signals.commands.exceptions.ThingConflictException;
import org.eclipse.ditto.things.model.signals.commands.modify.CreateThingResponse;
import org.eclipse.ditto.signals.events.base.Event;
import org.eclipse.ditto.things.model.signals.events.ThingCreated;

/**
 * A mutable builder with a fluent API for creating a {@link LiveCommandAnswer LiveCommandAnswer} for a
 * {@link CreateThingLiveCommand}.
 *
 * @since 2.0.0
 */
@ParametersAreNonnullByDefault
@NotThreadSafe
final class CreateThingLiveCommandAnswerBuilderImpl
        extends
        AbstractLiveCommandAnswerBuilder<CreateThingLiveCommand, CreateThingLiveCommandAnswerBuilder.ResponseFactory,
                CreateThingLiveCommandAnswerBuilder.EventFactory>
        implements CreateThingLiveCommandAnswerBuilder {

    private CreateThingLiveCommandAnswerBuilderImpl(final CreateThingLiveCommand command) {
        super(command);
    }

    /**
     * Returns a new instance of {@code CreateThingLiveCommandAnswerBuilderImpl}.
     *
     * @param command the command to build an answer for.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     */
    public static CreateThingLiveCommandAnswerBuilderImpl newInstance(final CreateThingLiveCommand command) {
        return new CreateThingLiveCommandAnswerBuilderImpl(command);
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
        public CreateThingResponse created() {
            return CreateThingResponse.of(command.getThing(), command.getDittoHeaders());
        }

        @Nonnull
        @Override
        public ThingErrorResponse thingConflictError() {
            final DittoRuntimeException exception = ThingConflictException.newBuilder(command.getEntityId())
                    .dittoHeaders(command.getDittoHeaders())
                    .build();
            return errorResponse(command.getEntityId(), exception);
        }
    }

    @Immutable
    private final class EventFactoryImpl implements EventFactory {

        @Nonnull
        @Override
        public ThingCreated created() {
            return ThingCreated.of(command.getThing(), -1, Instant.now(), command.getDittoHeaders(), null);
        }
    }

}
