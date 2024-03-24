package ba.unsa.etf.nwt.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/records")
public class RecordController {

    @GetMapping("/status/check")
    public String status(){
        return "Service is live";
    }
}
