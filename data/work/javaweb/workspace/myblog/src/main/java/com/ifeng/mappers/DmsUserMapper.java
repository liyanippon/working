package com.ifeng.mappers;

import com.ifeng.entitys.DmsUser;
import com.ifeng.entitys.DmsUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DmsUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_user
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    long countByExample(DmsUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_user
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    int deleteByExample(DmsUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_user
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_user
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    int insert(DmsUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_user
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    int insertSelective(DmsUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_user
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    List<DmsUser> selectByExample(DmsUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_user
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    DmsUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_user
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    int updateByExampleSelective(@Param("record") DmsUser record, @Param("example") DmsUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_user
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    int updateByExample(@Param("record") DmsUser record, @Param("example") DmsUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_user
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    int updateByPrimaryKeySelective(DmsUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_user
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    int updateByPrimaryKey(DmsUser record);
}