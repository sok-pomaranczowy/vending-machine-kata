package tdd.vendingMachine;

import tdd.vendingMachine.exceptions.VendingMachineException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class VendingMachine {

    private List<Shelve> shelves;
    private final int numberOfShelves;
    private String display;
    private Vault vault;
    private Transaction transaction;

    public VendingMachine(int numberOfShelves) {
        this.transaction = Transaction.getInstance();
        this.transaction.reset();
        this.vault = new Vault();
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
        transaction.addCoin(coin);
        vault.addCoin(coin);
    }

    public ProductAndChange selectProduct(int shelveNumber) throws VendingMachineException {
        checkShelveInRange(shelveNumber);
        boolean hasFundsAndShelveNotEmpty = transaction.getBalance().compareTo(shelves.get(shelveNumber).getPrice()) >= 0 &&
            !shelves.get(shelveNumber).getProducts().isEmpty();
        if (hasFundsAndShelveNotEmpty) {
            Product product = shelves.get(shelveNumber).removeProduct();
            List<Coin> change;
            try {
                change = ChangeCalculator.calculateChange(transaction.getBalance().subtract(product.getPrice()), vault.getVault());
            } catch (VendingMachineException e) {
                change = reset();
                display = e.getMessage();
                product = null;
            }
            display = "Welcome!";
            transaction.reset();
            return new ProductAndChange(product, change);
        } else {
            BigDecimal difference = shelves.get(shelveNumber).getPrice().subtract(transaction.getBalance());
            display = "You need: " + difference + " more.";
            return null;
        }
    }

    public List<Coin> cancel() {
        display = "Welcome!";
        return reset();
    }

    private List<Coin> reset(){
        vault.getVault().removeAll(transaction.getCoins());
        List<Coin> coins = transaction.getCoins();
        transaction.reset();
        return coins;
    }

    public Vault getVault() {
        return vault;
    }

    public void setVault(Vault vault) {
        this.vault = vault;
    }
}
