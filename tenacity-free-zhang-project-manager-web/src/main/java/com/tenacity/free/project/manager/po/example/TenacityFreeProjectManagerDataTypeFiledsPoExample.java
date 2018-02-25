package com.tenacity.free.project.manager.po.example;

import java.util.ArrayList;
import java.util.List;

public class TenacityFreeProjectManagerDataTypeFiledsPoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TenacityFreeProjectManagerDataTypeFiledsPoExample() {
        oredCriteria = new ArrayList<Criteria>();
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

        public Criteria andParentDatatypeIdIsNull() {
            addCriterion("parent_datatype_id is null");
            return (Criteria) this;
        }

        public Criteria andParentDatatypeIdIsNotNull() {
            addCriterion("parent_datatype_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentDatatypeIdEqualTo(Integer value) {
            addCriterion("parent_datatype_id =", value, "parentDatatypeId");
            return (Criteria) this;
        }

        public Criteria andParentDatatypeIdNotEqualTo(Integer value) {
            addCriterion("parent_datatype_id <>", value, "parentDatatypeId");
            return (Criteria) this;
        }

        public Criteria andParentDatatypeIdGreaterThan(Integer value) {
            addCriterion("parent_datatype_id >", value, "parentDatatypeId");
            return (Criteria) this;
        }

        public Criteria andParentDatatypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_datatype_id >=", value, "parentDatatypeId");
            return (Criteria) this;
        }

        public Criteria andParentDatatypeIdLessThan(Integer value) {
            addCriterion("parent_datatype_id <", value, "parentDatatypeId");
            return (Criteria) this;
        }

