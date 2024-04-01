import React, { useState } from 'react';
import { removeWhitespace } from '../../utils/common';
import axios from 'axios';

const Modal = ({ selectedMember, closeModal }) => {
    const [name, setName] = useState(selectedMember.name);
    const [phoneNumber, setPhoneNumber] = useState(selectedMember.phoneNumber);
    const [address, setAddress] = useState(selectedMember.address);
    
    // 회원 수정
    const handleUpdateMember = async () => {
        const updateMember = {
            id: selectedMember.id,
            name: name,
            phoneNumber: phoneNumber,
            address: address
        }
        try {
            await axios.put('/user', updateMember);
            alert('회원 수정이 완료되었습니다.');

            window.location.reload();
        } 
        catch (error) {
            alert('회원 수정에 실패했습니다.');
            console.log('회원 업데이트 실패: ', error);
        }
        closeModal();
    };

    return (
        <div className="modal">
            <div className="modal-content">
                <span className="close-button" onClick={closeModal}>&times;</span>
                <h2>회원 수정</h2>
                <input
                    type="text"
                    placeholder="이름"
                    value={name}
                    onChange={text => setName(removeWhitespace(text.target.value))}
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
                <button onClick={handleUpdateMember}>수정 완료</button>
            </div>
        </div>
    );
};

export default Modal;
