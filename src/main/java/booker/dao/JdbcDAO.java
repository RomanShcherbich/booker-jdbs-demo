package booker.dao;

import lombok.extern.log4j.Log4j2;

import java.net.URI;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class JdbcDAO<T> {

    protected static final String MY_SQL_DRIVER_URL = "jdbc:mysql://localhost:3306/";
    protected static final String MY_SQL_DB_NAME = "restful_booker";
    protected static final String MY_SQL_USERNAME = "root";
    protected static final String MY_SQL_PASSWORD = "mysql";
    protected String SELECT_ALL_STATEMENT;
    protected String SELECT_MAX_ID_STATEMENT;
    protected String SELECT_BY_ID_STATEMENT;

    Connection connect;

    public JdbcDAO(String table) {
        this.SELECT_ALL_STATEMENT = String.format("SELECT * FROM %s;\n", table);
        this.SELECT_MAX_ID_STATEMENT = String.format("SELECT MAX(id) FROM %s;\n", table);
        this.SELECT_BY_ID_STATEMENT = String.format("SELECT * FROM %s\n", table) +
                "WHERE id = %s;\n";
    }

    protected Connection connect() {
        String mysqlUri = MY_SQL_DRIVER_URL.concat(MY_SQL_DB_NAME);
        try {
            if (connect == null || connect.isClosed()) {
                connect = DriverManager.getConnection(mysqlUri, MY_SQL_USERNAME, MY_SQL_PASSWORD);

            }
        } catch (SQLException throwables) {
            log.error(String.format("Can't create connection to %s database", mysqlUri));
            throwables.printStackTrace();
        }
        return connect;
    }

    protected ResultSet selectAllRows() {
        try {
            Statement statement = connect().createStatement();
            return statement.executeQuery(SELECT_ALL_STATEMENT);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    protected ResultSet selectById(long id) {
        String query = String.format(SELECT_BY_ID_STATEMENT, id);
        try {
            Statement statement = connect().createStatement();
            log.info(query);
            return statement.executeQuery(SELECT_ALL_STATEMENT);
        } catch (SQLException throwables) {
            log.error("cannot ".concat(query));
            throwables.printStackTrace();
        }
        return null;
    }


}
