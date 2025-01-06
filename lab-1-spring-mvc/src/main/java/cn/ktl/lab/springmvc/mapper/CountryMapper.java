package cn.ktl.lab.springmvc.mapper;

import cn.ktl.lab.springmvc.model.Country;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

/**
* @author mingquan.liu
* @description 针对表【um_country】的数据库操作Mapper
* @createDate 2024-09-27 14:11:26
* @Entity generator.domain.Country
*/
@Mapper
public interface CountryMapper extends BaseMapper<Country> {

}




