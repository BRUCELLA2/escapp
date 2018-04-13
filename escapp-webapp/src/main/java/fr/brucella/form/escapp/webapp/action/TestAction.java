package fr.brucella.form.escapp.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.brucella.form.escapp.consumer.impl.DaoFactoryImpl;
import fr.brucella.form.escapp.consumer.impl.dao.comment.CommentDaoImpl;
import fr.brucella.form.escapp.model.beans.comment.Comment;
import fr.brucella.form.escapp.model.exceptions.TechnicalException;
import fr.brucella.form.escapp.webapp.WebappHelper;

import java.util.ArrayList;
import java.util.Iterator;
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

    public String doTest(){
        System.out.println("test");

        WebappHelper.getInjectAction().test();
        noteInjection.test();
        
        Comment comment = null;
		try {
			comment = daoFactoryImpl.getCommentDao().getComment(1);
		} catch (TechnicalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        System.out.println(comment.toString());
        
        Comment updateComment = new Comment();
        updateComment.setId(1);
        updateComment.setTargetType("site");
        updateComment.setIdCommentTarget(1);
        updateComment.setEscappUser(1);
        updateComment.setText("First comment, target_type = site, id_comment_target = 1, user = user1");
        
        try {
			daoFactoryImpl.getCommentDao().updateComment(updateComment);
		} catch (TechnicalException e) {
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
        
        List<Comment> comments = new ArrayList<>();
        try {
			comments = daoFactoryImpl.getCommentDao().getCommentsList("site", 1);
		} catch (TechnicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ListIterator<Comment> it = comments.listIterator();
        while(it.hasNext()) {
        	Comment com = it.next();
        	System.out.println(com.toString());
        }
        
        
        return ActionSupport.SUCCESS;
    }
}
