/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.coursecollector.esi.model;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Ibrahima DIIALLO
 */
public interface UserRepository extends CrudRepository<Course, Long> {

}