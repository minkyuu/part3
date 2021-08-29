package account;

public class LackofBalanceException extends RuntimeException{
    public LackofBalanceException(String s) {
        super(s);
    }
}
