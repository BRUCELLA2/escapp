package fr.brucella.form.escapp.consumer.impl.dao.user;

import fr.brucella.form.escapp.consumer.contract.dao.user.UserDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.user.UserRM;
import fr.brucella.form.escapp.model.beans.user.User;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl extends AbstractDao implements UserDao {
	
    // TODO Complete methods
    // TODO Add try/catch for Exception

	@Override
	public User getUserById(Integer pUserId) throws TechnicalException {
		
		String vSQL = "SELECT * FROM escapp_user WHERE id = :id";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pUserId);
		
		RowMapper<User> vRowMapper = new UserRM();
		
		return getNamedJdbcTemplate().queryForObject(vSQL, vParams, vRowMapper);		
	}
	

	@Override
	public User getUserByLogin(String pUserLogin) throws TechnicalException {
		
		String vSQL = "SELECT * FROM escapp_user WHERE login = :login";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("login", pUserLogin);
		
		RowMapper<User> vRowMapper = new UserRM();
		
		return getNamedJdbcTemplate().queryForObject(vSQL, vParams, vRowMapper);
	}

	@Override
	public void updateUser(User pUser) throws TechnicalException {

		String vSQL = "UPDATE escapp_user SET login = :login, email = :email, password = :password WHERE id = :id";
		
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pUser);
		
		getNamedJdbcTemplate().update(vSQL, vParams);
	}

	@Override
	public void insertUser(User pUser) throws TechnicalException {

		String vSQL = "INSERT INTO escapp_user (id, login, email, password) VALUES (DEFAULT, :login, :email, :password)";
		
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pUser);
		
		getNamedJdbcTemplate().update(vSQL, vParams);
	}

	@Override
	public void deleteUser(Integer pUserId) throws TechnicalException {
		
		String vSQL = "DELETE FROM escapp_user WHERE id = :id";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pUserId);
		
		getNamedJdbcTemplate().update(vSQL, vParams);
	}
}
