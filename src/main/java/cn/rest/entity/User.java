package cn.rest.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer fb_user_id;
    private String fb_user_name;
    private String fb_user_phone;
    private String fb_user_password;
    private Timestamp fb_created_at;
    private Timestamp fb_updated_at;
    public User() {
        super();
    }
    public User(Integer fb_user_id, String fb_user_name, String fb_user_phone,
            String fb_user_password, Timestamp fb_created_at,
            Timestamp fb_updated_at) {
        super();
        this.fb_user_id = fb_user_id;
        this.fb_user_name = fb_user_name;
        this.fb_user_phone = fb_user_phone;
        this.fb_user_password = fb_user_password;
        this.fb_created_at = fb_created_at;
        this.fb_updated_at = fb_updated_at;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((fb_created_at == null) ? 0 : fb_created_at.hashCode());
        result = prime * result
                + ((fb_updated_at == null) ? 0 : fb_updated_at.hashCode());
        result = prime * result
                + ((fb_user_id == null) ? 0 : fb_user_id.hashCode());
        result = prime * result
                + ((fb_user_name == null) ? 0 : fb_user_name.hashCode());
        result = prime
                * result
                + ((fb_user_password == null) ? 0 : fb_user_password.hashCode());
        result = prime * result
                + ((fb_user_phone == null) ? 0 : fb_user_phone.hashCode());
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
        if (fb_created_at == null) {
            if (other.fb_created_at != null)
                return false;
        } else if (!fb_created_at.equals(other.fb_created_at))
            return false;
        if (fb_updated_at == null) {
            if (other.fb_updated_at != null)
                return false;
        } else if (!fb_updated_at.equals(other.fb_updated_at))
            return false;
        if (fb_user_id == null) {
            if (other.fb_user_id != null)
                return false;
        } else if (!fb_user_id.equals(other.fb_user_id))
            return false;
        if (fb_user_name == null) {
            if (other.fb_user_name != null)
                return false;
        } else if (!fb_user_name.equals(other.fb_user_name))
            return false;
        if (fb_user_password == null) {
            if (other.fb_user_password != null)
                return false;
        } else if (!fb_user_password.equals(other.fb_user_password))
            return false;
        if (fb_user_phone == null) {
            if (other.fb_user_phone != null)
                return false;
        } else if (!fb_user_phone.equals(other.fb_user_phone))
            return false;
        return true;
    }
    public Integer getFb_user_id() {
        return fb_user_id;
    }
    public void setFb_user_id(Integer fb_user_id) {
        this.fb_user_id = fb_user_id;
    }
    public String getFb_user_name() {
        return fb_user_name;
    }
    public void setFb_user_name(String fb_user_name) {
        this.fb_user_name = fb_user_name;
    }
    public String getFb_user_phone() {
        return fb_user_phone;
    }
    public void setFb_user_phone(String fb_user_phone) {
        this.fb_user_phone = fb_user_phone;
    }
    public String getFb_user_password() {
        return fb_user_password;
    }
    public void setFb_user_password(String fb_user_password) {
        this.fb_user_password = fb_user_password;
    }
    public Timestamp getFb_created_at() {
        return fb_created_at;
    }
    public void setFb_created_at(Timestamp fb_created_at) {
        this.fb_created_at = fb_created_at;
    }
    public Timestamp getFb_updated_at() {
        return fb_updated_at;
    }
    public void setFb_updated_at(Timestamp fb_updated_at) {
        this.fb_updated_at = fb_updated_at;
    }
    @Override
    public String toString() {
        return "User [fb_user_id=" + fb_user_id + ", fb_user_name="
                + fb_user_name + ", fb_user_phone=" + fb_user_phone
                + ", fb_user_password=" + fb_user_password + ", fb_created_at="
                + fb_created_at + ", fb_updated_at=" + fb_updated_at + "]";
    }

   

}
