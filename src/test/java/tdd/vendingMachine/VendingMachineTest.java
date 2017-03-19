package tdd.vendingMachine;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.exceptions.VendingMachineException;

import java.util.ArrayList;
import java.util.List;

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
    public void givenVendingMachineThenShelvesExist() {
        Assertions.assertThat(vendingMachine.getShelves()).isNotNull();
    }

    @Test
    public void givenVendingMachineAddProductThenShelveHasProduct() throws VendingMachineException {
        vendingMachine.addProduct(COCA_COLA, 0);
        Assertions.assertThat(vendingMachine.getShelves().get(0).getProducts()).contains(COCA_COLA);
    }

    @Test
    public void givenVendingMachineThenHasDisplay(){
        Assertions.assertThat(vendingMachine.getDisplay()).isNotNull();
    }

    @Test
    public void givenVendingMachineThenDisplaySaysWelcome(){
        Assertions.assertThat(vendingMachine.getDisplay()).isEqualTo("Welcome!");
    }

    @Test
    public void givenShelveNumberThenVendingMachineDisplayShowsPrice() throws VendingMachineException {
        vendingMachine.addProduct(COCA_COLA,0);
        Assertions.assertThat(vendingMachine.showShelvePrice(0)).isEqualTo(COCA_COLA.getPrice());
    }

    @Test
    public void givenEmptyShelveNumberThenVendingMachineDisplayShowsZero() throws VendingMachineException {
        Assertions.assertThat(vendingMachine.showShelvePrice(0)).isZero();
    }

    @Test
    public void givenCorrectAmountOfCoinsThenReturnProduct() throws VendingMachineException {
        vendingMachine.addProduct(MINERAL_WATER,0);
        vendingMachine.putCoin(Coin.ONE);
        vendingMachine.putCoin(Coin.FIFTY_CENTS);
        Assertions.assertThat(vendingMachine.selectProduct(0)).isEqualTo(MINERAL_WATER);
    }

    @Test
    public void givenInsufficientAmountOfCoinsThenReturnProduct() throws VendingMachineException {
        vendingMachine.addProduct(MINERAL_WATER,0);
        vendingMachine.putCoin(Coin.FIFTY_CENTS);
        Assertions.assertThat(vendingMachine.selectProduct(0)).isNull();
    }

    @Test
    public void givenCoinsThenDisplayShowsRemainingValue() throws VendingMachineException {
        vendingMachine.addProduct(MINERAL_WATER,0);
        vendingMachine.putCoin(Coin.ONE);
        vendingMachine.selectProduct(0);
        Assertions.assertThat(vendingMachine.getDisplay()).isEqualTo("You need: "+
            Double.toString(MINERAL_WATER.getPrice() - 1.0)
            +" more.");
    }
}
