package tdd.vendingMachine;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.exceptions.VendingMachineException;

import java.math.BigDecimal;

/**
 * Created by sok_pomaranczowy on 19.03.17.
 */
public class ShelveTest {

    public Shelve shelve;
    public final Product COCA_COLA = new Product("Coca cola 0.25l", new BigDecimal(2.0));
    public final Product CHOCOLATE_BAR = new Product("chocolate bar", new BigDecimal(2.5));

    @Before
    public void onSetUp() {
        this.shelve = new Shelve();
    }

    @Test
    public void givenShelveWithOneProductTypeThenMayAddAnotherProductOfThatType() throws VendingMachineException {
        shelve.addProducts(COCA_COLA);
        shelve.addProducts(COCA_COLA);
        Assertions.assertThat(shelve.getProducts().size()).isEqualTo(2);
    }

    @Test(expected = VendingMachineException.class)
    public void givenShelveWithOneProductTypeThenCannotAddProductOfOtherType() throws VendingMachineException {
        shelve.addProducts(COCA_COLA);
        shelve.addProducts(CHOCOLATE_BAR);
    }
}
