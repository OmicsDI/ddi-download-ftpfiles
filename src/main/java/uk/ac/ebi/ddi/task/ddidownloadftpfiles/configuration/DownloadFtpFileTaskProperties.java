package uk.ac.ebi.ddi.task.ddidownloadftpfiles.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ftp")
public class DownloadFtpFileTaskProperties {

    // File to be download
    private String sourceDir;

    // Folder to copy the results file
    private String targetDir;

    // User of the ftp
    private String user = "anonymous";

    // Server of the ftp
    private String server;

    // Port of the FTP the default port fort most fo the services is 21
    private int port = 21;

    // Pattern of files to be download
    private String pattern;

    // Dirs to be ignored
    private String[] ignoreDirs = new String[0];

    // Password for the FTP
    private String password = "anonymous";

    public String getSourceDir() {
        return sourceDir;
    }

    public void setSourceDir(String sourceDir) {
        this.sourceDir = sourceDir;
    }

    public String getTargetDir() {
        return targetDir;
    }

    public void setTargetDir(String targetDir) {
        this.targetDir = targetDir;
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

    public String[] getIgnoreDirs() {
        return ignoreDirs;
    }

    public void setIgnoreDirs(String[] ignoreDirs) {
        this.ignoreDirs = ignoreDirs;
    }
}
