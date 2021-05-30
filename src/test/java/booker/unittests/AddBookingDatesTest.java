package booker.unittests;

import booker.dao.BookingDatesDAO;
import booker.model.BookingDates;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AddBookingDatesTest extends BaseTest  {

    @SneakyThrows
    @Test
    public void createBookingDatesTest() {
        BookingDatesDAO bookingDatesDAO = new BookingDatesDAO();
        BookingDates bookingDates = initBookingDates();
        bookingDatesDAO.create(bookingDates);
        List<BookingDates> actualBookingDatesList = bookingDatesDAO.read();
        Assert.assertEquals(actualBookingDatesList, currentBookingDates, "BookingDate is not created");
    }

}
