package account.exception;

public class BalanceEmptyException extends RuntimeException {

    public BalanceEmptyException(String message) {
        super(message);
    }

}
