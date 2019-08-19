// File:  UpdatePlayerTeam.java
//
// 
//
import java.sql.*;
public class UpdatePlayerTeam
{
    public static void main(String[] args)
    {
        connection connection = null;
        try
        {
            // Step 1: Connect to the database server.
            String host = "cslab-db.cs.wichita.edu";
            int port = 3306;
            String database = "dbuser13_database"; // fill in with group database
            String user = "dbuser13"; // fill in with group id
            String password = "jwQfZRKsBdoO"; // fill in with password
            String url = String.format("jdbc:mariadb://%s:%s/%s?user=%s&password=%s",
            host, port, database, user, password);

            connection = DriverManager.getConnection(url);
            // Step 2: Show the Team of player.
            Statement stmt = connection.createStatement();
            String qry = "select Team from Player where Name = Stephen Curry";
            System.out.println("Here is the original team for Stephen Curry:");
            ResultSet rs = stmt.executeQuery(qry);
            if (rs.next())
                {
                    String Tname = rs.getStr("Tname");
                    System.out.format("%12s%n", Tname);
                }

//Step 3: change the Team to Thunder.
            String cmd = "update Stephen Curry set Team = Thunder where Tname = Warriors";
            stmt.executeUpdate(cmd);
// Step 4: Show the changed team.
            System.out/println("Here is the changed team for Stephen Curry:");
            rs = stmt.executeQuery(qry);
        if (rs.next())
            {
                String tname = rs.getInt("Tname");
                System.out.format("%12s%n", Tname);
            }
        rs.close();
        System.out.format("%n");
        }
    catch(SQLException e)
    {
        e.printStackTrace();
    }
finally
{
try
{
if(connection != null)
connection.close();
}
catch(SQLException e)
{
e.printStackTrace();
}
}
}
}