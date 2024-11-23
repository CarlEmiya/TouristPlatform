package com.qdu.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReportExample() {
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

        public Criteria andReportidIsNull() {
            addCriterion("reportId is null");
            return (Criteria) this;
        }

        public Criteria andReportidIsNotNull() {
            addCriterion("reportId is not null");
            return (Criteria) this;
        }

        public Criteria andReportidEqualTo(String value) {
            addCriterion("reportId =", value, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidNotEqualTo(String value) {
            addCriterion("reportId <>", value, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidGreaterThan(String value) {
            addCriterion("reportId >", value, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidGreaterThanOrEqualTo(String value) {
            addCriterion("reportId >=", value, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidLessThan(String value) {
            addCriterion("reportId <", value, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidLessThanOrEqualTo(String value) {
            addCriterion("reportId <=", value, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidLike(String value) {
            addCriterion("reportId like", value, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidNotLike(String value) {
            addCriterion("reportId not like", value, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidIn(List<String> values) {
            addCriterion("reportId in", values, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidNotIn(List<String> values) {
            addCriterion("reportId not in", values, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidBetween(String value1, String value2) {
            addCriterion("reportId between", value1, value2, "reportid");
            return (Criteria) this;
        }

        public Criteria andReportidNotBetween(String value1, String value2) {
            addCriterion("reportId not between", value1, value2, "reportid");
            return (Criteria) this;
        }

        public Criteria andReporteridIsNull() {
            addCriterion("reporterId is null");
            return (Criteria) this;
        }

        public Criteria andReporteridIsNotNull() {
            addCriterion("reporterId is not null");
            return (Criteria) this;
        }

        public Criteria andReporteridEqualTo(String value) {
            addCriterion("reporterId =", value, "reporterid");
            return (Criteria) this;
        }

        public Criteria andReporteridNotEqualTo(String value) {
            addCriterion("reporterId <>", value, "reporterid");
            return (Criteria) this;
        }

        public Criteria andReporteridGreaterThan(String value) {
            addCriterion("reporterId >", value, "reporterid");
            return (Criteria) this;
        }

        public Criteria andReporteridGreaterThanOrEqualTo(String value) {
            addCriterion("reporterId >=", value, "reporterid");
            return (Criteria) this;
        }

        public Criteria andReporteridLessThan(String value) {
            addCriterion("reporterId <", value, "reporterid");
            return (Criteria) this;
        }

        public Criteria andReporteridLessThanOrEqualTo(String value) {
            addCriterion("reporterId <=", value, "reporterid");
            return (Criteria) this;
        }

        public Criteria andReporteridLike(String value) {
            addCriterion("reporterId like", value, "reporterid");
            return (Criteria) this;
        }

        public Criteria andReporteridNotLike(String value) {
            addCriterion("reporterId not like", value, "reporterid");
            return (Criteria) this;
        }

        public Criteria andReporteridIn(List<String> values) {
            addCriterion("reporterId in", values, "reporterid");
            return (Criteria) this;
        }

        public Criteria andReporteridNotIn(List<String> values) {
            addCriterion("reporterId not in", values, "reporterid");
            return (Criteria) this;
        }

        public Criteria andReporteridBetween(String value1, String value2) {
            addCriterion("reporterId between", value1, value2, "reporterid");
            return (Criteria) this;
        }

        public Criteria andReporteridNotBetween(String value1, String value2) {
            addCriterion("reporterId not between", value1, value2, "reporterid");
            return (Criteria) this;
        }

        public Criteria andReportedidIsNull() {
            addCriterion("reportedId is null");
            return (Criteria) this;
        }

        public Criteria andReportedidIsNotNull() {
            addCriterion("reportedId is not null");
            return (Criteria) this;
        }

        public Criteria andReportedidEqualTo(String value) {
            addCriterion("reportedId =", value, "reportedid");
            return (Criteria) this;
        }

        public Criteria andReportedidNotEqualTo(String value) {
            addCriterion("reportedId <>", value, "reportedid");
            return (Criteria) this;
        }

        public Criteria andReportedidGreaterThan(String value) {
            addCriterion("reportedId >", value, "reportedid");
            return (Criteria) this;
        }

        public Criteria andReportedidGreaterThanOrEqualTo(String value) {
            addCriterion("reportedId >=", value, "reportedid");
            return (Criteria) this;
        }

        public Criteria andReportedidLessThan(String value) {
            addCriterion("reportedId <", value, "reportedid");
            return (Criteria) this;
        }

        public Criteria andReportedidLessThanOrEqualTo(String value) {
            addCriterion("reportedId <=", value, "reportedid");
            return (Criteria) this;
        }

        public Criteria andReportedidLike(String value) {
            addCriterion("reportedId like", value, "reportedid");
            return (Criteria) this;
        }

        public Criteria andReportedidNotLike(String value) {
            addCriterion("reportedId not like", value, "reportedid");
            return (Criteria) this;
        }

        public Criteria andReportedidIn(List<String> values) {
            addCriterion("reportedId in", values, "reportedid");
            return (Criteria) this;
        }

        public Criteria andReportedidNotIn(List<String> values) {
            addCriterion("reportedId not in", values, "reportedid");
            return (Criteria) this;
        }

        public Criteria andReportedidBetween(String value1, String value2) {
            addCriterion("reportedId between", value1, value2, "reportedid");
            return (Criteria) this;
        }

        public Criteria andReportedidNotBetween(String value1, String value2) {
            addCriterion("reportedId not between", value1, value2, "reportedid");
            return (Criteria) this;
        }

        public Criteria andReportedtypeIsNull() {
            addCriterion("reportedType is null");
            return (Criteria) this;
        }

        public Criteria andReportedtypeIsNotNull() {
            addCriterion("reportedType is not null");
            return (Criteria) this;
        }

        public Criteria andReportedtypeEqualTo(String value) {
            addCriterion("reportedType =", value, "reportedtype");
            return (Criteria) this;
        }

        public Criteria andReportedtypeNotEqualTo(String value) {
            addCriterion("reportedType <>", value, "reportedtype");
            return (Criteria) this;
        }

        public Criteria andReportedtypeGreaterThan(String value) {
            addCriterion("reportedType >", value, "reportedtype");
            return (Criteria) this;
        }

        public Criteria andReportedtypeGreaterThanOrEqualTo(String value) {
            addCriterion("reportedType >=", value, "reportedtype");
            return (Criteria) this;
        }

        public Criteria andReportedtypeLessThan(String value) {
            addCriterion("reportedType <", value, "reportedtype");
            return (Criteria) this;
        }

        public Criteria andReportedtypeLessThanOrEqualTo(String value) {
            addCriterion("reportedType <=", value, "reportedtype");
            return (Criteria) this;
        }

        public Criteria andReportedtypeLike(String value) {
            addCriterion("reportedType like", value, "reportedtype");
            return (Criteria) this;
        }

        public Criteria andReportedtypeNotLike(String value) {
            addCriterion("reportedType not like", value, "reportedtype");
            return (Criteria) this;
        }

        public Criteria andReportedtypeIn(List<String> values) {
            addCriterion("reportedType in", values, "reportedtype");
            return (Criteria) this;
        }

        public Criteria andReportedtypeNotIn(List<String> values) {
            addCriterion("reportedType not in", values, "reportedtype");
            return (Criteria) this;
        }

        public Criteria andReportedtypeBetween(String value1, String value2) {
            addCriterion("reportedType between", value1, value2, "reportedtype");
            return (Criteria) this;
        }

        public Criteria andReportedtypeNotBetween(String value1, String value2) {
            addCriterion("reportedType not between", value1, value2, "reportedtype");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
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

        public Criteria andHandleridIsNull() {
            addCriterion("handlerId is null");
            return (Criteria) this;
        }

        public Criteria andHandleridIsNotNull() {
            addCriterion("handlerId is not null");
            return (Criteria) this;
        }

        public Criteria andHandleridEqualTo(String value) {
            addCriterion("handlerId =", value, "handlerid");
            return (Criteria) this;
        }

        public Criteria andHandleridNotEqualTo(String value) {
            addCriterion("handlerId <>", value, "handlerid");
            return (Criteria) this;
        }

        public Criteria andHandleridGreaterThan(String value) {
            addCriterion("handlerId >", value, "handlerid");
            return (Criteria) this;
        }

        public Criteria andHandleridGreaterThanOrEqualTo(String value) {
            addCriterion("handlerId >=", value, "handlerid");
            return (Criteria) this;
        }

        public Criteria andHandleridLessThan(String value) {
            addCriterion("handlerId <", value, "handlerid");
            return (Criteria) this;
        }

        public Criteria andHandleridLessThanOrEqualTo(String value) {
            addCriterion("handlerId <=", value, "handlerid");
            return (Criteria) this;
        }

        public Criteria andHandleridLike(String value) {
            addCriterion("handlerId like", value, "handlerid");
            return (Criteria) this;
        }

        public Criteria andHandleridNotLike(String value) {
            addCriterion("handlerId not like", value, "handlerid");
            return (Criteria) this;
        }

        public Criteria andHandleridIn(List<String> values) {
            addCriterion("handlerId in", values, "handlerid");
            return (Criteria) this;
        }

        public Criteria andHandleridNotIn(List<String> values) {
            addCriterion("handlerId not in", values, "handlerid");
            return (Criteria) this;
        }

        public Criteria andHandleridBetween(String value1, String value2) {
            addCriterion("handlerId between", value1, value2, "handlerid");
            return (Criteria) this;
        }

        public Criteria andHandleridNotBetween(String value1, String value2) {
            addCriterion("handlerId not between", value1, value2, "handlerid");
            return (Criteria) this;
        }

        public Criteria andHandledatIsNull() {
            addCriterion("handledAt is null");
            return (Criteria) this;
        }

        public Criteria andHandledatIsNotNull() {
            addCriterion("handledAt is not null");
            return (Criteria) this;
        }

        public Criteria andHandledatEqualTo(Date value) {
            addCriterionForJDBCDate("handledAt =", value, "handledat");
            return (Criteria) this;
        }

        public Criteria andHandledatNotEqualTo(Date value) {
            addCriterionForJDBCDate("handledAt <>", value, "handledat");
            return (Criteria) this;
        }

        public Criteria andHandledatGreaterThan(Date value) {
            addCriterionForJDBCDate("handledAt >", value, "handledat");
            return (Criteria) this;
        }

        public Criteria andHandledatGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("handledAt >=", value, "handledat");
            return (Criteria) this;
        }

        public Criteria andHandledatLessThan(Date value) {
            addCriterionForJDBCDate("handledAt <", value, "handledat");
            return (Criteria) this;
        }

        public Criteria andHandledatLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("handledAt <=", value, "handledat");
            return (Criteria) this;
        }

        public Criteria andHandledatIn(List<Date> values) {
            addCriterionForJDBCDate("handledAt in", values, "handledat");
            return (Criteria) this;
        }

        public Criteria andHandledatNotIn(List<Date> values) {
            addCriterionForJDBCDate("handledAt not in", values, "handledat");
            return (Criteria) this;
        }

        public Criteria andHandledatBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("handledAt between", value1, value2, "handledat");
            return (Criteria) this;
        }

        public Criteria andHandledatNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("handledAt not between", value1, value2, "handledat");
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