package model;

import java.util.List;

/**
 * Created by admin on 2017/9/12.
 */

public class ProjectPeoplePageData {

    /**
     * rows : [{"project_user":"李延","project_id":"8f0e04e31b444c8595ea34c4cc630989","id":"1c20733ab86e4fe7853de3c0522bc1f3","join_time":1496160000000},{"project_user":"任霄宇","project_id":"8f0e04e31b444c8595ea34c4cc630989","id":"1d7ab216f02a4f99bf60a37a792630b7","join_time":1496160000000},{"project_user":"马继开","leave_time":1497887999000,"project_id":"8f0e04e31b444c8595ea34c4cc630989","id":"4281c1302e4e49e0b1f7323a41175d31","join_time":1496160000000},{"project_user":"柳超","project_id":"8f0e04e31b444c8595ea34c4cc630989","id":"58c73dfb3042450a95103bb8fd68ce13","join_time":1483200000000},{"project_user":"刘冬梅","project_id":"8f0e04e31b444c8595ea34c4cc630989","id":"cc15afe1c2424b78bfa2535d6b937484","join_time":1483200000000},{"project_user":"张琪","project_id":"8f0e04e31b444c8595ea34c4cc630989","id":"da677a752aed46f5884d91ba17369d91","join_time":1496160000000}]
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
         * project_user : 李延
         * project_id : 8f0e04e31b444c8595ea34c4cc630989
         * id : 1c20733ab86e4fe7853de3c0522bc1f3
         * join_time : 1496160000000
         * leave_time : 1497887999000
         */

        private String project_user;
        private String project_id;
        private String id;
        private long join_time;
        private long leave_time;

        public String getProject_user() {
            return project_user;
        }

        public void setProject_user(String project_user) {
            this.project_user = project_user;
        }

        public String getProject_id() {
            return project_id;
        }

        public void setProject_id(String project_id) {
            this.project_id = project_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getJoin_time() {
            return join_time;
        }

        public void setJoin_time(long join_time) {
            this.join_time = join_time;
        }

        public long getLeave_time() {
            return leave_time;
        }

        public void setLeave_time(long leave_time) {
            this.leave_time = leave_time;
        }
    }
}
