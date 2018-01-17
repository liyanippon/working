package com.ifeng.entitys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DmsDocumentExample {
	protected int limit;
	protected int start;
	
    public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    public DmsDocumentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDocumentSnIsNull() {
            addCriterion("document_sn is null");
            return (Criteria) this;
        }

        public Criteria andDocumentSnIsNotNull() {
            addCriterion("document_sn is not null");
            return (Criteria) this;
        }

        public Criteria andDocumentSnEqualTo(String value) {
            addCriterion("document_sn =", value, "documentSn");
            return (Criteria) this;
        }

        public Criteria andDocumentSnNotEqualTo(String value) {
            addCriterion("document_sn <>", value, "documentSn");
            return (Criteria) this;
        }

        public Criteria andDocumentSnGreaterThan(String value) {
            addCriterion("document_sn >", value, "documentSn");
            return (Criteria) this;
        }

        public Criteria andDocumentSnGreaterThanOrEqualTo(String value) {
            addCriterion("document_sn >=", value, "documentSn");
            return (Criteria) this;
        }

        public Criteria andDocumentSnLessThan(String value) {
            addCriterion("document_sn <", value, "documentSn");
            return (Criteria) this;
        }

        public Criteria andDocumentSnLessThanOrEqualTo(String value) {
            addCriterion("document_sn <=", value, "documentSn");
            return (Criteria) this;
        }

        public Criteria andDocumentSnLike(String value) {
            addCriterion("document_sn like", value, "documentSn");
            return (Criteria) this;
        }

        public Criteria andDocumentSnNotLike(String value) {
            addCriterion("document_sn not like", value, "documentSn");
            return (Criteria) this;
        }

        public Criteria andDocumentSnIn(List<String> values) {
            addCriterion("document_sn in", values, "documentSn");
            return (Criteria) this;
        }

        public Criteria andDocumentSnNotIn(List<String> values) {
            addCriterion("document_sn not in", values, "documentSn");
            return (Criteria) this;
        }

        public Criteria andDocumentSnBetween(String value1, String value2) {
            addCriterion("document_sn between", value1, value2, "documentSn");
            return (Criteria) this;
        }

        public Criteria andDocumentSnNotBetween(String value1, String value2) {
            addCriterion("document_sn not between", value1, value2, "documentSn");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIsNull() {
            addCriterion("author_name is null");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIsNotNull() {
            addCriterion("author_name is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorNameEqualTo(String value) {
            addCriterion("author_name =", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotEqualTo(String value) {
            addCriterion("author_name <>", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameGreaterThan(String value) {
            addCriterion("author_name >", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameGreaterThanOrEqualTo(String value) {
            addCriterion("author_name >=", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLessThan(String value) {
            addCriterion("author_name <", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLessThanOrEqualTo(String value) {
            addCriterion("author_name <=", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLike(String value) {
            addCriterion("author_name like", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotLike(String value) {
            addCriterion("author_name not like", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIn(List<String> values) {
            addCriterion("author_name in", values, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotIn(List<String> values) {
            addCriterion("author_name not in", values, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameBetween(String value1, String value2) {
            addCriterion("author_name between", value1, value2, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotBetween(String value1, String value2) {
            addCriterion("author_name not between", value1, value2, "authorName");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDocumentNameIsNull() {
            addCriterion("document_name is null");
            return (Criteria) this;
        }

        public Criteria andDocumentNameIsNotNull() {
            addCriterion("document_name is not null");
            return (Criteria) this;
        }

        public Criteria andDocumentNameEqualTo(String value) {
            addCriterion("document_name =", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameNotEqualTo(String value) {
            addCriterion("document_name <>", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameGreaterThan(String value) {
            addCriterion("document_name >", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameGreaterThanOrEqualTo(String value) {
            addCriterion("document_name >=", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameLessThan(String value) {
            addCriterion("document_name <", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameLessThanOrEqualTo(String value) {
            addCriterion("document_name <=", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameLike(String value) {
            addCriterion("document_name like", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameNotLike(String value) {
            addCriterion("document_name not like", value, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameIn(List<String> values) {
            addCriterion("document_name in", values, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameNotIn(List<String> values) {
            addCriterion("document_name not in", values, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameBetween(String value1, String value2) {
            addCriterion("document_name between", value1, value2, "documentName");
            return (Criteria) this;
        }

        public Criteria andDocumentNameNotBetween(String value1, String value2) {
            addCriterion("document_name not between", value1, value2, "documentName");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andHeadimgsrcIsNull() {
            addCriterion("headimgsrc is null");
            return (Criteria) this;
        }

        public Criteria andHeadimgsrcIsNotNull() {
            addCriterion("headimgsrc is not null");
            return (Criteria) this;
        }

        public Criteria andHeadimgsrcEqualTo(String value) {
            addCriterion("headimgsrc =", value, "headimgsrc");
            return (Criteria) this;
        }

        public Criteria andHeadimgsrcNotEqualTo(String value) {
            addCriterion("headimgsrc <>", value, "headimgsrc");
            return (Criteria) this;
        }

        public Criteria andHeadimgsrcGreaterThan(String value) {
            addCriterion("headimgsrc >", value, "headimgsrc");
            return (Criteria) this;
        }

        public Criteria andHeadimgsrcGreaterThanOrEqualTo(String value) {
            addCriterion("headimgsrc >=", value, "headimgsrc");
            return (Criteria) this;
        }

        public Criteria andHeadimgsrcLessThan(String value) {
            addCriterion("headimgsrc <", value, "headimgsrc");
            return (Criteria) this;
        }

        public Criteria andHeadimgsrcLessThanOrEqualTo(String value) {
            addCriterion("headimgsrc <=", value, "headimgsrc");
            return (Criteria) this;
        }

        public Criteria andHeadimgsrcLike(String value) {
            addCriterion("headimgsrc like", value, "headimgsrc");
            return (Criteria) this;
        }

        public Criteria andHeadimgsrcNotLike(String value) {
            addCriterion("headimgsrc not like", value, "headimgsrc");
            return (Criteria) this;
        }

        public Criteria andHeadimgsrcIn(List<String> values) {
            addCriterion("headimgsrc in", values, "headimgsrc");
            return (Criteria) this;
        }

        public Criteria andHeadimgsrcNotIn(List<String> values) {
            addCriterion("headimgsrc not in", values, "headimgsrc");
            return (Criteria) this;
        }

        public Criteria andHeadimgsrcBetween(String value1, String value2) {
            addCriterion("headimgsrc between", value1, value2, "headimgsrc");
            return (Criteria) this;
        }

        public Criteria andHeadimgsrcNotBetween(String value1, String value2) {
            addCriterion("headimgsrc not between", value1, value2, "headimgsrc");
            return (Criteria) this;
        }

        public Criteria andFilesrcIsNull() {
            addCriterion("filesrc is null");
            return (Criteria) this;
        }

        public Criteria andFilesrcIsNotNull() {
            addCriterion("filesrc is not null");
            return (Criteria) this;
        }

        public Criteria andFilesrcEqualTo(String value) {
            addCriterion("filesrc =", value, "filesrc");
            return (Criteria) this;
        }

        public Criteria andFilesrcNotEqualTo(String value) {
            addCriterion("filesrc <>", value, "filesrc");
            return (Criteria) this;
        }

        public Criteria andFilesrcGreaterThan(String value) {
            addCriterion("filesrc >", value, "filesrc");
            return (Criteria) this;
        }

        public Criteria andFilesrcGreaterThanOrEqualTo(String value) {
            addCriterion("filesrc >=", value, "filesrc");
            return (Criteria) this;
        }

        public Criteria andFilesrcLessThan(String value) {
            addCriterion("filesrc <", value, "filesrc");
            return (Criteria) this;
        }

        public Criteria andFilesrcLessThanOrEqualTo(String value) {
            addCriterion("filesrc <=", value, "filesrc");
            return (Criteria) this;
        }

        public Criteria andFilesrcLike(String value) {
            addCriterion("filesrc like", value, "filesrc");
            return (Criteria) this;
        }

        public Criteria andFilesrcNotLike(String value) {
            addCriterion("filesrc not like", value, "filesrc");
            return (Criteria) this;
        }

        public Criteria andFilesrcIn(List<String> values) {
            addCriterion("filesrc in", values, "filesrc");
            return (Criteria) this;
        }

        public Criteria andFilesrcNotIn(List<String> values) {
            addCriterion("filesrc not in", values, "filesrc");
            return (Criteria) this;
        }

        public Criteria andFilesrcBetween(String value1, String value2) {
            addCriterion("filesrc between", value1, value2, "filesrc");
            return (Criteria) this;
        }

        public Criteria andFilesrcNotBetween(String value1, String value2) {
            addCriterion("filesrc not between", value1, value2, "filesrc");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table dms_document
     *
     * @mbg.generated do_not_delete_during_merge Wed Jan 17 11:21:59 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}