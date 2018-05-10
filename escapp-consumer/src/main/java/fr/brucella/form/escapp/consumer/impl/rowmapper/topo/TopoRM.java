package fr.brucella.form.escapp.consumer.impl.rowmapper.topo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.brucella.form.escapp.model.beans.topo.Topo;

/**
 * This class allow to map row of {@link java.sql.ResultSet} to an {@link Topo} business object
 * 
 * @author BRUCELLA2
 */
public class TopoRM implements RowMapper<Topo>{

	/**
	 * @see RowMapper#mapRow(ResultSet, int)
	 */
	@Override
	public Topo mapRow(ResultSet rs, int rowNum) throws SQLException {

		Topo vTopo = new Topo();
		
		vTopo.setId(rs.getInt("id"));
		vTopo.setName(rs.getString("name"));
		vTopo.setDepartment(rs.getString("department"));
		vTopo.setBorrowable(rs.getBoolean("is_borrowable"));
		vTopo.setPdfFileName(rs.getString("pdf_file_name"));
		vTopo.setMunicipality(rs.getString("municipality"));
		if(rs.getTimestamp("end_date_borrow") != null) {
			vTopo.setEndDateBorrow(rs.getTimestamp("end_date_borrow").toLocalDateTime());
		}
		else {
			vTopo.setEndDateBorrow(null);
		}
		vTopo.setBorrower(rs.getInt("borrower_id"));
		vTopo.setDescription(rs.getString("description"));
		vTopo.setOwner(rs.getInt("owner_id"));
		
		return vTopo;
	}

}
