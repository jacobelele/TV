package model;

public class Setting {
    private Integer id;
    private String serverIp;
    private Integer serverPort;
    private String localIp;
    private String mac;
    private Boolean enablePassword;
    private String password;

    public Setting(Integer id, String serverIp, Integer serverPort, String localIp,
                   String mac, Boolean enablePassword, String password) {
        this.id = id;
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.localIp = localIp;
        this.mac = mac;
        this.enablePassword = enablePassword;
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setEnablePassword(Boolean enablePassword) {
        this.enablePassword = enablePassword;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public String getMac() {
        return mac;
    }

    public Boolean getEnablePassword() {
        return enablePassword;
    }

    public String getLocalIp() {
        return localIp;
    }

    public String getPassword() {
        return password;
    }

    public String getServerIp() {
        return serverIp;
    }

    public Integer getServerPort() {
        return serverPort;
    }
}
