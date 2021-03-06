// Copyright © 2012-2020 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.lattice.grid.application;

import java.util.List;

import io.vlingo.actors.Address;
import io.vlingo.actors.Definition;
import io.vlingo.actors.Returns;
import io.vlingo.common.SerializableConsumer;
import io.vlingo.lattice.grid.application.message.Answer;
import io.vlingo.lattice.grid.application.message.Message;
import io.vlingo.wire.fdx.outbound.ApplicationOutboundStream;
import io.vlingo.wire.node.Id;

public interface GridActorControl {

  <T> void start(
          final Id recipient,
          final Id sender,
          final Class<T> protocol,
          final Address address,
          final Definition.SerializationProxy definitionProxy);

  <T> void deliver(
          final Id recipient,
          final Id sender,
          final Returns<?> returns,
          final Class<T> protocol,
          final Address address,
          final Definition.SerializationProxy definitionProxy,
          final SerializableConsumer<T> consumer,
          final String representation);

  <T> void answer(
          final Id receiver,
          final Id sender,
          final Answer<T> answer);

  void forward(final Id receiver, final Id sender, final Message message);

  void relocate(
          final Id receiver,
          final Id sender,
          final Definition.SerializationProxy definitionProxy,
          final Address address,
          final Object snapshot,
          final List<? extends io.vlingo.actors.Message> pending);

  void disburse(final Id id);

  interface Inbound extends GridActorControl {
  }

  interface Outbound extends GridActorControl {
    void useStream(final ApplicationOutboundStream outbound);
  }
}
