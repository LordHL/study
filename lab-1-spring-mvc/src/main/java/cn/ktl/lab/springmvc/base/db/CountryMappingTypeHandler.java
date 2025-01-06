package cn.ktl.lab.springmvc.base.db;

import cn.hutool.crypto.symmetric.AES;
import cn.hutool.extra.spring.SpringUtil;
import cn.ktl.lab.springmvc.model.Country;
import cn.ktl.lab.springmvc.service.CountryService;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * 字段字段的 TypeHandler 实现类，基于 {@link AES} 实现
 *
 */
public class CountryMappingTypeHandler extends BaseTypeHandler<String> {

//    private final CountryService countryService;
//
//    public CountryMappingTypeHandler(CountryService countryService) {
//        this.countryService = countryService;
//    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter);
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        CountryService countryService = SpringUtil.getBean(CountryService.class);
        Map<String, Country> countryCache = countryService.getCountryCache();

        return countryCache.get(value).getName();
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        CountryService countryService = SpringUtil.getBean(CountryService.class);
        Map<String, Country> countryCache = countryService.getCountryCache();

        return countryCache.get(value).getName();
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        CountryService countryService = SpringUtil.getBean(CountryService.class);
        Map<String, Country> countryCache = countryService.getCountryCache();
        return countryCache.get(value).getName();
    }

}
