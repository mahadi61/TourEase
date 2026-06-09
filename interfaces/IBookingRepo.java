package interfaces;

import entity.*;
import java.util.ArrayList;



public interface IBookingRepo {
    boolean createBooking(Booking b, double paymentAmount, String mode);

    ArrayList<Object[]> getBookingReport();
}