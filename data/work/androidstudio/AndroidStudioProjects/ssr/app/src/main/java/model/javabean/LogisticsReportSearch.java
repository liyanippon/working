package model.javabean;

import java.util.List;

/**
 * Created by admin on 2017/7/19.
 */

public class LogisticsReportSearch {

    /**
     * data : [{"jz1":26566,"ce":-100,"cz1":26666,"ye":2017,"mon":7},{"jz1":39659,"ce":18670,"cz1":20989,"ye":2017,"mon":6},{"jz1":41271,"ce":-24450,"cz1":65721,"ye":2017,"mon":5},{"jz1":53166,"ce":-10692,"cz1":63858,"ye":2017,"mon":4},{"jz1":54286,"ce":-6960,"cz1":61246,"ye":2017,"mon":3},{"jz1":33309,"ce":-11380,"cz1":44689,"ye":2017,"mon":2},{"jz1":16014,"ce":-11127,"cz1":27141,"ye":2017,"mon":1}]
     * message : 成功！
     * status : true
     */

    private String message;
    private boolean status;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * jz1 : 26566
         * ce : -100
         * cz1 : 26666
         * ye : 2017
         * mon : 7
         */

        private int jz1;
        private int ce;
        private int cz1;
        private int ye;
        private int mon;

        public int getJz1() {
            return jz1;
        }

        public void setJz1(int jz1) {
            this.jz1 = jz1;
        }

        public int getCe() {
            return ce;
        }

        public void setCe(int ce) {
            this.ce = ce;
        }

        public int getCz1() {
            return cz1;
        }

        public void setCz1(int cz1) {
            this.cz1 = cz1;
        }

        public int getYe() {
            return ye;
        }

        public void setYe(int ye) {
            this.ye = ye;
        }

        public int getMon() {
            return mon;
        }

        public void setMon(int mon) {
            this.mon = mon;
        }
    }
}
