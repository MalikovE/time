package time.os;

import java.time.LocalDateTime;

public class OSMock implements OS {

    private LocalDateTime systemTime;
    private int operationCounter = 0;

    @Override
    public LocalDateTime getCurrentSystemDateTime() {
        return systemTime;
    }

    @Override
    public void setSystemDateTime(LocalDateTime localDateTime) {
        systemTime = localDateTime;
        operationCounter++;
    }

    @Override
    public int getOperationCount() {
        return operationCounter;
    }
}
