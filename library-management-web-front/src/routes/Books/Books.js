import React, { useState } from 'react';
import axios from 'axios';
import Nav from '../../components/Nav';

const Books = () => {
    const [name, setName] = useState('');
    const [author, setAuthor] = useState('');
    const [publisher, setPublisher] = useState('');

    // 도서 등록 
    const handleRegister = async () => { 
        const newBook = {
            name: name.trim(),
            author: author.trim(),
            publisher: publisher.trim()
        }

        await axios.post('/book', newBook)
            .then(response => {
                alert('책 등록에 성공했습니다.');
                console.log('책 등록 성공', response.data);
            })
            .catch(error => {
                alert(error.response.data || error.response.data.message); 
                console.log('책 등록 실패: ', error);
            });
        
        setName('');
        setAuthor('');
        setPublisher('');
    };

    return (
        <>
            {/* ---------------- 헤더 ---------------- */}
            <Nav />

            <div className="management">
                <h2>도서 관리</h2>
                {/* ---------------- 책 등록 ---------------- */}
                <div className="input-section">
                    <h3>책 등록</h3>
                    <input
                        type="text"
                        placeholder="이름"
                        value={name}
                        onChange={text => setName(text.target.value)}
                    />
                    <input
                        type="text"
                        placeholder="저자명"
                        value={author}
                        onChange={text => setAuthor(text.target.value)}
                    />
                    <input
                        type="text"
                        placeholder="출판사"
                        value={publisher}
                        onChange={text => setPublisher(text.target.value)}
                    />
                    <button onClick={handleRegister}>등록</button>
                </div>
            </div>
        </>
    );
};

export default Books;

