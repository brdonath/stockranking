package org.hibernate.dialect;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Bruno on 10/08/14.
 */
public class BigDecimalToStringType implements UserType {

    private static final int[] SQL_TYPES = {Types.VARCHAR};

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    /**
     *
     * @return
     */
    @Override
    public Class<BigDecimal> returnedClass() {
        return BigDecimal.class;
    }

    @Override
    public Object nullSafeGet(final ResultSet resultSet, final String[] names, final SessionImplementor si, final Object owner) throws HibernateException, SQLException {
        final String value = StandardBasicTypes.STRING.nullSafeGet(resultSet, names[0], si);
        if (value != null) {
            return new BigDecimal(value);
        }
        return null;
    }

    @Override
    public void nullSafeSet(final PreparedStatement preparedStatement, final Object value, final int index, final SessionImplementor si) throws HibernateException, SQLException {
        if (null == value) {
            preparedStatement.setNull(index, Types.VARCHAR);
        } else {
            StandardBasicTypes.STRING.nullSafeSet(preparedStatement, ((BigDecimal) value).toPlainString(), index, si);
        }
    }

    @Override
    public Object deepCopy(final Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Serializable disassemble(final Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
        return original;
    }

    @Override
    public int hashCode(final Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public boolean equals(final Object x, final Object y) throws HibernateException {
        if (x == y) {
            return true;
        }
        if (null == x || null == y) {
            return false;
        }
        return x.equals(y);
    }
}