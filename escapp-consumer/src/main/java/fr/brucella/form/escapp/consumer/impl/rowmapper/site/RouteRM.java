package fr.brucella.form.escapp.consumer.impl.rowmapper.site;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.brucella.form.escapp.model.beans.site.Route;

/**
 * This class allow to map row of {@link java.sql.ResultSet} to an {@link Route} business object.
 *
 * @author BRUCELLA2
 */
public class RouteRM implements RowMapper<Route> {
  
  /**
   * @see RowMapper#mapRow(ResultSet, int)
   */
  @Override
  public Route mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
    
    final Route route = new Route();
    
    route.setId(resultSet.getInt("id"));
    route.setSectorId(resultSet.getInt("sector_id"));
    route.setName(resultSet.getString("name"));
    route.setGrade(resultSet.getString("grade"));
    route.setPointsNb(resultSet.getInt("points_nb"));
    route.setDescription(resultSet.getString("description"));
    
    return route;
  }
  
}
