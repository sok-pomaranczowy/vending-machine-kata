package tdd.vendingMachine;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.exceptions.VendingMachineException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sok_pomaranczowy on 01.04.17.
 */
public class ChangeCalculatorTest {


    @Test
    public void givenChangeAmountThenReturnCoins() throws VendingMachineException {
        ArrayList<Coin> coins = new ArrayList<>();
        coins.add(Coin.FIVE);
        coins.add(Coin.TWO);
        coins.add(Coin.TWENTY_CENTS);
        coins.add(Coin.TWENTY_CENTS);
        coins.add(Coin.TWENTY_CENTS);
        coins.add(Coin.TEN_CENTS);
        ArrayList change = ChangeCalculator.calculateChange(new BigDecimal(5.5), coins);
        Assertions.assertThat(change.contains(Coin.FIVE));
        Assertions.assertThat(change.contains(Coin.TWENTY_CENTS));
        Assertions.assertThat(change.contains(Coin.TWENTY_CENTS));
        Assertions.assertThat(change.contains(Coin.TEN_CENTS));
    }

    @Test(expected = VendingMachineException.class)
    public void givenChangeAmountAndNoCoinsThenReiseException() throws VendingMachineException {
        ArrayList<Coin> coins = new ArrayList<>();
        coins.add(Coin.TWO);
        coins.add(Coin.TWENTY_CENTS);
        coins.add(Coin.TWENTY_CENTS);
        coins.add(Coin.TWENTY_CENTS);
        coins.add(Coin.TEN_CENTS);
        ChangeCalculator.calculateChange(new BigDecimal(5.5), coins);
    }

    @Test
    public void givenChangeAmountAndAllCoinsThenCorrectChange() throws VendingMachineException {
        ArrayList<Coin> coins = new ArrayList<>();
        coins.add(Coin.TWO);
        coins.add(Coin.TWENTY_CENTS);
        coins.add(Coin.TWENTY_CENTS);
        coins.add(Coin.TEN_CENTS);
        coins.add(Coin.TEN_CENTS);
        ArrayList change = ChangeCalculator.calculateChange(new BigDecimal(2.6), coins);
        Assertions.assertThat(change.size()).isEqualTo(5);
        Assertions.assertThat(change.contains(Coin.TWO));
        Assertions.assertThat(change.contains(Coin.TWENTY_CENTS));
        Assertions.assertThat(change.contains(Coin.TWENTY_CENTS));
        Assertions.assertThat(change.contains(Coin.TEN_CENTS));
        Assertions.assertThat(change.contains(Coin.TEN_CENTS));
    }

    @Test(expected = VendingMachineException.class)
    public void givenChangeAmountAndNotEnoughCoinsThenException() throws VendingMachineException {
        ArrayList<Coin> coins = new ArrayList<>();
        coins.add(Coin.TEN_CENTS);
        coins.add(Coin.TEN_CENTS);
        ArrayList change = ChangeCalculator.calculateChange(new BigDecimal(2.6), coins);
        Assertions.assertThat(change.size()).isEqualTo(5);
        Assertions.assertThat(change.contains(Coin.TWO));
        Assertions.assertThat(change.contains(Coin.TWENTY_CENTS));
        Assertions.assertThat(change.contains(Coin.TWENTY_CENTS));
        Assertions.assertThat(change.contains(Coin.TEN_CENTS));
        Assertions.assertThat(change.contains(Coin.TEN_CENTS));
    }

    @Test
    public void givenChangeAmountAndNotEnoughCoinsThenExceptionMesageIsCorrect(){
        ArrayList<Coin> coins = new ArrayList<>();
        coins.add(Coin.TEN_CENTS);
        coins.add(Coin.TEN_CENTS);
        try {
            ArrayList change = ChangeCalculator.calculateChange(new BigDecimal(2.6), coins);
        } catch (VendingMachineException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Not enough coins in the machine to give change. Need: 2.40 more.");
        }
    }
}
