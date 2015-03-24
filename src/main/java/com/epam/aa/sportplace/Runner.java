package com.epam.aa.sportplace;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Runner {
    public static void main(String[] args) {
        int size = 0;
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection con = null;
        DataSource ds = null;
        InitialContext cxt = null;

        String url = "jdbc:postgresql://localhost/sportplace";
        String user = "almas";
        String password = "Q1w2e3r4t5";
        String query = "";
        StringBuilder response = new StringBuilder();


        try {

            cxt = new InitialContext();
            ds = (DataSource) cxt.lookup("java:comp/env/jdbc/sportplace");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try{

            try {

                Class.forName("org.postgresql.Driver");


            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            con = ds.getConnection();
            st =  con.prepareStatement("SELECT VERSION()");
            rs = st.executeQuery();


            while(rs.next())
            {
                response.append(rs.getString(1));

            }
        }

        catch(SQLException exc)
        {
            Logger lgr = Logger.getLogger(Runner.class.getName());
            lgr.log(Level.SEVERE, exc.getMessage(), exc);
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Runner.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        System.out.println(response);
//        Connection con = null;
//        Statement st = null;
//        PreparedStatement pst = null;
//        ResultSet rs = null;
//
//        String url = "jdbc:postgresql://localhost/sportplace";
//        String user = "almas";
//        String password = "Q1w2e3r4t5";
//
//        boolean customersTableCreated = false;
//        Customer customer = new Customer();
//        customer.setFirstName("Almas");
//        customer.setLastName("Akchabayev");
//        customer.setBirthDate(LocalDate.of(1992, 12, 19));
//        customer.setEmail("almas.akchabayev@gmail.com");
//
//        try {
//            con = DriverManager.getConnection(url, user, password);
//            st = con.createStatement();
//            if (!customersTableCreated) {
//                st.execute("CREATE TABLE IF NOT EXISTS customers (" +
//                        "id SERIAL PRIMARY KEY, " +
//                        "deleted BOOLEAN DEFAULT FALSE, " +
//                        "first_name VARCHAR(25), " +
//                        "last_name VARCHAR(25), " +
//                        "contact_info_id INTEGER, " +
//                        "password VARCHAR(25), " +
//                        "birth_date DATE" +
//                        ");");
//                customersTableCreated = true;
//            }
//            String stm = "INSERT INTO customers(first_name, last_name, birth_date) " +
//                    "VALUES(?, ?, ?)";
//            pst = con.prepareStatement(stm);
//            pst.setString(1, customer.getFirstName());
//            pst.setString(2, customer.getLastName());
////            if (customer.getContactInfo() != null) {
////                ContactInfoDAO contactInfoDAO = new ContactInfoDAO();
////                contactInfoDAO.insertWithReturningId(customer.getContactInfo());
////            }
////            //TODO: what to do about password?
//            Date birthDateToStore = Util.getSQLDateFromLocalDate(customer.getBirthDate());
//            pst.setDate(3, birthDateToStore);
//
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            Logger logger = Logger.getLogger(Runner.class.getName());
//            logger.log(Level.SEVERE, e.getMessage(), e);
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (st != null) {
//                    st.close();
//                }
//                if (pst != null) {
//                    pst.close();
//                }
//                if (con != null) {
//                    con.close();
//                }
//
//            } catch (SQLException ex) {
//                Logger lgr = Logger.getLogger(Runner.class.getName());
//                lgr.log(Level.WARNING, ex.getMessage(), ex);
//            }
//        }
    }
}
