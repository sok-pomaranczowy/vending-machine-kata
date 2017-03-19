package tdd.vendingMachine;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

    public VendingMachine vendingMachine;

    @Before
    public void onSetUp(){
        this.vendingMachine = new VendingMachine();
    }

    @Test
    public void given_stupid_passing_test_ensure_that_tests_are_run() {
        Assertions.assertThat(vendingMachine).isNotNull();
    }

    @Test
    public void givenVendingMachineThenProductsNotEmpty(){
        Assertions.assertThat(vendingMachine.getProducts()).isNotNull();
    }
}
