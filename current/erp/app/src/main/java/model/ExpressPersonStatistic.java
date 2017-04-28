package model;

/**
 * Created by admin on 2017/3/30.
 */

public class ExpressPersonStatistic {
    private String name_id;
    private String month;
    private String name;
    private String sum;

    public ExpressPersonStatistic(String name_id, String month, String name, String sum) {
        this.name_id = name_id;
        this.month = month;
        this.name = name;
        this.sum = sum;
    }

    public String getName_id() {
        return name_id;
    }

    public void setName_id(String name_id) {
        this.name_id = name_id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
