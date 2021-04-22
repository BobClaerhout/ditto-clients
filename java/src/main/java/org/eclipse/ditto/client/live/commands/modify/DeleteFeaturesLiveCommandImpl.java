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

import org.eclipse.ditto.base.model.headers.DittoHeaders;
import org.eclipse.ditto.base.model.signals.commands.Command;
import org.eclipse.ditto.things.model.signals.commands.modify.DeleteFeatures;

/**
 * An immutable implementation of {@link DeleteFeaturesLiveCommand}.
 *
 * @since 2.0.0
 */
@ParametersAreNonnullByDefault
@Immutable
final class DeleteFeaturesLiveCommandImpl
        extends AbstractModifyLiveCommand<DeleteFeaturesLiveCommand, DeleteFeaturesLiveCommandAnswerBuilder>
        implements DeleteFeaturesLiveCommand {

    private DeleteFeaturesLiveCommandImpl(final DeleteFeatures command) {
        super(command);
    }

    /**
     * Returns a new instance of {@code DeleteFeaturesLiveCommandImpl}.
     *
     * @param command the command to base the result on.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     * @throws ClassCastException if {@code command} is not an instance of {@link DeleteFeatures}.
     */
    @Nonnull
    public static DeleteFeaturesLiveCommandImpl of(final Command<?> command) {
        return new DeleteFeaturesLiveCommandImpl((DeleteFeatures) command);
    }

    @Override
    public Category getCategory() {
        return Category.DELETE;
    }

    @Override
    public DeleteFeaturesLiveCommand setDittoHeaders(final DittoHeaders dittoHeaders) {
        return new DeleteFeaturesLiveCommandImpl(DeleteFeatures.of(getEntityId(), dittoHeaders));
    }

    @Override
    public boolean changesAuthorization() {
        return false;
    }

    @Nonnull
    @Override
    public DeleteFeaturesLiveCommandAnswerBuilder answer() {
        return DeleteFeaturesLiveCommandAnswerBuilderImpl.newInstance(this);
    }

    @Nonnull
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + super.toString() + "]";
    }

}
