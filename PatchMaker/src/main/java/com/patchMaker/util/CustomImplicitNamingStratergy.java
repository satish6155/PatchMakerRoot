package com.patchMaker.util;


import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitCollectionTableNameSource;
import org.hibernate.boot.model.naming.ImplicitJoinColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitJoinTableNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;

public class CustomImplicitNamingStratergy extends ImplicitNamingStrategyJpaCompliantImpl
{
    private static final long serialVersionUID = 6890554030847046251L;
    
    public Identifier determineCollectionTableName(final ImplicitCollectionTableNameSource source) {
        final String entityName = this.transformEntityName(source.getOwningEntityNaming());
        final String propertyName = this.transformAttributePath(source.getOwningAttributePath());
        final String nameTemp = entityName + '_' + propertyName;
        return this.toIdentifier(nameTemp, source.getBuildingContext());
    }
    
    public Identifier determineJoinTableName(final ImplicitJoinTableNameSource source) {
        final String propertyName = this.transformAttributePath(source.getAssociationOwningAttributePath());
        final String nameTemp = source.getOwningPhysicalTableName() + '_' + propertyName;
        return this.toIdentifier(nameTemp, source.getBuildingContext());
    }
    
    public Identifier determineJoinColumnName(final ImplicitJoinColumnNameSource source) {
        String name = null;
        if (source.getNature() == ImplicitJoinColumnNameSource.Nature.ELEMENT_COLLECTION || source.getAttributePath() == null) {
            name = this.transformEntityName(source.getEntityNaming());
        }
        else {
            name = this.transformAttributePath(source.getAttributePath());
        }
        return this.toIdentifier(name, source.getBuildingContext());
    }
}
