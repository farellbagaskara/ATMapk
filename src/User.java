public class User {
    private String username;
    private String pin;
    private double saldo;

    public User(String username, String pin, double saldo) {
        this.username = username;
        this.pin = pin;
        this.saldo = saldo;
    }

    public String getUsername() {
        return username;
    }

    public String getPin() {
        return pin;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
