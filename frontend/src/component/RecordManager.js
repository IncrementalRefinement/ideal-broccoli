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
        // TODO: here
        console.log(this.props.recordId)
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
