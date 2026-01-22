/**
 * The class WeatherPatterns finds the longest span of days in which
 * each day’s temperature is higher than on the previous day in that sequence.
 *
 * @author Zach Blick
 * @author Vikram Saluja
 */

public class WeatherPatterns {
    /**
     * Longest Warming Trend
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */
    public static int longestWarmingTrend(int[] temperatures) {

        // Edge case so if there are no days, then that mean there is no warming trend
        if (temperatures == null || temperatures.length == 0) {
            return 0;
        }


        int numDays = temperatures.length;

        // Memoization[i] stores the value of LongestPathTo(i)
        int[] memoization = new int[numDays];

        // Tracks the overall longest path in the directed graph
        int best = 1;
        // Compute the longest path ending at each vertex i
        // The longest warming trend is the max of the values
        for (int i = 0; i < numDays; i++) {
            best = Math.max(best, longestPathTo(i, temperatures, memoization));
        }
        return best;
    }

    public static int longestPathTo(int i, int[] temps, int[] memoization){

        // already computed
        if (memoization[i] != 0) {
            return memoization[i];
        }

        // A path contains only this vertex and has a length of 1
        int path = 1;

        // Consider all vertices j that could have an edge of j to i
        for (int j = 0; j < i; j++) {
            // Edge exists in our directer graph
            if (temps[j] < temps[i]) {
                // Recursively compute the longest path to j, then extend that path by 1 in order to include i.
                path = Math.max(path, longestPathTo(j, temps, memoization) + 1);
            }
        }

        // Save the computed result to avoid recomputation
        memoization[i] = path;
        return path;
    }
}
