package model.javabean;

/**
 * Created by admin on 2017/3/22.
 */

public class ExpressNumberManagement {
    private String id;
    private String expressName;
    private String type;
    private String expressCount;
    private String billingTime;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ExpressNumberManagement(String id, String expressName, String type, String expressCount, String billingTime, String remark) {
        this.id = id;
        this.expressName = expressName;
        this.type = type;
        this.expressCount = expressCount;
        this.billingTime = billingTime;
        this.remark = remark;
    }

    public ExpressNumberManagement(String id, String expressName, String type, String expressCount, String billingTime) {
        this.id = id;
        this.expressName = expressName;
        this.type = type;
        this.expressCount = expressCount;
        this.billingTime = billingTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpressCount() {
        return expressCount;
    }

    public void setExpressCount(String expressCount) {
        this.expressCount = expressCount;
    }

    public String getBillingTime() {
        return billingTime;
    }

    public void setBillingTime(String billingTime) {
        this.billingTime = billingTime;
    }
}
