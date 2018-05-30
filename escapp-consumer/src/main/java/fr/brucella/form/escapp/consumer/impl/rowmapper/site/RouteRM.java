package fr.brucella.form.escapp.consumer.impl.rowmapper.site;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.brucella.form.escapp.model.beans.site.Route;

/**
 * This class allow to map row of {@link java.sql.ResultSet} to an {@link Route} business object
 *
 * @author BRUCELLA2
 */
public class RouteRM implements RowMapper<Route> {
  
  /**
   * @see RowMapper#mapRow(ResultSet, int)
   */
  @Override
  public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
    
    Route vRoute = new Route();
    
    vRoute.setId(rs.getInt("id"));
    vRoute.setSectorId(rs.getInt("sector_id"));
    vRoute.setName(rs.getString("name"));
    vRoute.setGrade(rs.getString("grade"));
    vRoute.setPointsNb(rs.getInt("points_nb"));
    vRoute.setDescription(rs.getString("description"));
    
    return vRoute;
  }
  
}
