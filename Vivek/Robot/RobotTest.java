package Robot;

public class RobotTest {

    public static void main(String[] args) {
        UniquePath uniquePath = new UniquePath();
        testNullArray(uniquePath);
        test3X4Array(uniquePath);

    }

    private static void testNullArray(UniquePath uniquePath) {
        try {
            uniquePath.findUniquePaths(null);
        } catch (RuntimeException e) {
            assert "Null Grid Array or Invalid Robot.Grid Array Length".equals(e.getMessage());
            return;
        }

        assert false;
    }

    private static void test3X4Array(UniquePath uniquePath) {

        int[][] gridArray = {{0, 0, 0, 0}, {1, 0, 0, 0}, {0, 1, 0, 0}};
        assert 5 == uniquePath.findUniquePaths(gridArray);

    }

}
