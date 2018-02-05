import React, { Component } from 'react';
import { getSelectValues, getProductModel, getProductSubCategory } from '../../api';
import Loader from "../Loader";
import Select from '../../components/Select';
import moment from 'moment';
import _ from 'lodash';

import './styles.css';
import Button from "../Button";

const classSelect = [{
  name: 'H',
  id: 0,
}, {
  name: 'M',
  id: 1,
}, {
  name: 'L',
  id: 2,
}];
const lineSelect = [{
  name: 'R',
  id: 0,
}, {
  name: 'M',
  id: 1,
}, {
  name: 'S',
  id: 2,
}, {
  name: 'Е',
  id: 3,
}];
const styleSelect = [{
  name: 'W',
  id: 0,
}, {
  name: 'M',
  id: 1,
}, {
  name: 'U',
  id: 2,
}];


class ItemForm extends Component {

  state = {
    rowguid: null,
    id: null,
    weight: '',
    sellStartDate: moment(),
    sellEndDate: moment(),
    discontinuedDate: moment(),
    classSelected: 0,
    styleSelected: 0,
    lineSelected: 0,
    selectData: null,
    modelData: null,
    productCategoryData: null,
    name: '',
    prodNumber: '',
    makeFlag: false,
    finishedGoodsFlag: false,
    color: '',
    safetyLevel: '',
    reorderPoint: '',
    standardCost: '',
    listPrice: '',
    daysToManufacture: '',
    size: '',
    selectSize: 0,
    selectWeight: 0,
    selectModel: 0,
    selectCategory: 0,
    canCheck: false,
    isDataLoading: false,
    validateArray: [{
      id: 'name',
      isValid: true,
      text: ''
    }, {
      id: 'prodNumber',
      isValid: true,
      text: ''
    }, {
      id: 'color',
      isValid: true,
      text: ''
    }, {
      id: 'size',
      isValid: true,
      text: ''
    }, {
      id: 'safetyLevel',
      isValid: true,
      text: ''
    }, {
      id: 'reorderPoint',
      isValid: true,
      text: ''
    }, {
      id: 'standardCost',
      isValid: true,
      text: ''
    }, {
      id: 'listPrice',
      isValid: true,
      text: ''
    }, {
      id: 'daysToManufacture',
      isValid: true,
      text: ''
    }]
  };


  componentDidMount() {
    let selectD = null;
    let modelD = null;
    let productCatD = null;
    getSelectValues().then(selectData => {
      selectD = selectData;
    }).then(() => {
      return getProductModel().then(modelData => {
        modelD = modelData;
      }).then(() => {
        return getProductSubCategory().then(productCatData => {
          productCatD = productCatData;
          if (this.props.itemObj) {
            const { itemObj } = this.props;
            const productClass = itemObj.productClass;
            const styleClass = itemObj.productStyle;
            const lineClass = itemObj.productLine;
            const productSubCategoryName = itemObj.productSubcategory.name;
            const selectCategory = _.findIndex(productCatD, (o) => o.name === productSubCategoryName);
            const productSizeName = itemObj.sizeUnitMeasureCode.name;
            const productSizeIndex = _.findIndex(selectD, (o) => o.name === productSizeName);
            const productWeightName = itemObj.weightUnitMeasureCode.name;
            const productWeightIndex = _.findIndex(selectD, (o) => o.name === productWeightName);
            const productModelName = itemObj.productModel.name;
            const productModelIndex = _.findIndex(modelD, (o) => o.name === productModelName);
            const classSelected = _.findIndex(classSelect, (o) => o.name === productClass);
            const styleSelected = _.findIndex(styleSelect, (o) => o.name === styleClass);
            const lineSelected = _.findIndex(lineSelect, (o) => o.name === lineClass);
            this.setState({
              rowguid: itemObj.rowguid,
              id: itemObj.id,
              selectCategory,
              selectSize: productSizeIndex,
              selectWeight: productWeightIndex,
              color: itemObj.color,
              safetyLevel: itemObj.safetyStockLevel,
              reorderPoint: itemObj.reorderPoint,
              standardCost: itemObj.standardCost,
              listPrice: itemObj.listPrice,
              size: itemObj.size,
              daysToManufacture: itemObj.daysToManufacture,
              makeFlag: itemObj.makeFlag,
              finishedGoodsFlag: itemObj.finishedGoodsFlag,
              prodNumber: itemObj.productNumber,
              name: itemObj.name,
              weight: itemObj.weight,
              classSelected,
              styleSelected,
              lineSelected,
              selectData: selectD,
              modelData: modelD,
              productCategoryData: productCatD,
              isDataLoading: true,
              sellStartDate: itemObj.sellStartDate,
              sellEndDate: itemObj.sellEndDate,
              discontinuedDate: itemObj.discontinuedDate,

              selectModel: productModelIndex,

            });
          } else {
            this.setState({
              selectData: selectD,
              modelData: modelD,
              productCategoryData: productCatD,
              isDataLoading: true,

            });
          }

        })
      })
    }).catch(err => {
      console.log('error', err);
    });
    this.isMount = true;

  }

