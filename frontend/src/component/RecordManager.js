import React, { Component } from 'react';
import { Button, Table, Tag, Modal } from 'antd';


const columns = [
    {
        title: 'Record ID',
        dataIndex: 'id',
        key: 'id',
    },
    {
        title: 'Job ID',
        dataIndex: 'jobId',
        key: 'jobId',
    },
    {
        title: 'Success',
        dataIndex: 'success',
        key: 'success',
        render: success => {
            let color, text;
            if (success) {
              color = 'blue';
              text = 'SUCCESS';
            } else {
              color = 'black';
              text = 'FAIL';
            }
    
            return (
              <Tag color={color}>
                {text}
              </Tag>
            )
        },
    },
    {
        title: 'Begin Time',
        dataIndex: 'beginTime',
        key: 'beginTime',
    },
    {
        title: 'End Time',
        dataIndex: 'endTime',
        key: 'endTime',
    },
]

class RecordManager extends Component {

    constructor(props) {
        super(props)
        this.state = {
            recordList: null
        }
    }

    queryJob = () => {
        fetch("http://localhost:8080/api/v1/record?" + new URLSearchParams({jobId: this.props.jobId}), {
            method: "GET",
        })
            .then(response => response.json())
            .then((json) => {
          // console.log(json)
          this.setState({recordList: json["payload"]})
        })
    }

    render() {
        return (
            <Modal title="Execution Records" visible={this.props.visible} onOk={this.props.handleOk} onCancel={this.props.handleOk}>
                <Table  columns={columns} dataSource={this.state.recordList}></Table>
                <Button onClick={this.queryJob}>Query/Refresh</Button>
            </Modal>
        )
    }
}

export default RecordManager;
