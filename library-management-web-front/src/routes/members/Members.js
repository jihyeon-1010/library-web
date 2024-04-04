import React, { useEffect, useState } from 'react';
import '../../utils/style.css';
import './members.css';
import Nav from '../../components/Nav';
import axios from 'axios';
import { removeWhitespace, insertHyphensInPhoneNumber } from '../../utils/common';
import Modal from './Modal';

const Members = () => {
    // 회원 등록 폼 상태
    const [name, setName] = useState('');
    const [birthdate, setBirthdate] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [address, setAddress] = useState('');
    const [registrationDate, setRegistrationDate] = useState('');

    // 회원 목록 상태
    const [members, setMembers] = useState([]);

    // 모달 상태
    const [isModalOpen, setIsModalOpen] = useState(false);
    
    // 선택한 회원 상태
    const [selectedMember, setSelectedMember] = useState(null);

    // 회원 목록
    useEffect(() => {
        axios.get('/user')
            .then(response => {
                setMembers(response.data);
            })
            .catch(error => {
                console.log('error: ', error);
            });
    }, []);

    // 회원 등록 
    const handleRegister = async () => {
        const newMember = {
            name: name,
            birth_date: birthdate,
            phone_number: phoneNumber,
            address: address.trim(),
            reg_date: registrationDate,
        };

        await axios.post('/user', newMember)
            .then(response => {
                alert('회원 등록에 성공했습니다.')
                console.log('회원 등록 성공', response);

                window.location.reload();
            })
            .catch(error => {
                alert(error.response.data)
                console.log('회원 등록 실패: ', error);
            });
        
        setName('');
        setBirthdate('');
        setPhoneNumber('');
        setAddress('');
        setRegistrationDate('');
    };
    
    // 모달 열기
    const openModal = (member) => {
        setSelectedMember(member);
        setIsModalOpen(true);
    };

    // 모달 닫기
    const closeModal = () => {
        setSelectedMember(null);
        setIsModalOpen(false);
    };

    // 회원 삭제
    const handleDeleteMember = async (userName, userId) => {
        const confirmDelete = window.confirm(`정말 ${userName} 회원을 삭제하시겠습니까?`);

        if (confirmDelete) {
            try {
                await axios.delete('/user', { params: { id: userId } })
                alert(`${userName} 회원이 삭제되었습니다.`);

                window.location.reload();
            }
            catch (error) {
                alert('회원 삭제에 실패했습니다.');
            }
        }
    };

    return (
        <>
            {/* ---------------- 헤더 ---------------- */}
            <Nav />

            {/* ---------------- 회원 등록 ---------------- */}
            <div className="management">
                <h2>회원 관리</h2>
                <div className="input-section">
                    <h3>회원 등록</h3>
                    <input
                        type="text"
                        placeholder="이름"
                        value={name}
                        onChange={text => setName(removeWhitespace(text.target.value))}
                    />
                    <input
                        type="text"
                        placeholder="생년월일 (YYMMDD)"
                        value={birthdate}
                        onChange={text => setBirthdate(removeWhitespace(text.target.value))}
                    />
                    <input
                        type="text"
                        placeholder="연락처 (01012345678)"
                        value={phoneNumber}
                        onChange={text => setPhoneNumber(removeWhitespace(text.target.value))}
                    />
                    <input
                        type="text"
                        placeholder="주소"
                        value={address}
                        onChange={text => setAddress(text.target.value)}
                    />
                    <input
                        type="date"
                        placeholder="가입일"
                        value={registrationDate}
                        onChange={text => setRegistrationDate(text.target.value)}
                    />
                    <button onClick={handleRegister}>등록</button>
                </div>

                {/* ---------------- 회원 목록 ---------------- */}
                <div className="member-list-section">
                    <h3>회원 목록</h3>
                    <p>총 회원 수 : {members.length}</p>
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

                    </table>
                </div>
            </div>

            {/* ---------------- 회원 수정 모달 ---------------- */}
            {isModalOpen && <Modal
                selectedMember={selectedMember}
                closeModal={closeModal}
            />}
        </>
    );
};

export default Members;
