/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Gruppyi;
import entity.Studentyi;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlets.NewHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author 17688
 */
public class Hello extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String enteredValue;
        enteredValue = request.getParameter("enteredValue");
        PrintWriter printWriter;
        
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        Query q = s.createQuery("from Gruppyi g");
        List<Gruppyi> gruppyi = q.list();
        
        
        try  {
            out.println("<!DOCTYPE html> <html> <head>");
            out.println("<title>Привет</title>");            
            out.println("</head>");
            out.println("<body>");
            printWriter = response.getWriter();
            printWriter.print("<p> Вы ввели: ");
            printWriter.print(enteredValue);
            printWriter.print("</p>");
            printWriter.print("<p> Students: ");          
            printWriter.println("<br/>");
            if(gruppyi != null){
                for (Gruppyi group : gruppyi)
                {
                    List<Studentyi> students = group.getStudentyis();
                    for(Studentyi student : students){
                    printWriter.print(student.getFamiliya()+" "+student.getImya()+" "+student.getOtchestvo() + " " + student.getGruppyi().getNazvanie());
                    printWriter.println("<br/>");
                    }
                }
                printWriter.print("<p> Информация о группе (name and count of students) ");
                printWriter.println("<br/>");
                for (Gruppyi group: gruppyi){
                    printWriter.print(group.getNazvanie()+" "+ group.getStudentyis().size());
                    printWriter.println("<br/>");
                }
                printWriter.println("</p>");
                printWriter.print("<p> Info about It");
                for (Gruppyi group: gruppyi){
                    if (group.getNazvanie().startsWith("It")){
                        printWriter.println("<br/>");
                        printWriter.print(group.getNazvanie());
                    }
                }
                if(enteredValue!=null){
                    for (Gruppyi group: gruppyi){
                    if (group.getNazvanie().equals(enteredValue)){
                        gruppyi.add(gruppyi.size()-1, group);
                        printWriter.println("<br/>");
                        printWriter.print("Group " + group.getNazvanie() + " removed");
                        gruppyi.remove(group);
                        printWriter.println("<br/>");
                    }
                    }
                }
                for (Gruppyi group : gruppyi)
                {
                    List<Studentyi> students = group.getStudentyis();
                    for(Studentyi student : students){
                    printWriter.print(student.getFamiliya()+" "+student.getImya()+" "+student.getOtchestvo() + " " + student.getGruppyi().getNazvanie());
                    printWriter.println("<br/>");
                    }
                }
                printWriter.print("<p> Info about groups (name and count of students) ");
                printWriter.println("<br/>");
                for (Gruppyi group: gruppyi){
                    printWriter.print(group.getNazvanie()+" "+ group.getStudentyis().size());
                    printWriter.println("<br/>");
                }
                printWriter.println("</p>");
                printWriter.print("<p> Info about It");
                for (Gruppyi group: gruppyi){
                    if (group.getNazvanie().startsWith("It")){
                        printWriter.println("<br/>");
                        printWriter.print(group.getNazvanie());
                    }
                }
                
                printWriter.println("</p>");
                
            }
            printWriter.println("</p>");
            //out.println("<h1>Servlet Hello at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
            s.close();
            sf.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
