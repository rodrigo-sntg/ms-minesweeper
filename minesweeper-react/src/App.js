// import logo from './logo.svg';
// import React, { useReducer, useState } from 'react';
// import './App.css';

// const formReducer = (state, event) => {
//   return {
//     ...state,
//     [event.name]: event.value
//   }
//  }

// function App() {
//   const [formData, setFormData] = useReducer(formReducer, {});
//   const [submitting, setSubmitting] = useState(false);
//   const handleSubmit = event => {
//     event.preventDefault();
//    setSubmitting(true);

//    setTimeout(() => {
//      setSubmitting(false);
//    }, 3000)
//  }
//  const handleChange = event => {
//   setFormData({
//     name: event.target.name,
//     value: event.target.value,
//   });
// }

//   return (
//     <div className="wrapper">
//       <h1>How About Them Apples</h1>
//       {submitting &&
//         <div>
//           You are submitting the following:
//           <ul>
//             {Object.entries(formData).map(([name, value]) => (
//               <li key={name}><strong>{name}</strong>: {value.toString()}</li>
//             ))}
//           </ul>
//         </div>
//       }
//       <form onSubmit={handleSubmit}>
//         <fieldset>
//           <label>
//             <p>Name</p>
//             <input name="name" onChange={handleChange}/>
//           </label>
//         </fieldset>
//         <fieldset>
//          <label>
//            <p>Apples</p>
//            <select name="apple" onChange={handleChange}>
//                <option value="">--Please choose an option--</option>
//                <option value="fuji">Fuji</option>
//                <option value="jonathan">Jonathan</option>
//                <option value="honey-crisp">Honey Crisp</option>
//            </select>
//          </label>
//          <label>
//            <p>Count</p>
//            <input type="number" name="count" onChange={handleChange} step="1"/>
//          </label>
//          <label>
//            <p>Gift Wrap</p>
//            <input type="checkbox" name="gift-wrap" onChange={handleChange} />
//          </label>
//        </fieldset>
//         <button type="submit">Submit</button>
//       </form>
//     </div>
//   );
// }

// export default App;



import React from 'react';

import './assets/styles/global.css';
import Home from './pages/Home';
import { Route, BrowserRouter, Routes } from "react-router-dom";
import NewGameForm from './pages/NewGameForm';
import ContinueGameForm from './pages/ContinueGameForm';
import Game from './pages/Game';
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path = "/" element={<Home />} />
        <Route path="/new-game" element={<NewGameForm />}/>
        <Route path="/continue" element={<ContinueGameForm />}/>
        <Route path="/game/:code" element={<Game />}/>

      </Routes>
    </BrowserRouter>
  );
}

export default App;
