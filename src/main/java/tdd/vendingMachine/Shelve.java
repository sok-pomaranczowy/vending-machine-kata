package tdd.vendingMachine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sok_pomaranczowy on 19.03.17.
 */
public class Shelve {
    private List<String> products;

    public Shelve() {
        this.products = new ArrayList<>();
    }

    public List<String> getProducts() {
        return products;
    }

    public void addProducts(String product){
        products.add(product);
    }
}
