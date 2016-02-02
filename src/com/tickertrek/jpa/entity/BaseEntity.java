/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tickertrek.jpa.entity;

import com.spaceprogram.simplejpa.model.IdedBase;
import java.io.Serializable;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author rajesroy
 */
@MappedSuperclass
@EntityListeners({ModelListener.class})
public class BaseEntity  extends IdedBase implements Serializable{
	/*@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@RemoteProperty
	private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    */

}
