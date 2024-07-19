# ✂️ url-shortner

> **긴 URL 을 짧은 URL로 변환하는 서비스 입니다.**   

### 🏭 Project 아키텍쳐

![readys-link](https://github.com/user-attachments/assets/e2862b7e-c67f-4fb6-8f4d-01b188de3aed)


## 📌 화면 구성

### 비회원 화면
<p align="center">
    <img width="1282" alt="스크린샷 2024-07-18 오후 11 33 07" src="https://github.com/user-attachments/assets/90631055-f033-4cc1-8bf8-944059294966">
    <img width="2558" alt="스크린샷 2024-07-18 오후 11 33 18" src="https://github.com/user-attachments/assets/932fed0d-bba0-420b-aec8-0477bcb39d5e">
</p>

### 회원 가입/로그인
<img width="770" alt="스크린샷 2024-07-18 오후 11 33 38" src="https://github.com/user-attachments/assets/10ea3b32-579c-4d9f-8fc6-59051b643721">
<img width="749" alt="스크린샷 2024-07-18 오후 11 33 46" src="https://github.com/user-attachments/assets/c7da024d-277e-44d0-bcfb-0b55bc1903f7">


### 회원 화면
<p align="center">
<img width="1277" alt="스크린샷 2024-07-18 오후 11 34 21" src="https://github.com/user-attachments/assets/05b4e422-7660-4cf5-8caa-bec4cedee409">
    Member가 생성한 shortUrl 확인 게시판
<img width="2557" alt="스크린샷 2024-07-18 오후 11 34 43" src="https://github.com/user-attachments/assets/1d6f888b-a4f1-40b0-b073-48c54a765101">
    Member가 생성한 shortUrl에 접근 기록 게시판
</p>

### 📋 주요 기능 소개

|API|URL|소개|
|:--------|:---------|:------------|
|메인 화면 이동| "Domain"/main| main 화면을 반환한다.|
|url 최적화| "Domain"/api/v1/shortenr| 사용자로부터 Request로 긴 url을 받으면, 짧은 url을 반환한다.|
|shortUrl로 originUrl 호출|"Domain"/{shortUrl}|짧은 url을 요청했을때, 긴 url을 Redirect 한다.|

   - 단축 URL에는 숫자와 영문자만 사용할 수 있다.
   - 시스템을 단순화하기 위해 삭제나 갱신은 할 수 없다.
   - 조회 API
      - shortUrl로 originUrl 조회 -> 70ms안에
      - originUrl로 shortUrl 조회 -> 70ms안에

2. LOGIN은 없다.(추가 요청)
   - 대신, 특정 사용자 요청에 대해서, 최대 10번까지만 가능하다. (Rate Limiter)

3. 생성된 Url은 최대 6개월까지만 사용 가능
   - 자정마다 6개월 지난 Url은 자동 삭제 

4. 일 1000만개의 생성 요청이 들어온다.
   - API의 응답 속도는 최대 70ms이다. (서버의 사양에 따라 달라짐)

5. 긴 데이터에 대해서는 메모리 이슈가 발생할 수 있기 때문에, 압축이 필요.
