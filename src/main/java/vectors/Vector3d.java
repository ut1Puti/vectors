package vectors;

/**
 * Класс трехмерных векторов
 *
 * @author Кедровских Олег
 * @version 1.0
 */
public class Vector3d extends AbstractVector<Vector3d> {
    /**
     * Поле первой координаты
     */
    private final double x;
    /**
     * Поле второй координаты
     */
    private final double y;
    /**
     * Поле третьей координаты
     */
    private final double z;

    /**
     * Конструктор - создает экземпляр классов
     *
     * @param x первая координата
     * @param y вторая координата
     * @param z третья координата
     */
    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Конструктор - создает экземляр класс на основе массива координат вектора
     *
     * @param vectorCoordinates массив координат вектора
     */
    private Vector3d(double[] vectorCoordinates) {

        if (vectorCoordinates.length != 3) {
            throw new IllegalStateException("");
        }

        this.x = vectorCoordinates[0];
        this.y = vectorCoordinates[1];
        this.z = vectorCoordinates[2];
    }

    /**
     * Метод получающий координату вектора
     *
     * @param coordinateIndex номер координаты, которую необходимо получить их вектора,
     *                        номер координаты должен быть в машинном представлении, то есть как индексы массива
     * @return координату вектора по индексу. Пример {1, 2, 3} индексы координат соодтвествуют числам - 1
     */
    @Override
    public double getCoordinate(int coordinateIndex) {

        if (coordinateIndex < 0 || coordinateIndex >= 3) {
            throw new IllegalArgumentException("");
        }

        if (coordinateIndex == 0) {
            return x;
        } else if (coordinateIndex == 1) {
            return y;
        } else {
            return z;
        }
    }

    /**
     * Метод получающий размерность вектора
     *
     * @return {@code 3}
     */
    @Override
    public int getDimension() {
        return 3;
    }

    /**
     * Метод выполняющий сложение трехмерных векторов
     *
     * @param anotherVector вектор, который является вторым аргументом операции сложения
     * @return новый трехмерный вектор являющийся результатом сложения двух трехмерных векторов
     */
    @Override
    public Vector3d add(Vector3d anotherVector) {

        if (anotherVector == null) {
            throw new IllegalArgumentException("");
        }

        double[] newVector3dCoordinates = this.addCoordinates(anotherVector);
        return new Vector3d(newVector3dCoordinates);
    }

    /**
     * Метод выполняющий умножение трехмерного вектора на скаляр
     *
     * @param scalar значение скаляра
     * @return новый трехмерный вектор являющийся результатом умножения трехмерного вектора на скаляр
     */
    @Override
    public Vector3d multiplyByScalar(double scalar) {
        double[] newVector3dCoordinates = this.multiplyCoordinatesOnScalar(scalar);
        return new Vector3d(newVector3dCoordinates);
    }
}
