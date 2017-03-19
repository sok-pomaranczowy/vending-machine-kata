package tdd.vendingMachine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private List<Shelve> shelves;

    public VendingMachine() {
        this.shelves = new ArrayList<>();
        this.shelves.add(new Shelve());
    }

    public List<String> getProducts() {
        ArrayList<String> allProducts = new ArrayList<>();

        for (Shelve shelve : shelves) {
            allProducts.addAll(shelve.getProducts());
        }
        return allProducts;
    }

    public void addProduct(String product) {
        shelves.get(0).addProducts(product);
    }

    public List<Shelve> getShelves() {
        return shelves;
    }
}
