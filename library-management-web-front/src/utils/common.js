// 문자열 공백 제거 
export const removeWhitespace = text => {
    const regex = /\s/g;
    return text.replace(regex, '');
};

// 전화번호 파싱
export const insertHyphensInPhoneNumber = text => {
    if (!text) return ''; // 전화번호가 정의되어 있지 않으면 빈 문자열 반환

    const digitsOnly = text.replace(/\D/g, ''); // 숫자 이외의 문자 제거
    const formattedPhoneNumber = digitsOnly.replace(/(\d{3})(\d{3,4})(\d{4})/, '$1-$2-$3'); // 전화번호 형식에 맞게 하이픈 삽입

    return formattedPhoneNumber;
};
