package ex02;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Kate", 10000);
        User user2 = new User("Lily",  8000);
        User user3 = new User("Denis", -120);

        UsersList list = new UsersArrayList();
        list.add(user1);
        list.add(user2);
        list.add(user3);

        System.out.println(list.getByIndex(0) + " == " + user1);
        System.out.println(list.getByIndex(user2.getIdentifier()) + " == " + user2);
        System.out.println("count = " + list.getCount());

        User user4 = new User("Dany", -20000);

        System.out.println(list.getById(3));
        System.out.println(list.getByIndex(user4.getIdentifier()));
    }
}
