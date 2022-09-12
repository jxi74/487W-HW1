import java.sql.*;
import java.util.Calendar;
class FooJDBC {

  public static void main(String[] args) throws Exception{

    System.out.print("Connecting to the database...");
    String url = "jdbc:mysql://127.0.0.1:3306/sys";   // Sets URL that is used to connect to database
    String username = "root";						 // Username to login to database
    int year = Calendar.getInstance().get(Calendar.YEAR);  // Gets current year from when the program was run
    int year_check = year - 5;		// Integer that is set to be used to compare the current year from the timestamp of the value in the database
	String password = new String(System.console().readPassword("Password: "));   // read password from console with echo disabled
    																			// Console object will be null if run from IDE, need to run from console
     
	try (Connection conn = DriverManager.getConnection(url, username, password)) { // Try's to connect to database using information given (url, username, password)
		System.out.println("connected."); // If the database successfully connects, it will print this line
		    
		Statement stmt = conn.createStatement(); 
		ResultSet rset = stmt.executeQuery("SELECT * FROM SunLabUser " + "WHERE EXTRACT(year FROM time_stamp) > '"+ year_check+"'"); // SQL query that selects all values from SunLabUser table that 
																																	 // are less than 5 years old. It accomplishes this by extracting 			
		// extracting attributes names and types																					 // the year from the timestamp for each value and comparing the 
		ResultSetMetaData rsmd = rset.getMetaData();																				//  current year - 5 years. 
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			System.out.print(rsmd.getColumnName(i) + "\t\t");
		}
		
		System.out.print("\n");
		while (rset.next()) {   // prints out each column based on its type (int, string, timestamp, etc.)
			System.out.println(rset.getInt(1) + "\t" +
		    rset.getString(2) + "\t" +
		    rset.getTimestamp(3) + "\t" +
		    rset.getString(4) );
		                        
		}
		
	} catch (SQLException e) { // If the parameters are incorrect for the database you are trying to connect to, it will catch this error.
		throw new IllegalStateException("Error! Something went wrong", e);	 // It will also catch an error for anything else in this try-catch block
	}
    
  }
}