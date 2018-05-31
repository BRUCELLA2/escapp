package fr.brucella.form.escapp.consumer.impl.rowmapper.topo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.brucella.form.escapp.model.beans.topo.Topo;

/**
 * This class allow to map row of {@link java.sql.ResultSet} to an {@link Topo} business object.
 *
 * @author BRUCELLA2
 */
public class TopoRM implements RowMapper<Topo> {
  
  /**
   * @see RowMapper#mapRow(ResultSet, int)
   */
  @Override
  public Topo mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
    
    final Topo topo = new Topo();
    
    topo.setId(resultSet.getInt("id"));
    topo.setName(resultSet.getString("name"));
    topo.setDepartment(resultSet.getString("department"));
    topo.setBorrowable(resultSet.getBoolean("is_borrowable"));
    topo.setPdfFileName(resultSet.getString("pdf_file_name"));
    topo.setMunicipality(resultSet.getString("municipality"));
    if (resultSet.getTimestamp("end_date_borrow") == null) {
      topo.setEndDateBorrow(null);
    }
    else {
      topo.setEndDateBorrow(resultSet.getTimestamp("end_date_borrow").toLocalDateTime());
    }
    topo.setBorrower(resultSet.getObject("borrower_id", Integer.class));
    topo.setDescription(resultSet.getString("description"));
    topo.setOwner(resultSet.getInt("owner_id"));
    
    return topo;
  }
  
}
