package fr.brucella.form.escapp.consumer.impl.rowmapper.site;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.brucella.form.escapp.model.beans.site.Length;

/**
 * This class allow to map row of {@link java.sql.ResultSet} to an {@link Length} business object.
 *
 * @author BRUCELLA2
 */
public class LengthRM implements RowMapper<Length> {
  
  /**
   * @see RowMapper#mapRow(ResultSet, int)
   */
  @Override
  public Length mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
    
    Length length = new Length();
    
    length.setId(resultSet.getInt("id"));
    length.setDescription(resultSet.getString("description"));
    length.setGrade(resultSet.getString("grade"));
    length.setLength(resultSet.getInt("length"));
    length.setPointsNb(resultSet.getInt("points_nb"));
    length.setRouteId(resultSet.getInt("route_id"));
    
    return length;
  }
  
}
