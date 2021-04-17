public class GenericListAgregator<T extends Number> {
    private T[] array;

    public GenericListAgregator(T[] array) {
        if (array == null) {
            throw new IllegalStateException("Array can't be null!");
        }
        this.array = array;
    }

    public double getAvgValue(){
        double sum = 0.0;
        for (int i = 0; i < array.length; i++){
            sum += array[i].doubleValue();
        }
        return sum/array.length;
    }

    public double getMaxValue(){
        double maxValue = array[0].doubleValue();

        for (int i = 1; i < array.length; i++){
            if (array[i].doubleValue() > maxValue){
                maxValue = array[i].doubleValue();
            }
        }
        return maxValue;
    }

    public double getMinValue() {
        double minValue = array[0].doubleValue();

        for (int i = 1; i < array.length; i++) {
            if (minValue > array[i].doubleValue()) {
                minValue = array[i].doubleValue();
            }
        }
        return minValue;
    }

    public static void main(String[] args) {
        //Number[] array = {"3", "5", "1024"};
        Number[] array = {1, 3, 5, -11, 7, 1024, -3, 10};
        Number[] array2 = {1.3, 3.5, -13.725, 7.25};

        GenericListAgregator genList = new GenericListAgregator(array);
        System.out.println("Avaeage value: " + genList.getAvgValue());
        System.out.println("Max. value: " + genList.getMaxValue());
        System.out.println("Min. value: " + genList.getMinValue());

        GenericListAgregator genList2 = new GenericListAgregator(array2);
        System.out.println("Avaeage value: " + genList2.getAvgValue());
        System.out.println("Max. value: " + genList2.getMaxValue());
        System.out.println("Min. value: " + genList2.getMinValue());

    }
}