  componentWillMount() {
    this.isMount = false;
  }

  switcherForValid = inputName => {
    let _text = '';
    let _valid = '';
    const regNumbers = /^\d+$/;
    const regDouble = /[\d]+.[\d]+/;
    const {
      name,
      prodNumber,
      safetyLevel,
      reorderPoint,
      standardCost,
      listPrice,
      color,
      size,
      daysToManufacture
    } = this.state;
    switch (inputName) {
      case 'name': {
        if (name.length < 1) {
          _text = 'This field can be empty';
          _valid = false;
        } else {
          _text = '';
          _valid = true;
        }
        break;
      }
      case 'safetyLevel': {
        if (safetyLevel.length < 1) {
          _text = 'This field can be empty';
          _valid = false;
        } else if (safetyLevel.length >= 6) {
          _text = 'To much symbols';
          _valid = false;
        } else if (!regNumbers.test(safetyLevel)) {
          _text = 'You can write only numbers';
          _valid = false;
        } else {
          _text = '';
          _valid = true;
        }
        break;
      }
      case 'color': {
        if (color.length > 15) {
          _text = 'To much symbols';
          _valid = false;
        } else{
          _text = '';
          _valid = true;
        }
        break;
      }
      case 'size': {
        if (size.length > 15) {
          _text = 'To much symbols';
          _valid = false;
        } else{
          _text = '';
          _valid = true;
        }
        break;
      }
      case 'prodNumber': {
        if (prodNumber.length < 1) {
          _text = 'This field can be empty';
          _valid = false;
        } else if (!regNumbers.test(prodNumber)) {
          _text = 'You can write only numbers';
          _valid = false;
        } else {
          _text = '';
          _valid = true;
        }
        break;
      }
      case 'reorderPoint': {
        if (reorderPoint.length < 1) {
          _text = 'This field can be empty';
          _valid = false;
        } else if (reorderPoint.length >= 6) {
          _text = 'To much symbols';
          _valid = false;
        } else if (!regNumbers.test(reorderPoint)) {
          _text = 'You can write only numbers';
          _valid = false;
        } else {
          _text = '';
          _valid = true;
        }
        break;
      }
      case 'standardCost': {
        if (standardCost.length < 1) {
          _text = 'This field can be empty';
          _valid = false;
        } else if (!regDouble.test(standardCost)) {
          _text = 'You can write only double numbers';
          _valid = false;
        } else {
          _text = '';
          _valid = true;
        }
        break;
      }
      case 'listPrice': {
        if (listPrice.length < 1) {
          _text = 'This field can be empty';
          _valid = false;
        } else if (!regDouble.test(listPrice)) {
          _text = 'You can write only double numbers';
          _valid = false;
        } else {
          _text = '';
          _valid = true;
        }
        break;
      }
      case 'daysToManufacture': {
        if (daysToManufacture.length < 1) {
          _text = 'This field can be empty';
          _valid = false;
        } else if (!regNumbers.test(daysToManufacture)) {
          _text = 'You can write only numbers';
          _valid = false;
        } else {
          _text = '';
          _valid = true;
        }
        break;
      }
      default: break;
    }
    return {
      _valid: _valid,
      _text: _text
    }
  };


