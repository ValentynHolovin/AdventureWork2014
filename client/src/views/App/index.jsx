import React, { Component } from 'react';
// router
import { Switch, Route, Redirect } from 'react-router';

import Main from './../Main';
import Product from './../Product';
import Create from './../Create'

import arrow from '../../left-arrow.svg';
import './styles.css';

class App extends Component {

  goTo = (path, id) => {
    const { history } = this.props;
    const { pathname } = history.location;
    if(!id){
      history.push(path);
    }
    else if (pathname !== path) {
      history.push(path+'?id='+id);
    }
  };

  render() {

    return (
      <div className='wrapper'>
        <header>
          <div className="container">
            {
              !window.location.href.includes('main') &&
              <button
                className='goBack-btn'
                onClick={ () => this.goTo('/main') }
              >
                <img src={ arrow } alt="back"/>
              </button>
            }
            <h1>BEST BIKES FROM  ADVENTUREWORK</h1>
            {
              !window.location.href.includes('create') &&
                <button
                  onClick={ () => this.goTo('/create') }
                  className='add-btn'>
                  <i className='plus' />
                  Add new
                </button>
            }

          </div>
        </header>
        <div
          id='content'
        >
          <div className="container">
            <Switch>
              <Route
                path='/main'
                render={ () =>
                  <Main goTo = { (path,id) => this.goTo(path,id) } />
                }
              />
              <Route
                path='/product'
                render={ () => <Product goTo = { (path) => this.goTo(path, null) } /> }
              />
              <Route
                path='/create'
                render={ () => <Create goTo = { (path, id) => this.goTo(path, id) } /> }
              />
              <Redirect to='/main' />
            </Switch>
          </div>
        </div>
        <footer>
         <div className="container">
           <span>All rights reserved &copy;2018.&nbsp;</span>
           <span>Terms of Service&nbsp;and Privacy Policy.</span>
         </div>
        </footer>
      </div>
    );
  }
}

export default App;
