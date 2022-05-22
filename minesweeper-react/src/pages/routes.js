import React from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import Home from './Home';
import NewGameForm from './NewGameForm';

// function Routes() {
//     return (
//         <div>
//         <Routes>
//             <Route path="/teste" component={Landing} exact/>
//         </Routes>
//         </div>
//     );
// }

// export default Routes;

const Routes = () => {
    return(
        <BrowserRouter>
            <Route element = { <Home/> }  path="/"  />
            <Route element = { <NewGameForm/> }  path="/new-game"  />
        </BrowserRouter>
    )
 }
 
 export default Routes;