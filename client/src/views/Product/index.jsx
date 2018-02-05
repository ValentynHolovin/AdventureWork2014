import React, { Component } from 'react';

import { getItem, updateItem, removeItem } from "../../api";
import Button from '../../components/Button';
import Loader from '../../components/Loader';
import Dialog from '../../components/Dialog';
import Form from '../../components/Form';
import './styles.css';

class Product extends Component {

  state = {
    loading: true,
    itemObj: null,
    tabIndex: 0,
    dialogVisibility: false,
    removeModalVisibility: false
  };


  componentDidMount() {
    const href = window.location.href;
    const id = href.split('?id=')[1];
    if (id) {
      getItem(id).then(data => {
        this.setState({
          itemObj: data
        });
      }).catch(err => {
        console.log('error', err);
      });
      this.isMount = true;
    } else {
      this.props.goTo('/');
    }
  }

  componentWillUnmount() {
    this.isMount = false;
  }


  submit = (obj) => {
    updateItem(obj).then(data => {
      if (data) {
        getItem(data).then(data => {
          this.setState({
            dialogVisibility: false,
            itemObj: data
          });
        }).catch(err => {
          console.log('error', err);
        });
      } else if(data.status === 500) {
        this.setState({
          dialogVisibility: false,
        });
        alert('Some thing went wrong..')
      }
    }).catch(err => {
      console.log('error', err);
      alert('Some thing went wrong..')
    });
  };

  doRemove = id => {
    const ID = parseInt(id, 10);
    removeItem(ID).then(data =>{
      this.props.goTo('/main', null);
    }).catch((err)=>{
      console.log('error', err);
      alert('Some thing went wrong..')
    })
  };


  tabHandler = (index) => {
    this.setState({
      tabIndex: index
    });
  };

  render() {

    if (!this.isMount) {
      return <Loader show={ true }/>;
    }
    const { itemObj, tabIndex, dialogVisibility } = this.state;
    return (
      <div
        className='product-item'
      >
        <img src={ 'data:image/gif;base64,' + itemObj.productPhotos[0].largePhoto } alt={ itemObj.name }/>
        <div className="text-info clearfix">
          <div className="name clearfix">
            <span>{ itemObj.name }</span>
            <span>{ itemObj.productSubcategory ? itemObj.productSubcategory.name : 'No subcategory name' }</span>
            <span>{ itemObj.productSubcategory ? itemObj.productSubcategory.category.name : 'No category name' }</span>
          </div>
          <div className='description'>
            <div className='d-head'>Description</div>
            <p className='description'>
              { itemObj.productModel ? itemObj.productModel.productDescriptions[0].description : 'No data' }
            </p>
          </div>
          <div className="price-wrap">
            <strong>Price: { itemObj.listPrice } $</strong>
          </div>
        </div>
        <div className="tabs-wrapper">
          <div className="tabs clearfix">
            <span className={ tabIndex === 0 ? 'active' : '' }
                  onClick={ () => this.tabHandler(0) }>Characteristics</span>
            <span className={ tabIndex === 1 ? 'active' : '' } onClick={ () => this.tabHandler(1) }>Reviews</span>
          </div>
          <div className="tabs-content">
            {
              tabIndex === 0 ?
                <ul>
                  <li><strong>Model:</strong> { itemObj.productModel ? itemObj.productModel.name : 'No data' }</li>
                  <li><strong>Color: </strong>{ itemObj.color }</li>
                  <li>
                    <strong>Size:</strong> { `${ itemObj.size } ${ itemObj.sizeUnitMeasureCode ? itemObj.sizeUnitMeasureCode.id : '' }` }
                  </li>
                  <li>
                    <strong>Weight:</strong>{ `${ itemObj.weight } ${ itemObj.weightUnitMeasureCode ? itemObj.weightUnitMeasureCode.id : '' }` }
                  </li>
                  <li><strong>Product Line:</strong> { itemObj.productLine }</li>
                  <li><strong>Class:</strong> { itemObj.productClass }</li>
                  <li><strong>Style:</strong> { itemObj.productStyle }</li>
                </ul> :
                <div>
                  {
                    itemObj.productReviews.length > 0 ?
                      itemObj.productReviews.map((el, index) => {
                        return (
                          <div key={ index } className='reviews'>
                            <div className="info clearfix">
                              <div className="review-name"><span>Reviewer:</span> { el.reviewerName } </div>
                              <div className="rating"><span>Rating:</span> { el.rating }</div>
                              <div className='post-date'><span>Post date:</span> { el.reviewDate }</div>
                            </div>
                            <p className="text">
                              { el.comments }
                            </p>
                          </div>
                        );
                      }) : 'No comments'
                  }
                </div>

            }
          </div>
        </div>
        <div
          style={ { margin: '10px 0 0 0' } }
          className="btn-group"
        >
          <Button
            style={ { marginRight: '15px' } }
            onClick={ () => this.setState({ dialogVisibility: true }) }
            blue
          >
            Update</Button>
          <Button
            onClick={() => this.setState({removeModalVisibility: true})}
            red
          >Remove</Button>
        </div>
        <Dialog
          className='form-dialog'
          title='Edit product'
          width={ 565 }
          close={ () => this.setState({ dialogVisibility: false }) }
          show={ dialogVisibility }
        >
          <Form
            itemObj={ itemObj }
            submit={ obj => this.submit(obj) }
          />
        </Dialog>
        <Dialog
          close={() => this.setState({ removeModalVisibility: false })}
          show={ this.state.removeModalVisibility }
          title='Remove product'
          width={ 565 }
        >
          <p style={{ fontSize: '18px', marginBottom: '15px'}}>Are you sure?</p>
         <div
           style={{ paddingBottom: '30px' }}
           className="btn-group"
         >
           <Button
             style={{ marginRight: '20px' }}
             red
             onClick={() => this.doRemove(itemObj.id)}>
             Yes
           </Button>
           <Button
             green
             onClick={() => this.setState({ removeModalVisibility: false })}
           >No
           </Button>
         </div>
        </Dialog>
      </div>
    )
  }
}

export default Product;