  validate = (inputName) => {
    const {
      validateArray,
    } = this.state;
    if (inputName === 'submit') {
      let flag = true;
      validateArray.forEach(el => {
        const validObject = this.switcherForValid(el.id);
        validateArray.find(function (obj) {
          return obj.id === el.id;
        }).text = validObject._text;
        validateArray.find(function (obj) {
          return obj.id === el.id;
        }).valid = validObject._valid;
        if (validObject._valid === false) {
          flag = false
        }
      });
      if (flag) {
        this.submit();
      } else {
        alert('check fields')
      }
      this.setState({
        validateArray
      });
    } else {
      const validObject = this.switcherForValid(inputName);
      validateArray.find(function (obj) {
        return obj.id === inputName;
      }).text = validObject._text;
      validateArray.find(function (obj) {
        return obj.id === inputName;
      }).valid = validObject._valid;
      this.setState({
        validateArray
      });
    }
  };

  submit = () => {
    const {
      name,
      prodNumber,
      makeFlag,
      finishedGoodsFlag,
      color,
      safetyLevel,
      reorderPoint,
      listPrice,
      lineSelected,
      standardCost,
      daysToManufacture,
      selectSize,
      size,
      selectData,
      selectWeight,
      weight,
      classSelected,
      styleSelected,
      productCategoryData,
      selectCategory,
      selectModel,
      modelData,
      sellStartDate,
      sellEndDate,
      discontinuedDate,
      id,
      rowguid
    } = this.state;

    const sizeUnitMeasureCode = selectData[selectSize];
    const weightUnitMeasureCode = selectData[selectWeight];
    const productL = lineSelect[lineSelected].name;
    const productC = classSelect[classSelected].name;
    const productS = styleSelect[styleSelected].name;
    const productSubcategory = productCategoryData[selectCategory];
    const productModel = modelData[selectModel];
    const objectForSending = {
      id,
      rowguid,
      name: name,
      productNumber: prodNumber,
      makeFlag: makeFlag,
      finishedGoodsFlag: finishedGoodsFlag,
      color: color,
      safetyStockLevel: safetyLevel,
      reorderPoint: reorderPoint,
      standardCost: standardCost,
      listPrice: listPrice,
      daysToManufacture: daysToManufacture,
      size: size,
      sizeUnitMeasureCode: sizeUnitMeasureCode,
      weightUnitMeasureCode: weightUnitMeasureCode,
      weight: weight,
      productLine: productL,
      productClass: productC,
      productStyle: productS,
      productSubcategory: productSubcategory,
      productModel: productModel,
      sellStartDate: sellStartDate,
      sellEndDate: sellEndDate,
      discontinuedDate: discontinuedDate,
      productPhotos: null,
      productReviews: null,
    };

    this.props.submit(objectForSending);
  };


