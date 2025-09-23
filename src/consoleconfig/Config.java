package consoleconfig;

public class Config {   
    private static Config instance;
    private Encoding encoding;

    private Config() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows")) {
            encoding = Encoding.CP866;
        } else {
            encoding = Encoding.UTF8;
        }
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public Encoding getEncoding() {
        return encoding;
    }

    public void setEncoding(Encoding encoding) {
        this.encoding = encoding;
    }
}
