import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const ActorList = () => {
    const [actors, setActors] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetchActors();
    }, []);

    const fetchActors = async () => {
        try {
            const response = await fetch(`http://localhost:8080/home/allActors`);
            const data = await response.json();
            setActors(data);
        } catch (error) {
            console.error('Error fetching actors:', error);
        }
    };

    const handleViewFilms = (actorId) => {
        navigate(`/films/${actorId}`);
    };

    return (
        <div>
            <h2> Actors </h2>
            <ul className="actor-list">
                {actors.map((actor) => (
                    <li key={actor.actorID}>
                        <span className="actor-name"> {actor.firstName} {actor.lastName} </span>
                        <button className="view-button" onClick={() => handleViewFilms(actor.actorID)}> View Films </button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ActorList;
