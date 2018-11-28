import React, { Component } from 'react';
import './App.css';
import AirplaneButton from './AirplaneButton';

class App extends Component {
  constructor() {
    super();
    this.state = {
      airplanes: []
    };

    this.getAirplanes = this.getAirplanes.bind(this);
  }

  async getAirplanes() {
    const airplanes = await fetch('/airplanes');

    this.setState({
      airplanes: await airplanes.json()
    });
  }

  render() {
    const haveAirplanes = this.state.airplanes.length > 0;
    let airplanes = (
        this.state.airplanes.map((airplane) =>
          <div key={airplane.id} style={{"margin": "2rem 0 0 0"}}>
            <p style={{"margin": "0", "fontWeight": "bold"}}>{airplane.tailNumber}</p>
            <p style={{"margin": "0"}}>{airplane.origin}</p>
          </div>
        )
    )

    if (!haveAirplanes) { airplanes = <p style={{"margin": "0"}}>No airplanes here</p>}

    return (
      <div className="App">
        <header className="App-header">
          <h1>Airplanes</h1>
          {airplanes}

          <AirplaneButton onClick={this.getAirplanes} />
        </header>
      </div>
    );
  }
}

export default App;
