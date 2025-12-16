import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Service {

    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>();

    // ---------- USER ----------
    public void setUser(int userId, int balance) {
        for (User u : users) {
            if (u.getUserId() == userId) {
                return; // already exists
            }
        }
        users.add(new User(userId, balance));
    }

    // ---------- ROOM ----------
    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber) {
                r.update(roomType, roomPricePerNight);
                return;
            }
        }
        rooms.add(new Room(roomNumber, roomType, roomPricePerNight));
    }

    // ---------- BOOKING ----------
    public void bookRoom(int userId, int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        try {
            if (!checkIn.isBefore(checkOut)) {
                throw new IllegalArgumentException("Invalid dates");
            }

            User user = null;
            for (User u : users) {
                if (u.getUserId() == userId) {
                    user = u;
                    break;
                }
            }
            if (user == null) throw new IllegalArgumentException("User not found");

            Room room = null;
            for (Room r : rooms) {
                if (r.getRoomNumber() == roomNumber) {
                    room = r;
                    break;
                }
            }
            if (room == null) throw new IllegalArgumentException("Room not found");

            for (Booking b : bookings) {
                if (b.getRoomNumber() == roomNumber) {
                    if (checkIn.isBefore(b.getCheckOut()) && checkOut.isAfter(b.getCheckIn())) {
                        throw new IllegalStateException("Room not available");
                    }
                }
            }

            long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
            int totalPrice = (int) nights * room.getPricePerNight();

            if (user.getBalance() < totalPrice) {
                throw new IllegalStateException("Insufficient balance");
            }

            user.deductBalance(totalPrice);

            bookings.add(new Booking(
                    userId,
                    roomNumber,
                    room.getRoomType(),
                    room.getPricePerNight(),
                    checkIn,
                    checkOut,
                    totalPrice
            ));

            System.out.println("Booking successful for user " + userId);

        } catch (Exception e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    // ---------- PRINT ----------
    public void printAllUsers() {
        System.out.println("\n--- USERS ---");
        for (int i = users.size() - 1; i >= 0; i--) {
            User u = users.get(i);
            System.out.println("User ID: " + u.getUserId() + " | Balance: " + u.getBalance());
        }
    }

    public void printAll() {
        System.out.println("\n--- ROOMS ---");
        for (int i = rooms.size() - 1; i >= 0; i--) {
            Room r = rooms.get(i);
            System.out.println("Room " + r.getRoomNumber() +
                    " | Type: " + r.getRoomType() +
                    " | Price/night: " + r.getPricePerNight());
        }

        System.out.println("\n--- BOOKINGS ---");
        for (int i = bookings.size() - 1; i >= 0; i--) {
            System.out.println(bookings.get(i));
        }
    }
}
