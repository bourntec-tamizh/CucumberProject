package managers;


import dataProvider.ConfigReader;

public class FileReaderManager {
    private static FileReaderManager fileReaderManager = new FileReaderManager();
    private static ConfigReader configReader;

    private FileReaderManager() {
    }

    public static FileReaderManager getInstance( ) {
        return fileReaderManager;
    }

    public ConfigReader getConfigReader() {
        return (configReader == null) ? new ConfigReader() : configReader;
    }

}
