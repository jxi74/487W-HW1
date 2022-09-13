import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Calendar;

public class DatabaseGUI2 {

	public static int year = Calendar.getInstance().get(Calendar.YEAR); // Gets current year from when the program was run
	public static int year_check = year - 5; // Integer that is set to be used to compare the current year from the timestamp of the value in the database
	
	public static String url = "jdbc:mysql://127.0.0.1:3306/sys";  // Sets URL that is used to connect to database
	public static Connection conn;
	public static JFrame adminFrame = new JFrame();
	public static JFrame studentFrame = new JFrame();
	public static JFrame homeFrame = new JFrame();
	public static JButton login = new JButton("Log in");
	public static JButton studentLogin = new JButton("Student Swipe");
	public static JButton swipe = new JButton("Swipe");
	public static JTextField studentSwipe = new JTextField(15);
	
	public static JButton AdminLogin = new JButton("Admin Login");
	public static JButton idHistory = new JButton("By Student ID");
	public static JTextField AdminStudentID = new JTextField(15);
	public static JButton dateHistory = new JButton("By Date");
	public static JTextField AdminDate = new JTextField(15);
	public static JButton rangeHistory = new JButton("By Range of Dates");
	public static JTextField AdminRange1 = new JTextField(15);
	public static JTextField AdminRange2 = new JTextField(15);
	
	public static JTextField username = new JTextField(15);
	public static JPasswordField password = new JPasswordField(15);
 
	//public static JTextField role = new JTextField(15);
	//public static JButton query = new JButton("Run Query"); //Run Query button

	
	public static void studentAction(ActionEvent event) throws Exception {
		
		studentFrame.setLayout(new FlowLayout());
		studentFrame.setTitle("Student Swipe");
		studentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		studentFrame.setSize(400,600);
		   
		studentFrame.add(new JLabel("Enter Student ID "));
		studentFrame.add(studentSwipe);
		studentFrame.add(swipe);
		   
		studentFrame.setResizable(true);
		studentFrame.setVisible(true);
		
		swipe.addActionListener(new ActionListener() { //swipe is button, addActionListener makes so the button does something
			public void actionPerformed(ActionEvent event) {
				try { //Try's to connect to database
					swipeAction(event);
				} catch (Exception E) {};
			}
		});
	}
	
	public static void swipeAction(ActionEvent event) throws Exception {
		String user = "root";         // Sets username
		String pass = "Pennstate123";		   // Sets password: Security issue for anyone who reads code
		conn = DriverManager.getConnection(url, user, pass);  //Attempts connection to database based on given url, username, and password
		
		Timestamp currTimeStamp = new Timestamp(System.currentTimeMillis());
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("INSERT INTO SunLabUser VALUES(" + studentSwipe.getText() + ",'" + currTimeStamp + "');");
		studentFrame.add(new JLabel("Swipe successful!"));
		studentFrame.setVisible(true);
	}
	
	public static void IDAction(ActionEvent event) throws Exception {
		Statement stmt = conn.createStatement();
		ResultSet rID = stmt.executeQuery("SELECT * FROM SunLabUser " + "WHERE sID = '" + AdminStudentID.getText() + 
															"' AND EXTRACT(year FROM time_stamp) > '"+ year_check+"'");
		
		while (rID.next() ) {																					
			// Adds to the GUI each column based on its type (String, int, etc.)									
			adminFrame.add(new JLabel(rID.getString(1)));    
				adminFrame.add(new JLabel(rID.getString(2)));  
			adminFrame.setVisible(true);  
		}
	}
		
	public static void dateAction(ActionEvent event) throws Exception {
		Statement stmt = conn.createStatement();
		ResultSet rDate = stmt.executeQuery("SELECT * FROM SunLabUser " + "WHERE date(time_stamp) = '" + AdminDate.getText() + 
															"' AND EXTRACT(year FROM time_stamp) > '"+ year_check+"'");
		
		while (rDate.next() ) {																					
			// Adds to the GUI each column based on its type (String, int, etc.)									
			adminFrame.add(new JLabel(rDate.getString(1)));    
				adminFrame.add(new JLabel(rDate.getString(2)));  
			adminFrame.setVisible(true);  
		}
	}
	
