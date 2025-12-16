import java.time.LocalDate;

public class Booking {
    private int userId;
    private int roomNumber;
    private RoomType roomTypeAtBooking;
    private int pricePerNightAtBooking;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int totalPrice;

    public Booking(
            int userId,
            int roomNumber,
            RoomType roomTypeAtBooking,
            int pricePerNightAtBooking,
            LocalDate checkIn,
            LocalDate checkOut,
            int totalPrice
    ) {
        this.userId = userId;
        this.roomNumber = roomNumber;
        this.roomTypeAtBooking = roomTypeAtBooking;
        this.pricePerNightAtBooking = pricePerNightAtBooking;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    @Override
    public String toString() {
        return "Booking {" +
                " userId=" + userId +
                ", roomNumber=" + roomNumber +
                ", roomType=" + roomTypeAtBooking +
                ", pricePerNight=" + pricePerNightAtBooking +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", totalPrice=" + totalPrice +
                " }";
    }
}
