package time.os;

import java.time.LocalDateTime;

public class TimeSetterMock implements TimeSetter {

    private OS os;

    public TimeSetterMock(OS os) {
        this.os = os;
    }

    @Override
    public void setOs(OS os) {
        this.os = os;
    }

    @Override
    public void setTime(LocalDateTime localDateTime) {
        os.setSystemDateTime(localDateTime);
    }
}