	public static void rangeAction(ActionEvent event) throws Exception {
		Statement stmt = conn.createStatement();
		ResultSet rRange = stmt.executeQuery("SELECT * FROM SunLabUser " + "WHERE date(time_stamp) BETWEEN '" + AdminRange1.getText() + 
															"' AND '" + AdminRange2.getText() + "' AND EXTRACT(year FROM time_stamp) > '"+ year_check+"'");
		
		while (rRange.next() ) {																					
			// Adds to the GUI each column based on its type (String, int, etc.)									
			adminFrame.add(new JLabel(rRange.getString(1)));    
				adminFrame.add(new JLabel(rRange.getString(2)));  
			adminFrame.setVisible(true);  
		}
	}
	
	
	public static void loginAction(ActionEvent event) throws Exception {
		String uservalue = username.getText();          // Sets username inputed from GUI
		String passvalue = password.getText();		   // Sets password inputed from GUI
		conn = DriverManager.getConnection(url, uservalue, passvalue);  //Attempts connection to database based on given url, username, and password
		System.out.println("Connected ");  //Prints this line if connection is successful
		adminFrame.add(new JLabel("Connected ")); //Displays the word "Connected" in GUI
		adminFrame.setVisible(true); //sets it visible
	}
	
	



	public static void queryAction(ActionEvent event) throws Exception{
		
		adminFrame.setLayout(new FlowLayout());
		adminFrame.setTitle("Admin Login");
		adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminFrame.setSize(400,600);
		adminFrame.add(new JLabel("Username "));
		adminFrame.add(username);
		adminFrame.add(new JLabel("Password "));
		adminFrame.add(password);
		adminFrame.add(login);
		adminFrame.add(AdminStudentID); //Textbox to enter role
		adminFrame.add(idHistory); //Run Query button (Used to calculate query for whatever you are doing)
		adminFrame.add(AdminDate);
		adminFrame.add(dateHistory);
		adminFrame.add(AdminRange1);
		adminFrame.add(AdminRange2);
		adminFrame.add(rangeHistory);
		
		adminFrame.setResizable(true);
		adminFrame.setVisible(true);
		
		login.addActionListener(new ActionListener() { //login is button, addActionListener makes so the button does something
			public void actionPerformed(ActionEvent event) {
				try { //Try's to connect to database
					loginAction(event);
				} catch (Exception E) {};
			}
		});
		
		idHistory.addActionListener(new ActionListener() { //idHistory is button, addActionListener makes so the button does something
			public void actionPerformed(ActionEvent event) {
				try { //Try's to connect to database
					IDAction(event);
				} catch (Exception E) {};
			}
		});
		
		dateHistory.addActionListener(new ActionListener() { //dateHistory is button, addActionListener makes so the button does something
			public void actionPerformed(ActionEvent event) {
				try { //Try's to connect to database
					dateAction(event);
				} catch (Exception E) {};
			}
		});
		
		rangeHistory.addActionListener(new ActionListener() { //rangeHistory is button, addActionListener makes so the button does something
			public void actionPerformed(ActionEvent event) {
				try { //Try's to connect to database
					rangeAction(event);
				} catch (Exception E) {};
			}
		});
		
	}

	
	public static void main(String[] args) throws Exception {
		
		studentLogin.addActionListener(new ActionListener() { //studentLogin is button, addActionListener makes so the button does something
			public void actionPerformed(ActionEvent event) {
				try { //Try's to connect to database
					studentAction(event);
				} catch (Exception E) {};
			}
		});
		
		AdminLogin.addActionListener(new ActionListener() { //AdminLogin is button, addActionListener makes so the button does something
			public void actionPerformed(ActionEvent event) {
				try { //Try's to connect to database
					queryAction(event);
				} catch (Exception E) {};
			}
		});
   
   // Code that creates Home GUI
   
   homeFrame.setLayout(new FlowLayout());
   homeFrame.setTitle("Home");
   homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   homeFrame.setSize(400,600);
   
   homeFrame.add(new JLabel("Select an option"));
   homeFrame.add(studentLogin);
   homeFrame.add(AdminLogin);
   
   homeFrame.setResizable(true);
   homeFrame.setVisible(true);
   
 }
} 