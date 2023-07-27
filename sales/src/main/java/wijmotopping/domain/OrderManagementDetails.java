package wijmotopping.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderManagementDetails {

    private Long id;

    private String uniqueNumber;

    private String itemCode;

    private String itemName;

    private String itemSpecification;
}
