# shorten-url

## 목차
- [개발 환경](#개발-환경)
- [빌드 및 실행하기](#빌드-및-실행하기)
- [기능 요구사항](#기능-요구사항)


## 개발 환경
- 기본 환경
    - IDE: IntelliJ IDEA Ultimate
    - OS: Mac OS X(Apple CPU M1)
    - GIT
- Server
    - Amazon Java11 correto
    - Spring Boot 2.6.1
    - Maven
    - Junit5

## 빌드 및 실행하기

```
[Java 설치]
$ sudo yum install java-11-amazon-corretto

[깃 설치]
$ sudo yum install git -y

$ git clone https://github.com/WooSungHwan/shorten-url.git
$ cd shorten-url

$ ./mvnw clean verify
$ ./mvnw package
$ java -jar target/shorten-url-0.0.1-SNAPSHOT.jar

(백그라운드 실행시)
$ nohup java -jar target/shorten-url-0.0.1-SNAPSHOT.jar
```

## 기능 요구사항
- URL 입력폼 제공 및 결과 출력
- URL Shortening Key는 8 Character 이내로 생성되어야 합니다.
  - 이슈존재 : origin url을 저장하는 Map의 Key를 Long 으로 설정하였음.
  - Long MAX_VALUE는 대략 9경, 하지만 Base 62 인코딩으로 표현할 수 있는 범위는 218조(62의 8승) 까지 가능.
  - 이 부분에서 Long 값의 범위가 Base 62 인코딩을 상회하는 범위이므로 Base 62 인코딩의 8자리의 마지막 값인 218340105584895 을 넘는 갯수를 보관하기는 어려움.
  - 해당 부분은 비즈니스 상 shorten url 기능이 Base 62 인코딩의 범위를 넘어서지 않을 것이라 판단하여 간단한 예외처리만 해두었음.
- 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답해야 합니다.
  - Base 62 인코딩을 활용하여 동일한 origin path 동일한 shorten key 응답.
- 동일한 URL에 대한 요청 수 정보를 가져야 한다(화면 제공 필수 아님)
  - 간단한 화면 제공(Jquery Ajax, mustache)
- Shortening된 URL을 요청받으면 원래 URL로 리다이렉트 합니다.
- Database 사용은 필수 아님
  - 디비 사용 안함.
