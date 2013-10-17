package cn.com.jnpc.foreign.po;

import java.util.Date;

public class FiResidencePermit {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FI_RESIDENCE_PERMIT.ID
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FI_RESIDENCE_PERMIT.RESIDENCE_PERMIT_ID
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    private String residencePermitId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FI_RESIDENCE_PERMIT.RESIDENCE_PERMIT_KIND
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    private String residencePermitKind;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FI_RESIDENCE_PERMIT.RP_EXP_ENDDATE
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    private Date rpExpEnddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FI_RESIDENCE_PERMIT.RP_ADDRESS
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    private String rpAddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FI_RESIDENCE_PERMIT.FK_ATTACHMENT_ID
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    private String fkAttachmentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FI_RESIDENCE_PERMIT.FK_FOREIGNER_ID
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    private String fkForeignerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FI_RESIDENCE_PERMIT.STATUS
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FI_RESIDENCE_PERMIT.CREATE_DATE
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FI_RESIDENCE_PERMIT.CREATE_USER
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FI_RESIDENCE_PERMIT.EDIT_DATE
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    private Date editDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FI_RESIDENCE_PERMIT.EDIT_USER
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    private String editUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FI_RESIDENCE_PERMIT.IS_DEFER
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    private Integer isDefer;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FI_RESIDENCE_PERMIT.ID
     *
     * @return the value of FI_RESIDENCE_PERMIT.ID
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FI_RESIDENCE_PERMIT.ID
     *
     * @param id the value for FI_RESIDENCE_PERMIT.ID
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FI_RESIDENCE_PERMIT.RESIDENCE_PERMIT_ID
     *
     * @return the value of FI_RESIDENCE_PERMIT.RESIDENCE_PERMIT_ID
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public String getResidencePermitId() {
        return residencePermitId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FI_RESIDENCE_PERMIT.RESIDENCE_PERMIT_ID
     *
     * @param residencePermitId the value for FI_RESIDENCE_PERMIT.RESIDENCE_PERMIT_ID
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public void setResidencePermitId(String residencePermitId) {
        this.residencePermitId = residencePermitId == null ? null : residencePermitId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FI_RESIDENCE_PERMIT.RESIDENCE_PERMIT_KIND
     *
     * @return the value of FI_RESIDENCE_PERMIT.RESIDENCE_PERMIT_KIND
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public String getResidencePermitKind() {
        return residencePermitKind;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FI_RESIDENCE_PERMIT.RESIDENCE_PERMIT_KIND
     *
     * @param residencePermitKind the value for FI_RESIDENCE_PERMIT.RESIDENCE_PERMIT_KIND
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public void setResidencePermitKind(String residencePermitKind) {
        this.residencePermitKind = residencePermitKind == null ? null : residencePermitKind.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FI_RESIDENCE_PERMIT.RP_EXP_ENDDATE
     *
     * @return the value of FI_RESIDENCE_PERMIT.RP_EXP_ENDDATE
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public Date getRpExpEnddate() {
        return rpExpEnddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FI_RESIDENCE_PERMIT.RP_EXP_ENDDATE
     *
     * @param rpExpEnddate the value for FI_RESIDENCE_PERMIT.RP_EXP_ENDDATE
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public void setRpExpEnddate(Date rpExpEnddate) {
        this.rpExpEnddate = rpExpEnddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FI_RESIDENCE_PERMIT.RP_ADDRESS
     *
     * @return the value of FI_RESIDENCE_PERMIT.RP_ADDRESS
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public String getRpAddress() {
        return rpAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FI_RESIDENCE_PERMIT.RP_ADDRESS
     *
     * @param rpAddress the value for FI_RESIDENCE_PERMIT.RP_ADDRESS
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public void setRpAddress(String rpAddress) {
        this.rpAddress = rpAddress == null ? null : rpAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FI_RESIDENCE_PERMIT.FK_ATTACHMENT_ID
     *
     * @return the value of FI_RESIDENCE_PERMIT.FK_ATTACHMENT_ID
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public String getFkAttachmentId() {
        return fkAttachmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FI_RESIDENCE_PERMIT.FK_ATTACHMENT_ID
     *
     * @param fkAttachmentId the value for FI_RESIDENCE_PERMIT.FK_ATTACHMENT_ID
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public void setFkAttachmentId(String fkAttachmentId) {
        this.fkAttachmentId = fkAttachmentId == null ? null : fkAttachmentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FI_RESIDENCE_PERMIT.FK_FOREIGNER_ID
     *
     * @return the value of FI_RESIDENCE_PERMIT.FK_FOREIGNER_ID
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public String getFkForeignerId() {
        return fkForeignerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FI_RESIDENCE_PERMIT.FK_FOREIGNER_ID
     *
     * @param fkForeignerId the value for FI_RESIDENCE_PERMIT.FK_FOREIGNER_ID
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public void setFkForeignerId(String fkForeignerId) {
        this.fkForeignerId = fkForeignerId == null ? null : fkForeignerId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FI_RESIDENCE_PERMIT.STATUS
     *
     * @return the value of FI_RESIDENCE_PERMIT.STATUS
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FI_RESIDENCE_PERMIT.STATUS
     *
     * @param status the value for FI_RESIDENCE_PERMIT.STATUS
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FI_RESIDENCE_PERMIT.CREATE_DATE
     *
     * @return the value of FI_RESIDENCE_PERMIT.CREATE_DATE
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FI_RESIDENCE_PERMIT.CREATE_DATE
     *
     * @param createDate the value for FI_RESIDENCE_PERMIT.CREATE_DATE
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FI_RESIDENCE_PERMIT.CREATE_USER
     *
     * @return the value of FI_RESIDENCE_PERMIT.CREATE_USER
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FI_RESIDENCE_PERMIT.CREATE_USER
     *
     * @param createUser the value for FI_RESIDENCE_PERMIT.CREATE_USER
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FI_RESIDENCE_PERMIT.EDIT_DATE
     *
     * @return the value of FI_RESIDENCE_PERMIT.EDIT_DATE
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public Date getEditDate() {
        return editDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FI_RESIDENCE_PERMIT.EDIT_DATE
     *
     * @param editDate the value for FI_RESIDENCE_PERMIT.EDIT_DATE
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FI_RESIDENCE_PERMIT.EDIT_USER
     *
     * @return the value of FI_RESIDENCE_PERMIT.EDIT_USER
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public String getEditUser() {
        return editUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FI_RESIDENCE_PERMIT.EDIT_USER
     *
     * @param editUser the value for FI_RESIDENCE_PERMIT.EDIT_USER
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public void setEditUser(String editUser) {
        this.editUser = editUser == null ? null : editUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FI_RESIDENCE_PERMIT.IS_DEFER
     *
     * @return the value of FI_RESIDENCE_PERMIT.IS_DEFER
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public Integer getIsDefer() {
        return isDefer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FI_RESIDENCE_PERMIT.IS_DEFER
     *
     * @param isDefer the value for FI_RESIDENCE_PERMIT.IS_DEFER
     *
     * @mbggenerated Mon Sep 23 10:12:13 CST 2013
     */
    public void setIsDefer(Integer isDefer) {
        this.isDefer = isDefer;
    }
}