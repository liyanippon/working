package model;

/**
 * Created by admin on 2017/3/31.
 */

public class ExpressPieceCountMonth {
    private String month;
    private String sum;
    private String day;

    public ExpressPieceCountMonth(String month, String sum, String day) {
        this.month = month;
        this.sum = sum;
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
