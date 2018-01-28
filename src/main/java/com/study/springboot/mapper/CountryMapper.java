package com.study.springboot.mapper;

import com.study.springboot.domain.Country;

public interface CountryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbggenerated Sun Jan 28 16:13:24 CST 2018
     */
    int deleteByPrimaryKey(String code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbggenerated Sun Jan 28 16:13:24 CST 2018
     */
    int insert(Country record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbggenerated Sun Jan 28 16:13:24 CST 2018
     */
    int insertSelective(Country record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbggenerated Sun Jan 28 16:13:24 CST 2018
     */
    Country selectByPrimaryKey(String code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbggenerated Sun Jan 28 16:13:24 CST 2018
     */
    int updateByPrimaryKeySelective(Country record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table country
     *
     * @mbggenerated Sun Jan 28 16:13:24 CST 2018
     */
    int updateByPrimaryKey(Country record);
}