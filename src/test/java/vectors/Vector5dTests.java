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
 * Класс тестирующий пятимерные вектора
 *
 * @author Кедровских Олег
 * @version 1.0
 */
public class Vector5dTests {
    /**
     * Метод тестирующий получение координаты пятимерного ветора
     *
     * @param vector          вектор
     * @param coordinateIndex индекс получаемой координаты
     * @param expectedValue   ожидаемое значение координаты
     */
    @ParameterizedTest(name = "{0}.{1} = {2}")
    @MethodSource("provideGetCoordinateTestData")
    public void testVector5dGetCoordinate(Vector5d vector, int coordinateIndex, double expectedValue) {
        assertEquals(expectedValue, vector.getCoordinate(coordinateIndex));
    }

    /**
     * Метод тестирующий получение размерности вектор
     */
    @Test
    public void testVector5dGetDimension() {
        Vector5d vector5d = new Vector5d(0, 0, 1, 1, 0);
        assertEquals(5, vector5d.getDimension());
    }

    /**
     * Метод тестирующий получение координаты вектора по несуществующей координате
     */
    @Test
    public void testVector3dGetCoordinateIllegalArguments() {
        Vector5d vector5d = new Vector5d(1, 3, 54, 10, -15);
        assertThrows(IllegalArgumentException.class, () -> vector5d.getCoordinate(-2));
        assertThrows(IllegalArgumentException.class, () -> vector5d.getCoordinate(10000));
    }

    /**
     * Метод тестирующий сложение пятимерных векторов
     *
     * @param firstVector    первый вектор
     * @param secondVector   второй вектор
     * @param expectedVector ожидаемый вектор
     */
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @MethodSource("provideAddTestData")
    public void testVector5dAdd(Vector5d firstVector, Vector5d secondVector, Vector5d expectedVector) {
        assertEquals(expectedVector, firstVector.add(secondVector));
    }

    /**
     * Метод тестирующий сложение с {@code null}
     */
    @Test
    public void testVector5dAddNull() {
        assertThrows(IllegalArgumentException.class, () -> new Vector5d(1, 2, 0, 8, 9).add(null));
    }

    /**
     * Метод тестирующий множение пятимерного вектора на скаляр
     *
     * @param vector         вектор
     * @param scalar         скаляр
     * @param expectedVector ожидаемый вектор
     */
    @ParameterizedTest(name = "{0} * {1} = {2}")
    @MethodSource("provideMultiplyByScalarData")
    public void testVector5dMultiplyByScalar(Vector5d vector, double scalar, Vector5d expectedVector) {
        assertEquals(expectedVector, vector.multiplyByScalar(scalar));
    }

    /**
     * Метод тестирующий вычитание пятимерных векторов
     *
     * @param firstVector    первый вектор
     * @param secondVector   второй вектор
     * @param expectedVector ожиаемый вектор
     */
    @ParameterizedTest(name = "{0} - {1} = {2}")
    @MethodSource("provideMinusTestData")
    public void testVector5dMinus(Vector5d firstVector, Vector5d secondVector, Vector5d expectedVector) {
        assertEquals(expectedVector, firstVector.minus(secondVector));
    }

    /**
     * Метод тестирующий вычитание {@code null}
     */
    @Test
    public void testVector5dMinusNull() {
        assertThrows(IllegalArgumentException.class, () -> new Vector5d(1, 2, 0, 4, 18).minus(null));
    }

    /**
     * Метод тестирующий скалярное произведение пятимерных векторов
     *
     * @param firstVector    первый вектор
     * @param secondVector   второй вектор
     * @param expectedResult ожидаемый вектор
     */
    @ParameterizedTest(name = "{0} * {1} = {2}")
    @MethodSource("provideDotProductTestData")
    public void testVector5dDotProduct(Vector5d firstVector, Vector5d secondVector, double expectedResult) {
        assertEquals(expectedResult, firstVector.dotProduct(secondVector));
    }

    /**
     * Метод тестирующий скалярное произведение с {@code null}
     */
    @Test
    public void testVector5dDotProductNull() {
        assertThrows(IllegalArgumentException.class, () -> new Vector5d(2, 1, 0, -1, -2).dotProduct(null));
    }

