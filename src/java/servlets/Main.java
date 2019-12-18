/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Studentyi;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 *
 * @author 17688
 */
public class Main {
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = sf.openSession();

        s.close();
        sf.close();
    }
}
