package org.wanja.fatcat;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.wanja.fatcat.model.PlayerAction;

import io.quarkus.logging.Log;

@ApplicationScoped
public class GameStateUpdateCollector {
    
    @Channel("player-actions")
    Emitter<PlayerAction> actionEmitter;


    @Incoming("incoming-states")    
    void collectPlayer(PlayerAction action) {        
        if( action.gameId == null || action.playerId == null ) {
            Log.warn("Skipping game state action, because gameId || playerId is NULL");
        }
        else {
            actionEmitter.send(action);
            Log.debug("Sending game action to Kafka");
        }
    }
}
