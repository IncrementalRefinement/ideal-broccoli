import React, { Component } from 'react';
import { Button, Modal, Radio, Space, Upload, Input } from "antd";
import { UploadOutlined } from '@ant-design/icons';

class CreateJobManager extends Component {

    constructor(props) {
        super(props);
    }

    handleOk = () => {
        // TODO
        this.props.handleClose();
    }

    handleCancel = () => {
        this.props.handleClose();
    }

    render() {
        return (
            <Modal visible={this.props.visible} onOk={this.handleOk} onCancel={this.handleCancel}>
                <div>
                    <Space>
                        Scheduled Job
                        <Radio.Group>
                            <Radio value={'true'}>TRUE</Radio>
                            <Radio value={'false'}>FALSE</Radio>
                        </Radio.Group>
                    </Space>
                </div>
                <div>
                    <Space>
                    Job Type
                        <Radio.Group>
                            <Radio value={'flink'}>FLINK</Radio>
                            <Radio value={'spark'}>SPARK</Radio>
                        </Radio.Group>
                    </Space>
                </div>
                <Input placeholder="Execution Rate(ms)" />
                <Input placeholder="Main Class Path" />
                <Input placeholder="Remark" />
                <Upload>
                    <Button icon={<UploadOutlined />}>Click to Upload jar file</Button>
                </Upload>
            </Modal>
        )
    }
}

export default CreateJobManager;
