package bank.account.domain.account.exception;

import bank.account.global.common.code.ErrorCode;
import bank.account.global.error.exception.BusinessException;

public class BankAccountException extends BusinessException {
    public BankAccountException(ErrorCode code) {
        super(code);
    }
}
