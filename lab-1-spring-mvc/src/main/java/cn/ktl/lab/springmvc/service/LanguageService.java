package cn.ktl.lab.springmvc.service;

import cn.hutool.core.collection.CollUtil;
import cn.ktl.lab.springmvc.mapper.LanguageMapper;
import cn.ktl.lab.springmvc.model.Country;
import cn.ktl.lab.springmvc.model.Language;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author lin ho
 * Des: TODO
 */
@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageMapper languageMapper;

    public Map<String, Language> getLanguageCache(){
        List<Language> languages = languageMapper.selectList(Wrappers.<Language>lambdaQuery());
        if (CollUtil.isEmpty(languages)){
            return new HashMap<>();
        }else {
            return languages.stream().collect(Collectors.toMap(Language::getLocalCode, Function.identity(),(k1, k2)-> k1));
        }
    }
}
