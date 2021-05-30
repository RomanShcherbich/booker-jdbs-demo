package booker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    public long id;

    @SerializedName("firstname")
    @Expose
    public String firstname;
    @SerializedName("lastname")
    @Expose
    public String lastname;
    @SerializedName("totalprice")
    @Expose
    public Long totalPrice;
    @SerializedName("depositpaid")
    @Expose
    public Boolean depositPaid;
    @SerializedName("bookingdates")
    @Expose
    public BookingDates bookingDates;

}