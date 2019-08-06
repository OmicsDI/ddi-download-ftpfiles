package uk.ac.ebi.ddi.task.ddidownloadftpfiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.ac.ebi.ddi.task.ddidownloadftpfiles.configuration.DownloadFtpFileTaskProperties;
import uk.ac.ebi.ddi.task.ddidownloadftpfiles.service.DownloadFilesService;

@SpringBootApplication
public class DdiDownloadFtpfilesApplication implements CommandLineRunner {

	@Autowired
	private DownloadFilesService downloadFilesService;

	@Autowired
	private DownloadFtpFileTaskProperties dldProps;

	public static void main(String[] args) {

		SpringApplication.run(DdiDownloadFtpfilesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		downloadFilesService.download(dldProps.getUser(), dldProps.getPort(), dldProps.getTargetDirectory(),
				dldProps.getServer(), dldProps.getPassword(), dldProps.getSourceDirectory(), dldProps.getPattern());
	}
}
