package time.os;

import java.time.LocalDateTime;

public interface OS {
    LocalDateTime getCurrentSystemDateTime();
    void setSystemDateTime(LocalDateTime localDateTime);
    int getOperationCount();
}
