package cn.rest.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class ShopTask implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer fb_shop_task_id;
    private Integer fb_shop_id;
    private String fb_shop_task_email;
    private Integer fb_shop_task_status;
    private Timestamp fb_created_at;
    private Timestamp fb_updated_at;
    public ShopTask() {
        super();
    }
    public ShopTask(Integer fb_shop_task_id, Integer fb_shop_id,
            String fb_shop_task_email, Integer fb_shop_task_status,
            Timestamp fb_created_at, Timestamp fb_updated_at) {
        super();
        this.fb_shop_task_id = fb_shop_task_id;
        this.fb_shop_id = fb_shop_id;
        this.fb_shop_task_email = fb_shop_task_email;
        this.fb_shop_task_status = fb_shop_task_status;
        this.fb_created_at = fb_created_at;
        this.fb_updated_at = fb_updated_at;
    }
    public Integer getFb_shop_task_id() {
        return fb_shop_task_id;
    }
    public void setFb_shop_task_id(Integer fb_shop_task_id) {
        this.fb_shop_task_id = fb_shop_task_id;
    }
    public Integer getFb_shop_id() {
        return fb_shop_id;
    }
    public void setFb_shop_id(Integer fb_shop_id) {
        this.fb_shop_id = fb_shop_id;
    }
    public String getFb_shop_task_email() {
        return fb_shop_task_email;
    }
    public void setFb_shop_task_email(String fb_shop_task_email) {
        this.fb_shop_task_email = fb_shop_task_email;
    }
    public Integer getFb_shop_task_status() {
        return fb_shop_task_status;
    }
    public void setFb_shop_task_status(Integer fb_shop_task_status) {
        this.fb_shop_task_status = fb_shop_task_status;
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
                + ((fb_shop_id == null) ? 0 : fb_shop_id.hashCode());
        result = prime
                * result
                + ((fb_shop_task_email == null) ? 0 : fb_shop_task_email
                        .hashCode());
        result = prime * result
                + ((fb_shop_task_id == null) ? 0 : fb_shop_task_id.hashCode());
        result = prime
                * result
                + ((fb_shop_task_status == null) ? 0 : fb_shop_task_status
                        .hashCode());
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
        ShopTask other = (ShopTask) obj;
        if (fb_created_at == null) {
            if (other.fb_created_at != null)
                return false;
        } else if (!fb_created_at.equals(other.fb_created_at))
            return false;
        if (fb_shop_id == null) {
            if (other.fb_shop_id != null)
                return false;
        } else if (!fb_shop_id.equals(other.fb_shop_id))
            return false;
        if (fb_shop_task_email == null) {
            if (other.fb_shop_task_email != null)
                return false;
        } else if (!fb_shop_task_email.equals(other.fb_shop_task_email))
            return false;
        if (fb_shop_task_id == null) {
            if (other.fb_shop_task_id != null)
                return false;
        } else if (!fb_shop_task_id.equals(other.fb_shop_task_id))
            return false;
        if (fb_shop_task_status == null) {
            if (other.fb_shop_task_status != null)
                return false;
        } else if (!fb_shop_task_status.equals(other.fb_shop_task_status))
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
        return "ShopTask [fb_shop_task_id=" + fb_shop_task_id + ", fb_shop_id="
                + fb_shop_id + ", fb_shop_task_email=" + fb_shop_task_email
                + ", fb_shop_task_status=" + fb_shop_task_status
                + ", fb_created_at=" + fb_created_at + ", fb_updated_at="
                + fb_updated_at + "]";
    }
    
    
}
