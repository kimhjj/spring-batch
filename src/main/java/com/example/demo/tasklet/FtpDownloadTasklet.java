package com.example.demo.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FtpDownloadTasklet implements Tasklet, InitializingBean {

	private Resource sourceDirectory;
	private Resource destDirectory;

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		//Assert.notNull(sourceDirectory, "directory must be set.");
	}

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> execute");
        return RepeatStatus.FINISHED;
    }

}
