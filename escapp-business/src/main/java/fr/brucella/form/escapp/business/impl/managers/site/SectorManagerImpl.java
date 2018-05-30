package fr.brucella.form.escapp.business.impl.managers.site;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.business.contract.managers.site.SectorManager;
import fr.brucella.form.escapp.business.impl.managers.AbstractManager;
import fr.brucella.form.escapp.model.beans.site.Sector;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;

/**
 * The Sector Manager
 *
 * @author BRUCELLA2
 */
@Component
public class SectorManagerImpl extends AbstractManager implements SectorManager {

    // ----- Logger
    private Log log = LogFactory.getLog(SectorManagerImpl.class);

    /**
     * @see SectorManager#getSectorsSiteList(Integer)
     */
    @Override
    public List<Sector> getSectorsSiteList(Integer pSiteId) throws TechnicalException, FunctionalException, NotFoundException {

        if (pSiteId == null) {
            throw new FunctionalException("L'identifiant du site est incorrect (Identifiant vide) - Echec de la recherche");
        }

        try {
            return this.getDaoFactory().getSectorDao().getSectorsList(pSiteId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }

    /**
     * @see SectorManager#getSectorById(Integer)
     */
    @Override
    public Sector getSectorById(Integer pSectorId) throws TechnicalException, FunctionalException, NotFoundException {

        if (pSectorId == null) {
            throw new FunctionalException("L'identifiant du secteur recherché est incorrect (Identifiant vide) - Echec de la recherche");
        }

        try {
            return this.getDaoFactory().getSectorDao().getSector(pSectorId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }

    /**
     * @see SectorManager#modifySector(Sector)
     */
    @Override
    public void modifySector(Sector pSector) throws TechnicalException, FunctionalException, NotFoundException {

        if (pSector == null) {
            throw new FunctionalException("Aucune modification n'a été transmise (Secteur vide) - Echec de la mise à jour");
        }

        Set<ConstraintViolation<Sector>> vViolations = this.getConstraintValidator().validate(pSector);

        if (!vViolations.isEmpty()) {
            for (ConstraintViolation<Sector> violation : vViolations) {
                this.log.debug(violation.getMessage());
            }
            throw new FunctionalException("Les modifications demandées ne sont pas valides", new ConstraintViolationException(vViolations));
        }

        try {
            this.getDaoFactory().getSectorDao().updateSector(pSector);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }

    /**
     * @see SectorManager#addSector(Sector)
     */
    @Override
    public void addSector(Sector pSector) throws TechnicalException, FunctionalException {

        if (pSector == null) {
            throw new FunctionalException("Aucun secteur n'a été transmis (Secteur vide) - Echec de l'ajout");
        }

        Set<ConstraintViolation<Sector>> vViolations = this.getConstraintValidator().validate(pSector);

        if (!vViolations.isEmpty()) {
            for (ConstraintViolation<Sector> violation : vViolations) {
                this.log.debug(violation.getMessage());
            }
            throw new FunctionalException("Le secteur à ajouter n'est pas valide", new ConstraintViolationException(vViolations));
        }

        try {
            int newSectorId = this.getDaoFactory().getSectorDao().insertSector(pSector);
            pSector.setId(newSectorId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        }
    }

    /**
     * @see SectorManager#deleteSector(Integer)
     */
    @Override
    public void deleteSector(Integer pSectorId) throws TechnicalException, FunctionalException, NotFoundException {

        if (pSectorId == null) {
            throw new FunctionalException("L'identifiant du sector à supprimer est incorrect (Identifiant null) - Echec de la suppression");
        }

        try {
            this.getDaoFactory().getSectorDao().deleteSector(pSectorId);
        } catch (TechnicalException pException) {
            throw new TechnicalException(pException.getMessage(), pException);
        } catch (NotFoundException pException) {
            throw new NotFoundException(pException.getMessage(), pException);
        }
    }

}
