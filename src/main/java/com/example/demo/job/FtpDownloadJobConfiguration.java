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

import com.example.demo.tasklet.FtpDownloadTasklet;
import com.example.demo.tasklet.WriteLogTasklet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class FtpDownloadJobConfiguration {

	private final JobBuilderFactory jobBuilderFactory;

	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job ftpDownloadJob() {
		return jobBuilderFactory.get("ftpDownloadJob")
				.start(readResource(null))
				.next(processResource())
				.next(writeLog())
				.build();
	}

	@Bean
	@JobScope
	public Step readResource(@Value("#{jobParameters[version]}") String version) {
		return stepBuilderFactory.get("readResource").tasklet(readResourceTasklet(version)).build();
	}

	@Bean
	public Step processResource() {
		return stepBuilderFactory.get("processResource").tasklet(processResourceTasklet()).build();
	}

	@Bean
	public Step writeLog() {
		return stepBuilderFactory.get("writeLog").tasklet(writeLogTasklet()).build();
	}

    private Tasklet readResourceTasklet(String version) {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                log.info(">>>>> This is Step1");
                log.info(">>>>> version = {}", version);
                return RepeatStatus.FINISHED;
            }
        };
    }

	@Bean
	public Tasklet processResourceTasklet() {
		return new FtpDownloadTasklet();
	}

	@Bean
	public Tasklet writeLogTasklet() {
		return new WriteLogTasklet();
	}
}
