package live.util;

import math.geom2d.Vector2D;

/**
 * Created by dindar.oz on 9/22/2017.
 */
public class VectorUtils {
    public static Vector2D scale(Vector2D v, double max) {
        if (v.norm()<=max)
            return v.clone();
        else {
            Vector2D scaled =v.normalize();
            scaled = scaled.times(max);
            return scaled;
        }
    }

    public static double distance(Vector2D node, Vector2D position) {
        return node.minus(position).norm();
    }
}
