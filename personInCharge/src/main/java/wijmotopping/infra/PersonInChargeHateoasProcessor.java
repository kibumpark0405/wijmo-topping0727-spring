package wijmotopping.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import wijmotopping.domain.*;

@Component
public class PersonInChargeHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<PersonInCharge>> {

    @Override
    public EntityModel<PersonInCharge> process(
        EntityModel<PersonInCharge> model
    ) {
        return model;
    }
}
