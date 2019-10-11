package com.example.demo.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadResourceTasklet implements Tasklet {

	String version;

	public ReadResourceTasklet(String version) {
		this.version = version;
	}

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
    	log.info(">>>>> This is Step1");
		return RepeatStatus.FINISHED;
    }

}
