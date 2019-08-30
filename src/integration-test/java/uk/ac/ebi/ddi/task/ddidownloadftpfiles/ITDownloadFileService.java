package uk.ac.ebi.ddi.task.ddidownloadftpfiles;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import uk.ac.ebi.ddi.ddifileservice.services.IFileSystem;
import uk.ac.ebi.ddi.task.ddidownloadftpfiles.configuration.DownloadFtpFileTaskProperties;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DdiDownloadFtpfilesApplication.class,
        initializers = ConfigFileApplicationContextInitializer.class)
@TestPropertySource(properties = {
        "ftp.sourceDir=/pub/databases/biomodels/logical/",
        "ftp.targetDir=/tmp/prod/biomodels",
        "ftp.server=ftp.ebi.ac.uk",
        "ftp.pattern=BioModels",
        "file.provider=local"
})
public class ITDownloadFileService {

    @Autowired
    private DownloadFtpFileTaskProperties dldProps;

    @Autowired
    private IFileSystem fileSystem;

    @Autowired
    private DdiDownloadFtpfilesApplication application;

    @Before
    public void setUp() throws Exception {
        new File(dldProps.getTargetDir()).mkdirs();
    }

    @After
    public void tearDown() throws Exception {
        fileSystem.cleanDirectory(dldProps.getTargetDir());
    }

    @Test
    public void contextLoads() throws Exception {
        application.run();
        Assert.assertTrue(fileSystem.listFilesFromFolder(dldProps.getTargetDir()).size() > 0);
    }
}