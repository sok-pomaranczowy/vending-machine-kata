package tdd.vendingMachine;

import tdd.vendingMachine.exceptions.VendingMachineException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sok_pomaranczowy on 19.03.17.
 */
public class ChangeCalculator {
    public static ArrayList calculateChange(BigDecimal change, List<Coin> coins) throws VendingMachineException{
        Collections.sort(coins);
        BigDecimal changeAmount = change.setScale(2,RoundingMode.HALF_UP);
        ArrayList<Coin> changeCoins = new ArrayList();
        for (int i = 0; ((i < coins.size()) && (changeAmount.compareTo(BigDecimal.ZERO) > 0)); i++) {
            BigDecimal coinValue = coins.get(i).getValue().setScale(2, RoundingMode.HALF_UP);
            if (changeAmount.compareTo(coinValue) >= 0) {
                changeCoins.add(getCoinForAmount(coinValue));
                changeAmount = changeAmount.subtract(coinValue);
            }
        }

        if(changeAmount.compareTo(BigDecimal.ZERO) > 0){
            throw new VendingMachineException("Not enough coins in the machine to give change. Need: "+changeAmount+" more.");
        }

        return changeCoins;
    }
    private static Coin getCoinForAmount(BigDecimal amount){
        if(amount.compareTo(new BigDecimal(0.1))==0){
            return Coin.TEN_CENTS;
        }else if(amount.compareTo(new BigDecimal(0.2))==0){
            return Coin.TWENTY_CENTS;
        }else if(amount.compareTo(new BigDecimal(0.5))==0){
            return Coin.FIFTY_CENTS;
        }else if(amount.compareTo(new BigDecimal(1.0))==0){
            return Coin.ONE;
        }else if(amount.compareTo(new BigDecimal(2.0))==0){
            return Coin.TWO;
        }else {
            return Coin.FIVE;
        }
    }
}
