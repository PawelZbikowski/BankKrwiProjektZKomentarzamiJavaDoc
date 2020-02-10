package edu.ib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CentrumKrwiodawstwaDAO {

    private DBUtil dbUtil;

    public CentrumKrwiodawstwaDAO(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }


    public ObservableList<CentrumKrwiodawstwa> showAllDonors() throws SQLException, ClassNotFoundException {

        String selectStatement = "SELECT * FROM centrumkrwiodawstwa;";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStatement);

            ObservableList<CentrumKrwiodawstwa> donorsList = getDonorsList(resultSet);

            System.out.println(selectStatement);

            return donorsList;

        } catch (SQLException e) {
            System.out.println("While searching, an error occurred.");
            throw e;
        }
    }

    public ObservableList<CentrumKrwiodawstwa> searchParameters(String parameter, String name) throws ClassNotFoundException, SQLException {

        String selectStmt = "SELECT * FROM centrumkrwiodawstwa WHERE " + parameter + " LIKE '%" + name + "%';";

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<CentrumKrwiodawstwa> donorsList = getDonorsList(resultSet);

            System.out.println(selectStmt);

            return donorsList;
        } catch (SQLException e) {
            System.out.println("While searching a " + parameter + " with '" + name + "' name, an error occurred.");
            throw e;
        }
    }

    private ObservableList<CentrumKrwiodawstwa> getDonorsList(ResultSet resultSet) throws SQLException {

        ObservableList<CentrumKrwiodawstwa> donorsList = FXCollections.observableArrayList();

        while (resultSet.next()) {

            CentrumKrwiodawstwa ck = new CentrumKrwiodawstwa();
            ck.setDonationDay(resultSet.getString("Data_pobrania"));
            ck.setPesel(resultSet.getString("PESEL"));
            ck.setName(resultSet.getString("Imie"));
            ck.setSurname(resultSet.getString("Nazwisko"));
            ck.setSex(resultSet.getString("Plec"));
            ck.setDateOfBirth(resultSet.getString("Data_urodzenia"));
            ck.setPlaceOfBirth(resultSet.getString("Miejsce_urodzenia"));
            ck.setAdress(resultSet.getString("Adres"));
            ck.setWeight(resultSet.getInt("Waga"));
            ck.setLastDonation(resultSet.getString("Ostatnie_oddanie"));
            ck.setBloodType(resultSet.getString("Grupa_krwi"));
            donorsList.add(ck);
        }

        return donorsList;
    }

    public void insertDonor(String PESEL, String imie, String nazwisko, String plec, String miejsce_urodzenia, String adres, int waga, String data_ostatniego_oddania,
                            String data_pobrania, String nazwa_placowki, String grupa_krwi) throws SQLException, ClassNotFoundException {

        StringBuilder sb = new StringBuilder("{ call nowa_osoba_data_pobrania('");
        sb.append(PESEL);
        sb.append("','");
        sb.append(imie);
        sb.append("','");
        sb.append(nazwisko);
        sb.append("','");
        sb.append(plec);
        sb.append("','");
        sb.append(miejsce_urodzenia);
        sb.append("','");
        sb.append(adres);
        sb.append("',");
        sb.append(waga);
        sb.append(",'");
        sb.append(data_ostatniego_oddania);
        sb.append("','");
        sb.append(data_pobrania);
        sb.append("','");
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
