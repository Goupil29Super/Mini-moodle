package sample.bdd;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
	
	private String firstName;
	private String secondName;
	private String email;
	private String password;
	private String status;
	private String group;


	public User(String firstName, String secondName, String email, String password, String status, String group) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.email = email;
		this.password = password;
		this.status = status;
		this.group = group;
	}

	public void insert() {
		Connection connection = DbConnection.connect();
		PreparedStatement ps = null;
		try {
			String sql = "INSERT INTO user(firstName, email,passWord, secondName, status, groupe) VALUES(?,?,?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1,  this.firstName);
			ps.setString(2,  this.email);
			ps.setString(3,  this.password);
			ps.setString(4,  this.secondName);
			ps.setString(5, this.status);
			ps.setString(6, this.group);
			ps.execute();
		}catch(SQLException e) {
			System.out.println(e.toString());
		}
	}

}
