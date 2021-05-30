package booker.unittests;

import booker.dao.BookingDAO;
import booker.dao.BookingDatesDAO;
import booker.model.Booking;
import booker.model.BookingDates;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AddBookingTest extends BaseTest {

    @SneakyThrows
    @Test
    public void createBooking() {
        BookingDAO bookingDAO = new BookingDAO();
        Booking booking = initBooking();
        bookingDAO.create(booking);
        List<Booking> bookingList = bookingDAO.read();
        Assert.assertEquals(bookingList.get(bookingList.size() - 1), booking, " booking no created");
    }

    private Booking initBooking() {
        BookingDates bookingDates = initBookingDates();
        return Booking.builder()
                .firstname("Roman")
                .lastname("Shcherbich")
                .totalPrice(102L)
                .depositPaid(false)
                .bookingDates(bookingDates)
                .build();
    }

}
