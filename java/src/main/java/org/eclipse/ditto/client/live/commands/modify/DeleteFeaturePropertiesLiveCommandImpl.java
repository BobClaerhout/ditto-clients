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
import org.eclipse.ditto.signals.commands.base.Command;
import org.eclipse.ditto.signals.commands.things.modify.DeleteFeatureProperties;

/**
 * An immutable implementation of {@link DeleteFeaturePropertiesLiveCommand}.
 *
 * @since 2.0.0
 */
@ParametersAreNonnullByDefault
@Immutable
final class DeleteFeaturePropertiesLiveCommandImpl
        extends
        AbstractModifyLiveCommand<DeleteFeaturePropertiesLiveCommand, DeleteFeaturePropertiesLiveCommandAnswerBuilder>
        implements DeleteFeaturePropertiesLiveCommand {

    private final String featureId;

    private DeleteFeaturePropertiesLiveCommandImpl(final DeleteFeatureProperties command) {
        super(command);
        featureId = command.getFeatureId();
    }

    /**
     * Returns a new instance of {@code DeleteFeaturePropertiesLiveCommandImpl}.
     *
     * @param command the command to base the result on.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     * @throws ClassCastException if {@code command} is not an instance of {@link DeleteFeatureProperties}.
     */
    @Nonnull
    public static DeleteFeaturePropertiesLiveCommandImpl of(final Command<?> command) {
        return new DeleteFeaturePropertiesLiveCommandImpl((DeleteFeatureProperties) command);
    }

    @Override
    public String getFeatureId() {
        return featureId;
    }

    @Override
    public Category getCategory() {
        return Category.DELETE;
    }

    @Override
    public DeleteFeaturePropertiesLiveCommand setDittoHeaders(final DittoHeaders dittoHeaders) {
        return new DeleteFeaturePropertiesLiveCommandImpl(DeleteFeatureProperties.of(getEntityId(), getFeatureId(),
                dittoHeaders));
    }

    @Override
    public boolean changesAuthorization() {
        return false;
    }

    @Nonnull
    @Override
    public DeleteFeaturePropertiesLiveCommandAnswerBuilder answer() {
        return DeleteFeaturePropertiesLiveCommandAnswerBuilderImpl.newInstance(this);
    }

    @Nonnull
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + super.toString() + "]";
    }

}
