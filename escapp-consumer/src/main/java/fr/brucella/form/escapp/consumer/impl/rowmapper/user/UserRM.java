package fr.brucella.form.escapp.consumer.impl.rowmapper.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.brucella.form.escapp.model.beans.user.User;

/**
 * This class allow to map row of {@link java.sql.ResultSet} to an {@link User} business object.
 *
 * @author BRUCELLA2
 */
public class UserRM implements RowMapper<User> {
  
  /**
   * @see RowMapper#mapRow(ResultSet, int)
   */
  @Override
  public User mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
    
    User user = new User();
    
    user.setId(resultSet.getInt("id"));
    user.setLogin(resultSet.getString("login"));
    user.setPassword(resultSet.getString("password"));
    user.setEmail(resultSet.getString("email"));
    
    return user;
  }
  
  
}
