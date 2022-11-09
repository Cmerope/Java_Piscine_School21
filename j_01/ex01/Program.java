package ex01;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Anna", 500);
        User user2 = new User("Lena", 2500);
        User user3 = new User("Dima", -300);

        System.out.println("ID: " + user1.getIdentifier() + " - " + user1.getName());
        System.out.println(user1.toString() + "\n");
        System.out.println("ID: " + user2.getIdentifier() + " - " + user2.getName());
        System.out.println(user2.toString()+ "\n");
        System.out.println("ID: " + user3.getIdentifier() + " - " + user3.getName());
        System.out.println(user3.toString());
    }
}
