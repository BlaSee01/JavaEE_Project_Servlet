/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.ejb;

import java.io.Serializable;
import java.sql.Connection;
import javax.ejb.Remote;

/**
 *
 * @author student
 */

@Remote
public interface MP3Remote {

    void set(String t);

    String print();

    String print_art();

    int print_id();
    
    void set_art(String a);

    void set_id(int i);

    void set_file(String f);

    String print_file();
    
}
