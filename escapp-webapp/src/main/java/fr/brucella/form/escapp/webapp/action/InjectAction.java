package fr.brucella.form.escapp.webapp.action;

import org.springframework.stereotype.Component;

@Component
public class InjectAction {

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    private String test;

    public void test(){
        System.out.println("Test inject");
    }
}
