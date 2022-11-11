package vectors;

/**
 * Интерфейс основных операций вектора
 *
 * @param <T> тип вектора, с которым будут выполняться операции вектора, должен реализовывать интерфейс {@code Vector}
 * @author Кедровских Олег
 * @version 1.0
 */
public interface Vector<T extends Vector<T>> {
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
    double getCoordinate(int coordinateIndex) throws IllegalArgumentException;

    /**
     * Метод получающий размерность вектора
     *
     * @return размерность вектора
     */
    int getDimension();

    /**
     * Метод выполняющий сравнение двух векторов с одинаковыми размерностями
     *
     * @param anotherVector вектор, который является вторым аргументом операции сложения
     * @return новый вектор типа {@code T}, координаты которого являются результатом по координатного сложения
     * вектора, из которого был вызван метод и вектора полученного в качестве параметра
     * @throws IllegalArgumentException возникает если параметр сравниваемого вектора {@code anotherVector} равен {@code null}
     */
    T add(T anotherVector) throws IllegalArgumentException;

    /**
     * Метод выполняющий умножение вектора на скаляр
     *
     * @param scalar значение скаляра
     * @return новый вектор типа {@code T},
     * координаты которого являются результатом умножения всех координат исходного вектора на скаляр {@code scalar}
     */
    T multiplyByScalar(double scalar);

    /**
     * Метод выполняющий операцию разности двух векторов
     *
     * @param anotherVector вектор, являющийся вторым аргументом операции вычитания векторов
     * @return новый вектор типа {@code T}, координатами которого является результат по координатного вычитания
     * вектора, из которого был вызван метод и вектора полученного в качестве параметра
     * @throws IllegalArgumentException возникает если параметр сравниваемого вектора {@code anotherVector} равен {@code null}
     */
    T minus(T anotherVector) throws IllegalArgumentException;

    /**
     * Метод выполняющий операцию скалярного произведения векторов
     *
     * @param anotherVector вектор, который является вторым аргументом операции скалярного произведения,
     *                      если {@code null} должно возникать исключение {@code IllegalArgumentException}
     * @return результат скалярного произведения двух векторов
     * @throws IllegalArgumentException возникает если параметр сравниваемого вектора {@code anotherVector} равен {@code null}
     */
    double dotProduct(T anotherVector) throws IllegalArgumentException;

    /**
     * Метод выполняющий по координатное сравнение векторов
     *
     * @param anotherVector   вектор, с координатами которого будет сравниваться исходный
     *                        если {@code null} должно возникать исключение {@code IllegalArgumentException}
     * @param coordinateIndex номер координат, по которым будут сравнивать вектора,
     *                        номер координаты в машинном представлении, то есть как в массиве.
     *                        Если номер координаты меньше {@code 0} или больше размерности вектора,
     *                        тогда должно возникать исключение {@code IllegalArgumentException}
     * @return < 0 если координаты исходного вектор меньше переданного в параметрах,
     * == 0 если координаты равны, > 0 если координаты исходного вектора больше чем у переданного
     * @throws IllegalArgumentException возникает если индекс координаты больше чем индекс последней координаты,
     *                                  а также если параметр сравниваемого вектора {@code anotherVector} равен {@code null}
     */
    int compareByCoordinate(T anotherVector, int coordinateIndex) throws IllegalArgumentException;
}
