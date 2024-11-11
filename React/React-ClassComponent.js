// src/ClassComponent.js
import React from 'react';
class Car extends React.Component{
  constructor() {
    super();
    this.state = {color: "State"};
  }
  changeColor = () => {
    this.setState({color: "blue"});
  }

  render() {
    return (
      <div>
        <center>
        <h3>This is class component.</h3>
        <p>
        It is a {this.state.color}
        </p>
        <button
          type="button"
          onClick={this.changeColor}
        >Change color</button>
      
        <h3>I am a {this.props.color} Car!(props)</h3>

        
       
        <img src="https://files.codingninjas.in/article_images/class-components-1-25673.webp" width="20%" height="20%"/>
        </center>
      </div>
    );
  }
}

export default Car;