// File: Menu.java
//
// This program illustrates the use of a menu, which would be the basis
// for constructing a larger program by adding more options, where each
// option is handled by a separate function.
//
import java.sql.*;
import java.util.Scanner;
public class Menu
{
    public static void main(String[] args)
    {
       int choice;
        Connection conn = null;
        try
        {     
            // Step 1: connect to the database server using a connection string.
            String host = "cslab-db.cs.wichita.edu";
            int port = 3306;
            String database = "dbuser13_database";
            String user = "dbuser13";
            String password = "jwQfZRKsBdoO";
            String url = 
            String.format("jdbc:mariadb://%s:%s/%s?user=%s&password=%s",
                            host, port, database, user, password);

                conn = DriverManager.getConnection(url);
            // Step 2: Display the menu and get the user response.
            choice = PrintMenuAndGetResponse( );
            // Step 3: Respond to the menu choice.
            switch (choice)
            {
                case 1: // A choice of 1 is to print all teams, locations and divison
                PlayerTeam(conn);
                    break;

                case 2: // Print a the coaches for a team
                CoachesQuery(conn);
                break;

                case 3: // To quit the program.

                System.out.println("Exiting Program");
                break;

            default: // Illegal choice for integers other than 1, 2 and 3.

            System.out.println("Illegal choice");
            break;

            }
        }
    catch(SQLException e)
    {
        e.printStackTrace();
    }
    finally
        {
// Step 4: Disconnect from the database server.
        try
            {
                if (conn != null)
                conn.close();
            }   
        catch(SQLException e)
            {
              e.printStackTrace();
            }
        }
    }
// This function controls the user interaction with the menu.
public static int PrintMenuAndGetResponse( )
{
Scanner keyboard = new Scanner(System.in);
int response;
System.out.println("Choose from one of the following options:");
System.out.println(" 1. List all players.");
System.out.println(" 2. For a given team, list the coach and playstyle.");
System.out.println(" 3. Quit the program%n");
System.out.print("Your choice ==> ");
response = keyboard.nextInt();
// Leave a blank line before printing the output response.
System.out.println( );
return response;
}

/*
// This function lists all student names, IDs and their majors.
public static void TeamLocationQuery(Connection conn) throws SQLException
{
Statement stmt = conn.createStatement();
String qry = "select Tname, City, Division"

+
"from Teams"

ResultSet rs = stmt.executeQuery(qry);
// Loop through the result set and print the output.
// First -- print the output column headings.
System.out.format("%n");
System.out.format("%-12s %12s %-20s%n", "Team Name", "City", "Division");
// Then -- print the body of the output table.
while (rs.next())
{
String tname = rs.getString("TName");
String loc = rs.getString("City");
String div = rs.getString("Division");
System.out.format("%-12s %12d %-20s%n", tname, loc, div);
}
System.out.println();
rs.close();
}

*/

public static void PlayerTeam(Connection conn) throws SQLException
        {
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
// This function is for the query of coaches for team
public static void CoachesQuery(Connection conn)

throws SQLException

{
Statement stmt = conn.createStatement();
Scanner keyboard = new Scanner(System.in);
int teamname;
System.out.println("Enter the team: ");
teamname = keyboard.nextInt();

String qry = "select Cname, Playstyle, Tname"

+
"from Coaches, Teams "
+
"where Tname = Tname "
+
"and Tname = " + teamname;
ResultSet rs = stmt.executeQuery(qry);
// Loop through the result set and print the output.
// First -- print the output column headings.
System.out.format("%n");
System.out.format("%-12s %12s %-20s%n", "Name", "Playstyle", "Team Name");
// Then -- print the body of the output table.
while (rs.next())
{
String cname = rs.getString("Cname");
String pStyle = rs.getString("Playstyle");
String tname = rs.getString("TeamN");
System.out.format("%-12s %12s %-20s%n", cname, pStyle, tname);
}
System.out.println( );
rs.close();
}
}