package dev.damaral.banking.service;
import dev.damaral.banking.domain.Account;
import dev.damaral.banking.repository.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.math.BigDecimal;
import java.util.*;
class BankingServiceTest {
 AccountRepository accounts=mock(AccountRepository.class);TransferRepository transfers=mock(TransferRepository.class);
 BankingService svc=new BankingService(accounts,transfers);
 @Test void rejectsSameAccount(){UUID id=UUID.randomUUID();assertThrows(IllegalArgumentException.class,()->svc.transfer(id,id,BigDecimal.ONE));}
 @Test void rejectsNegativeAmount(){assertThrows(IllegalArgumentException.class,()->svc.transfer(UUID.randomUUID(),UUID.randomUUID(),new BigDecimal("-1")));}
 @Test void rejectsInsufficientFunds(){Account a=new Account(UUID.randomUUID(),"A","A","USD",new BigDecimal("1"));assertThrows(IllegalArgumentException.class,()->a.debit(new BigDecimal("2")));}
 @Test void doesNotCallRepositoryForInvalidAmount(){assertThrows(IllegalArgumentException.class,()->svc.transfer(UUID.randomUUID(),UUID.randomUUID(),new BigDecimal("0.001")));verifyNoInteractions(accounts,transfers);}
}
