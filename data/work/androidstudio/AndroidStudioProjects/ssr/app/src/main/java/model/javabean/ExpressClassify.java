package model.javabean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/7/6.
 */

public class ExpressClassify implements Serializable{


    /**
     * data : [{"name":"进账","id":"023001"},{"name":"出账","id":"023002"}]
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

    public static class DataBean implements Serializable{
        /**
         * name : 进账
         * id : 023001
         */

        private String name;
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
