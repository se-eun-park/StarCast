# 1. Node.js 기반의 slim 이미지를 사용
FROM node:20-slim

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. package.json과 lock 파일 복사
COPY package.json package-lock.json ./

# 4. 의존성 설치
RUN npm install

# 5. 소스 코드 복사
COPY . .

# 6. 빌드 (React 애플리케이션을 프로덕션용으로 빌드)
RUN npm run build

# 7. 3000번 포트 노출 (기본 React 개발 서버 포트)
EXPOSE 3000

# 8. 애플리케이션 실행 (개발 모드 또는 프로덕션 모드)
CMD ["npm", "start"]