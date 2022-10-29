package vectors;

/**
 *
 *
 * @author
 * @version
 */
public class Vector3d extends AbstractVector<Vector3d> {
    /**
     *
     */
    private final double x;
    /**
     *
     */
    private final double y;
    /**
     *
     */
    private final double z;

    /**
     *
     *
     * @param x
     * @param y
     * @param z
     */
    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     *
     *
     * @param vectorCoordinates
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
     *
     *
     * @param coordinateIndex номер координаты, которую необходимо получить их вектора,
     *                        номер координаты должен быть в машинном представлении, то есть как индексы массива
     * @return
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
     *
     *
     * @return
     */
    @Override
    public int getDimension() {
        return 3;
    }

    /**
     *
     *
     * @param anotherVector вектор, который является вторым аргументом операции сложения
     * @return
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
     *
     *
     * @param scalar значение скаляра
     * @return
     */
    @Override
    public Vector3d multiplyByScalar(double scalar) {
        double[] newVector3dCoordinates = this.multiplyCoordinatesOnScalar(scalar);
        return new Vector3d(newVector3dCoordinates);
    }
}
