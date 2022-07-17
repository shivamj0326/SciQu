package com.codegus.sciqu.batch;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.codegus.sciqu.entity.QuestionChoiceEntity;
import com.codegus.sciqu.model.QuestionJson;
import com.codegus.sciqu.repository.QuestionChoiceRepository;


@EnableBatchProcessing
@Configuration
public class BatchConfig {


    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private QuestionChoiceRepository questionChoiceRepository;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    @Bean
    public JsonItemReader<QuestionJson> jsonItemReader() {
        return new JsonItemReaderBuilder<QuestionJson>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(QuestionJson.class))
                .resource(new ClassPathResource("test.json"))
                .name("questionJsonItemReader")
                .build();
    }
    
    @Bean
    public QuestionJsonItemProcessor questionJsonItemProcessor(){
    	return new QuestionJsonItemProcessor();
    }

  
    public QuestionJsonItemProcessor processor() {
        return questionJsonItemProcessor();
    }

    @Bean
    public RepositoryItemWriter<List<QuestionChoiceEntity>> writer(){
		RepositoryItemWriter writer = new RepositoryItemWriter<>();
		writer.setRepository(questionChoiceRepository);
		writer.setMethodName("saveAll");
		return writer;
    	
    }
    
    @Bean
    public Job questionJob() {
        JobBuilder jobBuilder = jobBuilderFactory.get("QuestionsIngestorBatch");
        jobBuilder.incrementer(new RunIdIncrementer());
        FlowJobBuilder flowJobBuilder = jobBuilder.flow(getFirstStep()).end();
        Job job = flowJobBuilder.build();
        return job;
    }

    @Bean
    public Step getFirstStep() {
        
        
        return stepBuilderFactory.get("getFirstStep").<QuestionJson, List<QuestionChoiceEntity>>chunk(10).reader(jsonItemReader()).processor(processor()).writer(writer()).build();
    }

}