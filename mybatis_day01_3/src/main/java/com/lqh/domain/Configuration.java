package com.lqh.domain;

import java.io.Serializable;
import java.util.Map;
/**核心配置类
 * 数据库四大核心信息
 * mapper对象的map集合
 * @author Administrator
 */
public class Configuration implements Serializable{

    private static final long serialVersionUID = -4281018433173425892L;
    private String username;
    private String password;
    private String driver;
    private String url;

    private Map<String,Mapper> mappers;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    public void setMappers(Map<String, Mapper> mappers) {
        this.mappers = mappers;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", mappers=" + mappers +
                '}';
    }
}
