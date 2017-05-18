package model;

import java.util.List;

/**
 * Created by admin on 2017/5/18.
 */

public class FinancialManagement {


    /**
     * data : [{"fy_name":"测试","fy_update_by":"liudongmei","fy_create_by":"liudongmei","fy_contact":"110","fy_create_time":{"date":15,"day":1,"hours":11,"minutes":45,"month":4,"nanos":324000000,"seconds":17,"time":1494819917324,"timezoneOffset":-480,"year":117},"fy_address":"地球","id":"09415dcc0ee54a78a9321813dc85f6ab","fy_update_time":{"date":15,"day":1,"hours":11,"minutes":45,"month":4,"nanos":325000000,"seconds":17,"time":1494819917325,"timezoneOffset":-480,"year":117},"delete":"0","fy_description":"呃"},{"fy_name":"这只是一个测试","fy_update_by":"liudongmei","fy_create_by":"liudongmei","fy_contact":"119","fy_create_time":{"date":12,"day":5,"hours":17,"minutes":39,"month":4,"nanos":242000000,"seconds":4,"time":1494581944242,"timezoneOffset":-480,"year":117},"fy_address":"中国","id":"d5848cd1635d461d8b8c566754451d62","fy_update_time":{"date":12,"day":5,"hours":17,"minutes":40,"month":4,"nanos":866000000,"seconds":31,"time":1494582031866,"timezoneOffset":-480,"year":117},"delete":"0","fy_description":"说啥。。"}]
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
         * fy_name : 测试
         * fy_update_by : liudongmei
         * fy_create_by : liudongmei
         * fy_contact : 110
         * fy_create_time : {"date":15,"day":1,"hours":11,"minutes":45,"month":4,"nanos":324000000,"seconds":17,"time":1494819917324,"timezoneOffset":-480,"year":117}
         * fy_address : 地球
         * id : 09415dcc0ee54a78a9321813dc85f6ab
         * fy_update_time : {"date":15,"day":1,"hours":11,"minutes":45,"month":4,"nanos":325000000,"seconds":17,"time":1494819917325,"timezoneOffset":-480,"year":117}
         * delete : 0
         * fy_description : 呃
         */

        private String fy_name;
        private String fy_update_by;
        private String fy_create_by;
        private String fy_contact;
        private FyCreateTimeBean fy_create_time;
        private String fy_address;
        private String id;
        private FyUpdateTimeBean fy_update_time;
        private String delete;
        private String fy_description;

        public String getFy_name() {
            return fy_name;
        }

        public void setFy_name(String fy_name) {
            this.fy_name = fy_name;
        }

        public String getFy_update_by() {
            return fy_update_by;
        }

        public void setFy_update_by(String fy_update_by) {
            this.fy_update_by = fy_update_by;
        }

        public String getFy_create_by() {
            return fy_create_by;
        }

        public void setFy_create_by(String fy_create_by) {
            this.fy_create_by = fy_create_by;
        }

        public String getFy_contact() {
            return fy_contact;
        }

        public void setFy_contact(String fy_contact) {
            this.fy_contact = fy_contact;
        }

        public FyCreateTimeBean getFy_create_time() {
            return fy_create_time;
        }

        public void setFy_create_time(FyCreateTimeBean fy_create_time) {
            this.fy_create_time = fy_create_time;
        }

        public String getFy_address() {
            return fy_address;
        }

        public void setFy_address(String fy_address) {
            this.fy_address = fy_address;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public FyUpdateTimeBean getFy_update_time() {
            return fy_update_time;
        }

        public void setFy_update_time(FyUpdateTimeBean fy_update_time) {
            this.fy_update_time = fy_update_time;
        }

        public String getDelete() {
            return delete;
        }

        public void setDelete(String delete) {
            this.delete = delete;
        }

        public String getFy_description() {
            return fy_description;
        }

        public void setFy_description(String fy_description) {
            this.fy_description = fy_description;
        }

        public static class FyCreateTimeBean {
            /**
             * date : 15
             * day : 1
             * hours : 11
             * minutes : 45
             * month : 4
             * nanos : 324000000
             * seconds : 17
             * time : 1494819917324
             * timezoneOffset : -480
             * year : 117
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int nanos;
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

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
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

        public static class FyUpdateTimeBean {
            /**
             * date : 15
             * day : 1
             * hours : 11
             * minutes : 45
             * month : 4
             * nanos : 325000000
             * seconds : 17
             * time : 1494819917325
             * timezoneOffset : -480
             * year : 117
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int nanos;
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

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
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
