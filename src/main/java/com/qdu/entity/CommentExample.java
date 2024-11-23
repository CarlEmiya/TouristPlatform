package com.qdu.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommentExample() {
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

        public Criteria andCommentidIsNull() {
            addCriterion("commentId is null");
            return (Criteria) this;
        }

        public Criteria andCommentidIsNotNull() {
            addCriterion("commentId is not null");
            return (Criteria) this;
        }

        public Criteria andCommentidEqualTo(String value) {
            addCriterion("commentId =", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidNotEqualTo(String value) {
            addCriterion("commentId <>", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidGreaterThan(String value) {
            addCriterion("commentId >", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidGreaterThanOrEqualTo(String value) {
            addCriterion("commentId >=", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidLessThan(String value) {
            addCriterion("commentId <", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidLessThanOrEqualTo(String value) {
            addCriterion("commentId <=", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidLike(String value) {
            addCriterion("commentId like", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidNotLike(String value) {
            addCriterion("commentId not like", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidIn(List<String> values) {
            addCriterion("commentId in", values, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidNotIn(List<String> values) {
            addCriterion("commentId not in", values, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidBetween(String value1, String value2) {
            addCriterion("commentId between", value1, value2, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidNotBetween(String value1, String value2) {
            addCriterion("commentId not between", value1, value2, "commentid");
            return (Criteria) this;
        }

        public Criteria andAssociatedidIsNull() {
            addCriterion("associatedId is null");
            return (Criteria) this;
        }

        public Criteria andAssociatedidIsNotNull() {
            addCriterion("associatedId is not null");
            return (Criteria) this;
        }

        public Criteria andAssociatedidEqualTo(String value) {
            addCriterion("associatedId =", value, "associatedid");
            return (Criteria) this;
        }

        public Criteria andAssociatedidNotEqualTo(String value) {
            addCriterion("associatedId <>", value, "associatedid");
            return (Criteria) this;
        }

        public Criteria andAssociatedidGreaterThan(String value) {
            addCriterion("associatedId >", value, "associatedid");
            return (Criteria) this;
        }

        public Criteria andAssociatedidGreaterThanOrEqualTo(String value) {
            addCriterion("associatedId >=", value, "associatedid");
            return (Criteria) this;
        }

        public Criteria andAssociatedidLessThan(String value) {
            addCriterion("associatedId <", value, "associatedid");
            return (Criteria) this;
        }

        public Criteria andAssociatedidLessThanOrEqualTo(String value) {
            addCriterion("associatedId <=", value, "associatedid");
            return (Criteria) this;
        }

        public Criteria andAssociatedidLike(String value) {
            addCriterion("associatedId like", value, "associatedid");
            return (Criteria) this;
        }

        public Criteria andAssociatedidNotLike(String value) {
            addCriterion("associatedId not like", value, "associatedid");
            return (Criteria) this;
        }

        public Criteria andAssociatedidIn(List<String> values) {
            addCriterion("associatedId in", values, "associatedid");
            return (Criteria) this;
        }

        public Criteria andAssociatedidNotIn(List<String> values) {
            addCriterion("associatedId not in", values, "associatedid");
            return (Criteria) this;
        }

        public Criteria andAssociatedidBetween(String value1, String value2) {
            addCriterion("associatedId between", value1, value2, "associatedid");
            return (Criteria) this;
        }

        public Criteria andAssociatedidNotBetween(String value1, String value2) {
            addCriterion("associatedId not between", value1, value2, "associatedid");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("userId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("userId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("userId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("userId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("userId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("userId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("userId like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("userId not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("userId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("userId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("userId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("userId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andCreatedatIsNull() {
            addCriterion("createdAt is null");
            return (Criteria) this;
        }

        public Criteria andCreatedatIsNotNull() {
            addCriterion("createdAt is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedatEqualTo(Date value) {
            addCriterionForJDBCDate("createdAt =", value, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatNotEqualTo(Date value) {
            addCriterionForJDBCDate("createdAt <>", value, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatGreaterThan(Date value) {
            addCriterionForJDBCDate("createdAt >", value, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createdAt >=", value, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatLessThan(Date value) {
            addCriterionForJDBCDate("createdAt <", value, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createdAt <=", value, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatIn(List<Date> values) {
            addCriterionForJDBCDate("createdAt in", values, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatNotIn(List<Date> values) {
            addCriterionForJDBCDate("createdAt not in", values, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createdAt between", value1, value2, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createdAt not between", value1, value2, "createdat");
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

        public Criteria andRateIsNull() {
            addCriterion("rate is null");
            return (Criteria) this;
        }

        public Criteria andRateIsNotNull() {
            addCriterion("rate is not null");
            return (Criteria) this;
        }

        public Criteria andRateEqualTo(Double value) {
            addCriterion("rate =", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotEqualTo(Double value) {
            addCriterion("rate <>", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThan(Double value) {
            addCriterion("rate >", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThanOrEqualTo(Double value) {
            addCriterion("rate >=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThan(Double value) {
            addCriterion("rate <", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThanOrEqualTo(Double value) {
            addCriterion("rate <=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateIn(List<Double> values) {
            addCriterion("rate in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotIn(List<Double> values) {
            addCriterion("rate not in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateBetween(Double value1, Double value2) {
            addCriterion("rate between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotBetween(Double value1, Double value2) {
            addCriterion("rate not between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andAgreeIsNull() {
            addCriterion("agree is null");
            return (Criteria) this;
        }

        public Criteria andAgreeIsNotNull() {
            addCriterion("agree is not null");
            return (Criteria) this;
        }

        public Criteria andAgreeEqualTo(Integer value) {
            addCriterion("agree =", value, "agree");
            return (Criteria) this;
        }

        public Criteria andAgreeNotEqualTo(Integer value) {
            addCriterion("agree <>", value, "agree");
            return (Criteria) this;
        }

        public Criteria andAgreeGreaterThan(Integer value) {
            addCriterion("agree >", value, "agree");
            return (Criteria) this;
        }

        public Criteria andAgreeGreaterThanOrEqualTo(Integer value) {
            addCriterion("agree >=", value, "agree");
            return (Criteria) this;
        }

        public Criteria andAgreeLessThan(Integer value) {
            addCriterion("agree <", value, "agree");
            return (Criteria) this;
        }

        public Criteria andAgreeLessThanOrEqualTo(Integer value) {
            addCriterion("agree <=", value, "agree");
            return (Criteria) this;
        }

        public Criteria andAgreeIn(List<Integer> values) {
            addCriterion("agree in", values, "agree");
            return (Criteria) this;
        }

        public Criteria andAgreeNotIn(List<Integer> values) {
            addCriterion("agree not in", values, "agree");
            return (Criteria) this;
        }

        public Criteria andAgreeBetween(Integer value1, Integer value2) {
            addCriterion("agree between", value1, value2, "agree");
            return (Criteria) this;
        }

        public Criteria andAgreeNotBetween(Integer value1, Integer value2) {
            addCriterion("agree not between", value1, value2, "agree");
            return (Criteria) this;
        }

        public Criteria andAssociatedtypeIsNull() {
            addCriterion("AssociatedType is null");
            return (Criteria) this;
        }

        public Criteria andAssociatedtypeIsNotNull() {
            addCriterion("AssociatedType is not null");
            return (Criteria) this;
        }

        public Criteria andAssociatedtypeEqualTo(String value) {
            addCriterion("AssociatedType =", value, "associatedtype");
            return (Criteria) this;
        }

        public Criteria andAssociatedtypeNotEqualTo(String value) {
            addCriterion("AssociatedType <>", value, "associatedtype");
            return (Criteria) this;
        }

        public Criteria andAssociatedtypeGreaterThan(String value) {
            addCriterion("AssociatedType >", value, "associatedtype");
            return (Criteria) this;
        }

        public Criteria andAssociatedtypeGreaterThanOrEqualTo(String value) {
            addCriterion("AssociatedType >=", value, "associatedtype");
            return (Criteria) this;
        }

        public Criteria andAssociatedtypeLessThan(String value) {
            addCriterion("AssociatedType <", value, "associatedtype");
            return (Criteria) this;
        }

        public Criteria andAssociatedtypeLessThanOrEqualTo(String value) {
            addCriterion("AssociatedType <=", value, "associatedtype");
            return (Criteria) this;
        }

        public Criteria andAssociatedtypeLike(String value) {
            addCriterion("AssociatedType like", value, "associatedtype");
            return (Criteria) this;
        }

        public Criteria andAssociatedtypeNotLike(String value) {
            addCriterion("AssociatedType not like", value, "associatedtype");
            return (Criteria) this;
        }

        public Criteria andAssociatedtypeIn(List<String> values) {
            addCriterion("AssociatedType in", values, "associatedtype");
            return (Criteria) this;
        }

        public Criteria andAssociatedtypeNotIn(List<String> values) {
            addCriterion("AssociatedType not in", values, "associatedtype");
            return (Criteria) this;
        }

        public Criteria andAssociatedtypeBetween(String value1, String value2) {
            addCriterion("AssociatedType between", value1, value2, "associatedtype");
            return (Criteria) this;
        }

        public Criteria andAssociatedtypeNotBetween(String value1, String value2) {
            addCriterion("AssociatedType not between", value1, value2, "associatedtype");
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