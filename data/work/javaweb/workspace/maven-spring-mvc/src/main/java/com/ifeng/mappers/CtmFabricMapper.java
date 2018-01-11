package com.ifeng.mappers;

import com.ifeng.entitys.CtmFabric;
import com.ifeng.entitys.CtmFabricExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtmFabricMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cmt_fabric
	 * @mbg.generated  Wed Jan 10 08:55:54 CST 2018
	 */
	long countByExample(CtmFabricExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cmt_fabric
	 * @mbg.generated  Wed Jan 10 08:55:54 CST 2018
	 */
	int deleteByExample(CtmFabricExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cmt_fabric
	 * @mbg.generated  Wed Jan 10 08:55:54 CST 2018
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cmt_fabric
	 * @mbg.generated  Wed Jan 10 08:55:54 CST 2018
	 */
	int insert(CtmFabric record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cmt_fabric
	 * @mbg.generated  Wed Jan 10 08:55:54 CST 2018
	 */
	int insertSelective(CtmFabric record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cmt_fabric
	 * @mbg.generated  Wed Jan 10 08:55:54 CST 2018
	 */
	List<CtmFabric> selectByExample(CtmFabricExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cmt_fabric
	 * @mbg.generated  Wed Jan 10 08:55:54 CST 2018
	 */
	CtmFabric selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cmt_fabric
	 * @mbg.generated  Wed Jan 10 08:55:54 CST 2018
	 */
	int updateByExampleSelective(@Param("record") CtmFabric record, @Param("example") CtmFabricExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cmt_fabric
	 * @mbg.generated  Wed Jan 10 08:55:54 CST 2018
	 */
	int updateByExample(@Param("record") CtmFabric record, @Param("example") CtmFabricExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cmt_fabric
	 * @mbg.generated  Wed Jan 10 08:55:54 CST 2018
	 */
	int updateByPrimaryKeySelective(CtmFabric record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cmt_fabric
	 * @mbg.generated  Wed Jan 10 08:55:54 CST 2018
	 */
	int updateByPrimaryKey(CtmFabric record);
}