  render() {
    if (!this.isMount || !this.state.isDataLoading) {
      return <Loader show={ true }/>
    }

    const {
      sellStartDate,
      sellEndDate,
      discontinuedDate,
      name,
      prodNumber,
      makeFlag,
      color,
      validateArray,
      finishedGoodsFlag,
      safetyLevel,
      reorderPoint,
      standardCost,
      listPrice,
      daysToManufacture,
      size,
      selectData,
      selectSize,
      modelData,
      productCategoryData,
      selectWeight,
      selectModel,
      selectCategory,
      weight,
      classSelected,
      lineSelected,
      styleSelected
    } = this.state;

    return (
      <form className='main-form'>
        <div className="form-box">
          <div className="row">
            <label htmlFor="name">Name:</label>
            <input
              value={ name }
              onBlur={ () => this.validate('name') }
              onChange={ e => this.setState({ name: e.target.value }) }
              id='name'
              type="text"
            />
            <span className='lb-error'>
            {
              validateArray.find(function (obj) {
                return obj.id === 'name';
              }).text
            }
          </span>
          </div>
          <div className="row">
            <label htmlFor="name">Product number:</label>
            <input
              value={ prodNumber }
              onBlur={ () => this.validate('prodNumber') }
              onChange={ e => this.setState({ prodNumber: e.target.value }) }
              id='name'
              type="text"
            />
            <span className='lb-error'>
            {
              validateArray.find(function (obj) {
                return obj.id === 'prodNumber';
              }).text
            }
          </span>
          </div>
          <div className="row">
            <label htmlFor="makeFlag" className='checkbox-label'>Make Flag:</label>
            <input
              checked={ makeFlag}
              onChange={ (e) => this.setState({ makeFlag: e.target.checked }) }
              id='makeFlag'
              type='checkbox'
            />
          </div>
          <div className="row">
            <label htmlFor="finishedGoodsFlag" className='checkbox-label'>Finished Goods Flag:</label>
            <input
              checked={ finishedGoodsFlag }
              onChange={ (e) => {
                this.setState({ finishedGoodsFlag: e.target.checked })
              } }
              id='finishedGoodsFlag'
              type='checkbox'
            />
          </div>
          <div className="row">
            <label htmlFor="color">Color:</label>
            <input
              onBlur={ () => this.validate('color') }
              value={ color }
              onChange={ e => this.setState({ color: e.target.value }) }
              id='color'
              type="text"
            />
            <span className='lb-error'>
            {
              validateArray.find(function (obj) {
                return obj.id === 'color';
              }).text
            }
            </span>
          </div>
          <div className="row">
            <label htmlFor="safetyStockLevel">Safety Stock Level:</label>
            <input
              value={ safetyLevel }
              onBlur={ () => this.validate('safetyLevel') }
              onChange={ e => this.setState({ safetyLevel: e.target.value }) }
              id='safetyStockLevel'
              type="text"/>
            <span className='lb-error'>
            {
              validateArray.find(function (obj) {
                return obj.id === 'safetyLevel';
              }).text
            }
          </span>
          </div>
          <div className="row">
            <label htmlFor="reorderPoint">Reorder Point:</label>
            <input
              value={ reorderPoint }
              onBlur={ () => this.validate('reorderPoint') }
              onChange={ e => this.setState({ reorderPoint: e.target.value }) }
              id='reorderPoint'
              type="text"/>
            <span className='lb-error'>
              {
                validateArray.find(function (obj) {
                  return obj.id === 'reorderPoint';
                }).text
              }
            </span>
          </div>
          <div className="wrapper clearfix">
            <div className="row third-left">
              <label htmlFor="standardCost">Standard cost:</label>
              <input
                value={ standardCost }
                onBlur={ () => this.validate('standardCost') }
                onChange={ e => this.setState({ standardCost: e.target.value }) }
                id='standardCost'
                type="text"/>
              <span className='lb-error'>
              {
                validateArray.find(function (obj) {
                  return obj.id === 'standardCost';
                }).text
              }
            </span>
            </div>
            <div className="row third-left">
              <label htmlFor="listPrice">List Price:</label>
              <input
                value={ listPrice }
                onBlur={ () => this.validate('listPrice') }
                onChange={ e => this.setState({ listPrice: e.target.value }) }
                id='listPrice'
                type="text"/>
              <span className='lb-error'>
              {
                validateArray.find(function (obj) {
                  return obj.id === 'listPrice';
                }).text
              }
            </span>
            </div>
            <div className="row third-left">
              <label htmlFor="daysToManufacture">Days To Manufacture:</label>
              <input
                value={ daysToManufacture }
                onBlur={ () => this.validate('daysToManufacture') }
                onChange={ e => this.setState({ daysToManufacture: e.target.value }) }
                id='daysToManufacture'
                type="text"/>
              <span className='lb-error'>
              {
                validateArray.find(function (obj) {
                  return obj.id === 'daysToManufacture';
                }).text
              }
            </span>
            </div>
          </div>
          <div className="row">
            <label htmlFor="size">Size</label>
            <input
              onBlur={ () => this.validate('size') }
              value={ size }
              onChange={ e => this.setState({ size: e.target.value }) }
              id='size'
              type="text"/>
            <span className='lb-error'>
            {
              validateArray.find(function (obj) {
                return obj.id === 'size';
              }).text
            }
            </span>
          </div>
          <div className="row">
            <label htmlFor="sizeUnitMeasureCode">Size Unit Measure Code</label>
            {
              selectData &&
              <Select
                id='sizeUnitMeasureCode'
                data={ selectData }
                selected={ selectSize }
                width='100%'
                onChange={ index => {
                  this.setState({ selectSize: index })
                } }
              />
            }
          </div>
          <div className="row">
            <label htmlFor="weight">Weight</label>
            <input
              value={ weight }
              onChange={ e => this.setState({ weight: e.target.value }) }
              id='weight'
              type="text"/>
          </div>
          <div className="row">
            <label htmlFor="weightUnitMeasureCode">Weight Unit Measure Code</label>
            {
              selectData &&
              <Select
                id='weightUnitMeasureCode'
                data={ selectData }
                selected={ selectWeight }
                width='100%'
                onChange={ index => {
                  this.setState({ selectWeight: index })
                } }
              />
            }

          </div>

          <div className="row">
            <label htmlFor="modelData">Model</label>
            <Select
              id='modelData'
              data={ modelData }
              selected={ selectModel }
              width='100%'
              onChange={ index => {
                this.setState({ selectModel: index })
              } }
            />
          </div>
          <div className="row">
            <label htmlFor="productCategoryData">Product Category</label>
            <Select
              id='productCategoryData'
              data={ productCategoryData }
              selected={ selectCategory }
              width='100%'
              onChange={ index => {
                this.setState({ selectCategory: index })
              } }
            />
          </div>
          <div className="wrapper clearfix">
            <div className="row third-left">
              <label htmlFor="ProductLine">Class</label>
              <Select
                id='ProductLine'
                data={ classSelect }
                selected={ classSelected }
                width='100%'
                onChange={ index => {
                  this.setState({ classSelected: index })
                } }
              />
            </div>
            <div className="row third-left">
              <label htmlFor="lineSelected">Line</label>
              <Select
                id='lineSelect'
                data={ lineSelect }
                selected={ lineSelected }
                width='100%'
                onChange={ index => {
                  this.setState({ lineSelected: index })
                } }
              />
            </div>
            <div className="row third-left">
              <label htmlFor="styleSelected">Style</label>
              <Select
                id='styleSelected'
                data={ styleSelect }
                selected={ styleSelected }
                width='100%'
                onChange={ index => {
                  this.setState({ styleSelected: index })
                } }
              />
            </div>
          </div>
          <div className="wrapper clearfix">
            <div className="row third-left">
              <label htmlFor="sellDate">Sell start date</label>
              <input
                onChange={ e => this.setState({ sellStartDate: e.target.value }) }
                value={ moment(sellStartDate).format('YYYY-MM-DD') }
                id='sellDate'
                type="date"
              />
            </div>
            <div className="row third-left">
              <label htmlFor="sellEndDate">Sell end date</label>
              <input
                onChange={ e => this.setState({ sellEndDate: e.target.value }) }
                value={ moment(sellEndDate).format('YYYY-MM-DD') }
                id='sellEndDate'
                type="date"
              />
            </div>
            <div className="row third-left">
              <label htmlFor="discontinuedDate">Discontinued date</label>
              <input
                onChange={ e => this.setState({ discontinuedDate: e.target.value }) }
                value={ moment(discontinuedDate).format('YYYY-MM-DD') }
                id='discontinuedDate'
                type="date"
              />
            </div>
          </div>
        </div>
        <Button
          style={ { display: 'block', margin: '0 auto' } }
          green
          type='button'
          onClick={ () => this.validate('submit') }
        >Submit
        </Button>,
      </form>
    )
  }
}

export default ItemForm;
