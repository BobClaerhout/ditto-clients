/*
 * Copyright (c) 2020 Contributors to the Eclipse Foundation
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
import static org.eclipse.ditto.signals.commands.base.assertions.CommandAssertions.assertThat;
import static org.mutabilitydetector.unittesting.AllowedReason.provided;
import static org.mutabilitydetector.unittesting.MutabilityAssert.assertInstancesOf;
import static org.mutabilitydetector.unittesting.MutabilityMatchers.areImmutable;

import java.text.MessageFormat;

import org.eclipse.ditto.json.JsonPointer;
import org.eclipse.ditto.model.base.headers.DittoHeaders;
import org.eclipse.ditto.signals.commands.base.Command;
import org.eclipse.ditto.things.model.signals.commands.TestConstants;
import org.eclipse.ditto.things.model.signals.commands.modify.DeleteFeatureDesiredProperty;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import nl.jqno.equalsverifier.EqualsVerifier;


/**
 * Unit test for {@link org.eclipse.ditto.client.live.commands.modify.DeleteFeatureDesiredPropertyLiveCommandImpl}.
 */
public final class DeleteFeatureDesiredPropertyLiveCommandImplTest {

    private DeleteFeatureDesiredProperty twinCommand;
    private DeleteFeatureDesiredPropertyLiveCommand underTest;

    @Before
    public void setUp() {
        twinCommand = DeleteFeatureDesiredProperty.of(TestConstants.Thing.THING_ID,
                TestConstants.Feature.HOVER_BOARD_ID, TestConstants.Feature.HOVER_BOARD_PROPERTY_POINTER,
                DittoHeaders.empty());
        underTest = DeleteFeatureDesiredPropertyLiveCommandImpl.of(twinCommand);
    }

    @Test
    public void assertImmutability() {
        assertInstancesOf(DeleteFeatureDesiredPropertyLiveCommandImpl.class,
                areImmutable(),
                provided(JsonPointer.class).isAlsoImmutable());
    }

    @Test
    public void testHashCodeAndEquals() {
        EqualsVerifier.forClass(DeleteFeatureDesiredPropertyLiveCommandImpl.class)
                .withRedefinedSuperclass()
                .withIgnoredFields("thingModifyCommand", "featureId", "desiredPropertyPointer")
                .verify();
    }

    @Test
    public void tryToGetDeleteFeatureDesiredPropertyLiveCommandForNull() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> DeleteFeatureDesiredPropertyLiveCommandImpl.of(null))
                .withMessage(MessageFormat.format("The {0} must not be null!", "command"))
                .withNoCause();
    }

    @Test
    public void tryToGetDeleteFeatureDesiredPropertyLiveCommandForCreateFeatureDesiredPropertyCommand() {
        final Command<?> commandMock = Mockito.mock(Command.class);

        assertThatExceptionOfType(ClassCastException.class)
                .isThrownBy(() -> DeleteFeatureDesiredPropertyLiveCommandImpl.of(commandMock))
                .withMessageContaining(DeleteFeatureDesiredProperty.class.getName())
                .withNoCause();
    }

    @Test
    public void getDeleteFeatureDesiredPropertyLiveCommandReturnsExpected() {
        assertThat(underTest)
                .withType(twinCommand.getType())
                .withDittoHeaders(twinCommand.getDittoHeaders())
                .withId(twinCommand.getEntityId())
                .withManifest(twinCommand.getManifest())
                .withResourcePath(twinCommand.getResourcePath());
        assertThat(underTest.getFeatureId()).isEqualTo(twinCommand.getFeatureId());
        assertThat(underTest.getDesiredPropertyPointer()).isEqualTo(twinCommand.getDesiredPropertyPointer());
    }

    @Test
    public void setDittoHeadersReturnsExpected() {
        final DittoHeaders emptyDittoHeaders = DittoHeaders.empty();
        final DeleteFeatureDesiredPropertyLiveCommand newDeleteFeatureDesiredPropertyLiveCommand =
                underTest.setDittoHeaders(emptyDittoHeaders);

        assertThat(newDeleteFeatureDesiredPropertyLiveCommand).withDittoHeaders(emptyDittoHeaders);
    }

    @Test
    public void answerReturnsNotNull() {
        assertThat(underTest.answer()).isNotNull();
    }

    @Test
    public void toStringReturnsExpected() {
        assertThat(underTest.toString())
                .contains(underTest.getClass().getSimpleName())
                .contains("command=")
                .contains(twinCommand.toString());
    }

}
