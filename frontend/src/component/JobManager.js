import React, { Component } from 'react';
import { Button, Space, Table, Tag } from 'antd';

const columns = [
  {
    title: 'Job ID',
    dataIndex: 'jobId',
    key: 'jobId',
  },
  {
      title: 'Job Type',
      dataIndex: 'jobType',
      key: 'jobType',
  },
  {
      title: 'Scheduled Job',
      dataIndex: 'isScheduledJob',
      key: 'scheduledJob',
      render: scheduledJob => {
        if (scheduledJob) {
          return (
            <Tag color='green'>
              TRUE
            </Tag>
          )
        } else {
          return (
            <Tag color='geekblue'>
              FALSE
            </Tag>
          )
        }
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
]

class JobManager extends Component {

  constructor(props) {
    super(props);
    this.state = {
      isInCreatePRocess: false,
      jobList: [
        {
          "jobId": 0,
          "jobType": "flink",
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

  render() {
    return (
      <>
        <Table  columns={columns} dataSource={this.state.jobList}></Table>
        <Space>
          <Button>Query/Refresh</Button>
          <Button type="primary">Create New Job</Button>
        </Space>
      </>
    )
  }
}

export default JobManager;