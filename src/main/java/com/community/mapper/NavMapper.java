package com.community.mapper;

import com.community.model.Nav;
import com.community.model.NavExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface NavMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nav
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    int countByExample(NavExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nav
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    int deleteByExample(NavExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nav
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nav
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    int insert(Nav record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nav
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    int insertSelective(Nav record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nav
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    List<Nav> selectByExample(NavExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nav
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    Nav selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nav
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    int updateByExampleSelective(@Param("record") Nav record, @Param("example") NavExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nav
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    int updateByExample(@Param("record") Nav record, @Param("example") NavExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nav
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    int updateByPrimaryKeySelective(Nav record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nav
     *
     * @mbggenerated Sun Apr 24 15:17:33 CST 2022
     */
    int updateByPrimaryKey(Nav record);
}