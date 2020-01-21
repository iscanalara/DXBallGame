package live.movement;

import math.geom2d.Vector2D;

public class StaticInfo {
    Vector2D pos;
    double orientation;

    OrientationType orientationType;

    public StaticInfo(Vector2D pos, double orientation, OrientationType orientationType) {
        this.pos = pos;
        this.orientation = orientation;
        this.orientationType = orientationType;
    }

    public Vector2D getPos() {
        return pos;
    }

    public void setPos(Vector2D pos) {
        this.pos = pos;
    }

    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }


    public void update(Velocity velocity, Acceleration acceleration, float time)
    {
        pos = pos.plus(velocity.getLinear().times(time));
        Vector2D finalLinearVelocity = acceleration.linear;


        if (acceleration.getAccelerationType()== AccelerationType.Dynamic)
        {
            pos = pos.plus(acceleration.getLinear().times(0.5*time*time));
            finalLinearVelocity = velocity.linear.plus(acceleration.linear.times(time));
        }




        if (orientationType == OrientationType.VelocityBased)
            adjuctOrientation(finalLinearVelocity);
        else {

            orientation = orientation + velocity.rotation*time;

            if (acceleration.getAccelerationType()== AccelerationType.Dynamic)
                orientation += acceleration.angular*0.5*time*time;
        }



    }


    private void adjuctOrientation(Vector2D finalLinearVelocity) {
        orientation = Math.atan2(finalLinearVelocity.y(),finalLinearVelocity.x());
    }

}
