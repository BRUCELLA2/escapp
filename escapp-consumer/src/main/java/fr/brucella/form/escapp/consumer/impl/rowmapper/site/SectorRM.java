package fr.brucella.form.escapp.consumer.impl.rowmapper.site;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.brucella.form.escapp.model.beans.site.Sector;

/**
 * This class allow to map row of {@link java.sql.ResultSet} to an {@link Sector} business object.
 *
 * @author BRUCELLA2
 */
public class SectorRM implements RowMapper<Sector> {
  
  /**
   * @see RowMapper#mapRow(ResultSet, int)
   */
  @Override
  public Sector mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
    
    final Sector sector = new Sector();
    
    sector.setId(resultSet.getInt("id"));
    sector.setName(resultSet.getString("name"));
    sector.setDescription(resultSet.getString("description"));
    sector.setSiteId(resultSet.getInt("site_id"));
    
    return sector;
  }
  
}
