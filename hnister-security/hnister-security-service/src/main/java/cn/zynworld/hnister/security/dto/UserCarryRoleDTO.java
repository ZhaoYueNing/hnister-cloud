package cn.zynworld.hnister.security.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/2/6
 * 携带角色信息
 */
public class UserCarryRoleDTO implements Serializable  {

    //user info
    private String username;

    private Integer zlassId;

    private String name;

    private Date birth;

    private Integer gender;

    private Short status;

    private String city;

    private String salt;

    private String password;

    private Date createTime;

    private String avatar;

    private Short type;

    //携带该用户的角色信息
    private List<Integer> roleIdList;

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public UserCarryRoleDTO setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserCarryRoleDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getZlassId() {
        return zlassId;
    }

    public UserCarryRoleDTO setZlassId(Integer zlassId) {
        this.zlassId = zlassId;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserCarryRoleDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Date getBirth() {
        return birth;
    }

    public UserCarryRoleDTO setBirth(Date birth) {
        this.birth = birth;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public UserCarryRoleDTO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    public Short getStatus() {
        return status;
    }

    public UserCarryRoleDTO setStatus(Short status) {
        this.status = status;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserCarryRoleDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public UserCarryRoleDTO setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserCarryRoleDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public UserCarryRoleDTO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public UserCarryRoleDTO setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public Short getType() {
        return type;
    }

    public UserCarryRoleDTO setType(Short type) {
        this.type = type;
        return this;
    }
}
