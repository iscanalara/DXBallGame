package live.movement.steering;

import live.movement.Acceleration;
import live.movement.StaticInfo;
import live.movement.Velocity;

public interface SteeringBehavior {

    Acceleration getSteering(StaticInfo staticInfo, Velocity velocity);
}
