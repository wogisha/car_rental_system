package cs544.edu.userMgmt;

import cs544.edu.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by eessc on 8/12/2017.
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
