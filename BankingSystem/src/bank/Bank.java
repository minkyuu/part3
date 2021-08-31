package bank;

import account.Account;
import account.SavingAccount;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Bank {
    //TODO: Bank 클래스는 출금, 입금, 송금, 계좌 생성, 계좌 검색 기능들을 갖고 있습니다.
    protected static Scanner scanner = new Scanner(System.in);
    protected static int seq = 0;
    public static DecimalFormat df = new DecimalFormat("#,###");

    protected HashMap<String, Account> accountList = new HashMap<String, Account>();

    // 뱅킹 시스템의 기능들

    //TODO: 출금 메서드 구현
    public void withdraw() throws Exception {
        // TODO: 계좌 조회 (계좌번호 입력)
        Account account = getAccountByAccNo("출금");

        // TODO: interestCalculators 이용하여 이자 조회 및 출금
        BigDecimal interest = calculateInterest(account);
        System.out.printf("이자는 %s원입니다.",interest);

        System.out.println("\n출금할 금액을 입력하세요.");
        BigDecimal withdrawAmount = new BigDecimal(scanner.nextLine());

        // TODO: 검색 -> 적금 계좌이면 적금 계좌의 출금 메소드 호출 -> 완료시 break
        try {
            if (account.getCategory() == "S"){
                ((SavingBank)this).withdraw((SavingAccount) account);
            } else {
                account.withdraw(withdrawAmount);   // 출금처리
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO: 입금 메서드 구현
    public void deposit(){
        // TODO: 존재하지 않는 계좌이면 다시 물어보기
        Account account = getAccountByAccNo("입금");

        // TODO: 입금 처리
        System.out.println("\n입금할 금액을 입력하세요.");
        BigDecimal depositAmount = new BigDecimal(scanner.nextLine());
        account.deposit(depositAmount);
    }

    public Account createAccount() throws InputMismatchException {
        //TODO: 계좌 생성하는 메서드 구현
        try {
            // 계좌번호 채번
            // 계좌번호는 "0000"+증가한 seq 포맷을 가진 번호입니다.
            df = new DecimalFormat("0000");
            String accNo = df.format(++seq);
            String owner = scanner.nextLine();
            BigDecimal balance = new BigDecimal(scanner.nextLine());

            Account account = new Account(accNo, owner, balance);
            accountList.put(accNo, account);

            //TODO
            System.out.printf("\n%s님 계좌가 발급되었습니다.\n", owner);
            return account;

        }catch (InputMismatchException e){
            //TODO: 오류 throw
            throw new InputMismatchException();
        }
    }

    public Account findAccount(String accNo) throws NoSuchElementException {
        //TODO: 계좌리스트에서 찾아서 반환하는 메서드 구현
        Account account = accountList.get(accNo);
        if (account == null)
            throw new NoSuchElementException();

        return account;
    }

    public void transfer() throws Exception{
        //TODO: 송금 메서드 구현
        // 잘못 입력하거나 예외처리시 다시 입력가능하도록
        //TODO
        System.out.println("\n송금하시려는 계좌번호를 입력해주세요.");
        //TODO
        System.out.println("\n어느 계좌번호로 보내시려나요?");
        //TODO
        System.out.println("\n본인 계좌로의 송금은 입금을 이용해주세요.");
        //TODO
        System.out.println("\n적금 계좌로는 송금이 불가합니다.");
        //TODO
        System.out.println("\n송금할 금액을 입력하세요.");
        //TODO
    }

    private Account getAccountByAccNo(String command) {
        Account account;
        while(true){
            System.out.printf("%s하시려는 계좌번호를 입력하세요.\n", command);
            String accNo = scanner.nextLine();

            try {
                account = findAccount(accNo);
                break;
            } catch (NoSuchElementException e) {
                System.out.println("입력한 계좌번호와 동일한 계좌가 존재하지 않습니다.");
            }
        }
        return account;
    }

    private BigDecimal calculateInterest(Account account) {
        //TODO: key, value 형태의 HashMap을 이용하여 interestCalculators 구현
        //여기서 key: category, value: 각 category의 InterestCalculator 인스턴스
        HashMap<String, InterestCalculator> interestCalculators = new HashMap<String, InterestCalculator>();
        interestCalculators.put("N", new BasicInterestCalculator());
        interestCalculators.put("S", new SavingInterestCalculator());
        InterestCalculator interestCalculator = interestCalculators.get(account.getCategory());

        // interest 계산 후 반환
        BigDecimal result = interestCalculator.getInterest(account.getBalance());
        return result;
    }
}
