import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const AllFilms = () => {
    const [films, setFilms] = useState([]);
    const [actors, setActors] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetchFilms();
    }, []);

    const fetchFilms = async () => {
        try {
            const response = await fetch(`http://localhost:8080/home/allFilms`);
            const data = await response.json();
            setFilms(data);
        } catch (error) {
            console.error("Error fetching data:", error);
        }
    };

    const handleGoBack = () => {
        navigate("/");
    };

    const handleGetCast = async (filmID) => {
        try {
            const response = await fetch(`http://localhost:8080/home/${filmID}/cast`);
            const data = await response.json();
            setActors(data);
        } catch (error) {
            console.error("Error fetching data:", error);
        }
        console.log(`Getting cast for film from http://localhost:8080/home/${filmID}/cast`);
    };

    return (
        <div>
            <h2> Films </h2>
            <table className="film-table">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Release Year</th>
                        <th>Cast</th>
                    </tr>
                </thead>
                <tbody>
                    {films.map((film) => (
                        <tr key={film.filmID}>
                            <td>{film.title}</td>
                            <td>{film.description}</td>
                            <td>{film.releaseYear}</td>
                            <td><button onClick={() => handleGetCast(film.filmID)}>View Cast</button></td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <button onClick={handleGoBack}>Go Back</button>
        </div>
    );
};




export default AllFilms;