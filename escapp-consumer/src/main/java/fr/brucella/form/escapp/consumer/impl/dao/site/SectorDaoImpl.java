package fr.brucella.form.escapp.consumer.impl.dao.site;

import fr.brucella.form.escapp.consumer.contract.dao.site.SectorDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.site.SectorRM;
import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class SectorDaoImpl extends AbstractDao implements SectorDao {

    // TODO Complete methods
    // TODO Add try/catch for Exception
	
	@Override
	public Sector getSector(Integer pSectorId) throws TechnicalException {
		
		String vSQL = "SELECT * FROM sector WHERE id = :id";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pSectorId);
		
		RowMapper<Sector> vRowMapper = new SectorRM();
		
		return getNamedJdbcTemplate().queryForObject(vSQL, vParams, vRowMapper);
	}

	@Override
	public List<Sector> getSectorsList(Integer pSiteId) throws TechnicalException {
		
		String vSQL = "SELECT * FROM sector WHERE site_id = :siteId";
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("siteId", pSiteId);
		
		RowMapper<Sector> vRowMapper = new SectorRM();
		
		return getNamedJdbcTemplate().query(vSQL, vParams, vRowMapper);
	}

	@Override
	public void updateSector(Sector pSector) throws TechnicalException {
		
		String vSQL = "UPDATE sector SET name = :name, description = :description, site_id = :siteId WHERE id = :id";
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pSector);
		
		getNamedJdbcTemplate().update(vSQL, vParams);
	}

	@Override
	public void insertSector(Sector pSector) throws TechnicalException {

		String vSQL = "INSERT INTO sector (id, name, description, site_id) VALUES (DEFAULT, :name, :description, :siteId)";
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pSector);

		getNamedJdbcTemplate().update(vSQL, vParams);
	}

	@Override
	public void deleteSector(Integer pSectorId) throws TechnicalException {

		String vSQL = "DELETE FROM sector WHERE id = :id";
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pSectorId);
		
		getNamedJdbcTemplate().update(vSQL, vParams);
	}
}
