package fr.brucella.form.escapp.business.impl.managers;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.consumer.contract.DaoFactory;

@Component
public class AbstractManager {

	/**
	 * The Data Access Object Factory
	 */
	@Autowired
	private DaoFactory vDaoFactory;
	
	/**
	 * Get the {@link DaoFactory}
	 * 
	 * @return the {@link DaoFactory}
	 */
	public DaoFactory getDaoFactory() {
		return vDaoFactory;
	}
	
	/**
	 * Get a constraint {@link Validator}. 
	 * 
	 * @return a constraint {@link Validator}.
	 */
	protected Validator getConstraintValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator vValidator = factory.getValidator();
		return vValidator;
	}
}
