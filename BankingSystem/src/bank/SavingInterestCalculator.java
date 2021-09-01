package bank;

import java.math.BigDecimal;
import java.math.RoundingMode;

// TODO: InterestCalculator 인터페이스 구현한 SavingInterestCalculator 클래스
public class SavingInterestCalculator implements InterestCalculator{
    public BigDecimal getInterest(BigDecimal balance) {
        BigDecimal interest;

        // TODO: 적금 계좌의 경우 잔액이 100만원 이상은 이자율이 50%, 그 외에는 1% 입니다.
        if (balance.compareTo(new BigDecimal("1000000")) != -1) {
            interest = balance.multiply(new BigDecimal("0.5"));
        } else {
            interest = balance.multiply(new BigDecimal("0.01"));
        }

        return interest.setScale(0, RoundingMode.HALF_EVEN);
    }
}
