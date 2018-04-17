package fr.brucella.form.escapp.consumer.impl.dao.topo;

import fr.brucella.form.escapp.consumer.contract.dao.topo.TopoDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.topo.TopoRM;
import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

/**
 * Topo Data Access Object
 * 
 * @author BRUCELLA2
 */
@Component
public class TopoDaoImpl extends AbstractDao implements TopoDao {
	
    // TODO Complete methods
	// TODO Add log system

	/**
	 * @see TopoDao#getTopo(Integer)
	 */
	@Override
	public Topo getTopo(Integer pTopoId) throws TechnicalException, NotFoundException {
		
		String vSQL = "SELECT * FROM topo WHERE id = :id";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pTopoId);
		
		RowMapper<Topo> vRowMapper = new TopoRM();
		
		try {
			
			return getNamedJdbcTemplate().queryForObject(vSQL, vParams, vRowMapper);
			
		} catch (EmptyResultDataAccessException pException) {
			pException.printStackTrace();
			throw new NotFoundException("Le topo demandé n'a pas été trouvé", pException);
		} catch (PermissionDeniedDataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		} catch (DataAccessResourceFailureException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
		} catch (DataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		} 
	}

	/**
	 * @see TopoDao#getAllToposList()
	 */
	@Override
	public List<Topo> getAllToposList() throws TechnicalException, NotFoundException {

		String vSQL = "SELECT * FROM topo";
		
		RowMapper<Topo> vRowMapper = new TopoRM();
		
		try {
			
			List<Topo> allToposList = getJdbcTemplate().query(vSQL, vRowMapper);
			if(allToposList.isEmpty()) {
				throw new NotFoundException("Aucun topo n'a été trouvé.");
			}else {
				return allToposList;
			}
			
		} catch (PermissionDeniedDataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		} catch (DataAccessResourceFailureException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
		} catch (DataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		}
	}

	/**
	 * @see TopoDao#getOwnerToposList(Integer)
	 */
	@Override
	public List<Topo> getOwnerToposList(Integer pOwnerId) throws TechnicalException, NotFoundException {

		String vSQL = "SELECT * FROM topo WHERE owner_id = :ownerId";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("ownerId", pOwnerId);
		
		RowMapper<Topo> vRowMapper = new TopoRM();
		
		try {
			
			List<Topo> ownerToposList = getNamedJdbcTemplate().query(vSQL, vParams, vRowMapper);
			if(ownerToposList.isEmpty()) {
				throw new NotFoundException("Aucun topo n'a été trouvé pour ce propriétaire.");
			}else {
				return ownerToposList;
			}
			
		} catch (PermissionDeniedDataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		} catch (DataAccessResourceFailureException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
		} catch (DataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		}
	}

	/**
	 * @see TopoDao#getBorrowerToposList(Integer)
	 */
	@Override
	public List<Topo> getBorrowerToposList(Integer pBorrowerId) throws TechnicalException, NotFoundException {
		
		String vSQL = "SELECT * FROM topo WHERE borrower_id = :borrowerId";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("borrowerId", pBorrowerId);
		
		RowMapper<Topo> vRowMapper = new TopoRM();
		
		try {
			
			List<Topo> borrowerToposList = getNamedJdbcTemplate().query(vSQL, vParams, vRowMapper);
			if(borrowerToposList.isEmpty()) {
				throw new NotFoundException("Aucun topo n'a été trouvé pour cet emprunteur.");
			}else {
				return borrowerToposList;
			}
			
		} catch (PermissionDeniedDataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		} catch (DataAccessResourceFailureException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
		} catch (DataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		}
	}

	/**
	 * @see TopoDao#updateTopo(Topo)
	 */
	@Override
	public void updateTopo(Topo pTopo) throws TechnicalException, NotFoundException {

		String vSQL = "UPDATE topo SET "
				+ "name = :name, department = :department, is_borrowable = :isBorrowable, pdf_file_name = :pdfFileName, municipality = :municipality, "
				+ "end_date_borrow = :endDateBorrow, borrower_id = :borrowerId, description = :description, owner_id = :ownerId WHERE id = :id";
		
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pTopo);
		
		try {
			int result = getNamedJdbcTemplate().update(vSQL, vParams);
			if(result == 0) {
				throw new NotFoundException("Le topo à modifier n'a pas été trouvé. La mise à jour n'a pas été faite.");
			}
			
		} catch (DataIntegrityViolationException pException) {
			pException.printStackTrace();
			throw new TechnicalException("Les données n'étant pas conformes, la mise à jour du topo n'a pu être réalisée.", pException);
    	} catch (PermissionDeniedDataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		} catch (DataAccessResourceFailureException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
		} catch (DataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		}		
	}

	/**
	 * @see TopoDao#insertTopo(Topo)
	 */
	@Override
	public void insertTopo(Topo pTopo) throws TechnicalException {

		String vSQL = "INSERT INTO topo (id, name, department, is_borrowable, pdf_file_name, municipality, end_date_borrow, borrower_id, description, owner_id) "
				+ "VALUES (DEFAULT, :name, :department, :isBorrowable, :pdfFileName, :municipality, :endDateBorrow, :borrowerId, :description, :ownerId";
		
		SqlParameterSource vParams = new BeanPropertySqlParameterSource(pTopo);
		
		try {
			
			getNamedJdbcTemplate().update(vSQL, vParams);
			
		} catch (DuplicateKeyException pException) {
			pException.printStackTrace();
			throw new TechnicalException("Un topo existe déjà avec cet identifiant", pException);
		} catch (DataIntegrityViolationException pException) {
			pException.printStackTrace();
			throw new TechnicalException("Les données n'étant pas conformes, la création du topo n'a pu être réalisée", pException);	
    	} catch (PermissionDeniedDataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		} catch (DataAccessResourceFailureException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
		} catch (DataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		}
	}

	/**
	 * @see TopoDao#deleteTopo(Integer)
	 */
	@Override
	public void deleteTopo(Integer pTopoId) throws TechnicalException, NotFoundException {

		String vSQL = "DELETE FROM topo WHERE id = :id";
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pTopoId);
		
		try {
			
			int result = getNamedJdbcTemplate().update(vSQL, vParams);
			if(result == 0) {
				throw new NotFoundException("Le topo à supprimer n'a pas été trouvé. La suppression n'a pas été réalisée.");
			}
			
    	} catch (PermissionDeniedDataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(PERMISSION_DENIED_DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		} catch (DataAccessResourceFailureException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION, pException);
		} catch (DataAccessException pException) {
			pException.printStackTrace();
			throw new TechnicalException(DATA_ACCESS_EXCEPTION_MESSAGE, pException);
		}
	}
}
