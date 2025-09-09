FROM node:20-alpine

#workdir /app
WORKDIR /app

#copy the package.json  files
COPY package*.json .

#install dependencies
RUN npm install 

#copy the rest of the files
COPY . .

#expose the port
EXPOSE 3000

#start the app
CMD ["npm", "start"]
