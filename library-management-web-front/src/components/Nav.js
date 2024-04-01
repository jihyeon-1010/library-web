import React from 'react';
import { Link } from 'react-router-dom';

const Nav = () => {
    return (
        <div className="buttons-container">
            <Link to="/" className="button">회원관리</Link>
            <Link to="/books" className="button">도서관리</Link>
            <Link to="/loan" className="button">대여관리</Link>
            <Link to="/return" className="button">반납관리</Link>
        </div>
    );
};

export default Nav;
