/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.ejb;

import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author student
 */
@Stateless
public class MP3 implements MP3Remote {

    private String title;
    private String artist;
    private int id;
    private String file;
    
    @Override
    public void set(String t) {
        title = t;
    }

    @Override
    public String print() {
        return title;
    }

    @Override
    public String print_art() {
        return artist;
    }
    

    @Override
    public int print_id() {
        return id;
    }
 @Override
    public void set_art(String a) {
        artist = a;
    }

    @Override
    public void set_id(int i) {
        id = i;
    }

    @Override
    public void set_file(String f) {
        file = f;
    }

    @Override
    public String print_file() {
        return file;
    }



   
    
}
