package fr.brucella.form.escapp.consumer.impl.dao.topo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.consumer.contract.dao.topo.TopoDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import fr.brucella.form.escapp.consumer.impl.rowmapper.topo.TopoRM;
import fr.brucella.form.escapp.model.beans.topo.Topo;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import fr.brucella.form.escapp.model.search.TopoSearch;

/**
 * Topo Data Access Object.
 *
 * @author BRUCELLA2
 */
@Component
public class TopoDaoImpl extends AbstractDao implements TopoDao {
  
  /**
   * Topo DAO logger.
   */
  private static final Log LOG = LogFactory.getLog(TopoDaoImpl.class);
  
  /**
   * @see TopoDao#getTopo(Integer)
   */
  @Override
  public Topo getTopo(final Integer topoId) throws TechnicalException, NotFoundException {
    
    final String sql = "SELECT * FROM topo WHERE id = :id";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("id", topoId);
    
    final RowMapper<Topo> rowMapper = new TopoRM();
    
    try {
      
      return this.getNamedJdbcTemplate().queryForObject(sql, params, rowMapper);
      
    } catch (EmptyResultDataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new NotFoundException("Le topo demandé n'a pas été trouvé", pException);
    } catch (PermissionDeniedDataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(PERMISSION_DENIED, pException);
    } catch (DataAccessResourceFailureException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, pException);
    } catch (DataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, pException);
    }
  }
  
  /**
   * @see TopoDao#getAllToposList()
   */
  @Override
  public List<Topo> getAllToposList() throws TechnicalException, NotFoundException {
    
    final String sql = "SELECT * FROM topo";
    
    final RowMapper<Topo> rowMapper = new TopoRM();
    
    try {
      
      final List<Topo> allToposList = this.getJdbcTemplate().query(sql, rowMapper);
      if (allToposList.isEmpty()) {
        throw new NotFoundException("Aucun topo n'a été trouvé.");
      }
      else {
        return allToposList;
      }
      
    } catch (PermissionDeniedDataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(PERMISSION_DENIED, pException);
    } catch (DataAccessResourceFailureException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, pException);
    } catch (DataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, pException);
    }
  }
  
  /**
   * @see TopoDao#getSearchTopoList(TopoSearch)
   */
  @Override
  public List<Topo> getSearchToposList(final TopoSearch topoSearch) throws TechnicalException, NotFoundException {
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    
    final StringBuilder sql = new StringBuilder(100);
    sql.append("SELECT * FROM topo WHERE 1 = 1 ");
    
    if (topoSearch != null) {
      if (!StringUtils.isEmpty(topoSearch.getDepartmentTopo())) {
        sql.append(" AND UPPER(department) = UPPER(:departmentTopo) ");
        params.addValue("departmentTopo", topoSearch.getDepartmentTopo());
      }
      if (!StringUtils.isEmpty(topoSearch.getMunicipalityTopo())) {
        sql.append(" AND UPPER(municipality) = UPPER(:municipalityTopo) ");
        params.addValue("municipalityTopo", topoSearch.getMunicipalityTopo());
      }
      if (topoSearch.getAvailableTopo()) {
        sql.append(" AND is_borrowable = true AND (end_date_borrow is null or end_date_borrow < CURRENT_TIMESTAMP)");
      }
    }
    
    final RowMapper<Topo> rowMapper = new TopoRM();
    
    try {
      return this.getNamedJdbcTemplate().query(sql.toString(), params, rowMapper);
    } catch (EmptyResultDataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new NotFoundException("Aucun topo ne correspond à votre recherche", pException);
    } catch (PermissionDeniedDataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(PERMISSION_DENIED, pException);
    } catch (DataAccessResourceFailureException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, pException);
    } catch (DataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, pException);
    }
  }
  
  /**
   * @see TopoDao#getOwnerToposList(Integer)
   */
  @Override
  public List<Topo> getOwnerToposList(final Integer ownerId) throws TechnicalException, NotFoundException {
    
    final String sql = "SELECT * FROM topo WHERE owner_id = :ownerId";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("ownerId", ownerId);
    
    final RowMapper<Topo> rowMapper = new TopoRM();
    
    try {
      
      final List<Topo> ownerToposList = this.getNamedJdbcTemplate().query(sql, params, rowMapper);
      if (ownerToposList.isEmpty()) {
        throw new NotFoundException("Aucun topo n'a été trouvé pour ce propriétaire.");
      }
      else {
        return ownerToposList;
      }
      
    } catch (PermissionDeniedDataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(PERMISSION_DENIED, pException);
    } catch (DataAccessResourceFailureException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, pException);
    } catch (DataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, pException);
    }
  }
  
  /**
   * @see TopoDao#getBorrowerToposList(Integer)
   */
  @Override
  public List<Topo> getBorrowerToposList(final Integer borrowerId) throws TechnicalException, NotFoundException {
    
    final String sql = "SELECT * FROM topo WHERE borrower_id = :borrowerId";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("borrowerId", borrowerId);
    
    final RowMapper<Topo> rowMapper = new TopoRM();
    
    try {
      
      final List<Topo> borrowerToposList = this.getNamedJdbcTemplate().query(sql, params, rowMapper);
      if (borrowerToposList.isEmpty()) {
        throw new NotFoundException("Aucun topo n'a été trouvé pour cet emprunteur.");
      }
      else {
        return borrowerToposList;
      }
      
    } catch (PermissionDeniedDataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(PERMISSION_DENIED, pException);
    } catch (DataAccessResourceFailureException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, pException);
    } catch (DataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, pException);
    }
  }
  
  /**
   * @see TopoDao#updateTopo(Topo)
   */
  @Override
  public void updateTopo(final Topo topo) throws TechnicalException, NotFoundException {
    
    final String sql = "UPDATE topo SET "
        + "name = :name, department = :department, is_borrowable = :isBorrowable, pdf_file_name = :pdfFileName, municipality = :municipality, "
        + "end_date_borrow = :endDateBorrow, borrower_id = :borrowerId, description = :description, owner_id = :ownerId WHERE id = :id";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("name", topo.getName());
    params.addValue("department", topo.getDepartment());
    params.addValue("isBorrowable", topo.isIsBorrowable());
    params.addValue("pdfFileName", topo.getPdfFileName());
    params.addValue("municipality", topo.getMunicipality());
    params.addValue("endDateBorrow", topo.getEndDateBorrow());
    params.addValue("borrowerId", topo.getBorrower());
    params.addValue("description", topo.getDescription());
    params.addValue("ownerId", topo.getOwner());
    params.addValue("id", topo.getId());
    
    try {
      final int result = this.getNamedJdbcTemplate().update(sql, params);
      if (result == 0) {
        throw new NotFoundException("Le topo à modifier n'a pas été trouvé. La mise à jour n'a pas été faite.");
      }
      
    } catch (DataIntegrityViolationException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException("Les données n'étant pas conformes, la mise à jour du topo n'a pu être réalisée.", pException);
    } catch (PermissionDeniedDataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(PERMISSION_DENIED, pException);
    } catch (DataAccessResourceFailureException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, pException);
    } catch (DataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, pException);
    }
  }
  
  /**
   * @see TopoDao#insertTopo(Topo)
   */
  @Override
  public int insertTopo(final Topo topo) throws TechnicalException {
    
    final String sql =
        "INSERT INTO topo (id, name, department, is_borrowable, pdf_file_name, municipality, end_date_borrow, borrower_id, description, owner_id) "
            + "VALUES (DEFAULT, :name, :department, :isBorrowable, :pdfFileName, :municipality, :endDateBorrow, :borrowerId, :description, :ownerId)";
    
    final KeyHolder keyHolder = new GeneratedKeyHolder();
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("name", topo.getName());
    params.addValue("department", topo.getDepartment());
    params.addValue("isBorrowable", topo.isIsBorrowable());
    params.addValue("pdfFileName", topo.getPdfFileName());
    params.addValue("municipality", topo.getMunicipality());
    params.addValue("endDateBorrow", topo.getEndDateBorrow());
    params.addValue("borrowerId", topo.getBorrower());
    params.addValue("description", topo.getDescription());
    params.addValue("ownerId", topo.getOwner());
    
    try {
      
      this.getNamedJdbcTemplate().update(sql, params, keyHolder, new String[] {"id"});
      return keyHolder.getKey().intValue();
      
    } catch (DuplicateKeyException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException("Un topo existe déjà avec cet identifiant", pException);
    } catch (DataIntegrityViolationException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException("Les données n'étant pas conformes, la création du topo n'a pu être réalisée", pException);
    } catch (PermissionDeniedDataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(PERMISSION_DENIED, pException);
    } catch (DataAccessResourceFailureException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, pException);
    } catch (DataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, pException);
    }
  }
  
  /**
   * @see TopoDao#deleteTopo(Integer)
   */
  @Override
  public void deleteTopo(final Integer topoId) throws TechnicalException, NotFoundException {
    
    final String sql = "DELETE FROM topo WHERE id = :id";
    
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("id", topoId);
    
    try {
      
      final int result = this.getNamedJdbcTemplate().update(sql, params);
      if (result == 0) {
        throw new NotFoundException("Le topo à supprimer n'a pas été trouvé. La suppression n'a pas été réalisée.");
      }
      
    } catch (PermissionDeniedDataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(PERMISSION_DENIED, pException);
    } catch (DataAccessResourceFailureException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_RESOURCE_FAILURE, pException);
    } catch (DataAccessException pException) {
      LOG.debug(pException.getStackTrace());
      throw new TechnicalException(DATA_ACCESS_EXCEPTION, pException);
    }
  }
}
