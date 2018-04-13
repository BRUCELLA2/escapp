package fr.brucella.form.escapp.consumer.impl.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public abstract class AbstractDao {

    @Autowired
    private JdbcTemplate vJdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate vNamedJdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return vJdbcTemplate;
    }

    public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
        return vNamedJdbcTemplate;
    }
}
