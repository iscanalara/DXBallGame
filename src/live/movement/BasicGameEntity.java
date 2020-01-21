package live.movement;

import live.gameEngine.Game;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class BasicGameEntity  implements GameEntity {

    StaticInfo staticInfo;
    Renderable body;


    public BasicGameEntity(StaticInfo staticInfo, Renderable body) {
        this.staticInfo = staticInfo;
        this.body = body;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {

        body.render(staticInfo, graphics);


    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, float time) {

    }
}
