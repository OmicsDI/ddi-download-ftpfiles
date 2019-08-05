package uk.ac.ebi.ddi.task.ddidownloadftpfiles.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties("downloadftpfiles")
public class DownloadFtpFileTaskProperties {

    // File to be download
    private String sourceDirectory;

    //Folder to copy the results file
    private Resource targetDirectory;

    // User of the ftp
    private String user;

    // Server of the ftp
    private String server;

    // Port of the FTP the default port fort most fo the services is 21
    private int port = 21;

    //Pattern of files to be download
    private String pattern;

    //Passowrd for the FTP
    private String password;

    public String getSourceDirectory() {
        return sourceDirectory;
    }

    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    public Resource getTargetDirectory() {
        return targetDirectory;
    }

    public void setTargetDirectory(Resource targetDirectory) {
        this.targetDirectory = targetDirectory;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


}
