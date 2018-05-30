package fr.brucella.form.escapp.consumer.impl.rowmapper.site;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.brucella.form.escapp.model.beans.site.Site;

/**
 * This class allow to map row of {@link java.sql.ResultSet} to an {@link Site} business object.
 *
 * @author BRUCELLA2
 */
public class SiteRM implements RowMapper<Site> {
  
  /**
   * @see RowMapper#mapRow(ResultSet, int)
   */
  @Override
  public Site mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
    
    final Site site = new Site();
    
    site.setId(resultSet.getInt("id"));
    site.setName(resultSet.getString("name"));
    site.setDepartment(resultSet.getString("department"));
    site.setMunicipality(resultSet.getString("municipality"));
    site.setDescription(resultSet.getString("description"));
    
    return site;
  }
  
  
}
