package model.javabean;

/**
 * Created by admin on 2017/3/30.
 */

public class ExpressPersonMonthStatisticsXiangqing {
    private String month;
    private String sum;
    private String name_id;
    private String day;

    public ExpressPersonMonthStatisticsXiangqing(String month, String sum, String name_id, String day) {
        this.month = month;
        this.sum = sum;
        this.name_id = name_id;
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

    public String getName_id() {
        return name_id;
    }

    public void setName_id(String name_id) {
        this.name_id = name_id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
