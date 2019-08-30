package uk.ac.ebi.ddi.task.ddidownloadftpfiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.ac.ebi.ddi.ddifileservice.services.IFileSystem;
import uk.ac.ebi.ddi.task.ddidownloadftpfiles.configuration.DownloadFtpFileTaskProperties;
import uk.ac.ebi.ddi.task.ddidownloadftpfiles.utils.DdiFPTClient;
import uk.ac.ebi.ddi.task.ddidownloadftpfiles.utils.FilenameUtils;
import uk.ac.ebi.ddi.task.ddidownloadftpfiles.utils.FtpUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

@SpringBootApplication
public class DdiDownloadFtpfilesApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DdiDownloadFtpfilesApplication.class);

    @Autowired
    private DownloadFtpFileTaskProperties props;

    @Autowired
    private IFileSystem fileSystem;

    public static void main(String[] args) {
        SpringApplication.run(DdiDownloadFtpfilesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try (DdiFPTClient client = FtpUtils.createFtpClient(props.getServer(), props.getUser(), props.getPassword())) {
            List<String> files = FtpUtils.getListFiles(client, props.getSourceDir(), props.getIgnoreDirs());
            for (String file : files) {
                if (file.contains(props.getPattern())) {
                    downloadFile(client, file);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Exception occurred when trying to download ftp files, {}, ", props, e);
        }
    }

    private void downloadFile(DdiFPTClient client, String filePath) {
        try {
            String fileName = FilenameUtils.getFileName(filePath);
            File tmpFile = File.createTempFile("ddi", fileName);
            try (OutputStream output = new FileOutputStream(tmpFile)) {
                client.retrieveFile(filePath, output);
            }
            String outputFile = props.getTargetDir() + "/" + fileName;
            fileSystem.copyFile(tmpFile, outputFile);
        } catch (Exception e) {
            LOGGER.error("Exception occurred when trying download file {}, ", filePath, e);
        }
    }
}
