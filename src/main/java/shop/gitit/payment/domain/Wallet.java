package shop.gitit.payment.domain;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.gitit.core.baseentity.BaseEntity;
import shop.gitit.payment.domain.memberId.MemberId;
import shop.gitit.payment.domain.money.Money;

@Entity
@Table(name = "WALLET")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wallet extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "wallet_id")
    private Long id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "wallet_owner_id", nullable = false))
    private MemberId owner;

    @Embedded
    @AttributeOverride(name = "point", column = @Column(name = "wallet_money", nullable = false))
    private Money money;

    public Wallet(MemberId owner) {
        this.owner = owner;
        this.money = new Money();
    }
}