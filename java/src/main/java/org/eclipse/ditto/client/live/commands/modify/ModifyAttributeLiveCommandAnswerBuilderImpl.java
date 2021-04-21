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
import org.eclipse.ditto.things.model.signals.commands.exceptions.AttributeNotAccessibleException;
import org.eclipse.ditto.things.model.signals.commands.exceptions.AttributeNotModifiableException;
import org.eclipse.ditto.things.model.signals.commands.modify.ModifyAttributeResponse;
import org.eclipse.ditto.signals.events.base.Event;
import org.eclipse.ditto.things.model.signals.events.AttributeCreated;
import org.eclipse.ditto.things.model.signals.events.AttributeModified;

/**
 * A mutable builder with a fluent API for creating a {@link LiveCommandAnswer} for a
 * {@link ModifyAttributeLiveCommand}.
 *
 * @since 2.0.0
 */
@ParametersAreNonnullByDefault
@NotThreadSafe
final class ModifyAttributeLiveCommandAnswerBuilderImpl
        extends
        AbstractLiveCommandAnswerBuilder<ModifyAttributeLiveCommand, ModifyAttributeLiveCommandAnswerBuilder.ResponseFactory,
                ModifyAttributeLiveCommandAnswerBuilder.EventFactory>
        implements ModifyAttributeLiveCommandAnswerBuilder {

    private ModifyAttributeLiveCommandAnswerBuilderImpl(final ModifyAttributeLiveCommand command) {
        super(command);
    }

    /**
     * Returns a new instance of {@code ModifyAttributeLiveCommandAnswerBuilderImpl}.
     *
     * @param command the command to build an answer for.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     */
    public static ModifyAttributeLiveCommandAnswerBuilderImpl newInstance(final ModifyAttributeLiveCommand command) {
        return new ModifyAttributeLiveCommandAnswerBuilderImpl(command);
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
        public ModifyAttributeResponse created() {
            return ModifyAttributeResponse.created(command.getEntityId(), command.getAttributePointer(),
                    command.getAttributeValue(),
                    command.getDittoHeaders());
        }

        @Nonnull
        @Override
        public ModifyAttributeResponse modified() {
            return ModifyAttributeResponse.modified(command.getEntityId(), command.getAttributePointer(),
                    command.getDittoHeaders());
        }

        @Nonnull
        @Override
        public ThingErrorResponse attributeNotAccessibleError() {
            return errorResponse(command.getEntityId(),
                    AttributeNotAccessibleException.newBuilder(command.getEntityId(),
                            command.getAttributePointer())
                            .dittoHeaders(command.getDittoHeaders())
                            .build());
        }

        @Nonnull
        @Override
        public ThingErrorResponse attributeNotModifiableError() {
            return errorResponse(command.getEntityId(),
                    AttributeNotModifiableException.newBuilder(command.getEntityId(),
                            command.getAttributePointer())
                            .dittoHeaders(command.getDittoHeaders())
                            .build());
        }
    }

    @Immutable
    private final class EventFactoryImpl implements EventFactory {

        @Nonnull
        @Override
        public AttributeCreated created() {
            return AttributeCreated.of(command.getEntityId(), command.getAttributePointer(),
                    command.getAttributeValue(), -1, Instant.now(), command.getDittoHeaders(), null);
        }

        @Nonnull
        @Override
        public AttributeModified modified() {
            return AttributeModified.of(command.getEntityId(), command.getAttributePointer(),
                    command.getAttributeValue(), -1, Instant.now(), command.getDittoHeaders(), null);
        }
    }

}
