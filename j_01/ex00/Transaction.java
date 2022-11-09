package ex00;

import java.util.UUID;

public class Transaction {
    enum TransactionType {
        CREDIT("OUTCOME", ""), DEBIT("INCOME", "+");

        private String message;
        private String sign;

        TransactionType(String message, String sign) {
            this.message = message;
            this.sign = sign;
        }

        public String toString() {
            return message;
        }

        public String getSign() {
            return sign;
        }
    }

    private UUID identifier;
    private User recipient;
    private User sender;
    private TransactionType transaction;
    private Integer amount;

    public Transaction(User recipient, User sender, TransactionType transaction, Integer amount) {
        this.recipient = recipient;
        this.sender = sender;
        this.amount = amount;
        this.transaction = transaction;
        identifier = UUID.randomUUID();
    }
    public String toString() {
        return String.format("%s -> %s, %s%s, %s, %s", sender.getName(), recipient.getName(),
                transaction.getSign(), amount, transaction, identifier);
    }

    public static Transaction create(User recipient, User sender, TransactionType transaction, Integer amount) {

        if ((transaction == TransactionType.CREDIT && recipient.getBalance() >= -amount)) {
            recipient.setBalance(recipient.getBalance() + amount);
            sender.setBalance(sender.getBalance() - amount);
            Transaction tip = new Transaction(recipient, sender, transaction, amount);
            System.out.println("\n" + tip.toString());
            return tip;
        }
        else if((transaction == TransactionType.DEBIT && sender.getBalance() >= amount)) {
            sender.setBalance(sender.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
            Transaction tip = new Transaction(recipient, sender, transaction, amount);
            System.out.println("\n" + tip.toString());
            return tip;

        }
        Transaction tip = new Transaction(recipient, sender, transaction, amount);
        System.out.println("\n" + tip.toString());
        System.out.println("Transaction  failed. Not enough money.");
        return tip;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Integer getAmount() {
        return amount;
    }
}
