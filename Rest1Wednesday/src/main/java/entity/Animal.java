/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Sebastian
 */
@Entity
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;
    private String type;
    private String sound;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Animal() {
    }

    public Animal(String type, String sound) {
        this.type = type;
        this.sound = sound;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Animal{" + "type=" + type + ", sound=" + sound + '}';
    }

}
