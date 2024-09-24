pipeline {
    agent any
    
    tools {
        jdk ("jdk21")
    }

    environment {
        SSH_CREDENTIALS_ID = "${env.SSH_CREDENTIALS_ID}"
        REMOTE_SERVER = "${env.REMOTE_SERVER}"
        FRONTEND_DIR = 'frontend'
        BACKEND_DIR = 'backend/starcast'
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'
    }

    stages {

        // 1. 브랜치 정보 출력
        stage('Print Branch Info') {
            steps {
                script {
                    def branch = sh(script: "git rev-parse --abbrev-ref HEAD", returnStdout: true).trim()
                    echo "Current branch: ${branch}"
                    env.BRANCH_NAME = branch  // 현재 브랜치 이름을 저장
                }
            }
        }

        // 2. 코드 체크아웃
        stage('Checkout Code') {
            steps {
                script {
                    git branch: "${env.BRANCH_NAME}", credentialsId: 'jenkins', url: 'https://lab.ssafy.com/s11-bigdata-dist-sub1/S11P21A609.git'
                }
            }
        }

        // 3. 디렉토리 리스트 출력
        stage('List Directory') {
            steps {
                sh 'ls -l'
                sh 'ls -l /var/jenkins_home/workspace/a609/frontend'
                sh 'ls -l /var/jenkins_home/workspace/a609/backend/starcast'
            }
        }

        // 4. Docker 이미지 빌드
        stage('Build Docker Images') {
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
                    
                    // 최근 커밋 메세지와 커밋 작성자 추출
                    env.COMMIT_MESSAGE = sh(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
                    env.COMMITTER_NAME = sh(script: 'git log -1 --pretty=format:"%an"', returnStdout: true).trim()
                }
            }
        }

        // 5. 원격 서버에 배포
        stage('Deploy to Remote Server') {
            steps {
                script {
                    sshagent([SSH_CREDENTIALS_ID]) {
                        sh '''
                        docker save frontend:latest | ssh -o StrictHostKeyChecking=no ${REMOTE_SERVER} 'docker load'
                        docker save backend:latest | ssh -o StrictHostKeyChecking=no ${REMOTE_SERVER} 'docker load'
                        scp -o StrictHostKeyChecking=no ${DOCKER_COMPOSE_FILE} ${REMOTE_SERVER}:/home/ubuntu
                        ssh -o StrictHostKeyChecking=no ${REMOTE_SERVER} << EOF
                            cd /home/ubuntu
                            docker-compose -f ${DOCKER_COMPOSE_FILE} up -d
EOF
                        '''
                    }
                }
            }
        }
    }

    post {
        success {
            script {
                mattermostSend (
                    color: 'good',
                    channel: 'JenkinsBuild',
                    endpoint: 'https://meeting.ssafy.com/hooks/e8wiuh31q3rqjjnwpyw5niprxo',
                    message: """\
빌드 성공 !! 당신은 유능한 개발자입니다 :castar_build_happy:
Build Number: ${env.BUILD_NUMBER}
Commit Message: ${env.COMMIT_MESSAGE}
Committer: ${env.COMMITTER_NAME}
Branch: ${env.BRANCH_NAME}
<${env.BUILD_URL}|Link to build>"""
                )
            }
        }
        failure {
            script {
                mattermostSend (
                    color: 'danger',
                    channel: 'JenkinsBuild',
                    endpoint: 'https://meeting.ssafy.com/hooks/e8wiuh31q3rqjjnwpyw5niprxo',
                    message: """\
빌드 실패 !! 힘내서 고쳐주세요 :castar_build_sad:
Build Number: ${env.BUILD_NUMBER}
Commit Message: ${env.COMMIT_MESSAGE}
Committer: ${env.COMMITTER_NAME}
Branch: ${env.BRANCH_NAME}
<${env.BUILD_URL}|Link to build>"""
                )
            }
        }
        always {
            cleanWs()
        }
    }
}
