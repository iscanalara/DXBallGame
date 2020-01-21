package live.movement;

import math.geom2d.Vector2D;

public class Velocity {

    Vector2D linear;
    double rotation;


    public Velocity(Vector2D linear, double rotation) {
        this.linear = linear;
        this.rotation = rotation;
    }

    public Vector2D getLinear() {
        return linear;
    }

    public void setLinear(Vector2D linear) {
        this.linear = linear;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }


    public void update(Acceleration acceleration, float time)
    {
        if (acceleration.getAccelerationType() == AccelerationType.Dynamic)
            linear = linear.plus(acceleration.linear.times(time));
        else linear= acceleration.linear;

        if (acceleration.getAccelerationType() == AccelerationType.Dynamic)
            rotation = rotation + acceleration.getAngular()*time;
        else rotation= acceleration.getAngular();
    }
}
