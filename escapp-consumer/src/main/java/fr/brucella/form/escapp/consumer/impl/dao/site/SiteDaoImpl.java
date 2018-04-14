package fr.brucella.form.escapp.consumer.impl.dao.site;

import fr.brucella.form.escapp.consumer.contract.dao.site.SiteDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.site.SiteRM;
import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class SiteDaoImpl extends AbstractDao implements SiteDao {
	
    // TODO Complete methods
    // TODO Add try/catch for Exception

	@Override
	public Site getSite(Integer pSiteId) throws TechnicalException {
		
		String vSQL = "SELECT * FROM site WHERE id = :id";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pSiteId);
		
		RowMapper<Site> vRowMapper = new SiteRM();
		
		return getNamedJdbcTemplate().queryForObject(vSQL, vParams, vRowMapper);
	}

	@Override
	public List<Site> getAllSitesList() throws TechnicalException {

		String vSQL = "SELECT * FROM site";
		
		RowMapper<Site> vRowMapper = new SiteRM();
		
		return getJdbcTemplate().query(vSQL, vRowMapper);
	}

	@Override
	public void updateSite(Site pSite) throws TechnicalException {

		String vSQL = "UPDATE site SET name = :name, department = :department, municipality = :municipality, description = :description WHERE id = :id";
		
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pSite);
		
		getNamedJdbcTemplate().update(vSQL, vParams);
	}

	@Override
	public void insertSite(Site pSite) throws TechnicalException {

		String vSQL = "INSERT INTO site (id, name, department, municipality, description) VALUES (DEFAULT, :name, :department, :municipality, :description";
		
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pSite);
		
		getNamedJdbcTemplate().update(vSQL, vParams);
	}

	@Override
	public void deleteSite(Integer pSiteId) throws TechnicalException {

		String vSQL = "DELETE FROM site WHERE id = :id";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pSiteId);
		
		getNamedJdbcTemplate().update(vSQL, vParams);	
	}

}
