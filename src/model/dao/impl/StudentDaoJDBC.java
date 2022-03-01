package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.StudentDao;
import model.entities.Student;

public class StudentDaoJDBC implements StudentDao {

	private Connection conn;
	
	public StudentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Student obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO tbl_student " +
				"(pk_number, name_student, course, nota1, nota2, nota3, nota4) " +
				"VALUES " +
				"(?, ?, ?, ?, ?, ?, ?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, obj.getNumber());
			st.setString(2, obj.getName());
			st.setString(3, obj.getCourse());
			st.setFloat(4, obj.getNota1());
			st.setFloat(5, obj.getNota2());
			st.setFloat(6, obj.getNota3());
			st.setFloat(7, obj.getNota4()); 		

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setNumber(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Student obj) {}

	@Override
	public void deleteById(Integer id) {}

	@Override
	public Student findById(Integer id) {
		return null;
	}

	@Override
	public List<Student> findAll() {
		return null;
	}
}