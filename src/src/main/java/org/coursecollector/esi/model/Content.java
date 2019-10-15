/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.coursecollector.esi.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import lombok.Data;

/**
 *
 * @author 
 */
@Entity
@Data
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    
}
