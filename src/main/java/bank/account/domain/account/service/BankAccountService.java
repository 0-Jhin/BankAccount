package bank.account.domain.account.service;


import bank.account.domain.account.dto.BankAccountDto.BankAccountListResponse;
import bank.account.domain.account.dto.BankAccountDto.BankAccountCreateRequest;
import bank.account.domain.account.exception.BankAccountException;
import bank.account.domain.account.model.BankAccount;
import bank.account.domain.account.model.Money;
import bank.account.domain.account.repository.BankAccountRepository;
import bank.account.global.common.code.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private List<String> transactionHistory = new ArrayList<>();

    //계좌 생성
    @Transactional
    public void create(BankAccountCreateRequest request) throws IOException {
        BankAccount bankAccount = createBankAccount(request);
        String keyword = request.accountNumber();
        validateDuplicate(keyword);
        bankAccountRepository.save(bankAccount);
    }//+중복확인
    private void validateDuplicate(String keyword) {
        List<BankAccount> bankAccountList = bankAccountRepository.findByNumberContains(keyword);
        if (!bankAccountList.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //계좌 생성 보조
    private static BankAccount createBankAccount(BankAccountCreateRequest request){
        Money money = Money.builder()
                .accountMoney(request.accountMoney())
                .deposit(request.deposit())
                .withdrawal(request.withdrawal())
                .build();

        BankAccount bankAccount = BankAccount.builder()
                .accountName(request.accountName())
                .accountNumber(request.accountNumber())
                .accountPassWord(request.accountPassWord())
                .money(money)
                .build();
        return bankAccount;
    }

    //계좌 비밀번호 업데이트
    @Transactional
    public void updateBankAccount(Long accountId, BankAccountCreateRequest request){
        BankAccount findBankAccount = findById(accountId);
        findBankAccount.updatePassWord(request);
    }
    //계좌 잔고 확인

    //계좌 입금
    @Transactional
    public void updateDeposit(Long accountId, BankAccountCreateRequest request){
        BankAccount findBankAccount = findById(accountId);
        Long amount = request.deposit();
        String record = "Deposited: " + amount;
        findBankAccount.addTransactionHistory(record);
        findBankAccount.updateMoney(request, amount);

    }
    //계좌 출금
    @Transactional
    public void updateWithdrawal(Long accountId, BankAccountCreateRequest request){
        BankAccount findBankAccount = findById(accountId);
        Long amount = request.withdrawal();
        String record = "Withdrawn: " + amount;
        findBankAccount.addTransactionHistory(record);
        findBankAccount.updateMoney(request,amount*(-1));
    }

    //거래 내역 생성 //?
    @Transactional
    public void updateTransactionHistory(Long accountId, BankAccountCreateRequest request){
    }

    //ID기반으로 계좌 찾기
    public BankAccount findById(Long accountId){
        return bankAccountRepository.findById(accountId).orElseThrow(()->new BankAccountException(ErrorCode.BANK_ACCOUNT_NOT_FOUND));
    }

    //모든 계좌 찾기(관리자 권한)
    public List<BankAccountListResponse> findBankAccounts(){
        List<BankAccount> bankAccounts =bankAccountRepository.findAll();
        return bankAccounts.stream().map(BankAccountListResponse::of).toList();
    }

    //계좌 삭제
    @Transactional
    public void delete(Long bankAccountId){bankAccountRepository.deleteById(bankAccountId);}

    //계좌 검색, like 검색 사용
    @Transactional
    public List<BankAccountListResponse> search(String keyword) {
        List<BankAccount> bankAccountList;

        // 입력된 키워드가 숫자로만 이루어졌다면 계좌 번호로 검색, 아니면 이름으로 검색
        if (isNumeric(keyword)) {
            bankAccountList = bankAccountRepository.findByNumberContains(keyword);
        } else {
            bankAccountList = bankAccountRepository.findByNameContains(keyword);
        }

        return bankAccountList.stream()
                .map(BankAccountListResponse::of)
                .collect(Collectors.toList());
    }
    private boolean isNumeric(String str) {
        return str != null && str.matches("[0-9]+");
    }
    //수정 전 코드
//    @Transactional
//    public List<BankAccountListResponse> searchName(String keyword){
//        List<BankAccount> bankAccountList = bankAccountRepository.findByNameContains(keyword);
//        return bankAccountList.stream().map(BankAccountListResponse::of).toList();
//    }
//    @Transactional
//    public List<BankAccountListResponse> searchNumber(String keyword){
//        List<BankAccount> bankAccountList = bankAccountRepository.findByNumberContains(keyword);
//        return bankAccountList.stream().map(BankAccountListResponse::of).toList();
//    }//위의 두개 합쳐야함
}
