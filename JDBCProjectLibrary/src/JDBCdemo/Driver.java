package JDBCdemo;

import java.sql.*;
import java.util.Scanner;

public class Driver {
	
	
	//insert function with PreparedStatements
	public static void addNewBook(Connection con, String name, String date, String ISBN) throws SQLException{
		PreparedStatement pstmt = null;
		
	    String insertString = "INSERT INTO LIBRARY.BOOKS(NAME, DATEOFISSUE, ISBN)" +
				"values( ? , ? , ? )";
	    try {
	        con.setAutoCommit(false);
	        pstmt = con.prepareStatement(insertString);
	        
	        pstmt.setString(1,name);
	        pstmt.setString(2,date);
	        pstmt.setString(3,ISBN);
	        
	        pstmt.executeUpdate();	
	      
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	    } finally {
	       pstmt.close();
	    }
		
	}
	
	//delete function with PreparedStatements
	public static void deleteBook(Connection con, String name) throws SQLException{
		PreparedStatement pstmt = null;
		
	    String insertString = "DELETE FROM Books " +
                "WHERE name = ?";
	    try {
	        con.setAutoCommit(false);
	        pstmt = con.prepareStatement(insertString);
	        
	        pstmt.setString(1,name);
	        
	        pstmt.executeUpdate();	
	      
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	    } finally {
	       pstmt.close();
	    }
		
	}
	
