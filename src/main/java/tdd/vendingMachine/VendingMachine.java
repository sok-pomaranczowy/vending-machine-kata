package tdd.vendingMachine;

import tdd.vendingMachine.exceptions.VendingMachineException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private List<Shelve> shelves;
    private final int numberOfShelves;
    private String display;
    private BigDecimal transactionBalance;
    private Vault vault;

    public VendingMachine(int numberOfShelves) {
        this.vault = new Vault();
        this.transactionBalance = BigDecimal.ZERO;
        this.display = "Welcome!";
        this.numberOfShelves = numberOfShelves;
        this.shelves = new ArrayList<>(numberOfShelves);
        for (int i = 0; i < numberOfShelves; i++) {
            this.shelves.add(new Shelve());
        }
    }

    public List<Product> getAllProducts() {
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

    public BigDecimal showShelvePrice(int shelveNumber) throws VendingMachineException {
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
        transactionBalance = transactionBalance.add(coin.getValue());
        vault.addCoin(coin);
    }

    public ProductAndChange selectProduct(int shelveNumber) throws VendingMachineException {
        checkShelveInRange(shelveNumber);
        if(transactionBalance.compareTo(shelves.get(shelveNumber).getPrice()) >= 0 &&
            !shelves.get(shelveNumber).getProducts().isEmpty()){
            Product product = shelves.get(shelveNumber).removeProduct();

            display = "Welcome!";
            ArrayList change = ChangeCalculator.calculateChange(transactionBalance.subtract(product.getPrice()), vault.getVault());
            return new ProductAndChange(product, change);
        }
        else{
            BigDecimal difference = shelves.get(shelveNumber).getPrice().subtract(transactionBalance);
            display = "You need: "+difference+" more.";
            return null;
        }
    }

    public Vault getVault() {
        return vault;
    }


}
