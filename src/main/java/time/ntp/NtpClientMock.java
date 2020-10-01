package time.ntp;


import java.net.InetAddress;
import java.time.LocalDateTime;

public class NtpClientMock implements NtpClient {

    private final InetAddress ntpServer;

    public NtpClientMock(final InetAddress ntpServer) {
        this.ntpServer = ntpServer;
    }

    @Override
    public InetAddress getNtpServer() {
        return this.ntpServer;
    }

    @Override
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }
}
