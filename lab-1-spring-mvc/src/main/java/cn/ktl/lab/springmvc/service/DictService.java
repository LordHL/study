package cn.ktl.lab.springmvc.service;

import cn.hutool.core.collection.CollUtil;
import cn.ktl.lab.springmvc.mapper.DictMapper;
import cn.ktl.lab.springmvc.model.Dict;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author lin ho
 * Des: TODO
 */
@Service
@RequiredArgsConstructor
public class DictService {

    private final DictMapper dictMapper;

    public Map<String, Dict> getDict(){

        return null;
    }
}
