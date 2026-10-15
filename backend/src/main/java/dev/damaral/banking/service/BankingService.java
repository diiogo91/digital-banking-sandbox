package dev.damaral.banking.service;
import dev.damaral.banking.domain.*;
import dev.damaral.banking.repository.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service public class BankingService {
 private final AccountRepository accounts; private final TransferRepository transfers;
 public BankingService(AccountRepository accounts,TransferRepository transfers){this.accounts=accounts;this.transfers=transfers;}
 @Transactional(readOnly=true) public List<Account> accounts(){return accounts.findAll();}
 @Transactional(readOnly=true) public Account account(UUID id){return accounts.findById(id).orElseThrow(()->new NoSuchElementException("Account not found"));}
 @Transactional(readOnly=true) public List<Transfer> history(UUID id){ account(id);return transfers.findBySourceAccountIdOrTargetAccountIdOrderByCreatedAtDesc(id,id);}
 @Transactional public Transfer transfer(UUID sourceId,UUID targetId,BigDecimal amount){
  if(sourceId.equals(targetId))throw new IllegalArgumentException("Source and destination must differ");
  if(amount==null || amount.signum()<=0 || amount.scale()>2)throw new IllegalArgumentException("Amount must be positive with at most 2 decimals");
  // Lock in deterministic order to reduce deadlock risk.
  UUID first=sourceId.compareTo(targetId)<0?sourceId:targetId;UUID second=first.equals(sourceId)?targetId:sourceId;
  Account a=accounts.findLockedById(first).orElseThrow(()->new NoSuchElementException("Account not found"));
  Account b=accounts.findLockedById(second).orElseThrow(()->new NoSuchElementException("Account not found"));
  Account source=a.getId().equals(sourceId)?a:b;Account target=a.getId().equals(targetId)?a:b;
  if(!source.getCurrency().equals(target.getCurrency())) throw new IllegalArgumentException("Currency mismatch");
  source.debit(amount);target.credit(amount);
  return transfers.save(new Transfer(UUID.randomUUID(),sourceId,targetId,amount,Instant.now()));
 }
}
