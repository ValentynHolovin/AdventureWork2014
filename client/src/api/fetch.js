require('es6-promise').polyfill();
const fetch = require('isomorphic-fetch');

/**
 * Full header
 */
const getFullHeader = () => ({
  ...{
    'Content-Type': 'application/json'
  }
});

const fetchMiddleware = (response) => {
  try {
    return response.status !== 204 ? response.json() : '';
  } catch (e) {
    console.log(`Ошибка ${ e.name } : ${ e.message }`);
    return '';
  }
};


export { fetch, getFullHeader, fetchMiddleware };

