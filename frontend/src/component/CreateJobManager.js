import React, { Component } from 'react';
import { Button, Modal, Radio, Space, Upload, Input, message } from "antd";
import { UploadOutlined } from '@ant-design/icons';

class CreateJobManager extends Component {

    constructor(props) {
        super(props);
        this.state = {
            scheduledJob: null,
            jobType: null,
            executeRate: null,
            mainClassPath: null,
            jobName: null,
            remark: null,
            jarFilePath: null,
        }
    }

    handleOk = () => {
        // TODO
        console.log(this.state);
        this.props.handleClose();
    }

    handleCancel = () => {
        this.state.params = {};
        this.props.handleClose();
    }

    updateScheduledJob = (e) => {
        this.setState({scheduledJob: e.target.value});
    }

    updateJobType = (e) => {
        this.setState({jobType: e.target.value});
    }

    updateExecuteRate = (e) => {
        const value = e.target.value;
        this.setState({executeRate: value});
    }

    updateJobName = (e) => {
        const value = e.target.value;
        this.setState({jobName: value});
    }

    updateRemark = (e) => {
        const value = e.target.value;
        this.setState({remark: value});
    }

    updateMainClassPath = (e) => {
        const value = e.target.value;
        this.setState({mainClassPath: value});
    }

    uploadProps = {
        maxCount: 1,
        name: 'file',
        // TODO: here
        action: 'https://www.mocky.io/v2/5cc8019d300000980a055e76',
        headers: {
            authorization: 'authorization-text',
        },
        onChange(info) {
            if (info.file.status === 'done') {
                // TODO: here
                console.log(info.file.response);
                message.success(`${info.file.name} file uploaded successfully`);
            } else if (info.file.status === 'error') {
                message.error(`${info.file.name} file upload failed.`);
            }
        }
    }

    render() {
        return (
            <Modal visible={this.props.visible} onOk={this.handleOk} onCancel={this.handleCancel}>
                <Space direction="vertical" size="middle">
                    <div>
                        <Space>
                            Scheduled Job
                            <Radio.Group onChange={this.updateScheduledJob}>
                                <Radio value={'true'}>TRUE</Radio>
                                <Radio value={'false'}>FALSE</Radio>
                            </Radio.Group>
                        </Space>
                    </div>
                    <div>
                        <Space>
                        Job Type
                            <Radio.Group onChange={this.updateJobType}>
                                <Radio value={'flink'}>FLINK</Radio>
                                <Radio value={'spark'}>SPARK</Radio>
                            </Radio.Group>
                        </Space>
                    </div>
                    <Input placeholder="Execution Rate(ms)" allowClear onChange={this.updateExecuteRate} />
                    <Input placeholder="Main Class Path" allowClear onChange={this.updateMainClassPath} />
                    <Input placeholder="Job Name" allowClear onChange={this.updateJobName} />
                    <Input placeholder="Remark" allowClear onChange={this.updateRemark} />
                    <Upload {...this.uploadProps}>
                        <Button icon={<UploadOutlined />}>Click to Upload jar file</Button>
                    </Upload>
                </Space>
            </Modal>
        )
    }
}

export default CreateJobManager;
