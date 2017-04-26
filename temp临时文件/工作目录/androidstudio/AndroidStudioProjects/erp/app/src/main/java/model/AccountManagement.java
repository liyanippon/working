package model;

/**
 * Created by admin on 2017/2/22.
 */

public class AccountManagement {
    private String id;
    private String type;
    private String classify;
    private String reason;
    private String sum;
    private String createBy;
    private String customerId;
    private String remark;

    public AccountManagement(String id, String type, String classify, String reason, String sum, String createBy, String customerId, String remark) {
        this.id = id;
        this.type = type;
        this.classify = classify;
        this.reason = reason;
        this.sum = sum;
        this.createBy = createBy;
        this.customerId = customerId;
        this.remark = remark;
    }
    public String getCreateBy() {
        return createBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getcreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
