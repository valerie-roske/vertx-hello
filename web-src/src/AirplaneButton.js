import React from 'react';

const AirplaneButton = props => {
const onClick = props.onClick;
return <button className="App-button" onClick={onClick}>
            It's a bird! It's a plane!
          </button> };

export default AirplaneButton;
