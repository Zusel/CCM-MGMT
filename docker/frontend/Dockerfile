FROM node:slim
WORKDIR /app
COPY ./frontend/ .
RUN npm install
RUN npm install -g serve
RUN npm run build
CMD ["serve","-s", "dist" ]
