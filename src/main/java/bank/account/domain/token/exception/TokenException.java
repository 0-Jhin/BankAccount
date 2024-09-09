package bank.account.domain.token.exception;


import bank.account.global.common.code.ErrorCode;
import bank.account.global.error.exception.BusinessException;

public class TokenException extends BusinessException {

    public TokenException(ErrorCode code) {
        super(code);
    }
}
