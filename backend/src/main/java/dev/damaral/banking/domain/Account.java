package dev.damaral.banking.domain;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
@Entity @Table(name="accounts") public class Account {
 @Id private UUID id;
 @Column(name="holder_name",nullable=false) private String holderName;
 @Column(name="account_number",nullable=false,unique=true) private String accountNumber;
 @Column(nullable=false) private String currency;
 @Column(nullable=false,precision=19,scale=2) private BigDecimal balance;
 @Version private long version;
 protected Account() {}
 public Account(UUID id,String holderName,String accountNumber,String currency,BigDecimal balance) {this.id=id;this.holderName=holderName;this.accountNumber=accountNumber;this.currency=currency;this.balance=balance;}
 public UUID getId(){return id;} public String getHolderName(){return holderName;} public String getAccountNumber(){return accountNumber;} public String getCurrency(){return currency;} public BigDecimal getBalance(){return balance;}
 public void debit(BigDecimal amount) { if(balance.compareTo(amount)<0) throw new IllegalArgumentException("Insufficient funds"); balance=balance.subtract(amount); }
 public void credit(BigDecimal amount){balance=balance.add(amount);}
}
