package cn.com.jnpc.foreign.vo;

import java.util.Date;

import cn.com.jnpc.foreign.po.FiAttachment;
import cn.com.jnpc.foreign.po.FiInvitation;

public class foreigner {
    private String id;

    private String name;

    private String sex;

    private String birthday;

    private String country;

    private String company_department;

    private String passport_id;

    private String passport_exp_date;

    private String post;

    private String role;

    private String fk_pp_attachment_id;
    private String fk_pp_url;

    public String getFk_pp_url() {
        return fk_pp_url;
    }
    public void setFk_pp_url(String fk_pp_url) {
        this.fk_pp_url = fk_pp_url;
    }
    public String getFk_ee_url() {
        return fk_ee_url;
    }
    public void setFk_ee_url(String fk_ee_url) {
        this.fk_ee_url = fk_ee_url;
    }
    private String expert_evidence;

    private String fk_ee_attachment_id;
    private String fk_ee_url;

    private String fk_rp_permit_id;

    public String getFk_rp_permit_id() {
        return fk_rp_permit_id;
    }
    public void setFk_rp_permit_id(String fk_rp_permit_id) {
        this.fk_rp_permit_id = fk_rp_permit_id;
    }
    private String fk_invitation_id;

    private String is_here;

    private String status;

    private String residence_permit_id;
    private String residence_permit_kind;
    private String rp_exp_endDate;
    private String rp_Address;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCompany_department() {
        return company_department;
    }
    public void setCompany_department(String company_department) {
        this.company_department = company_department;
    }
    public String getPassport_id() {
        return passport_id;
    }
    public void setPassport_id(String passport_id) {
        this.passport_id = passport_id;
    }
    public String getPassport_exp_date() {
        return passport_exp_date;
    }
    public void setPassport_exp_date(String passport_exp_date) {
        this.passport_exp_date = passport_exp_date;
    }
    public String getPost() {
        return post;
    }
    public void setPost(String post) {
        this.post = post;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getFk_pp_attachment_id() {
        return fk_pp_attachment_id;
    }
    public void setFk_pp_attachment_id(String fk_pp_attachment_id) {
        this.fk_pp_attachment_id = fk_pp_attachment_id;
    }
    public String getExpert_evidence() {
        return expert_evidence;
    }
    public void setExpert_evidence(String expert_evidence) {
        this.expert_evidence = expert_evidence;
    }
    public String getFk_ee_attachment_id() {
        return fk_ee_attachment_id;
    }
    public void setFk_ee_attachment_id(String fk_ee_attachment_id) {
        this.fk_ee_attachment_id = fk_ee_attachment_id;
    }
    
    public String getFk_invitation_id() {
        return fk_invitation_id;
    }
    public void setFk_invitation_id(String fk_invitation_id) {
        this.fk_invitation_id = fk_invitation_id;
    }
    public String getIs_here() {
        return is_here;
    }
    public void setIs_here(String is_here) {
        this.is_here = is_here;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getResidence_permit_id() {
        return residence_permit_id;
    }
    public void setResidence_permit_id(String residence_permit_id) {
        this.residence_permit_id = residence_permit_id;
    }
    public String getResidence_permit_kind() {
        return residence_permit_kind;
    }
    public void setResidence_permit_kind(String residence_permit_kind) {
        this.residence_permit_kind = residence_permit_kind;
    }
    public String getRp_exp_endDate() {
        return rp_exp_endDate;
    }
    public void setRp_exp_endDate(String rp_exp_endDate) {
        this.rp_exp_endDate = rp_exp_endDate;
    }
    public String getRp_Address() {
        return rp_Address;
    }
    public void setRp_Address(String rp_Address) {
        this.rp_Address = rp_Address;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

}
