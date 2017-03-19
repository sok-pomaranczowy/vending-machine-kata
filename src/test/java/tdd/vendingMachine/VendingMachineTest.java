package tdd.vendingMachine;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.exceptions.VendingMachineException;

public class VendingMachineTest {

    public VendingMachine vendingMachine;

    @Before
    public void onSetUp() {
        this.vendingMachine = new VendingMachine(2);
    }

    @Test
    public void given_stupid_passing_test_ensure_that_tests_are_run() {
        Assertions.assertThat(vendingMachine).isNotNull();
    }

    @Test
    public void givenVendingMachineThenProductsNotEmpty() {
        Assertions.assertThat(vendingMachine.getProducts()).isNotNull();
    }

    @Test
    public void givenAddProductsThenVendingMachineContainProduct() throws VendingMachineException {
        vendingMachine.addProduct(new Product("Cocla cola o.25l", Product.TYPE.BEVERAGE), 0);
        vendingMachine.addProduct(new Product("chocolate bar", Product.TYPE.FOOD), 1);
        Assertions.assertThat(vendingMachine.getProducts()).contains(new Product("Cocla cola o.25l", Product.TYPE.BEVERAGE));
        Assertions.assertThat(vendingMachine.getProducts()).contains(new Product("chocolate bar", Product.TYPE.FOOD));
    }

    @Test
    public void givenAddProductsOfTheSameTypeThenVendingMachineContainsProduct() throws VendingMachineException {
        vendingMachine.addProduct(new Product("Cocla cola o.25l", Product.TYPE.BEVERAGE), 0);
        vendingMachine.addProduct(new Product("mineral water 0.33l", Product.TYPE.BEVERAGE), 0);
        Assertions.assertThat(vendingMachine.getProducts()).contains(new Product("Cocla cola o.25l", Product.TYPE.BEVERAGE));
        Assertions.assertThat(vendingMachine.getProducts()).contains(new Product("mineral water 0.33l", Product.TYPE.BEVERAGE));
    }

    @Test(expected = VendingMachineException.class)
    public void givenAddProductsOnNonExistingShelveThenRaiseException() throws VendingMachineException {
        vendingMachine.addProduct(new Product("Cocla cola o.25l", Product.TYPE.BEVERAGE), 7);
    }

    @Test(expected = VendingMachineException.class)
    public void givenAddProductsOfDifferentTypeToShelveThenRaiseException() throws VendingMachineException {
        vendingMachine.addProduct(new Product("Cocla cola o.25l", Product.TYPE.BEVERAGE), 0);
        vendingMachine.addProduct(new Product("chocolate bar", Product.TYPE.FOOD), 0);

    }

    @Test
    public void givenAddProductsOnNonExistingShelveExceptionThenCorrectMessage() throws VendingMachineException {
        try {
            vendingMachine.addProduct(new Product("Cocla cola o.25l", Product.TYPE.BEVERAGE), 7);
        } catch (VendingMachineException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Shelve number: 7 does not exist. Choose shelve number between [0;1]");
        }
    }

    @Test
    public void givenAddProductsOfDifferentTypeToShelveExceptionThenCorrectMessage() throws VendingMachineException {
        try {
            vendingMachine.addProduct(new Product("Cocla cola o.25l", Product.TYPE.BEVERAGE), 0);
            vendingMachine.addProduct(new Product("chocolate bar", Product.TYPE.FOOD), 0);
        } catch (VendingMachineException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Inserted product: chocolate bar is of type: FOOD but this " +
                "shelve only accepts products of type: BEVERAGE");
        }

    }

    @Test
    public void givenVendingMachineThenShelvesExist() {
        Assertions.assertThat(vendingMachine.getShelves()).isNotNull();
    }

    @Test
    public void givenVendingMachineAddProductThenShelveHasProduct() throws VendingMachineException {
        vendingMachine.addProduct(new Product("Coca cola 0.25l", Product.TYPE.BEVERAGE), 0);
        Assertions.assertThat(vendingMachine.getShelves().get(0).getProducts()).contains(new Product("Coca cola 0.25l", Product.TYPE.BEVERAGE));
    }

    @Test
    public void givenShelveWithOneProductTypeThenMayAddAnotherProductOfThatType() throws VendingMachineException {
        Shelve shelve = new Shelve();
        shelve.addProducts(new Product("Coca cola 0.25l", Product.TYPE.BEVERAGE));
        shelve.addProducts(new Product("mineral water 0.33l", Product.TYPE.BEVERAGE));
        Assertions.assertThat(shelve.getProducts().size()).isEqualTo(2);
    }

    @Test(expected = VendingMachineException.class)
    public void givenShelveWithOneProductTypeThenCannotAddProductOfOtherType() throws VendingMachineException {
        Shelve shelve = new Shelve();
        shelve.addProducts(new Product("Coca cola 0.25l", Product.TYPE.BEVERAGE));
        shelve.addProducts(new Product("chocolate bar", Product.TYPE.FOOD));
    }

    @Test
    public void givenExceptionOnProductTypeMismatchThenExeptionMessageIsCorrect() {
        Shelve shelve = new Shelve();
        try {
            shelve.addProducts(new Product("Coca cola 0.25l", Product.TYPE.BEVERAGE));
            shelve.addProducts(new Product("chocolate bar", Product.TYPE.FOOD));
        } catch (VendingMachineException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Inserted product: chocolate bar is of type: FOOD but this " +
                "shelve only accepts products of type: BEVERAGE");
        }
    }
}
