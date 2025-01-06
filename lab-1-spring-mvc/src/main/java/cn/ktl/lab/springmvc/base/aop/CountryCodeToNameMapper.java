package cn.ktl.lab.springmvc.base.aop;

import cn.ktl.lab.springmvc.service.CountryService;
import org.springframework.stereotype.Component;

/**
 * @Author lin ho
 * Des: TODO
 */


@Component
public class CountryCodeToNameMapper implements FieldMapper<String, String> {

    private final CountryService countryService;

    public CountryCodeToNameMapper(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public String map(String countryCode) {
        if (countryCode == null) {
            return null;
        }
        return countryService.getCountryCache().get(countryCode).getName();
    }
}

