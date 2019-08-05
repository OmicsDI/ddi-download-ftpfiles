package uk.ac.ebi.ddi.task.ddidownloadftpfiles;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import uk.ac.ebi.ddi.task.ddidownloadftpfiles.service.DownloadFilesService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DdiDownloadFtpfilesApplication.class,
        initializers = ConfigFileApplicationContextInitializer.class)
@TestPropertySource(properties = {
        "downloadftpfiles.sourceDirectory=/pub/databases/biomodels/omicsdi/BioModelsOmicsDIExport/",
        "downloadftpfiles.targetDirectory=file:/tmp/prod/biomodels",
        "downloadftpfiles.user=anonymous",
        "downloadftpfiles.server=ftp.ebi.ac.uk",
        "downloadftpfiles.pattern=OmicsDIEntries"
})
public class ITDownloadFileService {

    @Autowired
    private DownloadFilesService downloadFilesService;

    @Test
    public void contextLoads() throws Exception {
        downloadFilesService.download();
        Path path = Paths.get("/tmp/prod/biomodels");
        Assert.assertTrue(Files.exists(path));
    }
}