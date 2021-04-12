package hierarchy.operation;

import java.util.*;

public class Journal {
    private List<Operation> operations;

    public Journal() {
        operations = new ArrayList<>();
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void addOperaion(Operation operation) {
        operations.add(operation);
    }

}
