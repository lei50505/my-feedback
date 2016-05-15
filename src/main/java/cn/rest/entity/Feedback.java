package cn.rest.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Feedback implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Integer fb_feedback_id;
    private Integer fb_user_id;
    private Integer fb_shop_id;
    private String fb_feedback_phone;
    private String fb_feedback_content;
    private Integer fb_feedback_type;
    private Integer fb_feedback_status;
    private Timestamp fb_created_at;
    private Timestamp fb_updated_at;
    public Feedback() {
        super();
    }
    public Feedback(Integer fb_feedback_id, Integer fb_user_id,
            Integer fb_shop_id, String fb_feedback_phone,
            String fb_feedback_content, Integer fb_feedback_type,
            Integer fb_feedback_status, Timestamp fb_created_at,
            Timestamp fb_updated_at) {
        super();
        this.fb_feedback_id = fb_feedback_id;
        this.fb_user_id = fb_user_id;
        this.fb_shop_id = fb_shop_id;
        this.fb_feedback_phone = fb_feedback_phone;
        this.fb_feedback_content = fb_feedback_content;
        this.fb_feedback_type = fb_feedback_type;
        this.fb_feedback_status = fb_feedback_status;
        this.fb_created_at = fb_created_at;
        this.fb_updated_at = fb_updated_at;
    }
    public Integer getFb_feedback_id() {
        return fb_feedback_id;
    }
    public void setFb_feedback_id(Integer fb_feedback_id) {
        this.fb_feedback_id = fb_feedback_id;
    }
    public Integer getFb_user_id() {
        return fb_user_id;
    }
    public void setFb_user_id(Integer fb_user_id) {
        this.fb_user_id = fb_user_id;
    }
    public Integer getFb_shop_id() {
        return fb_shop_id;
    }
    public void setFb_shop_id(Integer fb_shop_id) {
        this.fb_shop_id = fb_shop_id;
    }
    public String getFb_feedback_phone() {
        return fb_feedback_phone;
    }
    public void setFb_feedback_phone(String fb_feedback_phone) {
        this.fb_feedback_phone = fb_feedback_phone;
    }
    public String getFb_feedback_content() {
        return fb_feedback_content;
    }
    public void setFb_feedback_content(String fb_feedback_content) {
        this.fb_feedback_content = fb_feedback_content;
    }
    public Integer getFb_feedback_type() {
        return fb_feedback_type;
    }
    public void setFb_feedback_type(Integer fb_feedback_type) {
        this.fb_feedback_type = fb_feedback_type;
    }
    public Integer getFb_feedback_status() {
        return fb_feedback_status;
    }
    public void setFb_feedback_status(Integer fb_feedback_status) {
        this.fb_feedback_status = fb_feedback_status;
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
        result = prime
                * result
                + ((fb_feedback_content == null) ? 0 : fb_feedback_content
                        .hashCode());
        result = prime * result
                + ((fb_feedback_id == null) ? 0 : fb_feedback_id.hashCode());
        result = prime
                * result
                + ((fb_feedback_phone == null) ? 0 : fb_feedback_phone
                        .hashCode());
        result = prime
                * result
                + ((fb_feedback_status == null) ? 0 : fb_feedback_status
                        .hashCode());
        result = prime
                * result
                + ((fb_feedback_type == null) ? 0 : fb_feedback_type.hashCode());
        result = prime * result
                + ((fb_shop_id == null) ? 0 : fb_shop_id.hashCode());
        result = prime * result
                + ((fb_updated_at == null) ? 0 : fb_updated_at.hashCode());
        result = prime * result
                + ((fb_user_id == null) ? 0 : fb_user_id.hashCode());
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
        Feedback other = (Feedback) obj;
        if (fb_created_at == null) {
            if (other.fb_created_at != null)
                return false;
        } else if (!fb_created_at.equals(other.fb_created_at))
            return false;
        if (fb_feedback_content == null) {
            if (other.fb_feedback_content != null)
                return false;
        } else if (!fb_feedback_content.equals(other.fb_feedback_content))
            return false;
        if (fb_feedback_id == null) {
            if (other.fb_feedback_id != null)
                return false;
        } else if (!fb_feedback_id.equals(other.fb_feedback_id))
            return false;
        if (fb_feedback_phone == null) {
            if (other.fb_feedback_phone != null)
                return false;
        } else if (!fb_feedback_phone.equals(other.fb_feedback_phone))
            return false;
        if (fb_feedback_status == null) {
            if (other.fb_feedback_status != null)
                return false;
        } else if (!fb_feedback_status.equals(other.fb_feedback_status))
            return false;
        if (fb_feedback_type == null) {
            if (other.fb_feedback_type != null)
                return false;
        } else if (!fb_feedback_type.equals(other.fb_feedback_type))
            return false;
        if (fb_shop_id == null) {
            if (other.fb_shop_id != null)
                return false;
        } else if (!fb_shop_id.equals(other.fb_shop_id))
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
        return true;
    }
    @Override
    public String toString() {
        return "Feedback [fb_feedback_id=" + fb_feedback_id + ", fb_user_id="
                + fb_user_id + ", fb_shop_id=" + fb_shop_id
                + ", fb_feedback_phone=" + fb_feedback_phone
                + ", fb_feedback_content=" + fb_feedback_content
                + ", fb_feedback_type=" + fb_feedback_type
                + ", fb_feedback_status=" + fb_feedback_status
                + ", fb_created_at=" + fb_created_at + ", fb_updated_at="
                + fb_updated_at + "]";
    }
}
