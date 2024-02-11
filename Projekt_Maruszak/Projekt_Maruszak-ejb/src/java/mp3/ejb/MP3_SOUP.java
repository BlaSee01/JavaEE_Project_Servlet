/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.ejb;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author student
 */
@WebService(serviceName = "MP3_SOUP")
@Stateless()
public class MP3_SOUP {

    @EJB
    private MP3Remote ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "set")
    @Oneway
    public void set(@WebParam(name = "t") String t) { 
       ejbRef.set(t);
    }

    @WebMethod(operationName = "print")
    public String print() {
        return ejbRef.print();
    }

    @WebMethod(operationName = "print_art")
    public String print_art() {
        //TODO write your implementation code here:
        return ejbRef.print_art();
    }

    @WebMethod(operationName = "print_id")
    public Integer print_id() {
        //TODO write your implementation code here:
        return ejbRef.print_id();
    }

    @WebMethod(operationName = "set_art")
    @Oneway
    public void set_art(@WebParam(name = "a") String a) {
        ejbRef.set_art(a);
    }

    @WebMethod(operationName = "set_id")
    @Oneway
    public void set_id(@WebParam(name = "i") Integer i) {
        ejbRef.set_id(i);
    }

    @WebMethod(operationName = "set_file")
    @Oneway
    public void set_file(@WebParam(name = "f") String f) {
        ejbRef.set_file(f);
    }
    
   @WebMethod(operationName = "print_file")
    public String print_file() {
        return ejbRef.print_file();
    }
}