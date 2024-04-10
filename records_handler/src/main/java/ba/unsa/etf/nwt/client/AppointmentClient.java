package ba.unsa.etf.nwt.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("appointment-api")
public interface AppointmentClient {
    @RequestMapping(method = RequestMethod.GET, value = "/appointments/health/status")
    String getHealthStatus();
}
