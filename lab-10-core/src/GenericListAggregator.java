import java.util.Comparator;

public class GenericListAggregator<T extends Number> {
    private final T[] array;

    public GenericListAggregator(T[] array) {
        if (array == null) {
            throw new IllegalStateException("Array can't be null!");
        }
        this.array = array;
    }

    public static void main(String[] args) {
        Integer[] array = {1, 3, 5, -11, 7, 1024, -3, 10};
        Double[] array2 = {1.3, 3.5, -13.725, 7.25};

        GenericListAggregator<Integer> genList = new GenericListAggregator<>(array);
        System.out.println("Average value: " + genList.getAvgValue());
        System.out.println("Max. value: " + genList.getMaxValue());
        System.out.println("Min. value: " + genList.getMinValue());

        GenericListAggregator<Double> genList2 = new GenericListAggregator<>(array2);
        System.out.println("Average value: " + genList2.getAvgValue());
        System.out.println("Max. value: " + genList2.getMaxValue());
        System.out.println("Min. value: " + genList2.getMinValue());

    }

    public double getAvgValue() {
        var sum = 0.0;
        for (T t : array) {
            sum += t.doubleValue();
        }
        return sum / array.length;
    }

    public double getMaxValue() {
        return maxValue(Comparator.naturalOrder());
    }

    private double maxValue(Comparator<Double> comparator) {
        var maxValue = array[0].doubleValue();

        for (T t : array) {
            if (comparator.compare(t.doubleValue(), maxValue) > 0) {
                maxValue = t.doubleValue();
            }
        }
        return maxValue;
    }

    public double getMinValue() {
        return maxValue(Comparator.reverseOrder());
    }
}
