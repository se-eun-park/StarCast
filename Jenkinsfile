pipeline {
		// Jenkins가 실행될 에이전트를 정의. 
		// 'any'는 어떤 에이전트에서나 파이프라인이 실행될 수 있음을 의미.
		// 현재 특화 PJT에서 사용하는 노드나 파이프라인은 한 개라서 따로 설정할 필요 없음
    agent any  
		
    tools {
      jdk ("jdk21")
    }

		// Jenkins 환경 변수 (.env 안 쓰고 직접 넣어줘도 됨)
    environment {
		    // Jenkins에 저장된 SSH 인증 정보를 사용하여 원격 서버에 연결.
		    // credentials에서 SSH USERNAME
        SSH_CREDENTIALS_ID = "${env.SSH_CREDENTIALS_ID}"
        
        // 배포할 원격 서버의 SSH 주소.
        // ubuntu@j11a609.p.ssafy.io
        REMOTE_SERVER = "${env.REMOTE_SERVER}"
        
        // 프론트엔드 디렉토리 경로 (젠킨스 파이프라인에서 사용할)
        FRONTEND_DIR = 'frontend'
        
        // 백엔드 디렉토리 경로 (젠킨스 파이프라인에서 사용할)
        BACKEND_DIR = 'backend/starcast'
        
        // Docker Compose 파일 경로 (얘는 깃랩 루트 디렉토리에 있을 거)
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'  
    }

    stages {

        // 1. 현재 빌드가 진행 중인 브랜치 정보 출력
        // 없어도 되지만, branch값이 null이라는 에러가 뜬다면 
        // 이 stage 넣어서 출력되는 값 확인해서 변수로 사용
        // 그냥 devleop, master 넣으면 안 될 수도 있다
        stage('Print Branch Info') {
            steps {
                script {
                    // 현재 BRANCH_NAME 및 GIT_BRANCH 정보를 출력하고, git 명령어로 현재 브랜치를 확인.
                    echo "Current GIT_BRANCH: ${env.GIT_BRANCH}"
                    // git 명령어로 현재 브랜치 확인
                    def branch = sh(script: "git rev-parse --abbrev-ref HEAD", returnStdout: true).trim()  
                    echo "Current branch: ${branch}"
                    echo "REMOTE_SERVER: ${env.REMOTE_SERVER}"
                    echo "BRANCH_NAME: ${env.BRANCH_NAME}"
                }
            }
        }

        // 2. 코드 체크아웃
        stage('Checkout Code') {
		        // when 조건으로 이런 상황일 때 진행하겠다고 정의
            when {
                anyOf {
		                // 브랜치가 develop일 경우
                    expression { env.GIT_BRANCH == 'origin/release' } 
                    // 브랜치가 master일 경우 
                    expression { env.GIT_BRANCH == 'origin/master' }  
                }
            } 
            steps {
                script {
                    // 현재 브랜치가 develop이면 
                    // develop 브랜치에서 코드를 가져오고 
                    // master이면 master 브랜치에서 코드를 가져옴.
                    if (env.BRANCH_NAME == 'release') {
                        git branch: 'release', credentialsId: 'jenkins', url: 'https://lab.ssafy.com/s11-bigdata-dist-sub1/S11P21A609.git'
                    } else if (env.BRANCH_NAME == 'master') {
                        git branch: 'master', credentialsId: 'jenkins', url: 'https://lab.ssafy.com/s11-bigdata-dist-sub1/S11P21A609.git'
                    }
                }
            }
        }

        // 3. 디렉토리 리스트 출력
        // 이것도 없어도 됨
        // 4. Docker 이미지 빌드를 위한 Jenkins 워크스페이스 확인임
        // -> 젠킨스 컨테이너 안!!!
        // a406은 내 파이프라인 이름
        // backend, vue-frontend는 환경변수에서 선언했던 이름
        // 깃랩에서 클론해와서 저 위치에 저장됨!!
        stage('List Directory') {
            steps {
                sh 'ls -l'  // Jenkins 워크스페이스의 현재 디렉토리 목록을 출력
                sh 'ls -l /var/jenkins_home/workspace/a609/frontend'
                // 백엔드 디렉토리가 depth가 하나 더 깊어서 이렇게 하는지 맞는지 모르겠지만 일단 함,,,,
                sh 'ls -l /var/jenkins_home/workspace/a609/backend/starcast'
            }
        }

        // 4. Docker 이미지 빌드
        stage('Build Docker Images') {
		        // 브랜치가 release일 경우
		        // 브랜치가 master일 경우
            when {
                anyOf {
                    expression { env.GIT_BRANCH == 'origin/release' }  
                    expression { env.GIT_BRANCH == 'origin/master' }
                }
            }
            steps {
                script {
                              // 프론트엔드 Docker 이미지 빌드
                              sh 'docker build -t frontend:latest /var/jenkins_home/workspace/a609/frontend'

                              // 백엔드 디렉토리로 이동하여 Gradle 빌드 실행
                              dir('/var/jenkins_home/workspace/a609/backend/starcast') {
                                  sh 'chmod +x gradlew'
                                  sh './gradlew build'
                              }

                              sh 'ls -l /var/jenkins_home/workspace/a609/backend/starcast/build/libs/'

                              // Docker 이미지 빌드
                              sh 'docker build -t backend:latest /var/jenkins_home/workspace/a609/backend/starcast'

                }
            }
        }

        // 5. 원격 서버에 배포
        stage('Deploy to Remote Server') {
		        // 브랜치가 release일 경우
		        // 브랜치가 master일 경우
            when {
                anyOf {
                    expression { env.GIT_BRANCH == 'origin/release' }  
                    expression { env.GIT_BRANCH == 'origin/master' }  
                }
            }
            steps {
                script {
                    // SSH를 통해 원격 서버에 접속하고, 
                    // Docker 이미지를 전송 및 로드하여 실행.
                    // SSH 인증을 통해 원격 서버에 연결
                    sshagent([SSH_CREDENTIALS_ID]) {  
                        sh '''
                        # 프론트엔드 이미지를 원격 서버로 전송하고 로드
                        docker save frontend:latest | ssh -o StrictHostKeyChecking=no ${REMOTE_SERVER} 'docker load'
                        # 백엔드 이미지를 원격 서버로 전송하고 로드
                        docker save backend:latest | ssh -o StrictHostKeyChecking=no ${REMOTE_SERVER} 'docker load'
												
												# Docker Compose 파일을 원격 서버로 전송
                        scp -o StrictHostKeyChecking=no ${DOCKER_COMPOSE_FILE} ${REMOTE_SERVER}:/home/ubuntu  

												# 원격 서버에서 Docker Compose로 컨테이너 실행
                        ssh -o StrictHostKeyChecking=no ${REMOTE_SERVER} << EOF
                            cd /home/ubuntu
                            docker-compose -f ${DOCKER_COMPOSE_FILE} up -d  
EOF
                        '''
                    // EOF: command not found 오류일 때는 빌드 실패함 
                    // -> EOF 인식이 안 됨
                    // 맞게 사용했다면 대부분 띄어쓰기(빈칸) 오류임
                    
                    // 마지막을 << EOF로 끝내면
                    // -bash: line 4: warning: here-document at line 3 delimited by end-of-file (wanted EOF')
                    // 빌드는 성공하고 컨테이너도 돌아가는데 경고 문구가 뜸
                    // EOF 종료를 인식하지 못했다는데 잘 모르겠음
                    
                    // 해결 방법: 마지막 EOF를 걍 왼쪽 끝에 붙여버리기 
                    }
                }
            }
        }
    }

    // 6. 작업 완료 후 워크스페이스 정리
    post {
        success {
            script {
                mattermostSend (
                    color: 'good',
                    // 본인 채널명
                    channel: 'JenkinsBuild',
                    // 본인 webhook
                    endpoint: 'https://meeting.ssafy.com/hooks/e8wiuh31q3rqjjnwpyw5niprxo',
                    message: """\
빌드 성공 !! 당신은 유능한 개발자입니다 :castar_build_happy:
Build Number: ${env.BUILD_NUMBER}
Commit Message: ${env.COMMIT_MESSAGE}
Committer: ${env.COMMITTER_NAME}
Branch: ${env.GIT_BRANCH}
<${origin/env.BUILD_URL}|Link to build>"""
                )
            }
        }
        failure {
            script {
                mattermostSend (
                    color: 'danger',
                    // 본인 채널명
                    channel: 'JenkinsBuild',
                    // 본인 webhook
                    endpoint: 'https://meeting.ssafy.com/hooks/e8wiuh31q3rqjjnwpyw5niprxo',
                    message: """\
빌드 실패 !! 힘내서 고쳐주세요 :castar_build_sad:
Build Number: ${env.BUILD_NUMBER}
Commit Message: ${env.COMMIT_MESSAGE}
Committer: ${env.COMMITTER_NAME}
Branch: ${env.GIT_BRANCH}
<${origin/env.BUILD_URL}|Link to build>"""
                )
            }
        }
        always {
            cleanWs()  // 파이프라인 실행 후 워크스페이스 정리 (불필요한 파일 삭제)
        }
    }
}
