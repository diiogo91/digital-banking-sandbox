package dev.damaral.banking.domain;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
@Entity @Table(name="transfers") public class Transfer {
 @Id private UUID id;
 @Column(name="source_account_id",nullable=false) private UUID sourceAccountId;
 @Column(name="target_account_id",nullable=false) private UUID targetAccountId;
 @Column(nullable=false,precision=19,scale=2) private BigDecimal amount;
 @Column(name="created_at",nullable=false) private Instant createdAt;
 protected Transfer(){}
 public Transfer(UUID id,UUID source,UUID target,BigDecimal amount,Instant at){this.id=id;this.sourceAccountId=source;this.targetAccountId=target;this.amount=amount;this.createdAt=at;}
 public UUID getId(){return id;} public UUID getSourceAccountId(){return sourceAccountId;} public UUID getTargetAccountId(){return targetAccountId;} public BigDecimal getAmount(){return amount;} public Instant getCreatedAt(){return createdAt;}
}
