import React, { Component } from 'react';
import ReactDOM from 'react-dom';

import classnames from 'classnames';

import './styles.css';

export default class Dialog extends Component {
  componentDidMount() {
    this.renderDialog();
  }
  componentDidUpdate() {
    this.renderDialog();
  }
  componentWillUnmount() {
    ReactDOM.unmountComponentAtNode(this.dialog);
    document.body.removeChild(this.dialog);
  }

  render() {
    return null;
  }

  renderDialog() {
    if (!this.dialog) {
      this.dialog = document.createElement('div');
      document.body.appendChild(this.dialog);
    }
    const {
      className,
      title,
      children,
      show,
      width,
      close,
      showLoading
    } = this.props;

    ReactDOM.render(
      <div className={ classnames('dialog', className, { 'active': show }) } >
        <div className='dialog-overlay' onClick={ close } />
        <div className='dialog-box' style={ { maxWidth: width } }>
          {showLoading &&
          <div className='dialog-loading' />
          }
          <i className='dialog-close' onClick={ close } />
          <div className='dialog-title'>{title}</div>
          <div className='dialog-content'>
            {children}
          </div>
        </div>
      </div>
      , this.dialog
    );
  }
}
