package fr.brucella.form.escapp.consumer.impl.rowmapper.site;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.brucella.form.escapp.model.beans.site.Site;

public class SiteRM implements RowMapper<Site>{

	@Override
	public Site mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Site vSite = new Site();
		
		vSite.setId(rs.getInt("id"));
		vSite.setName(rs.getString("name"));
		vSite.setDepartment(rs.getString("department"));
		vSite.setMunicipality(rs.getString("municipality"));
		vSite.setDescription(rs.getString("description"));
		
		return vSite;
	}

	
}