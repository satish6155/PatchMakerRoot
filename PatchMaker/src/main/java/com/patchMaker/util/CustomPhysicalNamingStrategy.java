package com.patchMaker.util;


import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl
{
    private static final long serialVersionUID = 6890554030847046251L;
    
    public Identifier toPhysicalTableName(final Identifier name, final JdbcEnvironment context) {
        final String underScoreText = addUnderscores(name.getText());
        return new Identifier(this.transformToPluralForm(underScoreText), name.isQuoted());
    }
    
    public Identifier toPhysicalColumnName(final Identifier name, final JdbcEnvironment context) {
        final String underScoreText = addUnderscores(name.getText());
        return new Identifier(this.transformToPluralForm(underScoreText), name.isQuoted());
    }
    
    public Identifier toPhysicalSequenceName(final Identifier name, final JdbcEnvironment context) {
        final String underScoreText = addUnderscores(name.getText());
        return new Identifier(this.transformToPluralForm(underScoreText), name.isQuoted());
    }
    
    private String transformToPluralForm(final String tableNameInSingularForm) {
        return DataBaseMappingUtil.abbreviateName(tableNameInSingularForm);
    }
    
    protected static String addUnderscores(final String name) {
        final StringBuilder buf = new StringBuilder(name.replace('.', '_'));
        for (int i = 1; i < buf.length() - 1; ++i) {
            if (Character.isLowerCase(buf.charAt(i - 1)) && Character.isUpperCase(buf.charAt(i)) && Character.isLowerCase(buf.charAt(i + 1))) {
                buf.insert(i++, '_');
            }
        }
        return buf.toString().toLowerCase();
    }
}
