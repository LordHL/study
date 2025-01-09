package cn.ktl.lab.springmvc.base.db;

import cn.hutool.crypto.symmetric.AES;
import cn.hutool.extra.spring.SpringUtil;

import cn.ktl.lab.springmvc.utils.EncryptionUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 字段字段的 TypeHandler 实现类，基于 {@link AES} 实现
 *
 */
public class EncryptTypeHandler extends BaseTypeHandler<String> {
    private final EncryptionUtil encryptionUtil;

    public EncryptTypeHandler() {
        this.encryptionUtil = SpringUtil.getBean(EncryptionUtil.class);
        if (this.encryptionUtil == null) {
            throw new IllegalStateException("EncryptionUtil bean could not be found in Spring context");
        }
    }
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, encryptionUtil.encrypt(parameter));
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return encryptionUtil.decrypt(value);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return encryptionUtil.decrypt(value);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return encryptionUtil.decrypt(value);
    }

}
