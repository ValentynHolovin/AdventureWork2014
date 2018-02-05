import React, { Component } from 'react';

import PropTypes from 'prop-types';
import classnames from 'classnames';

import './styles.css';

export default class Button extends Component {

  /** Handle click on Button
   * @param {MouseEvent} event
   */
  clickHandler = event => {
    const { onClick, disabled } = this.props;

    if (onClick && !disabled) {
      this.props.onClick();
    }
  }

  render() {
    const { children, disabled, green, width, small, red, type, style, className, blue } = this.props;
    const newStyle = { ...{}, ...style };

    if (width) {
      newStyle.width = width;
    }

    return (
      <button
        className={ (className !== undefined ? `${ className } ` : '') + classnames({
          'button': true,
          'disabled': disabled,
          'green': green,
          'red': red,
          'small': small,
          'blue': blue
        }) }
        type={ type != null ? type : 'button' }
        style={ newStyle }
        onClick={ this.clickHandler }
      >
        {children}
      </button>
    );
  }
}

Button.propTypes = {
  onClick: PropTypes.func,
  green: PropTypes.bool,
  red: PropTypes.bool,
  disabled: PropTypes.bool,
  small: PropTypes.bool,
  width: PropTypes.number,
  type: PropTypes.string,
  style: PropTypes.object,
  className: PropTypes.string
};
