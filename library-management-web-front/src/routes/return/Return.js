import React, { useState } from 'react';
import './return.css';
import axios from 'axios';
import Nav from '../../components/Nav';
import { removeWhitespace, insertHyphensInPhoneNumber } from '../../utils/common';

const Return = () => {
    const [searchMember, setSearchMember] = useState('');
    const [members, setMembers] = useState([]);
    const [selectedUserId, setSelectedUserId] = useState(null);
    const [showMemberList, setShowMemberList] = useState(false);
    const [loanHistoryMap, setLoanHistoryMap] = useState({}); 
    const [noLoanHistoryMessage, setNoLoanHistoryMessage] = useState('');
    
    // 반납 완료된 책과 반납 대기 중인 책을 분류하여 정렬
    const completedBooks = loanHistoryMap[selectedUserId]?.filter(history => history.return).slice().sort((a, b) => new Date(b.loanDate) - new Date(a.loanDate));
    const pendingBooks = loanHistoryMap[selectedUserId]?.filter(history => !history.return).slice().sort((a, b) => new Date(b.loanDate) - new Date(a.loanDate));

    // 회원 조회 
    const handleMemberSearch = async () => {
        try {
            const response = await axios.get('/user/member', { params: { query: searchMember } });
            setMembers(response.data);
            setShowMemberList(true);
            console.log(response.data);
        }
        catch (error) {
            alert(error.response.data || error.response.data.message); 
            console.log('검색 오류', error);
        }
    };

    // 회원의 도서 대여 기록
    const fetchMemberBooks = async (id, name) => {
        try {
            const response = await axios.get(`/user/${id}`);
            setLoanHistoryMap(prevState => ({ ...prevState, [id]: response.data })); 
            setNoLoanHistoryMessage(''); 
            console.log(response.data);
        }
        catch (error) {
            if (error.response.data) {
                setNoLoanHistoryMessage(`${name} 회원님의 대여 기록이 없습니다.`);
            }
            else {
                setNoLoanHistoryMessage('');
            }
            
            console.log('검색 오류', error);
        }
    };

    // 도서 반납
    const handleReturnButtonClick = async (bookId) => {
        const returnBook = {
            userId: selectedUserId,
            bookId: bookId
        }

        try {
            await axios.put('/book/return', returnBook);
            alert('책 반납이 완료되었습니다.');

            window.location.reload();
        }
        catch (error) {
            alert(error.response.data || error.response.data.message); 
            alert('책 반납에 실패했습니다.');
            console.log('책 반납 실패: ', error);
        }
    };

    return (
        <>
            {/* ---------------- 헤더 ---------------- */}
            <Nav />
            <div className='management'>
                <h2>반납 관리</h2>

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

                {/* ---------------- 회원 표시 ---------------- */}
                {showMemberList && (
                    <table>
                        <thead>
                            <tr>
                                <th>이름</th>
                                <th>생년월일</th>
                                <th>핸드폰 번호</th>
                                <th>주소</th>
                                <th>가입일</th>
                            </tr>
                        </thead>
                        <tbody>
                            {members.map(member => (
                                <tr key={member.id}>
                                    <td>{member.name}</td>
                                    <td>{member.birthDate}</td>
                                    <td>{insertHyphensInPhoneNumber(member.phoneNumber)}</td>
                                    <td>{member.address}</td>
                                    <td>{member.regDate}</td>
                                    <td>
                                        <button
                                            onClick={() => {
                                                fetchMemberBooks(member.id, member.name);
                                                setShowMemberList(false);
                                                setSelectedUserId(member.id);
                                            }}
                                        >
                                            선택
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                )}

                {/* ---------------- 도서 표시 ---------------- */}
                {!showMemberList && (
                    <div>
                        {noLoanHistoryMessage && <div>{noLoanHistoryMessage}</div>}
                        {/* 반납 대기 중인 책 표시 */}
                        {pendingBooks && pendingBooks.map(history => (
                            <div key={history.id} className="history-item" style={{ cursor: 'pointer' }}>
                                <span>{history.name}</span>
                                <span>{history.author}</span>
                                <span>{history.publisher}</span>
                                <span>{history.loanDate}</span>
                                <button
                                    className="return-btn return-pending"
                                    onClick={() => handleReturnButtonClick(history.bookId)}
                                >
                                    반납하기
                                </button>
                            </div>
                        ))}

                        {/* 반납 완료된 책 표시 */}
                        {completedBooks && completedBooks.map(history => (
                            <div key={history.id} className="history-item" style={{ cursor: 'pointer' }}>
                                <span>{history.name}</span>
                                <span>{history.author}</span>
                                <span>{history.publisher}</span>
                                <span>{history.loanDate}</span>
                                <button className="return-btn return-completed" disabled>반납완료</button>
                            </div>
                        ))}
                    </div>
                )}
            </div>
        </>
    );
};

export default Return;
