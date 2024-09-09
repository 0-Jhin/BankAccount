package bank.account.domain.user.dao;


import bank.account.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.userId = :userId")
    Optional<User> findByUserId(String userId);

    User findByProviderId(String providerId);
//    Optional<User> findByRefreshToken(String refreshToken);
}
