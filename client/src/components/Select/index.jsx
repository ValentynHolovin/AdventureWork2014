import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import classnames from 'classnames';

import * as _ from 'lodash';

import './styles.css';

export default class Select extends Component {
  constructor(props) {
    super(props);

    this.state = {
      show: false
    };
  }

  componentDidMount() {
    this.$contentDiv = document.querySelector('#content');
    this.renderDrop();
    document.addEventListener('click', this.hideDrop);
    // this.$contentDiv.addEventListener('scroll', this.renderDrop);
    window.addEventListener('scroll', this.renderDrop, true);
  }

  componentDidUpdate() {
    this.renderDrop();
  }

  componentWillUnmount() {
    document.removeEventListener('click', this.hideDrop);
    // this.$contentDiv.removeEventListener('scroll', this.renderDrop);
    window.removeEventListener('scroll', this.renderDrop, true);
    ReactDOM.unmountComponentAtNode(this.drop);
    document.body.removeChild(this.drop);
  }

  getDropPosition = () => {
    const $element = this.refs.select; // eslint-disable-line react/no-string-refs
    const rect = $element.getBoundingClientRect();

    return {
      width: $element.offsetWidth,
      height: $element.offsetHeight,
      rect,
      left: (window.scrollX || window.pageXOffset) + rect.left,
      top: (window.scrollY || window.pageYOffset) + rect.top
    };
  }

  hideDrop = event => {
    const $element = this.refs.select; // eslint-disable-line react/no-string-refs

    if (!$element.contains(event.target)) {
      this.setState({ show: false });
    }
  }

  handleChange = (index, item) => { // eslint-disable-line consistent-return
    if (index === this.props.selected) {
      return this.setState({ show: false });
    }
    this.props.onChange(index, item);
  }

  renderDrop = () => {
    if (!this.drop) {
      this.drop = document.createElement('div');
      document.body.appendChild(this.drop);
    }

    const position = this.getDropPosition();
    const top = position.top + position.height;
    const { show } = this.state;
    const { data, selectedValue, noBorder } = this.props;
    let { selected } = this.props;

    if (!Number.isInteger(selected) && selectedValue) {
      selected = _.findIndex(data, el => el.value === selectedValue);
    }

    ReactDOM.render(
      <div
        className={ classnames('select-dropdown', { 'active': show }, { noBorder }) }
        style={ {
          top,
          width: position.width,
          left: position.left
        } }
      >
        <ul>
          {data.map((item, index) => (
            index !== selected &&
            <li
              onClick={ () => this.handleChange(index, item) }
              dangerouslySetInnerHTML={ {
                __html: item.name
              } }
              className={ classnames({ 'active': index === selected }) }
              key={ index }
            />
          ))}
        </ul>
      </div>,
      this.drop
    );
  }

  render() {
    const { selectedValue, data, className, width, noBorder, placeholder } = this.props;
    const { show } = this.state;
    let { selected } = this.props;

    if (!Number.isInteger(selected) && selectedValue) {
      selected = _.findIndex(data, el => el.value === selectedValue);
    }
    return (
      <div
        className={ classnames('select', className, { noBorder }) }
        ref='select' // eslint-disable-line react/no-string-refs
        style={ { width: width || '' } }
        onClick={ () => this.setState({ show: !show }) }
        dangerouslySetInnerHTML={
          !Number.isInteger(selected)
            ? { __html: `<span class='placeholder'>${ placeholder }</span> <span class="select-icon"></span>` }
            : { __html: `${ data[selected].name } <span class="select-icon icon-df-drop_down"></span>` }
        }
      />
    );
  }
}