//
// This is a program to test connecting to a mariadb server from a Java
// program. The use of a format method is employed to print the output.
//
// The output lists student names, IDs and their majors.
//
// Compile this program using the command: javac StudentMajor.java
// Execute this program using the command below with the Driver Manager jar file:
//
// java -cp "mariadb-java-client-2.4.1.jar:." PlayerTeam
//
import java.sql.*;
    public class PlayerTeam
    {
        public static void main(String[] args)
        {
        Connection connection = null;
        try
        {
            // Step 1: Connect to the database server.
            String host = "cslab-db.cs.wichita.edu";
            int port = 3306;
            String database = "dbuser13_database"; // *** group database here ***
            String user = "dbuser13"; // *** group account here ***
            String password = "jwQfZRKsBdoO"; // *** group password here ***
            String url = String.format("jdbc:mariadb://%s:%s/%s?user=%s&password=%s",
                                    host, port, database, user, password);

            connection = DriverManager.getConnection(url);
            // Step 2: build and execute the query.
            Statement stmt = connection.createStatement();
            String qry = "select Name, JerseyNumber, Tname "
            + "from Players, Teams "
            + "where Tname = TeamName";
            ResultSet rs = stmt.executeQuery(qry);

            // Step 3: Prepare the output.
            // Print the output headings:
                // -- note that %n represents the newline in Java
            System.out.format("%n");
            System.out.format("%-12s %3s %-20s%n", "Name", "#", "Team");
            // Step 4: Loop through the result set returned by the server.
            while (rs.next())
                {
                    String pname = rs.getString("Name");
                    Int num = rs.getInt("JerseyNumber");
                    String teamname = rs.getString("Tname");
                    System.out.format("%-12s %3d %-20s%n", pname, num, teamname);
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
// Step 5: Disconnect from the server.
try
{
if (connection != null)
connection.close();
}
catch(SQLException e)
{
e.printStackTrace();
}
}
}
}