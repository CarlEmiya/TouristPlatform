package com.qdu.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TravelDiaryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TravelDiaryExample() {
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

        public Criteria andDiaryidIsNull() {
            addCriterion("diaryId is null");
            return (Criteria) this;
        }

        public Criteria andDiaryidIsNotNull() {
            addCriterion("diaryId is not null");
            return (Criteria) this;
        }

        public Criteria andDiaryidEqualTo(String value) {
            addCriterion("diaryId =", value, "diaryid");
            return (Criteria) this;
        }

        public Criteria andDiaryidNotEqualTo(String value) {
            addCriterion("diaryId <>", value, "diaryid");
            return (Criteria) this;
        }

        public Criteria andDiaryidGreaterThan(String value) {
            addCriterion("diaryId >", value, "diaryid");
            return (Criteria) this;
        }

        public Criteria andDiaryidGreaterThanOrEqualTo(String value) {
            addCriterion("diaryId >=", value, "diaryid");
            return (Criteria) this;
        }

        public Criteria andDiaryidLessThan(String value) {
            addCriterion("diaryId <", value, "diaryid");
            return (Criteria) this;
        }

        public Criteria andDiaryidLessThanOrEqualTo(String value) {
            addCriterion("diaryId <=", value, "diaryid");
            return (Criteria) this;
        }

        public Criteria andDiaryidLike(String value) {
            addCriterion("diaryId like", value, "diaryid");
            return (Criteria) this;
        }

        public Criteria andDiaryidNotLike(String value) {
            addCriterion("diaryId not like", value, "diaryid");
            return (Criteria) this;
        }

        public Criteria andDiaryidIn(List<String> values) {
            addCriterion("diaryId in", values, "diaryid");
            return (Criteria) this;
        }

        public Criteria andDiaryidNotIn(List<String> values) {
            addCriterion("diaryId not in", values, "diaryid");
            return (Criteria) this;
        }

        public Criteria andDiaryidBetween(String value1, String value2) {
            addCriterion("diaryId between", value1, value2, "diaryid");
            return (Criteria) this;
        }

        public Criteria andDiaryidNotBetween(String value1, String value2) {
            addCriterion("diaryId not between", value1, value2, "diaryid");
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

        public Criteria andIscommentabledIsNull() {
            addCriterion("IsCommentAbled is null");
            return (Criteria) this;
        }

        public Criteria andIscommentabledIsNotNull() {
            addCriterion("IsCommentAbled is not null");
            return (Criteria) this;
        }

        public Criteria andIscommentabledEqualTo(Boolean value) {
            addCriterion("IsCommentAbled =", value, "iscommentabled");
            return (Criteria) this;
        }

        public Criteria andIscommentabledNotEqualTo(Boolean value) {
            addCriterion("IsCommentAbled <>", value, "iscommentabled");
            return (Criteria) this;
        }

        public Criteria andIscommentabledGreaterThan(Boolean value) {
            addCriterion("IsCommentAbled >", value, "iscommentabled");
            return (Criteria) this;
        }

        public Criteria andIscommentabledGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsCommentAbled >=", value, "iscommentabled");
            return (Criteria) this;
        }

        public Criteria andIscommentabledLessThan(Boolean value) {
            addCriterion("IsCommentAbled <", value, "iscommentabled");
            return (Criteria) this;
        }

        public Criteria andIscommentabledLessThanOrEqualTo(Boolean value) {
            addCriterion("IsCommentAbled <=", value, "iscommentabled");
            return (Criteria) this;
        }

        public Criteria andIscommentabledIn(List<Boolean> values) {
            addCriterion("IsCommentAbled in", values, "iscommentabled");
            return (Criteria) this;
        }

        public Criteria andIscommentabledNotIn(List<Boolean> values) {
            addCriterion("IsCommentAbled not in", values, "iscommentabled");
            return (Criteria) this;
        }

        public Criteria andIscommentabledBetween(Boolean value1, Boolean value2) {
            addCriterion("IsCommentAbled between", value1, value2, "iscommentabled");
            return (Criteria) this;
        }

        public Criteria andIscommentabledNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsCommentAbled not between", value1, value2, "iscommentabled");
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