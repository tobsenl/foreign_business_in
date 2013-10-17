package cn.com.jnpc.foreign.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FiMiddleExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table FI_MIDDLE
     *
     * @mbggenerated Tue Sep 24 10:41:03 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table FI_MIDDLE
     *
     * @mbggenerated Tue Sep 24 10:41:03 CST 2013
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table FI_MIDDLE
     *
     * @mbggenerated Tue Sep 24 10:41:03 CST 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_MIDDLE
     *
     * @mbggenerated Tue Sep 24 10:41:03 CST 2013
     */
    public FiMiddleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_MIDDLE
     *
     * @mbggenerated Tue Sep 24 10:41:03 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_MIDDLE
     *
     * @mbggenerated Tue Sep 24 10:41:03 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_MIDDLE
     *
     * @mbggenerated Tue Sep 24 10:41:03 CST 2013
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_MIDDLE
     *
     * @mbggenerated Tue Sep 24 10:41:03 CST 2013
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_MIDDLE
     *
     * @mbggenerated Tue Sep 24 10:41:03 CST 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_MIDDLE
     *
     * @mbggenerated Tue Sep 24 10:41:03 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_MIDDLE
     *
     * @mbggenerated Tue Sep 24 10:41:03 CST 2013
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_MIDDLE
     *
     * @mbggenerated Tue Sep 24 10:41:03 CST 2013
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
     * This method corresponds to the database table FI_MIDDLE
     *
     * @mbggenerated Tue Sep 24 10:41:03 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_MIDDLE
     *
     * @mbggenerated Tue Sep 24 10:41:03 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table FI_MIDDLE
     *
     * @mbggenerated Tue Sep 24 10:41:03 CST 2013
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

        public Criteria andFkInvitationIdIsNull() {
            addCriterion("FK_INVITATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andFkInvitationIdIsNotNull() {
            addCriterion("FK_INVITATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFkInvitationIdEqualTo(String value) {
            addCriterion("FK_INVITATION_ID =", value, "fkInvitationId");
            return (Criteria) this;
        }

        public Criteria andFkInvitationIdNotEqualTo(String value) {
            addCriterion("FK_INVITATION_ID <>", value, "fkInvitationId");
            return (Criteria) this;
        }

        public Criteria andFkInvitationIdGreaterThan(String value) {
            addCriterion("FK_INVITATION_ID >", value, "fkInvitationId");
            return (Criteria) this;
        }

        public Criteria andFkInvitationIdGreaterThanOrEqualTo(String value) {
            addCriterion("FK_INVITATION_ID >=", value, "fkInvitationId");
            return (Criteria) this;
        }

        public Criteria andFkInvitationIdLessThan(String value) {
            addCriterion("FK_INVITATION_ID <", value, "fkInvitationId");
            return (Criteria) this;
        }

        public Criteria andFkInvitationIdLessThanOrEqualTo(String value) {
            addCriterion("FK_INVITATION_ID <=", value, "fkInvitationId");
            return (Criteria) this;
        }

        public Criteria andFkInvitationIdLike(String value) {
            addCriterion("FK_INVITATION_ID like", value, "fkInvitationId");
            return (Criteria) this;
        }

        public Criteria andFkInvitationIdNotLike(String value) {
            addCriterion("FK_INVITATION_ID not like", value, "fkInvitationId");
            return (Criteria) this;
        }

        public Criteria andFkInvitationIdIn(List<String> values) {
            addCriterion("FK_INVITATION_ID in", values, "fkInvitationId");
            return (Criteria) this;
        }

        public Criteria andFkInvitationIdNotIn(List<String> values) {
            addCriterion("FK_INVITATION_ID not in", values, "fkInvitationId");
            return (Criteria) this;
        }

        public Criteria andFkInvitationIdBetween(String value1, String value2) {
            addCriterion("FK_INVITATION_ID between", value1, value2, "fkInvitationId");
            return (Criteria) this;
        }

        public Criteria andFkInvitationIdNotBetween(String value1, String value2) {
            addCriterion("FK_INVITATION_ID not between", value1, value2, "fkInvitationId");
            return (Criteria) this;
        }

        public Criteria andFkPersonIdIsNull() {
            addCriterion("FK_PERSON_ID is null");
            return (Criteria) this;
        }

        public Criteria andFkPersonIdIsNotNull() {
            addCriterion("FK_PERSON_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFkPersonIdEqualTo(String value) {
            addCriterion("FK_PERSON_ID =", value, "fkPersonId");
            return (Criteria) this;
        }

        public Criteria andFkPersonIdNotEqualTo(String value) {
            addCriterion("FK_PERSON_ID <>", value, "fkPersonId");
            return (Criteria) this;
        }

        public Criteria andFkPersonIdGreaterThan(String value) {
            addCriterion("FK_PERSON_ID >", value, "fkPersonId");
            return (Criteria) this;
        }

        public Criteria andFkPersonIdGreaterThanOrEqualTo(String value) {
            addCriterion("FK_PERSON_ID >=", value, "fkPersonId");
            return (Criteria) this;
        }

        public Criteria andFkPersonIdLessThan(String value) {
            addCriterion("FK_PERSON_ID <", value, "fkPersonId");
            return (Criteria) this;
        }

        public Criteria andFkPersonIdLessThanOrEqualTo(String value) {
            addCriterion("FK_PERSON_ID <=", value, "fkPersonId");
            return (Criteria) this;
        }

        public Criteria andFkPersonIdLike(String value) {
            addCriterion("FK_PERSON_ID like", value, "fkPersonId");
            return (Criteria) this;
        }

        public Criteria andFkPersonIdNotLike(String value) {
            addCriterion("FK_PERSON_ID not like", value, "fkPersonId");
            return (Criteria) this;
        }

        public Criteria andFkPersonIdIn(List<String> values) {
            addCriterion("FK_PERSON_ID in", values, "fkPersonId");
            return (Criteria) this;
        }

        public Criteria andFkPersonIdNotIn(List<String> values) {
            addCriterion("FK_PERSON_ID not in", values, "fkPersonId");
            return (Criteria) this;
        }

        public Criteria andFkPersonIdBetween(String value1, String value2) {
            addCriterion("FK_PERSON_ID between", value1, value2, "fkPersonId");
            return (Criteria) this;
        }

        public Criteria andFkPersonIdNotBetween(String value1, String value2) {
            addCriterion("FK_PERSON_ID not between", value1, value2, "fkPersonId");
            return (Criteria) this;
        }

        public Criteria andFkPersonPpidIsNull() {
            addCriterion("FK_PERSON_PPID is null");
            return (Criteria) this;
        }

        public Criteria andFkPersonPpidIsNotNull() {
            addCriterion("FK_PERSON_PPID is not null");
            return (Criteria) this;
        }

        public Criteria andFkPersonPpidEqualTo(String value) {
            addCriterion("FK_PERSON_PPID =", value, "fkPersonPpid");
            return (Criteria) this;
        }

        public Criteria andFkPersonPpidNotEqualTo(String value) {
            addCriterion("FK_PERSON_PPID <>", value, "fkPersonPpid");
            return (Criteria) this;
        }

        public Criteria andFkPersonPpidGreaterThan(String value) {
            addCriterion("FK_PERSON_PPID >", value, "fkPersonPpid");
            return (Criteria) this;
        }

        public Criteria andFkPersonPpidGreaterThanOrEqualTo(String value) {
            addCriterion("FK_PERSON_PPID >=", value, "fkPersonPpid");
            return (Criteria) this;
        }

        public Criteria andFkPersonPpidLessThan(String value) {
            addCriterion("FK_PERSON_PPID <", value, "fkPersonPpid");
            return (Criteria) this;
        }

        public Criteria andFkPersonPpidLessThanOrEqualTo(String value) {
            addCriterion("FK_PERSON_PPID <=", value, "fkPersonPpid");
            return (Criteria) this;
        }

        public Criteria andFkPersonPpidLike(String value) {
            addCriterion("FK_PERSON_PPID like", value, "fkPersonPpid");
            return (Criteria) this;
        }

        public Criteria andFkPersonPpidNotLike(String value) {
            addCriterion("FK_PERSON_PPID not like", value, "fkPersonPpid");
            return (Criteria) this;
        }

        public Criteria andFkPersonPpidIn(List<String> values) {
            addCriterion("FK_PERSON_PPID in", values, "fkPersonPpid");
            return (Criteria) this;
        }

        public Criteria andFkPersonPpidNotIn(List<String> values) {
            addCriterion("FK_PERSON_PPID not in", values, "fkPersonPpid");
            return (Criteria) this;
        }

        public Criteria andFkPersonPpidBetween(String value1, String value2) {
            addCriterion("FK_PERSON_PPID between", value1, value2, "fkPersonPpid");
            return (Criteria) this;
        }

        public Criteria andFkPersonPpidNotBetween(String value1, String value2) {
            addCriterion("FK_PERSON_PPID not between", value1, value2, "fkPersonPpid");
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

        public Criteria andWorkIsNull() {
            addCriterion("WORK is null");
            return (Criteria) this;
        }

        public Criteria andWorkIsNotNull() {
            addCriterion("WORK is not null");
            return (Criteria) this;
        }

        public Criteria andWorkEqualTo(String value) {
            addCriterion("WORK =", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkNotEqualTo(String value) {
            addCriterion("WORK <>", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkGreaterThan(String value) {
            addCriterion("WORK >", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkGreaterThanOrEqualTo(String value) {
            addCriterion("WORK >=", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkLessThan(String value) {
            addCriterion("WORK <", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkLessThanOrEqualTo(String value) {
            addCriterion("WORK <=", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkLike(String value) {
            addCriterion("WORK like", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkNotLike(String value) {
            addCriterion("WORK not like", value, "work");
            return (Criteria) this;
        }

        public Criteria andWorkIn(List<String> values) {
            addCriterion("WORK in", values, "work");
            return (Criteria) this;
        }

        public Criteria andWorkNotIn(List<String> values) {
            addCriterion("WORK not in", values, "work");
            return (Criteria) this;
        }

        public Criteria andWorkBetween(String value1, String value2) {
            addCriterion("WORK between", value1, value2, "work");
            return (Criteria) this;
        }

        public Criteria andWorkNotBetween(String value1, String value2) {
            addCriterion("WORK not between", value1, value2, "work");
            return (Criteria) this;
        }

        public Criteria andWorkAddressIsNull() {
            addCriterion("WORK_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andWorkAddressIsNotNull() {
            addCriterion("WORK_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andWorkAddressEqualTo(String value) {
            addCriterion("WORK_ADDRESS =", value, "workAddress");
            return (Criteria) this;
        }

        public Criteria andWorkAddressNotEqualTo(String value) {
            addCriterion("WORK_ADDRESS <>", value, "workAddress");
            return (Criteria) this;
        }

        public Criteria andWorkAddressGreaterThan(String value) {
            addCriterion("WORK_ADDRESS >", value, "workAddress");
            return (Criteria) this;
        }

        public Criteria andWorkAddressGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_ADDRESS >=", value, "workAddress");
            return (Criteria) this;
        }

        public Criteria andWorkAddressLessThan(String value) {
            addCriterion("WORK_ADDRESS <", value, "workAddress");
            return (Criteria) this;
        }

        public Criteria andWorkAddressLessThanOrEqualTo(String value) {
            addCriterion("WORK_ADDRESS <=", value, "workAddress");
            return (Criteria) this;
        }

        public Criteria andWorkAddressLike(String value) {
            addCriterion("WORK_ADDRESS like", value, "workAddress");
            return (Criteria) this;
        }

        public Criteria andWorkAddressNotLike(String value) {
            addCriterion("WORK_ADDRESS not like", value, "workAddress");
            return (Criteria) this;
        }

        public Criteria andWorkAddressIn(List<String> values) {
            addCriterion("WORK_ADDRESS in", values, "workAddress");
            return (Criteria) this;
        }

        public Criteria andWorkAddressNotIn(List<String> values) {
            addCriterion("WORK_ADDRESS not in", values, "workAddress");
            return (Criteria) this;
        }

        public Criteria andWorkAddressBetween(String value1, String value2) {
            addCriterion("WORK_ADDRESS between", value1, value2, "workAddress");
            return (Criteria) this;
        }

        public Criteria andWorkAddressNotBetween(String value1, String value2) {
            addCriterion("WORK_ADDRESS not between", value1, value2, "workAddress");
            return (Criteria) this;
        }

        public Criteria andLivingAddressIsNull() {
            addCriterion("LIVING_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andLivingAddressIsNotNull() {
            addCriterion("LIVING_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andLivingAddressEqualTo(String value) {
            addCriterion("LIVING_ADDRESS =", value, "livingAddress");
            return (Criteria) this;
        }

        public Criteria andLivingAddressNotEqualTo(String value) {
            addCriterion("LIVING_ADDRESS <>", value, "livingAddress");
            return (Criteria) this;
        }

        public Criteria andLivingAddressGreaterThan(String value) {
            addCriterion("LIVING_ADDRESS >", value, "livingAddress");
            return (Criteria) this;
        }

        public Criteria andLivingAddressGreaterThanOrEqualTo(String value) {
            addCriterion("LIVING_ADDRESS >=", value, "livingAddress");
            return (Criteria) this;
        }

        public Criteria andLivingAddressLessThan(String value) {
            addCriterion("LIVING_ADDRESS <", value, "livingAddress");
            return (Criteria) this;
        }

        public Criteria andLivingAddressLessThanOrEqualTo(String value) {
            addCriterion("LIVING_ADDRESS <=", value, "livingAddress");
            return (Criteria) this;
        }

        public Criteria andLivingAddressLike(String value) {
            addCriterion("LIVING_ADDRESS like", value, "livingAddress");
            return (Criteria) this;
        }

        public Criteria andLivingAddressNotLike(String value) {
            addCriterion("LIVING_ADDRESS not like", value, "livingAddress");
            return (Criteria) this;
        }

        public Criteria andLivingAddressIn(List<String> values) {
            addCriterion("LIVING_ADDRESS in", values, "livingAddress");
            return (Criteria) this;
        }

        public Criteria andLivingAddressNotIn(List<String> values) {
            addCriterion("LIVING_ADDRESS not in", values, "livingAddress");
            return (Criteria) this;
        }

        public Criteria andLivingAddressBetween(String value1, String value2) {
            addCriterion("LIVING_ADDRESS between", value1, value2, "livingAddress");
            return (Criteria) this;
        }

        public Criteria andLivingAddressNotBetween(String value1, String value2) {
            addCriterion("LIVING_ADDRESS not between", value1, value2, "livingAddress");
            return (Criteria) this;
        }

        public Criteria andDeferIdIsNull() {
            addCriterion("DEFER_ID is null");
            return (Criteria) this;
        }

        public Criteria andDeferIdIsNotNull() {
            addCriterion("DEFER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDeferIdEqualTo(String value) {
            addCriterion("DEFER_ID =", value, "deferId");
            return (Criteria) this;
        }

        public Criteria andDeferIdNotEqualTo(String value) {
            addCriterion("DEFER_ID <>", value, "deferId");
            return (Criteria) this;
        }

        public Criteria andDeferIdGreaterThan(String value) {
            addCriterion("DEFER_ID >", value, "deferId");
            return (Criteria) this;
        }

        public Criteria andDeferIdGreaterThanOrEqualTo(String value) {
            addCriterion("DEFER_ID >=", value, "deferId");
            return (Criteria) this;
        }

        public Criteria andDeferIdLessThan(String value) {
            addCriterion("DEFER_ID <", value, "deferId");
            return (Criteria) this;
        }

        public Criteria andDeferIdLessThanOrEqualTo(String value) {
            addCriterion("DEFER_ID <=", value, "deferId");
            return (Criteria) this;
        }

        public Criteria andDeferIdLike(String value) {
            addCriterion("DEFER_ID like", value, "deferId");
            return (Criteria) this;
        }

        public Criteria andDeferIdNotLike(String value) {
            addCriterion("DEFER_ID not like", value, "deferId");
            return (Criteria) this;
        }

        public Criteria andDeferIdIn(List<String> values) {
            addCriterion("DEFER_ID in", values, "deferId");
            return (Criteria) this;
        }

        public Criteria andDeferIdNotIn(List<String> values) {
            addCriterion("DEFER_ID not in", values, "deferId");
            return (Criteria) this;
        }

        public Criteria andDeferIdBetween(String value1, String value2) {
            addCriterion("DEFER_ID between", value1, value2, "deferId");
            return (Criteria) this;
        }

        public Criteria andDeferIdNotBetween(String value1, String value2) {
            addCriterion("DEFER_ID not between", value1, value2, "deferId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table FI_MIDDLE
     *
     * @mbggenerated do_not_delete_during_merge Tue Sep 24 10:41:03 CST 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table FI_MIDDLE
     *
     * @mbggenerated Tue Sep 24 10:41:03 CST 2013
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