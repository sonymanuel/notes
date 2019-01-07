package com.sm.notes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.Inet4Address;
import java.util.Properties;

public class Version implements Serializable {
    String version;
    String commit;
    String hostname;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @JsonIgnoreProperties
    static Version ver = null;

    public Version() {

    }

    public Version loadVersion() throws Exception {

        if (ver == null) {
            InputStream is = null;
            try {
                is = this.getClass().getClassLoader().getResourceAsStream("version.txt");
                Properties props = new Properties();
                props.load(is);
                ver = new Version();
                ver.setVersion(props.getProperty("version"));
                ver.setCommit(props.getProperty("commit"));
                ver.setHostname(Inet4Address.getLocalHost().getHostName());
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }
        System.out.println(ver);
        return ver;
    }

    public String toString() {
        return "Version = " + version + " ; commit id = " + commit;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }
}
