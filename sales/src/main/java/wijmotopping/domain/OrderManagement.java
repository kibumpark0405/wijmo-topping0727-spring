package wijmotopping.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import wijmotopping.SalesApplication;

@Entity
@Table(name = "OrderManagement_table")
@Data
public class OrderManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date accountingYearAndMonth;

    private String orderType;

    private String orderNumber;

    private String placeOfDelivery;

    private Date fewWeeks;

    private Date dueDate;

    private String numberOfOrderItems;

    private String totalDrinkingCapacity;

    private String totalOrderAmount;

    @Embedded
    private PersonInChargeId personInChargeId;

    private RegisteredPerson registeredPerson;

    @ElementCollection
    private List<OrderManagementDetails> orderManagementDetails;

    public static OrderManagementRepository repository() {
        OrderManagementRepository orderManagementRepository = SalesApplication.applicationContext.getBean(
            OrderManagementRepository.class
        );
        return orderManagementRepository;
    }
}
