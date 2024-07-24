# ✂️ url-shortner


> **긴 URL 을 짧은 URL로 변환하는 서비스 입니다.**
>
> 
> **서비스 링크** : [URL Shortenr Service](https://readys.link/)   
> **개발 기간** : 2024.06 ~ 2024.07   

## 개발팀 소개
---



## 목차

[1. 프로젝트 소개](#프로젝트-소개)

[2. Stacks](#stacks)   
&nbsp;&nbsp;&nbsp;&nbsp;[2-1. Environment](#environment)   
&nbsp;&nbsp;&nbsp;&nbsp;[2-2. Development](#development)   
&nbsp;&nbsp;&nbsp;&nbsp;[2-3. CI/CD](#CI-CD)

[3. 주요 기능 소개](#주요-기능-소개)   
&nbsp;&nbsp;&nbsp;&nbsp;[3-1. 프로젝트 제악 사항](#프로젝트-제악-사항)   
&nbsp;&nbsp;&nbsp;&nbsp;[3-2. Issu 해결](#issu-해결)   

[4. 아키텍처](#project-아키텍처)

[5. 화면 구성](#화면-구성)   
&nbsp;&nbsp;&nbsp;&nbsp;[5-1. 비회원](#비회원-화면)   
&nbsp;&nbsp;&nbsp;&nbsp;[5-2. 회원가입 / 로그인](#회원가입-로그인)   
&nbsp;&nbsp;&nbsp;&nbsp;[5-3. 회원](#회원-화면)
  

---


### 프로젝트 소개

<div>
    
**사용하기 긴 url을 짧은 길이로 url을 사용하기 위해 시작된 프로젝트입니다.**    
간편한 서비스로 대량의 요청을 견디는 아키텍처를 적용하여 안정적인 서비스를 제공합니다.

이 프로젝트는 "가상면접 사례로 배우는 대규모 시스템 설계 기초" 8장을 기반으로 추가적인 기능을 더해 진행되었습니다.   
대량의 **트래픽을 감당할 수 있는 서버** 구축 **솔루션**을 공부하고 적용하여 익히기 위한 토이 프로젝트입니다.

---

</div>

## Stacks

<div align="center">
    
### Environment

<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"/> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"/>

### Development

<img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white"/> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"/>
<img src="https://img.shields.io/badge/Bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white"/>

<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/> <img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white"/>

<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/> <img src="https://img.shields.io/badge/Redis-FF4438?style=for-the-badge&logo=redis&logoColor=white"/>


<img src="https://img.shields.io/badge/aws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"/> <img src="https://img.shields.io/badge/linux-FCC624?style=for-the-badge&logo=linux&logoColor=black"/>

### CI CD

<img src="https://img.shields.io/badge/GithubAction-2088FF?style=for-the-badge&logo=github&logoColor=black"/> <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=black"/>

### Communication

<img src="https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=discord&logoColor=black"/>

---

</div>

## 주요 기능

**회원 관리**
- 로그인 / 회원가입 기능

**비회원**
- 긴 URL을 단축한 url(숫자와 영문자) 제공
- 2분 안에 최대 10번의 요청 회수 제안

**회원**
- 긴 URL을 단축한 url(숫자와 영문자) 제공
- 2분 안에 최대 10번의 요청 회수 제안
- 생성한 Short URL 리스트 제공
- 생성한 Short URL 에 접근한 기록 제공

---

## 프로젝트 제악 사항

   
    - 단축 URL에는 숫자와 영문자만 사용할 수 있다.
    - 시스템을 단순화하기 위해 삭제나 갱신은 할 수 없다.
    - 조회 API
        - shortUrl로 originUrl 조회 -> 70ms안에
        - originUrl로 shortUrl 조회 -> 70ms안에
  
  
    Guest
      - 특정 사용자 요청에 대해서, 최대 10번까지만 가능하다. (Rate Limiter)
    
    Meber
      - 생성한 단축 url을 확인이 가능하다.
      - 생성한 단축 url에 접근한 SystemActionLog를 확인이 가능하다.


    생성된 Url은 최대 6개월까지만 사용 가능
      - 자정마다 6개월 지난 Url은 자동 삭제 


    일 1000만개의 생성 요청이 들어온다.
      - API의 응답 속도는 최대 70ms이다. (서버의 사양에 따라 달라짐)
 

    긴 데이터에 대해서는 메모리 이슈가 발생할 수 있기 때문에, 압축이 필요.

---

## Issu 해결 

### As Is

1. origin url 을 Short url 변환 기능 구현
2. 동시에 대량의 중복되는 조회 request 최적화
3. short url 접근 내용 기록
4. 회원 가입시 비회원 때 생성한 short url 기록에 가입한 memberId 업데이트
5. 6개월 지난 Url은 자동 삭제 기능 구현
6. 비회원과 회원이 생성한 short url의 저장된 memberId 를 System Action Log DB 와 싱크 맞추기
  
### Challenge

**1. 빠른 작업 속도를 위해 Shortcode 생성과 캐시 저장 과정을 멀티스레드를 이용해서 기능 최적화**   

      1-1. origin url 을 중복 검사 없이 DB에 저장하여 저장된 Index를 Base62(숫자와 영문자)로 인코딩하여 Shortcode를 생성   
      1-2. 생성하면서 멀티스레드를 적용하여 새로운 스레드가 Redis에 Key(Shortcode) - Value(origin url) 형식으로 저장, 일정 시간 동안 유지


**2. 중복되는 검사를 빠르게 처리하기 위해 캐시의 원리를 이용하여 프로젝트에 적용하여 중복 검색은 시간복잡도 O(1)로 성능 최적화**
   
      2-1. 1차로 Redis에서 요청 받은 Shortcode를 key값으로 검색   
         캐시히트 : 검색된 value(origin url)을 리턴   
         캐시미스 : DB에 조회하여 origin url을 리턴   

**3. System Action Log 기록하는 기능 구현을 위해, HttpServeletRequest에 있는 Header 데이터를 기록하여 접근기록을 보며 데이터를 확인 후 저장**
   
      3-1. HttpSelveletReqeust 에서 원하는 Header 값을 필터링하는 HeaderUtil 구현하여 저장   

**4. 동적 쿼리를 적용하여 복잡한 쿼리 조건을 충족**  
   
      4-1. 회원가입을 진행하면서, HttpServeletRequest의 Header의 "client-id"(uuid)로 DB에 조회함. 이후 동일한 uuid가 저장된 Shortcode를 조회하여 생성된 MemberId를 Update함  
      4-2. uuid가 사용자마다 다르기 때문에 QueryDsl을 사용한 동적쿼리를 사용하여 기능 구현   
      4-3. uuid가 생성한 Shortcode가 대량이 존재하여 전체 조회가 발생하면 서버의 부화가 발생할 수 있으므로 1000개씩 청크를 나누어 조회 후 memberId 업데이트 진행   
    
**5. Batch 작업을 적용하여 일정 기능을 STEP 별로 기능 자동화를 적용**
   
      5-1. Spring batch를 사용, 6개월이 지난 DATA를 매일 자정 12시에 Scheduler를 구현   
      5-2. 삭제 해야하는 data가 대용량으로 존재할시, 서버의 부하로 리퀘스트에 지장을 줄 수 있기 때문에, 1000건씩 청크를 나누어 삭제 진행   

**6. Batch 작업과 멀티스레드를 적용하여 저장된 ShortUrl Entity와 SystemActionLogs Entity 싱크 맞추기**   
   
      6-1. 5초 마다 스케줄러가 작동하여 싱크 맞춤 작업 진행   
      6-2. 저장된 ShortUrl Entity에 memberId가 null이나 -1L 이 적용되어 있으면 SystemActionLogs Entity의 memberId에 -1L을 저장하고 그렇지 않으면 memberId를 그대로 저장   
      6-3. 6-2작업을 새로운 쓰레드에 할당하여 작업을 진행하여 서버의 부하를 줄임   


### To-BE

---


### Project 아키텍처

<div align="center">
    
![readys-link](https://github.com/user-attachments/assets/97a11075-1539-4ff9-85b3-0a88feacd272)

---

</div>

## 화면 구성

### 비회원 화면

<div align="center">

| URL 생성 전 화면   | URL 생성 후 화면  | 
|--------------|--------------|
| <img  src="https://github.com/user-attachments/assets/90631055-f033-4cc1-8bf8-944059294966" width="650"> | <img  src="https://github.com/user-attachments/assets/932fed0d-bba0-420b-aec8-0477bcb39d5e" width="650"> |

</div>


### 회원가입 로그인


<div align="center">

| 로그인 화면   |  회원 가입 화면  | 
|--------------|--------------|
| <img  src="https://github.com/user-attachments/assets/10ea3b32-579c-4d9f-8fc6-59051b643721" width="650"> | <img src="https://github.com/user-attachments/assets/c7da024d-277e-44d0-bcfb-0b55bc1903f7" width="650"> |

</div>

### 회원 화면

<div align="center">

|  Member가 생성한 shortUrl 확인 게시판  |
|--------------|
| <img src="https://github.com/user-attachments/assets/05b4e422-7660-4cf5-8caa-bec4cedee409" width="1300"> |

| Member가 생성한 shortUrl에 접근 기록 게시판 |
|--------------|
| <img src="https://github.com/user-attachments/assets/1d6f888b-a4f1-40b0-b073-48c54a765101" width="1300"> | 

</div>

---
