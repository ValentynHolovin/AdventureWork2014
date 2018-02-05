import {
  fetch,
  fetchMiddleware,
  getFullHeader
} from './fetch';
import url_config from "./url_config";


export const getBikes = () => fetch(
  `${url_config.url}/get_top_five`,
  {
    headers: getFullHeader(),
  }
).then(fetchMiddleware);


export const getSearchCount = (searchString, count) => fetch(
  `${url_config.url}/search/${searchString}/${count}`,
  {
    headers: getFullHeader(),
  }
).then(fetchMiddleware);


export const getItem = (id) => fetch(
  `${url_config.url}/products/${ id }`,
  {
    headers: getFullHeader(),
  }
).then(fetchMiddleware);


export const getSelectValues = () => fetch(
  `${url_config.url}/get_unitmeasurecode`,
  {
    headers: getFullHeader(),
  }
).then(fetchMiddleware);

export const getProductModel = () => fetch(
  `${url_config.url}/get_models`,
  {
    headers: getFullHeader(),
  }
).then(fetchMiddleware);


export const getProductSubCategory = () => fetch(
  `${url_config.url}/get_subcategories`,
  {
    headers: getFullHeader(),
  }
).then(fetchMiddleware);


export const submitNewItem = (obj) => fetch(
  `${url_config.url}/products/create`,
  {
    method: 'POST',
    headers: getFullHeader(),
    body: JSON.stringify(obj)
  }
).then(fetchMiddleware);


export const updateItem = (obj) => fetch(
  `${url_config.url}/products/update`,
  {
    method: 'POST',
    headers: getFullHeader(),
    body: JSON.stringify(obj)
  }
).then(fetchMiddleware);


export const removeItem = id => fetch(
  `${url_config.url}/products/delete/${id}`,
  {
    method: 'POST',
    headers: getFullHeader(),
  }
).then(fetchMiddleware);
