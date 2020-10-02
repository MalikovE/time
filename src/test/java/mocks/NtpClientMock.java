package mocks;


import time.ntp.NtpClient;

import java.net.InetSocketAddress;
import java.time.LocalDateTime;

public class NtpClientMock implements NtpClient {

    private final InetSocketAddress ntpServer;

    public NtpClientMock(final InetSocketAddress ntpServer) {
        this.ntpServer = ntpServer;
    }

    @Override
    public InetSocketAddress getNtpServer() {
        return this.ntpServer;
    }

    @Override
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }
}
