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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.eclipse.ditto.model.base.headers.DittoHeaders;
import org.eclipse.ditto.model.things.FeatureDefinition;
import org.eclipse.ditto.signals.commands.base.Command;
import org.eclipse.ditto.signals.commands.things.modify.ModifyFeatureDefinition;
import org.eclipse.ditto.utils.jsr305.annotations.AllValuesAreNonnullByDefault;

/**
 * An immutable implementation of {@link ModifyFeatureDefinitionLiveCommand}.
 *
 * @since 2.0.0
 */
@AllValuesAreNonnullByDefault
@Immutable
final class ModifyFeatureDefinitionLiveCommandImpl
        extends
        AbstractModifyLiveCommand<ModifyFeatureDefinitionLiveCommand, ModifyFeatureDefinitionLiveCommandAnswerBuilder>
        implements ModifyFeatureDefinitionLiveCommand {

    private final String featureId;
    private final FeatureDefinition featureProperties;

    private ModifyFeatureDefinitionLiveCommandImpl(final ModifyFeatureDefinition command) {
        super(command);
        featureId = command.getFeatureId();
        featureProperties = command.getDefinition();
    }

    /**
     * Returns a new instance of {@code ModifyFeatureDefinitionLiveCommandImpl}.
     *
     * @param command the command to base the result on.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     * @throws ClassCastException if {@code command} is not an instance of {@link ModifyFeatureDefinition}.
     */
    @Nonnull
    public static ModifyFeatureDefinitionLiveCommandImpl of(final Command<?> command) {
        return new ModifyFeatureDefinitionLiveCommandImpl((ModifyFeatureDefinition) command);
    }

    @Override
    public String getFeatureId() {
        return featureId;
    }

    @Nonnull
    @Override
    public FeatureDefinition getDefinition() {
        return featureProperties;
    }

    @Override
    public Category getCategory() {
        return Category.MODIFY;
    }

    @Override
    public ModifyFeatureDefinitionLiveCommand setDittoHeaders(final DittoHeaders dittoHeaders) {
        return new ModifyFeatureDefinitionLiveCommandImpl(ModifyFeatureDefinition.of(getThingEntityId(), getFeatureId(),
                getDefinition(), dittoHeaders));
    }

    @Override
    public boolean changesAuthorization() {
        return false;
    }

    @Nonnull
    @Override
    public ModifyFeatureDefinitionLiveCommandAnswerBuilder answer() {
        return ModifyFeatureDefinitionLiveCommandAnswerBuilderImpl.newInstance(this);
    }

    @Nonnull
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + super.toString() + "]";
    }

}
