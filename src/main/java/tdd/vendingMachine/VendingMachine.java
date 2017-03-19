package tdd.vendingMachine;

import tdd.vendingMachine.exceptions.VendingMachineException;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private List<Shelve> shelves;
    private final int shelvesNumber;
    private String display;

    public VendingMachine(int shelvesNumber) {
        this.display = "";
        this.shelvesNumber = shelvesNumber;
        this.shelves = new ArrayList<>(shelvesNumber);
        for (int i = 0; i < shelvesNumber; i++) {
            this.shelves.add(new Shelve());
        }
    }

    public List<Product> getProducts() {
        ArrayList<Product> allProducts = new ArrayList<>();

        for (Shelve shelve : shelves) {
            allProducts.addAll(shelve.getProducts());
        }
        return allProducts;
    }

    public void addProduct(Product product, int shelveNumber) throws VendingMachineException {
        checkShelveInRange(shelveNumber);
        shelves.get(shelveNumber).addProducts(product);
    }

    public List<Shelve> getShelves() {
        return shelves;
    }

    public String getDisplay() {
        return display;
    }

    public double showShelvePrice(int shelveNumber) throws VendingMachineException {
        checkShelveInRange(shelveNumber);
        return shelves.get(shelveNumber).getPrice();
    }

    private void checkShelveInRange(int shelveNumber) throws VendingMachineException {
        if (shelveNumber > this.shelvesNumber - 1 || shelveNumber < 0) {
            throw new VendingMachineException("Shelve number: " + shelveNumber + " does not exist. Choose shelve number between " +
                "[0;" + (shelvesNumber - 1) + "]");
        }
    }
}
