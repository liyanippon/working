package com.ifeng.mappers;

import com.ifeng.entitys.DmsDocument;
import com.ifeng.entitys.DmsDocumentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DmsDocumentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    long countByExample(DmsDocumentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    int deleteByExample(DmsDocumentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    int insert(DmsDocument record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    int insertSelective(DmsDocument record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    List<DmsDocument> selectByExampleWithBLOBs(DmsDocumentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    List<DmsDocument> selectByExample(DmsDocumentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    DmsDocument selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    int updateByExampleSelective(@Param("record") DmsDocument record, @Param("example") DmsDocumentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") DmsDocument record, @Param("example") DmsDocumentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    int updateByExample(@Param("record") DmsDocument record, @Param("example") DmsDocumentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    int updateByPrimaryKeySelective(DmsDocument record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(DmsDocument record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dms_document
     *
     * @mbg.generated Wed Jan 17 11:21:59 CST 2018
     */
    int updateByPrimaryKey(DmsDocument record);
}