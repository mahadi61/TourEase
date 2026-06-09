package entity;

public class Booking {
    private String bookingId, customerId, packageId, bookingDate, status;

    public Booking(String bookingId, String customerId, String packageId, String bookingDate, String status) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.packageId = packageId;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getStatus() {
        return status;
    }
}