package tdd.vendingMachine;

import tdd.vendingMachine.exceptions.VendingMachineException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by sok_pomaranczowy on 19.03.17.
 */
public class Shelve {
    private Stack<Product> products;
    private String typeOfProductsOnShelve;
    private BigDecimal productPrice
        ;
    public Shelve() {
        this.products = new Stack<>();
        this.productPrice = BigDecimal.ZERO;
    }

    public Stack<Product> getProducts() {
        return products;
    }

    public BigDecimal getPrice(){
        return productPrice;
    }

    public void addProducts(Product product) throws VendingMachineException {
        if(products.isEmpty()){
            products.push(product);
            typeOfProductsOnShelve = product.getName();
            productPrice = product.getPrice();
        }
        else if(typeOfProductsOnShelve == product.getName()){
            products.push(product);
        }
        else{
            throw new VendingMachineException("Inserted product: "
                +product.getName()+" but this shelve only accepts: "
                +typeOfProductsOnShelve);
        }
    }

    public Product removeProduct(){
        return products.pop();
    }
}
