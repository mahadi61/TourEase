package entity;

public class Customer {
    private String customerId, name, phone, email, nidOrPassport;

    public Customer(String customerId, String name, String phone, String email, String nidOrPassport) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nidOrPassport = nidOrPassport;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getNidOrPassport() {
        return nidOrPassport;
    }
}