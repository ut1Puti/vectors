package vectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 *
 * @author
 * @version
 */
public class Vector3dTests {
    /**
     *
     *
     * @param vector
     * @param coordinateIndex
     * @param expectedValue
     */
    @ParameterizedTest(name = "{0}.{1} = {2}")
    @MethodSource("provideGetCoordinateTestData")
    public void testVector3dGetCoordinate(Vector3d vector, int coordinateIndex, double expectedValue) {
        assertEquals(expectedValue, vector.getCoordinate(coordinateIndex));
    }

    /**
     *
     */
    @Test
    public void testVector3dGetDimension() {
        Vector3d vector3d = new Vector3d(0, 0, 1);
        assertEquals(3, vector3d.getDimension());
    }

    /**
     *
     */
    @Test
    public void testVector3dGetCoordinateIllegalArguments() {
        Vector3d vector3d = new Vector3d(1, 3, 54);
        assertThrows(IllegalArgumentException.class, () -> vector3d.getCoordinate(-2));
        assertThrows(IllegalArgumentException.class, () -> vector3d.getCoordinate(10000));
    }

    /**
     * @param firstVector
     * @param secondVector
     * @param expectedVector
     */
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @MethodSource("provideAddTestData")
    public void testVector3dAdd(Vector3d firstVector, Vector3d secondVector, Vector3d expectedVector) {
        assertEquals(expectedVector, firstVector.add(secondVector));
    }

    /**
     *
     */
    @Test
    public void testVector3dAddNull() {
        assertThrows(IllegalArgumentException.class, () -> new Vector3d(1, 2, 0).add(null));
    }

    /**
     * @param vector
     * @param scalar
     * @param expectedVector
     */
    @ParameterizedTest(name = "{0} * {1} = {2}")
    @MethodSource("provideMultiplyByScalarData")
    public void testVector3dMultiplyByScalar(Vector3d vector, double scalar, Vector3d expectedVector) {
        assertEquals(expectedVector, vector.multiplyByScalar(scalar));
    }

    /**
     * @param firstVector
     * @param secondVector
     * @param expectedVector
     */
    @ParameterizedTest(name = "{0} - {1} = {2}")
    @MethodSource("provideMinusTestData")
    public void testVector3dMinus(Vector3d firstVector, Vector3d secondVector, Vector3d expectedVector) {
        assertEquals(expectedVector, firstVector.minus(secondVector));
    }

    /**
     *
     */
    @Test
    public void testVector3dMinusNull() {
        assertThrows(IllegalArgumentException.class, () -> new Vector3d(1, 2, 0).minus(null));
    }

    /**
     * @param firstVector
     * @param secondVector
     * @param expectedResult
     */
    @ParameterizedTest(name = "{0} * {1} = {2}")
    @MethodSource("provideDotProductTestData")
    public void testVector3dDotProduct(Vector3d firstVector, Vector3d secondVector, double expectedResult) {
        assertEquals(expectedResult, firstVector.dotProduct(secondVector));
    }

    /**
     *
     */
    @Test
    public void testVector3dDotProductNull() {
        assertThrows(IllegalArgumentException.class, () -> new Vector3d(1, 2, 0).dotProduct(null));
    }

    /**
     *
     *
     * @param firstVector
     * @param secondVector
     * @param expectedResult
     */
    @ParameterizedTest(name = "coordinate ratio {0} and {1} is {2}")
    @MethodSource("provideCompareByCoordinateTestData")
    public void testVector3dCompareByCoordinate(Vector3d firstVector, Vector3d secondVector, List<Integer> expectedResult) {
        assertEquals(expectedResult.get(0), firstVector.compareByCoordinate(secondVector, 0));
        assertEquals(expectedResult.get(1), firstVector.compareByCoordinate(secondVector, 1));
        assertEquals(expectedResult.get(2), firstVector.compareByCoordinate(secondVector, 2));
    }

    /**
     *
     */
    @Test
    public void testVector3dCompareByCoordinateIllegalArguments() {
        Vector3d vector3d = new Vector3d(1, 1, 1);
        Vector3d vector3d1 = new Vector3d(4, 5, -1);
        assertThrows(IllegalArgumentException.class, () -> vector3d.compareByCoordinate(null, 1));
        assertThrows(IllegalArgumentException.class, () -> vector3d.compareByCoordinate(null, -1));
        assertThrows(IllegalArgumentException.class, () -> vector3d.compareByCoordinate(null, 4));
        assertThrows(IllegalArgumentException.class, () -> vector3d.compareByCoordinate(vector3d1, 4));
        assertThrows(IllegalArgumentException.class, () -> vector3d.compareByCoordinate(vector3d1, -4));
    }

    /**
     *
     *
     * @return
     */
    private static Stream<Arguments> provideGetCoordinateTestData() {
        return Stream.of(
                Arguments.of(new Vector3d(1, 2, 3), 0, 1),
                Arguments.of(new Vector3d(1, 2, 3), 1, 2),
                Arguments.of(new Vector3d(1, 2, 3), 2, 3)
        );
    }

    /**
     *
     *
     * @return
     */
    private static Stream<Arguments> provideAddTestData() {
        return Stream.of(
                Arguments.of(new Vector3d(1, 2, 3), new Vector3d(3, 2, 1), new Vector3d(4, 4, 4))
        );
    }

    /**
     *
     *
     * @return
     */
    private static Stream<Arguments> provideMultiplyByScalarData() {
        return Stream.of(
                Arguments.of(new Vector3d(1, 2, 3), -1, new Vector3d(-1, -2, -3)),
                Arguments.of(new Vector3d(1, 2, 8), 2.5, new Vector3d(2.5, 5, 20)),
                Arguments.of(new Vector3d(4, 3, 7), 10, new Vector3d(40, 30, 70)),
                Arguments.of(new Vector3d(1000000087, 274832723, 384238), 0, new Vector3d(0, 0, 0))
        );
    }

    /**
     *
     *
     * @return
     */
    private static Stream<Arguments> provideMinusTestData() {
        return Stream.of(
                Arguments.of(new Vector3d(1, 2, 3), new Vector3d(3, 2, 1), new Vector3d(-2, 0, 2))
        );
    }

    /**
     *
     *
     * @return
     */
    private static Stream<Arguments> provideDotProductTestData() {
        return Stream.of(
                Arguments.of(new Vector3d(1, 3, -5), new Vector3d(4, -2, -1), 3)
        );
    }

    /**
     *
     *
     * @return
     */
    private static Stream<Arguments> provideCompareByCoordinateTestData() {
        return Stream.of(
                Arguments.of(new Vector3d(1, 3, -5), new Vector3d(4, -2, -1), List.of(-1, 1, -1))
        );
    }
}
