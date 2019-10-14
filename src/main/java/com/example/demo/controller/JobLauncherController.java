package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class JobLauncherController {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	@Qualifier("ftpDownloadJob")
	Job ftpDownloadJob;

	/**
	 * Job 실행
	 * @param version
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/job/ftp")
	public ResponseEntity<?> handleJobFTP(@RequestParam("version") String version) throws Exception {
		try {
			JobParameters jobParameters = new JobParametersBuilder()
					.addString("version", version)
					//.addLong("time", System.currentTimeMillis())
					.toJobParameters();
			jobLauncher.run(ftpDownloadJob, jobParameters);
			return new ResponseEntity<>("DONE", HttpStatus.OK);

		} catch (JobInstanceAlreadyCompleteException e) {
			e.printStackTrace();
			Map<String, Object> result = new HashMap<>();
			result.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
			result.put("error", e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> result = new HashMap<>();
			result.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
			result.put("error",  e.getMessage() != null ? e.getCause().getMessage() : e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
