package com.patchMaker.util;

import java.util.Map;
import java.util.Properties;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.relational.QualifiedName;
import org.hibernate.boot.model.relational.QualifiedNameImpl;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;

public class CustomSequenceGenerator extends SequenceStyleGenerator
{
    public static final String ENTITY_TARGET_TABLE_NAME = "target_table";
    
/*    private static void setIsPostgresDb(final Boolean isPostgresDb) {
        NeutrinoSequenceGenerator.isPostgresDb = isPostgresDb;
    }
    
    private static void setDatabaseSequenceGenerator(final DatabaseSequenceGenerator databaseSequenceGenerator) {
        NeutrinoSequenceGenerator.databaseSequenceGenerator = databaseSequenceGenerator;
    }
    
    private static void setTransactionManager(final PlatformTransactionManager transactionManager) {
        NeutrinoSequenceGenerator.transactionManager = transactionManager;
    }*/
    
    protected QualifiedName determineSequenceName(final Properties params, final Dialect dialect, final JdbcEnvironment jdbcEnv) {
        final String sequencePerEntitySuffix = ConfigurationHelper.getString("sequence_per_entity_suffix", (Map)params, "_SEQ");
        String sequenceName = ConfigurationHelper.getBoolean("prefer_sequence_per_entity", (Map)params, false) ? (params.getProperty("target_table") + sequencePerEntitySuffix) : "hibernate_sequence";
        sequenceName = DataBaseMappingUtil.abbreviateName(sequenceName);
        sequenceName = ConfigurationHelper.getString("sequence_name", (Map)params, sequenceName);
        return (QualifiedName)new QualifiedNameImpl(jdbcEnv.getCurrentCatalog(), jdbcEnv.getCurrentSchema(), new Identifier(sequenceName, false));
    }
    
/*    public Serializable generate(final SharedSessionContractImplementor session, final Object object) {
        if (NeutrinoSequenceGenerator.isPostgresDb == null) {
            final DatabaseSequenceGenerator dbSequenceGenerator = (DatabaseSequenceGenerator)NeutrinoSpringAppContextUtil.getBeanByName("neutrinoSequenceGenerator", (Class)DatabaseSequenceGenerator.class);
            final String dbType = dbSequenceGenerator.getDatabaseType();
            if ("postgres".equals(dbType)) {
                setIsPostgresDb(true);
                setDatabaseSequenceGenerator(dbSequenceGenerator);
                setTransactionManager((PlatformTransactionManager)NeutrinoSpringAppContextUtil.getBeanByName("transactionManager", (Class)PlatformTransactionManager.class));
            }
            else {
                setIsPostgresDb(false);
            }
        }
        if (NeutrinoSequenceGenerator.isPostgresDb && TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            final String sequenceName = super.getDatabaseStructure().getName();
            final TransactionTemplate template = new TransactionTemplate(NeutrinoSequenceGenerator.transactionManager);
            template.setPropagationBehavior(3);
            return (Serializable)template.execute(status -> NeutrinoSequenceGenerator.databaseSequenceGenerator.getNextValue(sequenceName));
        }
        return super.generate(session, object);
    }*/
}

