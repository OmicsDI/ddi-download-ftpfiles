package uk.ac.ebi.ddi.task.ddidownloadftpfiles.utils;

import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FtpUtils {

    private static final int FTP_TIMEOUT = 5 * 60000;

    private static final int BUFFER_LIMIT = 1024 * 1024;

    private FtpUtils() {
    }

    public static DdiFPTClient createFtpClient(String host, String username, String password) throws IOException {
        DdiFPTClient ftpClient = new DdiFPTClient();
        ftpClient.setConnectTimeout(FTP_TIMEOUT);
        ftpClient.setDefaultTimeout(FTP_TIMEOUT);
        ftpClient.connect(host);
        ftpClient.login(username, password);
        ftpClient.setBufferSize(BUFFER_LIMIT);
        ftpClient.enterLocalPassiveMode();
        return ftpClient;
    }

    /**
     * Get list of files from given path that in their name or inside their path must not contain the given string
     * @param client FTPClient
     * @param path path of the folder
     * @param ignoreDir list of dir name to be ignored
     * @return list of file paths
     * @throws IOException
     */
    public static List<String> getListFiles(DdiFPTClient client, String path, String... ignoreDir) throws IOException {
        List<String> result = new ArrayList<>();
        for (String dir : ignoreDir) {
            if (path.equals(dir)) {
                return result;
            }
        }
        if (!client.changeWorkingDirectory(path)) {
            return Collections.emptyList();
        }
        FTPFile[] ftpFiles = client.listFiles();
        for (FTPFile file : ftpFiles) {
            if (!file.getName().equals(".") && !file.getName().equals("..")) {
                if (file.isDirectory()) {
                    result.addAll(getListFiles(client, file.getName(), ignoreDir));
                } else {
                    String link = String.format("%s/%s", client.printWorkingDirectory(), file.getName());
                    result.add(link);
                }
            }
        }
        client.changeToParentDirectory();
        return result;
    }
}
