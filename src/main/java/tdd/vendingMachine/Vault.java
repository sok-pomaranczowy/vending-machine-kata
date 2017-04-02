package tdd.vendingMachine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sok_pomaranczowy on 19.03.17.
 */
public class Vault {
    private List<Coin> vault;

    public Vault() {
        vault = new ArrayList<>();
    }

    public Vault(List<Coin> vault) {
        this.vault = vault;
    }

    public void addCoin(Coin c) {
        vault.add(c);
    }

    public List<Coin> getVault() {
        return vault;
    }

    public BigDecimal getTotalAmountHeld(){
        BigDecimal sum= BigDecimal.ZERO;
        for(Coin c : vault){
            sum = sum.add(c.getValue());
        }
        return sum;
    }
}
