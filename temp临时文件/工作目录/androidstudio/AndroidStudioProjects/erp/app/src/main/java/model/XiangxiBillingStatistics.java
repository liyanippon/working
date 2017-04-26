package model;

/**
 * Created by admin on 2017/3/8.
 */

public class XiangxiBillingStatistics {
    private String classifyname;
    private String resonname;
    private String date;
    private String price;
    private String remark;

    public XiangxiBillingStatistics(String classifyname, String resonname, String date, String price, String remark) {
        this.classifyname = classifyname;
        this.resonname = resonname;
        this.date = date;
        this.price = price;
        this.remark = remark;
    }

    public String getClassifyname() {
        return classifyname;
    }

    public void setClassifyname(String classifyname) {
        this.classifyname = classifyname;
    }

    public String getResonname() {
        return resonname;
    }

    public void setResonname(String resonname) {
        this.resonname = resonname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
