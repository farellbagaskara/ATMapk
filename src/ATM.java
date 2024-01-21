import java.util.ArrayList;
import java.util.Scanner;
public class ATM {
    private static ArrayList<User> users;
    private static User currentUser;
    private static Scanner scanner;

    public static void main(String[] args) {
        initializeUsers();
        showWelcomeMenu();
    }

    private static void initializeUsers() {
        // Inisialisasi user dengan saldo awal
        users = new ArrayList<>();
        users.add(new User("farell", "12345", 3000000.0));
        users.add(new User("gita", "54321", 5000000.0));
    }

    private static void showWelcomeMenu() {
        scanner = new Scanner(System.in);
        System.out.println("Selamat datang di ATM!");

        while (true) {
            System.out.print("Masukkan username: ");
            String username = scanner.nextLine();

            System.out.print("Masukkan pin ");
            String pin = scanner.nextLine();

            if (authenticateUser(username, pin)) {
                System.out.println("Login berhasil!");
                break;
            } else {
                System.out.println("Login gagal. Coba lagi.");
            }
        }

        showMainMenu();
    }

    private static boolean authenticateUser(String username, String pin) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPin().equals(pin)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\n===== Menu Utama =====");
            System.out.println("1. Cek Saldo");
            System.out.println("2. Tarik Tunai");
            System.out.println("3. Transfer");
            System.out.println("4. Logout");

            System.out.print("Pilih menu (1-4): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    cekSaldo();
                    break;
                case 2:
                    tarikTunai();
                    break;
                case 3:
                    transfer();
                    break;
                case 4:
                    logout();
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }
    }

    private static void cekSaldo() {
        System.out.println("\n===== Cek Saldo =====");
        System.out.println("Saldo Anda saat ini: $" + currentUser.getSaldo());
    }

    private static void tarikTunai() {
        System.out.println("\n===== Tarik Tunai =====");
        System.out.println("Pilih nominal uang yang ingin ditarik:");
        System.out.println("1. Rp 50,000");
        System.out.println("2. Rp 100,000");
        System.out.println("3. Rp 200,000");
        System.out.println("4. Rp 500,000");
        System.out.println("5. Rp 1,000,000");
        System.out.println("6. Rp 2,000,000");
        System.out.println("7. Nominal Lain");

        System.out.print("Pilih nomor uang (1-7): ");
        int choice = scanner.nextInt();

        double amount = 0;

        switch (choice) {
            case 1:
                amount = 50000;
                break;
            case 2:
                amount = 100000;
                break;
            case 3:
                amount = 200000;
                break;
            case 4:
                amount = 500000;
                break;
            case 5:
                amount = 1000000;
                break;
            case 6:
                amount = 2000000;
                break;
            case 7:
                System.out.print("Masukkan jumlah uang yang ingin ditarik: Rp ");
                amount = scanner.nextDouble();
                break;
            default:
                System.out.println("Pilihan tidak valid. Pembatalan tarik tunai.");
                return;
        }

        if (amount > 0 && amount <= currentUser.getSaldo()) {
            currentUser.setSaldo(currentUser.getSaldo() - amount);
            System.out.println("Penarikan tunai berhasil. Saldo Anda saat ini: Rp " + currentUser.getSaldo());
        } else {
            System.out.println("Penarikan tunai gagal. Jumlah uang tidak valid atau saldo tidak mencukupi.");
        }
    }


    private static void transfer() {
        System.out.println("\n===== Transfer =====");
        System.out.print("Masukkan username penerima: ");
        String receiverUsername = scanner.next();

        User receiver = findUserByUsername(receiverUsername);

        if (receiver != null) {
            System.out.print("Masukkan jumlah uang yang ingin ditransfer: $");
            double amount = scanner.nextDouble();

            if (amount > 0 && amount <= currentUser.getSaldo()) {
                currentUser.setSaldo(currentUser.getSaldo() - amount);
                receiver.setSaldo(receiver.getSaldo() + amount);
                System.out.println("Transfer berhasil. Saldo Anda saat ini: $" + currentUser.getSaldo());
            } else {
                System.out.println("Transfer gagal. Jumlah uang tidak valid atau saldo tidak mencukupi.");
            }
        } else {
            System.out.println("Transfer gagal. Username penerima tidak ditemukan.");
        }
    }


    private static User findUserByAccountNumber(String accountNumber) {
        for (User user : users) {
            if (user.getUsername().equals(accountNumber)) {
                return user;
            }
        }
        return null;
    }


    private static User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private static void logout() {
        currentUser = null;
        System.out.println("Logout berhasil. Terima kasih!");
        showWelcomeMenu();
    }
}