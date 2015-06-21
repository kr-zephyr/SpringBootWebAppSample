# SpringBootWebAppSample
Web Application을 위한 Spring Boot Sample 프로젝트 입니다.

이 샘플 소스는 mysql 및 http 통신을 위한 기본적인 기능들이 구현되어 있습니다.
스프링 부트를 이용한 웹 어플리케이션 구현을 공부하시거나 빠르게 마이크로 사이트를 구현하시고자 하실 때 도움이 될 수 있습니다.

이 샘플 소스에는 아래와 같은 기능이 포함되어 있습니다.

- Maven build 및 pom 설정
- Profile을 이용한 Maven build
- MySql을 사용하기 위한 MyBatis 설정
- Http 통신을 위한 HttpClient 설정
- logback 설정
- java config 기반
- embedded Tomcat을 통한 JSP 및 Servlet, jstl 지원


# 구동 방법
mvn clean package

cd target

java -jar SpringBootWebAppSample-0.1.0.war

# 주의사항 및 공지
* 본 소스는 급하게 마이크로 서버가 필요해 반나절만에 구성한 것이라 어떤 폭탄이 숨겨져 있을지 모릅니다;;;
* 시간되는데로 조금씩 보강하겠습니다.
* 이 프로젝트는 여러가지 구현 방법들을 담을 예정입니다. 다만, 이러한 여러가지 방법들이 반드시 정답은 아닙니다.
* 본 소스는 마음대로 Fork하셔서 재배포하셔도 되구요... Contributor로 직접 수정해 주셔도 좋습니다.
