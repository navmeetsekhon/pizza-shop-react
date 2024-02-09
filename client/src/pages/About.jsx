import React from "react";
import BannerImage from "../assets/background.jpg";
import "../styles/About.css"
function About() {
  return (
    <div className="about">
      <div
        className="aboutTop"
        style={{ backgroundImage: `url(${BannerImage})` }}
      ></div>
      <div className="aboutBottom">
        <h1> ABOUT US</h1>
        <p>
        Pizza by Alfredo, a beloved pizzeria that originated in the heart of Scranton, Pennsylvania, is a testament to the American dream brought to life by a passionate Mexican immigrant couple. Nestled in the vibrant community, this pizza shop has become a local gem, drawing patrons with its unique blend of Mexican flavors infused into traditional Italian cuisine.

Founded by Carlos and Maria Hernandez, who arrived in Scranton with a dream of creating a place where people could savor the delicious fusion of their Mexican heritage and the classic appeal of pizza. Pizza by Alfredo seamlessly intertwines the rich, spicy notes of Mexican spices with the comforting taste of traditional Italian crust and toppings.

The couple's dedication to quality ingredients and authentic flavors sets Alfredo's apart. From the mouthwatering chorizo and jalapeño pizza to the delectable queso fresco-studded Margherita, each slice is a celebration of culinary diversity. The vibrant atmosphere within Alfredo's echoes the warmth of its founders, creating a welcoming space that reflects the spirit of Scranton's cultural melting pot.

In a city known for its industrial history, Pizza by Alfredo stands as a testament to the transformative power of passion, hard work, and the blending of diverse culinary traditions, leaving an indelible mark on the taste buds and hearts of Scranton locals.
        </p>
      </div>
    </div>
  );
}

export default About;
