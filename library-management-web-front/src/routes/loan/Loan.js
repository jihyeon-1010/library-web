import React, { useState } from 'react';
import './loan.css';
import axios from 'axios';
import Nav from '../../components/Nav';
import Modal2 from './Modal2';
import { removeWhitespace, insertHyphensInPhoneNumber } from '../../utils/common';

const Loan = () => {
    const [searchMember, setSearchMember] = useState('');
    const [searchBook, setSearchBook] = useState('');
    const [loanDate, setLoanDate] = useState('');
    const [members, setMembers] = useState([]);
    const [books, setBooks] = useState([]);
    const [selectedUserId, setSelectedUserId] = useState(null);
    const [selectedBookId, setSelectedBookId] = useState(null);

    // 모달 상태
    const [isMemberModal, setIsMemberModal] = useState(false);
    const [isBookModal, setIsBookModal] = useState(false);

    const openMemberModal = () => {
        setIsMemberModal(true);
    };

    const closeMemberModal = () => {
        setIsMemberModal(false);
    };

    const openBookModal = () => {
        setIsBookModal(true);
    };

    const closeBookModal = () => {
        setIsBookModal(false);
    };

    // 회원 조회 
    const handleMemberSearch = async () => {
        try {
            const response = await axios.get('/user/member', { params: { query: searchMember } });
            setMembers(response.data);
            openMemberModal();
        }
        catch (error) {
            alert(error.response.data || error.response.data.message); 
            console.log(error.response.data);
        }
    };

    // 도서 조회 
    const handleBookSearch = async () => {
        try {
            const response = await axios.get('/book', { params: { query: searchBook.trim() } });
            setBooks(response.data);
            openBookModal();
        }
        catch (error) {
            alert(error.response.data || error.response.data.message); 
            console.log('검색 오류', error);
        }
    };

    // 도서 대출 
    const handleBookLoan = async () => {
        const ids = {
            userId: selectedUserId,
            bookId: selectedBookId,
            loanDate: loanDate
        };

        try {
            await axios.post('/book/loan', ids);
            alert('책 대여가 완료되었습니다.');

            window.location.reload();
        } catch (error) {
            alert(error.response.data || error.response.data.message); 
            console.error('책 대출 실패: ', error);
        }
    };

    return (
        <>
            {/* ---------------- 헤더 ---------------- */}
            <Nav />

            <div className='book-rental'>
                <h2>대여 관리</h2>
                {/* ---------------- 회원 검색 ---------------- */}
                <div className="input-section">
                    <h3>회원 검색</h3>
                    <input
                        type="text"
                        placeholder="이름, 생년월일, 연락처 뒷자리로 조회하기"
                        onChange={text => setSearchMember(removeWhitespace(text.target.value))}
                        value={searchMember}
                    />
                    <button onClick={handleMemberSearch}>조회하기</button>
                </div>

                {/* ---------------- 도서 검색 ---------------- */}
                <div className="input-section">
                    <h3>도서 검색</h3>
                    <input
                        type="text"
                        placeholder="도서명 검색"
                        onChange={text => setSearchBook(text.target.value)}
                        value={searchBook}
                    />
                    <button onClick={handleBookSearch}>조회하기</button>
                </div>

                {/* ---------------- 대여 날짜 ---------------- */}
                <div className="input-section">
                    <h3>대여 날짜</h3>
                    <input
                        type="date"
                        placeholder="대여 날짜"
                        onChange={text => setLoanDate(text.target.value)}
                        value={loanDate}
                    />
                </div>

                {/* ---------------- 목록 리스트 ---------------- */}
                {isMemberModal && <Modal2
                    isModal="members"
                    showList={members}
                    closeModal={closeMemberModal}
                    setSelected={setSelectedUserId}
                />}

                {isBookModal && <Modal2
                    isModal="books"
                    showList={books}
                    closeModal={closeBookModal}
                    setSelected={setSelectedBookId}
                />}

                {/* ---------------- 대여하기 ---------------- */}
                <div className='selected-info'>
                    {selectedUserId != null ? (
                        <div>
                            <span>{members.find(member => member.id === selectedUserId)?.name}</span>
                            <span>{members.find(member => member.id === selectedUserId)?.birthDate}</span>
                            <span>{insertHyphensInPhoneNumber(members.find(member => member.id === selectedUserId)?.phoneNumber)}</span>
                            <span>{members.find(member => member.id === selectedUserId)?.address}</span>
                            <span>{members.find(member => member.id === selectedUserId)?.regDate}</span>
                        </div>
                    ) : null}

                    {selectedBookId != null ? (
                        <div>
                            <span>{books.find(book => book.id === selectedBookId)?.name}</span>
                            <span>{books.find(book => book.id === selectedBookId)?.author}</span>
                            <span>{books.find(book => book.id === selectedBookId)?.publisher}</span>
                        </div>
                    ) : null}

                    {selectedUserId && selectedBookId && loanDate ? (
                        <button onClick={handleBookLoan} className='loan-button'>대여하기</button>
                    ) : null}
                </div>
            </div>
        </>
    );
};

export default Loan;
