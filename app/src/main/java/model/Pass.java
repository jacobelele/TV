package model;

/**
 * Created by PSI_DEV_07 on 2/1/2018.
 */

public class Pass {
    private String clearTextPassword;
    private long createTime;
    private String hashPassword;
    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public String getClearTextPassword() {
        return clearTextPassword;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClearTextPassword(String clearTextPassword) {
        this.clearTextPassword = clearTextPassword;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }
}
