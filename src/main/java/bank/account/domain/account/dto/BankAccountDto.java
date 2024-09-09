package bank.account.domain.account.dto;

import bank.account.domain.account.model.BankAccount;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;

public class BankAccountDto {
    //Request
    public record BankAccountCreateRequest(
            Long accountId,
            String accountName,
            String accountNumber,
            String accountPassWord,
            Long accountMoney,
            Long deposit,
            Long withdrawal
            //boolean balanceCheck,
            //String transactionHistory
    ){}

    //Response
    @Builder
    public record BankAccountListResponse(Long accountId, String accountName, String accountNumber, Long accountMoney){
        public static BankAccountListResponse of(BankAccount bankAccount){
            return BankAccountListResponse
                    .builder()
                    .accountId(bankAccount.getAccountId())
                    .accountName(bankAccount.getAccountName())
                    .accountNumber(bankAccount.getAccountNumber())
                    .accountMoney(bankAccount.getMoney().getAccountMoney())
                    .build();
        }
    }

    @Builder
    public record BankAccountDetailResponse(
            Long accountId,
            String accountName,
            String accountNumber,
            String accountPassWord,
            Long accountMoney,
            Long deposit,
            Long withdrawal
//            boolean balanceCheck,
//            String transactionHistory
    ){
        public static BankAccountDetailResponse of(BankAccount bankAccount){
            return BankAccountDetailResponse.builder()
                    .accountId(bankAccount.getAccountId())
                    .accountName(bankAccount.getAccountName())
                    .accountNumber(bankAccount.getAccountNumber())
                    .accountPassWord(bankAccount.getAccountPassWord())
                    .accountMoney(bankAccount.getMoney().getAccountMoney())
                    .deposit(bankAccount.getMoney().getDeposit())
                    .withdrawal(bankAccount.getMoney().getWithdrawal())
                    //.balanceCheck(bankAccount.getTransaction().isBalanceCheck())
                    //.transactionHistory(bankAccount.getTransaction().getTransactionHistory())
                    .build();
        }
    }
}
