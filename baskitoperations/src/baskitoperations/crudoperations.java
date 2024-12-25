package baskitoperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class crudoperations {
	private static final String Driver = "com.mysql.cj.jdbc.Driver";
	private static final String username = "root";
	private static final String password = "root";
	private static final String url = "jdbc:mysql://localhost:3306/student";
	private static Connection conn;
	private static PreparedStatement pmst;

	public static void main(String[] args) {
		Scanner scr = new Scanner(System.in);
		int ch;
		do {
			getdetails();
			ch = Integer.parseInt(scr.next());
			switch (ch) {
			case 1:
				login();
				break;
			case 2:
				registration();
				break;
			case 3:
				adduser();
				break;
			case 4:
				deleteuser();
				break;
			case 5:
				modifyuserdetails();
				break;
			case 6:
				getall();
				break;
			case 7:
				getbyemail();
				break;
			case 8:
				System.exit(0);
				break;

			default:
				System.out.println("invalid operation");
				break;
			}
		} while (ch > 0);

	}

	private static void getbyemail() {
		try {
			Scanner scr = new Scanner(System.in);
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter tablename");
			String sql = "select *from " + scr.next() + " where email=?";
			pmst = conn.prepareStatement(sql);
			System.out.println("enter email");
			pmst.setString(1, scr.next());
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
				System.out.println("id : " + rs.getInt("id"));
				System.out.println("name : " + rs.getString("name"));
				System.out.println("email : " + rs.getString("email"));
				System.out.println("password : " + rs.getString("password"));

			}

			pmst.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private static void getall() {
		try {
			Scanner scr = new Scanner(System.in);
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter tablename");
			String sql = "select *from " + scr.next();
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
				System.out.println("id : " + rs.getInt("id"));
				System.out.println("name : " + rs.getString("name"));
				System.out.println("email : " + rs.getString("email"));
				System.out.println("password : " + rs.getString("password"));

			}

			pmst.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private static void modifyuserdetails() {
		try {
			Scanner scr = new Scanner(System.in);
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter table name");
			String sql = "update " + scr.next() + " set id=?,name=?,email=?,password=? where id=?";

			pmst = conn.prepareStatement(sql);
			System.out.println("enter id");
			pmst.setInt(1, scr.nextInt());

			System.out.println("enter name");
			pmst.setString(2, scr.next());
			System.out.println("enter email");
			pmst.setString(3, scr.next());
			System.out.println("enter password");
			pmst.setString(4, scr.next());
			System.out.println("provide id to be updated");
			pmst.setInt(5, scr.nextInt());

			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("updated succesfully");

			} else {
				System.out.println("error");
			}
			conn.close();
			pmst.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void adduser() {
		try {
			Scanner scr = new Scanner(System.in);
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);
			String sql = "insert into studentinfo(name,email,password) values(?,?,?) ";
			pmst = conn.prepareStatement(sql);
			System.out.println("enter name");
			pmst.setString(1, scr.next());
			System.out.println("enter email");
			pmst.setString(2, scr.next());
			System.out.println("enter password");
			pmst.setString(3, scr.next());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("data is inserted");

			} else {
				System.out.println("error");

			}
			pmst.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private static void deleteuser() {
		try {
			Scanner scr = new Scanner(System.in);
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter tablename");
			String sql = "delete from " + scr.next() + " where id=?";
			pmst = conn.prepareStatement(sql);
			System.out.println("enter id to be deleted");
			pmst.setInt(1, scr.nextInt());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("sucessfully deletd");

			} else {
				System.out.println("error");

			}
			pmst.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private static void registration() {
		// TODO Auto-generated method stub

	}

	private static void login() {
		try {
			Scanner scr = new Scanner(System.in);
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter email");
			String username = scr.next();
			System.out.println("enter password");
			String password = scr.next();
			String sql = "select *from studentinfo where email=? and password=?";
			pmst = conn.prepareStatement(sql);
			pmst.setString(1, username);
			pmst.setString(2, password);
			ResultSet rs = pmst.executeQuery();
			if (rs.next()) {
				System.out.println("succesfull login");
			} else {
				System.out.println("invalid user");
			}

			pmst.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private static void getdetails() {
		System.out.println("baskitwebsite operations");
		System.out.println("________________________________");
		System.out.println("choose an option");
		System.out.println("\t 1.login");
		System.out.println("\t 2.registration");
		System.out.println("\t 3.adduser");
		System.out.println("\t 4.delete user");
		System.out.println("\t 5.modify user details");
		System.out.println("\t 6.get all user details");
		System.out.println("\t 7.get user details by email");
		System.out.println("\t 8.exit");

	}

}
