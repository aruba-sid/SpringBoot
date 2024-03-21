import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

const FilmList = () => {
    const [films, setFilms] = useState([]);
    const { actorId } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        fetchFilmsByActor(actorId);
    }, [actorId]);

    const fetchFilmsByActor = async (actorID) => {
        try {
            const response = await fetch(`http://localhost:8080/home/films/${actorID}`);
            const data = await response.json();
            setFilms(data);
        } catch (error) {
            console.error("Error fetching data:", error);
        }
    };
    
    const handleGoBack = () => {
        navigate("/");
    };

    return (
        <div>
            <h2> Films for Actor ID: {actorId} </h2>
            <ul>
                {films.map((film, index) => (
                    <li key={index}>{film} </li>
                ))}
            </ul>
            <button onClick={handleGoBack}>Go Back</button>
        </div>
    );
};

export default FilmList;
