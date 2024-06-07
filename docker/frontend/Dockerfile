FROM jitesoft/node-yarn:latest-slim
WORKDIR /app
COPY ./frontend/ .
RUN yarn install
RUN yarn global add serve
CMD yarn run build; serve -s dist
