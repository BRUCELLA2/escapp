package fr.brucella.form.escapp.webapp.action;

import com.opensymphony.xwork2.ActionSupport;

import fr.brucella.form.escapp.business.contract.ManagerFactory;
import fr.brucella.form.escapp.consumer.impl.DaoFactoryImpl;
import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.beans.site.Site;
import fr.brucella.form.escapp.model.exceptions.FunctionalException;
import fr.brucella.form.escapp.model.exceptions.NotFoundException;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import fr.brucella.form.escapp.webapp.WebappHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestAction extends ActionSupport {

    @Autowired
    private NoteInjection noteInjection;

    @Autowired
    private DaoFactoryImpl daoFactoryImpl;
    
    @Autowired
    private ManagerFactory managerFactory;
    
    // --------- Elements en sortie
    private List<Site> listSite;

    
    // --------- Getters
    public List<Site> getListSite(){
    	return listSite;
    }
    
    public String doTest(){
        System.out.println("test");
        
        Site badSite = new Site();
        badSite.setName("");
        badSite.setDepartment("dsfze");
        badSite.setDescription("bad site");
        badSite.setMunicipality("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

        try {
			managerFactory.getSiteManager().modifySite(badSite);
		} catch (TechnicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FunctionalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			managerFactory.getSiteManager().deleteSite(7);
		} catch (TechnicalException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (FunctionalException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        
        Site newSite = new Site();
        newSite.setName("Site ajouté");
        newSite.setDepartment("034");
        newSite.setMunicipality("Montpellier");
        newSite.setDescription("Test ajout site");
        
        try {
			managerFactory.getSiteManager().addSite(newSite);
		} catch (TechnicalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FunctionalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        try {
        	listSite = managerFactory.getSiteManager().getAllSitesList();
			
			ListIterator<Site> it = listSite.listIterator();
			while(it.hasNext()) {
				System.out.println(it.next().toString());
			}
			
		} catch (TechnicalException e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		} catch (NotFoundException e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		} catch (FunctionalException e) {
			e.printStackTrace();
		}
        
        
        /*
        WebappHelper.getInjectAction().test();
        noteInjection.test();
        
        Comment comment = null;
		try {
			comment = daoFactoryImpl.getCommentDao().getComment(1);
	        System.out.println(comment.toString());
		} catch (NotFoundException pNotFoundException) {
			addActionError(pNotFoundException.getMessage());
		} catch (TechnicalException pTechnicalException) {
			addActionError(pTechnicalException.getMessage());
			pTechnicalException.printStackTrace();
			return ActionSupport.ERROR;
		}
        
        
        Comment updateComment = new Comment();
        updateComment.setId(1);
        updateComment.setTargetType("site");
        updateComment.setIdCommentTarget(1);
        updateComment.setEscappUser(1);
        updateComment.setText("First comment, target_type = site, id_comment_target = 1, user = user1");
        
        try {
			daoFactoryImpl.getCommentDao().updateComment(updateComment);
		} catch (TechnicalException | NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        /*
        Comment insertComment = new Comment();
        insertComment.setId(null);
        insertComment.setTargetType("site");
        insertComment.setIdCommentTarget(1);
        insertComment.setEscappUser(1);
        insertComment.setText("insert Comment test");
        
        try {
			daoFactoryImpl.getCommentDao().insertComment(insertComment);
		} catch (TechnicalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        try {
			daoFactoryImpl.getCommentDao().deleteComment(4);
		} catch (TechnicalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/ 
        
        /*
        List<Comment> comments = new ArrayList<>();
        try {
			comments = daoFactoryImpl.getCommentDao().getCommentsList("site", 0);

		} catch (NotFoundException pNotFoundException) {
			addActionError(pNotFoundException.getMessage());
	        ListIterator<Comment> it = comments.listIterator();
	        while(it.hasNext()) {
	        	Comment com = it.next();
	        	System.out.println(com.toString());
	        }
		} catch (TechnicalException pTechnicalException) {
			addActionError(pTechnicalException.getMessage());
			pTechnicalException.printStackTrace();
			return ActionSupport.ERROR;
		}
        */
        
        return ActionSupport.SUCCESS;
    }
}
