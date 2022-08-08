import SpiderEnemy from "../spider-enemy";
import { EnemyReplayer } from "./enemy-replayer";

export class RemoteSpiderSprite extends SpiderEnemy {
	constructor(x, y, actions) {
		super(x, y);

		this.replayer = new EnemyReplayer(this, actions);
	}

	updatePosition(dt) {
		this.replayer.playNext(dt);
	}
}
