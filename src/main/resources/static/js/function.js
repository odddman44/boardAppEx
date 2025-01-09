/**
 * 문자열의 마지막(끝) 문자의 종성 포함 여부 확인
 * @param value - Target String
 * @returns 종성 포함 여부
 */
function hasCoda(value) {
    return ((value.charCodeAt(value.length - 1) - 0xAC00) % 28) > 0;
}


/**
 * 필드(Elemenet) 유효성 검사
 * @param target - 검사 대상 Element
 * @param fieldName - 필드명
 * @param focusTarget - 포커스 대상 Element
 * @returns 필드 입력(선택) 여부
 */
function isValid(target, fieldName, focusTarget) {
    if (target.value.trim()) {
        return true;
    }

    const particle = (hasCoda(fieldName)) ? '을' : '를'; // 조사
    const elementType = (target.type === 'text' || target.type === 'password' || target.type === 'search' || target.type === 'textarea') ? '입력' : '선택';
    alert( `${fieldName + particle} ${elementType}해 주세요.` );

    target.value = '';
    ( !focusTarget ? target : focusTarget).focus();
    throw new Error(`"${target.id}" is required...`)
}

async function getJson(uri, params) {
    if (params) {
        uri = uri + '?' + new URLSearchParams(params).toString();
    }
    const response = await fetch(uri);

    if (!response.ok) {
        await response.json().then(error => {
            throw error;
        });
    }

    return await response.json();
}

/**
 * 데이터 저장/수정/삭제
 * @param uri - API Request URI
 * @param method - API Request Method ('POST', 'PUT', 'DELETE')
 * @param params - Parameters (Object)
 * @returns json - 결과 데이터
 */
async function callApi(uri, method, params) {
    try {
        const response = await fetch(uri, {
            method: method,
            headers: {
                'Content-Type': 'application/json; charset=utf-8',
            },
            body: params ? JSON.stringify(params) : null,
        });

        if (!response.ok) {
            const error = await response.json();
            throw error;
        }

        return await response.json();
    } catch (error) {
        console.error('API 요청 중 오류 발생:', error);
        throw error;
    }
}