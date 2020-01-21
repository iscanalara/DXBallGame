package live.movement.steering;

import live.movement.Acceleration;
import live.movement.AccelerationType;
import live.movement.StaticInfo;
import live.movement.Velocity;
import math.geom2d.Vector2D;

public class KinematicSeek  implements SteeringBehavior{

    Vector2D targetPosition;
    float maxSpeed;

    public KinematicSeek(Vector2D targetPosition, float maxSpeed) {
        this.targetPosition = targetPosition;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public Acceleration getSteering(StaticInfo staticInfo, Velocity velocity) {

        Vector2D finalVelocity = targetPosition.minus(staticInfo.getPos());

        if (finalVelocity.norm()>maxSpeed)
            finalVelocity = finalVelocity.normalize().times(maxSpeed);

        return new Acceleration(finalVelocity,0, AccelerationType.Kinematic);
    }
}
