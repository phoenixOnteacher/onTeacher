# OnTeacher Deploy Guide  



## AWS EC2 ubuntu Linux 접속하기 

* AWS EC2 :  **Amazon Web Service Elastic  Compute Cloud**. 자체 서버를 구축하지 않고(On Premise Model), AWS가 운영하는 퍼블릭 클라우드(Public Cloud)에 접속하여 가상화된 서버를 이용하는 서비스. 

* AWS EC2에 접속하기 위해서는 SSH 클라이언트 도구를 이용해야 함 :  **putty**, **MobaXterm** 

* 본 가이드에서는 UI/UX 기능이 대폭 강화된 **MobaXTerm Home Edition**을 사용함. 

* 본 프로젝트에서는  ubuntu Linux와  Docker 컨테이너 기술을 이용하여 웹 애플리케이션을 배포함. 

  > 사용하는 ubuntu Linux  버전 :  **ubuntu 18.04.5 LTS**
  
  

##   MobaXtem을 이용한 ubuntu Linux 접속 절차 
1. MobaXterm 에 접속한 후 Personal Edtn을 다운로드 합니다. 

   > https://mobaxterm.mobatek.net/download.html

2. MobaXterm을 실행 후 sessions > Putty sessions에 오른쪽 클릭 후 New Session 을 선택합니다. 

3. Session settings 창에서  SSH를 선택합니다. 

4. Session settings > Basic SSH settings 창에 아래와 같은 정보를 입력합니다. 

| 구분             | 설명          |
| :--------------- | ------------- |
| Remote host      | 18.216.45.215 |
| Specify username | ubuntu        |
| Port             | 22            |

![Basic SSH Settings](docs_img/Basic%20SSH%20settings.jpg)

5. Advanced SSH setting 창에 **Use private key** 를 체크하고, 발급 받은 개인키(**ai02.pem**)를 연결합니다. 

   > 공개키 암호화 알고리즘에서 사용하는 개인에게만 할당된 키로서, 개인키를 발급하지 않았다면 콘솔에 접속할 수 없습니다. 개인키가 없다면 시스템 관리자에게 요청하시기 바랍니다. 

![ADV SSH Settings](docs_img/adv%20ssh%20settings.jpg)

6.  AWS EC2 ubuntu Linux에 정상적으로 접속한 후 기본적인 정보를 확인합니다. 

   ```bash 
   $ cat /etc/issue "ubuntu  버전 확인" 
   $ whoami "터미널 접속 계정 이름 확인"
   $ df -f "디스크 공간 확인"
   ```
   
   > Real AWS  서버(접속 IP : 18.216.45.215)는 실제 호스팅하는 서버이므로, 스킬 업을 위한 실습은 별도의  AWS 계정을 이용해서 진행해야 합니다.  

![Test Sever](md-images/Test%20Svr.jpg)



## Docker 설치 절차

1. ubuntu 리눅스 최신 기능 업데이트를 적용합니다. 

   ```bash 
   $ sudo apt update
   ```

2. Docker 설치에 필요한 유틸리티 프로그램을 설치합니다. 

   ```bash 
   $ sudo apt install apt-transport-https
   $ sudo apt install ca-certificates
   $ sudo apt install curl
   $ sudo apt install software-properties-common
   ```

3. curl을 이용해서 docker를 설치합니다. 

   ``` bash
   $ sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
   ```

4. ubuntu bionic  배포판 저장소(Repository)를 설정합니다. 

   ```bash
   $ sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"
   ```

5. ubuntu 리눅스 최선 기능 업데이트를 다시 한번 적용합니다. 

   ```bash 
   $ sudo apt update
   ```

6. Docker를 다운로드하고 설치합니다. 

   ```bash 
   $ sudo apt-cache policy docker-ce
   $ sudo su 								"Docker 설치를 위해서 root 권한으로 상승합니다."
   # apt install docker-ce 				"프롬프트가 #(root) 변경되었음."
   ```

   > 리눅스에서 프로그램 설치 , 서비스 설정 시 권한 관련 에러가 표시되면 권한을 root 로 상승시킨 후 시도해보면 많은 문제가 해결됨. 

7. Docker가  System Service에 등록되며, 아래 명령으로  Docker version을 확인 가능합니다. 

   ```bash
   $ sudo docker version
   ```

   ![Docker install](md-images/docker%20installed.jpg)



## Git 설치 절차 

1. git을 apt 커맨드로 설치합니다. 

   ``` bash 
   $ sudo apt install git 
   ```

2. git이 정상적으로 설치되었는지 version을 확인합니다. 

   ``` bash 
   $ sudo git --version 
   ```

   ![git version](md-images/git%20version.jpg)



## Oracle  설치 절차 

1.  Oracle DB엔진을 아래의 명령을 이용해서 설치합니다. 

   ```BASH
   $ sudo docker run -d --name oracle -p 1521:1521 -p 48080:8080 -v ~/0jes/oracle_db:/opt/oracle/oradata deepdiver/docker-oracle-xe-11g
   ```

   |  옵션  | 설명                                                         |
   | :----: | ------------------------------------------------------------ |
   |   -d   | Daemon으로 만들어줌. Background로 실행하도록 함.             |
   |   -p   | Port  Forwarding 을 수행함.                                  |
   | --name | Docker 컨테이너 이름을 지정해줍니다. 위 예제에서는 oracle이라고 명명했습니다. |

   ![Docker Oracle Install](md-images/oracle%20docker%20install.jpg)

2. 설치 및 테스트를 용이하기 위해서 sudo su 명령으로 root  권한을 가져옵니다. 

   ```bash
   $ sudo su 
   # (root 권한 상승 시 프롬프트가 #으로 변경됩니다.)
   ```

3. oracle 도커 이미지의 #container id를 확인합니다. 

   ```bash 
   # docker ps -a 
   ```

   ![docker oracle up](md-images/docker%20sql%20up.jpg)

   > STATUS		Up 27 minutes		27 분 동안 ORACLE  서비스가 백그라운드에서 실행하고 있다는 의미
   >
   > CONTAINER  ID		Docker Image를 호출하는 데 사용하는 유니크 값 	예시) 2aa62d90d2f6

4. oracle   도커 이미지가 중단되어 있다면 아래의 명령으로 실행하여 줍니다. 

   ```bash
   # docker start {CONTAINER ID}
   # docker start 2aa62d90d2f6 
   # docker stop 2aa62d90d2f6			"Docker image를 중단합니다."
   ```
