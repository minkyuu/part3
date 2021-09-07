package account;

import bank.Bank;

import java.math.BigDecimal;

//TODO: SavingAccount는 Account에서 상속을 받습니다.
public class SavingAccount extends Account{
    //TODO: 적금 계좌 클래스의 속성은 목표금액 속성을 추가로 가집니다.
    private BigDecimal goalAmount;

    public SavingAccount(){
        //TODO: 카테고리를 S로 설정해 줍니다.
        this.isActive = true;
        this.category = "S";
    }

    public SavingAccount(String accNo, String owner, BigDecimal balance, BigDecimal goalAmount) {
        this.category = "S";
        this.accNo = accNo;
        this.owner = owner;
        this.balance = balance;
        this.goalAmount = goalAmount;
        this.isActive = true;
    }

    //TODO: GoalAmount getter 구현
    public BigDecimal getGoalAmount() {
        return goalAmount;
    }

    //TODO: getAccountInfo를 재정의하여 "목표 금액"도 노출해줍니다.
    public void getAccountInfo(Account account) {
        System.out.printf("계좌종류: %s | 계좌번호: %s | 계좌주명: %s | 잔액: %s원 | 목표 금액: %s원\n", account.getCategory(), account.getAccNo(), account.getOwner(), account.getBalance(), ((SavingAccount) account).getGoalAmount());
    }

    // 목표 금액을 달성할 경우
    @Override
    public BigDecimal deposit(BigDecimal amount) {
        amount = checkAmount(amount, "입금");
        setBalance(this.balance.add(amount));

        if (this.balance.compareTo(goalAmount) != -1)
            System.out.println("축하드립니다. 목표 금액을 달성하셨습니다!");

        return amount;
    }
}
