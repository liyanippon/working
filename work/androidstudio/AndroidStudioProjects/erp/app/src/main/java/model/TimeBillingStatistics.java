package model;

/**
 * Created by admin on 2017/3/2.
 */

public class TimeBillingStatistics {
    private String month;
    private String income;
    private String outcom;
    private String imbalance;

    public TimeBillingStatistics(String month, String income, String outcom, String imbalance) {
        this.month = month;
        this.income = income;
        this.outcom = outcom;
        this.imbalance = imbalance;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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
