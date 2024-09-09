package bank.account.domain.account.repository;

import bank.account.domain.account.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByNameContains(String keyword);
    List<BankAccount> findByNumberContains(String keyword);
}
