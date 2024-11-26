package cn.ktl.lab.springmvc.mapper;

import cn.ktl.lab.springmvc.model.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author lin ho
 * Des: EmployeeMapper
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
