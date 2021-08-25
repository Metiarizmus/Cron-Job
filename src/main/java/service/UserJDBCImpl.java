package service;

import Entity.User;
import connect_service.DAOFactory;
import connect_service.PropertyInf;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserJDBCImpl implements UserJDBCI {
    private PropertyInf property = new PropertyInf();

    private Connection daoFactory = DAOFactory.getInstance().getConnection();

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = daoFactory;
            statement = connection.prepareStatement(property.getSqlQuery().getProperty("GET_ALL"));
            result = statement.executeQuery();

            while (result.next()) {
                list.add(addUserFromTable(result));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                result.close();
                statement.close();
                DAOFactory.closeConnection(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (list.isEmpty()) {
            System.err.println("list is null");
        }

        return list;
    }

    private User addUserFromTable(ResultSet result) throws SQLException {
        User user = new User();
        user.setId(result.getInt("id"));
        user.setBirthday(result.getDate("birthday"));
        user.setLastLogin(result.getTimestamp("last login"));
        user.setLogin(result.getString("login"));
        user.setName(result.getString("name"));
        user.setPassword(result.getString("password"));
        user.setRegistrationDate(result.getTimestamp("registration date"));

        return user;
    }
}
