package com.nabeeh.webmail;
import java.sql.*;

public class Test {
    // Go to http://www.mysql.com/doc/en/Java.html to get JDBC driver
    // Or directly to get the driver here:
    // http://www.mysql.com/downloads/api-jdbc-stable.html
    // or just see /home/public/cs601 for the jar:
    //   mysql-connector-java-3.0.9-stable-bin.jar
    public static void main(String[] args) throws SQLException {
        String username  = "user";
        String password  = "passswprd";
        String db  = "userdb";
        try {
            // load driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch (Exception e) {
            System.err.println("Can't find driver");
            System.exit(1);
        }
        // format "jdbc:mysql://[hostname][:port]/[dbname]"
        String urlString = "jdbc:mysql://sql.usfca.edu/"+db;
        Connection con = DriverManager.getConnection(urlString,
                                                     username,
                                                     password);

	// do your work here
        Statement stmt = con.createStatement();
      
        ResultSet result = stmt.executeQuery (
                   "SELECT programmer, cups " + 
                   "FROM consumption ORDER BY cups DESC;");

             System.out.println("Programmer\tCups");
            while ( result.next() ) {
              String programmer = result.getString("programmer");
              int cups = result.getInt("cups");
                System.out.println(programmer+"\t"+cups);
                 }
            stmt.close();
      
         
            Statement istmt = con.createStatement();
             int n = istmt.executeUpdate(
          "INSERT INTO consumption VALUES ('Acedo','Fri',8)");
            System.out.println("Inserted "+n+" records");

               PreparedStatement prep = con.prepareStatement(
              "INSERT into consumption (programmer,cups) values (?, ?)");
                  prep.setString(1, "Jim");
                   prep.setInt(2, 8);
              /* if (prep.executeUpdate () != 1) {
                   throw new Exception ("Bad Update");                    
                 }
              */


        con.close();
    }
}

