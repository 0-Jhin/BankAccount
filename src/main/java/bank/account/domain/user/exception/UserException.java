package bank.account.domain.user.exception;


import bank.account.global.common.code.ErrorCode;
import bank.account.global.error.exception.BusinessException;

public class UserException extends BusinessException {
    public UserException(ErrorCode code) {
        super(code);
    }
}

