window.onload = function () {
    // 로컬 스토리지에서 토큰 확인
    const authToken = localStorage.getItem('X-READYS-AUTH-TOKEN');

    // 로그인 버튼과 로그아웃 버튼 요소 가져오기
    const loginButton = document.getElementById('loginButton');
    const signUp = document.getElementById('signUp');
    const logoutButton = document.getElementById('logoutButton');

    // 토큰 유무에 따라 버튼 표시 조정
    if (authToken !== null && authToken !== "") {
        loginButton.style.display = 'none';
        signUp.style.display = 'none'
        logoutButton.style.display = 'block';
    } else {
        loginButton.style.display = 'block';
        signUp.style.display = 'block'
        logoutButton.style.display = 'none';
    }
};

function logout() {
    // 로컬 스토리지에서 토큰 삭제
    localStorage.removeItem('X-READYS-AUTH-TOKEN');
    // 페이지 새로고침
}