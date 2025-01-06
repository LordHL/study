package cn.ktl.lab.springmvc.service;

import cn.hutool.core.collection.CollUtil;
import cn.ktl.lab.springmvc.mapper.CountryMapper;
import cn.ktl.lab.springmvc.model.Country;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
public class CountryService {
    private final CountryMapper countryMapper;
    public Map<String, Country> getCountryCache(){
        LambdaQueryWrapper<Country> qw = Wrappers.<Country>lambdaQuery();
        List<Country> countries = countryMapper.selectList(qw);
        if (CollUtil.isEmpty(countries)){
            return new HashMap<>();
        }else {
            return countries.stream().collect(Collectors.toMap(Country::getIso2, Function.identity(),(k1,k2)-> k1));
        }
    }
}
