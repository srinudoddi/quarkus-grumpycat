package org.wanja.fatcat;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.wanja.fatcat.model.EnemyAction;
import org.wanja.fatcat.model.PlayerAction;

import io.quarkus.logging.Log;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
//@Path("/state-collector")
public class GameStateUpdateCollector {
    
    @Channel("player-actions")
    Emitter<PlayerAction> actionEmitter;


    @Incoming("incoming-states")
    @Outgoing("outgoing-states")
    boolean collectPlayer(PlayerAction action) {
        System.out.println("collectPlayer() " + action.toString());
        if( action.gameId == null || action.playerId == null ) {
            Log.info("Skipping game state action, because gameId || playerId is NULL");
            return false;
        }
        else {
            actionEmitter.send(action);
            Log.info("Sending game action to Kafka");
            return true;
        }
    }
}
