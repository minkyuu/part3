package bank;

import account.FailToAchieveGoalAmountException;
import account.SavingAccount;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.NoSuchElementException;

public class SavingBank extends Bank {

    public void withdraw(SavingAccount account) throws Exception{
        // TODO: Account의 출금 메서드에서 잔액/목표 금액 체크하여 조금 다르게 구현
        // throws Exception 적금 계좌는 잔액이 목표 금액(%s원) 이상이어야 출금 가능합니다.
        if (account.getBalance().compareTo(account.getGoalAmount()) == -1){
            System.out.printf("적금 계좌는 잔액이 목표 금액(%s원) 이상이어야 출금 가능합니다.\n", account.getGoalAmount());
            throw new FailToAchieveGoalAmountException();
        }

        System.out.println("\n출금할 금액을 입력하세요.");
        BigDecimal withdrawAmount = new BigDecimal(scanner.nextLine());

        try {
            account.withdraw(withdrawAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO: 목표금액을 입력받아서 SavingAccount 객체 생성하도록 재정의
    @Override
    public SavingAccount createAccount() throws NoSuchElementException{
        try{
            // 계좌번호 채번
            // 계좌번호는 "0000"+증가한 seq 포맷을 가진 번호입니다.
            df = new DecimalFormat("0000");
            String accNo = df.format(++seq);

            System.out.print("이름을 입력해주세요 : ");
            String owner = scanner.nextLine();

            System.out.print("생성 계좌에 입금할 금액을 입력해주세요 : ");
            BigDecimal balance = new BigDecimal(scanner.nextLine());

            System.out.print("적금 계좌의 목표 금액을 입력해주세요 : ");
            BigDecimal goalAmount = new BigDecimal(scanner.nextLine());

            SavingAccount account = new SavingAccount(accNo, owner, balance, goalAmount);
            accountList.add(account);

            System.out.printf("%s님 계좌가 발급되었습니다.\n\n", owner);
            return account;
        }catch (NoSuchElementException e){
            //TODO: 오류 throw
            seq--;
            throw new NoSuchElementException();
        }
    }

}