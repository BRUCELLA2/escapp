package fr.brucella.form.escapp.webapp.boostrap;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;


/**
 * Spring configuration class
 */
@Configuration
@ComponentScan("fr.brucella.form.escapp")
@ImportResource("classpath:/bootstrapContext.xml")
public class SpringConfiguration {
}
