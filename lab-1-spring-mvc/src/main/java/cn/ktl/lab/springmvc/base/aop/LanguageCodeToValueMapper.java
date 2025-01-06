package cn.ktl.lab.springmvc.base.aop;

import cn.ktl.lab.springmvc.service.LanguageService;
import org.springframework.stereotype.Component;

/**
 * @Author lin ho
 * Des: TODO
 */


@Component
public class LanguageCodeToValueMapper implements FieldMapper<String, String> {

    private final LanguageService languageService;

    public LanguageCodeToValueMapper(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Override
    public String map(String localCode) {
        if (localCode == null) {
            return null;
        }
        return languageService.getLanguageCache().get(localCode).getValue();
    }
}

