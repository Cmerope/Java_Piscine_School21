package ex00;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Mari", 1_000);
        user1.setIdentifier(1);
        User user2 = new User("lily", 500);
        user2.setIdentifier(2);

        System.out.println("----------- User's start balance ----------- ");

        System.out.println(user1.getName() + " " + user1.getBalance());
        System.out.println(user2.getName() + " " + user2.getBalance());

        System.out.println("----------- The first transaction ----------- ");

        Transaction transaction1 = Transaction.create(user1, user2, Transaction.TransactionType.CREDIT, -8);
        System.out.println("TransactionId " + transaction1.getIdentifier());
        System.out.println(user1.getName() + " " + user1.getBalance());
        System.out.println(user2.getName() + " " + user2.getBalance());

        Transaction transaction2 = Transaction.create(user1, user2, Transaction.TransactionType.DEBIT, 9);
        System.out.println("TransactionId " + transaction2.getIdentifier());
        System.out.println(user1.getName() + " " + user1.getBalance());
        System.out.println(user2.getName() + " " + user2.getBalance());

        Transaction transaction3 = Transaction.create(user1, user2, Transaction.TransactionType.CREDIT, -1500);
        System.out.println("TransactionId " + transaction3.getIdentifier());
        System.out.println(user1.getName() + " " + user1.getBalance());
        System.out.println(user2.getName() + " " + user2.getBalance());
    }
}
