package shop.gitit.payment.domain;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.gitit.core.baseentity.BaseEntity;
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

    @Column(name = "wallet_owner_id", nullable = false)
    private Long ownerId;

    @Embedded
    @AttributeOverride(name = "point", column = @Column(name = "wallet_money", nullable = false))
    private Money money;

    public Wallet(long ownerId) {
        this.ownerId = ownerId;
        this.money = new Money();
    }

    public int getPoint() {
        return this.money.getPoint();
    }

    public void pay(int cost) {
        this.money.minus(cost);
    }

    public void accumulatePoint(int point) {
        this.money.plus(point);
    }
}
