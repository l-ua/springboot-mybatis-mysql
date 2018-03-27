package com.study.springboot.mapper;


import com.study.springboot.domain.City;
import org.springframework.stereotype.Repository;

@Repository
public interface CityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Sun Jan 28 16:13:24 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Sun Jan 28 16:13:24 CST 2018
     */
    int insert(City record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Sun Jan 28 16:13:24 CST 2018
     */
    int insertSelective(City record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Sun Jan 28 16:13:24 CST 2018
     */
    City selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Sun Jan 28 16:13:24 CST 2018
     */
    int updateByPrimaryKeySelective(City record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Sun Jan 28 16:13:24 CST 2018
     */
    int updateByPrimaryKey(City record);
}