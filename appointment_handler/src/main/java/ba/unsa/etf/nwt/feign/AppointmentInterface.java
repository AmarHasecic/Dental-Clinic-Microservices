package ba.unsa.etf.nwt.feign;

import ba.unsa.etf.nwt.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("users-api")
public interface AppointmentInterface {

    @GetMapping("users/{userId}")
    public ResponseEntity<UserDto> findUsers(@PathVariable Long userId);

}
