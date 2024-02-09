import React from 'react'
import BannerImage from "../assets/background.jpg";
import "../styles/Contact.css"  
function Contact() {
  return (
    <div className='contact'>
      <div 
      className='leftSide'
      style={{ backgroundImage: `url(${BannerImage})`}}></div>
      <div className='rightSide'>
        <h1>Get in Touch</h1>
        <form id='contact-form'>
            <label htmlFor="name">Name: </label>
            <input type='text' name='name' required placeholder='Enter full name...'/>
            <label htmlFor="email">Email: </label>
            <input type='text' name='email' required placeholder='Enter your e-mail...'/>
            <label htmlFor="message">Feedback: </label>
            <textarea name="message" rows="6" required placeholder='Enter your feedback here ..'/>
            <button type='submit'>Send</button>
        </form>
      </div>
    </div>
  )
}

export default Contact
