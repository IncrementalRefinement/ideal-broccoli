package com.example.idealbroccoli.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    private String jobType;

    private Boolean isScheduledJob;

    private Long executeRate;

    private String jarFilePath;

    private String mainClassPath;

    private String jobName;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public Boolean getScheduledJob() {
        return isScheduledJob;
    }

    public void setScheduledJob(Boolean scheduledJob) {
        isScheduledJob = scheduledJob;
    }

    public Long getExecuteRate() {
        return executeRate;
    }

    public void setExecuteRate(Long executeRate) {
        this.executeRate = executeRate;
    }

    public String getJarFilePath() {
        return jarFilePath;
    }

    public void setJarFilePath(String jarFilePath) {
        this.jarFilePath = jarFilePath;
    }

    public String getMainClassPath() {
        return mainClassPath;
    }

    public void setMainClassPath(String mainClassPath) {
        this.mainClassPath = mainClassPath;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
