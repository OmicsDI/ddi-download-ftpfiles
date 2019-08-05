package uk.ac.ebi.ddi.task.ddidownloadftpfiles.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.core.io.Resource;
import uk.ac.ebi.ddi.task.ddidownloadftpfiles.DdiDownloadFtpfilesApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class DDICleanDirectory {
    private static final Logger LOGGER = LoggerFactory.getLogger(DDICleanDirectory.class);

    public static void cleanDirectory(String directoryName) {
        File inputDirectory = new File(directoryName);
        if (inputDirectory.isDirectory()) {
            File[] files = inputDirectory.listFiles();
            if (files == null) {
                return;
            }
            for (File file : files) {
                try {
                    Files.deleteIfExists(file.toPath());
                } catch (IOException e) {
                    LOGGER.error("An error occurred when delete file {}, ", file.getAbsolutePath(), e);
                }
            }
        }
    }

    public static void cleanDirectory(Resource inputDirectory) throws IOException {
        if (inputDirectory != null && inputDirectory.getFile().isDirectory()) {
            File[] files = inputDirectory.getFile().listFiles();
            if (files == null) {
                return;
            }
            for (File file : files) {
                try {
                    Files.deleteIfExists(file.toPath());
                } catch (IOException e) {
                    LOGGER.error("An error occurred when delete file {}, ", file.getAbsolutePath(), e);
                }
            }
        }
    }
}
