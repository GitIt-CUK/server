package shop.gitit.payment.exception;

public class NoWalletException extends RuntimeException {

    public NoWalletException() {}

    public NoWalletException(String message) {
        super(message);
    }

    public NoWalletException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoWalletException(Throwable cause) {
        super(cause);
    }
}
