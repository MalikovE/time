import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import time.os.OS;
import mocks.OSMock;
import time.os.TimeSetter;
import mocks.TimeSetterMock;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimeSetterTest {

    private TimeSetter timeSetter;
    private OS os;

    @Before
    public void setUp() throws Exception {
        os = new OSMock();
        timeSetter = new TimeSetterMock(os);
    }

    @Test
    public void testSetTime() {
        timeSetter.setTime(LocalDateTime.now());
        LocalDateTime expectedDateTime = os.getCurrentSystemDateTime();

        ZoneOffset zoneOffset = ZoneOffset.UTC;
        Assert.assertEquals(expectedDateTime.toInstant(zoneOffset), LocalDateTime.now().toInstant(zoneOffset));
    }
}
