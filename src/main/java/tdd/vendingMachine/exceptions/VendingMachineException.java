package tdd.vendingMachine.exceptions;

/**
 * Created by sok_pomaranczowy on 19.03.17.
 */
public class VendingMachineException extends Exception {
    public VendingMachineException() { super(); }
    public VendingMachineException(String message) { super(message); }
    public VendingMachineException(String message, Throwable cause) { super(message, cause); }
    public VendingMachineException(Throwable cause) { super(cause); }
}
