package model;

import java.util.List;

/**
 * Created by admin on 2017/5/18.
 */

public class FinancialManagement {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * billClassification : 测试
         * billClassify : 出账
         * billCreateBy : liudongmei
         * billCreateTime : {"date":18,"day":4,"hours":10,"minutes":31,"month":4,"seconds":10,"time":1495074670673,"timezoneOffset":-480,"year":117}
         * billCustomerId : 测试
         * billDescription : 123
         * billSum : 333
         * billTime : {"date":18,"day":4,"hours":0,"minutes":0,"month":4,"seconds":0,"time":1495036800000,"timezoneOffset":-480,"year":117}
         * billType : 现金
         * billUpdateBy : liudongmei
         * billUpdateTime : {"date":18,"day":4,"hours":10,"minutes":31,"month":4,"seconds":10,"time":1495074670673,"timezoneOffset":-480,"year":117}
         * delete : 0
         * id : 5a495735e8b643a5ab24912b8698316d
         */

        private String billClassification;
        private String billClassify;
        private String billCreateBy;
        private BillCreateTimeBean billCreateTime;
        private String billCustomerId;
        private String billDescription;
        private float billSum;
        private BillTimeBean billTime;
        private String billType;
        private String billUpdateBy;
        private BillUpdateTimeBean billUpdateTime;
        private String delete;
        private String id;

        public String getBillClassification() {
            return billClassification;
        }

        public void setBillClassification(String billClassification) {
            this.billClassification = billClassification;
        }

        public String getBillClassify() {
            return billClassify;
        }

        public void setBillClassify(String billClassify) {
            this.billClassify = billClassify;
        }

        public String getBillCreateBy() {
            return billCreateBy;
        }

        public void setBillCreateBy(String billCreateBy) {
            this.billCreateBy = billCreateBy;
        }

        public BillCreateTimeBean getBillCreateTime() {
            return billCreateTime;
        }

        public void setBillCreateTime(BillCreateTimeBean billCreateTime) {
            this.billCreateTime = billCreateTime;
        }

        public String getBillCustomerId() {
            return billCustomerId;
        }

        public void setBillCustomerId(String billCustomerId) {
            this.billCustomerId = billCustomerId;
        }

        public String getBillDescription() {
            return billDescription;
        }

        public void setBillDescription(String billDescription) {
            this.billDescription = billDescription;
        }

        public float getBillSum() {
            return billSum;
        }

        public void setBillSum(int billSum) {
            this.billSum = billSum;
        }

        public BillTimeBean getBillTime() {
            return billTime;
        }

        public void setBillTime(BillTimeBean billTime) {
            this.billTime = billTime;
        }

        public String getBillType() {
            return billType;
        }

        public void setBillType(String billType) {
            this.billType = billType;
        }

        public String getBillUpdateBy() {
            return billUpdateBy;
        }

        public void setBillUpdateBy(String billUpdateBy) {
            this.billUpdateBy = billUpdateBy;
        }

        public BillUpdateTimeBean getBillUpdateTime() {
            return billUpdateTime;
        }

        public void setBillUpdateTime(BillUpdateTimeBean billUpdateTime) {
            this.billUpdateTime = billUpdateTime;
        }

        public String getDelete() {
            return delete;
        }

        public void setDelete(String delete) {
            this.delete = delete;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class BillCreateTimeBean {
            /**
             * date : 18
             * day : 4
             * hours : 10
             * minutes : 31
             * month : 4
             * seconds : 10
             * time : 1495074670673
             * timezoneOffset : -480
             * year : 117
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }

        public static class BillTimeBean {
            /**
             * date : 18
             * day : 4
             * hours : 0
             * minutes : 0
             * month : 4
             * seconds : 0
             * time : 1495036800000
             * timezoneOffset : -480
             * year : 117
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }

        public static class BillUpdateTimeBean {
            /**
             * date : 18
             * day : 4
             * hours : 10
             * minutes : 31
             * month : 4
             * seconds : 10
             * time : 1495074670673
             * timezoneOffset : -480
             * year : 117
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }
    }
}
