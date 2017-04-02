package tdd.vendingMachine;

import java.math.BigDecimal;

/**
 * Created by sok_pomaranczowy on 19.03.17.
 */
public enum Coin {
    FIVE(new BigDecimal(5.0)),
    TWO(new BigDecimal(2.0)),
    ONE(new BigDecimal(1.0)),
    FIFTY_CENTS(new BigDecimal(0.5)),
    TWENTY_CENTS(new BigDecimal(0.2)),
    TEN_CENTS(new BigDecimal(0.1));

    private final BigDecimal value;

    Coin(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

}
