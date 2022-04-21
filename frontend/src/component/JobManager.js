import React, { Component } from 'react';
import { Button, Space, Table, Tag, message } from 'antd';

import RecordManager from "./RecordManager";
import CreateJobManager from "./CreateJobManager";

class JobManager extends Component {

  columns = () => {
    return [
      {
        title: 'Job ID',
        dataIndex: 'jobId',
        key: 'jobId',
      },
      {
          title: 'Job Type',
          dataIndex: 'jobType',
          key: 'jobType',
          render: jobType => {
            let color, text;
            if (jobType == "flink") {
              color = 'purple';
              text = 'FLINK';
            } else if (jobType == "spark"){
              color = 'orange';
              text = 'SPARK';
            }
    
            return (
              <Tag color={color}>
                {text}
              </Tag>
            )
          },
      },
      {
          title: 'Scheduled Job',
          dataIndex: 'isScheduledJob',
          key: 'scheduledJob',
          render: scheduledJob => {
            let color, text;
            if (scheduledJob) {
              color = 'green';
              text = 'TRUE';
            } else {
              color = 'geekblue';
              text = 'FALSE';
            }
    
            return (
              <Tag color={color}>
                {text}
              </Tag>
            )
          },
      },
      {
          title: 'Execute Rate(ms)',
          dataIndex: 'executeRate',
          key: 'executeRate',
      },
      {
          title: 'Job Name',
          dataIndex: 'jobName',
          key: 'jobName',
      },
      {
          title: 'Remark',
          dataIndex: 'remark',
          key: 'remark',
      },
      {
        title: 'Query Record',
        dataIndex: 'queryRecord',
        key: 'queryRecord',
        render: (text, record) => {
          return (
            <Button onClick={this.handleQueryRecord(record)} type="primary">
              Query
            </Button>
          )
        }
      },
      {
        title: 'Delete',
        dataIndex: 'delete',
        key: 'delete',
        render: (text, record) => {
          return (
            <Button onClick={this.handleDeleteJob(record)} type="primary" danger>
              Delete
            </Button>
          )
        }
      },
    ]
  }
  
  constructor(props) {
    super(props);
    this.state = {
      isRecordManagerVisible: false,
      currentJobId: undefined,
      isCreateJobManagerVisible: false,
      jobList: [
        {
          "jobId": 0,
          "jobType": "spark",
          "isScheduledJob": false,
          "executeRate": 10000,
          "jarFilePath": "TODO",
          "mainClassPath": "TODO",
          "jobName": "testJob2",
          "remark": "this job is for test222"
      }
      ]
    }
  }
  
  handleCloseQueryRecord = () => {
    this.setState({isRecordManagerVisible: false})
  }

  handleQueryRecord = (record) => {
    const ret = () => {
      this.setState({currentJobId: record['jobId']})
      this.setState({isRecordManagerVisible: true})
    }
    return ret;
  }

  handleDeleteJob = (record) => {
    const ret = () => {
      this.deleteJob(record);
    }
    return ret;
  }

  deleteJob = (record) => {
    // TODO
    message.info("job deleted")
  }

  makeCreateJobManagerVisible = () => {
    this.setState({isInCreateJobManagerVisible: true});
  }

  openCreateJobManager = () => {
    this.setState({isCreateJobManagerVisible: true});
  }

  closeCreateJobManager = () => {
    this.setState({isCreateJobManagerVisible: false});
  }

  queryAndUpdateAllJob = () => {
    // TODO
    console.log("Query all jobs")
  }

  render() {
    return (
      <>
        <RecordManager handleOk={this.handleCloseQueryRecord} visible={this.state.isRecordManagerVisible} recordId={this.state.currentJobId} />
        <CreateJobManager handleClose={this.closeCreateJobManager} visible={this.state.isCreateJobManagerVisible}/>
        <Table  columns={this.columns()} dataSource={this.state.jobList}></Table>
        <Space>
          <Button onClick={this.queryAndUpdateAllJob}>Query/Refresh</Button>
          <Button type="primary" onClick={this.openCreateJobManager}>Create New Job</Button>
        </Space>
      </>
    )
  }
}

export default JobManager;
