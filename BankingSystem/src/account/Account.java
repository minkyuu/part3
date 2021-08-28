package account;

import bank.Bank;

import java.math.BigDecimal;

public class Account {
    //TODO: 일반 계좌 클래스의 속성은 계좌종류(N: 예금계좌, S:적금계좌), 계좌번호, 소유자, 잔액, 활성화 여부 5가지 입니다.
    protected String category;            // String 계좌 종류
    protected String accNo;               // String 계좌번호
    protected String owner;               // String 소유자
    protected BigDecimal balance;         // BigDecimal 잔액
    protected boolean isActive;           // boolean 활성화 여부

    public Account() {
        //TODO: 일반 계좌의 활성화 여부를 True로, 계좌 종류를 "N"(NORMAL을 의미) 설정해줍니다.
        this.isActive = true;
        this.category = "N";
    }
    public Account(String accNo, String owner, BigDecimal balance) {
        //TODO
        this.category = "N";
        this.accNo = accNo;
        this.owner = owner;
        this.balance = balance;
        this.isActive = true;
    }

    public void getAccountInfo(Account account){
        //TODO: 계좌의 기본 정보를 아래 형태로 출력해줍니다.
        //계좌종류: %s | 계좌번호: %s | 계좌주명: %s | 잔액: %s원
        String category = account.getCategory();
        String accNo = account.getAccNo();
        String owner = account.getOwner();
        BigDecimal balance = account.getBalance();

        System.out.printf("계좌종류: %s | 계좌번호: %s | 계좌주명: %s | 잔액: %s원\n", category, accNo, owner, balance);
    }

    public BigDecimal withdraw(BigDecimal amount) throws Exception{
        //TODO: 출금액을 받아서 출금하는 기본 메소드입니다. this를 이용해 구현해보세요.
        if (balance.intValue() == 0){
            throw new BalanceEmptyException("잔고가 비어있습니다.");
        }

        if (balance.compareTo(amount) == -1){
            throw new LackofBalanceException("잔액이 부족합니다.");
        }

        balance.subtract(amount);

        return amount;
    }
    public BigDecimal deposit(BigDecimal amount){
        //TODO: 입금액을 받아서 입금하는 기본 메소드입니다. this를 이용해 구현해보세요.
        return amount;
    }

    // TODO: 일반 계좌 클래스의 각 속성에 getter/setter를 제공합니다.
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

