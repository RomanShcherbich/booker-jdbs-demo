package booker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookingDates {

    public long id;
    @SerializedName("checkin")
    @Expose
    public String checkin;
    @SerializedName("checkout")
    @Expose
    public String checkout;

}