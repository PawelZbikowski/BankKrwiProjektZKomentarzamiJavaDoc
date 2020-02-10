package edu.ib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegBankTypyKrwiDAO {

    private DBUtil dbUtil;

    public RegBankTypyKrwiDAO(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    public ObservableList<RegBankArchiwa> showArchives() throws SQLException, ClassNotFoundException {

        String selectStatement = "SELECT * FROM archiwa GROUP BY pobrania_jednostki_krwi_ID;";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStatement);

            ObservableList<RegBankArchiwa> archiveList = getArchiveList(resultSet);

            System.out.println(selectStatement);

            return archiveList;

        } catch (SQLException e) {
            System.out.println("While searching, an error occurred.");
            throw e;
        }
    }

    private ObservableList<RegBankArchiwa> getArchiveList(ResultSet resultSet) throws SQLException {
        ObservableList<RegBankArchiwa> archiveTypeList = FXCollections.observableArrayList();

        while (resultSet.next()) {

            RegBankArchiwa rba = new RegBankArchiwa();
            rba.setArchiveDate(resultSet.getString("data_zarchiwizowania"));
            rba.setPesel2(resultSet.getString("pobrania_dawcy_PESEL"));
            rba.setBloodID2(resultSet.getInt("pobrania_jednostki_krwi_ID"));
            archiveTypeList.add(rba);
        }

        return archiveTypeList;
    }

    public ObservableList<RegBankTypyKrwi> searchBloodType(String bloodGroup, String Rh) throws ClassNotFoundException, SQLException {

        String selectStmt = "SELECT * FROM regBankKrwiGrupaRh WHERE Grupa = '" + bloodGroup + "' AND Czynnik_Rh = '" + Rh + "'; ";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<RegBankTypyKrwi> donorsList = getBloodTypeList(resultSet);

            System.out.println(selectStmt);

            return donorsList;
        } catch (SQLException e) {
            System.out.println("While searching a " + bloodGroup + " blood group with " + Rh + " Rh parameter, an error occurred.");
            throw e;
        }
    }

    private ObservableList<RegBankTypyKrwi> getBloodTypeList(ResultSet resultSet) throws SQLException {

        ObservableList<RegBankTypyKrwi> bloodTypeList = FXCollections.observableArrayList();

        while (resultSet.next()) {

            RegBankTypyKrwi rb = new RegBankTypyKrwi();
            rb.setBloodID(resultSet.getInt("ID"));
            rb.setDateOfDonation(resultSet.getString("Data_pobrania"));
            rb.setFreshness(resultSet.getString("Swiezosc"));
            rb.setBloodGroup(resultSet.getString("Grupa"));
            rb.setRh(resultSet.getString("Czynnik_Rh"));
            bloodTypeList.add(rb);
        }

        return bloodTypeList;
    }

    public ObservableList<RegBankPlacowki> searchCenter(String center) throws ClassNotFoundException, SQLException {

        String selectStmt = "SELECT * FROM placówki WHERE regionalne_banki = '" + center + "'; ";
//        SELECT * FROM placówki WHERE regionalne_banki = 'RCKiK Białystok';

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<RegBankPlacowki> centersList = getCentersList(resultSet);

            System.out.println(selectStmt);

            return centersList;
        } catch (SQLException e) {
            System.out.println("While searching a center named " + center + " , an error occurred.");
            throw e;
        }
    }

    private ObservableList<RegBankPlacowki> getCentersList(ResultSet resultSet) throws SQLException {
        ObservableList<RegBankPlacowki> centersList = FXCollections.observableArrayList();

        while (resultSet.next()) {

            RegBankPlacowki rb = new RegBankPlacowki();
            rb.setBloodID(resultSet.getInt("ID"));
            rb.setAmountOfBloodUnits(resultSet.getInt("ilosc_jednostek"));
            rb.setBloodType(resultSet.getString("grupa_krwi"));
            rb.setCenterName(resultSet.getString("regionalne_banki"));
            rb.setHowManyBloodUnits(resultSet.getString("informacje_o_zapasach"));
            centersList.add(rb);
        }

        return centersList;
    }

    public void transfusion(String nazwa_placowki,  String grupa_krwi) throws SQLException, ClassNotFoundException {

        StringBuilder sb = new StringBuilder("{ call pobranie_do_transfuzji('");
        sb.append(nazwa_placowki);
        sb.append("','");
        sb.append(grupa_krwi);
        sb.append("')}");

        String callableStmt = sb.toString();

        try {

            dbUtil.dbCallableStatement(callableStmt);
            System.out.println(callableStmt);
        } catch (SQLException e) {
            System.out.println("Error occurred while calling procedure operation.");
            throw e;
        }
    }
}
