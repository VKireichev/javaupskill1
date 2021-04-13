public class MyArrayList<T> {
    private final static int INIT_SIZE = 16;
    private final static int CUT_RATE = 4;
    private Object[] array;
    private int pointer = 0;

    public MyArrayList() {
        this(INIT_SIZE);
    }

    public MyArrayList(int capacity) {
        array = new Object[capacity];
    }

    public T get(int index) {
        return (T) array[index];
    }

    public int size() {
        return pointer;
    }

    public void add(T item) {
        if (pointer == array.length - 1)
            resize(array.length * 2);
        array[pointer++] = item;
    }

    public void remove(int index) {
        for (int i = index; i < pointer; i++)
            array[i] = array[i + 1];
        array[pointer] = null;
        pointer--;
        if (array.length > INIT_SIZE && pointer < array.length / CUT_RATE)
            resize(array.length / 2); // если элементов в CUT_RATE раз меньше чем
        // длина массива, то уменьшу в два раза
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < pointer; i++) {
            result += "\n" + array[i];
        }
        return result;
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }

    /****  Test MyArrayList  ***/
    public static void main(String[] args) {

        // Create,fill up (check add() method) and print MyArrayList
        MyArrayList<String> list = new MyArrayList<String>();
        for (int i = 0; i < 20; i++) {
            String str = "Element" + i;
            list.add(str);
        }
        System.out.println("List with size=" + list.size());
        System.out.println(list + "\n\n");

        // Check get() method
        for (int i = 0; i < list.size(); i += 4) {
            System.out.println("list[" + i + "] = " + list.get(i));
        }

        // Check remove() method
        for (int i = list.size(); i >= 0; i -= 2) {
            list.remove(i);
        }
        System.out.println("\n\nList with size=" + list.size());
        System.out.println(list + "\n\n");
    }

}
