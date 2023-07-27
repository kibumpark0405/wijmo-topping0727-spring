package wijmotopping.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import wijmotopping.PersonInChargeApplication;

@Entity
@Table(name = "PersonInCharge_table")
@Data
public class PersonInCharge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public static PersonInChargeRepository repository() {
        PersonInChargeRepository personInChargeRepository = PersonInChargeApplication.applicationContext.getBean(
            PersonInChargeRepository.class
        );
        return personInChargeRepository;
    }
}
