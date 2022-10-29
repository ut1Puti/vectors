package vectors;

/**
 *
 *
 * @author
 * @version
 */
public class VectorNd extends AbstractVector<VectorNd> {
    /**
     *
     */
    private final double[] coordinates;

    /**
     *
     *
     * @param coordinates
     */
    public VectorNd(double... coordinates) {
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
        return coordinates.length;
    }

    /**
     *
     *
     * @param anotherVector вектор, который является вторым аргументом операции сложения
     * @return
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
     *
     *
     * @param scalar значение скаляра
     * @return
     */
    @Override
    public VectorNd multiplyByScalar(double scalar) {
        double[] newVectorNdCoordinates = this.multiplyCoordinatesOnScalar(scalar);
        return new VectorNd(newVectorNdCoordinates);
    }
}
