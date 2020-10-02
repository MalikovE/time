package time.ntp;

import java.net.InetSocketAddress;
import java.time.LocalDateTime;

public interface NtpClient {
    InetSocketAddress getNtpServer();
    LocalDateTime getTime();
}
