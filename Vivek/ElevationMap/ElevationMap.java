package ElevationMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


/**
 * Imagine standing at an elevation,
 * <p>
 * The water that can be stored at that exact elevation would be between the highest elevation on the left and the highest elevation on the right.
 * Needless to say, the height would be minimum of max left and max right.
 * <p>
 * Thus, total amount at any elevation = min(max left, max right) - current elevation.
 * <p>
 * Of course, total amount at any elevation would never be negative.
 * <p>
 * Hence, final formula for total amount at any elevation = max ( min(max left, max right) - current elevation, 0)
 * <p>
 * Total amount of water filled would be sum of total amount at all the elevations.
 */
public class ElevationMap {

    public static void main(String[] args) {
        int[] elevationMap = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        List<Integer> leftMax = getLeftMax(elevationMap);
        List<Integer> rightMax = getRightMax(elevationMap);

        int waterFilled = IntStream.range(0, elevationMap.length)
                .map(index -> max(0, min(leftMax.get(index), rightMax.get(index)) - elevationMap[index]))
                .sum();

        System.out.println(waterFilled);

    }

    private static List<Integer> getLeftMax(int[] elevationMap) {
        List<Integer> leftMax = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < elevationMap.length; i++) {
            leftMax.add(max);
            if (max < elevationMap[i]) {
                max = elevationMap[i];
            }
        }

        return leftMax;
    }

    private static List<Integer> getRightMax(int[] elevationMap) {
        List<Integer> rightMax = new ArrayList<>();
        int max = 0;
        for (int i = elevationMap.length - 1; i >= 0; i--) {
            rightMax.add(0, max);
            if (max < elevationMap[i]) {
                max = elevationMap[i];
            }
        }

        return rightMax;
    }

    private static int max(int a, int b) {
        return a > b ? a : b;
    }

    private static int min(int a, int b) {
        return a > b ? b : a;
    }


}


