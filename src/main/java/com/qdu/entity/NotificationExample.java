package com.qdu.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class NotificationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NotificationExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andNotificationidIsNull() {
            addCriterion("notificationId is null");
            return (Criteria) this;
        }

        public Criteria andNotificationidIsNotNull() {
            addCriterion("notificationId is not null");
            return (Criteria) this;
        }

        public Criteria andNotificationidEqualTo(String value) {
            addCriterion("notificationId =", value, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidNotEqualTo(String value) {
            addCriterion("notificationId <>", value, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidGreaterThan(String value) {
            addCriterion("notificationId >", value, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidGreaterThanOrEqualTo(String value) {
            addCriterion("notificationId >=", value, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidLessThan(String value) {
            addCriterion("notificationId <", value, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidLessThanOrEqualTo(String value) {
            addCriterion("notificationId <=", value, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidLike(String value) {
            addCriterion("notificationId like", value, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidNotLike(String value) {
            addCriterion("notificationId not like", value, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidIn(List<String> values) {
            addCriterion("notificationId in", values, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidNotIn(List<String> values) {
            addCriterion("notificationId not in", values, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidBetween(String value1, String value2) {
            addCriterion("notificationId between", value1, value2, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidNotBetween(String value1, String value2) {
            addCriterion("notificationId not between", value1, value2, "notificationid");
            return (Criteria) this;
        }

        public Criteria andSenderidIsNull() {
            addCriterion("senderId is null");
            return (Criteria) this;
        }

        public Criteria andSenderidIsNotNull() {
            addCriterion("senderId is not null");
            return (Criteria) this;
        }

        public Criteria andSenderidEqualTo(String value) {
            addCriterion("senderId =", value, "senderid");
            return (Criteria) this;
        }

        public Criteria andSenderidNotEqualTo(String value) {
            addCriterion("senderId <>", value, "senderid");
            return (Criteria) this;
        }

        public Criteria andSenderidGreaterThan(String value) {
            addCriterion("senderId >", value, "senderid");
            return (Criteria) this;
        }

        public Criteria andSenderidGreaterThanOrEqualTo(String value) {
            addCriterion("senderId >=", value, "senderid");
            return (Criteria) this;
        }

        public Criteria andSenderidLessThan(String value) {
            addCriterion("senderId <", value, "senderid");
            return (Criteria) this;
        }

        public Criteria andSenderidLessThanOrEqualTo(String value) {
            addCriterion("senderId <=", value, "senderid");
            return (Criteria) this;
        }

        public Criteria andSenderidLike(String value) {
            addCriterion("senderId like", value, "senderid");
            return (Criteria) this;
        }

        public Criteria andSenderidNotLike(String value) {
            addCriterion("senderId not like", value, "senderid");
            return (Criteria) this;
        }

        public Criteria andSenderidIn(List<String> values) {
            addCriterion("senderId in", values, "senderid");
            return (Criteria) this;
        }

        public Criteria andSenderidNotIn(List<String> values) {
            addCriterion("senderId not in", values, "senderid");
            return (Criteria) this;
        }

        public Criteria andSenderidBetween(String value1, String value2) {
            addCriterion("senderId between", value1, value2, "senderid");
            return (Criteria) this;
        }

        public Criteria andSenderidNotBetween(String value1, String value2) {
            addCriterion("senderId not between", value1, value2, "senderid");
            return (Criteria) this;
        }

        public Criteria andReceiveridIsNull() {
            addCriterion("receiverId is null");
            return (Criteria) this;
        }

        public Criteria andReceiveridIsNotNull() {
            addCriterion("receiverId is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveridEqualTo(String value) {
            addCriterion("receiverId =", value, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridNotEqualTo(String value) {
            addCriterion("receiverId <>", value, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridGreaterThan(String value) {
            addCriterion("receiverId >", value, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridGreaterThanOrEqualTo(String value) {
            addCriterion("receiverId >=", value, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridLessThan(String value) {
            addCriterion("receiverId <", value, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridLessThanOrEqualTo(String value) {
            addCriterion("receiverId <=", value, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridLike(String value) {
            addCriterion("receiverId like", value, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridNotLike(String value) {
            addCriterion("receiverId not like", value, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridIn(List<String> values) {
            addCriterion("receiverId in", values, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridNotIn(List<String> values) {
            addCriterion("receiverId not in", values, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridBetween(String value1, String value2) {
            addCriterion("receiverId between", value1, value2, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridNotBetween(String value1, String value2) {
            addCriterion("receiverId not between", value1, value2, "receiverid");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeIsNull() {
            addCriterion("createdTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeIsNotNull() {
            addCriterion("createdTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeEqualTo(Date value) {
            addCriterion("createdTime =", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeNotEqualTo(Date value) {
            addCriterion("createdTime <>", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeGreaterThan(Date value) {
            addCriterion("createdTime >", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createdTime >=", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeLessThan(Date value) {
            addCriterion("createdTime <", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeLessThanOrEqualTo(Date value) {
            addCriterion("createdTime <=", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeIn(List<Date> values) {
            addCriterion("createdTime in", values, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeNotIn(List<Date> values) {
            addCriterion("createdTime not in", values, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeBetween(Date value1, Date value2) {
            addCriterion("createdTime between", value1, value2, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeNotBetween(Date value1, Date value2) {
            addCriterion("createdTime not between", value1, value2, "createdtime");
            return (Criteria) this;
        }

        public Criteria andDeletedatIsNull() {
            addCriterion("deletedAt is null");
            return (Criteria) this;
        }

        public Criteria andDeletedatIsNotNull() {
            addCriterion("deletedAt is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedatEqualTo(Date value) {
            addCriterionForJDBCDate("deletedAt =", value, "deletedat");
            return (Criteria) this;
        }

        public Criteria andDeletedatNotEqualTo(Date value) {
            addCriterionForJDBCDate("deletedAt <>", value, "deletedat");
            return (Criteria) this;
        }

        public Criteria andDeletedatGreaterThan(Date value) {
            addCriterionForJDBCDate("deletedAt >", value, "deletedat");
            return (Criteria) this;
        }

        public Criteria andDeletedatGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("deletedAt >=", value, "deletedat");
            return (Criteria) this;
        }

        public Criteria andDeletedatLessThan(Date value) {
            addCriterionForJDBCDate("deletedAt <", value, "deletedat");
            return (Criteria) this;
        }

        public Criteria andDeletedatLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("deletedAt <=", value, "deletedat");
            return (Criteria) this;
        }

        public Criteria andDeletedatIn(List<Date> values) {
            addCriterionForJDBCDate("deletedAt in", values, "deletedat");
            return (Criteria) this;
        }

        public Criteria andDeletedatNotIn(List<Date> values) {
            addCriterionForJDBCDate("deletedAt not in", values, "deletedat");
            return (Criteria) this;
        }

        public Criteria andDeletedatBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("deletedAt between", value1, value2, "deletedat");
            return (Criteria) this;
        }

        public Criteria andDeletedatNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("deletedAt not between", value1, value2, "deletedat");
            return (Criteria) this;
        }

        public Criteria andDeletionperiodIsNull() {
            addCriterion("deletionPeriod is null");
            return (Criteria) this;
        }

        public Criteria andDeletionperiodIsNotNull() {
            addCriterion("deletionPeriod is not null");
            return (Criteria) this;
        }

        public Criteria andDeletionperiodEqualTo(Integer value) {
            addCriterion("deletionPeriod =", value, "deletionperiod");
            return (Criteria) this;
        }

        public Criteria andDeletionperiodNotEqualTo(Integer value) {
            addCriterion("deletionPeriod <>", value, "deletionperiod");
            return (Criteria) this;
        }

        public Criteria andDeletionperiodGreaterThan(Integer value) {
            addCriterion("deletionPeriod >", value, "deletionperiod");
            return (Criteria) this;
        }

        public Criteria andDeletionperiodGreaterThanOrEqualTo(Integer value) {
            addCriterion("deletionPeriod >=", value, "deletionperiod");
            return (Criteria) this;
        }

        public Criteria andDeletionperiodLessThan(Integer value) {
            addCriterion("deletionPeriod <", value, "deletionperiod");
            return (Criteria) this;
        }

        public Criteria andDeletionperiodLessThanOrEqualTo(Integer value) {
            addCriterion("deletionPeriod <=", value, "deletionperiod");
            return (Criteria) this;
        }

        public Criteria andDeletionperiodIn(List<Integer> values) {
            addCriterion("deletionPeriod in", values, "deletionperiod");
            return (Criteria) this;
        }

        public Criteria andDeletionperiodNotIn(List<Integer> values) {
            addCriterion("deletionPeriod not in", values, "deletionperiod");
            return (Criteria) this;
        }

        public Criteria andDeletionperiodBetween(Integer value1, Integer value2) {
            addCriterion("deletionPeriod between", value1, value2, "deletionperiod");
            return (Criteria) this;
        }

        public Criteria andDeletionperiodNotBetween(Integer value1, Integer value2) {
            addCriterion("deletionPeriod not between", value1, value2, "deletionperiod");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

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