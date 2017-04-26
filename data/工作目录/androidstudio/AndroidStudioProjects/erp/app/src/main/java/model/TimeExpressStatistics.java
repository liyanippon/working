package model;

/**
 * Created by admin on 2017/3/30.
 */

public class TimeExpressStatistics {
    private String month;
    private String year;
    private String sum;

    public TimeExpressStatistics(String month, String year, String sum) {
        this.month = month;
        this.year = year;
        this.sum = sum;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
