package com.example.demo.tasklet;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class SimpleTasklet implements Tasklet, StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> beforeStep");
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> execute");
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> afterStep");
        return ExitStatus.COMPLETED;
    }
}
