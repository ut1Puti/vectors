package vectors;

/**
 * Класс представляющий абстрактный вектор
 *
 * @param <T> тип представляющий собой реализацию интерфейса {@code Vector} или класса {@code AbstractVector}
 * @author Кедровских Олег
 * @version 1.0
 */
public abstract class AbstractVector<T extends Vector<T>> implements Vector<T> {
    /**
     * Метод для получения значения координаты по ее индексу,
     * например для вектора (1, 5, 2) координата по индексу 1 - 1, по индексу 2 - 5, по индексу 3 - 2,
     * если было получено значение большее, чем последняя координата, метод должен выкидывать {@code IllegalArgumentException}
     *
     * @param coordinateIndex номер координаты, которую необходимо получить их вектора,
     *                        номер координаты должен быть в машинном представлении, то есть как индексы массива
     * @return координату вектора в виде {@code double'a}
     * @throws IllegalArgumentException возникает если индекс координаты больше чем индекс последней координаты
     */
    @Override
    public abstract double getCoordinate(int coordinateIndex) throws IllegalArgumentException;

    /**
     * Метод получающий размерность вектора
     *
     * @return размерность вектора
     */
    @Override
    public abstract int getDimension();

    /**
     * Метод выполняющий сравнение двух векторов с одинаковыми размерностями
     *
     * @param anotherVector вектор, который является вторым аргументом операции сложения
     * @return новый вектор типа {@code T}, координаты которого являются результатом по координатного сложения
     * вектора, из которого был вызван метод и вектора полученного в качестве параметра
     * @throws IllegalArgumentException возникает если параметр сравниваемого вектора {@code anotherVector} равен {@code null}
     */
    @Override
    public abstract T add(T anotherVector) throws IllegalArgumentException;

    /**
     * Метод складывающий координаты двух векторов
     *
     * @param anotherVector вектор, являющийся вторым аргументом в операции сложения координат векторов
     * @return массив {@code double'ов} в котором элементы - это суммы одноименных координат в векторах
     */
    protected double[] addCoordinates(T anotherVector) {
        int newVectorDimension = this.getDimension();
        double[] newVectorCoordinates = new double[newVectorDimension];
        for (int i = 0; i < newVectorDimension; i++) {
            newVectorCoordinates[i] = this.getCoordinate(i) + anotherVector.getCoordinate(i);
        }
        return newVectorCoordinates;
    }

    /**
     * Метод выполняющий умножение вектора на скаляр
     *
     * @param scalar значение скаляра
     * @return новый вектор типа {@code T},
     * координаты которого являются результатом умножения всех координат исходного вектора на скаляр {@code scalar}
     */
    @Override
    public abstract T multiplyByScalar(double scalar);

    /**
     * Метод выполняющий умножение координат вектора на скаляр
     *
     * @param scalar скаляр на который умножаются координаты вектора
     * @return массив {@code double'ов} состоящий из умноженных на скаляр координат вектора
     */
    protected double[] multiplyCoordinatesOnScalar(double scalar) {
        int newVectorDimension = this.getDimension();
        double[] newVectorCoordinates = new double[newVectorDimension];
        for (int i = 0; i < newVectorDimension; i++) {
            newVectorCoordinates[i] = scalar * this.getCoordinate(i);
        }
        return newVectorCoordinates;
    }

    /**
     * Метод выполняющий операцию разности двух векторов
     *
     * @param anotherVector вектор, являющийся вторым аргументом операции вычитания векторов
     * @return новый вектор типа {@code T}, координатами которого является результат по координатного вычитания
     * вектора, из которого был вызван метод и вектора полученного в качестве параметра
     * @throws IllegalArgumentException возникает если параметр сравниваемого вектора {@code anotherVector} равен {@code null}
     */
    @Override
    public T minus(T anotherVector) throws IllegalArgumentException {

        if (anotherVector == null) {
            throw new IllegalArgumentException("");
        }

        if (this.getDimension() != anotherVector.getDimension()) {
            throw new IllegalArgumentException("");
        }

        return this.add(anotherVector.multiplyByScalar(-1));
    }

    /**
     * Метод операции скалярного произведения векторов
     *
     * @param anotherVector вектор, который является вторым аргументом скалярного произведения
     * @return результат скалярного произведения двух векторов
     * @throws IllegalArgumentException возникает если параметр сравниваемого вектора {@code anotherVector} равен {@code null}
     */
    @Override
    public double dotProduct(T anotherVector) throws IllegalArgumentException {

        if (anotherVector == null) {
            throw new IllegalArgumentException("");
        }

        if (this.getDimension() != anotherVector.getDimension()) {
            throw new IllegalArgumentException("");
        }

        double result = 0;
        for (int i = 0; i < this.getDimension(); i++) {
            result += this.getCoordinate(i) * anotherVector.getCoordinate(i);
        }
        return result;
    }

    /**
     * Метод сравнивает два вектора по координатно
     *
     * @param anotherVector   вектор, с координатами которого будет сравниваться исходный
     * @param coordinateIndex номер координат, по которым будут сравнивать вектора,
     *                        номер координаты в машинном представлении, то есть как в массиве
     * @return < 0 если координаты исходного вектор меньше переданного в параметрах,
     * == 0 если координаты равны, > 0 если координаты исходного вектора больше чем у переданного
     * @throws IllegalArgumentException возникает если индекс координаты больше чем индекс последней координаты,
     *                                  а также если параметр сравниваемого вектора {@code anotherVector} равен {@code null}
     */
    @Override
    public int compareByCoordinate(T anotherVector, int coordinateIndex) throws IllegalArgumentException {

        if (anotherVector == null) {
            throw new IllegalArgumentException("");
        }

        if (this.getDimension() != anotherVector.getDimension()) {
            throw new IllegalArgumentException("");
        }

        if (0 > coordinateIndex || coordinateIndex >= this.getDimension()) {
            throw new IllegalArgumentException("");
        }

        return Double.compare(this.getCoordinate(coordinateIndex), anotherVector.getCoordinate(coordinateIndex));
    }

    /**
     * Метод проверяющий на равенство экземпляр {@code AbstractVector} {@code obj}
     *
     * @param obj объект сравниваемый с экземпляром {@code AbstractVector}
     * @return {@code true} если объект равен экземпляру, {@code false} если объект не равен экземпляру
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AbstractVector<?> vector)) {
            return false;
        }

        int vectorDimension = this.getDimension();

        if (vectorDimension != vector.getDimension()) {
            return false;
        }

        for (int i = 0; i < vectorDimension; i++) {

            if (this.getCoordinate(i) != vector.getCoordinate(i)) {
                return false;
            }

        }
        return true;
    }

    /**
     * Метод преобразовывающий экземпляр класса в строку
     *
     * @return строку содержащую строку с координатами вектора, строка выглядит так {@code (n1, n2, n3...)},
     * где {@code ni} это {@code i-ая} координата вектора
     */
    @Override
    public String toString() {
        int vectorDimension = this.getDimension();
        StringBuilder vectorInStringForm = new StringBuilder("(");
        for (int i = 0; i < vectorDimension; i++) {
            vectorInStringForm.append(this.getCoordinate(i));

            if (i + 1 != vectorDimension) {
                vectorInStringForm.append(", ");
            }

        }
        vectorInStringForm.append(")");
        return vectorInStringForm.toString();
    }
}
