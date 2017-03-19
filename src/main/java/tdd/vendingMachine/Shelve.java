package tdd.vendingMachine;

import tdd.vendingMachine.exceptions.VendingMachineException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sok_pomaranczowy on 19.03.17.
 */
public class Shelve {
    private List<Product> products;
    private String typeOfProductsOnShelve;

    public Shelve() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProducts(Product product) throws VendingMachineException {
        if(products.isEmpty()){
            products.add(product);
            typeOfProductsOnShelve = product.getName();
        }
        else if(typeOfProductsOnShelve == product.getName()){
            products.add(product);
        }
        else{
            throw new VendingMachineException("Inserted product: "+product.getName()+" but this shelve only accepts: "+typeOfProductsOnShelve);
        }
    }
}