	public static void main(String[] args) {
		
		try{
			//connecting to database (database created in mysql workbench called Library)
			
			String connectionURL = "jdbc:mysql://localhost/Library";
			String User = "root";
			String Pass = "root";
			Connection Conn = DriverManager.getConnection(connectionURL, User, Pass);
			
			//create a statement
			Statement Stmt = Conn.createStatement();
			
			
			//String sql  = "CREATE DATABASE LIBRARY";
			//Stmt.executeUpdate(sql);
			
			
			
			
			String sql2 = "DROP TABLE IF EXISTS Books";
			Stmt.executeUpdate(sql2);
			
			sql2 = "CREATE TABLE BOOKS " +
	                   "(id INTEGER not NULL AUTO_INCREMENT, " +
	                   " Name VARCHAR(255), " + 
	                   " DateofIssue DATE NOT NULL, " + 
	                   " ISBN CHAR(13) NOT NULL, " + 
	                   " PRIMARY KEY ( id ))";
			Stmt.executeUpdate(sql2);
			
			
			sql2 = "DROP TABLE IF EXISTS Authors";
			Stmt.executeUpdate(sql2);
			
			sql2 = "CREATE TABLE Library.Authors" +
					"(Id INTEGER NOT NULL AUTO_INCREMENT, " +
					"Name VARCHAR(255) NOT NULL, " +
					"PRIMARY KEY(Id))";
			Stmt.executeUpdate(sql2);
			
			
			sql2 = "DROP TABLE IF EXISTS Connections";
			Stmt.executeUpdate(sql2);
			
			sql2 = "CREATE TABLE Library.Connections" +
					"(book integer NOT NULL," +
					"Author integer NOT NULL," +
					"FOREIGN KEY(book) REFERENCES Library.Books(Id)  ON DELETE CASCADE," +
					"FOREIGN KEY(Author) REFERENCES Library.Authors(Id) ON DELETE CASCADE," +
					"PRIMARY KEY(book, Author))";
			Stmt.executeUpdate(sql2);
			
			
			
			//Value input
			String inputs = "INSERT INTO LIBRARY.BOOKS(NAME, DATEOFISSUE, ISBN)" +
							"values('Hobbit','2015-02-21','1234567890123')";
			Stmt.executeUpdate(inputs);
			
			inputs = "INSERT INTO Library.Books(Name,DateOfIssue,ISBN)" +
					"values('Metro 2033','2012-09-25','7777778888881')";
			Stmt.executeUpdate(inputs);
			
			inputs = "INSERT INTO Library.Books(Name,DateOfIssue,ISBN)" +
					"values('Suncho','2008-06-08','1234567890157')";
			Stmt.executeUpdate(inputs);
			
			inputs = "INSERT INTO Library.Books(Name,DateOfIssue,ISBN)" +
					"values('SQL for dummies','2017-04-06','9876543210987')";
			Stmt.executeUpdate(inputs);
			
			inputs = "INSERT INTO Library.Books(Name,DateOfIssue,ISBN)" +
					"values('Minecraft','2015-12-25','0987654321875')";
			Stmt.executeUpdate(inputs);
			
			inputs = "INSERT INTO Library.Books(Name,DateOfIssue,ISBN)" +
					"values('BUM BIM','2015-06-12','0987654321555')";
			Stmt.executeUpdate(inputs);
			
			//Using PreparedStatement function
			String name = "THIsIsATEST";
			String date = "2025-06-12";
			String ISBN = "0666666666666";
			addNewBook(Conn, name, date, ISBN);
			
			
			//user input
			Scanner scanner = new Scanner (System.in);
			System.out.print("enter a name to of the book"  +  "\n"); 
			name = scanner.next();
			System.out.print("date of issue");
			date = scanner.next();
			System.out.print("and ISBN");
			ISBN = scanner.next();
			scanner.close();
			addNewBook(Conn, name, date, ISBN);
			
			
			
			
			inputs = "INSERT INTO Library.Authors(name)" +
					"values('Ceko cekov')";
			Stmt.executeUpdate(inputs);
			
			inputs = "INSERT INTO Library.Authors(name)" +
					"values('Pesho Peshov')";
			Stmt.executeUpdate(inputs);
			
			inputs = "INSERT INTO Library.Authors(name)" +
					"values('Ivan Ivanov')";
			Stmt.executeUpdate(inputs);
			
			inputs = "INSERT INTO Library.Authors(name)" +
					"values('Gosho Goshev')";
			Stmt.executeUpdate(inputs);
			
			inputs = "INSERT INTO Library.Authors(name)" +
					"values('Ganka Gankova')";
			Stmt.executeUpdate(inputs);
			
			inputs = "INSERT INTO Library.Authors(name)" +
					"values('Milko Milkov')";
			Stmt.executeUpdate(inputs);
			
			inputs = "INSERT INTO Library.Authors(name)" +
					"values('asdfa sf asdf')";
			Stmt.executeUpdate(inputs);
			
			
			inputs = "INSERT INTO Library.Connections(book , Author)" +
					"values(1,1)";
			Stmt.executeUpdate(inputs);
			
			inputs = "INSERT INTO Library.Connections(book , Author)" +
					"values(2,2)";
			Stmt.executeUpdate(inputs);
			
			inputs = "INSERT INTO Library.Connections(book , Author)" +
					"values(3,3)";
			Stmt.executeUpdate(inputs);
			
			inputs = "INSERT INTO Library.Connections(book , Author)" +
					"values(4,4)";
			Stmt.executeUpdate(inputs);
			
			inputs = "INSERT INTO Library.Connections(book , Author)" +
					"values(5,5)";
			Stmt.executeUpdate(inputs);
			
			inputs = "INSERT INTO Library.Connections(book , Author)" +
					"values(6,6)";
			Stmt.executeUpdate(inputs);
			
			inputs = "INSERT INTO Library.Connections(book , Author)" +
					"values(6,1)";
			Stmt.executeUpdate(inputs);
			
			inputs = "INSERT INTO Library.Connections(book , Author)" +
					"values(6,2)";
			Stmt.executeUpdate(inputs);
			
		
			
			//UPDATE
			 String sql = "UPDATE Books " +
	                   "SET name = 'COOL NEW NAME FOR A BOOK' WHERE name = 'Metro 2033'";
			 Stmt.executeUpdate(sql);
			
			 //DELETE
			 //deleteBook(connection, name of the book to delete);
			 deleteBook(Conn, "Hobbit");
			 
			//executing SQL query
			ResultSet Res = Stmt.executeQuery("Select * from Books");
			
			//process
			while(Res.next()){
				System.out.println(Res.getString("Name") + ", " + Res.getString("DateOfIssue"));
			}
			
			Res = Stmt.executeQuery("SELECT name FROM Library.Books as books WHERE year(books.DateOfIssue)='2015'");
			
			System.out.println();
			
			System.out.println("Every book writen in 2015");
			while(Res.next()){
				System.out.println(Res.getString("Name"));
			}
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}