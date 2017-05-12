package model;

/**
 * Created by admin on 2017/3/3.
 */

public class CustomerBillingStatistics {
    private String customerId;
    private String month;
    private String customer;
    private String income;
    private String outcom;
    private String imbalance;

    public CustomerBillingStatistics(String customerId, String month, String customer, String income, String outcom, String imbalance) {
        this.customerId = customerId;
        this.month = month;
        this.customer = customer;
        this.income = income;
        this.outcom = outcom;
        this.imbalance = imbalance;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getOutcom() {
        return outcom;
    }

    public void setOutcom(String outcom) {
        this.outcom = outcom;
    }

    public String getImbalance() {
        return imbalance;
    }

    public void setImbalance(String imbalance) {
        this.imbalance = imbalance;
    }
}
