import { BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Home from './Home';
import ActorList from "./ActorList";
import FilmList from "./FilmList"; 
import AllFilms from "./AllFilms";
import './App.css'

function App() {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Home />} />
        <Route path="/actors" exact element={<ActorList/>} />
        <Route path="/films" exact element={<AllFilms/>} />
        <Route path="/films/:actorId" element={<FilmList/>} />
      </Routes>
    </Router>
  );
};

export default App
