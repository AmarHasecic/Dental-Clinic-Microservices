package ba.unsa.etf.nwt.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "appointment_handler")
public interface AppointmentClient {
    @GetMapping("/appointments/health/status")
    String checkHealthStatus();
}
