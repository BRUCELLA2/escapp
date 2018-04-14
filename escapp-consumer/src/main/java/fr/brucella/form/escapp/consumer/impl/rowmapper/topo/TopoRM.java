package fr.brucella.form.escapp.consumer.impl.rowmapper.topo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.brucella.form.escapp.model.beans.topo.Topo;

public class TopoRM implements RowMapper<Topo>{

	@Override
	public Topo mapRow(ResultSet rs, int rowNum) throws SQLException {

		Topo vTopo = new Topo();
		
		vTopo.setId(rs.getInt("id"));
		vTopo.setName(rs.getString("name"));
		vTopo.setDepartment(rs.getString("department"));
		vTopo.setBorrowable(rs.getBoolean("is_borrowable"));
		vTopo.setPdfFileName(rs.getString("pdf_file_name"));
		vTopo.setMunicipality(rs.getString("municipality"));
		vTopo.setEndDateBorrow(rs.getDate("end_date_borrow"));
		vTopo.setBorrower(rs.getInt("borrower_id"));
		vTopo.setDescription(rs.getString("description"));
		vTopo.setOwner(rs.getInt("owner_id"));
		
		return vTopo;
	}

}
