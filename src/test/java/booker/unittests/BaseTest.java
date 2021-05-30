package booker.unittests;

import booker.dao.BookingDatesDAO;
import booker.model.BookingDates;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    List<BookingDates> currentBookingDates = null;

    protected void currentBookingDates() {
        BookingDatesDAO bookingDatesDAO = new BookingDatesDAO();
        currentBookingDates = bookingDatesDAO.read();
    }

    protected BookingDates initBookingDates() {
        currentBookingDates();
        Assert.assertNotNull(currentBookingDates, "Can't read existing dates from DB");
        int currentId = currentBookingDates.size();
        BookingDates newBD = BookingDates.builder()
                .id(currentId + 1)
                .checkin("2021-07-21")
                .checkout("2021-07-22")
                .build();
        currentBookingDates.add(newBD);
        return newBD;
    }

}
