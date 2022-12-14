package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private DataSource dataSource;
    private final String idFind = "SELECT * FROM models.user WHERE id = ";
    private final String emFind = "SELECT * FROM models.user WHERE email = ?";
    private final String alFindAll = "SELECT * FROM models.user";
    private final String emUpdate = "UPDATE models.user SET email = ? WHERE id = ?";
    private final String idDelete = "DELETE FROM models.user WHERE id = ";

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(idFind + id);

            if (!rs.next()) {
                return null;
            }
            return new User(rs.getLong(1), rs.getString(2));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(alFindAll);

            while (rs.next()) {
                users.add(new User(rs.getLong(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return users.isEmpty() ? null : users;
    }

    @Override
    public void save(User entity) {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            int result = st.executeUpdate("INSERT INTO models.user (id, email) VALUES ("
                    + entity.getIdentifier() + ", '"
                    + entity.getEmail() + "');");

            if (result == 0) {
                System.err.println("User wasn't saved with id: " + entity.getIdentifier());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(User entity) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(emUpdate)) {
            st.setString(1, entity.getEmail());
            st.setLong(2, entity.getIdentifier());
            int result = st.executeUpdate();

            if (result == 0) {
                System.err.println("User wasn't updated with id: " + entity.getIdentifier());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(idDelete + id)) {
            int result = st.executeUpdate();

            if (result == 0) {
                System.err.println("User not found with id: " + id);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(emFind)) {
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (!rs.next()) {
                return Optional.empty();
            }
            return Optional.of(new User(rs.getLong(1), rs.getString(2)));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.empty();
    }
}
