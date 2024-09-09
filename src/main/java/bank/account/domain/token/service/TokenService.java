package bank.account.domain.token.service;

import bank.account.domain.token.exception.TokenException;
import bank.account.domain.token.model.RefreshToken;
import bank.account.domain.token.model.TokenResponse;
import bank.account.domain.token.repository.RefreshTokenRepository;
import bank.account.global.common.code.ErrorCode;
import bank.account.global.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    public TokenResponse reissueAccessToken(HttpServletRequest request) {
        String refreshToken = jwtUtil.extractRefreshToken(request).orElseThrow(() -> new TokenException(ErrorCode.INVALID_REFRESH_TOKEN));
        String userId = jwtUtil.extractUserId(refreshToken);

        validateRefreshToken(userId, refreshToken);

        String accessToken = jwtUtil.createAccessToken(userId);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    /* 토큰이 존재하는지, 일치하는지, 유효한지 검증 */
    private void validateRefreshToken(String userId, String refreshToken) {

        Optional<RefreshToken> existRefreshTokenOpt = refreshTokenRepository.findByUserId(userId);
        if (!existRefreshTokenOpt.isPresent()) {
            throw new TokenException(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
        }

        if ((!existRefreshTokenOpt.get().getToken().equals(refreshToken))) {
            throw new TokenException(ErrorCode.INVALID_REFRESH_TOKEN);
        }
    }
}
