package fr.brucella.form.escapp.consumer.impl.dao.site;

import fr.brucella.form.escapp.consumer.contract.dao.site.SiteDao;
import fr.brucella.form.escapp.consumer.impl.dao.AbstractDao;
import org.springframework.stereotype.Component;

@Component
public class SiteDaoImpl extends AbstractDao implements SiteDao {

    public SiteDaoImpl(){

            System.out.println("testDao CONST");
    }

    public void test(){
        System.out.println("testDao");
    }
}
