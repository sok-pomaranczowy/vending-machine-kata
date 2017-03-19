package tdd.vendingMachine;

import tdd.vendingMachine.exceptions.VendingMachineException;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private List<Shelve> shelves;
    private final int numberOfShelves;
    private String display;
    private double transactionBalance;

    public VendingMachine(int numberOfShelves) {
        this.transactionBalance = .0;
        this.display = "Welcome!";
        this.numberOfShelves = numberOfShelves;
        this.shelves = new ArrayList<>(numberOfShelves);
        for (int i = 0; i < numberOfShelves; i++) {
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
        if (shelveNumber > this.numberOfShelves - 1 || shelveNumber < 0) {
            throw new VendingMachineException("Shelve number: " + shelveNumber + " does not exist. Choose shelve number between " +
                "[0;" + (numberOfShelves - 1) + "]");
        }
    }

    public void putCoin(Coin coin) {
        transactionBalance += coin.getValue();
    }

    public Product selectProduct(int shelveNumber) throws VendingMachineException {
        checkShelveInRange(shelveNumber);
        if(transactionBalance >= shelves.get(shelveNumber).getPrice() && !shelves.get(shelveNumber).getProducts().isEmpty()){
            Product product = shelves.get(shelveNumber).getProducts().get(0);
            shelves.get(shelveNumber).getProducts().remove(0);
            transactionBalance = .0;
            display = "Welcome!";
            return product;
        }
        else{
            double difference = shelves.get(shelveNumber).getPrice() - transactionBalance;
            display = "You need: "+difference+" more.";
            return null;
        }
    }
}
