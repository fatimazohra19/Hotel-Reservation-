import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Service service = new Service();

        service.setRoom(1, RoomType.STANDARD, 1000);
        service.setRoom(2, RoomType.JUNIOR, 2000);
        service.setRoom(3, RoomType.SUITE, 3000);

        service.setUser(1, 5000);
        service.setUser(2, 10000);

        service.bookRoom(1, 2, LocalDate.of(2026, 6, 30), LocalDate.of(2026, 7, 7));
        service.bookRoom(1, 2, LocalDate.of(2026, 7, 7), LocalDate.of(2026, 6, 30));
        service.bookRoom(1, 1, LocalDate.of(2026, 7, 7), LocalDate.of(2026, 7, 8));
        service.bookRoom(2, 1, LocalDate.of(2026, 7, 7), LocalDate.of(2026, 7, 9));
        service.bookRoom(2, 3, LocalDate.of(2026, 7, 7), LocalDate.of(2026, 7, 8));

        service.setRoom(1, RoomType.SUITE, 10000);

        service.printAll();
        service.printAllUsers();
    }
}