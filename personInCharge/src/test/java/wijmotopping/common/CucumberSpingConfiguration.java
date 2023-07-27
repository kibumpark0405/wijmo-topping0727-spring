package wijmotopping.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import wijmotopping.PersonInChargeApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { PersonInChargeApplication.class })
public class CucumberSpingConfiguration {}
