package bank.account.domain.account.model;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Embeddable
@Getter
public class Money {
    Long accountMoney;
    Long deposit;
    Long withdrawal;

    protected Money(){}

    @Builder
    public Money(Long accountMoney, Long deposit, Long withdrawal){
        this.accountMoney = accountMoney;
        this.deposit = deposit;
        this.withdrawal = withdrawal;
    }
}
