package fxyh.crewler.bean;

import java.util.ArrayList;
import java.util.List;

public class NewsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    private Boolean forUpdate;

    public NewsExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    public void setForUpdate(Boolean forUpdate) {
        this.forUpdate = forUpdate;
    }

    public Boolean getForUpdate() {
        return forUpdate;
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

        public Criteria andNewsMd5IsNull() {
            addCriterion("news_md5 is null");
            return (Criteria) this;
        }

        public Criteria andNewsMd5IsNotNull() {
            addCriterion("news_md5 is not null");
            return (Criteria) this;
        }

        public Criteria andNewsMd5EqualTo(String value) {
            addCriterion("news_md5 =", value, "newsMd5");
            return (Criteria) this;
        }

        public Criteria andNewsMd5NotEqualTo(String value) {
            addCriterion("news_md5 <>", value, "newsMd5");
            return (Criteria) this;
        }

        public Criteria andNewsMd5GreaterThan(String value) {
            addCriterion("news_md5 >", value, "newsMd5");
            return (Criteria) this;
        }

        public Criteria andNewsMd5GreaterThanOrEqualTo(String value) {
            addCriterion("news_md5 >=", value, "newsMd5");
            return (Criteria) this;
        }

        public Criteria andNewsMd5LessThan(String value) {
            addCriterion("news_md5 <", value, "newsMd5");
            return (Criteria) this;
        }

        public Criteria andNewsMd5LessThanOrEqualTo(String value) {
            addCriterion("news_md5 <=", value, "newsMd5");
            return (Criteria) this;
        }

        public Criteria andNewsMd5Like(String value) {
            addCriterion("news_md5 like", value, "newsMd5");
            return (Criteria) this;
        }

        public Criteria andNewsMd5NotLike(String value) {
            addCriterion("news_md5 not like", value, "newsMd5");
            return (Criteria) this;
        }

        public Criteria andNewsMd5In(List<String> values) {
            addCriterion("news_md5 in", values, "newsMd5");
            return (Criteria) this;
        }

        public Criteria andNewsMd5NotIn(List<String> values) {
            addCriterion("news_md5 not in", values, "newsMd5");
            return (Criteria) this;
        }

        public Criteria andNewsMd5Between(String value1, String value2) {
            addCriterion("news_md5 between", value1, value2, "newsMd5");
            return (Criteria) this;
        }

        public Criteria andNewsMd5NotBetween(String value1, String value2) {
            addCriterion("news_md5 not between", value1, value2, "newsMd5");
            return (Criteria) this;
        }

        public Criteria andNewsDescIsNull() {
            addCriterion("news_desc is null");
            return (Criteria) this;
        }

        public Criteria andNewsDescIsNotNull() {
            addCriterion("news_desc is not null");
            return (Criteria) this;
        }

        public Criteria andNewsDescEqualTo(String value) {
            addCriterion("news_desc =", value, "newsDesc");
            return (Criteria) this;
        }

        public Criteria andNewsDescNotEqualTo(String value) {
            addCriterion("news_desc <>", value, "newsDesc");
            return (Criteria) this;
        }

        public Criteria andNewsDescGreaterThan(String value) {
            addCriterion("news_desc >", value, "newsDesc");
            return (Criteria) this;
        }

        public Criteria andNewsDescGreaterThanOrEqualTo(String value) {
            addCriterion("news_desc >=", value, "newsDesc");
            return (Criteria) this;
        }

        public Criteria andNewsDescLessThan(String value) {
            addCriterion("news_desc <", value, "newsDesc");
            return (Criteria) this;
        }

        public Criteria andNewsDescLessThanOrEqualTo(String value) {
            addCriterion("news_desc <=", value, "newsDesc");
            return (Criteria) this;
        }

        public Criteria andNewsDescLike(String value) {
            addCriterion("news_desc like", value, "newsDesc");
            return (Criteria) this;
        }

        public Criteria andNewsDescNotLike(String value) {
            addCriterion("news_desc not like", value, "newsDesc");
            return (Criteria) this;
        }

        public Criteria andNewsDescIn(List<String> values) {
            addCriterion("news_desc in", values, "newsDesc");
            return (Criteria) this;
        }

        public Criteria andNewsDescNotIn(List<String> values) {
            addCriterion("news_desc not in", values, "newsDesc");
            return (Criteria) this;
        }

        public Criteria andNewsDescBetween(String value1, String value2) {
            addCriterion("news_desc between", value1, value2, "newsDesc");
            return (Criteria) this;
        }

        public Criteria andNewsDescNotBetween(String value1, String value2) {
            addCriterion("news_desc not between", value1, value2, "newsDesc");
            return (Criteria) this;
        }

        public Criteria andNewsHrefIsNull() {
            addCriterion("news_href is null");
            return (Criteria) this;
        }

        public Criteria andNewsHrefIsNotNull() {
            addCriterion("news_href is not null");
            return (Criteria) this;
        }

        public Criteria andNewsHrefEqualTo(String value) {
            addCriterion("news_href =", value, "newsHref");
            return (Criteria) this;
        }

        public Criteria andNewsHrefNotEqualTo(String value) {
            addCriterion("news_href <>", value, "newsHref");
            return (Criteria) this;
        }

        public Criteria andNewsHrefGreaterThan(String value) {
            addCriterion("news_href >", value, "newsHref");
            return (Criteria) this;
        }

        public Criteria andNewsHrefGreaterThanOrEqualTo(String value) {
            addCriterion("news_href >=", value, "newsHref");
            return (Criteria) this;
        }

        public Criteria andNewsHrefLessThan(String value) {
            addCriterion("news_href <", value, "newsHref");
            return (Criteria) this;
        }

        public Criteria andNewsHrefLessThanOrEqualTo(String value) {
            addCriterion("news_href <=", value, "newsHref");
            return (Criteria) this;
        }

        public Criteria andNewsHrefLike(String value) {
            addCriterion("news_href like", value, "newsHref");
            return (Criteria) this;
        }

        public Criteria andNewsHrefNotLike(String value) {
            addCriterion("news_href not like", value, "newsHref");
            return (Criteria) this;
        }

        public Criteria andNewsHrefIn(List<String> values) {
            addCriterion("news_href in", values, "newsHref");
            return (Criteria) this;
        }

        public Criteria andNewsHrefNotIn(List<String> values) {
            addCriterion("news_href not in", values, "newsHref");
            return (Criteria) this;
        }

        public Criteria andNewsHrefBetween(String value1, String value2) {
            addCriterion("news_href between", value1, value2, "newsHref");
            return (Criteria) this;
        }

        public Criteria andNewsHrefNotBetween(String value1, String value2) {
            addCriterion("news_href not between", value1, value2, "newsHref");
            return (Criteria) this;
        }

        public Criteria andNewsNameIsNull() {
            addCriterion("news_name is null");
            return (Criteria) this;
        }

        public Criteria andNewsNameIsNotNull() {
            addCriterion("news_name is not null");
            return (Criteria) this;
        }

        public Criteria andNewsNameEqualTo(String value) {
            addCriterion("news_name =", value, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameNotEqualTo(String value) {
            addCriterion("news_name <>", value, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameGreaterThan(String value) {
            addCriterion("news_name >", value, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameGreaterThanOrEqualTo(String value) {
            addCriterion("news_name >=", value, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameLessThan(String value) {
            addCriterion("news_name <", value, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameLessThanOrEqualTo(String value) {
            addCriterion("news_name <=", value, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameLike(String value) {
            addCriterion("news_name like", value, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameNotLike(String value) {
            addCriterion("news_name not like", value, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameIn(List<String> values) {
            addCriterion("news_name in", values, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameNotIn(List<String> values) {
            addCriterion("news_name not in", values, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameBetween(String value1, String value2) {
            addCriterion("news_name between", value1, value2, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameNotBetween(String value1, String value2) {
            addCriterion("news_name not between", value1, value2, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsDateIsNull() {
            addCriterion("news_date is null");
            return (Criteria) this;
        }

        public Criteria andNewsDateIsNotNull() {
            addCriterion("news_date is not null");
            return (Criteria) this;
        }

        public Criteria andNewsDateEqualTo(String value) {
            addCriterion("news_date =", value, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateNotEqualTo(String value) {
            addCriterion("news_date <>", value, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateGreaterThan(String value) {
            addCriterion("news_date >", value, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateGreaterThanOrEqualTo(String value) {
            addCriterion("news_date >=", value, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateLessThan(String value) {
            addCriterion("news_date <", value, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateLessThanOrEqualTo(String value) {
            addCriterion("news_date <=", value, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateLike(String value) {
            addCriterion("news_date like", value, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateNotLike(String value) {
            addCriterion("news_date not like", value, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateIn(List<String> values) {
            addCriterion("news_date in", values, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateNotIn(List<String> values) {
            addCriterion("news_date not in", values, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateBetween(String value1, String value2) {
            addCriterion("news_date between", value1, value2, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateNotBetween(String value1, String value2) {
            addCriterion("news_date not between", value1, value2, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsTimeIsNull() {
            addCriterion("news_time is null");
            return (Criteria) this;
        }

        public Criteria andNewsTimeIsNotNull() {
            addCriterion("news_time is not null");
            return (Criteria) this;
        }

        public Criteria andNewsTimeEqualTo(String value) {
            addCriterion("news_time =", value, "newsTime");
            return (Criteria) this;
        }

        public Criteria andNewsTimeNotEqualTo(String value) {
            addCriterion("news_time <>", value, "newsTime");
            return (Criteria) this;
        }

        public Criteria andNewsTimeGreaterThan(String value) {
            addCriterion("news_time >", value, "newsTime");
            return (Criteria) this;
        }

        public Criteria andNewsTimeGreaterThanOrEqualTo(String value) {
            addCriterion("news_time >=", value, "newsTime");
            return (Criteria) this;
        }

        public Criteria andNewsTimeLessThan(String value) {
            addCriterion("news_time <", value, "newsTime");
            return (Criteria) this;
        }

        public Criteria andNewsTimeLessThanOrEqualTo(String value) {
            addCriterion("news_time <=", value, "newsTime");
            return (Criteria) this;
        }

        public Criteria andNewsTimeLike(String value) {
            addCriterion("news_time like", value, "newsTime");
            return (Criteria) this;
        }

        public Criteria andNewsTimeNotLike(String value) {
            addCriterion("news_time not like", value, "newsTime");
            return (Criteria) this;
        }

        public Criteria andNewsTimeIn(List<String> values) {
            addCriterion("news_time in", values, "newsTime");
            return (Criteria) this;
        }

        public Criteria andNewsTimeNotIn(List<String> values) {
            addCriterion("news_time not in", values, "newsTime");
            return (Criteria) this;
        }

        public Criteria andNewsTimeBetween(String value1, String value2) {
            addCriterion("news_time between", value1, value2, "newsTime");
            return (Criteria) this;
        }

        public Criteria andNewsTimeNotBetween(String value1, String value2) {
            addCriterion("news_time not between", value1, value2, "newsTime");
            return (Criteria) this;
        }

        public Criteria andNewsTitleIsNull() {
            addCriterion("news_title is null");
            return (Criteria) this;
        }

        public Criteria andNewsTitleIsNotNull() {
            addCriterion("news_title is not null");
            return (Criteria) this;
        }

        public Criteria andNewsTitleEqualTo(String value) {
            addCriterion("news_title =", value, "newsTitle");
            return (Criteria) this;
        }

        public Criteria andNewsTitleNotEqualTo(String value) {
            addCriterion("news_title <>", value, "newsTitle");
            return (Criteria) this;
        }

        public Criteria andNewsTitleGreaterThan(String value) {
            addCriterion("news_title >", value, "newsTitle");
            return (Criteria) this;
        }

        public Criteria andNewsTitleGreaterThanOrEqualTo(String value) {
            addCriterion("news_title >=", value, "newsTitle");
            return (Criteria) this;
        }

        public Criteria andNewsTitleLessThan(String value) {
            addCriterion("news_title <", value, "newsTitle");
            return (Criteria) this;
        }

        public Criteria andNewsTitleLessThanOrEqualTo(String value) {
            addCriterion("news_title <=", value, "newsTitle");
            return (Criteria) this;
        }

        public Criteria andNewsTitleLike(String value) {
            addCriterion("news_title like", value, "newsTitle");
            return (Criteria) this;
        }

        public Criteria andNewsTitleNotLike(String value) {
            addCriterion("news_title not like", value, "newsTitle");
            return (Criteria) this;
        }

        public Criteria andNewsTitleIn(List<String> values) {
            addCriterion("news_title in", values, "newsTitle");
            return (Criteria) this;
        }

        public Criteria andNewsTitleNotIn(List<String> values) {
            addCriterion("news_title not in", values, "newsTitle");
            return (Criteria) this;
        }

        public Criteria andNewsTitleBetween(String value1, String value2) {
            addCriterion("news_title between", value1, value2, "newsTitle");
            return (Criteria) this;
        }

        public Criteria andNewsTitleNotBetween(String value1, String value2) {
            addCriterion("news_title not between", value1, value2, "newsTitle");
            return (Criteria) this;
        }
    }

    /**
     */
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