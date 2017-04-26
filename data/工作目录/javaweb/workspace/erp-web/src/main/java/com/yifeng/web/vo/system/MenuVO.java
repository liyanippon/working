package com.yifeng.web.vo.system;

import java.util.Date;
import java.util.Set;

import com.ds.fw.vo.BaseVO;

/**
 * 菜单，只有一级、二级菜单两个层级
 * 
 * @author Administrator
 *
 */
public class MenuVO extends BaseVO implements Comparable<MenuVO> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5355392531566135493L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_menu.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_menu.menu_name
     *
     * @mbggenerated
     */
    private String menuName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_menu.parent_id
     *
     * @mbggenerated
     */
    private String parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_menu.level
     *
     * @mbggenerated
     */
    private Short level;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_menu.url
     *
     * @mbggenerated
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_menu.node_type
     *
     * @mbggenerated
     */
    private String nodeType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_menu.icon
     *
     * @mbggenerated
     */
    private String icon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_menu.status
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_menu.order
     *
     * @mbggenerated
     */
    private Short sortorder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_menu.menu_type
     *
     * @mbggenerated
     */
    private String menuType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_menu.memo
     *
     * @mbggenerated
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_menu.delete
     *
     * @mbggenerated
     */
    private String delete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_menu.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_menu.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_menu.update_by
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_menu.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;
    
    
    private Set<MenuVO> subMenu;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_menu.id
     *
     * @return the value of t_menu.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_menu.id
     *
     * @param id the value for t_menu.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_menu.menu_name
     *
     * @return the value of t_menu.menu_name
     *
     * @mbggenerated
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_menu.menu_name
     *
     * @param menuName the value for t_menu.menu_name
     *
     * @mbggenerated
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_menu.parent_id
     *
     * @return the value of t_menu.parent_id
     *
     * @mbggenerated
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_menu.parent_id
     *
     * @param parentId the value for t_menu.parent_id
     *
     * @mbggenerated
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_menu.level
     *
     * @return the value of t_menu.level
     *
     * @mbggenerated
     */
    public Short getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_menu.level
     *
     * @param level the value for t_menu.level
     *
     * @mbggenerated
     */
    public void setLevel(Short level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_menu.url
     *
     * @return the value of t_menu.url
     *
     * @mbggenerated
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_menu.url
     *
     * @param url the value for t_menu.url
     *
     * @mbggenerated
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_menu.node_type
     *
     * @return the value of t_menu.node_type
     *
     * @mbggenerated
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_menu.node_type
     *
     * @param nodeType the value for t_menu.node_type
     *
     * @mbggenerated
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType == null ? null : nodeType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_menu.icon
     *
     * @return the value of t_menu.icon
     *
     * @mbggenerated
     */
    public String getIcon() {
        return icon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_menu.icon
     *
     * @param icon the value for t_menu.icon
     *
     * @mbggenerated
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_menu.status
     *
     * @return the value of t_menu.status
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_menu.status
     *
     * @param status the value for t_menu.status
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_menu.order
     *
     * @return the value of t_menu.order
     *
     * @mbggenerated
     */
    public Short getSortorder() {
        return sortorder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_menu.order
     *
     * @param order the value for t_menu.order
     *
     * @mbggenerated
     */
    public void setSortorder(Short sortorder) {
        this.sortorder = sortorder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_menu.menu_type
     *
     * @return the value of t_menu.menu_type
     *
     * @mbggenerated
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_menu.menu_type
     *
     * @param menuType the value for t_menu.menu_type
     *
     * @mbggenerated
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_menu.memo
     *
     * @return the value of t_menu.memo
     *
     * @mbggenerated
     */
    public String getMemo() {
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_menu.memo
     *
     * @param memo the value for t_menu.memo
     *
     * @mbggenerated
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_menu.delete
     *
     * @return the value of t_menu.delete
     *
     * @mbggenerated
     */
    public String getDelete() {
        return delete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_menu.delete
     *
     * @param delete the value for t_menu.delete
     *
     * @mbggenerated
     */
    public void setDelete(String delete) {
        this.delete = delete == null ? null : delete.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_menu.create_by
     *
     * @return the value of t_menu.create_by
     *
     * @mbggenerated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_menu.create_by
     *
     * @param createBy the value for t_menu.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_menu.create_time
     *
     * @return the value of t_menu.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_menu.create_time
     *
     * @param createTime the value for t_menu.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_menu.update_by
     *
     * @return the value of t_menu.update_by
     *
     * @mbggenerated
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_menu.update_by
     *
     * @param updateBy the value for t_menu.update_by
     *
     * @mbggenerated
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_menu.update_time
     *
     * @return the value of t_menu.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_menu.update_time
     *
     * @param updateTime the value for t_menu.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public Set<MenuVO> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(Set<MenuVO> subMenu) {
        this.subMenu = subMenu;
    }

    @Override
    public int compareTo(MenuVO o) {
        if(o.getSortorder() > this.getSortorder()){
            return -1;
        }else{
            return 1;
        }
    }

    
}
