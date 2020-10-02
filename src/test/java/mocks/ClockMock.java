package mocks;

import time.clock.Clock;

import java.time.LocalDateTime;

public class ClockMock implements Clock {

    private LocalDateTime dateTime;

    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public void setDateTime(LocalDateTime localDateTime) {
        dateTime = localDateTime;
    }

}
