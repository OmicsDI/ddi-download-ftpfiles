package uk.ac.ebi.ddi.task.ddidownloadftpfiles.utils;

import org.apache.commons.net.ftp.FTPClient;

import java.io.Closeable;
import java.io.IOException;

public class DdiFPTClient extends FTPClient implements Closeable {
    @Override
    public void close() throws IOException {
        if (isConnected()) {
            logout();
            disconnect();
        }
    }
}
