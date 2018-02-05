import React, { Component } from 'react';

import { getBikes, getSearchCount } from "../../api";

import Loader from '../../components/Loader';
import Button from '../../components/Button';

import './styles.css';


class Main extends Component {

  constructor(props){
    super(props);
    this.state = {
      data: null,
      searchResult: null,
      count: 0,
      bufferInput: '',
      loading: true
    }
  }

  componentDidMount() {
    getBikes()
      .then(data => {
        this.setState({
          data: data,
          loading: false,
        });
        this.isMount = true;
      }).catch(err => {
        this.setState({
          loading: false
        });
      console.log('error', err);
    });
    this.isMount = true;
  }
  componentWillUnmount() {
    this.isMount = false;
  }


  searchBtnHandler = (btnCount) => {
    this.setState({
      loading: true
    });
    const { count } = this.state;
    if (this.searchInput.value === '') {
      alert('Fill in the field');
      this.setState({
        loading: false
      });
      return;
    }
    getSearchCount(this.searchInput.value, btnCount ? btnCount : 0).then(data => {
      this.setState({
        searchResult: data,
        data: null,
        loading: false,
        count: btnCount ? count + 1 : 0
      });
    }).catch(err => {
      this.setState({
        loading: false
      });
      console.log('error', err);
    });
  };



  render() {

    const { data, searchResult, loading } = this.state;
    const { goTo } = this.props;



    if (!this.isMount) {
      return <Loader show={ true } />;
    }

    return(
      <div className='main-page'>
        <div className="search-wrap clearfix">
          <input
            type="text"
            placeholder='Enter searching item...'
            ref={(input) => this.searchInput = input}
          />
          <Button
            onClick={ () => this.searchBtnHandler() }
            blue
          >Search</Button>
        </div>
        <div className='products-list'>
          {
            data && !searchResult ?
              data.map((el, index) => {
                return(
                  <div
                    onClick={ () => goTo('/product', el.id) }
                    className='product-elem'
                    key={ index }
                  >
                    <img src={ 'data:image/gif;base64,' + el.productPhotos[0].largePhoto } alt={ el.name } />
                    <span>{ el.name }</span>
                  </div>
                );
              })
              : (searchResult.length > 0 && !data ? searchResult.map((el, index) => {
                return(
                 <div
                   onClick={ () => goTo('/product', el.id) }
                   className='product-elem searching-elem clearfix'
                   key={ index }
                 >
                   <img src={ 'data:image/gif;base64,' + el.productPhotos[0].largePhoto } alt={ el.name } />
                   <div className="text-info">
                      <span>{ el.name }</span>
                      <span>{ el.productSubcategory ? el.productSubcategory.name : 'No subcategory name' }</span>
                      <span>{ el.productSubcategory ? el.productSubcategory.category.name : 'No category name' }</span>
                      <div className='description'>
                        <div className='d-head'>Description</div>
                        <p className='description'>
                          { el.productModel ? el.productModel.productDescriptions[0].description : 'No data'}
                        </p>
                      </div>
                   </div>
                 </div>
                )
              }) : <div className='noResult'>No data find...</div>)
          }
          {
            searchResult && searchResult.length > 0 &&
            <Button
              style={{ width: '150px', display: 'block', margin: '30px auto' }}
              onClick={ () => this.searchBtnHandler(this.state.count + 1) }
              blue
            >More</Button>
          }
        </div>
        {
          loading && <Loader show={ true } />
        }
      </div>
    )
  }
}

export default Main;