# ✂️ url-shortner

**✂️긴 URL 을 짧은 URL로 변환하는 서비스 입니다.**   

## 📌 화면 구성
<p align="center">
    <img src="https://github.com/selab-hs/url-shortener/assets/76032947/1abab77e-d71c-44af-9487-bd2183efddf6" alt="First Image" width="45%"/>
  <img src="https://github.com/selab-hs/url-shortener/assets/76032947/0798670d-4b43-401c-be69-a583e5036e7c" alt="Second Image" width="45%"/>
</p>

### 🏭 Project 아키텍쳐


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
