package tdd.vendingMachine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sok_pomaranczowy on 02.04.17.
 */
public class Transaction {
    private static Transaction instance = null;
    private List<Coin> coins;
    private BigDecimal balance;

    private Transaction() {
        this.coins= new ArrayList<>();
        this.balance = new BigDecimal(.0);
    }

    public static Transaction getInstance() {
        if (instance == null) {
            instance = new Transaction();
        }
        return instance;
    }

    public void reset() {
        this.coins = new ArrayList<>();
        this.balance = new BigDecimal(.0);
    }

    public void addCoin(Coin c) {
        coins.add(c);
        balance = balance.add(c.getValue());
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
