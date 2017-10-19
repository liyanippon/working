package model.javabean;

import java.util.List;

/**
 * Created by admin on 2017/10/9.
 */

public class ResourceGetWXExteriorProjects {

    /**
     * rows : [{"createBy":"liudongmei","createTime":1506583851714,"customerName":"恒大","delete":"0","description":"1","endTime":1506700800000,"id":"37b4d00752794688ba0d47854a022908","peopleNumber":10,"projectName":"vcm","startTime":1504195200000,"updateBy":"liudongmei","updateTime":1507510803690},{"createBy":"liudongmei","createTime":1506653839939,"customerName":"翼峰","delete":"0","description":"123","endTime":1506700800000,"id":"39fdc49b10c647c29b29acc8daa93d40","peopleNumber":10,"projectName":"erp","startTime":1504368000000,"updateBy":"liudongmei","updateTime":1506653839939}]
     * total : 2
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
         * createBy : liudongmei
         * createTime : 1506583851714
         * customerName : 恒大
         * delete : 0
         * description : 1
         * endTime : 1506700800000
         * id : 37b4d00752794688ba0d47854a022908
         * peopleNumber : 10
         * projectName : vcm
         * startTime : 1504195200000
         * updateBy : liudongmei
         * updateTime : 1507510803690
         */

        private String createBy;
        private long createTime;
        private String customerName;
        private String delete;
        private String description;
        private long endTime;
        private String id;
        private int peopleNumber;
        private String projectName;
        private long startTime;
        private String updateBy;
        private long updateTime;

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getDelete() {
            return delete;
        }

        public void setDelete(String delete) {
            this.delete = delete;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public int getPeopleNumber() {
            return peopleNumber;
        }

        public void setPeopleNumber(int peopleNumber) {
            this.peopleNumber = peopleNumber;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }
    }
}
