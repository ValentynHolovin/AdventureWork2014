import React, { Component } from 'react';
import './styles.css';

import loaderImage from './tail-spin.svg';

export default class Loader extends Component {

  render() {
    const { show } = this.props;

    return (
      <div className='loader' style={ { display: show ? 'block' : 'none' } }>
        <div className='loader-img' style={ { backgroundImage: `url(${ loaderImage })` } } />
      </div>
    );
  }
}
