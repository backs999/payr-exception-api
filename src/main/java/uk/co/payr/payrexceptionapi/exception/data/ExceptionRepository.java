package uk.co.payr.payrusersapi.user.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.co.payr.payrusersapi.user.model.User;

public interface UserRepository extends CrudRepository
        <User, String>, PagingAndSortingRepository<User, String> {
}
