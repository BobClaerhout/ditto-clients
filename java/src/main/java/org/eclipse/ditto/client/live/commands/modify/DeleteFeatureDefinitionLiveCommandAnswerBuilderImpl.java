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
import org.eclipse.ditto.things.model.signals.commands.ThingErrorResponse;
import org.eclipse.ditto.things.model.signals.commands.exceptions.FeatureDefinitionNotAccessibleException;
import org.eclipse.ditto.things.model.signals.commands.exceptions.FeatureDefinitionNotModifiableException;
import org.eclipse.ditto.things.model.signals.commands.modify.DeleteFeatureDefinitionResponse;
import org.eclipse.ditto.signals.events.base.Event;
import org.eclipse.ditto.things.model.signals.events.FeatureDefinitionDeleted;

/**
 * A mutable builder with a fluent API for creating a {@link LiveCommandAnswer} for a
 * {@link DeleteFeatureDefinitionLiveCommand}.
 *
 * @since 2.0.0
 */
@ParametersAreNonnullByDefault
@NotThreadSafe
final class DeleteFeatureDefinitionLiveCommandAnswerBuilderImpl
        extends
        AbstractLiveCommandAnswerBuilder<DeleteFeatureDefinitionLiveCommand, DeleteFeatureDefinitionLiveCommandAnswerBuilder.ResponseFactory,
                DeleteFeatureDefinitionLiveCommandAnswerBuilder.EventFactory>
        implements DeleteFeatureDefinitionLiveCommandAnswerBuilder {

    private DeleteFeatureDefinitionLiveCommandAnswerBuilderImpl(final DeleteFeatureDefinitionLiveCommand command) {
        super(command);
    }

    /**
     * Returns a new instance of {@code DeleteFeatureDefinitionLiveCommandAnswerBuilderImpl}.
     *
     * @param command the command to build an answer for.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     */
    public static DeleteFeatureDefinitionLiveCommandAnswerBuilderImpl newInstance(
            final DeleteFeatureDefinitionLiveCommand command) {

        return new DeleteFeatureDefinitionLiveCommandAnswerBuilderImpl(command);
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
        public DeleteFeatureDefinitionResponse deleted() {
            return DeleteFeatureDefinitionResponse.of(command.getEntityId(), command.getFeatureId(),
                    command.getDittoHeaders());
        }

        @Nonnull
        @Override
        public ThingErrorResponse featureDefinitionNotAccessibleError() {
            return errorResponse(command.getEntityId(),
                    FeatureDefinitionNotAccessibleException.newBuilder(command.getEntityId(),
                            command.getFeatureId())
                            .dittoHeaders(command.getDittoHeaders())
                            .build());
        }

        @Nonnull
        @Override
        public ThingErrorResponse featureDefinitionNotModifiableError() {
            return errorResponse(command.getEntityId(),
                    FeatureDefinitionNotModifiableException.newBuilder(command.getEntityId(),
                            command.getFeatureId())
                            .dittoHeaders(command.getDittoHeaders())
                            .build());
        }

    }

    private final class EventFactoryImpl implements EventFactory {

        @Nonnull
        @Override
        public FeatureDefinitionDeleted deleted() {
            return FeatureDefinitionDeleted.of(command.getEntityId(), command.getFeatureId(), -1,
                    Instant.now(), command.getDittoHeaders(), null);
        }

    }

}
