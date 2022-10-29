package vectors;

/**
 *
 *
 * @author
 * @version
 */
public class Vector5d extends AbstractVector<Vector5d> {
    /**
     *
     */
    private final double[] coordinates;

    /**
     *
     *
     * @param a1
     * @param a2
     * @param a3
     * @param a4
     * @param a5
     */
    public Vector5d(double a1, double a2, double a3, double a4, double a5) {
        this.coordinates = new double[] {a1, a2, a3, a4, a5};
    }

    /**
     *
     *
     * @param coordinates
     */
    private Vector5d(double[] coordinates) {

        if (coordinates.length != 5) {
            throw new IllegalStateException("");
        }

        this.coordinates = coordinates;
    }

    /**
     *
     *
     * @param coordinateIndex номер координаты, которую необходимо получить их вектора,
     *                        номер координаты должен быть в машинном представлении, то есть как индексы массива
     * @return
     */
    @Override
    public double getCoordinate(int coordinateIndex) {

        if (coordinateIndex < 0 || coordinateIndex >= coordinates.length) {
            throw new IllegalArgumentException("");
        }

        return coordinates[coordinateIndex];
    }

    /**
     *
     *
     * @return
     */
    @Override
    public int getDimension() {
        return 5;
    }

    /**
     *
     *
     * @param anotherVector вектор, который является вторым аргументом операции сложения
     * @return
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
     *
     *
     * @param scalar значение скаляра
     * @return
     */
    @Override
    public Vector5d multiplyByScalar(double scalar) {
        double[] newVector5dCoordinates = this.multiplyCoordinatesOnScalar(scalar);
        return new Vector5d(newVector5dCoordinates);
    }
}
