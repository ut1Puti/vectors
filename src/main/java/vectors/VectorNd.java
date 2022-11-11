package vectors;

/**
 * Класс n-мерных векторов
 *
 * @author Кедровских Олег
 * @version 1.0
 */
public class VectorNd extends AbstractVector<VectorNd> {
    /**
     * Поле массива координат n-мерного вектора
     */
    private final double[] coordinates;

    /**
     * Конструктор - создает экземпляр классов
     *
     * @param coordinates
     */
    public VectorNd(double... coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Метод получающий координату вектора
     *
     * @param coordinateIndex номер координаты, которую необходимо получить их вектора,
     *                        номер координаты должен быть в машинном представлении, то есть как индексы массива
     * @return координату вектора по индексу. Пример {1, 2, 3, 4} индексы координат соодтвествуют числам - 1
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
     * @return размерность вектора
     */
    @Override
    public int getDimension() {
        return coordinates.length;
    }

    /**
     * Метод выполняющий сложение n-мерных векторов
     *
     * @param anotherVector вектор, который является вторым аргументом операции сложения
     * @return новый n-мерный вектор являющийся результатом сложения двух n-мерных векторов с одинаковой размерностью
     */
    @Override
    public VectorNd add(VectorNd anotherVector) {

        if (anotherVector == null) {
            throw new IllegalArgumentException("");
        }

        if (this.getDimension() != anotherVector.getDimension()) {
            throw new IllegalArgumentException("");
        }

        double[] newVectorNdCoordinates = this.addCoordinates(anotherVector);
        return new VectorNd(newVectorNdCoordinates);
    }

    /**
     * Метод выполняющий умножение n-мерного вектора на скаляр
     *
     * @param scalar значение скаляра
     * @return новый n-мерный вектор являющийся результатом умножения n-мерного вектора на скаляр
     */
    @Override
    public VectorNd multiplyByScalar(double scalar) {
        double[] newVectorNdCoordinates = this.multiplyCoordinatesOnScalar(scalar);
        return new VectorNd(newVectorNdCoordinates);
    }
}
