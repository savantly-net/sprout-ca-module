/**
 * MetricsDataServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservices.introscope.wily.ca;

import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.savantly.ca.CaModule;
import net.savantly.ca.configuration.CaConfiguration;
import net.savantly.sprout.modules.test.SproutModuleTest;

@TestPropertySource(locations="classpath:test.properties")
@ContextConfiguration(classes=CaModule.class)
public class MetricsDataServiceTestCase  extends SproutModuleTest {
	
	private final static Logger log = LoggerFactory.getLogger(MetricsDataServiceTestCase.class);
	
	@Autowired
	CaConfiguration caConfiguration;
	@Autowired
	ObjectMapper mapper;
	
	@Value("${test.agentRegex}")
	String agentRegex;
	@Value("${test.metricPrefix}")
	String metricPrefix;
	

    @Test
    public void testMetricsDataService() {
        try {
            MetricsDataService service = new MetricsDataServiceLocator(caConfiguration);
            URL url = new URL(service.getMetricsDataServiceAddress());
			MetricsDataServiceSoapBindingStub stub = (MetricsDataServiceSoapBindingStub) service.getMetricsDataService(url );

            stub.setUsername(caConfiguration.getUsername());
            stub.setPassword(caConfiguration.getPassword());

            log.info("Testing MetricsDataService");

            TimeslicedResultSetMetricData[] data =
                    stub.getLiveMetricData(agentRegex, metricPrefix);
            
            if (null != data) {
                for (int i = 0; i < data.length; ++i) {
                	log.info("{}", mapper.writeValueAsString(data[i]));
                }
            } else {
            	log.info("Data is null");
            }
        } catch (Exception e) {
            log.error("There was an error: %s", e);
            Assert.fail("SOAP request failed");
        }
    }


}
