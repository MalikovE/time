package time.ntp;

import java.net.InetAddress;
import java.time.LocalDateTime;

public interface NtpClient {
    InetAddress getNtpServer();
    LocalDateTime getTime();
}
