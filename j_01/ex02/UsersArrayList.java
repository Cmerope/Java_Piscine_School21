package ex02;

class UserNotFoundException extends RuntimeException {}

public class UsersArrayList implements UsersList {
    private User[] arry;
    private Integer count;

    private static Integer DEFAULT_SIZE = 10;

    public UsersArrayList() {
        arry = new User[DEFAULT_SIZE];
        count = 0;
    }

    public void add(User user) {
        if (arry.length == count) {
            User[] newArry = new User[count + count];
            for (int i = 0; i < arry.length; i++) {
                newArry[i] = arry[i];
            }
            arry = newArry;
        }
        arry[count] = user;
        count++;
    }

    public User getById(Integer id) {
        for (int i = 0; i < count; i++) {
            if (id.equals(arry[i].getIdentifier())) {
                return arry[i];
            }
        }
        throw new UserNotFoundException();
    }

    public User getByIndex(Integer id) {
        if (id < 0 || id >= count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return arry[id];
    }

    public Integer getCount() {
        return count;
    }
}
