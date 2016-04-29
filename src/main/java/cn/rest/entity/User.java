package cn.rest.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int userId;
    private String userCardId;
    private String userName;
    private String userCardPhoto;
    private String userPhone;
    private Integer userStatus;
    private String userPassword;
    private Timestamp createAt;
    private Timestamp updateAt;

    public User() {
    }

    public User(int userId, String userCardId, String userName,
            String userCardPhoto, String userPhone, Integer userStatus,
            String userPassword, Timestamp createAt, Timestamp updateAt) {
        this.userId = userId;
        this.userCardId = userCardId;
        this.userName = userName;
        this.userCardPhoto = userCardPhoto;
        this.userPhone = userPhone;
        this.userStatus = userStatus;
        this.userPassword = userPassword;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(String userCardId) {
        this.userCardId = userCardId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCardPhoto() {
        return userCardPhoto;
    }

    public void setUserCardPhoto(String userCardPhoto) {
        this.userCardPhoto = userCardPhoto;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((createAt == null) ? 0 : createAt.hashCode());
        result = prime * result
                + ((updateAt == null) ? 0 : updateAt.hashCode());
        result = prime * result
                + ((userCardId == null) ? 0 : userCardId.hashCode());
        result = prime * result
                + ((userCardPhoto == null) ? 0 : userCardPhoto.hashCode());
        result = prime * result + userId;
        result = prime * result
                + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result
                + ((userPassword == null) ? 0 : userPassword.hashCode());
        result = prime * result
                + ((userPhone == null) ? 0 : userPhone.hashCode());
        result = prime * result
                + ((userStatus == null) ? 0 : userStatus.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (createAt == null) {
            if (other.createAt != null)
                return false;
        } else if (!createAt.equals(other.createAt))
            return false;
        if (updateAt == null) {
            if (other.updateAt != null)
                return false;
        } else if (!updateAt.equals(other.updateAt))
            return false;
        if (userCardId == null) {
            if (other.userCardId != null)
                return false;
        } else if (!userCardId.equals(other.userCardId))
            return false;
        if (userCardPhoto == null) {
            if (other.userCardPhoto != null)
                return false;
        } else if (!userCardPhoto.equals(other.userCardPhoto))
            return false;
        if (userId != other.userId)
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        if (userPassword == null) {
            if (other.userPassword != null)
                return false;
        } else if (!userPassword.equals(other.userPassword))
            return false;
        if (userPhone == null) {
            if (other.userPhone != null)
                return false;
        } else if (!userPhone.equals(other.userPhone))
            return false;
        if (userStatus == null) {
            if (other.userStatus != null)
                return false;
        } else if (!userStatus.equals(other.userStatus))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userCardId=" + userCardId
                + ", userName=" + userName + ", userCardPhoto=" + userCardPhoto
                + ", userPhone=" + userPhone + ", userStatus=" + userStatus
                + ", userPassword=" + userPassword + ", createAt=" + createAt
                + ", updateAt=" + updateAt + "]";
    }

}
