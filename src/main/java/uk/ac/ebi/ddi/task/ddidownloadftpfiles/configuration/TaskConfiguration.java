package uk.ac.ebi.ddi.task.ddidownloadftpfiles.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableTask
@EnableConfigurationProperties({ DownloadFtpFileTaskProperties.class })
public class TaskConfiguration {
}
