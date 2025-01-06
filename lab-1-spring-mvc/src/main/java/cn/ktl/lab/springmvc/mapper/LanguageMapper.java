package cn.ktl.lab.springmvc.mapper;

import cn.ktl.lab.springmvc.model.Language;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

/**
* @author mingquan.liu
* @description 针对表【um_language】的数据库操作Mapper
* @createDate 2024-09-27 13:29:04
* @Entity com.oneforma.usermanagement.model.Language
*/
@Mapper
public interface LanguageMapper extends BaseMapper<Language> {

}




