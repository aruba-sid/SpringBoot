import React from "react";
import {Link} from 'react-router-dom';

const Home = () => {
    return (
        <div> 
            <h1> Welcome </h1>
            <h2> <Link to="/actors"> View Actors </Link> <br/>
            <Link to="/films"> View Films </Link> </h2>
        </div>
    );
};

export default Home;