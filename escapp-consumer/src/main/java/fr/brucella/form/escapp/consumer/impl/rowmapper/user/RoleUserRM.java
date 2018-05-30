package fr.brucella.form.escapp.consumer.impl.rowmapper.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.brucella.form.escapp.model.beans.user.RoleUser;

/**
 * This class allow to map row of {@link java.sql.ResultSet} to an {@link RoleUser} business object
 *
 * @author BRUCELLA2
 */
public class RoleUserRM implements RowMapper<RoleUser> {

    /**
     * @see RowMapper#mapRow(ResultSet, int)
     */
    @Override
    public RoleUser mapRow(ResultSet rs, int rowNum) throws SQLException {

        RoleUser vRoleUser = new RoleUser();

        vRoleUser.setUserId(rs.getObject("escapp_user", Integer.class));
        vRoleUser.setUserRole(rs.getString("role"));

        return vRoleUser;
    }
}
