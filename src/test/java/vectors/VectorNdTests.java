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
 * Класс тестирующий n-мерные вектора
 *
 * @author Кедровских Олег
 * @version 1.0
 */
public class VectorNdTests {
    /**
     * Метод тестирующий получение координаты n-мерного ветора
     *
     * @param vector          вектор
     * @param coordinateIndex индекс получаемой координаты
     * @param expectedValue   ожидаемое значение координаты
     */
    @ParameterizedTest(name = "{0}.{1} = {2}")
    @MethodSource("provideGetCoordinateTestData")
    public void testVectorNdGetCoordinate(VectorNd vector, int coordinateIndex, double expectedValue) {
        assertEquals(expectedValue, vector.getCoordinate(coordinateIndex));
    }

    /**
     * Метод тестирующий получение координаты вектора по несуществующей координате
     */
    @Test
    public void testVectorNdGetCoordinateIllegalArguments() {
        VectorNd vectorNd = new VectorNd(1, 3, 54, 10, -15);
        assertThrows(IllegalArgumentException.class, () -> vectorNd.getCoordinate(-2));
        assertThrows(IllegalArgumentException.class, () -> vectorNd.getCoordinate(10000));
    }

    /**
     * Метод тестирующий получение размерности n-мерного вектора
     *
     * @param vectorNd              вектор
     * @param expectedDimensionSize ожидаемая размерность
     */
    @ParameterizedTest(name = "dim({0}) = {1}")
    @MethodSource("provideGetDimensionTestData")
    public void testVectorNdGetDimension(VectorNd vectorNd, int expectedDimensionSize) {
        assertEquals(expectedDimensionSize, vectorNd.getDimension());
    }

    /**
     * Метод тестирующий сложение n-мерных векторов
     *
     * @param firstVector    первый вектор
     * @param secondVector   второй вектор
     * @param expectedVector ожидаемый вектор
     */
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @MethodSource("provideAddTestData")
    public void testVectorNdAdd(VectorNd firstVector, VectorNd secondVector, VectorNd expectedVector) {
        assertEquals(expectedVector, firstVector.add(secondVector));
    }

    /**
     * Метод тестирующий сложение с {@code null}
     */
    @Test
    public void testVectorNdAddNull() {
        assertThrows(IllegalArgumentException.class, () -> new VectorNd(1).add(null));
    }

    /**
     * Метод тестирующий сложение векторов разной размерности
     */
    @Test
    public void testVectorNdDiffDimensionsAdd() {
        assertThrows(IllegalArgumentException.class, () -> new VectorNd(1, 2).add(new VectorNd(4, 5, 6)));
    }

    /**
     * Метод тестирующий множение n-мерного вектора на скаляр
     *
     * @param vector         вектор
     * @param scalar         скаляр
     * @param expectedVector ожидаемый вектор
     */
    @ParameterizedTest(name = "{0} * {1} = {2}")
    @MethodSource("provideMultiplyByScalarData")
    public void testVectorNdMultiplyByScalar(VectorNd vector, double scalar, VectorNd expectedVector) {
        assertEquals(expectedVector, vector.multiplyByScalar(scalar));
    }

    /**
     * Метод тестирующий вычитание n-мерных векторов
     *
     * @param firstVector    первый вектор
     * @param secondVector   второй вектор
     * @param expectedVector ожиаемый вектор
     */
    @ParameterizedTest(name = "{0} - {1} = {2}")
    @MethodSource("provideMinusTestData")
    public void testVectorNdMinus(VectorNd firstVector, VectorNd secondVector, VectorNd expectedVector) {
        assertEquals(expectedVector, firstVector.minus(secondVector));
    }

    /**
     * Метод тестирующий вычитание векторов разной размерности
     */
    @Test
    public void testVectorNdDiffDimensionsMinus() {
        assertThrows(IllegalArgumentException.class, () -> new VectorNd(1, 2).minus(new VectorNd(4, 5, 6)));
    }

    /**
     * Метод тестирующий вычитание {@code null}
     */
    @Test
    public void testVectorNdMinusNull() {
        assertThrows(IllegalArgumentException.class, () -> new VectorNd(1, 2, 0, 4, 18, 89890, 76, 43, 54).minus(null));
    }

    /**
     * Метод тестирующий скалярное произведение n-мерных векторов
     *
     * @param firstVector    первый вектор
     * @param secondVector   второй вектор
     * @param expectedResult ожидаемый вектор
     */
    @ParameterizedTest(name = "{0} * {1} = {2}")
    @MethodSource("provideDotProductTestData")
    public void testVectorNdDotProduct(VectorNd firstVector, VectorNd secondVector, double expectedResult) {
        assertEquals(expectedResult, firstVector.dotProduct(secondVector));
    }

    /**
     * Метод тестирующий вычитание векторов разной размерности
     */
    @Test
    public void testVectorNdDiffDimensionsDotProduct() {
        assertThrows(IllegalArgumentException.class, () -> new VectorNd(1, 2).dotProduct(new VectorNd(4, 5, 6)));
    }

    /**
     * Метод тестирующий скалярное произведение с {@code null}
     */
    @Test
    public void testVectorNdDotProductNull() {
        assertThrows(IllegalArgumentException.class, () -> new VectorNd(2, 1, -1, -2).dotProduct(null));
    }

