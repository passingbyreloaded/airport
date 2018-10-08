package ru.innopolis.stc13.repository.dao;

import ru.innopolis.stc13.repository.connectionManager.ConnectionManager;
import ru.innopolis.stc13.repository.connectionManager.ConnectionManagerJdbcImpl;
import ru.innopolis.stc13.repository.dao.daoint.UserDao;
import ru.innopolis.stc13.repository.pojo.AirCompany;
import ru.innopolis.stc13.repository.pojo.Role;
import ru.innopolis.stc13.repository.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private static ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();

    @Override
    public User getUserByLogin(String login) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select users.*, " +
                     "c2.name as company_name from users " +
                     "left join companies c2 on users.company = c2.id " +
                     "where users.name = ?")) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String company = resultSet.getString("company");
                return new User(resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        Role.valueOf(resultSet.getString("role")),
                        company == null ? null : new AirCompany(company, resultSet.getString("company_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
