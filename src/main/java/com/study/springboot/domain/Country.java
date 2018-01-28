package com.study.springboot.domain;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {

    private String code;


    private String name;


    private String continent;


    private String region;


    private Float surfacearea;


    private Short indepyear;


    private Integer population;


    private Float lifeexpectancy;


    private Float gnp;


    private Float gnpold;


    private String localname;


    private String governmentform;


    private String headofstate;


    private Integer capital;

    private String code2;


}