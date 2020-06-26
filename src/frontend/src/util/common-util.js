/*
 * 유틸성 JavaScript 함수들 모아 놓는 클래스로 활용
 */
class CommonUtil {

    /* 
     * 넘어온 값이 빈값인지 체크합니다. 
     * !value 하면 생기는 논리적 오류를 제거하기 위해 명시적으로 value == 사용 [], {} 도 빈값으로 처리
     */ 
    isEmpty (value) {
        if (value === null) return true 
        if (typeof value === 'undefined') return true 
        if (typeof value === 'string' && value === '') return true 
        if (Array.isArray(value) && value.length < 1) return true 
        if (typeof value === 'object' && value.constructor.name === 'Object' && Object.keys(value).length < 1 && Object.getOwnPropertyNames(value) < 1) return true 
        if (typeof value === 'object' && value.constructor.name === 'String' && Object.keys(value).length < 1) return true // new String() 
        return false
    }
}

export default new CommonUtil();