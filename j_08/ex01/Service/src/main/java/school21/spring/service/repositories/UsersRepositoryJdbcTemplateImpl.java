package school21.spring.service.repositories;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import school21.spring.service.models.User;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private final String idFind = "SELECT * FROM models.user WHERE id = :id";
    private final String emFind = "SELECT * FROM models.user WHERE email = :email";
    private final String alFindAll = "SELECT * FROM models.user";
    private final String emUpdate = "UPDATE models.user SET email = :email WHERE id = :id";
    private final String idDelete = "DELETE FROM models.user WHERE id = :id";
    private final String inSave = "INSERT INTO models.user (id, email) VALUES (:id, :email)";

    private class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();

            user.setIdentifier(rs.getLong("id"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    }

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        User user = jdbcTemplate.query(idFind,
                new MapSqlParameterSource().addValue("id", id),
                new UserRowMapper()).stream().findAny().orElse(null);

        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = jdbcTemplate.query(alFindAll, new UserRowMapper());
        return users;
    }

    @Override
    public void save(User entity) {
        int i = jdbcTemplate.update(inSave, new MapSqlParameterSource()
                .addValue("id", entity.getIdentifier())
                .addValue("email", entity.getEmail()));

        if (i == 0) {
            System.err.println("User wasn't saved with id: " + entity.getIdentifier());
        }
    }

    @Override
    public void update(User entity) {
        int i = jdbcTemplate.update(emUpdate, new MapSqlParameterSource()
                .addValue("id", entity.getIdentifier())
                .addValue("email", entity.getEmail()));

        if (i == 0) {
            System.err.println("User wasn't updated with id: " + entity.getIdentifier());
        }
    }

    @Override
    public void delete(Long id) {
        int i = jdbcTemplate.update(idDelete, new MapSqlParameterSource()
                .addValue("id", id));

        if (i == 0) {
            System.err.println("User not found with id: " + id);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = jdbcTemplate.query(emFind,
                new MapSqlParameterSource().addValue("email", email),
                new UserRowMapper()).stream().findAny().orElse(null);
        return Optional.ofNullable(user);
    }
}
