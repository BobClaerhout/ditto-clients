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

import java.util.function.Function;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.ditto.client.live.commands.base.LiveCommandAnswer;
import org.eclipse.ditto.model.things.FeatureDefinition;
import org.eclipse.ditto.signals.commands.base.CommandResponse;
import org.eclipse.ditto.signals.commands.things.ThingErrorResponse;
import org.eclipse.ditto.signals.commands.things.exceptions.FeatureDefinitionNotAccessibleException;
import org.eclipse.ditto.signals.commands.things.query.RetrieveFeatureDefinitionResponse;
import org.eclipse.ditto.utils.jsr305.annotations.AllValuesAreNonnullByDefault;

/**
 * A mutable builder with a fluent API for creating a {@link LiveCommandAnswer} for a
 * {@link RetrieveFeatureDefinitionLiveCommand}.
 *
 * @since 2.0.0
 */
@ParametersAreNonnullByDefault
@NotThreadSafe
final class RetrieveFeatureDefinitionLiveCommandAnswerBuilderImpl
        extends
        AbstractLiveCommandAnswerBuilder<RetrieveFeatureDefinitionLiveCommand, RetrieveFeatureDefinitionLiveCommandAnswerBuilder.ResponseFactory>
        implements RetrieveFeatureDefinitionLiveCommandAnswerBuilder {

    private RetrieveFeatureDefinitionLiveCommandAnswerBuilderImpl(final RetrieveFeatureDefinitionLiveCommand command) {
        super(command);
    }

    /**
     * Returns a new instance of {@code RetrieveFeatureDefinitionLiveCommandAnswerBuilderImpl}.
     *
     * @param command the command to build an answer for.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     */
    public static RetrieveFeatureDefinitionLiveCommandAnswerBuilderImpl newInstance(
            final RetrieveFeatureDefinitionLiveCommand command) {

        return new RetrieveFeatureDefinitionLiveCommandAnswerBuilderImpl(command);
    }

    @Override
    protected CommandResponse doCreateResponse(
            final Function<ResponseFactory, CommandResponse<?>> createResponseFunction) {

        return createResponseFunction.apply(new ResponseFactoryImpl());
    }

    @AllValuesAreNonnullByDefault
    @Immutable
    private final class ResponseFactoryImpl implements ResponseFactory {

        @Override
        public RetrieveFeatureDefinitionResponse retrieved(final FeatureDefinition featureProperties) {
            return RetrieveFeatureDefinitionResponse.of(command.getEntityId(), command.getFeatureId(),
                    featureProperties,
                    command.getDittoHeaders());
        }

        @Override
        public ThingErrorResponse featureDefinitionNotAccessibleError() {
            return errorResponse(command.getEntityId(),
                    FeatureDefinitionNotAccessibleException.newBuilder(command.getEntityId(),
                            command.getFeatureId())
                            .dittoHeaders(command.getDittoHeaders())
                            .build());
        }

    }

}
