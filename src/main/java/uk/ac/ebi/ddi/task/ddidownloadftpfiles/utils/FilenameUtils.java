package uk.ac.ebi.ddi.task.ddidownloadftpfiles.utils;

public class FilenameUtils {

    private FilenameUtils() {
    }

    public static String getFileName(String path) {
        if (path.contains("/")) {
            return path.substring(path.lastIndexOf('/') + 1);
        }
        return path;
    }
}
