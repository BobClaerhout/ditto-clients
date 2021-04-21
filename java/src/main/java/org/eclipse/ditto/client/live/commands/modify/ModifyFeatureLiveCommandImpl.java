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
import org.eclipse.ditto.things.model.Feature;
import org.eclipse.ditto.signals.commands.base.Command;
import org.eclipse.ditto.things.model.signals.commands.modify.ModifyFeature;

/**
 * An immutable implementation of {@link ModifyFeatureLiveCommand}.
 *
 * @since 2.0.0
 */
@ParametersAreNonnullByDefault
@Immutable
final class ModifyFeatureLiveCommandImpl extends AbstractModifyLiveCommand<ModifyFeatureLiveCommand,
        ModifyFeatureLiveCommandAnswerBuilder> implements ModifyFeatureLiveCommand {

    private final Feature feature;

    private ModifyFeatureLiveCommandImpl(final ModifyFeature command) {
        super(command);
        feature = command.getFeature();
    }

    /**
     * Returns a new instance of {@code ModifyFeatureLiveCommandImpl}.
     *
     * @param command the command to base the result on.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     * @throws ClassCastException if {@code command} is not an instance of {@link ModifyFeature}.
     */
    @Nonnull
    public static ModifyFeatureLiveCommandImpl of(final Command<?> command) {
        return new ModifyFeatureLiveCommandImpl((ModifyFeature) command);
    }

    @Override
    public String getFeatureId() {
        return feature.getId();
    }

    @Nonnull
    @Override
    public Feature getFeature() {
        return feature;
    }

    @Override
    public Category getCategory() {
        return Category.MODIFY;
    }

    @Override
    public ModifyFeatureLiveCommand setDittoHeaders(final DittoHeaders dittoHeaders) {
        return new ModifyFeatureLiveCommandImpl(ModifyFeature.of(getEntityId(), getFeature(), dittoHeaders));
    }

    @Override
    public boolean changesAuthorization() {
        return false;
    }

    @Nonnull
    @Override
    public ModifyFeatureLiveCommandAnswerBuilder answer() {
        return ModifyFeatureLiveCommandAnswerBuilderImpl.newInstance(this);
    }

    @Nonnull
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + super.toString() + "]";
    }

}
