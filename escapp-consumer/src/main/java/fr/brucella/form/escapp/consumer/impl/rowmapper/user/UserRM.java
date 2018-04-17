package fr.brucella.form.escapp.consumer.impl.rowmapper.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.brucella.form.escapp.model.beans.user.User;

/**
 * This class allow to map row of {@link java.sql.ResultSet} to an {@link User} business object
 * 
 * @author BRUCELLA2
 */
public class UserRM implements RowMapper<User> {

	/**
	 * @see RowMapper#mapRow(ResultSet, int)
	 */
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User vUser = new User();
		
		vUser.setId(rs.getInt("id"));
		vUser.setLogin(rs.getString("login"));
		vUser.setPassword(rs.getString("password"));
		vUser.setEmail(rs.getString("email"));
		
		return vUser;
	}

	
}
