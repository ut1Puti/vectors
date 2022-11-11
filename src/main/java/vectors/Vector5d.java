package vectors;

/**
 * Класс пятимерных векторов
 *
 * @author Кедровских Олег
 * @version 1.0
 */
public class Vector5d extends AbstractVector<Vector5d> {
    /**
     * Поле массива координат пятимерного вектора
     */
    private final double[] coordinates;

    /**
     * Конструктор - создает экземпляр классов
     *
     * @param a1 первая координата
     * @param a2 вторая координата
     * @param a3 третья координата
     * @param a4 четвертая координата
     * @param a5 пятая координата
     */
    public Vector5d(double a1, double a2, double a3, double a4, double a5) {
        this.coordinates = new double[]{a1, a2, a3, a4, a5};
    }

    /**
     * Конструктор - создает экземляр класс на основе массива координат вектора
     *
     * @param coordinates массив координат вектора
     */
    private Vector5d(double[] coordinates) {

        if (coordinates.length != 5) {
            throw new IllegalStateException("");
        }

        this.coordinates = coordinates;
    }

    /**
     * Метод получающий координату вектора
     *
     * @param coordinateIndex номер координаты, которую необходимо получить их вектора,
     *                        номер координаты должен быть в машинном представлении, то есть как индексы массива
     * @return координату вектора по индексу. Пример {1, 2, 3, 4, 5} индексы координат соодтвествуют числам - 1
     */
    @Override
    public double getCoordinate(int coordinateIndex) {

        if (coordinateIndex < 0 || coordinateIndex >= coordinates.length) {
            throw new IllegalArgumentException("");
        }

        return coordinates[coordinateIndex];
    }

    /**
     * Метод получающий размерность вектора
     *
     * @return {@code 5}
     */
    @Override
    public int getDimension() {
        return 5;
    }

    /**
     * Метод выполняющий сложение пятимерных векторов векторов
     *
     * @param anotherVector вектор, который является вторым аргументом операции сложения
     * @return новый пятимерный вектор являющийся результатом сложения двух пятимерных векторов
     */
    @Override
    public Vector5d add(Vector5d anotherVector) {

        if (anotherVector == null) {
            throw new IllegalArgumentException("");
        }

        double[] newVector5dCoordinate = this.addCoordinates(anotherVector);
        return new Vector5d(newVector5dCoordinate);
    }

    /**
     * Метод выполняющий умножение пятимерного вектора на скаляр
     *
     * @param scalar значение скаляра
     * @return новый пятимерный вектор являющийся результатом умножения пятимерного вектора на скаляр
     */
    @Override
    public Vector5d multiplyByScalar(double scalar) {
        double[] newVector5dCoordinates = this.multiplyCoordinatesOnScalar(scalar);
        return new Vector5d(newVector5dCoordinates);
    }
}
