import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Calendar;

public class DatabaseGUI {

 public static Connection conn;
 public static JFrame frame = new JFrame();
 public static JButton login = new JButton("Log in");
 public static JTextField username = new JTextField(15);
 public static JPasswordField password = new JPasswordField(15);
 
 public static JTextField role = new JTextField(15);
 public static JButton query = new JButton("Run Query");

public static void loginAction(ActionEvent event) throws Exception{
   String url = "jdbc:mysql://127.0.0.1:3306/sys";  // Sets URL that is used to connect to database
   String uservalue = username.getText();          // Sets username inputed from GUI
   String passvalue = password.getText();		   // Sets password inputed from GUI
   conn = DriverManager.getConnection(url, uservalue, passvalue);  //Attempts connection to database based on given url, username, and password
   System.out.println("Connected ");  //Prints this line if connection is successful
   frame.add(new JLabel("Connected "));
   frame.setVisible(true);
}

public static void queryAction(ActionEvent event) throws Exception{

   int year = Calendar.getInstance().get(Calendar.YEAR);  // Gets current year from when the program was run
   int year_check = year - 5;                             // Integer that is set to be used to compare the current year from the timestamp of the value in the database
   Statement stmt = conn.createStatement();
   ResultSet rset = stmt.executeQuery("SELECT * FROM SunLabUser " + "WHERE role = '" + role.getText() + // SQL query that selects all values from SunLabUser table that
		   										"' AND EXTRACT(year FROM time_stamp) > '"+ year_check+"'");  // are from the specific role (Teacher, Student, Faculty, etc.) given in the GUI input box.
    while (rset.next() ) {																					// It is also only the records that are less than 5 years old.
      // Adds to the GUI each column based on its type (String, int, etc.)									// This is done by extracting the year from the timestamp for each value
      frame.add(new JLabel(rset.getString(1)));      														// and comparing the current year - 5 years.
            frame.add(new JLabel(rset.getString(2)));
                  frame.add(new JLabel(rset.getString(3)));
                        frame.add(new JLabel(rset.getString(4)));   
      frame.setVisible(true);  
      
    } 

}
 public static void main(String[] args) throws Exception {

   login.addActionListener(new ActionListener() { 
         public void actionPerformed(ActionEvent event) {
            try { //Try's to connect to database
               loginAction(event);
               } catch (Exception E) {};
         }
   });
   
   query.addActionListener(new ActionListener() { 
         public void actionPerformed(ActionEvent event) {
            try {
               queryAction(event);
               } catch (Exception E) {};
         }
   });
   
   // Code that creates GUI
   frame.setLayout(new FlowLayout());
   frame.setTitle("Database Connection Example");
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setSize(400,600);
   
   frame.add(new JLabel("Username "));
   frame.add(username);
   frame.add(new JLabel("Password "));
   frame.add(password);
   frame.add(login);
   
   frame.add(role);
   frame.add(query);
   
   frame.setResizable(true);
   frame.setVisible(true);
 }
} 