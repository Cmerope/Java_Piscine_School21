package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        Optional<Message> optionalMessage;
        try {
                Connection connection = dataSource.getConnection();

                Statement statement = connection.createStatement();

                String query = "SELECT chat.message.id, chat.message.sender, chat.users.login, chat.users.password, chat.message.room_id, chat.room.chat_name, chat.message.message , chat.message.time " +
                        "FROM chat.message " +
                        "join chat.users on chat.message.sender = chat.users.id " +
                        "join chat.room on chat.message.room_id = chat.room.id " +
                        "where chat.message.id =" + id;
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    User user = new User(resultSet.getLong("sender"), resultSet.getString("login"), resultSet.getString("password"), null, null);
                    Chatroom chatroom = new Chatroom(resultSet.getLong("room_id"), resultSet.getString("chat_name"), null, null);
                    LocalDateTime dateTime = resultSet.getTimestamp("time").toLocalDateTime();
                    optionalMessage = Optional.of(new Message((long) resultSet.getInt(1), user,
                            chatroom, resultSet.getString("message"), dateTime));
                    return optionalMessage;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        return Optional.empty();
    }
}
