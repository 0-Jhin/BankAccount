package bank.account.domain.account.controller;

import bank.account.domain.account.dto.BankAccountDto.BankAccountCreateRequest;
import bank.account.domain.account.dto.BankAccountDto.BankAccountListResponse;
import bank.account.domain.account.dto.BankAccountDto.BankAccountDetailResponse;
import bank.account.domain.account.model.BankAccount;
import bank.account.domain.account.service.BankAccountService;
import bank.account.global.common.code.SuccessCode;
import bank.account.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BankAccountRestController {

    private final BankAccountService bankAccountService;

    //계좌 등록
    @PostMapping("/bankAccount")
    public ResponseEntity<ApiResponse<Object>> createBankAccount(@RequestPart(name = "request") BankAccountCreateRequest request) throws IOException {
        bankAccountService.create(request);
        return ApiResponse.onSuccess(SuccessCode._CREATED);
    }//계좌번호 중복확인 첨가하기

    //계좌 목록 조회(관리자)
    @GetMapping("/admin/bankAccount")
    public ResponseEntity<ApiResponse<Object>> listALLBankAccount(){
        List<BankAccountListResponse> bankAccounts = bankAccountService.findBankAccounts();

        return ApiResponse.onSuccess(SuccessCode._OK, bankAccounts);
    }

    //계좌 단일 조회(개인회원)
    @GetMapping("/individual/bankAccount/{bankAccountId}")
    public ResponseEntity<ApiResponse<Object>> getBankAccountById(@PathVariable Long bankAccountId) {
        BankAccount bankAccount = bankAccountService.findById(bankAccountId);

        BankAccountDetailResponse bankAccountDetailResponse = BankAccountDetailResponse.of(bankAccount);

        return ApiResponse.onSuccess(SuccessCode._OK, bankAccountDetailResponse);
    }

    //비밀번호 수정(개인회원)
    @PutMapping("/individual/bankAccount/{bankAccountId}")
    public ResponseEntity<ApiResponse<Object>> updateBankAccount(
            @PathVariable Long bankAccountId,
            @RequestPart(name = "request") BankAccountCreateRequest request) {

        bankAccountService.updateBankAccount(bankAccountId, request);

        return ApiResponse.onSuccess(SuccessCode._OK);
    }

    //계좌 삭제(개인회원)
    @DeleteMapping("/individual/bankAccount/{bankAccountId}")
    public ResponseEntity<ApiResponse<Object>> deleteBankAccount(
            @PathVariable Long bankAccountId
    ) {
        bankAccountService.delete(bankAccountId);

        return ApiResponse.onSuccess(SuccessCode._OK);
    }
    //계좌검색
    @GetMapping("/bankAccount/search")
    public ResponseEntity<ApiResponse<List<BankAccountListResponse>>> searchBankAccount(
            @RequestParam(value = "keyword", required = true) String keyword) {

        List<BankAccountListResponse> response = bankAccountService.search(keyword);

        return ApiResponse.onSuccess(SuccessCode._OK, response);
    }
    //수정 전 코드
//    @GetMapping("/bankAccount/search")
//    public ResponseEntity<ApiResponse<List<BankAccountListResponse>>> searchLawyer(
//            @RequestParam(value = "keyword", required = true, defaultValue = "") String keyword) {
//        if (keyword == null || keyword.trim().isEmpty()) {
//            return ApiResponse.onSuccess(SuccessCode._OK, Collections.emptyList());
//        }
//        if(keyword.matches("\\d+")){
//            List<BankAccountListResponse> searchList = bankAccountService.searchName(keyword);
//            return ApiResponse.onSuccess(SuccessCode._OK, searchList);}
//        else{
//            List<BankAccountListResponse> searchList = bankAccountService.searchName(keyword);
//            return ApiResponse.onSuccess(SuccessCode._OK, searchList);}
//    }


    //잔고조회
    @GetMapping("individual/bankAccount/{bankAccountId}")
    public ResponseEntity<ApiResponse<Object>> AccountBalance(@PathVariable Long accountId){
        BankAccount bankAccount = bankAccountService.findById(accountId);

        BankAccountDetailResponse bankAccountDetailResponse = BankAccountDetailResponse.of(bankAccount);
        return ApiResponse.onSuccess(SuccessCode._OK, bankAccountDetailResponse.accountMoney());
    }

    //입금
    @PutMapping("/individual/bankAccount/Deposit/{bankAccountId}")
    public ResponseEntity<ApiResponse<Object>> DepositBankAccount(
            @PathVariable Long bankAccountId,
            @RequestPart(name = "request") BankAccountCreateRequest request) {

        bankAccountService.updateDeposit(bankAccountId,request);

        return ApiResponse.onSuccess(SuccessCode._OK);
    }
    //출금
    @PutMapping("/individual/bankAccount/Withdrawal/{bankAccountId}")
    public ResponseEntity<ApiResponse<Object>> WithdrawalBankAccount(
            @PathVariable Long bankAccountId,
            @RequestPart(name = "request") BankAccountCreateRequest request) {

        bankAccountService.updateWithdrawal(bankAccountId,request);

        return ApiResponse.onSuccess(SuccessCode._OK);
    }
    //거래내역
    @GetMapping("/individual/bankAccount/transactionHistory/{bankAccountId}")
    public ResponseEntity<ApiResponse<Object>>getTransactionHistory(@PathVariable Long accountId){
        BankAccount bankAccount = bankAccountService.findById(accountId);
        return ApiResponse.onSuccess(SuccessCode._OK, bankAccount.getTransactionHistory());
    }

}
