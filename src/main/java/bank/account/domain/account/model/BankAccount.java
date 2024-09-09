package bank.account.domain.account.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static bank.account.domain.account.dto.BankAccountDto.BankAccountCreateRequest;

@Getter
@DiscriminatorColumn(name = "dtype")
@Entity
public class BankAccount {
    private List<String> transactionHistory = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long accountId;
    String accountName;
    String accountNumber;
    String accountPassWord;

    @Embedded
    private Money money;


    public BankAccount(){}

    public void addTransactionHistory(String record) {
        transactionHistory.add(record);
    }

    // 계좌 잔액 업데이트 등 로직
    public void updateMoney(BankAccountCreateRequest request, Long amount) {
        this.money.accountMoney = request.accountMoney()+amount;
    }
    // 계좌 비밀번호 업데이트
    public void updatePassWord(BankAccountCreateRequest request){
        this.accountPassWord = request.accountPassWord();
    }

    @Builder
    public BankAccount(Long accountId, String accountNumber, String accountName, String accountPassWord, Money money){
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountPassWord = accountPassWord;
        this.money = money;
    }
}
