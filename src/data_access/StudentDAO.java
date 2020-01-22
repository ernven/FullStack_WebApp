package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.Student;

public class StudentDAO {

	public StudentDAO() {
		try {
			Class.forName(ConnectionParameters.jdbcDriver);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
	}

	private Connection openConnection() throws SQLException {
		return DriverManager.getConnection(ConnectionParameters.connectionString, ConnectionParameters.username,
				ConnectionParameters.password);
	}

	public List<Student> getAllStudents() {
		List<Student> studentList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = openConnection();

			String sqlText = "SELECT * FROM Student ORDER BY lastname";

			preparedStatement = connection.prepareStatement(sqlText);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				String streetAddress = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode");
				String city = resultSet.getString("postoffice");

				studentList.add(new Student(id, firstName, lastName, streetAddress, postcode, city));
			}

		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: getAllStudents() failed. " + sqle.getMessage() + "\n");
			studentList = null;

		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}

		return studentList;
	}

	public Student getStudentById(int studentId) {
		Student student = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = openConnection();

			String sqlText = "SELECT * FROM Student WHERE id = ?";

			preparedStatement = connection.prepareStatement(sqlText);

			preparedStatement.setInt(1, studentId);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				String streetAddress = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode");
				String city = resultSet.getString("postoffice");

				student = new Student(id, firstName, lastName, streetAddress, postcode, city);
			}

		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: getAllStudents() failed. " + sqle.getMessage() + "\n");
			student = null;

		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}

		return student;
	}

	public String getAllStudentsJSON() {
		Gson gson = new Gson();
		String toRet = "";

		Student student = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = openConnection();

			String sqlText = "SELECT * FROM Student ORDER BY lastname";

			preparedStatement = connection.prepareStatement(sqlText);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode");
				String postoffice = resultSet.getString("postoffice");

				student = new Student(id, firstname, lastname, streetaddress, postcode, postoffice);

				toRet = toRet + gson.toJson(student);
			}

		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: getAllStudents() failed. " + sqle.getMessage() + "\n");
			student = null;

		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}

		return toRet;
	}

	public int insertStudent(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result;

		try {
			connection = openConnection();

			String query = "INSERT INTO Student (id, firstname, lastname, streetaddress, postcode, postoffice) VALUES (?, ?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getFirstName());
			preparedStatement.setString(3, student.getLastName());
			preparedStatement.setString(4, student.getStreetAddress());
			preparedStatement.setString(5, student.getPostcode());
			preparedStatement.setString(6, student.getCity());

			preparedStatement.executeUpdate();

			result = 0;
		} catch (SQLException sqle) {
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				result = 1;
			} else {
				result = -1;
			}
		} finally {
			DbUtils.closeQuietly(preparedStatement, connection);
		}

		return result;
	}

	public int deleteStudent(int studentId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result;

		try {
			connection = openConnection();

			String query = "DELETE FROM Student WHERE id = ?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, studentId);

			result = preparedStatement.executeUpdate();
		} catch (SQLException sqle) {
			result = -1;
		} finally {
			DbUtils.closeQuietly(preparedStatement, connection);
		}

		return result;
	}

	public int updateStudent(Student student) {
		int result;

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = openConnection();

			String query = "UPDATE Student SET id = ?, firstname = ?, lastname = ?, streetaddress = ?, postcode = ?, postoffice = ? WHERE id = "
					+ student.getId();

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getFirstName());
			preparedStatement.setString(3, student.getLastName());
			preparedStatement.setString(4, student.getStreetAddress());
			preparedStatement.setString(5, student.getPostcode());
			preparedStatement.setString(6, student.getCity());

			result = preparedStatement.executeUpdate();
		} catch (SQLException sqle) {
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				result = 0;
			} else {
				result = -1;
			}
		} finally {
			DbUtils.closeQuietly(preparedStatement, connection);
		}

		return result;
	}
}