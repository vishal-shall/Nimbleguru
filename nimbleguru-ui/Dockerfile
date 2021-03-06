#FROM nginx:1.17.1-alpine
#COPY /dist/nimbleguru-ui /usr/share/nginx/html

#FROM node:10-alpine as build-step
#
#RUN mkdir -p /app
#
#WORKDIR /app
#
#COPY package.json /app
#
#RUN npm install
#
#COPY . /app
#
#RUN npm run build --prod
#
## Stage 2
#
#FROM nginx:1.17.1-alpine
#
#COPY --from=build-step /app /usr/share/nginx/html

FROM node:12.2.0
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json /app/package.json
RUN npm install

COPY . /app
EXPOSE 4200
CMD ng serve --host 0.0.0.0

