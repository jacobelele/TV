package model;

import android.app.Application;

/**
 * Created by PSI_DEV_07 on 1/29/2018.
 */

public class SettingApplication extends Application {
    private String serverIp;
    private String serverPort;
    private String macAddress;

    public String getServerIp() {
        return serverIp;
    }

    public String getServerPort() {
        return serverPort;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
