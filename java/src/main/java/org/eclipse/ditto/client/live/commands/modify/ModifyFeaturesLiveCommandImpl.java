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
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import org.eclipse.ditto.model.base.headers.DittoHeaders;
import org.eclipse.ditto.model.things.Features;
import org.eclipse.ditto.signals.commands.base.Command;
import org.eclipse.ditto.signals.commands.things.modify.ModifyFeatures;

/**
 * An immutable implementation of {@link ModifyFeaturesLiveCommand}.
 *
 * @since 2.0.0
 */
@ParametersAreNonnullByDefault
@Immutable
final class ModifyFeaturesLiveCommandImpl extends AbstractModifyLiveCommand<ModifyFeaturesLiveCommand,
        ModifyFeaturesLiveCommandAnswerBuilder> implements ModifyFeaturesLiveCommand {

    private final Features features;

    private ModifyFeaturesLiveCommandImpl(final ModifyFeatures command) {
        super(command);
        features = command.getFeatures();
    }

    /**
     * Returns a new instance of {@code ModifyFeaturesLiveCommandImpl}.
     *
     * @param command the command to base the result on.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     * @throws ClassCastException if {@code command} is not an instance of {@link ModifyFeatures}.
     */
    @Nonnull
    public static ModifyFeaturesLiveCommandImpl of(final Command<?> command) {
        return new ModifyFeaturesLiveCommandImpl((ModifyFeatures) command);
    }

    @Nonnull
    @Override
    public Features getFeatures() {
        return features;
    }

    @Override
    public Category getCategory() {
        return Category.MODIFY;
    }

    @Override
    public ModifyFeaturesLiveCommand setDittoHeaders(final DittoHeaders dittoHeaders) {
        return new ModifyFeaturesLiveCommandImpl(ModifyFeatures.of(getEntityId(), getFeatures(), dittoHeaders));
    }

    @Override
    public boolean changesAuthorization() {
        return false;
    }

    @Nonnull
    @Override
    public ModifyFeaturesLiveCommandAnswerBuilder answer() {
        return ModifyFeaturesLiveCommandAnswerBuilderImpl.newInstance(this);
    }

    @Nonnull
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + super.toString() + "]";
    }

}
