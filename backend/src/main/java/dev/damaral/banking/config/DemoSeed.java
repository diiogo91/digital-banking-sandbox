package dev.damaral.banking.config;
import dev.damaral.banking.domain.Account;
import dev.damaral.banking.repository.AccountRepository;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.boot.*;
import org.springframework.context.annotation.*;
@Configuration public class DemoSeed {
 @Bean CommandLineRunner seed(AccountRepository repo){return args->{if(repo.count()==0){repo.save(new Account(UUID.fromString("11111111-1111-1111-1111-111111111111"),"Alex Morgan","DEMO-10001","USD",new BigDecimal("12500.00")));repo.save(new Account(UUID.fromString("22222222-2222-2222-2222-222222222222"),"Sam Rivera","DEMO-10002","USD",new BigDecimal("4200.00")));repo.save(new Account(UUID.fromString("33333333-3333-3333-3333-333333333333"),"Taylor Lee","DEMO-10003","USD",new BigDecimal("7000.00")));}};}
}
