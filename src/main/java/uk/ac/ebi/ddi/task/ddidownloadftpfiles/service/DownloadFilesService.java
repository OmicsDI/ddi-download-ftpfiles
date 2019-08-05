package uk.ac.ebi.ddi.task.ddidownloadftpfiles.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.core.io.Resource;
import uk.ac.ebi.ddi.task.ddidownloadftpfiles.configuration.DownloadFtpFileTaskProperties;

@Service
public class DownloadFilesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DownloadFilesService.class);

    @Autowired
    private DownloadFtpFileTaskProperties downloadFtpFileTaskProperties;

    public void download() throws IOException {

        DDICleanDirectory.cleanDirectory(downloadFtpFileTaskProperties.getTargetDirectory());

        //new ftp client
        FTPClient ftp = new FTPClient();
        //try to connect
        ftp.connect(downloadFtpFileTaskProperties.getServer(), downloadFtpFileTaskProperties.getPort());
        //login to server
        if (!ftp.login(downloadFtpFileTaskProperties.getUser(), downloadFtpFileTaskProperties.getPassword())) {
            ftp.logout();
        }
        int reply = ftp.getReplyCode();
        //FTPReply stores a set of constants for FTP reply codes.
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
        }

        //enter passive mode
        ftp.enterLocalPassiveMode();
        //get system name
        LOGGER.info("Remote system is {}", ftp.getSystemType());
        //change current directory
        ftp.changeWorkingDirectory(downloadFtpFileTaskProperties.getSourceDirectory());

        LOGGER.info("Current directory is {}", ftp.printWorkingDirectory());

        LOGGER.info("pattern is {}", downloadFtpFileTaskProperties.getPattern());

        //get list of filenames
        FTPFile[] ftpFiles = ftp.listFiles();

        LOGGER.info("number of files are " + ftpFiles.length);
        LOGGER.info("file names are {}", ftpFiles);
        for (FTPFile file : ftpFiles) {
            LOGGER.info("file name is " + file.getName());
            LOGGER.info("does file has pattern {}", file.getName().contains(downloadFtpFileTaskProperties.getPattern()));
            if ((file.isFile() || file.isSymbolicLink())
                    && (downloadFtpFileTaskProperties.getPattern() != null && !downloadFtpFileTaskProperties.getPattern().isEmpty()
                    && file.getName().contains(downloadFtpFileTaskProperties.getPattern()))) {
                LOGGER.info("File is " + file.getName());
                try (OutputStream output = new FileOutputStream(
                        downloadFtpFileTaskProperties.getTargetDirectory().getFile() + "/" + file.getName())) {
                    ftp.retrieveFile(file.getName(), output);
                }
            }
        }
        ftp.logout();
        ftp.disconnect();
    }

}
