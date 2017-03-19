package tdd.vendingMachine;

import tdd.vendingMachine.exceptions.VendingMachineException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sok_pomaranczowy on 19.03.17.
 */
public class Shelve {
    private List<Product> products;
    private Product.TYPE typeOfProductsOnShelve;

    public Shelve() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProducts(Product product) throws VendingMachineException {
        if(products.isEmpty()){
            products.add(product);
            typeOfProductsOnShelve = product.getType();
        }
        else if(typeOfProductsOnShelve == product.getType()){
            products.add(product);
        }
        else{
            throw new VendingMachineException("Inserted product: "+product.getName()+" is of type: "+ product.getType()
                +" but this shelve only accepts products of type: "+typeOfProductsOnShelve);
        }
    }
}
