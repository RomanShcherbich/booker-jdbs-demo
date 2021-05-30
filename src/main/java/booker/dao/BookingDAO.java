package booker.dao;

import booker.model.Booking;
import booker.model.BookingDates;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class BookingDAO extends JdbcDAO<Booking> implements GenericDAO<Booking> {

    protected static final String BOOKING_TABLE = "booking";

    public BookingDAO() {
        super(BOOKING_TABLE);
    }

    protected static final String INSERT_STATEMENT =
            String.format("INSERT INTO %s.%s", MY_SQL_DB_NAME, BOOKING_TABLE) +
                    "(firstname, lastname, total_price, deposit_paid, bookingdates_id)\n" +
                    "VALUES('%s', '%s', %s, %s, %s)\n";

    public BookingDAO(String table) {
        super(table);
    }

    @Override
    public Booking create(Booking booking) throws SQLException {
        try(Connection connection = connect()) {
            String query = String.format(INSERT_STATEMENT, booking.firstname, booking.lastname, booking.totalPrice,
                    booking.depositPaid, booking.bookingDates.id);
            log.info(query);
            Statement statement = connection.createStatement();
            int affectedRows = statement.executeUpdate(query);
            if (affectedRows > 0) {
                return booking;
            }
        } catch (SQLException ex) {
            log.error("cannot create ".concat(booking.toString()));
        }
        return null;
    }

    @Override
    public List<Booking> read() {
        List<Booking> bookings = new ArrayList<>();
        try (ResultSet resultSet = selectAllRows()) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                Long totalPrice = resultSet.getLong("total_price");
                Boolean depositPaid = resultSet.getBoolean("deposit_paid");
                Long bookingDatesId = resultSet.getLong("bookingdates_id");
                BookingDates bookingDates = new BookingDatesDAO().get(bookingDatesId);
                bookings.add(new Booking(id, firstname, lastname, totalPrice, depositPaid, bookingDates));
            }
        } catch (SQLException throwables) {
            log.error("cannot ".concat(SELECT_ALL_STATEMENT));
            return null;
        }
        return bookings;
    }

    @Override
    public Booking update(Booking booking) {
        return null;
    }

    @Override
    public boolean delete(Object id) throws SQLException {
        return false;
    }

    @Override
    public Booking get(Object id) throws SQLException {
        return null;
    }
}
