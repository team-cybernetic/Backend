package beryloctopus.models;

public class Wallet {
    //The public key for the wallet
    private String publicKey;
    //The private key for the wallet (only the user who owns the wallet knows this)
    private String privateKey;
    //The balance of the wallet
    private double balance;

    public Wallet(String publicKey) {
        this.publicKey = publicKey;
        this.balance = 0;
    }
}
