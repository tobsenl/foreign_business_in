package cn.com.jnpc.foreign.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import cn.com.jnpc.foreign.mapperprovider.FiAttachmentSqlProvider;
import cn.com.jnpc.foreign.model.FiAttachmentExample;
import cn.com.jnpc.foreign.po.FiAttachment;

public interface FiAttachmentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_ATTACHMENT
     *
     * @mbggenerated Mon Sep 23 01:08:51 CST 2013
     */
    @SelectProvider(type=FiAttachmentSqlProvider.class, method="countByExample")
    int countByExample(FiAttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_ATTACHMENT
     *
     * @mbggenerated Mon Sep 23 01:08:51 CST 2013
     */
    @DeleteProvider(type=FiAttachmentSqlProvider.class, method="deleteByExample")
    int deleteByExample(FiAttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_ATTACHMENT
     *
     * @mbggenerated Mon Sep 23 01:08:51 CST 2013
     */
    @Delete({
        "delete from FI_ATTACHMENT",
        "where ID = #{id,jdbcType=NUMERIC}"
    })
    int deleteByPrimaryKey(int id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_ATTACHMENT
     *
     * @mbggenerated Mon Sep 23 01:08:51 CST 2013
     */
    @Insert({
        "insert into FI_ATTACHMENT (ID, OLD_NAME, ",
        "STORE_NAME, URL, ",
        "FILE_ID, TYPE, KF_PARENT_ID, ",
        "PARENT_TYPE, STATUS, ",
        "START_TIME, END_TIME, ",
        "CARD_ID, CREATE_TIME, ",
        "EDIT_TIME, CREATE_USER, ",
        "EDIT_USER, IS_DEFER)",
        "values (#{id,jdbcType=NUMERIC}, #{oldName,jdbcType=VARCHAR}, ",
        "#{storeName,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, ",
        "#{fileId,jdbcType=VARCHAR}, #{type,jdbcType=NUMERIC}, #{kfParentId,jdbcType=VARCHAR}, ",
        "#{parentType,jdbcType=VARCHAR}, #{status,jdbcType=NUMERIC}, ",
        "#{startTime,jdbcType=TIMESTAMP}, #{endTime,jdbcType=TIMESTAMP}, ",
        "#{cardId,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{editTime,jdbcType=TIMESTAMP}, #{createUser,jdbcType=VARCHAR}, ",
        "#{editUser,jdbcType=VARCHAR}, #{isDefer,jdbcType=NUMERIC})"
    })
    @SelectKey(before=true,keyProperty="id",resultType=Integer.class,statement="select fiattachment_sequence.nextval as id from dual")
    int insert(FiAttachment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_ATTACHMENT
     *
     * @mbggenerated Mon Sep 23 01:08:51 CST 2013
     */
    @InsertProvider(type=FiAttachmentSqlProvider.class, method="insertSelective")
    int insertSelective(FiAttachment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_ATTACHMENT
     *
     * @mbggenerated Mon Sep 23 01:08:51 CST 2013
     */
    @SelectProvider(type=FiAttachmentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.NUMERIC, id=true),
        @Result(column="OLD_NAME", property="oldName", jdbcType=JdbcType.VARCHAR),
        @Result(column="STORE_NAME", property="storeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="URL", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="FILE_ID", property="fileId", jdbcType=JdbcType.VARCHAR),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.NUMERIC),
        @Result(column="KF_PARENT_ID", property="kfParentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="PARENT_TYPE", property="parentType", jdbcType=JdbcType.VARCHAR),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.NUMERIC),
        @Result(column="START_TIME", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="END_TIME", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CARD_ID", property="cardId", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="EDIT_TIME", property="editTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATE_USER", property="createUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="EDIT_USER", property="editUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_DEFER", property="isDefer", jdbcType=JdbcType.NUMERIC)
    })
    List<FiAttachment> selectByExample(FiAttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_ATTACHMENT
     *
     * @mbggenerated Mon Sep 23 01:08:51 CST 2013
     */
    @Select({
        "select",
        "ID, OLD_NAME, STORE_NAME, URL, FILE_ID, TYPE, KF_PARENT_ID, PARENT_TYPE, STATUS, ",
        "START_TIME, END_TIME, CARD_ID, CREATE_TIME, EDIT_TIME, CREATE_USER, EDIT_USER, ",
        "IS_DEFER",
        "from FI_ATTACHMENT",
        "where ID = #{id,jdbcType=NUMERIC}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.NUMERIC, id=true),
        @Result(column="OLD_NAME", property="oldName", jdbcType=JdbcType.VARCHAR),
        @Result(column="STORE_NAME", property="storeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="URL", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="FILE_ID", property="fileId", jdbcType=JdbcType.VARCHAR),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.NUMERIC),
        @Result(column="KF_PARENT_ID", property="kfParentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="PARENT_TYPE", property="parentType", jdbcType=JdbcType.VARCHAR),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.NUMERIC),
        @Result(column="START_TIME", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="END_TIME", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CARD_ID", property="cardId", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="EDIT_TIME", property="editTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATE_USER", property="createUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="EDIT_USER", property="editUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_DEFER", property="isDefer", jdbcType=JdbcType.NUMERIC)
    })
    FiAttachment selectByPrimaryKey(int id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_ATTACHMENT
     *
     * @mbggenerated Mon Sep 23 01:08:51 CST 2013
     */
    @UpdateProvider(type=FiAttachmentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FiAttachment record, @Param("example") FiAttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_ATTACHMENT
     *
     * @mbggenerated Mon Sep 23 01:08:51 CST 2013
     */
    @UpdateProvider(type=FiAttachmentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FiAttachment record, @Param("example") FiAttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_ATTACHMENT
     *
     * @mbggenerated Mon Sep 23 01:08:51 CST 2013
     */
    @UpdateProvider(type=FiAttachmentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FiAttachment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_ATTACHMENT
     *
     * @mbggenerated Mon Sep 23 01:08:51 CST 2013
     */
    @Update({
        "update FI_ATTACHMENT",
        "set OLD_NAME = #{oldName,jdbcType=VARCHAR},",
          "STORE_NAME = #{storeName,jdbcType=VARCHAR},",
          "URL = #{url,jdbcType=VARCHAR},",
          "FILE_ID = #{fileId,jdbcType=VARCHAR},",
          "TYPE = #{type,jdbcType=NUMERIC},",
          "KF_PARENT_ID = #{kfParentId,jdbcType=VARCHAR},",
          "PARENT_TYPE = #{parentType,jdbcType=VARCHAR},",
          "STATUS = #{status,jdbcType=NUMERIC},",
          "START_TIME = #{startTime,jdbcType=TIMESTAMP},",
          "END_TIME = #{endTime,jdbcType=TIMESTAMP},",
          "CARD_ID = #{cardId,jdbcType=VARCHAR},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "EDIT_TIME = #{editTime,jdbcType=TIMESTAMP},",
          "CREATE_USER = #{createUser,jdbcType=VARCHAR},",
          "EDIT_USER = #{editUser,jdbcType=VARCHAR},",
          "IS_DEFER = #{isDefer,jdbcType=NUMERIC}",
        "where ID = #{id,jdbcType=NUMERIC}"
    })
    int updateByPrimaryKey(FiAttachment record);
}