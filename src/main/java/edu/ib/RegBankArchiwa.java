package edu.ib;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RegBankArchiwa {

    private StringProperty archiveDate;
    private StringProperty pesel2;
    private IntegerProperty bloodID2;

    public RegBankArchiwa() {
        archiveDate = new SimpleStringProperty();
        pesel2 = new SimpleStringProperty();
        bloodID2 = new SimpleIntegerProperty();
    }

    public String getArchiveDate() {
        return archiveDate.get();
    }

    public StringProperty archiveDateProperty() {
        return archiveDate;
    }

    public void setArchiveDate(String archiveDate) {
        this.archiveDate.set(archiveDate);
    }

    public String getPesel2() {
        return pesel2.get();
    }

    public StringProperty pesel2Property() {
        return pesel2;
    }

    public void setPesel2(String pesel2) {
        this.pesel2.set(pesel2);
    }

    public int getBloodID2() {
        return bloodID2.get();
    }

    public IntegerProperty bloodID2Property() {
        return bloodID2;
    }

    public void setBloodID2(int bloodID2) {
        this.bloodID2.set(bloodID2);
    }
}
