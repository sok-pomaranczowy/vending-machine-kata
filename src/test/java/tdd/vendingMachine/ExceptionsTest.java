package tdd.vendingMachine;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.exceptions.VendingMachineException;

/**
 * Created by sok_pomaranczowy on 19.03.17.
 */
public class ExceptionsTest {

    public VendingMachine vendingMachine;
    public final Product COCA_COLA = new Product("Coca cola 0.25l", 2.0);
    public final Product CHOCOLATE_BAR = new Product("chocolate bar", 2.5);

    @Before
    public void onSetUp() {
        this.vendingMachine = new VendingMachine(2);
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
    public void givenAddProductsOnNonExistingShelveExceptionThenCorrectMessage() throws VendingMachineException {
        try {
            vendingMachine.addProduct(COCA_COLA, 7);
        } catch (VendingMachineException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Shelve number: 7 does not exist. Choose shelve number between [0;1]");
        }
    }
}
