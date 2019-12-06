package org.coursecollector.esi.model;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author
 */
public interface UserRepository extends CrudRepository<User, String> {

    public List<User> findAllByOrderByUserName();
    
}
