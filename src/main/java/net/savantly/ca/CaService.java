package net.savantly.ca;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wily.introscope.server.webservicesapi.IntroscopeWebServicesException;

import net.savantly.ca.configuration.CaConfiguration;
import webservices.introscope.wily.ca.MetricsDataService;
import webservices.introscope.wily.ca.MetricsDataServiceLocator;
import webservices.introscope.wily.ca.MetricsDataServiceSoapBindingStub;
import webservices.introscope.wily.ca.TimeslicedResultSetMetricData;

public class CaService {
	
	private static final Logger log = LoggerFactory.getLogger(CaService.class);

	@Autowired
	CaConfiguration caConfiguration;
	
	public TimeslicedResultSetMetricData[] getMetricData(String agentRegex, String metricPrefix) throws IntroscopeWebServicesException, RemoteException, MalformedURLException, ServiceException {
        MetricsDataService service = new MetricsDataServiceLocator(caConfiguration);
        URL url = new URL(service.getMetricsDataServiceAddress());
		MetricsDataServiceSoapBindingStub stub = (MetricsDataServiceSoapBindingStub) service.getMetricsDataService(url);

        stub.setUsername(caConfiguration.getUsername());
        stub.setPassword(caConfiguration.getPassword());

        log.info("Testing MetricsDataService");

        TimeslicedResultSetMetricData[] data =
                stub.getLiveMetricData(agentRegex, metricPrefix);
        
        if (null == data) {
        	data = new TimeslicedResultSetMetricData[0];
        }
        return data;
	}
}
