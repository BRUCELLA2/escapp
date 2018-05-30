package fr.brucella.form.escapp.consumer.impl.rowmapper.site;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.brucella.form.escapp.model.beans.site.Length;

/**
 * This class allow to map row of {@link java.sql.ResultSet} to an {@link Length} business object
 *
 * @author BRUCELLA2
 */
public class LengthRM implements RowMapper<Length> {
  
  /**
   * @see RowMapper#mapRow(ResultSet, int)
   */
  @Override
  public Length mapRow(ResultSet rs, int rowNum) throws SQLException {
    
    Length vLength = new Length();
    
    vLength.setId(rs.getInt("id"));
    vLength.setDescription(rs.getString("description"));
    vLength.setGrade(rs.getString("grade"));
    vLength.setLength(rs.getInt("length"));
    vLength.setPointsNb(rs.getInt("points_nb"));
    vLength.setRouteId(rs.getInt("route_id"));
    
    return vLength;
  }
  
}
