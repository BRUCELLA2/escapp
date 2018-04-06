package fr.brucella.form.escapp.consumer.impl.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;

public abstract class AbstractDao {

    @Autowired
    @Qualifier("dataSourceEscapp")
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }
}
