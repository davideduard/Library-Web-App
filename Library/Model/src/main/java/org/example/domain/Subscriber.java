package org.example.domain;

import java.util.Objects;

public class Subscriber extends Entity<String>{
    private String first_name;
    private String last_name;
    private String password;
    private String address;
    private String phone_number;
    private String social_security_number;

    public Subscriber() {
    }

    public Subscriber(String s, String first_name, String last_name, String password, String address, String phone_number, String social_security_number) {
        super(s);
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.address = address;
        this.phone_number = phone_number;
        this.social_security_number = social_security_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getSocial_security_number() {
        return social_security_number;
    }

    public void setSocial_security_number(String social_security_number) {
        this.social_security_number = social_security_number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subscriber that = (Subscriber) o;
        return Objects.equals(first_name, that.first_name) && Objects.equals(last_name, that.last_name) && Objects.equals(password, that.password) && Objects.equals(address, that.address) && Objects.equals(phone_number, that.phone_number) && Objects.equals(social_security_number, that.social_security_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), first_name, last_name, password, address, phone_number, social_security_number);
    }
}
