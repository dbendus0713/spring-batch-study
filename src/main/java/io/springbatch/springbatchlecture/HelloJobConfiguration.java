package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class HelloJobConfiguration {
  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job helloJob() {
    return jobBuilderFactory.get("helloJob")
        .start(helloStep1())
        .next(helloStep2())
        .build();
  }

  @Bean
  public Step helloStep1() {
    return stepBuilderFactory.get("helloStep1")
        .tasklet(new Tasklet() {
          @Override
          public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            log.info(" ======================");
            log.info(" --> Hello spring batch");
            log.info(" ======================");
            return RepeatStatus.FINISHED; // equals to NULL (execute once)
          }
        }).build();
  }

  @Bean
  public Step helloStep2() {
    return stepBuilderFactory.get("helloStep2")
        .tasklet(new Tasklet() {
          @Override
          public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            log.info(" ======================");
            log.info(" --> Step 2 was executed");
            log.info(" ======================");
            return RepeatStatus.FINISHED; // equals to NULL (execute once)
          }
        }).build();
  }
}
