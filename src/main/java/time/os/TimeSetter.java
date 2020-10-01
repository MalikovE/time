package time.os;

import java.time.LocalDateTime;

public interface TimeSetter {
    void setOs(OS os);
    void setTime(LocalDateTime localDateTime);
}
