package wijmotopping.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import wijmotopping.SalesApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { SalesApplication.class })
public class CucumberSpingConfiguration {}
