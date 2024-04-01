import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { Books, Members, Loan, Return } from './routes';

const Navigation = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route exact path='/' element={<Members />} />
                <Route exact path='/loan' element={<Loan />} />
                <Route exact path='/books' element={<Books />} />
                <Route exact path='/return' element={<Return />} />
            </Routes>
        </BrowserRouter>
    );
};

export default Navigation;
