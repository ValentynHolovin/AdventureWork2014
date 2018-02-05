import React, { Component } from 'react';
import ItemForm from '../../components/Form';
import { submitNewItem } from '../../api'
import './styles.css';

class Create extends Component {

  submit = submitObj => {
    submitNewItem(submitObj).then(data =>{
      if(data.status === 500){
        alert('Some thing went wrong...');
      } else {
        this.props.goTo('/product', data);
      }
    }).catch(err => {
      alert('Some thing went wrong...');
      console.log('Error ', err);
    });
  };

  render() {

    return(
      <div className='created-wrapper'>
        <h2>Add new item</h2>
        <ItemForm
          submit = { (submitObj) => this.submit(submitObj) }
        />
      </div>
    )

  }
}

export default Create;