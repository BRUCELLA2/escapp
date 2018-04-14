package fr.brucella.form.escapp.consumer.impl.dao.site;

import fr.brucella.form.escapp.consumer.contract.dao.site.RouteDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.site.RouteRM;
import fr.brucella.form.escapp.model.beans.site.Route;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class RouteDaoImpl extends AbstractDao implements RouteDao {

	
    // TODO Complete methods
    // TODO Add try/catch for Exception
	
	@Override
	public Route getRoute(Integer pRouteId) throws TechnicalException {
		
		String vSQL = "SELECT * FROM route WHERE id = :id";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pRouteId);
		
		RowMapper<Route> vRowMapper = new RouteRM();
		
		return getNamedJdbcTemplate().queryForObject(vSQL, vParams, vRowMapper);
	}

	@Override
	public List<Route> getRoutesList(Integer pSectorId) throws TechnicalException {

		String vSQL = "SELECT * FROM route WHERE sector_id = :sectorId";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("sectorId", pSectorId);
		
		RowMapper<Route> vRowMapper = new RouteRM();
		
		return getNamedJdbcTemplate().query(vSQL, vParams, vRowMapper);
	}

	@Override
	public void updateRoute(Route pRoute) throws TechnicalException {
		
		String vSQL = "UPDATE route SET sector_id = :sectorId, name = :name, grade = :grade, points_nb = :pointsNb, description = :description WHERE id = :id";
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pRoute);
		
		getNamedJdbcTemplate().update(vSQL, vParams);		
	}

	@Override
	public void insertRoute(Route pRoute) throws TechnicalException {
		
		String vSQL = "INSERT INTO route (id, sector_id, name, grade, points_nb, description) VALUES (DEFAULT, :sectorId, :name, :grade, :pointsNb, :description";
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pRoute);
		
		getNamedJdbcTemplate().update(vSQL, vParams);		
	}

	@Override
	public void deleteRoute(Integer pRouteId) throws TechnicalException {

		String vSQL = "DELETE FROM route WHERE id = :id";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pRouteId);
		
		getNamedJdbcTemplate().update(vSQL, vParams);
	}
	
}
