package com.lucene.demo.hibernate.search;

import org.springframework.context.annotation.Bean;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucene.demo.hibernate.search.config.SearchIndexBuild;

@SpringBootApplication
public class HibernateSearchDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateSearchDemoApplication.class, args);
	}

	/**
	 * Creation of the bean that will create all Lucene indexes
	 */
	@Bean
	public ApplicationRunner buildIndex(SearchIndexBuild searchIndexBuild) {
		return (ApplicationArguments args) -> {
			searchIndexBuild.indexPersistedData();
		};
	}

}