    /**
     * Метод тестирующий сравнение n-мерного вектора по координатам
     *
     * @param firstVector    первый вектор
     * @param secondVector   второй вектор
     * @param expectedResult ожидаемое значение сравнения
     */
    @ParameterizedTest(name = "coordinate ratio {0} and {1} is {2}")
    @MethodSource("provideCompareByCoordinateTestData")
    public void testVectorNdCompareByCoordinate(VectorNd firstVector, VectorNd secondVector, List<Integer> expectedResult) {
        for (int i = 0; i < firstVector.getDimension(); i++) {
            assertEquals(expectedResult.get(i), firstVector.compareByCoordinate(secondVector, i));
        }
    }

    /**
     * Метод тестирующий сравнение векторов разной размерности
     */
    @Test
    public void testVectorNdDiffDimensionsCompareByCoordinate() {
        assertThrows(IllegalArgumentException.class, () -> new VectorNd(1, 2).compareByCoordinate(new VectorNd(4, 5, 6), 0));
    }

    /**
     * Метод тестирующий сравнение некорекктных параметров при сравнении
     */
    @Test
    public void testVectorNdCompareByCoordinateIllegalArguments() {
        VectorNd vectorNd = new VectorNd(1, 1, 1);
        VectorNd vectorNd1 = new VectorNd(4, 5, -1);
        assertThrows(IllegalArgumentException.class, () -> vectorNd.compareByCoordinate(null, 1));
        assertThrows(IllegalArgumentException.class, () -> vectorNd.compareByCoordinate(null, -1));
        assertThrows(IllegalArgumentException.class, () -> vectorNd.compareByCoordinate(null, 6));
        assertThrows(IllegalArgumentException.class, () -> vectorNd.compareByCoordinate(vectorNd1, 6));
        assertThrows(IllegalArgumentException.class, () -> vectorNd.compareByCoordinate(vectorNd1, -4));
    }

    /**
     * Метод данных для тестов размерностей
     *
     * @return {@code stream} данных для теста
     */
    private static Stream<Arguments> provideGetDimensionTestData() {
        return Stream.of(
                Arguments.of(new VectorNd(1, 4, 5, 6, 7, 8), 6),
                Arguments.of(new VectorNd(1, 4), 2)
        );
    }

    /**
     * Метод данных для теста получения координаты
     *
     * @return {@code stream} данных для теста
     */
    private static Stream<Arguments> provideGetCoordinateTestData() {
        return Stream.of(
                Arguments.of(new VectorNd(1, 2, 3, 4, 5), 0, 1),
                Arguments.of(new VectorNd(1, 2, 3, 4, 5), 1, 2),
                Arguments.of(new VectorNd(1, 2, 3, 4, 5), 2, 3),
                Arguments.of(new VectorNd(1, 2, 3, 4, 5), 3, 4),
                Arguments.of(new VectorNd(1, 2, 3, 4, 5), 4, 5)
        );
    }

    /**
     * Метод данных для тестов сложения
     *
     * @return {@code stream} данных для теста
     */
    private static Stream<Arguments> provideAddTestData() {
        return Stream.of(
                Arguments.of(new VectorNd(1, 2, 3, 17, -9), new VectorNd(3, 2, 1, -13, 13), new VectorNd(4, 4, 4, 4, 4))
        );
    }

    /**
     * Метод данных для тестов умножения на скаляр
     *
     * @return {@code stream} данных для теста
     */
    private static Stream<Arguments> provideMultiplyByScalarData() {
        return Stream.of(
                Arguments.of(new VectorNd(1, 2, 3, 4, 5), -1, new VectorNd(-1, -2, -3, -4, -5)),
                Arguments.of(new VectorNd(1, 2, 8, 4, -2), 2.5, new VectorNd(2.5, 5, 20, 10, -5)),
                Arguments.of(new VectorNd(4, 3, 7, 1, 0), 10, new VectorNd(40, 30, 70, 10, 0)),
                Arguments.of(new VectorNd(1000000087, 274832723, 384238), 0, new VectorNd(0, 0, 0))
        );
    }

    /**
     * Метод данных для тестов вычитания
     *
     * @return {@code stream} данных для теста
     */
    private static Stream<Arguments> provideMinusTestData() {
        return Stream.of(
                Arguments.of(new VectorNd(1, 2, 3, 4, 5), new VectorNd(3, 2, 1, 0, -1), new VectorNd(-2, 0, 2, 4, 6))
        );
    }

    /**
     * Метод данных для тестов скалярного произведения
     *
     * @return {@code stream} данных для теста
     */
    private static Stream<Arguments> provideDotProductTestData() {
        return Stream.of(
                Arguments.of(new VectorNd(1, 3, -5, 14, 4), new VectorNd(4, -2, -1, 6, 9), 123)
        );
    }

    /**
     * Метод данных для тестов сравнения по координатам
     *
     * @return {@code stream} данных для теста
     */
    private static Stream<Arguments> provideCompareByCoordinateTestData() {
        return Stream.of(
                Arguments.of(new VectorNd(1, 3, -5, 0, 19), new VectorNd(4, -2, -1, 0, 100), List.of(-1, 1, -1, 0, -1))
        );
    }
}
