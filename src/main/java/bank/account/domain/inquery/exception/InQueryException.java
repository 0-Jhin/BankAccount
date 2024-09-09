package bank.account.domain.inquery.exception;


import bank.account.global.common.code.ErrorCode;
import bank.account.global.error.exception.BusinessException;

public class InQueryException extends BusinessException {
    public InQueryException(ErrorCode code) {
        super(code);
    }
}

