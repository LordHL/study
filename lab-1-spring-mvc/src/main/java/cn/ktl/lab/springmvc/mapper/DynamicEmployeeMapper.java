package cn.ktl.lab.springmvc.mapper;


import cn.ktl.lab.springmvc.model.DynamicEmployee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author lin ho
 * Des: DynamicEmployeeMapper
 */
@Mapper
public interface DynamicEmployeeMapper extends BaseMapper<DynamicEmployee> {
}
