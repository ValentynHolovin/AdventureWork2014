import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './views/App/index';
import registerServiceWorker from './registerServiceWorker';
import { Router, Route} from 'react-router';
import createBrowserHistory from 'history/createBrowserHistory'

const history = createBrowserHistory();
const root = document.getElementById('root');

ReactDOM.render(
  <Router history={history}>
      <Route
        path='/'
        component={ App }
      >
      </Route>
  </Router>,
  root);
registerServiceWorker();
