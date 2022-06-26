package com.example.aop;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

/**
 * @Title: MetadataExtractorIntegrator
 * @Package com/example/aop/MetadataExtractorIntegrator.java
 * @Description: 获取数据库源信息
 * 参考: https://vladmihalcea.com/how-to-get-the-entity-mapping-to-database-table-binding-metadata-from-hibernate/
 * The HibernateMetadata class uses Hibernate's Metadata API to get information about entity mappings and relationships.
 * This information should be consistent .
 * 里边只有配置了映射的表信息
 * @author zhaozhiwei
 * @date 2022/6/17 下午3:40
 * @version V1.0
 */
public class MetadataExtractorIntegrator implements org.hibernate.integrator.spi.Integrator {

    public static final MetadataExtractorIntegrator INSTANCE = new MetadataExtractorIntegrator();

    private Database database;

    private Metadata metadata;

    public Database getDatabase() {
        return database;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        this.database = metadata.getDatabase();
        this.metadata = metadata;
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {}
}
