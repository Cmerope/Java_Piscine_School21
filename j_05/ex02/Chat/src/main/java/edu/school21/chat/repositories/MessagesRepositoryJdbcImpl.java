package edu.school21.chat.repositories;

import edu.school21.chat.app.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource source) {
        this.dataSource = source;
    }

    @Override
    public Optional<Message> findById(Long id) {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT chat.message.id, chat.message.sender, chat.users.login, chat.users.password, " +
                    "chat.message.room_id, chat.room.chat_name, chat.message.message , chat.message.time " +
                    "FROM chat.message " +
                    "join chat.users on chat.message.sender = chat.users.id " +
                    "join chat.room on chat.message.room_id = chat.room.id " +
                    "where chat.message.id =" + id;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                User user = new User(resultSet.getLong("sender"), resultSet.getString("login"),
                        resultSet.getString("password"), null, null);
                Chatroom chatroom = new Chatroom(resultSet.getLong("room_id"),
                        resultSet.getString("chat_name"), null, null);
                LocalDateTime dateTime = resultSet.getTimestamp("time").toLocalDateTime();
                return Optional.of(new Message((long) resultSet.getInt(1), user ,
                        chatroom, resultSet.getString("message"), dateTime));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void save(Message message) throws NotSavedSubEntityException {
        String takeMes = "INSERT into chat.message (room_id, sender, message, time)" +
                " VALUES (" +
                message.getRoom().getId() + ", " +
                message.getAuthor().getId() + ", " +
                "'" + message.getMessage() + "'" + ", " +
                "'" + message.getDateTime() + "'" +
                ");";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(takeMes, Statement.RETURN_GENERATED_KEYS)) {
            statement.execute();
            ResultSet key = statement.getGeneratedKeys();
            key.next();
            message.setId((long) key.getInt(1));
        } catch (SQLException e) {
            throw new NotSavedSubEntityException(e.getMessage());
        }
    }
}