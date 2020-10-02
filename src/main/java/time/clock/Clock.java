package time.clock;

import java.time.LocalDateTime;

public interface Clock {
    LocalDateTime getDateTime();
    void setDateTime(LocalDateTime localDateTime);
}
