package ba.unsa.etf.nwt.feign;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class RabbitMqReceiver {
    private CountDownLatch latch = new CountDownLatch();
    public void recieveMessage(String message){
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }
    public CountDownLatch getLatch() {
        return latch;
    }
}
