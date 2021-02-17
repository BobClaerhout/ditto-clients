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

import org.eclipse.ditto.client.live.commands.base.LiveCommand;
import org.eclipse.ditto.model.things.Feature;
import org.eclipse.ditto.signals.base.WithFeatureId;
import org.eclipse.ditto.signals.commands.things.modify.ModifyFeature;
import org.eclipse.ditto.signals.commands.things.modify.ThingModifyCommand;

/**
 * {@link ModifyFeature} live command giving access to the command and all of its special accessors. Also the entry
 * point for creating a {@link ModifyFeatureLiveCommandAnswerBuilder} capable of answering incoming commands.
 *
 * @since 2.0.0
 */
public interface ModifyFeatureLiveCommand extends
        LiveCommand<ModifyFeatureLiveCommand, ModifyFeatureLiveCommandAnswerBuilder>,
        ThingModifyCommand<ModifyFeatureLiveCommand>,
        WithFeatureId {

    /**
     * Returns the new {@code Feature} to modify.
     *
     * @return the Feature to modify
     */
    @Nonnull
    Feature getFeature();

}
