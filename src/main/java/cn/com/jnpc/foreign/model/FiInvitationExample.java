package cn.com.jnpc.foreign.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FiInvitationExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table FI_INVITATION
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table FI_INVITATION
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table FI_INVITATION
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INVITATION
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    public FiInvitationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INVITATION
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INVITATION
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INVITATION
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INVITATION
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INVITATION
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INVITATION
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INVITATION
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INVITATION
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
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
     * This method corresponds to the database table FI_INVITATION
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INVITATION
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table FI_INVITATION
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(int value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(int value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(int value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(int value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(int value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(int value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(int value1, int value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(int value1, int value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andInvitationIdIsNull() {
            addCriterion("INVITATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andInvitationIdIsNotNull() {
            addCriterion("INVITATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInvitationIdEqualTo(String value) {
            addCriterion("INVITATION_ID =", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdNotEqualTo(String value) {
            addCriterion("INVITATION_ID <>", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdGreaterThan(String value) {
            addCriterion("INVITATION_ID >", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdGreaterThanOrEqualTo(String value) {
            addCriterion("INVITATION_ID >=", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdLessThan(String value) {
            addCriterion("INVITATION_ID <", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdLessThanOrEqualTo(String value) {
            addCriterion("INVITATION_ID <=", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdLike(String value) {
            addCriterion("INVITATION_ID like", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdNotLike(String value) {
            addCriterion("INVITATION_ID not like", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdIn(List<String> values) {
            addCriterion("INVITATION_ID in", values, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdNotIn(List<String> values) {
            addCriterion("INVITATION_ID not in", values, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdBetween(String value1, String value2) {
            addCriterion("INVITATION_ID between", value1, value2, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdNotBetween(String value1, String value2) {
            addCriterion("INVITATION_ID not between", value1, value2, "invitationId");
            return (Criteria) this;
        }

        public Criteria andFkAttachmentIdIsNull() {
            addCriterion("FK_ATTACHMENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andFkAttachmentIdIsNotNull() {
            addCriterion("FK_ATTACHMENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFkAttachmentIdEqualTo(String value) {
            addCriterion("FK_ATTACHMENT_ID =", value, "fkAttachmentId");
            return (Criteria) this;
        }

        public Criteria andFkAttachmentIdNotEqualTo(String value) {
            addCriterion("FK_ATTACHMENT_ID <>", value, "fkAttachmentId");
            return (Criteria) this;
        }

        public Criteria andFkAttachmentIdGreaterThan(String value) {
            addCriterion("FK_ATTACHMENT_ID >", value, "fkAttachmentId");
            return (Criteria) this;
        }

        public Criteria andFkAttachmentIdGreaterThanOrEqualTo(String value) {
            addCriterion("FK_ATTACHMENT_ID >=", value, "fkAttachmentId");
            return (Criteria) this;
        }

        public Criteria andFkAttachmentIdLessThan(String value) {
            addCriterion("FK_ATTACHMENT_ID <", value, "fkAttachmentId");
            return (Criteria) this;
        }

        public Criteria andFkAttachmentIdLessThanOrEqualTo(String value) {
            addCriterion("FK_ATTACHMENT_ID <=", value, "fkAttachmentId");
            return (Criteria) this;
        }

        public Criteria andFkAttachmentIdLike(String value) {
            addCriterion("FK_ATTACHMENT_ID like", value, "fkAttachmentId");
            return (Criteria) this;
        }

        public Criteria andFkAttachmentIdNotLike(String value) {
            addCriterion("FK_ATTACHMENT_ID not like", value, "fkAttachmentId");
            return (Criteria) this;
        }

        public Criteria andFkAttachmentIdIn(List<String> values) {
            addCriterion("FK_ATTACHMENT_ID in", values, "fkAttachmentId");
            return (Criteria) this;
        }

        public Criteria andFkAttachmentIdNotIn(List<String> values) {
            addCriterion("FK_ATTACHMENT_ID not in", values, "fkAttachmentId");
            return (Criteria) this;
        }

        public Criteria andFkAttachmentIdBetween(String value1, String value2) {
            addCriterion("FK_ATTACHMENT_ID between", value1, value2, "fkAttachmentId");
            return (Criteria) this;
        }

        public Criteria andFkAttachmentIdNotBetween(String value1, String value2) {
            addCriterion("FK_ATTACHMENT_ID not between", value1, value2, "fkAttachmentId");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNull() {
            addCriterion("IS_USE is null");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNotNull() {
            addCriterion("IS_USE is not null");
            return (Criteria) this;
        }

        public Criteria andIsUseEqualTo(int value) {
            addCriterion("IS_USE =", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotEqualTo(int value) {
            addCriterion("IS_USE <>", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThan(int value) {
            addCriterion("IS_USE >", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThanOrEqualTo(int value) {
            addCriterion("IS_USE >=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThan(int value) {
            addCriterion("IS_USE <", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThanOrEqualTo(int value) {
            addCriterion("IS_USE <=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseIn(List<Integer> values) {
            addCriterion("IS_USE in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotIn(List<Integer> values) {
            addCriterion("IS_USE not in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseBetween(int value1, int value2) {
            addCriterion("IS_USE between", value1, value2, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotBetween(int value1, int value2) {
            addCriterion("IS_USE not between", value1, value2, "isUse");
            return (Criteria) this;
        }

        public Criteria andStayTimeIsNull() {
            addCriterion("STAY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStayTimeIsNotNull() {
            addCriterion("STAY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStayTimeEqualTo(String value) {
            addCriterion("STAY_TIME =", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeNotEqualTo(String value) {
            addCriterion("STAY_TIME <>", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeGreaterThan(String value) {
            addCriterion("STAY_TIME >", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeGreaterThanOrEqualTo(String value) {
            addCriterion("STAY_TIME >=", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeLessThan(String value) {
            addCriterion("STAY_TIME <", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeLessThanOrEqualTo(String value) {
            addCriterion("STAY_TIME <=", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeLike(String value) {
            addCriterion("STAY_TIME like", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeNotLike(String value) {
            addCriterion("STAY_TIME not like", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeIn(List<String> values) {
            addCriterion("STAY_TIME in", values, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeNotIn(List<String> values) {
            addCriterion("STAY_TIME not in", values, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeBetween(String value1, String value2) {
            addCriterion("STAY_TIME between", value1, value2, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeNotBetween(String value1, String value2) {
            addCriterion("STAY_TIME not between", value1, value2, "stayTime");
            return (Criteria) this;
        }

        public Criteria andGobackTimesIsNull() {
            addCriterion("GOBACK_TIMES is null");
            return (Criteria) this;
        }

        public Criteria andGobackTimesIsNotNull() {
            addCriterion("GOBACK_TIMES is not null");
            return (Criteria) this;
        }

        public Criteria andGobackTimesEqualTo(String value) {
            addCriterion("GOBACK_TIMES =", value, "gobackTimes");
            return (Criteria) this;
        }

        public Criteria andGobackTimesNotEqualTo(String value) {
            addCriterion("GOBACK_TIMES <>", value, "gobackTimes");
            return (Criteria) this;
        }

        public Criteria andGobackTimesGreaterThan(String value) {
            addCriterion("GOBACK_TIMES >", value, "gobackTimes");
            return (Criteria) this;
        }

        public Criteria andGobackTimesGreaterThanOrEqualTo(String value) {
            addCriterion("GOBACK_TIMES >=", value, "gobackTimes");
            return (Criteria) this;
        }

        public Criteria andGobackTimesLessThan(String value) {
            addCriterion("GOBACK_TIMES <", value, "gobackTimes");
            return (Criteria) this;
        }

        public Criteria andGobackTimesLessThanOrEqualTo(String value) {
            addCriterion("GOBACK_TIMES <=", value, "gobackTimes");
            return (Criteria) this;
        }

        public Criteria andGobackTimesLike(String value) {
            addCriterion("GOBACK_TIMES like", value, "gobackTimes");
            return (Criteria) this;
        }

        public Criteria andGobackTimesNotLike(String value) {
            addCriterion("GOBACK_TIMES not like", value, "gobackTimes");
            return (Criteria) this;
        }

        public Criteria andGobackTimesIn(List<String> values) {
            addCriterion("GOBACK_TIMES in", values, "gobackTimes");
            return (Criteria) this;
        }

        public Criteria andGobackTimesNotIn(List<String> values) {
            addCriterion("GOBACK_TIMES not in", values, "gobackTimes");
            return (Criteria) this;
        }

        public Criteria andGobackTimesBetween(String value1, String value2) {
            addCriterion("GOBACK_TIMES between", value1, value2, "gobackTimes");
            return (Criteria) this;
        }

        public Criteria andGobackTimesNotBetween(String value1, String value2) {
            addCriterion("GOBACK_TIMES not between", value1, value2, "gobackTimes");
            return (Criteria) this;
        }

        public Criteria andArrivedDateIsNull() {
            addCriterion("ARRIVED_DATE is null");
            return (Criteria) this;
        }

        public Criteria andArrivedDateIsNotNull() {
            addCriterion("ARRIVED_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andArrivedDateEqualTo(Date value) {
            addCriterion("ARRIVED_DATE =", value, "arrivedDate");
            return (Criteria) this;
        }

        public Criteria andArrivedDateNotEqualTo(Date value) {
            addCriterion("ARRIVED_DATE <>", value, "arrivedDate");
            return (Criteria) this;
        }

        public Criteria andArrivedDateGreaterThan(Date value) {
            addCriterion("ARRIVED_DATE >", value, "arrivedDate");
            return (Criteria) this;
        }

        public Criteria andArrivedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("ARRIVED_DATE >=", value, "arrivedDate");
            return (Criteria) this;
        }

        public Criteria andArrivedDateLessThan(Date value) {
            addCriterion("ARRIVED_DATE <", value, "arrivedDate");
            return (Criteria) this;
        }

        public Criteria andArrivedDateLessThanOrEqualTo(Date value) {
            addCriterion("ARRIVED_DATE <=", value, "arrivedDate");
            return (Criteria) this;
        }

        public Criteria andArrivedDateIn(List<Date> values) {
            addCriterion("ARRIVED_DATE in", values, "arrivedDate");
            return (Criteria) this;
        }

        public Criteria andArrivedDateNotIn(List<Date> values) {
            addCriterion("ARRIVED_DATE not in", values, "arrivedDate");
            return (Criteria) this;
        }

        public Criteria andArrivedDateBetween(Date value1, Date value2) {
            addCriterion("ARRIVED_DATE between", value1, value2, "arrivedDate");
            return (Criteria) this;
        }

        public Criteria andArrivedDateNotBetween(Date value1, Date value2) {
            addCriterion("ARRIVED_DATE not between", value1, value2, "arrivedDate");
            return (Criteria) this;
        }

        public Criteria andLeavingDateIsNull() {
            addCriterion("LEAVING_DATE is null");
            return (Criteria) this;
        }

        public Criteria andLeavingDateIsNotNull() {
            addCriterion("LEAVING_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andLeavingDateEqualTo(Date value) {
            addCriterion("LEAVING_DATE =", value, "leavingDate");
            return (Criteria) this;
        }

        public Criteria andLeavingDateNotEqualTo(Date value) {
            addCriterion("LEAVING_DATE <>", value, "leavingDate");
            return (Criteria) this;
        }

        public Criteria andLeavingDateGreaterThan(Date value) {
            addCriterion("LEAVING_DATE >", value, "leavingDate");
            return (Criteria) this;
        }

        public Criteria andLeavingDateGreaterThanOrEqualTo(Date value) {
            addCriterion("LEAVING_DATE >=", value, "leavingDate");
            return (Criteria) this;
        }

        public Criteria andLeavingDateLessThan(Date value) {
            addCriterion("LEAVING_DATE <", value, "leavingDate");
            return (Criteria) this;
        }

        public Criteria andLeavingDateLessThanOrEqualTo(Date value) {
            addCriterion("LEAVING_DATE <=", value, "leavingDate");
            return (Criteria) this;
        }

        public Criteria andLeavingDateIn(List<Date> values) {
            addCriterion("LEAVING_DATE in", values, "leavingDate");
            return (Criteria) this;
        }

        public Criteria andLeavingDateNotIn(List<Date> values) {
            addCriterion("LEAVING_DATE not in", values, "leavingDate");
            return (Criteria) this;
        }

        public Criteria andLeavingDateBetween(Date value1, Date value2) {
            addCriterion("LEAVING_DATE between", value1, value2, "leavingDate");
            return (Criteria) this;
        }

        public Criteria andLeavingDateNotBetween(Date value1, Date value2) {
            addCriterion("LEAVING_DATE not between", value1, value2, "leavingDate");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(int value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(int value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(int value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(int value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(int value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(int value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(int value1, int value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(int value1, int value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andEditDateIsNull() {
            addCriterion("EDIT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEditDateIsNotNull() {
            addCriterion("EDIT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEditDateEqualTo(Date value) {
            addCriterion("EDIT_DATE =", value, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateNotEqualTo(Date value) {
            addCriterion("EDIT_DATE <>", value, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateGreaterThan(Date value) {
            addCriterion("EDIT_DATE >", value, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("EDIT_DATE >=", value, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateLessThan(Date value) {
            addCriterion("EDIT_DATE <", value, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateLessThanOrEqualTo(Date value) {
            addCriterion("EDIT_DATE <=", value, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateIn(List<Date> values) {
            addCriterion("EDIT_DATE in", values, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateNotIn(List<Date> values) {
            addCriterion("EDIT_DATE not in", values, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateBetween(Date value1, Date value2) {
            addCriterion("EDIT_DATE between", value1, value2, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateNotBetween(Date value1, Date value2) {
            addCriterion("EDIT_DATE not between", value1, value2, "editDate");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("CREATE_USER is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("CREATE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("CREATE_USER =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("CREATE_USER <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("CREATE_USER >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_USER >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("CREATE_USER <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("CREATE_USER <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("CREATE_USER like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("CREATE_USER not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("CREATE_USER in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("CREATE_USER not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("CREATE_USER between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("CREATE_USER not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andEditUserIsNull() {
            addCriterion("EDIT_USER is null");
            return (Criteria) this;
        }

        public Criteria andEditUserIsNotNull() {
            addCriterion("EDIT_USER is not null");
            return (Criteria) this;
        }

        public Criteria andEditUserEqualTo(String value) {
            addCriterion("EDIT_USER =", value, "editUser");
            return (Criteria) this;
        }

        public Criteria andEditUserNotEqualTo(String value) {
            addCriterion("EDIT_USER <>", value, "editUser");
            return (Criteria) this;
        }

        public Criteria andEditUserGreaterThan(String value) {
            addCriterion("EDIT_USER >", value, "editUser");
            return (Criteria) this;
        }

        public Criteria andEditUserGreaterThanOrEqualTo(String value) {
            addCriterion("EDIT_USER >=", value, "editUser");
            return (Criteria) this;
        }

        public Criteria andEditUserLessThan(String value) {
            addCriterion("EDIT_USER <", value, "editUser");
            return (Criteria) this;
        }

        public Criteria andEditUserLessThanOrEqualTo(String value) {
            addCriterion("EDIT_USER <=", value, "editUser");
            return (Criteria) this;
        }

        public Criteria andEditUserLike(String value) {
            addCriterion("EDIT_USER like", value, "editUser");
            return (Criteria) this;
        }

        public Criteria andEditUserNotLike(String value) {
            addCriterion("EDIT_USER not like", value, "editUser");
            return (Criteria) this;
        }

        public Criteria andEditUserIn(List<String> values) {
            addCriterion("EDIT_USER in", values, "editUser");
            return (Criteria) this;
        }

        public Criteria andEditUserNotIn(List<String> values) {
            addCriterion("EDIT_USER not in", values, "editUser");
            return (Criteria) this;
        }

        public Criteria andEditUserBetween(String value1, String value2) {
            addCriterion("EDIT_USER between", value1, value2, "editUser");
            return (Criteria) this;
        }

        public Criteria andEditUserNotBetween(String value1, String value2) {
            addCriterion("EDIT_USER not between", value1, value2, "editUser");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table FI_INVITATION
     *
     * @mbggenerated do_not_delete_during_merge Sun Sep 22 22:28:01 CST 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table FI_INVITATION
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
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