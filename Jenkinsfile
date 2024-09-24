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
        stage('Print Branch Info') {
            steps {
                script {
                    echo "Current GIT_BRANCH: ${env.GIT_BRANCH}"
                    def branch = sh(script: "git rev-parse --abbrev-ref HEAD", returnStdout: true).trim()
                    echo "Current branch: ${branch}"
                    echo "REMOTE_SERVER: ${env.REMOTE_SERVER}"
                    echo "BRANCH_NAME: ${env.BRANCH_NAME}"
                }
            }
        }

        stage('Checkout Code') {
            when {
                anyOf {
                    expression { env.GIT_BRANCH == 'origin/release' }
                    expression { env.GIT_BRANCH == 'origin/master' }
                }
            }
            steps {
                script {
                    // 브랜치 동기화
                    sh 'git fetch --all'
                    sh 'git reset --hard origin/${env.GIT_BRANCH}' // 브랜치 리셋 후 최신 상태로 맞춤
                    
                    if (env.GIT_BRANCH == 'origin/release') {
                        git branch: 'release', credentialsId: 'jenkins', url: 'https://lab.ssafy.com/s11-bigdata-dist-sub1/S11P21A609.git'
                    } else if (env.GIT_BRANCH == 'origin/master') {
                        git branch: 'master', credentialsId: 'jenkins', url: 'https://lab.ssafy.com/s11-bigdata-dist-sub1/S11P21A609.git'
                    }
                }
            }
        }

        stage('Build Docker Images') {
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

                    // 백엔드 빌드
                    dir('/var/jenkins_home/workspace/a609/backend/starcast') {
                        sh 'chmod +x gradlew'
                        sh './gradlew build'
                    }

                    sh 'ls -l /var/jenkins_home/workspace/a609/backend/starcast/build/libs/'

                    // Docker 이미지 빌드
                    sh 'docker build -t backend:latest /var/jenkins_home/workspace/a609/backend/starcast'

                    // 커밋 정보 다시 체크
                    sh 'git reset --hard origin/${env.GIT_BRANCH}' // 추가된 부분
                    env.COMMIT_MESSAGE = sh(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
                    env.COMMITTER_NAME = sh(script: 'git log -1 --pretty=format:"%an"', returnStdout: true).trim()
                }
            }
        }

        stage('Deploy to Remote Server') {
            when {
                anyOf {
                    expression { env.GIT_BRANCH == 'origin/release' }
                    expression { env.GIT_BRANCH == 'origin/master' }
                }
            }
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
Branch: ${env.GIT_BRANCH}
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
빌드 실패 !! 당신이 범인이었구나? :castar_build_sad:
Build Number: ${env.BUILD_NUMBER}
Commit Message: ${env.COMMIT_MESSAGE}
Committer: ${env.COMMITTER_NAME}
Branch: ${env.GIT_BRANCH}
<${env.BUILD_URL}|Link to build>"""
                )
            }
        }
        always {
            cleanWs()
        }
    }
}
