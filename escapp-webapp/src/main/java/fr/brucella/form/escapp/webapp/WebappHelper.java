package fr.brucella.form.escapp.webapp;

import fr.brucella.form.escapp.webapp.action.InjectAction;

public abstract class WebappHelper {

    private static InjectAction injectAction;

    public static InjectAction getInjectAction(){
        return injectAction;
    }

    public static void setInjectAction(InjectAction pInjectAction){
        injectAction = pInjectAction;
    }
}
