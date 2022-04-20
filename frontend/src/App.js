import React, { Component } from 'react';
import { Layout, Button, Space } from 'antd';

import './App.css';
import JobManager from './component/JobManager';

const { Header, Footer, Sider, Content } = Layout;

class App extends Component {

  render() {
    return (
      <div className="App">
        <Layout>
          <Header style={{backgroundColor: "rgb(116, 163, 228)"}}>Big Data Management System</Header>
          <Content>
            <JobManager />
          </Content>
          <Footer>Footer</Footer>
        </Layout>
      </div>
    )
  }
}

export default App;
