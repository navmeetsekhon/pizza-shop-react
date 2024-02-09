import React from 'react'
import '../styles/Home.css'
import {Link} from "react-router-dom"
import BannerImage from "../assets/background.jpg";
function Home() {
  return (
    <div className='home'  style={{ backgroundImage: `url(${BannerImage})`}}>
      <div className='headerContainer'>
        <h1>Welcome to Pizza by Alfredo</h1>
        <p>Delicious pizzas!</p>
        <Link to="/menu">
        <button>ORDER NOW</button>
        </Link>
      </div>
      
    </div>
  )
}

export default Home
