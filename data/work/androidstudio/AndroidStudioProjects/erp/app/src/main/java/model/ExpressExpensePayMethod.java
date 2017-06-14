package model;

/**
 * Created by admin on 2017/6/12.
 */

public class ExpressExpensePayMethod {

    /**
     * code : 028001
     * codeGroupCode : 028
     * comment : 支付方式:现金/银行/支付宝/微信
     * createTime : 1497235899888
     * deleted : 0
     * label : 现金
     * sortOrder : 1
     * updateTime : 1497235899888
     */

    private String code;
    private String codeGroupCode;
    private String comment;
    private long createTime;
    private String deleted;
    private String label;
    private int sortOrder;
    private long updateTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeGroupCode() {
        return codeGroupCode;
    }

    public void setCodeGroupCode(String codeGroupCode) {
        this.codeGroupCode = codeGroupCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
