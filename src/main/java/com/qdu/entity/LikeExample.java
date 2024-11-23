package com.qdu.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LikeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LikeExample() {
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

        public Criteria andLikeidIsNull() {
            addCriterion("likeId is null");
            return (Criteria) this;
        }

        public Criteria andLikeidIsNotNull() {
            addCriterion("likeId is not null");
            return (Criteria) this;
        }

        public Criteria andLikeidEqualTo(String value) {
            addCriterion("likeId =", value, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidNotEqualTo(String value) {
            addCriterion("likeId <>", value, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidGreaterThan(String value) {
            addCriterion("likeId >", value, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidGreaterThanOrEqualTo(String value) {
            addCriterion("likeId >=", value, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidLessThan(String value) {
            addCriterion("likeId <", value, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidLessThanOrEqualTo(String value) {
            addCriterion("likeId <=", value, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidLike(String value) {
            addCriterion("likeId like", value, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidNotLike(String value) {
            addCriterion("likeId not like", value, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidIn(List<String> values) {
            addCriterion("likeId in", values, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidNotIn(List<String> values) {
            addCriterion("likeId not in", values, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidBetween(String value1, String value2) {
            addCriterion("likeId between", value1, value2, "likeid");
            return (Criteria) this;
        }

        public Criteria andLikeidNotBetween(String value1, String value2) {
            addCriterion("likeId not between", value1, value2, "likeid");
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

        public Criteria andEntityidIsNull() {
            addCriterion("entityId is null");
            return (Criteria) this;
        }

        public Criteria andEntityidIsNotNull() {
            addCriterion("entityId is not null");
            return (Criteria) this;
        }

        public Criteria andEntityidEqualTo(String value) {
            addCriterion("entityId =", value, "entityid");
            return (Criteria) this;
        }

        public Criteria andEntityidNotEqualTo(String value) {
            addCriterion("entityId <>", value, "entityid");
            return (Criteria) this;
        }

        public Criteria andEntityidGreaterThan(String value) {
            addCriterion("entityId >", value, "entityid");
            return (Criteria) this;
        }

        public Criteria andEntityidGreaterThanOrEqualTo(String value) {
            addCriterion("entityId >=", value, "entityid");
            return (Criteria) this;
        }

        public Criteria andEntityidLessThan(String value) {
            addCriterion("entityId <", value, "entityid");
            return (Criteria) this;
        }

        public Criteria andEntityidLessThanOrEqualTo(String value) {
            addCriterion("entityId <=", value, "entityid");
            return (Criteria) this;
        }

        public Criteria andEntityidLike(String value) {
            addCriterion("entityId like", value, "entityid");
            return (Criteria) this;
        }

        public Criteria andEntityidNotLike(String value) {
            addCriterion("entityId not like", value, "entityid");
            return (Criteria) this;
        }

        public Criteria andEntityidIn(List<String> values) {
            addCriterion("entityId in", values, "entityid");
            return (Criteria) this;
        }

        public Criteria andEntityidNotIn(List<String> values) {
            addCriterion("entityId not in", values, "entityid");
            return (Criteria) this;
        }

        public Criteria andEntityidBetween(String value1, String value2) {
            addCriterion("entityId between", value1, value2, "entityid");
            return (Criteria) this;
        }

        public Criteria andEntityidNotBetween(String value1, String value2) {
            addCriterion("entityId not between", value1, value2, "entityid");
            return (Criteria) this;
        }

        public Criteria andEntitytypeIsNull() {
            addCriterion("entityType is null");
            return (Criteria) this;
        }

        public Criteria andEntitytypeIsNotNull() {
            addCriterion("entityType is not null");
            return (Criteria) this;
        }

        public Criteria andEntitytypeEqualTo(String value) {
            addCriterion("entityType =", value, "entitytype");
            return (Criteria) this;
        }

        public Criteria andEntitytypeNotEqualTo(String value) {
            addCriterion("entityType <>", value, "entitytype");
            return (Criteria) this;
        }

        public Criteria andEntitytypeGreaterThan(String value) {
            addCriterion("entityType >", value, "entitytype");
            return (Criteria) this;
        }

        public Criteria andEntitytypeGreaterThanOrEqualTo(String value) {
            addCriterion("entityType >=", value, "entitytype");
            return (Criteria) this;
        }

        public Criteria andEntitytypeLessThan(String value) {
            addCriterion("entityType <", value, "entitytype");
            return (Criteria) this;
        }

        public Criteria andEntitytypeLessThanOrEqualTo(String value) {
            addCriterion("entityType <=", value, "entitytype");
            return (Criteria) this;
        }

        public Criteria andEntitytypeLike(String value) {
            addCriterion("entityType like", value, "entitytype");
            return (Criteria) this;
        }

        public Criteria andEntitytypeNotLike(String value) {
            addCriterion("entityType not like", value, "entitytype");
            return (Criteria) this;
        }

        public Criteria andEntitytypeIn(List<String> values) {
            addCriterion("entityType in", values, "entitytype");
            return (Criteria) this;
        }

        public Criteria andEntitytypeNotIn(List<String> values) {
            addCriterion("entityType not in", values, "entitytype");
            return (Criteria) this;
        }

        public Criteria andEntitytypeBetween(String value1, String value2) {
            addCriterion("entityType between", value1, value2, "entitytype");
            return (Criteria) this;
        }

        public Criteria andEntitytypeNotBetween(String value1, String value2) {
            addCriterion("entityType not between", value1, value2, "entitytype");
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
            addCriterion("createdAt =", value, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatNotEqualTo(Date value) {
            addCriterion("createdAt <>", value, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatGreaterThan(Date value) {
            addCriterion("createdAt >", value, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatGreaterThanOrEqualTo(Date value) {
            addCriterion("createdAt >=", value, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatLessThan(Date value) {
            addCriterion("createdAt <", value, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatLessThanOrEqualTo(Date value) {
            addCriterion("createdAt <=", value, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatIn(List<Date> values) {
            addCriterion("createdAt in", values, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatNotIn(List<Date> values) {
            addCriterion("createdAt not in", values, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatBetween(Date value1, Date value2) {
            addCriterion("createdAt between", value1, value2, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatNotBetween(Date value1, Date value2) {
            addCriterion("createdAt not between", value1, value2, "createdat");
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

        public Criteria andStatusEqualTo(Boolean value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Boolean> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Boolean> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("status not between", value1, value2, "status");
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