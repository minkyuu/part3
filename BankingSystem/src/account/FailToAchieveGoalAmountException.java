package account;

public class FailToAchieveGoalAmountException extends RuntimeException {
    public FailToAchieveGoalAmountException() {
    }

    public FailToAchieveGoalAmountException(String message) {
        super(message);
    }
}