    /**
     * Метод тестирующий сравнение пятимерного вектора по координатам
     *
     * @param firstVector    первый вектор
     * @param secondVector   второй вектор
     * @param expectedResult ожидаемое значение сравнения
     */
    @ParameterizedTest(name = "coordinate ratio {0} and {1} is {2}")
    @MethodSource("provideCompareByCoordinateTestData")
    public void testVector5dCompareByCoordinate(Vector5d firstVector, Vector5d secondVector, List<Integer> expectedResult) {
        assertEquals(expectedResult.get(0), firstVector.compareByCoordinate(secondVector, 0));
        assertEquals(expectedResult.get(1), firstVector.compareByCoordinate(secondVector, 1));
        assertEquals(expectedResult.get(2), firstVector.compareByCoordinate(secondVector, 2));
        assertEquals(expectedResult.get(3), firstVector.compareByCoordinate(secondVector, 3));
        assertEquals(expectedResult.get(4), firstVector.compareByCoordinate(secondVector, 4));
    }

    /**
     * Метод тестирующий сравнение некорекктных параметров при сравнении
     */
    @Test
    public void testVector5dCompareByCoordinateIllegalArguments() {
        Vector5d vector5d = new Vector5d(1, 1, 1, 1, 1);
        Vector5d vector5d1 = new Vector5d(4, 5, -1, 0, 9);
        assertThrows(IllegalArgumentException.class, () -> vector5d.compareByCoordinate(null, 1));
        assertThrows(IllegalArgumentException.class, () -> vector5d.compareByCoordinate(null, -1));
        assertThrows(IllegalArgumentException.class, () -> vector5d.compareByCoordinate(null, 6));
        assertThrows(IllegalArgumentException.class, () -> vector5d.compareByCoordinate(vector5d1, 6));
        assertThrows(IllegalArgumentException.class, () -> vector5d.compareByCoordinate(vector5d1, -4));
    }

    /**
     * Метод данных для теста получения координаты
     *
     * @return {@code stream} данных для теста
     */
    private static Stream<Arguments> provideGetCoordinateTestData() {
        return Stream.of(
                Arguments.of(new Vector5d(1, 2, 3, 4, 5), 0, 1),
                Arguments.of(new Vector5d(1, 2, 3, 4, 5), 1, 2),
                Arguments.of(new Vector5d(1, 2, 3, 4, 5), 2, 3),
                Arguments.of(new Vector5d(1, 2, 3, 4, 5), 3, 4),
                Arguments.of(new Vector5d(1, 2, 3, 4, 5), 4, 5)
        );
    }

    /**
     * Метод данных для тестов сложения
     *
     * @return {@code stream} данных для теста
     */
    private static Stream<Arguments> provideAddTestData() {
        return Stream.of(
                Arguments.of(new Vector5d(1, 2, 3, 17, -9), new Vector5d(3, 2, 1, -13, 13), new Vector5d(4, 4, 4, 4, 4))
        );
    }

    /**
     * Метод данных для тестов умножения на скаляр
     *
     * @return {@code stream} данных для теста
     */
    private static Stream<Arguments> provideMultiplyByScalarData() {
        return Stream.of(
                Arguments.of(new Vector5d(1, 2, 3, 4, 5), -1, new Vector5d(-1, -2, -3, -4, -5)),
                Arguments.of(new Vector5d(1, 2, 8, 4, -2), 2.5, new Vector5d(2.5, 5, 20, 10, -5)),
                Arguments.of(new Vector5d(4, 3, 7, 1, 0), 10, new Vector5d(40, 30, 70, 10, 0)),
                Arguments.of(new Vector5d(1000000087, 274832723, 384238, -7879, -43728937), 0, new Vector5d(0, 0, 0, 0, 0))
        );
    }

    /**
     * Метод данных для тестов вычитания
     *
     * @return {@code stream} данных для теста
     */
    private static Stream<Arguments> provideMinusTestData() {
        return Stream.of(
                Arguments.of(new Vector5d(1, 2, 3, 4, 5), new Vector5d(3, 2, 1, 0, -1), new Vector5d(-2, 0, 2, 4, 6))
        );
    }

    /**
     * Метод данных для тестов скалярного произведения
     *
     * @return {@code stream} данных для теста
     */
    private static Stream<Arguments> provideDotProductTestData() {
        return Stream.of(
                Arguments.of(new Vector5d(1, 3, -5, 14, 4), new Vector5d(4, -2, -1, 6, 9), 123)
        );
    }

    /**
     * Метод данных для тестов сравнения по координатам
     *
     * @return {@code stream} данных для теста
     */
    private static Stream<Arguments> provideCompareByCoordinateTestData() {
        return Stream.of(
                Arguments.of(new Vector5d(1, 3, -5, 0, 19), new Vector5d(4, -2, -1, 0, 100), List.of(-1, 1, -1, 0, -1))
        );
    }
}
