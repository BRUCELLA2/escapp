package fr.brucella.form.escapp.consumer.impl.dao.topo;

import fr.brucella.form.escapp.consumer.contract.dao.topo.TopoDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.topo.TopoRM;
import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class TopoDaoImpl extends AbstractDao implements TopoDao {
	
    // TODO Complete methods
    // TODO Add try/catch for Exception

	@Override
	public Topo getTopo(Integer pTopoId) throws TechnicalException {
		
		String vSQL = "SELECT * FROM topo WHERE id = :id";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pTopoId);
		
		RowMapper<Topo> vRowMapper = new TopoRM();
		
		return getNamedJdbcTemplate().queryForObject(vSQL, vParams, vRowMapper);
	}

	@Override
	public List<Topo> getAllToposList() throws TechnicalException {

		String vSQL = "SELECT * FROM topo";
		
		RowMapper<Topo> vRowMapper = new TopoRM();
		
		return getJdbcTemplate().query(vSQL, vRowMapper);
	}

	@Override
	public List<Topo> getOwnerToposList(Integer pOwnerId) throws TechnicalException {

		String vSQL = "SELECT * FROM topo WHERE owner_id = :ownerId";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("ownerId", pOwnerId);
		
		RowMapper<Topo> vRowMapper = new TopoRM();
		
		return getNamedJdbcTemplate().query(vSQL, vParams, vRowMapper);
	}

	@Override
	public List<Topo> getBorrowerToposList(Integer pBorrowerId) throws TechnicalException {
		
		String vSQL = "SELECT * FROM topo WHERE borrower_id = :borrowerId";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("borrowerId", pBorrowerId);
		
		RowMapper<Topo> vRowMapper = new TopoRM();
		
		return getNamedJdbcTemplate().query(vSQL, vParams, vRowMapper);
	}

	@Override
	public void updateTopo(Topo pTopo) throws TechnicalException {

		String vSQL = "UPDATE topo SET "
				+ "name = :name, department = :department, is_borrowable = :isBorrowable, pdf_file_name = :pdfFileName, municipality = :municipality, "
				+ "end_date_borrow = :endDateBorrow, borrower_id = :borrowerId, description = :description, owner_id = :ownerId WHERE id = :id";
		
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pTopo);
		
		getNamedJdbcTemplate().update(vSQL, vParams);		
	}

	@Override
	public void insertTopo(Topo pTopo) throws TechnicalException {

		String vSQL = "INSERT INTO topo (id, name, department, is_borrowable, pdf_file_name, municipality, end_date_borrow, borrower_id, description, owner_id) "
				+ "VALUES (DEFAULT, :name, :department, :isBorrowable, :pdfFileName, :municipality, :endDateBorrow, :borrowerId, :description, :ownerId";
		
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pTopo);
		
		getNamedJdbcTemplate().update(vSQL, vParams);
	}

	@Override
	public void deleteTopo(Integer pTopoId) throws TechnicalException {

		String vSQL = "DELETE FROM topo WHERE id = :id";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pTopoId);
		
		getNamedJdbcTemplate().update(vSQL, vParams);
	}
}
