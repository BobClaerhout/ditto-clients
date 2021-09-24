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

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.text.MessageFormat;

import org.eclipse.ditto.base.model.common.HttpStatus;
import org.eclipse.ditto.base.model.headers.DittoHeaders;
import org.eclipse.ditto.client.live.TestConstants;
import org.eclipse.ditto.client.live.commands.assertions.LiveCommandAssertions;
import org.eclipse.ditto.client.live.commands.base.LiveCommandAnswer;
import org.eclipse.ditto.things.model.signals.commands.ThingErrorResponse;
import org.eclipse.ditto.things.model.signals.commands.exceptions.FeaturesNotAccessibleException;
import org.eclipse.ditto.things.model.signals.commands.exceptions.FeaturesNotModifiableException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Unit test for {@link org.eclipse.ditto.client.live.commands.modify.DeleteFeaturesLiveCommandAnswerBuilderImpl}.
 */
@RunWith(MockitoJUnitRunner.class)
public final class DeleteFeaturesLiveCommandAnswerBuilderImplTest {

    @Mock
    private DeleteFeaturesLiveCommand commandMock;

    private DeleteFeaturesLiveCommandAnswerBuilderImpl underTest;

    @Before
    public void setUp() {
        Mockito.when(commandMock.getEntityId()).thenReturn(TestConstants.Thing.THING_ID);
        Mockito.when(commandMock.getDittoHeaders()).thenReturn(DittoHeaders.empty());

        underTest = DeleteFeaturesLiveCommandAnswerBuilderImpl.newInstance(commandMock);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void tryToGetNewInstanceWithNullCommand() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> DeleteFeaturesLiveCommandAnswerBuilderImpl.newInstance(null))
                .withMessage(MessageFormat.format("The {0} must not be null!", "command"))
                .withNoCause();
    }

    @Test
    public void buildAnswerWithDeleteFeaturesResponseOnly() {
        final LiveCommandAnswer liveCommandAnswer =
                underTest.withResponse(DeleteFeaturesLiveCommandAnswerBuilder.ResponseFactory::deleted)
                        .withoutEvent()
                        .build();

        LiveCommandAssertions.assertThat(liveCommandAnswer)
                .hasNoEvent()
                .hasThingModifyCommandResponse();
    }

    @Test
    public void buildAnswerWithFeaturesNotAccessibleErrorResponseOnly() {
        final LiveCommandAnswer liveCommandAnswer =
                underTest.withResponse(
                        DeleteFeaturesLiveCommandAnswerBuilder.ResponseFactory::featuresNotAccessibleError)
                        .withoutEvent()
                        .build();

        LiveCommandAssertions.assertThat(liveCommandAnswer)
                .hasNoEvent()
                .hasThingErrorResponse()
                .withType(ThingErrorResponse.TYPE)
                .withDittoHeaders(DittoHeaders.newBuilder().responseRequired(false).build())
                .withStatus(HttpStatus.NOT_FOUND)
                .withDittoRuntimeExceptionOfType(FeaturesNotAccessibleException.class);
    }

    @Test
    public void buildAnswerWithFeaturesNotModifiableErrorResponseOnly() {
        final LiveCommandAnswer liveCommandAnswer =
                underTest.withResponse(
                        DeleteFeaturesLiveCommandAnswerBuilder.ResponseFactory::featuresNotModifiableError)
                        .withoutEvent()
                        .build();

        LiveCommandAssertions.assertThat(liveCommandAnswer)
                .hasNoEvent()
                .hasThingErrorResponse()
                .withType(ThingErrorResponse.TYPE)
                .withDittoHeaders(DittoHeaders.newBuilder().responseRequired(false).build())
                .withStatus(HttpStatus.FORBIDDEN)
                .withDittoRuntimeExceptionOfType(FeaturesNotModifiableException.class);
    }

    @Test
    public void buildAnswerWithFeaturesDeletedEventOnly() {
        final LiveCommandAnswer liveCommandAnswer = underTest.withoutResponse()
                .withEvent(DeleteFeaturesLiveCommandAnswerBuilder.EventFactory::deleted)
                .build();

        LiveCommandAssertions.assertThat(liveCommandAnswer)
                .hasNoResponse()
                .hasThingModifiedEvent();
    }

}
