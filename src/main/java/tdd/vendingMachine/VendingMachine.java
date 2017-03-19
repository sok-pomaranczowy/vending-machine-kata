package tdd.vendingMachine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private List<String> products;

    public VendingMachine() {
        this.products = new ArrayList<>();
    }

    public List<String> getProducts() {
        return products;
    }
}
