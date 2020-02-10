package edu.ib;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RegBankTypyKrwi {

    private IntegerProperty bloodID;
    private StringProperty dateOfDonation;
    private StringProperty freshness;
    private StringProperty bloodGroup;
    private StringProperty Rh;

    public RegBankTypyKrwi() {

        bloodID = new SimpleIntegerProperty();
        dateOfDonation = new SimpleStringProperty();
        freshness = new SimpleStringProperty();
        bloodGroup = new SimpleStringProperty();
        Rh = new SimpleStringProperty();
    }

    public int getBloodID() {
        return bloodID.get();
    }

    public IntegerProperty bloodIDProperty() {
        return bloodID;
    }

    public void setBloodID(int bloodID) {
        this.bloodID.set(bloodID);
    }

    public String getDateOfDonation() {
        return dateOfDonation.get();
    }

    public StringProperty dateOfDonationProperty() {
        return dateOfDonation;
    }

    public void setDateOfDonation(String dateOfDonation) {
        this.dateOfDonation.set(dateOfDonation);
    }

    public String getFreshness() {
        return freshness.get();
    }

    public StringProperty freshnessProperty() {
        return freshness;
    }

    public void setFreshness(String freshness) {
        this.freshness.set(freshness);
    }

    public String getBloodGroup() {
        return bloodGroup.get();
    }

    public StringProperty bloodGroupProperty() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup.set(bloodGroup);
    }

    public String getRh() {
        return Rh.get();
    }

    public StringProperty rhProperty() {
        return Rh;
    }

    public void setRh(String rh) {
        this.Rh.set(rh);
    }
}
