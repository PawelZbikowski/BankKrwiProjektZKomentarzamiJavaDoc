package edu.ib;

import javafx.beans.property.*;

import java.time.LocalDate;

public class CentrumKrwiodawstwa {

    private StringProperty donationDay;
    private StringProperty pesel;
    private StringProperty name;
    private StringProperty surname;
    private StringProperty sex;
    private StringProperty dateOfBirth;
    private StringProperty placeOfBirth;
    private StringProperty adress;
    private IntegerProperty weight;
    private StringProperty lastDonation;
    private StringProperty bloodType;

    public CentrumKrwiodawstwa() {

        donationDay = new SimpleStringProperty();
        pesel = new SimpleStringProperty();
        name = new SimpleStringProperty();
        surname = new SimpleStringProperty();
        sex = new SimpleStringProperty();
        dateOfBirth = new SimpleStringProperty();
        placeOfBirth = new SimpleStringProperty();
        adress = new SimpleStringProperty();
        weight = new SimpleIntegerProperty();
        lastDonation = new SimpleStringProperty();
        bloodType = new SimpleStringProperty();
    }

    public String getDonationDay() {
        return donationDay.get();
    }

    public StringProperty donationDayProperty() {
        return donationDay;
    }

    public void setDonationDay(String donationDay) {
        this.donationDay.set(donationDay);
    }

    public String getPesel() {
        return pesel.get();
    }

    public StringProperty peselProperty() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getSex() {
        return sex.get();
    }

    public StringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    public StringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public String getPlaceOfBirth() {
        return placeOfBirth.get();
    }

    public StringProperty placeOfBirthProperty() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth.set(placeOfBirth);
    }

    public String getAdress() {
        return adress.get();
    }

    public StringProperty adressProperty() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress.set(adress);
    }

    public int getWeight() {
        return weight.get();
    }

    public IntegerProperty weightProperty() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight.set(weight);
    }

    public String getLastDonation() {
        return lastDonation.get();
    }

    public StringProperty lastDonationProperty() {
        return lastDonation;
    }

    public void setLastDonation(String lastDonation) {
        this.lastDonation.set(lastDonation);
    }

    public String getBloodType() {
        return bloodType.get();
    }

    public StringProperty bloodTypeProperty() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType.set(bloodType);
    }
}
