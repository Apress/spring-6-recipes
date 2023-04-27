package com.apress.spring6recipes.springbatch.config;

import com.apress.spring6recipes.springbatch.UserRegistration;
import com.apress.spring6recipes.springbatch.UserRegistrationValidationItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;

@Configuration
public class UserJob {

	private static final String INSERT_REGISTRATION_QUERY = """
					insert into USER_REGISTRATION (FIRST_NAME, LAST_NAME, COMPANY, ADDRESS,CITY,STATE,ZIP,COUNTY,URL,PHONE_NUMBER,FAX)
					values 
					(:firstName,:lastName,:company,:address,:city,:state,:zip,:county,:url,:phoneNumber,:fax);""";

	private final JobRepository jobRepository;
	private final Resource input;

	public UserJob(JobRepository jobRepository,
					@Value("file:${user.home}/batches/registrations.csv") Resource input) {
		this.jobRepository = jobRepository;
		this.input=input;
	}

	@Bean
	public Job insertIntoDbFromCsvJob(Step step1) {
		var name = "User Registration Import Job";
		var builder = new JobBuilder(name, jobRepository);
		return builder.start(step1).build();
	}

	@Bean
	public Step step1(ItemReader<UserRegistration> reader,
			ItemProcessor<UserRegistration, UserRegistration> processor,
			ItemWriter<UserRegistration> writer,
			PlatformTransactionManager txManager) {
		var name = "User Registration CSV To DB Step";
		var builder = new StepBuilder(name, jobRepository);
		return builder
						.<UserRegistration, UserRegistration>chunk(5, txManager)
						.reader(reader)
						.processor(processor)
						.writer(writer)
						.build();
	}

	@Bean
	public FlatFileItemReader<UserRegistration> csvFileReader() {
		var names = new String[]{"firstName", "lastName", "company", "address", "city",
														 "state", "zip", "county", "url", "phoneNumber", "fax"};
		return new FlatFileItemReaderBuilder<UserRegistration>()
						.name(ClassUtils.getShortName(FlatFileItemReader.class))
						.resource(input)
						.targetType(UserRegistration.class)
						.delimited()
						.names(names)
						.build();
	}

	@Bean
	public UserRegistrationValidationItemProcessor validatingItemProcessor() {
		return new UserRegistrationValidationItemProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<UserRegistration> jdbcItemWriter(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<UserRegistration>()
						.dataSource(dataSource)
						.sql(INSERT_REGISTRATION_QUERY)
						.beanMapped()
						.build();
	}
}
