package com.example.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SimpleJobConfiguration {

	private final JobBuilderFactory jobBuilderFactory;

	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job simpleJob() {
		return jobBuilderFactory.get("simpleJob")
				.start(step1(null))
				.next(step2())
				.next(step3())
				.build();
	}

	@Bean
	@JobScope
	public Step step1(@Value("#{jobParameters[requestDate]}") String requestDate) {
		return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				log.info(">>>>> This is Step1");
				log.info(">>>>> RequestDate = {}", requestDate);
				return RepeatStatus.FINISHED;
			}
		}).build();

//		return stepBuilderFactory.get("simpleStep").tasklet((contribution, chunkContext) -> {
//		    log.info(">>>>> This is Step1");
//		    return RepeatStatus.FINISHED;
//		}).build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				log.info(">>>>> This is Step2");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step step3() {
		return stepBuilderFactory.get("step3").tasklet(step3Tasklet()).build();
	}

	@Bean
	public Tasklet step3Tasklet() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				log.info(">>>>> This is Step3");
				return RepeatStatus.FINISHED;
			}
		};
	}
}