        public Criteria andParentDatatypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_datatype_id <=", value, "parentDatatypeId");
            return (Criteria) this;
        }

        public Criteria andParentDatatypeIdIn(List<Integer> values) {
            addCriterion("parent_datatype_id in", values, "parentDatatypeId");
            return (Criteria) this;
        }

        public Criteria andParentDatatypeIdNotIn(List<Integer> values) {
            addCriterion("parent_datatype_id not in", values, "parentDatatypeId");
            return (Criteria) this;
        }

        public Criteria andParentDatatypeIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_datatype_id between", value1, value2, "parentDatatypeId");
            return (Criteria) this;
        }

        public Criteria andParentDatatypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_datatype_id not between", value1, value2, "parentDatatypeId");
            return (Criteria) this;
        }

        public Criteria andFieldNameIsNull() {
            addCriterion("field_name is null");
            return (Criteria) this;
        }

        public Criteria andFieldNameIsNotNull() {
            addCriterion("field_name is not null");
            return (Criteria) this;
        }

        public Criteria andFieldNameEqualTo(String value) {
            addCriterion("field_name =", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotEqualTo(String value) {
            addCriterion("field_name <>", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameGreaterThan(String value) {
            addCriterion("field_name >", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameGreaterThanOrEqualTo(String value) {
            addCriterion("field_name >=", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameLessThan(String value) {
            addCriterion("field_name <", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameLessThanOrEqualTo(String value) {
            addCriterion("field_name <=", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameLike(String value) {
            addCriterion("field_name like", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotLike(String value) {
            addCriterion("field_name not like", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameIn(List<String> values) {
            addCriterion("field_name in", values, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotIn(List<String> values) {
            addCriterion("field_name not in", values, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameBetween(String value1, String value2) {
            addCriterion("field_name between", value1, value2, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotBetween(String value1, String value2) {
            addCriterion("field_name not between", value1, value2, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldAboutIsNull() {
            addCriterion("field_about is null");
            return (Criteria) this;
        }

        public Criteria andFieldAboutIsNotNull() {
            addCriterion("field_about is not null");
            return (Criteria) this;
        }

        public Criteria andFieldAboutEqualTo(String value) {
            addCriterion("field_about =", value, "fieldAbout");
            return (Criteria) this;
        }

        public Criteria andFieldAboutNotEqualTo(String value) {
            addCriterion("field_about <>", value, "fieldAbout");
            return (Criteria) this;
        }

        public Criteria andFieldAboutGreaterThan(String value) {
            addCriterion("field_about >", value, "fieldAbout");
            return (Criteria) this;
        }

        public Criteria andFieldAboutGreaterThanOrEqualTo(String value) {
            addCriterion("field_about >=", value, "fieldAbout");
            return (Criteria) this;
        }

        public Criteria andFieldAboutLessThan(String value) {
            addCriterion("field_about <", value, "fieldAbout");
            return (Criteria) this;
        }

        public Criteria andFieldAboutLessThanOrEqualTo(String value) {
            addCriterion("field_about <=", value, "fieldAbout");
            return (Criteria) this;
        }

        public Criteria andFieldAboutLike(String value) {
            addCriterion("field_about like", value, "fieldAbout");
            return (Criteria) this;
        }

        public Criteria andFieldAboutNotLike(String value) {
            addCriterion("field_about not like", value, "fieldAbout");
            return (Criteria) this;
        }

        public Criteria andFieldAboutIn(List<String> values) {
            addCriterion("field_about in", values, "fieldAbout");
            return (Criteria) this;
        }

        public Criteria andFieldAboutNotIn(List<String> values) {
            addCriterion("field_about not in", values, "fieldAbout");
            return (Criteria) this;
        }

        public Criteria andFieldAboutBetween(String value1, String value2) {
            addCriterion("field_about between", value1, value2, "fieldAbout");
            return (Criteria) this;
        }

        public Criteria andFieldAboutNotBetween(String value1, String value2) {
            addCriterion("field_about not between", value1, value2, "fieldAbout");
            return (Criteria) this;
        }

        public Criteria andFieldDatatypeIdIsNull() {
            addCriterion("field_datatype_id is null");
            return (Criteria) this;
        }

        public Criteria andFieldDatatypeIdIsNotNull() {
            addCriterion("field_datatype_id is not null");
            return (Criteria) this;
        }

        public Criteria andFieldDatatypeIdEqualTo(Integer value) {
            addCriterion("field_datatype_id =", value, "fieldDatatypeId");
            return (Criteria) this;
        }

        public Criteria andFieldDatatypeIdNotEqualTo(Integer value) {
            addCriterion("field_datatype_id <>", value, "fieldDatatypeId");
            return (Criteria) this;
        }

        public Criteria andFieldDatatypeIdGreaterThan(Integer value) {
            addCriterion("field_datatype_id >", value, "fieldDatatypeId");
            return (Criteria) this;
        }

        public Criteria andFieldDatatypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("field_datatype_id >=", value, "fieldDatatypeId");
            return (Criteria) this;
        }

        public Criteria andFieldDatatypeIdLessThan(Integer value) {
            addCriterion("field_datatype_id <", value, "fieldDatatypeId");
            return (Criteria) this;
        }

        public Criteria andFieldDatatypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("field_datatype_id <=", value, "fieldDatatypeId");
            return (Criteria) this;
        }

        public Criteria andFieldDatatypeIdIn(List<Integer> values) {
            addCriterion("field_datatype_id in", values, "fieldDatatypeId");
            return (Criteria) this;
        }

        public Criteria andFieldDatatypeIdNotIn(List<Integer> values) {
            addCriterion("field_datatype_id not in", values, "fieldDatatypeId");
            return (Criteria) this;
        }

        public Criteria andFieldDatatypeIdBetween(Integer value1, Integer value2) {
            addCriterion("field_datatype_id between", value1, value2, "fieldDatatypeId");
            return (Criteria) this;
        }

        public Criteria andFieldDatatypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("field_datatype_id not between", value1, value2, "fieldDatatypeId");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIsNull() {
            addCriterion("field_type is null");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIsNotNull() {
            addCriterion("field_type is not null");
            return (Criteria) this;
        }

        public Criteria andFieldTypeEqualTo(Byte value) {
            addCriterion("field_type =", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotEqualTo(Byte value) {
            addCriterion("field_type <>", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeGreaterThan(Byte value) {
            addCriterion("field_type >", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("field_type >=", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeLessThan(Byte value) {
            addCriterion("field_type <", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeLessThanOrEqualTo(Byte value) {
            addCriterion("field_type <=", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIn(List<Byte> values) {
            addCriterion("field_type in", values, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotIn(List<Byte> values) {
            addCriterion("field_type not in", values, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeBetween(Byte value1, Byte value2) {
            addCriterion("field_type between", value1, value2, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("field_type not between", value1, value2, "fieldType");
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