package org.coursecollector.esi.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Ibrahima DIALLO
 * @author Solofo R.
 */
public interface StudentRepository extends CrudRepository<Student, String> {

    public List<Student> findAllByOrderByUserName();

}
