package bank.account.domain.user.application;

import bank.account.domain.user.dao.UserRepository;
import bank.account.domain.user.exception.UserException;
import bank.account.domain.user.model.User;
import bank.account.global.common.code.ErrorCode;
import bank.account.global.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserException(ErrorCode.USER_NOT_FOUND)
        );
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(
                () -> new UserException(ErrorCode.USER_NOT_FOUND)
        );
    }

    public User getUserInfo(HttpServletRequest request) {
        return jwtUtil.getUserFromRequest(request);
    }
}
