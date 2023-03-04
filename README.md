# 🔥MODAC (학습 내용 기록 및 공유 플랫폼)

![modac](/assets/images/modac.png)

## Table of Content

<!-- 1. [시연 영상](#0-시연-영상) -->
1. [프로젝트 진행 기간](#1-프로젝트-진행-기간)
1. [서비스 기획 배경](#2-서비스-기획-배경)
1. [서비스 개요](#3-서비스-개요)
1. [주요 서비스 화면](#4-주요-서비스-화면)
1. [기술 스택](#5-기술-스택)
1. [팀원 소개](#6-팀원-소개)
1. [협업](#7-협업)
1. [Backend Convention](#8-backend-convention)
1. [Frontend Convention](#9-frontend-convention)

<!-- <br>

## 시연 영상
📺 https://youtu.be/E_fv5XTlvFE  -->

<br>

## 프로젝트 진행 기간
📅 2023.01.09(월) ~ 2023.02.17(금) (30일간 진행) <br>
SSAFY 8기 2학기 공통 프로젝트


<br>

## 서비스 기획 배경

개발자가 되기 위해 '공부하는' 사람들은 공부한 내용을 정리할 때 어떤 서비스를 사용하고 있을까요? <br>
바로 블로그, 노션, 깃허브입니다. <br>
이 서비스들이 가지고 있는 장단점 때문에 공부한 내용을 정리할 때 두 개 이상의 서비스를 이용하는 분들이 꽤나 많습니다.<br>
이 과정을 한 번에 해결할 수는 없을까요? 저희 프로젝트는 이러한 고민에서 출발하게 되었습니다.

<br>

## 서비스 개요
저희 서비스가 추구하는 목표는 **학습 내용 기록 및 공유 플랫폼**입니다.

 ✅ Todo 리스트를 작성해 목표를 설정하고 학습할 수 있습니다.<br>
 ✅ 공부한 내용을 Markdown 파일로 작성할 수 있습니다.<br>
 ✅ Todo 항목 카테고리별 Markdown 템플릿을 제공해줍니다.<br>
 ✅ 편리하게 Github에 Commit 할 수 있습니다.<br>
 ✅ 공부한 기록을 Feed를 통해 모아 볼 수 있습니다.<br>

<br>

## 주요 서비스 화면

### 스터디룸 실시간 사용자 참여

![modac](/assets/images/gif/실시간참여.gif)

### todo 리스트 작성 및 정리하러 가기

![modac](/assets/images/gif/todo생성.gif)

### todo 카테고리별 마크다운 템플릿 제공

![modac](/assets/images/gif/템플릿.gif)

### 마크다운 작성

![modac](/assets/images/gif/게시글작성복사다운.gif)

### 깃허브 원격저장소 커밋

![modac](/assets/images/gif/깃허브.gif)

### 친구 기반 피드

![modac](/assets/images/gif/피드.gif)

<br>

## 기술 스택

### FrontEnd

| <div align="center"><img src="/assets/images/html.svg" alt="HTML5" width="50px" height="50px" /> </div> | <div align="center"><img src="/assets/images/css.svg" alt="CSS3" width="50px" height="50px" /></div> | <div align="center"><img src="/assets/images/js.png" alt="JavaScript" width="50px" height="50px" /></div> |
| :-----------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------: |
|                                      &nbsp;&nbsp;HTML5&nbsp;&nbsp;                                      |                         &nbsp;&nbsp;&nbsp;&nbsp;CSS3&nbsp;&nbsp;&nbsp;&nbsp;                         |                                                JavaScript                                                 |

|<div align="center"><img src="/assets/images/vue.png" alt="vue" width="50px" height="50px" /> </div>      | <div align="center"><img src="/assets/images/axios.png" alt="axios" width="50px" height="50px" /> </div>   | 
| :------------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------------: | 
|                  &nbsp;&nbsp;&nbsp;&nbsp;Vue&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                               |                                               &nbsp;&nbsp;&nbsp;&nbsp;          AXIOS                                  



### BackEnd

| <div align="center"><img src="/assets/images/java.svg" alt="java" width="50px" height="50px" /> </div> | <div align="center"><img src="/assets/images/springboot.png" alt="springboot" width="100px" height="50px" /> </div> | <div align="center"><img src="/assets/images/springsecurity.png" alt="springsecurity" width="100px" height="50px" /></div> | <div align="center"><img src="/assets/images/gradle.png" alt="gradle" width="50px" height="50px" /></div> | <div align="center"><img src="/assets/images/hibernate.png" alt="hibernate" width="50px" height="50px" /></div> |
| :----------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------: |
|                                                  Java                                                  |                                                     Spring-Boot                                                     |                                                      Spring-Security                                                       |                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Gradle&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                     |                                  &nbsp;&nbsp;&nbsp;Hibernate&nbsp;&nbsp;&nbsp;                                  |

| <div align="center"><img src="/assets/images/mysql.svg" alt="mysql" width="70px" height="50px" /> </div> | <div align="center"><img src="/assets/images/postman.svg" alt="postman" width="50px" height="50px" /></div> | <div align="center"><img src="/assets/images/websocket.svg" alt="websocket" width="50px" height="50px" /></div> | <div align="center"><img src="/assets/images/redis.png" alt="redis" width="70px" height="50px" /></div> |
| :--------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------: |
|                                                  Mysql                                                   |                                                   Postman                                                   |                                                    Websocket                                                    |                                                  Redis                                                  |

### DevOps

| <div align="center"><img src="/assets/images/docker.png" alt="docker" width="50px" height="50px" /> </div> | <div align="center"><img src="/assets/images/nginx.png" alt="nginx" width="50px" height="50px" /></div> | <div align="center"><img src="/assets/images/ec2.png" alt="EC2" width="50px" height="50px" /></div> | <div align="center"><img src="/assets/images/s3.png" alt="S3" width="50px" height="50px" /></div> |
| :--------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------: |
|                                                   Docker                                                   |                                                  NginX                                                  |                                               AWS EC2                                               |                                              AWS S3                                               |

<br>

## 팀원 소개

|이름||담당|
|------|---|---|
|이민수|팀장|Backend 개발 + Infra + 협업툴 관리|
|정다은|팀원|Backend 개발총괄 + API 설계 및 관리|
|김동현|팀원|Backend 개발 + DB 설계 + 발표|
|김성수|팀원|Frontend 개발총괄 + 아키텍쳐 설계|
|윤지영|팀원|Frontend 개발 + 디자인|
|권용재|팀원|Frontend 개발 + 일정관리|

<br>

## 협업

### Jira Convention

Jira는 개발 이슈 트랙킹 용도로 사용합니다.

- 계층

  1. ![epic](/assets/images/epic.svg) (epic)
  2. ![story](/assets/images/story.svg), ![task](/assets/images/task.svg) (BE)

- 이름 규칙

  - Epic은 대분류입니다. Front, Back 작업의 부모 항목입니다.
  - Front, Back 작업은 각각 프론트엔드 작업과 백엔드 작업 세부 이름입니다. (한글로 상세히 작성해 주세요)
  - 예시) ![story](/assets/images/story.svg) 유저 로그인 레이아웃 제작- Front

- 이슈

  - 담당자, 우선순위, story point, Epic Link와 함께 이슈를 생성하세요
  - Backlog 탭에서 이슈를 미리 만들어 놓고, 매주 월요일 아침 회의를 통해 이슈를 개인별로 할당합니다. 할당한 이슈를 스프린트에 옮겨놓으세요
  - 해당 이슈를 완료하거나 작업을 시작했다면, Active sprints 탭에서 workflow (Todo, In-progress, Done)을 변경해주세요

- Burndown Chart
  - 번다운 차트는 스프린트별 시간당 잔여 Story-point 를 추적합니다
  - 주차별 스프린트를 시작하기 전에 모든 이슈를 생성하고 할당해야 올바른 번다운 차트를 생성할 수 있습니다

<br>

### Git Flow Convention

디렉터리 구조는 **frontend** 와 **backend** 로 구분됩니다. 각 개발자들은 해당 디렉터리로 이동한다음 작업하면 됩니다

```
├── front
└── back
```

Branch Types

 🌳 master: 제품으로 출시될 수 있는 브랜치<br>
 🌳 develop: 제품 출시 전 기능 개발 후 버그 수정 브랜치<br>
 🌳 front: Frontend 개발 브랜치<br>
 🌳 Back: Backend 개발 브랜치<br>
 🌳 feature/기능명-back: Backend 기능 개발 브랜치<br>
 🌳 feature/기능명-front: Frontend 기능 개발 브랜치<br>


<br>

![epic](/assets/images/branch.png)

 1️⃣ origin/back 브랜치에서 작업 브랜치 생성<br>
 2️⃣ 작업 브랜치에서 작업<br>
 3️⃣ 작업 브랜치에서 소스를 커밋<br>
 4️⃣ 작업 브랜치를 origin에 push<br>
 5️⃣ Gitlab에서 작업 브랜치를 develop에 합치도록 Merge Request 생성<br>
 6️⃣ 동료에게 승인받고, merge<br>

<!-- [ref](https://techblog.woowahan.com/2553/) -->

<br>

### Commit Message Convention & Template

```
# I 키 누르면 수정 시작
# esc -> :wq! 저장 및 종료
# esc -> :q! 저장 없이 종료
# (#은 주석으로 커밋 메시지에 영향X)
# ======================= COMMIT START ========================
# [제목] - 최대 50글자   ex) Feat: Add Key mapping  


# [본문] - 최대 72글자 (생략 가능 / 최대한 자세히 작성)  


# ======================== COMMIT END ========================= 
#   <타입> 리스트  
#   Feat        : 새로운 기능 추가   
#   Fix         : 버그 수정
#   Design      : CSS 등 사용자 UI 디자인 변경  
#   !BREAKING CHANGE : 커타란 API 변경
#   Style       : 코드 포맷 변경, 세미 콜론 누락 (비즈니스 로직 변경 X)  
#   Refactor    : 프로덕션 코드 리팩토링
#   Comment     : 필요한 주석 추가 및 변경
#   Docs        : 문서 수정 (문서 추가, 수정, 삭제, README)  
#   Test        : 테스트 추가, 테스트 리팩토링 (비즈니스 로직 변경 X)  
#   Chore       : 빌드 태스크 업데이트, 패키지 매니저 설정할 경우 (비즈니스 로직 변경 X)
#   Rename      : 파일 혹은 폴더명을 수정, 옮기는 작업만
#   Remove      : 사용하지 않는 파일 혹은 폴더를 삭제하는 경우
#   Init        : 초기 생성  
#  
#   <주의사항>
#   제목 첫 글자를 대문자로  
#   제목은 명령문으로  
#   제목 끝에 마침표(.) 금지  
#   제목과 본문을 한 줄 띄워 분리하기  
#   본문은 "어떻게" 보다 "무엇을", "왜"를 설명한다.  
#   본문에 여러줄의 메시지를 작성할 땐 "-"로 구분  
```

<br>

## Backend Convention

- Style Guide
  - [캠퍼스 핵데이 Java 코딩 컨벤션](https://naver.github.io/hackday-conventions-java/)
- Project Structure

```
// modac-backend 디렉터리 안에서
.
└── main                                   // java src 디렉터리
    ├── java 
    │   └── com
    │       └── a608
    │           └── modac                   // REST API 디렉터리
    │               ├── controller
    │               ├── repository
    │               ├── service
    │               ├── config              
    │               ├── scheduler          
    │               ├── security
    │               └── config                  
    │                   ├── article            
    │                   ├── category
    │                   ├── comment
    │                   ├── follow
    │                   ├── notification
    │                   ├── participant
    │                   ├── room
    │                   ├── todo
    │                   ├── user
    │                   └── chat
    └── resources                           // 웹 리소스 디렉터리
        ├── database
        │    ├── modac-erd.mwb
        │    └── modac-erd.mwb.bak 
        │
        └── application.properties

```

## Frontend Convention

- Project Structure

```
// frontend 디렉터리 안에서
.
├── public          // 소스 코드에서 참조되지 않는 에셋
└── src             // 소스 코드
   ├── api             // 서버와 통신에 쓰이는 api
   ├── assets          // 소스 코드에서 참조되는 에셋
   ├── components      // 컴포넌트 디렉터리
   ├── domains
   │   ├── Article
   │   ├── Error
   │   ├── Feed
   │   ├── MyPage
   │   ├── Navbar
   │   ├── Room
   │   └── User
   ├── router
   ├── stores          
   └── views           // 페이지 디렉터리
```

<br>
