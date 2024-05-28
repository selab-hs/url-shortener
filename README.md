# url-shortner

### Feature

1. 3개가 필요.
   - 사용자로부터 Request로 긴 url을 받으면, 짧은 url을 반환한다. (API.1)
     - www.selab.com/sdkj1243
     - 짧으면 짧을수록 좋다.
     - 단축 URL에는 숫자와 영문자만 사용할 수 있다.
     - 시스템을 단순화하기 위해 삭제나 갱신은 할 수 없다.
   - 짧은 url을 요청했을때, 긴 url을 Redirect 한다. (API.2)
     - www.selab.com/sdkj1243 -> 긴 Url 제공
   - 조회 API
      - shortUrl로 originUrl 조회 -> 70ms안에
      - originUrl로 shortUrl 조회 -> 70ms안에

2. LOGIN은 없다.
   - 대신, 특정 사용자 요청에 대해서, 최대 10번까지만 가능하다. (Rate Limiter)

3. 생성된 Url은 최대 6개월까지만 사용 가능
   - 삭제. 

4. 일 1000만개의 생성 요청이 들어온다.
   - API의 응답 속도는 최대 70ms이다. (서버의 사양에 따라 달라짐)

5. 긴 데이터에 대해서는 메모리 이슈가 발생할 수 있기 때문에, 압축이 필요.
