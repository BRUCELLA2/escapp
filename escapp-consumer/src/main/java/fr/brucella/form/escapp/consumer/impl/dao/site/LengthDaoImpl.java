package fr.brucella.form.escapp.consumer.impl.dao.site;

import fr.brucella.form.escapp.consumer.contract.dao.site.LengthDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.site.LengthRM;
import fr.brucella.form.escapp.model.beans.site.Length;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class LengthDaoImpl extends AbstractDao implements LengthDao {
	
    // TODO Complete methods
    // TODO Add try/catch for Exception

	@Override
	public Length getLength(Integer pLengthId) throws TechnicalException {
		
		String vSQL = "SELECT * FROM length WHERE id = :id";
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pLengthId);
		
		RowMapper<Length> vRowMapper = new LengthRM();
		
		return getNamedJdbcTemplate().queryForObject(vSQL, vParams, vRowMapper);
	}
	

	@Override
	public List<Length> getLengthsList(Integer pRouteId) throws TechnicalException {

		String vSQL = "SELECT * FROM length WHERE route_id = :routeId";
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("routeId", pRouteId);
		
		RowMapper<Length> vRowMapper = new LengthRM();
		
		return getNamedJdbcTemplate().query(vSQL, vParams,vRowMapper);
	}

	@Override
	public void updateLength(Length pLength) throws TechnicalException {
		
		String vSQL = "UPDATE length SET length = :length, grade = :grade, points_nb = :pointsNb, description = :description, route_id = :routeId WHERE id = :id";
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pLength);
		
		getNamedJdbcTemplate().update(vSQL, vParams);
	}

	@Override
	public void insertLength(Length pLength) throws TechnicalException {
		
		String vSQL = "INSERT INTO length (id, length, grade, points_nb, description, route_id) VALUES (DEFAULT, :length, :grade, :pointsNb, :description, :routeId)";
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pLength);
		
		getNamedJdbcTemplate().update(vSQL, vParams);		
	}

	@Override
	public void deleteLength(Integer pLengthId) throws TechnicalException {
		
		String vSQL = "DELETE FROM lenght WHERE id = :id";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pLengthId);
		
		getNamedJdbcTemplate().update(vSQL, vParams);
	}
	
}
