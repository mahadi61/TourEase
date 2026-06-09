package entity;

public class TourPackage {
    private String packageId, destination, category, duration, hotelId, transportId;
    private double price;

    public TourPackage(String packageId, String destination, String category, double price, String duration,
            String hotelId, String transportId) {
        this.packageId = packageId;
        this.destination = destination;
        this.category = category;
        this.price = price;
        this.duration = duration;
        this.hotelId = hotelId;
        this.transportId = transportId;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getDestination() {
        return destination;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getDuration() {
        return duration;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getTransportId() {
        return transportId;
    }
}