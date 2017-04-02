package tdd.vendingMachine;

import java.util.List;

/**
 * Created by sok_pomaranczowy on 19.03.17.
 */
public class ProductAndChange {
    private Product product;
    private List<Coin> change;

    public ProductAndChange(Product product, List<Coin> change) {
        this.product = product;
        this.change = change;
    }

    public Product getProduct() {
        return product;
    }

    public List<Coin> getChange() {
        return change;
    }
}
