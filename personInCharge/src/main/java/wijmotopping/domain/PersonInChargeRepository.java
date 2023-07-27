package wijmotopping.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import wijmotopping.domain.*;

@RepositoryRestResource(
    collectionResourceRel = "personInCharges",
    path = "personInCharges"
)
public interface PersonInChargeRepository
    extends PagingAndSortingRepository<PersonInCharge, Long> {}
