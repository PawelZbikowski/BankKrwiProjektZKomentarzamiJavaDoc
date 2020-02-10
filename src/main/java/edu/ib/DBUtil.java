package edu.ib;

import javafx.scene.control.TextArea;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

public class DBUtil {

    private String userName;
    private String userPassword;

    private Connection conn = null;

    public DBUtil(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public void dbConnect() throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("No MySQL JDBC Driver found.");
            e.printStackTrace();
            throw e;
        }

        try {
            conn = DriverManager.getConnection(createURL());
        } catch (SQLException e) {
            System.out.println("Connection Failed! Try again.");
        }
    }

    public void dbDisconnect() throws SQLException {

        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed. Bye!");
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    private String createURL() {

        StringBuilder urlSB = new StringBuilder("jdbc:mysql://");
        urlSB.append("localhost:3306/");
        urlSB.append("bankkrwinew?");
        urlSB.append("useUnicode=true&characterEncoding=utf-8");
        urlSB.append("&user=");
        urlSB.append(userName);
        urlSB.append("&password=");
        urlSB.append(userPassword);
        urlSB.append("&serverTimezone=CET");

        return urlSB.toString();
    }

    public ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {

        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs;

        try {

            dbConnect();

            stmt = conn.prepareStatement(queryStmt);

            resultSet = stmt.executeQuery(queryStmt);

            crs = new CachedRowSetWrapper();

            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation.");
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }

        return crs;
    }

    public void dbCallableStatement(String sqlStmt) throws SQLException, ClassNotFoundException{

        CallableStatement stmt = null;
        try {
            dbConnect();
            stmt = conn.prepareCall(sqlStmt);
            stmt.executeQuery();

        } catch (SQLException e) {
            System.out.println("Problem occurred at callableStatement operation");
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }
}
