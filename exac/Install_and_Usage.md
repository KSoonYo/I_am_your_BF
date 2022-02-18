
## ⚙️  Install and Usage
### 시스템 환경

I'm Your BF는 아래와 같은 환경에서 실행 중입니다.

- Cloud : AWS EC2
- OS : Ubuntu 20.04.3 LTS
- CPU 모델 : Intel(R) Xeon(R) CPU E5-2686 v4 @ 2.30GHz
- Total Memory : 16396056 kB
- 물리 cpu 개수 : 1 | cpu당 물리 코어 : 4 

### 시스템 구성

- docker : 20.10.12
- docker-compose : 1.29.2
- DB : mysql  8.0.28
- WebRTC : openvidu 2.20.0
- FrontEnd : Vue build 파일 
- BackEnd : Springboot 
- SSLIS : uwsgi  2.0.20 | django
- Modeling : blender  3.0

### Server Port

| 이름 | 포트 번호 |
| ---- | --------- |
|  web server(nginx)    |      80     |
|   springboot (tomcat)   |    8080       |
|   django(uwsgi)   |       8000      |
|  openvidu(http)    |    4442        |
|    openvidu(https)  |     4443       |
|   https   |    443         |
|    mysql   |    3306        |


#### Ubuntu 버전 업 및 기본 설치
```
sudo apt-get update
sudo apt-get install nodejs
sudo apt-get install npm
```
#### frontend 빌드 및 backend 무중단 배포
```
#frontend
npm run build

#backend
nohup java -jar bf.jar & 
```
#### HTTPS 키 발급
```
sudo apt-get install letsencrypt
# 만약 nginx를 사용중이면 중지
sudo systemctl stop nginx
# 인증서 발급
sudo letsencrypt certonly --standalone -d www제외한 도메인 이름

# 아래 키가 발급되는 경로를 /etc/nginx/sites-availabe/default 설정파일에 넣어줘야 함.
 ssl_certificate /etc/letsencrypt/live/도메인이름/fullchain.pem; 
 ssl_certificate_key /etc/letsencrypt/live/도메인이름/privkey.pem;
```
