package com.study.springboot.domain;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryLanguageKey {

    protected String countrycode;

    protected String language;

}