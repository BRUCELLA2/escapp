package fr.brucella.form.escapp.consumer.impl.rowmapper.site;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.brucella.form.escapp.model.beans.site.Sector;

/**
 * This class allow to map row of {@link java.sql.ResultSet} to an {@link Sector} business object
 *
 * @author BRUCELLA2
 */
public class SectorRM implements RowMapper<Sector> {
    
    /**
     * @see RowMapper#mapRow(ResultSet, int)
     */
    @Override
    public Sector mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Sector vSector = new Sector();
        
        vSector.setId(rs.getInt("id"));
        vSector.setName(rs.getString("name"));
        vSector.setDescription(rs.getString("description"));
        vSector.setSiteId(rs.getInt("site_id"));
        
        return vSector;
    }
    
}
