package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws SQLException {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/cmerope");
        ds.setUsername("cmerope");
        ds.setPassword("");


        MessagesRepository repository = new MessagesRepositoryJdbcImpl(ds);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a message ID");
        System.out.print("-> ");
        System.out.println(repository.findById(scanner.nextLong()).get());
    }

}
