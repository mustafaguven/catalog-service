package com.mg.catalog.config

import com.mg.catalog.repository.CategoryRepository
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@EnableMongoRepositories(basePackageClasses = [CategoryRepository::class])
@Configuration
class MongoDBConfig {
}