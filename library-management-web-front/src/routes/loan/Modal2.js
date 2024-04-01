import React from 'react';
import { insertHyphensInPhoneNumber } from '../../utils/common';

const Modal2 = ({ isModal, showList, closeModal, setSelected }) => {
    console.log(showList)
    return isModal === 'members' ? (
        <div className='modal-2'>
            <div className='modal-content-2'>
                <h2>회원 정보</h2>
                <span className="close-button" onClick={closeModal}>&times;</span>
                <div className='modal-list'>
                    {showList.map(member => (
                    <div className='modal-content2' key={member.id}>
                        <span>{member.name}</span>
                        <span>{member.birthDate}</span>
                        <span>{insertHyphensInPhoneNumber(member.phoneNumber)}</span>
                        <span>{member.address}</span>
                        <span>{member.regDate}</span>
                        <span className='select-button'>
                            <button
                                onClick={() => { setSelected(member.id); closeModal(); }}
                            >
                                선택
                            </button>
                        </span>
                    </div>   
                    ))}
                </div>
            </div>
        </div>
    ) : (
        <div className='modal-2'>
            <div className='modal-content-2'>
                <h2>도서 정보</h2>
                <span className="close-button" onClick={closeModal}>&times;</span>
                <div className='modal-list'>
                    {showList.map(book => (
                    <div className='modal-content2' key={book.id}>
                            <span>{book.name}</span>
                        
                        <span>{book.author}</span>
                        <span>{book.publisher}</span>
                        <span className='select-button'>
                            <button
                                onClick={() => { setSelected(book.id); closeModal(); }}
                            >
                                선택
                            </button>
                        </span>
                    </div>   
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Modal2;
