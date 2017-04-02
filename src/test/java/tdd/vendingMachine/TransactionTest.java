package tdd.vendingMachine;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by sok_pomaranczowy on 02.04.17.
 */
public class TransactionTest {
    Transaction transaction;

    @Before
    public void onSetUp() throws NoSuchFieldException, IllegalAccessException {
        Field instance = Transaction.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
        transaction = Transaction.getInstance();
    }

    @Test
    public void givenNewInstanceOfSingletonThenIsIsEqualToExistingOne(){
        Assertions.assertThat(transaction).isEqualTo(Transaction.getInstance());
    }

    @Test
    public void givenCoinThenBalanceIncreases(){
        transaction.addCoin(Coin.FIFTY_CENTS);
        Assertions.assertThat(transaction.getBalance()).isEqualTo(Coin.FIFTY_CENTS.getValue());
    }

    @Test
    public void givenCoinThenHasCoin(){
        transaction.addCoin(Coin.FIFTY_CENTS);
        Assertions.assertThat(transaction.getCoins()).hasSize(1);
        Assertions.assertThat(transaction.getCoins()).contains(Coin.FIFTY_CENTS);
    }

    @Test
    public void givenResetThenHasNoCoin(){
        transaction.addCoin(Coin.FIFTY_CENTS);
        transaction.reset();
        Assertions.assertThat(transaction.getCoins()).hasSize(0);
    }

    @Test
    public void givenResetThenBalanceIsZero(){
        transaction.addCoin(Coin.FIFTY_CENTS);
        transaction.reset();
        Assertions.assertThat(transaction.getBalance()).isZero();
    }
}
