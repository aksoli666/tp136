package app.tp136.util;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class AuctionNumberGenerator {
    public String generateAuctionNumber() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
