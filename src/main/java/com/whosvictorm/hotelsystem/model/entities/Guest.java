package com.whosvictorm.hotelsystem.model.entities;

import java.util.Date;
import java.util.Objects;

public class Guest {

    private String name;
    private Long cpf;
    private Long number;
    private String email;
    private Date checkin;
    private Date checkout;

    public Guest(){}

    public Guest(String name, Long cpf, Long number, String email, Date checkin, Date checkout) {
        this.name = name;
        this.cpf = cpf;
        this.number = number;
        this.email = email;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(name, guest.name) && Objects.equals(cpf, guest.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cpf);
    }
}
