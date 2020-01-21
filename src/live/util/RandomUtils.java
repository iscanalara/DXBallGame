package live.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by dindar.oz on 9/29/2017.
 */
public class RandomUtils {
    static Random rng = new SecureRandom();

    public static double randomBinomial()
    {
        return  -1 + rng.nextDouble()*2; //rng.nextDouble()-rng.nextDouble();
    }
}
