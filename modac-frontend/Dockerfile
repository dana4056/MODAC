FROM nginx:stable-alpine

# root 에 app 폴더를 생성
RUN mkdir  /app

# work dir 고정
WORKDIR /app

# work dir 에 build 폴더 생성 /app/build
RUN mkdir  ./build

# host pc의 현재경로의 build 폴더를 workdir 의 build 폴더로 복사
ADD ./dist ./build

RUN rm /etc/nginx/conf.d/default.conf

COPY ./nginx.conf /etc/nginx/conf.d

# 5173포트 오픈
EXPOSE 5173

CMD ["nginx", "-g", "daemon off;"]
