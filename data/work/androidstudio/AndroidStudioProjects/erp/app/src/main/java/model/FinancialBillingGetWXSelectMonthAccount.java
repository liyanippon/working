package model;

import java.math.BigDecimal;

/**
 * Created by admin on 2017/5/24.
 */

public class FinancialBillingGetWXSelectMonthAccount {


    /**
     * bill_update_by : liudongmei
     * bill_create_time : 1497235827456
     * bill_classification : <span style='color:red'><b>一证通年检续费</b><span>
     * nick_name : 刘冬梅
     * bill_type : 027002
     * sum : -90.00
     * bill_classify : 023002
     * id : <span style='color:red'><b>现金</b><span>
     * bill_customer_id : 15a237403a9c4dfba2be4e16eccdc1f4
     * bill_time : 1496764800000
     * classifyname : <span style='color:red'><b>出账</b><span>
     */

    private String bill_update_by;
    private long bill_create_time;
    private String bill_classification;
    private String nick_name;
    private String bill_type;
    private BigDecimal sum;
    private String bill_classify;
    private String id;
    private String bill_customer_id;
    private long bill_time;
    private String classifyname;

    public String getBill_update_by() {
        return bill_update_by;
    }

    public void setBill_update_by(String bill_update_by) {
        this.bill_update_by = bill_update_by;
    }

    public long getBill_create_time() {
        return bill_create_time;
    }

    public void setBill_create_time(long bill_create_time) {
        this.bill_create_time = bill_create_time;
    }

    public String getBill_classification() {
        return bill_classification;
    }

    public void setBill_classification(String bill_classification) {
        this.bill_classification = bill_classification;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getBill_type() {
        return bill_type;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getBill_classify() {
        return bill_classify;
    }

    public void setBill_classify(String bill_classify) {
        this.bill_classify = bill_classify;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBill_customer_id() {
        return bill_customer_id;
    }

    public void setBill_customer_id(String bill_customer_id) {
        this.bill_customer_id = bill_customer_id;
    }

    public long getBill_time() {
        return bill_time;
    }

    public void setBill_time(long bill_time) {
        this.bill_time = bill_time;
    }

    public String getClassifyname() {
        return classifyname;
    }

    public void setClassifyname(String classifyname) {
        this.classifyname = classifyname;
    }
}
