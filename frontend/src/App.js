import React, { Component } from 'react';
import { Layout, Button, Space, PageHeader } from 'antd';

import './App.css';
import JobManager from './component/JobManager';

const { Header, Footer, Sider, Content } = Layout;

class App extends Component {

  render() {
    return (
      <div className="App">
        <Layout>
          <Header style={{backgroundColor: "rgb(116, 163, 228)"}}>
            <PageHeader
              className="site-page-header"
              title="Ideal-broccoli"
              subTitle="A big data management platform"
            />
          </Header>
          <Content>
            <JobManager />
          </Content>
          <Footer>Some footer goes here</Footer>
        </Layout>
      </div>
    )
  }
}

export default App;
