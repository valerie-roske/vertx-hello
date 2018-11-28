import React from 'react';
import { storiesOf } from '@storybook/react';
import App from '../../src/App';
import AirplaneButton from '../../src/AirplaneButton.js';

storiesOf('Button', module)
  .add('with text', () => <AirplaneButton onClick={() => {alert('we clicked')}} />)
  .add('with some emoji', () => (
    <App></App>
  ));
