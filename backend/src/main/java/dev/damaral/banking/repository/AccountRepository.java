package dev.damaral.banking.repository;
import dev.damaral.banking.domain.Account;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import jakarta.persistence.LockModeType;
import java.util.Optional;
public interface AccountRepository extends JpaRepository<Account,UUID>{
 @Lock(LockModeType.PESSIMISTIC_WRITE) @org.springframework.data.jpa.repository.Query("select a from Account a where a.id=:id") Optional<Account> findLockedById(@org.springframework.data.repository.query.Param("id") UUID id);
}
