import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        {/*Paste the URL from any of components on grafana dashboard (tick off the current timeframe before embedding)*/}
        <iframe src="http://localhost:3000/d-solo/-WL7JUPGz/apache-jmeter-dashboard-using-core-influxdbbackendlistenerclient?orgId=1&refresh=5s&var-data_source=InfluxDB&var-application=application%20name&var-transaction=&var-measurement_name=jmeter&var-send_interval=5&panelId=4" width="450" height="200" frameborder="0"></iframe>
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
