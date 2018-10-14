package parametertuning;

/**
 * This class can be used for getting external parameters, so the same algorithm can be run several times
 * with different parameters.
 *
 * To use this, first Build from IDEA, and then run the
 * */
public class Parameters {
    public static int getDummy() {
        return Integer.parseInt(System.getProperty("dummy"));
    }

    public static int getDummy2() {
        return Integer.parseInt(System.getProperty("dummy2"));
    }
}
