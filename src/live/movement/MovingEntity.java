package live.movement;

import live.movement.steering.SteeringBehavior;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class MovingEntity extends BasicGameEntity {


    private static final float TIME_COEFFICIENT = 1f;
    Velocity velocity;

    Acceleration acceleration = Acceleration.NoAcceleration;

    public void setSteeringBehavior(SteeringBehavior steeringBehavior) {
        this.steeringBehavior = steeringBehavior;
    }

    SteeringBehavior steeringBehavior;


    public MovingEntity(StaticInfo staticInfo, Renderable body) {
        super(staticInfo, body);
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public void setAcceleration(Acceleration acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        super.init(gameContainer, stateBasedGame);
    }



    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, float time) {
        super.update(gameContainer, stateBasedGame, time);

        if (steeringBehavior != null)
            acceleration = steeringBehavior.getSteering(staticInfo,velocity);

        time = time*TIME_COEFFICIENT;



        staticInfo.update(velocity,acceleration,time);

        velocity.update(acceleration,time);





    }
}
