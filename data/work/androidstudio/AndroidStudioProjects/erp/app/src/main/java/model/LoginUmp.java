package model;

import java.util.List;

/**
 * Created by admin on 2017/5/9.
 */

public class LoginUmp {


    /**
     * checked : false
     * children : [{"checked":false,"id":"0813dc3fe2fa40b5955a77fa95c0e345","parentId":"01edb238b51846a0bd0e3865755993dc","sortorder":0,"text":"系统管理员"}]
     * id : 01edb238b51846a0bd0e3865755993dc
     * sortorder : 0
     * state : open
     * text : 管理员
     */

    private boolean checked;
    private String id;
    private int sortorder;
    private String state;
    private String text;
    private List<ChildrenBean> children;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSortorder() {
        return sortorder;
    }

    public void setSortorder(int sortorder) {
        this.sortorder = sortorder;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean {
        /**
         * checked : false
         * id : 0813dc3fe2fa40b5955a77fa95c0e345
         * parentId : 01edb238b51846a0bd0e3865755993dc
         * sortorder : 0
         * text : 系统管理员
         */

        private boolean checked;
        private String id;
        private String parentId;
        private int sortorder;
        private String text;

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public int getSortorder() {
            return sortorder;
        }

        public void setSortorder(int sortorder) {
            this.sortorder = sortorder;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
