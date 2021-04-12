package hierarchy.valut;

public class Currecncy {
    private String name;
    private Number value;

    public Currecncy(String name, Number value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Number getValue() {
        return value;
    }
    public void setValue(Number value) {
        this.value = value;
    }

}
