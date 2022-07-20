package org.wanja.fatcat;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.wanja.fatcat.model.PlayerAction;

import io.smallrye.reactive.messaging.annotations.Blocking;

@ApplicationScoped
public class PlayerMovementProcessor {
    
    @Incoming("player-actions")
    @Blocking
    @Transactional
    public void processPlayerAction(PlayerAction action) {
        action.id = null;
        action.persist();
    }

}
