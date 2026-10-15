package dev.damaral.banking.api;
import dev.damaral.banking.domain.*;
import dev.damaral.banking.service.BankingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1") @CrossOrigin(origins="http://localhost:5173") public class BankingController {
 private final BankingService banking;public BankingController(BankingService banking){this.banking=banking;}
 @GetMapping("/accounts") public List<Account> accounts(){return banking.accounts();}
 @GetMapping("/accounts/{id}") public Account account(@PathVariable UUID id){return banking.account(id);}
 @GetMapping("/accounts/{id}/transfers") public List<Transfer> history(@PathVariable UUID id){return banking.history(id);}
 @PostMapping("/transfers") public Transfer transfer(@Valid @RequestBody TransferRequest request){return banking.transfer(request.sourceAccountId(),request.targetAccountId(),request.amount());}
 public record TransferRequest(@NotNull UUID sourceAccountId,@NotNull UUID targetAccountId,@NotNull @DecimalMin("0.01") @Digits(integer=17,fraction=2) BigDecimal amount){}
}
