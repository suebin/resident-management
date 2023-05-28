# 🗂 증명서 발급 시스템
- 주민 관리를 위한 문서(가족관계증명서, 주민등록등본, 출생신고서, 사망신고서)를 발급할 수 있는 웹 페이지입니다.

<br>

# 🔗 URL
`test 로그인 ID : admin, Password : admin (Github AOuth 2.0는 localhost 에서만 동작)`
<br>

- Azure IaaS 환경 배포 
  - http://20.214.167.213/
- Azure PaaS (Web App) 환경 배포 
  - http://app-resident-management-230524123005.azurewebsites.net/

<br>

# 🛠 기술 스택
- `Front-End` : HTML (Thymeleaf), CSS, Javascript
- `Back-End` : Java, Spring Data JPA, Querydsl, Spring Security
- `Database` : MySQL
- `Cloud Services` : Microsoft Azure
- `Build` : Maven
- `Tool` : IntelliJ IDEA, Github

<br>

# 👩‍💻 개발 인원
- 1명 (개인)

<br>

# ⏰ 개발 기간
- 2023.05.17 - 2023.05.23

<br>

# 🗄 ERD 
- 초기 ERD
![ERD](https://github.com/suebin/resident-management/assets/97905221/24001a84-7592-4cf8-8e06-0eb6a62f141c)

- 최종 ERD 
<img width="1268" alt="스크린샷 2023-05-28 오후 8 55 50" src="https://github.com/suebin/resident-management/assets/97905221/19cd97a8-6601-48b8-9742-cb5e244a3a23">

<br>

# 🛎 주요 서비스
- `ID, PWD 로그인` / `Github AOuth 2.0 로그인` (Github AOuth 2.0는 localhost 에서만 동작) 
- `주민 목록` (Pagenation을 이용한 페이징 기능 구현)
- `증명서 발급 및 조회`
  - 가족관계증명서
  - 주민등록등본
  - 출생신고서 (있는 경우에만 출력)
  - 사망신고서 (있는 경우에만 출력)
- `증명서 발급 목록` (Pagenation을 이용한 페이징 기능 구현)
- `주민 삭제` (해당 주민과 관련한 모든 정보 삭제)
- `한/영 locale 일부 지원`

<br>

# 📋 API 명세서
<img width="725" alt="스크린샷 2023-05-28 오후 8 58 56" src="https://github.com/suebin/resident-management/assets/97905221/002c831e-45a0-41cb-add4-85ffeb050607">
<img width="728" alt="스크린샷 2023-05-28 오후 9 01 13" src="https://github.com/suebin/resident-management/assets/97905221/69f8ab31-4990-4600-8407-d948b72ee317">

- `jackson library를 이용해 JSON 으로 요청 및 응답`

<br>

# 🖥 UI
<img width="1469" alt="스크린샷 2023-05-28 오후 8 45 32" src="https://github.com/suebin/resident-management/assets/97905221/bfa42e10-d4b2-4adb-92c1-00a490fcde7e">

<img width="1470" alt="스크린샷 2023-05-28 오후 8 45 52" src="https://github.com/suebin/resident-management/assets/97905221/84ae2a08-7d5a-4032-aeb6-b912de33e28e">

<img width="1470" alt="스크린샷 2023-05-28 오후 8 46 54" src="https://github.com/suebin/resident-management/assets/97905221/1cd0b53d-a739-43f5-91e8-9225cd4af171">

<img width="1467" alt="스크린샷 2023-05-28 오후 8 49 07" src="https://github.com/suebin/resident-management/assets/97905221/5f027ca4-c4c0-49af-85cd-f8c6da6df597">

<img width="1470" alt="스크린샷 2023-05-28 오후 8 49 39" src="https://github.com/suebin/resident-management/assets/97905221/4f8d2db2-98a0-4429-8c1a-3a158c691efa">

<img width="1470" alt="스크린샷 2023-05-28 오후 8 49 53" src="https://github.com/suebin/resident-management/assets/97905221/b0cc65dd-d463-4e9b-b437-06ed15de5c97">

