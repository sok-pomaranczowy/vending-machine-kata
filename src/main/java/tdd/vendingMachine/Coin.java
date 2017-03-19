package tdd.vendingMachine;

/**
 * Created by sok_pomaranczowy on 19.03.17.
 */
public enum Coin {
    FIVE (5.0),
    TWO (2.0),
    ONE (1.0),
    FIFTY_CENTS (0.5),
    TWENTY_CENTS (0.2),
    TEN_CENTS (0.1);

    private final double value;
    Coin(double value) {
        this.value= value;
    }
    double getValue(){
        return value;
    }
}
