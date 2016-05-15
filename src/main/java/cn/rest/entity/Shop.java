package cn.rest.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Shop implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer fb_shop_id;
    private String fb_shop_name;
    private String fb_shop_phone;
    private String fb_shop_address;
    private String fb_shop_email;
    private String fb_shop_password;
    private String fb_shop_token;
    private Timestamp fb_expired_at;
    private Timestamp fb_created_at;
    private Timestamp fb_updated_at;
    public Shop() {
        super();
    }
    public Shop(Integer fb_shop_id, String fb_shop_name, String fb_shop_phone,
            String fb_shop_address, String fb_shop_email,
            String fb_shop_password, String fb_shop_token,
            Timestamp fb_expired_at, Timestamp fb_created_at,
            Timestamp fb_updated_at) {
        super();
        this.fb_shop_id = fb_shop_id;
        this.fb_shop_name = fb_shop_name;
        this.fb_shop_phone = fb_shop_phone;
        this.fb_shop_address = fb_shop_address;
        this.fb_shop_email = fb_shop_email;
        this.fb_shop_password = fb_shop_password;
        this.fb_shop_token = fb_shop_token;
        this.fb_expired_at = fb_expired_at;
        this.fb_created_at = fb_created_at;
        this.fb_updated_at = fb_updated_at;
    }
    public Integer getFb_shop_id() {
        return fb_shop_id;
    }
    public void setFb_shop_id(Integer fb_shop_id) {
        this.fb_shop_id = fb_shop_id;
    }
    public String getFb_shop_name() {
        return fb_shop_name;
    }
    public void setFb_shop_name(String fb_shop_name) {
        this.fb_shop_name = fb_shop_name;
    }
    public String getFb_shop_phone() {
        return fb_shop_phone;
    }
    public void setFb_shop_phone(String fb_shop_phone) {
        this.fb_shop_phone = fb_shop_phone;
    }
    public String getFb_shop_address() {
        return fb_shop_address;
    }
    public void setFb_shop_address(String fb_shop_address) {
        this.fb_shop_address = fb_shop_address;
    }
    public String getFb_shop_email() {
        return fb_shop_email;
    }
    public void setFb_shop_email(String fb_shop_email) {
        this.fb_shop_email = fb_shop_email;
    }
    public String getFb_shop_password() {
        return fb_shop_password;
    }
    public void setFb_shop_password(String fb_shop_password) {
        this.fb_shop_password = fb_shop_password;
    }
    public String getFb_shop_token() {
        return fb_shop_token;
    }
    public void setFb_shop_token(String fb_shop_token) {
        this.fb_shop_token = fb_shop_token;
    }
    public Timestamp getFb_expired_at() {
        return fb_expired_at;
    }
    public void setFb_expired_at(Timestamp fb_expired_at) {
        this.fb_expired_at = fb_expired_at;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((fb_created_at == null) ? 0 : fb_created_at.hashCode());
        result = prime * result
                + ((fb_expired_at == null) ? 0 : fb_expired_at.hashCode());
        result = prime * result
                + ((fb_shop_address == null) ? 0 : fb_shop_address.hashCode());
        result = prime * result
                + ((fb_shop_email == null) ? 0 : fb_shop_email.hashCode());
        result = prime * result
                + ((fb_shop_id == null) ? 0 : fb_shop_id.hashCode());
        result = prime * result
                + ((fb_shop_name == null) ? 0 : fb_shop_name.hashCode());
        result = prime
                * result
                + ((fb_shop_password == null) ? 0 : fb_shop_password.hashCode());
        result = prime * result
                + ((fb_shop_phone == null) ? 0 : fb_shop_phone.hashCode());
        result = prime * result
                + ((fb_shop_token == null) ? 0 : fb_shop_token.hashCode());
        result = prime * result
                + ((fb_updated_at == null) ? 0 : fb_updated_at.hashCode());
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
        Shop other = (Shop) obj;
        if (fb_created_at == null) {
            if (other.fb_created_at != null)
                return false;
        } else if (!fb_created_at.equals(other.fb_created_at))
            return false;
        if (fb_expired_at == null) {
            if (other.fb_expired_at != null)
                return false;
        } else if (!fb_expired_at.equals(other.fb_expired_at))
            return false;
        if (fb_shop_address == null) {
            if (other.fb_shop_address != null)
                return false;
        } else if (!fb_shop_address.equals(other.fb_shop_address))
            return false;
        if (fb_shop_email == null) {
            if (other.fb_shop_email != null)
                return false;
        } else if (!fb_shop_email.equals(other.fb_shop_email))
            return false;
        if (fb_shop_id == null) {
            if (other.fb_shop_id != null)
                return false;
        } else if (!fb_shop_id.equals(other.fb_shop_id))
            return false;
        if (fb_shop_name == null) {
            if (other.fb_shop_name != null)
                return false;
        } else if (!fb_shop_name.equals(other.fb_shop_name))
            return false;
        if (fb_shop_password == null) {
            if (other.fb_shop_password != null)
                return false;
        } else if (!fb_shop_password.equals(other.fb_shop_password))
            return false;
        if (fb_shop_phone == null) {
            if (other.fb_shop_phone != null)
                return false;
        } else if (!fb_shop_phone.equals(other.fb_shop_phone))
            return false;
        if (fb_shop_token == null) {
            if (other.fb_shop_token != null)
                return false;
        } else if (!fb_shop_token.equals(other.fb_shop_token))
            return false;
        if (fb_updated_at == null) {
            if (other.fb_updated_at != null)
                return false;
        } else if (!fb_updated_at.equals(other.fb_updated_at))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Shop [fb_shop_id=" + fb_shop_id + ", fb_shop_name="
                + fb_shop_name + ", fb_shop_phone=" + fb_shop_phone
                + ", fb_shop_address=" + fb_shop_address + ", fb_shop_email="
                + fb_shop_email + ", fb_shop_password=" + fb_shop_password
                + ", fb_shop_token=" + fb_shop_token + ", fb_expired_at="
                + fb_expired_at + ", fb_created_at=" + fb_created_at
                + ", fb_updated_at=" + fb_updated_at + "]";
    }
    
}
