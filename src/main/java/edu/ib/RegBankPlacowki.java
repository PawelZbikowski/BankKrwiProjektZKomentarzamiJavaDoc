package edu.ib;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RegBankPlacowki {

    private IntegerProperty bloodID;
    private IntegerProperty amountOfBloodUnits;
    private StringProperty bloodType;
    private StringProperty centerName;
    private StringProperty howManyBloodUnits;

    public RegBankPlacowki() {

        bloodID = new SimpleIntegerProperty();
        amountOfBloodUnits = new SimpleIntegerProperty();
        bloodType = new SimpleStringProperty();
        centerName = new SimpleStringProperty();
        howManyBloodUnits = new SimpleStringProperty();
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

    public int getAmountOfBloodUnits() {
        return amountOfBloodUnits.get();
    }

    public IntegerProperty amountOfBloodUnitsProperty() {
        return amountOfBloodUnits;
    }

    public void setAmountOfBloodUnits(int amountOfBloodUnits) {
        this.amountOfBloodUnits.set(amountOfBloodUnits);
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

    public String getCenterName() {
        return centerName.get();
    }

    public StringProperty centerNameProperty() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName.set(centerName);
    }

    public String getHowManyBloodUnits() {
        return howManyBloodUnits.get();
    }

    public StringProperty howManyBloodUnitsProperty() {
        return howManyBloodUnits;
    }

    public void setHowManyBloodUnits(String howManyBloodUnits) {
        this.howManyBloodUnits.set(howManyBloodUnits);
    }
}
