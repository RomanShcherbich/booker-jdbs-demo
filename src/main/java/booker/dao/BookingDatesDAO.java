package booker.dao;

import booker.model.BookingDates;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class BookingDatesDAO extends JdbcDAO<BookingDates> implements GenericDAO<BookingDates> {

    protected static final String BOOKING_DATES_TABLE = "booking_dates";

    public BookingDatesDAO() {
        super(BOOKING_DATES_TABLE);
    }

    protected static final String INSERT_STATEMENT =
            String.format("INSERT INTO %s.%s", MY_SQL_DB_NAME, BOOKING_DATES_TABLE) +
            "(checkin, checkout)\n" +
            "VALUES('%s', '%s');\n";

    @Override
    public BookingDates create(BookingDates bookingDates) {
        try(Connection connection = connect()) {
            String query = String.format(INSERT_STATEMENT, bookingDates.checkin, bookingDates.checkout);
            Statement statement = connection.createStatement();
            int affectedRows = statement.executeUpdate(query);
            if (affectedRows > 0) {
                return bookingDates;
            }
        } catch (SQLException ex) {
            log.error("cannot create ".concat(bookingDates.toString()));
        }
        return null;
    }

    @Override
    public List<BookingDates> read() {
        List<BookingDates> bookingDates = new ArrayList<>();
        try (ResultSet resultSet = selectAllRows()) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String checkin = resultSet.getString("checkin");
                String checkout = resultSet.getString("checkout");
                bookingDates.add(new BookingDates(id, checkin, checkout));
            }
        } catch (SQLException throwables) {
            log.error("cannot ".concat(SELECT_ALL_STATEMENT));
            return null;
        }
        return bookingDates;
    }

    @Override
    public BookingDates update(BookingDates bookingDates) {
        return null;
    }

    @Override
    public boolean delete(Object id) throws SQLException {
        return false;
    }

    @Override
    public BookingDates get(Object id) throws SQLException {
        List<BookingDates> bookingDates = new ArrayList<>();
        try (ResultSet resultSet = selectById((long) id)) {
            while (resultSet.next()) {
                long bkid = resultSet.getLong("id");
                String checkin = resultSet.getString("checkin");
                String checkout = resultSet.getString("checkout");
                bookingDates.add(new BookingDates(bkid, checkin, checkout));
            }
        } catch (SQLException throwables) {
            log.error("cannot select by ".concat(id.toString()));
            return null;
        }
        if (bookingDates.size() > 1) {
            log.error("cannot select by "
                    .concat(id.toString()).concat(" case multiple values ")
                    .concat(bookingDates.toString()));
            return null;
        } else if (bookingDates.size() == 0) {
            log.error("cannot select by "
                    .concat(id.toString()).concat(" cause no such id"));
            return null;
        }
        return bookingDates.get(0);
    }

}
