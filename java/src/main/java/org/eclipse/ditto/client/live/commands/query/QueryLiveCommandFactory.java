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

import javax.annotation.concurrent.Immutable;

import org.eclipse.ditto.client.live.commands.base.LiveCommand;
import org.eclipse.ditto.signals.commands.base.Command;
import org.eclipse.ditto.utils.jsr305.annotations.AllValuesAreNonnullByDefault;

/**
 * A factory for getting immutable instances of query {@link LiveCommand LiveCommand}s.
 *
 * @since 2.0.0
 */
@AllValuesAreNonnullByDefault
@Immutable
public final class QueryLiveCommandFactory {

    private QueryLiveCommandFactory() {
        throw new AssertionError();
    }

    /**
     * Returns a new immutable instance of {@code RetrieveAttributeLiveCommand}.
     *
     * @param command the command to base the result on.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     * @throws ClassCastException if {@code command} is not an instance of
     * {@link org.eclipse.ditto.things.model.signals.commands.query.RetrieveAttribute RetrieveAttribute}.
     */
    public static RetrieveAttributeLiveCommand retrieveAttribute(final Command<?> command) {
        return RetrieveAttributeLiveCommandImpl.of(command);
    }

    /**
     * Returns a new immutable instance of {@code RetrieveAttributesLiveCommand}.
     *
     * @param command the command to base the result on.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     * @throws ClassCastException if {@code command} is not an instance of
     * {@link org.eclipse.ditto.things.model.signals.commands.query.RetrieveAttributes RetrieveAttributes}.
     */
    public static RetrieveAttributesLiveCommand retrieveAttributes(final Command<?> command) {
        return RetrieveAttributesLiveCommandImpl.of(command);
    }

    /**
     * Returns a new immutable instance of {@code RetrieveFeatureLiveCommand}.
     *
     * @param command the command to base the result on.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     * @throws ClassCastException if {@code command} is not an instance of
     * {@link org.eclipse.ditto.things.model.signals.commands.query.RetrieveFeature RetrieveFeature}.
     */
    public static RetrieveFeatureLiveCommand retrieveFeature(final Command<?> command) {
        return RetrieveFeatureLiveCommandImpl.of(command);
    }

    /**
     * Returns a new immutable instance of {@code RetrieveFeatureDefinitionLiveCommand}.
     *
     * @param command the command to base the result on.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     * @throws ClassCastException if {@code command} is not an instance of
     * {@link org.eclipse.ditto.things.model.signals.commands.query.RetrieveFeatureDefinition RetrieveFeatureDefinition}.
     */
    public static RetrieveFeatureDefinitionLiveCommand retrieveFeatureDefinition(final Command<?> command) {
        return RetrieveFeatureDefinitionLiveCommandImpl.of(command);
    }

    /**
     * Returns a new immutable instance of {@code RetrieveFeaturePropertiesLiveCommand}.
     *
     * @param command the command to base the result on.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     * @throws ClassCastException if {@code command} is not an instance of
     * {@link org.eclipse.ditto.things.model.signals.commands.query.RetrieveFeatureProperties RetrieveFeatureProperties}.
     */
    public static RetrieveFeaturePropertiesLiveCommand retrieveFeatureProperties(final Command<?> command) {
        return RetrieveFeaturePropertiesLiveCommandImpl.of(command);
    }

    /**
     * Returns a new immutable instance of {@code RetrieveFeaturePropertyLiveCommand}.
     *
     * @param command the command to base the result on.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     * @throws ClassCastException if {@code command} is not an instance of
     * {@link org.eclipse.ditto.things.model.signals.commands.query.RetrieveFeatureProperty RetrieveFeatureProperty}.
     */
    public static RetrieveFeaturePropertyLiveCommand retrieveFeatureProperty(final Command<?> command) {
        return RetrieveFeaturePropertyLiveCommandImpl.of(command);
    }

    /**
     * Returns a new immutable instance of {@code RetrieveFeaturesLiveCommand}.
     *
     * @param command the command to base the result on.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     * @throws ClassCastException if {@code command} is not an instance of
     * {@link org.eclipse.ditto.things.model.signals.commands.query.RetrieveFeatures RetrieveFeatures}.
     */
    public static RetrieveFeaturesLiveCommand retrieveFeatures(final Command<?> command) {
        return RetrieveFeaturesLiveCommandImpl.of(command);
    }

    /**
     * Returns a new immutable instance of {@code RetrieveThingLiveCommand}.
     *
     * @param command the command to base the result on.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     * @throws ClassCastException if {@code command} is not an instance of
     * {@link org.eclipse.ditto.things.model.signals.commands.query.RetrieveThing RetrieveThing}.
     */
    public static RetrieveThingLiveCommand retrieveThing(final Command<?> command) {
        return RetrieveThingLiveCommandImpl.of(command);
    }

    /**
     * Returns a new immutable instance of {@code RetrieveThingsLiveCommand}.
     *
     * @param command the command to base the result on.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     * @throws ClassCastException if {@code command} is not an instance of
     * {@link org.eclipse.ditto.things.model.signals.commands.query.RetrieveThings RetrieveThings}.
     */
    public static RetrieveThingsLiveCommand retrieveThings(final Command<?> command) {
        return RetrieveThingsLiveCommandImpl.of(command);
    }

}
