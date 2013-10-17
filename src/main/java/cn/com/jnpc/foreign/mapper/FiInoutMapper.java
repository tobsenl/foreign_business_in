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

import cn.com.jnpc.foreign.mapperprovider.FiInoutSqlProvider;
import cn.com.jnpc.foreign.model.FiInoutExample;
import cn.com.jnpc.foreign.po.FiInout;

public interface FiInoutMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INOUT
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    @SelectProvider(type=FiInoutSqlProvider.class, method="countByExample")
    int countByExample(FiInoutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INOUT
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    @DeleteProvider(type=FiInoutSqlProvider.class, method="deleteByExample")
    int deleteByExample(FiInoutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INOUT
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    @Delete({
        "delete from FI_INOUT",
        "where ID = #{id,jdbcType=NUMERIC}"
    })
    int deleteByPrimaryKey(int id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INOUT
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    @Insert({
        "insert into FI_INOUT (ID, BEGIN_TIME, ",
        "END_TIME, FK_FOREIGNER_ID, ",
        "FK_INVITATION_ID, TYPE, ",
        "CONTENT, STATUS, ",
        "CREATE_DATE, CREATE_USER, ",
        "EDIT_DATE, EDIT_USER)",
        "values (#{id,jdbcType=NUMERIC}, #{beginTime,jdbcType=TIMESTAMP}, ",
        "#{endTime,jdbcType=TIMESTAMP}, #{fkForeignerId,jdbcType=VARCHAR}, ",
        "#{fkInvitationId,jdbcType=VARCHAR}, #{type,jdbcType=NUMERIC}, ",
        "#{content,jdbcType=VARCHAR}, #{status,jdbcType=NUMERIC}, ",
        "#{createDate,jdbcType=TIMESTAMP}, #{createUser,jdbcType=VARCHAR}, ",
        "#{editDate,jdbcType=TIMESTAMP}, #{editUser,jdbcType=VARCHAR})"
    })
    @SelectKey(before=true,keyProperty="id",resultType=Integer.class,statement="select inout_sequence.nextval from dual")
    int insert(FiInout record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INOUT
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    @InsertProvider(type=FiInoutSqlProvider.class, method="insertSelective")
    int insertSelective(FiInout record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INOUT
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    @SelectProvider(type=FiInoutSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.NUMERIC, id=true),
        @Result(column="BEGIN_TIME", property="beginTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="END_TIME", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="FK_FOREIGNER_ID", property="fkForeignerId", jdbcType=JdbcType.VARCHAR),
        @Result(column="FK_INVITATION_ID", property="fkInvitationId", jdbcType=JdbcType.VARCHAR),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.NUMERIC),
        @Result(column="CONTENT", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.NUMERIC),
        @Result(column="CREATE_DATE", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATE_USER", property="createUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="EDIT_DATE", property="editDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="EDIT_USER", property="editUser", jdbcType=JdbcType.VARCHAR)
    })
    List<FiInout> selectByExample(FiInoutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INOUT
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    @Select({
        "select",
        "ID, BEGIN_TIME, END_TIME, FK_FOREIGNER_ID, FK_INVITATION_ID, TYPE, CONTENT, ",
        "STATUS, CREATE_DATE, CREATE_USER, EDIT_DATE, EDIT_USER",
        "from FI_INOUT",
        "where ID = #{id,jdbcType=NUMERIC}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.NUMERIC, id=true),
        @Result(column="BEGIN_TIME", property="beginTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="END_TIME", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="FK_FOREIGNER_ID", property="fkForeignerId", jdbcType=JdbcType.VARCHAR),
        @Result(column="FK_INVITATION_ID", property="fkInvitationId", jdbcType=JdbcType.VARCHAR),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.NUMERIC),
        @Result(column="CONTENT", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.NUMERIC),
        @Result(column="CREATE_DATE", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATE_USER", property="createUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="EDIT_DATE", property="editDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="EDIT_USER", property="editUser", jdbcType=JdbcType.VARCHAR)
    })
    FiInout selectByPrimaryKey(int id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INOUT
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    @UpdateProvider(type=FiInoutSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FiInout record, @Param("example") FiInoutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INOUT
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    @UpdateProvider(type=FiInoutSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FiInout record, @Param("example") FiInoutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INOUT
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    @UpdateProvider(type=FiInoutSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FiInout record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FI_INOUT
     *
     * @mbggenerated Sun Sep 22 22:28:01 CST 2013
     */
    @Update({
        "update FI_INOUT",
        "set BEGIN_TIME = #{beginTime,jdbcType=TIMESTAMP},",
          "END_TIME = #{endTime,jdbcType=TIMESTAMP},",
          "FK_FOREIGNER_ID = #{fkForeignerId,jdbcType=VARCHAR},",
          "FK_INVITATION_ID = #{fkInvitationId,jdbcType=VARCHAR},",
          "TYPE = #{type,jdbcType=NUMERIC},",
          "CONTENT = #{content,jdbcType=VARCHAR},",
          "STATUS = #{status,jdbcType=NUMERIC},",
          "CREATE_DATE = #{createDate,jdbcType=TIMESTAMP},",
          "CREATE_USER = #{createUser,jdbcType=VARCHAR},",
          "EDIT_DATE = #{editDate,jdbcType=TIMESTAMP},",
          "EDIT_USER = #{editUser,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=NUMERIC}"
    })
    int updateByPrimaryKey(FiInout record);
}