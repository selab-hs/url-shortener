# ✂️ url-shortner


> **긴 URL 을 짧은 URL로 변환하는 서비스 입니다.**   
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
[4. 아키텍처](#project-아키텍처)

[5. 화면 구성](#화면-구성)   
&nbsp;&nbsp;&nbsp;&nbsp;[5-1. 비회원](#비회원-화면)   
&nbsp;&nbsp;&nbsp;&nbsp;[5-2. 회원 가입/로그인](#회원-가입/로그인)   
&nbsp;&nbsp;&nbsp;&nbsp;[5-3. 회원](#회원-화면)
  

---


### 프로젝트 소개

<div>
    
**사용하기 긴 url을 짧은 길이로 url을 사용하기 위해 시작된 프로젝트입니다.**  
프로젝트는 간편한 서비스를 통해 대량의 요청을 견디는 아키텍커를 적용하여 안정적인 서비스를 제공합니다.   
프로젝트의 기반은 "가상면접 사례로 배우는 대규모 시스템 설계 기초" 8장의 기반으로 추가적인 기능을 더해 진행된 프로젝트입니다.
프로젝트를 통해 대량의 트래픽을 감당이 가능한 서버를 구축하는 솔루션을 공부하고 적용하여 익히기 위한 토이 프로젝트입니다. 

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

### 주요 기능 소개

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


### 회원 가입/로그인


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
