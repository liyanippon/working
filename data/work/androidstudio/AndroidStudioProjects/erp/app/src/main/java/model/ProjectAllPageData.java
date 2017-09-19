package model;

import java.util.List;

/**
 * Created by admin on 2017/9/12.
 */

public class ProjectAllPageData {

    /**
     * rows : [{"delete":"0","endTime":1514649600000,"id":"8f0e04e31b444c8595ea34c4cc630989","mome":"人事部日常考勤","projectCycle":"1","projectManager":"刘冬梅","projectName":"人事部考勤","projectPeople":"","startTime":1483200000000,"stu":"017001","sum":23,"yjendTime":1484532600751,"yjstartTime":1484532600751},{"delete":"0","endTime":1516032000000,"id":"50927114bc3c4fc7a5e4656ffc267bf6","mome":"自动贩卖机项目","projectCycle":"3","projectManager":"赵雪田","projectName":"自动贩卖机项目","projectPeople":"","startTime":1484582400000,"stu":"017001","sum":2,"yjendTime":1484704395832,"yjstartTime":1484704395832},{"delete":"0","endTime":1485792000000,"id":"b8f9da13246e4f2e9a62a17ff377e3a4","mome":"开发公司内部ERP项目","projectManager":"刘冬梅","projectName":"公司内部ERP项目","projectPeople":"majikai","startTime":1482422400000,"stu":"017001","yjendTime":1486444907427,"yjstartTime":1482477986377},{"delete":"0","endTime":1514736000000,"id":"d717f5c7f316439992c28061b03124a0","mome":"物流考勤","projectManager":"刘冬梅","projectName":"物流考勤","projectPeople":"","startTime":1488297600000,"stu":"017001","yjendTime":1493171869457,"yjstartTime":1493171869457},{"delete":"0","endTime":1506700800000,"id":"c071ccbf6d5b44efb20cd2f4de7ac1b2","mome":"测试1234567890","projectCycle":"2","projectManager":"刘冬梅","projectName":"测试","projectPeople":"","startTime":1505059200000,"stu":"017001","sum":123,"yjendTime":1505115760480,"yjstartTime":1505115760480},{"delete":"0","endTime":1505318400000,"id":"3ed8d358a5bd46079d6b6f02291ccfb8","mome":"","projectCycle":"5","projectManager":"刘冬梅","projectName":"暗暗","projectPeople":"","startTime":1505059200000,"stu":"017001","sum":11,"yjendTime":1505122704280,"yjstartTime":1505122704280}]
     * total : 6
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * delete : 0
         * endTime : 1514649600000
         * id : 8f0e04e31b444c8595ea34c4cc630989
         * mome : 人事部日常考勤
         * projectCycle : 1
         * projectManager : 刘冬梅
         * projectName : 人事部考勤
         * projectPeople :
         * startTime : 1483200000000
         * stu : 017001
         * sum : 23.0
         * yjendTime : 1484532600751
         * yjstartTime : 1484532600751
         */

        private String delete;
        private long endTime;
        private String id;
        private String mome;
        private String projectCycle;
        private String projectManager;
        private String projectName;
        private String projectPeople;
        private long startTime;
        private String stu;
        private double sum;
        private long yjendTime;
        private long yjstartTime;

        public String getDelete() {
            return delete;
        }

        public void setDelete(String delete) {
            this.delete = delete;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMome() {
            return mome;
        }

        public void setMome(String mome) {
            this.mome = mome;
        }

        public String getProjectCycle() {
            return projectCycle;
        }

        public void setProjectCycle(String projectCycle) {
            this.projectCycle = projectCycle;
        }

        public String getProjectManager() {
            return projectManager;
        }

        public void setProjectManager(String projectManager) {
            this.projectManager = projectManager;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getProjectPeople() {
            return projectPeople;
        }

        public void setProjectPeople(String projectPeople) {
            this.projectPeople = projectPeople;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public String getStu() {
            return stu;
        }

        public void setStu(String stu) {
            this.stu = stu;
        }

        public double getSum() {
            return sum;
        }

        public void setSum(double sum) {
            this.sum = sum;
        }

        public long getYjendTime() {
            return yjendTime;
        }

        public void setYjendTime(long yjendTime) {
            this.yjendTime = yjendTime;
        }

        public long getYjstartTime() {
            return yjstartTime;
        }

        public void setYjstartTime(long yjstartTime) {
            this.yjstartTime = yjstartTime;
        }
    }
}
