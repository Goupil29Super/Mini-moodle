package sample.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Verification {
    public void readAllData() {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM user";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("firstname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String secondName = rs.getString("secondName");

                System.out.println("First Name " + firstName);
                System.out.println("Second Name" + secondName);
                System.out.println("email" + email);
                System.out.println("password" + password);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                rs.close();
                ;
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }


    public ArrayList<String> readSpeData(String data, String table) {
        ArrayList<String> liste = new ArrayList<String>();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT " + data + " FROM " + table;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String dataContain = rs.getString(data);

                System.out.println(data + " " + dataContain);
                liste.add(dataContain);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return liste;
    }


    public ArrayList<String> readQcmGroup(String smallGroup, String normalGroup) {
        ArrayList<String> liste = new ArrayList<String>();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT qcm_name, qcm_date, qcm_id, nb_question FROM qcm where group_owner = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, smallGroup);
            //ps.setString(2, normalGroup);
            rs = ps.executeQuery();
            while (rs.next()) {
                String dataContain = rs.getString("qcm_name");
                Date dateData = rs.getDate("qcm_date");
                String qcm_id = rs.getString("qcm_id");
                String nb_question = rs.getString("nb_question");

                // System.out.println(  dataContain);
                liste.add(dataContain + "\n" + dateData.toString() + "\n" + qcm_id.toString() + "\n" + nb_question.toString());
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return liste;
    }


    public boolean doubleUser(String emailAdress) {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isFind = false;

        try {
            String sql = "SELECT " + "email" + " FROM user";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next() && isFind != true) {
                String dataContain = rs.getString("email");
                if (emailAdress.equals(dataContain)) {
                    isFind = true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return isFind;
    }


    /*public String  readSpecificRow(String row,String table,String rowCondition, String condition){
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String informationSearch = "notFind";
        try {
            String sql = "Select " + "*" + " from "+ table +" where " + rowCondition + " = ?";
            // sql = "Select " + row + " from user where email = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, condition);
            rs = ps.executeQuery();
            informationSearch = rs.getString(1);// + rs.getString(2) + rs.getString(3) + rs.getString(4);
        }catch (SQLException e){
            System.out.println(e.toString() + "Q");
        }finally {
            try {
                rs.close();
                ps.close();
                con.close();

            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
        System.out.println("mdp :" + informationSearch);
        return informationSearch;
    }*/

    public String readSpecificRow(String row, String condition) {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String informationSearch = "notFind";
        try {
            String sql = "Select " + row + " from user where email = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, condition);
            rs = ps.executeQuery();
            informationSearch = rs.getString(1);
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        System.out.println("mdp :" + informationSearch);
        return informationSearch;
    }


    public void updateRow(String table, String editRow, String editValue, String rowCondition, String rowCondition2, String rowConditionValue, String rowConditionValue2) {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE " + table + " set " + editRow + " = ? where " + rowCondition + "  = ? and " + rowCondition2 + " = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, editValue);
            ps.setString(2, rowConditionValue);
            ps.setString(3, rowConditionValue2);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public String readSpecificRowtest(/*String table, String row, String rowCondition,String rowCondition2, String rowConditionValue, String rowConditionValue2*/) {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String informationSearch = "notFind";
        try {
            String sql = "select * from question where numeroQuestion = 1";
            //String sql = "select "+ row + " from "+ table +" where "+ rowCondition +"  = ? and " + rowCondition2 + " = ?";
            ps = con.prepareStatement(sql);
            //ps.setString(1, rowConditionValue);
            //ps.setString(2, rowConditionValue2);
            rs = ps.executeQuery();
            informationSearch = rs.getString(1) + rs.getString(2) + rs.getString(3) + rs.getString(4);
        } catch (SQLException e) {
            System.out.println(e.toString() + "Q");
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return informationSearch;
    }


    public String readAllDatatest(String table, String row, String rowCondition, String rowCondition2, String rowConditionValue, String rowConditionValue2) {
        String information = "";
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //String sql = "select * from question where numeroQuestion = 1 and reponse = 4";
            String sql = "select " + row + " from " + table + " where " + rowCondition + "  = ? and " + rowCondition2 + " = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, rowConditionValue);
            ps.setString(2, rowConditionValue2);
            rs = ps.executeQuery();
            while (rs.next()) {
                String qcm_id = rs.getString("qcm_id");
                String numeoroQuestion = rs.getString("numeroQuestion");
                String question = rs.getString("question");
                String reponse = rs.getString("reponse");
                String typeQuestion = rs.getString("typeQuestion");

                information = qcm_id + "\n" + numeoroQuestion + "\n" + question + "\n" + reponse + "\n" + typeQuestion;


                System.out.println("qcm_id " + qcm_id);
                System.out.println("numeorQuestion " + numeoroQuestion);
                System.out.println("question " + question);
                System.out.println("reponse " + reponse);
                System.out.println("typeQuestion " + typeQuestion);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                rs.close();
                ;
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

        return information;
    }


    public boolean checkUserQcm(String qcmTokken, String mail) {
        boolean isFind = false;
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String informationSearch = "notFind";
        try {
            String sql = "Select qcm from user_data where qcm = ? and email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, qcmTokken);
            ps.setString(2, mail);
            rs = ps.executeQuery();
            informationSearch = rs.getString(1);
            if (informationSearch.equals(qcmTokken)) {
                isFind = true;
                System.out.println("okkkkkkkkkkkkkkkkk");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return isFind;
    }

}
