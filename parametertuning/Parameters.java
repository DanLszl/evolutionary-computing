package parametertuning;

/**
 * This class can be used for getting external parameters, so the same algorithm can be run several times
 * with different parameters.
 *
 * To use this, first Build from IDEA, and then run the parameter_tuning.py file from terminal
 * */
public class Parameters {

    public static Integer getpatience() {
        try {
            return Integer.parseInt(System.getProperty("shockPatience"));
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }

    public static Boolean getuseShockingForTournament() {
        String raw = System.getProperty("useShockingForTournament");
        return raw == null ? null : Boolean.parseBoolean(raw);
    }
    public static Boolean getuseShockingForMutation() {
        String raw = System.getProperty("useShockingForMutation");
        return raw == null ? null : Boolean.parseBoolean(raw);
    }

    public static Integer getshockInterval() {
        try {
            return Integer.parseInt(System.getProperty("shockInterval"));
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }

    }

    public static Integer getpopulationSize() {
        try {
            return Integer.parseInt(System.getProperty("populationSize"));
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }
    public static Double getblendAlpha() {
        try {
            return Double.parseDouble(System.getProperty("blendAlpha"));
        } catch (NullPointerException | NumberFormatException e) {
            return null;
        }
    }
    public static Double getsigmaThreshold() {
        try {
            return Double.parseDouble(System.getProperty("sigmaThreshold"));
        } catch (NullPointerException | NumberFormatException e) {
            return null;
        }
    }
    public static Integer gettournamentSizeStart() {
        try {
            return Integer.parseInt(System.getProperty("tournamentSizeStart"));
        } catch (NumberFormatException e) {
            return null;
        }
    }
    public static Integer gettournamentSizeEnd() {
        try {
            return Integer.parseInt(System.getProperty("tournamentSizeEnd"));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer gettournamentGenerations() {
        try {
            return Integer.parseInt(System.getProperty("tournamentGenerations"));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
