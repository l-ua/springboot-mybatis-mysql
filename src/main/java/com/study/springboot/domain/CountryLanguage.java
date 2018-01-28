package com.study.springboot.domain;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CountryLanguage extends CountryLanguageKey {

    private String isofficial;


    private Float percentage;

}