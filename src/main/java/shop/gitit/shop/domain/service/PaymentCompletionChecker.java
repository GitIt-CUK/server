package shop.gitit.shop.domain.service;

public interface PaymentCompletionChecker {

    boolean completePayment(long memberId, int cost);
}
