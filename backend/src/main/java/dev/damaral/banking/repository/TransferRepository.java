package dev.damaral.banking.repository;
import dev.damaral.banking.domain.Transfer;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TransferRepository extends JpaRepository<Transfer,UUID>{ List<Transfer> findBySourceAccountIdOrTargetAccountIdOrderByCreatedAtDesc(UUID source,UUID target); }
