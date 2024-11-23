package com.qdu.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TravelActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TravelActivityExample() {
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

        public Criteria andActivityidIsNull() {
            addCriterion("activityId is null");
            return (Criteria) this;
        }

        public Criteria andActivityidIsNotNull() {
            addCriterion("activityId is not null");
            return (Criteria) this;
        }

        public Criteria andActivityidEqualTo(String value) {
            addCriterion("activityId =", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidNotEqualTo(String value) {
            addCriterion("activityId <>", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidGreaterThan(String value) {
            addCriterion("activityId >", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidGreaterThanOrEqualTo(String value) {
            addCriterion("activityId >=", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidLessThan(String value) {
            addCriterion("activityId <", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidLessThanOrEqualTo(String value) {
            addCriterion("activityId <=", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidLike(String value) {
            addCriterion("activityId like", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidNotLike(String value) {
            addCriterion("activityId not like", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidIn(List<String> values) {
            addCriterion("activityId in", values, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidNotIn(List<String> values) {
            addCriterion("activityId not in", values, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidBetween(String value1, String value2) {
            addCriterion("activityId between", value1, value2, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidNotBetween(String value1, String value2) {
            addCriterion("activityId not between", value1, value2, "activityid");
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

        public Criteria andParticipantlevelIsNull() {
            addCriterion("participantLevel is null");
            return (Criteria) this;
        }

        public Criteria andParticipantlevelIsNotNull() {
            addCriterion("participantLevel is not null");
            return (Criteria) this;
        }

        public Criteria andParticipantlevelEqualTo(Integer value) {
            addCriterion("participantLevel =", value, "participantlevel");
            return (Criteria) this;
        }

        public Criteria andParticipantlevelNotEqualTo(Integer value) {
            addCriterion("participantLevel <>", value, "participantlevel");
            return (Criteria) this;
        }

        public Criteria andParticipantlevelGreaterThan(Integer value) {
            addCriterion("participantLevel >", value, "participantlevel");
            return (Criteria) this;
        }

        public Criteria andParticipantlevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("participantLevel >=", value, "participantlevel");
            return (Criteria) this;
        }

        public Criteria andParticipantlevelLessThan(Integer value) {
            addCriterion("participantLevel <", value, "participantlevel");
            return (Criteria) this;
        }

        public Criteria andParticipantlevelLessThanOrEqualTo(Integer value) {
            addCriterion("participantLevel <=", value, "participantlevel");
            return (Criteria) this;
        }

        public Criteria andParticipantlevelIn(List<Integer> values) {
            addCriterion("participantLevel in", values, "participantlevel");
            return (Criteria) this;
        }

        public Criteria andParticipantlevelNotIn(List<Integer> values) {
            addCriterion("participantLevel not in", values, "participantlevel");
            return (Criteria) this;
        }

        public Criteria andParticipantlevelBetween(Integer value1, Integer value2) {
            addCriterion("participantLevel between", value1, value2, "participantlevel");
            return (Criteria) this;
        }

        public Criteria andParticipantlevelNotBetween(Integer value1, Integer value2) {
            addCriterion("participantLevel not between", value1, value2, "participantlevel");
            return (Criteria) this;
        }

        public Criteria andRequiredparticipantsIsNull() {
            addCriterion("requiredParticipants is null");
            return (Criteria) this;
        }

        public Criteria andRequiredparticipantsIsNotNull() {
            addCriterion("requiredParticipants is not null");
            return (Criteria) this;
        }

        public Criteria andRequiredparticipantsEqualTo(Integer value) {
            addCriterion("requiredParticipants =", value, "requiredparticipants");
            return (Criteria) this;
        }

        public Criteria andRequiredparticipantsNotEqualTo(Integer value) {
            addCriterion("requiredParticipants <>", value, "requiredparticipants");
            return (Criteria) this;
        }

        public Criteria andRequiredparticipantsGreaterThan(Integer value) {
            addCriterion("requiredParticipants >", value, "requiredparticipants");
            return (Criteria) this;
        }

        public Criteria andRequiredparticipantsGreaterThanOrEqualTo(Integer value) {
            addCriterion("requiredParticipants >=", value, "requiredparticipants");
            return (Criteria) this;
        }

        public Criteria andRequiredparticipantsLessThan(Integer value) {
            addCriterion("requiredParticipants <", value, "requiredparticipants");
            return (Criteria) this;
        }

        public Criteria andRequiredparticipantsLessThanOrEqualTo(Integer value) {
            addCriterion("requiredParticipants <=", value, "requiredparticipants");
            return (Criteria) this;
        }

        public Criteria andRequiredparticipantsIn(List<Integer> values) {
            addCriterion("requiredParticipants in", values, "requiredparticipants");
            return (Criteria) this;
        }

        public Criteria andRequiredparticipantsNotIn(List<Integer> values) {
            addCriterion("requiredParticipants not in", values, "requiredparticipants");
            return (Criteria) this;
        }

        public Criteria andRequiredparticipantsBetween(Integer value1, Integer value2) {
            addCriterion("requiredParticipants between", value1, value2, "requiredparticipants");
            return (Criteria) this;
        }

        public Criteria andRequiredparticipantsNotBetween(Integer value1, Integer value2) {
            addCriterion("requiredParticipants not between", value1, value2, "requiredparticipants");
            return (Criteria) this;
        }

        public Criteria andCurrentparticipantsIsNull() {
            addCriterion("currentParticipants is null");
            return (Criteria) this;
        }

        public Criteria andCurrentparticipantsIsNotNull() {
            addCriterion("currentParticipants is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentparticipantsEqualTo(Integer value) {
            addCriterion("currentParticipants =", value, "currentparticipants");
            return (Criteria) this;
        }

        public Criteria andCurrentparticipantsNotEqualTo(Integer value) {
            addCriterion("currentParticipants <>", value, "currentparticipants");
            return (Criteria) this;
        }

        public Criteria andCurrentparticipantsGreaterThan(Integer value) {
            addCriterion("currentParticipants >", value, "currentparticipants");
            return (Criteria) this;
        }

        public Criteria andCurrentparticipantsGreaterThanOrEqualTo(Integer value) {
            addCriterion("currentParticipants >=", value, "currentparticipants");
            return (Criteria) this;
        }

        public Criteria andCurrentparticipantsLessThan(Integer value) {
            addCriterion("currentParticipants <", value, "currentparticipants");
            return (Criteria) this;
        }

        public Criteria andCurrentparticipantsLessThanOrEqualTo(Integer value) {
            addCriterion("currentParticipants <=", value, "currentparticipants");
            return (Criteria) this;
        }

        public Criteria andCurrentparticipantsIn(List<Integer> values) {
            addCriterion("currentParticipants in", values, "currentparticipants");
            return (Criteria) this;
        }

        public Criteria andCurrentparticipantsNotIn(List<Integer> values) {
            addCriterion("currentParticipants not in", values, "currentparticipants");
            return (Criteria) this;
        }

        public Criteria andCurrentparticipantsBetween(Integer value1, Integer value2) {
            addCriterion("currentParticipants between", value1, value2, "currentparticipants");
            return (Criteria) this;
        }

        public Criteria andCurrentparticipantsNotBetween(Integer value1, Integer value2) {
            addCriterion("currentParticipants not between", value1, value2, "currentparticipants");
            return (Criteria) this;
        }

        public Criteria andStartdateIsNull() {
            addCriterion("startDate is null");
            return (Criteria) this;
        }

        public Criteria andStartdateIsNotNull() {
            addCriterion("startDate is not null");
            return (Criteria) this;
        }

        public Criteria andStartdateEqualTo(Date value) {
            addCriterionForJDBCDate("startDate =", value, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("startDate <>", value, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateGreaterThan(Date value) {
            addCriterionForJDBCDate("startDate >", value, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("startDate >=", value, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateLessThan(Date value) {
            addCriterionForJDBCDate("startDate <", value, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("startDate <=", value, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateIn(List<Date> values) {
            addCriterionForJDBCDate("startDate in", values, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("startDate not in", values, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("startDate between", value1, value2, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("startDate not between", value1, value2, "startdate");
            return (Criteria) this;
        }

        public Criteria andEnddateIsNull() {
            addCriterion("endDate is null");
            return (Criteria) this;
        }

        public Criteria andEnddateIsNotNull() {
            addCriterion("endDate is not null");
            return (Criteria) this;
        }

        public Criteria andEnddateEqualTo(Date value) {
            addCriterionForJDBCDate("endDate =", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateNotEqualTo(Date value) {
            addCriterionForJDBCDate("endDate <>", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateGreaterThan(Date value) {
            addCriterionForJDBCDate("endDate >", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("endDate >=", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateLessThan(Date value) {
            addCriterionForJDBCDate("endDate <", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("endDate <=", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateIn(List<Date> values) {
            addCriterionForJDBCDate("endDate in", values, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateNotIn(List<Date> values) {
            addCriterionForJDBCDate("endDate not in", values, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("endDate between", value1, value2, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("endDate not between", value1, value2, "enddate");
            return (Criteria) this;
        }

        public Criteria andCostIsNull() {
            addCriterion("cost is null");
            return (Criteria) this;
        }

        public Criteria andCostIsNotNull() {
            addCriterion("cost is not null");
            return (Criteria) this;
        }

        public Criteria andCostEqualTo(BigDecimal value) {
            addCriterion("cost =", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotEqualTo(BigDecimal value) {
            addCriterion("cost <>", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostGreaterThan(BigDecimal value) {
            addCriterion("cost >", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cost >=", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostLessThan(BigDecimal value) {
            addCriterion("cost <", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cost <=", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostIn(List<BigDecimal> values) {
            addCriterion("cost in", values, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotIn(List<BigDecimal> values) {
            addCriterion("cost not in", values, "cost");
            return (Criteria) this;
        }

        public Criteria andCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cost between", value1, value2, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cost not between", value1, value2, "cost");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
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

        public Criteria andRegistrationdeadlineIsNull() {
            addCriterion("RegistrationDeadline is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationdeadlineIsNotNull() {
            addCriterion("RegistrationDeadline is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationdeadlineEqualTo(Date value) {
            addCriterion("RegistrationDeadline =", value, "registrationdeadline");
            return (Criteria) this;
        }

        public Criteria andRegistrationdeadlineNotEqualTo(Date value) {
            addCriterion("RegistrationDeadline <>", value, "registrationdeadline");
            return (Criteria) this;
        }

        public Criteria andRegistrationdeadlineGreaterThan(Date value) {
            addCriterion("RegistrationDeadline >", value, "registrationdeadline");
            return (Criteria) this;
        }

        public Criteria andRegistrationdeadlineGreaterThanOrEqualTo(Date value) {
            addCriterion("RegistrationDeadline >=", value, "registrationdeadline");
            return (Criteria) this;
        }

        public Criteria andRegistrationdeadlineLessThan(Date value) {
            addCriterion("RegistrationDeadline <", value, "registrationdeadline");
            return (Criteria) this;
        }

        public Criteria andRegistrationdeadlineLessThanOrEqualTo(Date value) {
            addCriterion("RegistrationDeadline <=", value, "registrationdeadline");
            return (Criteria) this;
        }

        public Criteria andRegistrationdeadlineIn(List<Date> values) {
            addCriterion("RegistrationDeadline in", values, "registrationdeadline");
            return (Criteria) this;
        }

        public Criteria andRegistrationdeadlineNotIn(List<Date> values) {
            addCriterion("RegistrationDeadline not in", values, "registrationdeadline");
            return (Criteria) this;
        }

        public Criteria andRegistrationdeadlineBetween(Date value1, Date value2) {
            addCriterion("RegistrationDeadline between", value1, value2, "registrationdeadline");
            return (Criteria) this;
        }

        public Criteria andRegistrationdeadlineNotBetween(Date value1, Date value2) {
            addCriterion("RegistrationDeadline not between", value1, value2, "registrationdeadline");
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