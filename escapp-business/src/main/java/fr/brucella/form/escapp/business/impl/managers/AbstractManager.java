package fr.brucella.form.escapp.business.impl.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.brucella.form.escapp.consumer.contract.DaoFactory;

@Component
public class AbstractManager {

	@Autowired
	private DaoFactory vDaoFactory;
	
	public DaoFactory getDaoFactory() {
		return vDaoFactory;
	}
}
