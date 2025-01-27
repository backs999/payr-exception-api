package uk.co.payr.payrexceptionapi.exception.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.co.payr.payrexceptionapi.exception.model.StoredException;

public interface ExceptionRepository extends CrudRepository
        <StoredException, String>, PagingAndSortingRepository<StoredException, String> {
}
