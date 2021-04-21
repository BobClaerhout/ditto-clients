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
import java.util.function.Function;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.ditto.client.live.commands.base.LiveCommandAnswer;
import org.eclipse.ditto.json.JsonField;
import org.eclipse.ditto.json.JsonFieldSelector;
import org.eclipse.ditto.model.base.headers.DittoHeaders;
import org.eclipse.ditto.things.model.Thing;
import org.eclipse.ditto.signals.commands.base.CommandResponse;
import org.eclipse.ditto.things.model.signals.commands.query.RetrieveThingsResponse;

/**
 * A mutable builder with a fluent API for creating a {@link LiveCommandAnswer} for a
 * {@link RetrieveThingsLiveCommand}.
 *
 * @since 2.0.0
 */
@ParametersAreNonnullByDefault
@NotThreadSafe
final class RetrieveThingsLiveCommandAnswerBuilderImpl
        extends AbstractLiveCommandAnswerBuilder<RetrieveThingsLiveCommand, RetrieveThingsLiveCommandAnswerBuilder.ResponseFactory>
        implements RetrieveThingsLiveCommandAnswerBuilder {

    private RetrieveThingsLiveCommandAnswerBuilderImpl(final RetrieveThingsLiveCommand command) {
        super(command);
    }

    /**
     * Returns a new instance of {@code RetrieveThingsLiveCommandAnswerBuilderImpl}.
     *
     * @param command the command to build an answer for.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     */
    public static RetrieveThingsLiveCommandAnswerBuilderImpl newInstance(final RetrieveThingsLiveCommand command) {
        return new RetrieveThingsLiveCommandAnswerBuilderImpl(command);
    }

    @Override
    protected CommandResponse<?> doCreateResponse(
            final Function<ResponseFactory, CommandResponse<?>> createResponseFunction) {
        return createResponseFunction.apply(new ResponseFactoryImpl());
    }

    @ParametersAreNonnullByDefault
    @Immutable
    private final class ResponseFactoryImpl implements ResponseFactory {

        @Nonnull
        @Override
        public RetrieveThingsResponse retrieved(final List<Thing> things, final Predicate<JsonField> predicate) {
            final DittoHeaders dittoHeaders = command.getDittoHeaders();

            final String namespace = command.getNamespace().orElse(null);

            final Function<JsonFieldSelector, RetrieveThingsResponse> fieldSelectorToResponse =
                    fieldSelector -> RetrieveThingsResponse.of(things, fieldSelector, predicate, namespace, dittoHeaders);

            return command.getSelectedFields()
                    .map(fieldSelectorToResponse)
                    .orElse(RetrieveThingsResponse.of(things, predicate, namespace, dittoHeaders));
        }

        @Nonnull
        @Override
        public RetrieveThingsResponse retrieved(final List<Thing> things) {
            return retrieved(things, jsonField -> true);
        }
    }

}
