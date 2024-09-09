# BankAccount
!이슈: branch-BankAccount에 코드가 있습니다.

## Installation
git clone https://github.com/0-Jhin/BankAccount.git

## Project information

-Java를 통해 은행 계좌 시스템을 구현합니다.

계좌 생성
=> 계좌번호 정규표현식, 계좌번호 중복 확인 후 생성

계좌 관리
=> 계좌 찾기 : 계좌번호 or 소유자 명(동명이인일 경우 모두 출력)으로 계좌 출력
=> 계좌 수정 : 비밀번호 수정만 가능
=> 계좌 삭제

계좌 조회,
=> 잔고 확인 : 소유주만 확인 가능
=> 입/출금 : 입출금 시, 거래내역 추가

모든 계좌 조회
=> 비밀번호 확인 후, 관리자일 경우 해당 은행의 계좌 목록 모두 조회

거래 내역 생성/조회
=> 잔고 변화가 있을 때, 거래 내역 생성


## ⚒️ Tech Stack

### API
API: 
계좌번호, 소유자 명, 비밀번호, 잔고(입금액, 출금액)
계좌 생성 - 계좌번호, 소유자명, 비밀번호, 계좌 잔고는 처음 생성시 0원으로 초기화
계좌 수정 - 비밀번호 수정
계좌 삭제 - Delete
입/출금 - 입금, 출금시 거래내역 생성
거래내역생성 - 입/출금 성공시
거래내역조회 - 조회
계좌검색 - 계좌번호, 이름 모두로 찾기 (request로 받은 값이 String 숫자면 계좌번호, else 이름), like검색
잔고조회 - 개인회원, 잔고내역 확인
계좌조회 - 계좌 리스트, 관리자
개인회원 권한 빌요한경우: /individual/
어드민 권한 필요한 경우: /admin/
개인 회원 식별: /{bankAccountId}

### Languages
- Java 17

### Frameworks
- Spring Boot 3.2.5
  - Spring Boot Starter Web
  - Spring Boot Starter WebSocket
  - Spring Boot Starter Validation
  - Spring Boot Starter OAuth2 Client
  - Spring Boot Starter Security
  - Spring Boot Starter Data JPA

### Security
- JWT (io.jsonwebtoken)
- Auth0 Java JWT

### Database
- H2 Databse

### Build Tools
- Gradle

### Utilities
- Lombok
- Spring Boot DevTools
  
### API Test/Documentation 
- Postman
