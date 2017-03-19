package tdd.vendingMachine;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.exceptions.VendingMachineException;

public class VendingMachineTest {

    public VendingMachine vendingMachine;
    public final Product COCA_COLA = new Product("Coca cola 0.25l", 2.0);
    public final Product MINERAL_WATER = new Product("mineral water 0.33l", 1.5);
    public final Product CHOCOLATE_BAR = new Product("chocolate bar", 2.5);

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
        vendingMachine.addProduct(COCA_COLA, 0);
        vendingMachine.addProduct(CHOCOLATE_BAR, 1);
        Assertions.assertThat(vendingMachine.getProducts()).contains(COCA_COLA);
        Assertions.assertThat(vendingMachine.getProducts()).contains(CHOCOLATE_BAR);
    }

    @Test
    public void givenAddProductsOfTheSameTypeThenVendingMachineContainsProduct() throws VendingMachineException {
        vendingMachine.addProduct(COCA_COLA, 0);
        vendingMachine.addProduct(COCA_COLA, 0);
        Assertions.assertThat(vendingMachine.getProducts()).contains(COCA_COLA);
        Assertions.assertThat(vendingMachine.getProducts().size()).isEqualTo(2);
    }

    @Test(expected = VendingMachineException.class)
    public void givenAddProductsOnNonExistingShelveThenRaiseException() throws VendingMachineException {
        vendingMachine.addProduct(COCA_COLA, 7);
    }

    @Test(expected = VendingMachineException.class)
    public void givenAddProductsOfDifferentTypeToShelveThenRaiseException() throws VendingMachineException {
        vendingMachine.addProduct(COCA_COLA, 0);
        vendingMachine.addProduct(CHOCOLATE_BAR, 0);

    }

    @Test
    public void givenAddProductsOnNonExistingShelveExceptionThenCorrectMessage() throws VendingMachineException {
        try {
            vendingMachine.addProduct(COCA_COLA, 7);
        } catch (VendingMachineException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Shelve number: 7 does not exist. Choose shelve number between [0;1]");
        }
    }

    @Test
    public void givenAddProductsOfDifferentTypeToShelveExceptionThenCorrectMessage() throws VendingMachineException {
        try {
            vendingMachine.addProduct(COCA_COLA, 0);
            vendingMachine.addProduct(CHOCOLATE_BAR, 0);
        } catch (VendingMachineException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Inserted product: "+CHOCOLATE_BAR.getName()+" but this shelve only accepts: "+COCA_COLA.getName());
        }

    }

    @Test
    public void givenVendingMachineThenShelvesExist() {
        Assertions.assertThat(vendingMachine.getShelves()).isNotNull();
    }

    @Test
    public void givenVendingMachineAddProductThenShelveHasProduct() throws VendingMachineException {
        vendingMachine.addProduct(COCA_COLA, 0);
        Assertions.assertThat(vendingMachine.getShelves().get(0).getProducts()).contains(COCA_COLA);
    }

    @Test
    public void givenShelveWithOneProductTypeThenMayAddAnotherProductOfThatType() throws VendingMachineException {
        Shelve shelve = new Shelve();
        shelve.addProducts(COCA_COLA);
        shelve.addProducts(COCA_COLA);
        Assertions.assertThat(shelve.getProducts().size()).isEqualTo(2);
    }

    @Test(expected = VendingMachineException.class)
    public void givenShelveWithOneProductTypeThenCannotAddProductOfOtherType() throws VendingMachineException {
        Shelve shelve = new Shelve();
        shelve.addProducts(COCA_COLA);
        shelve.addProducts(CHOCOLATE_BAR);
    }

    @Test
    public void givenExceptionOnProductTypeMismatchThenExeptionMessageIsCorrect() {
        Shelve shelve = new Shelve();
        try {
            shelve.addProducts(COCA_COLA);
            shelve.addProducts(CHOCOLATE_BAR);
        } catch (VendingMachineException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Inserted product: "+CHOCOLATE_BAR.getName()+" but this shelve only accepts: "+COCA_COLA.getName());
        }
    }
}
