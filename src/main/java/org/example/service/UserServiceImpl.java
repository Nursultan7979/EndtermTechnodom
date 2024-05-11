package org.example;

import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final DataSource dataSource;

    public UserServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {

            while (resultSet.next()) {
                Users user = new Users();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Обработка исключений
        }
        return users;
    }
}

