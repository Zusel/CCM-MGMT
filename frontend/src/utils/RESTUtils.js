import axios from "axios";

const BASE_URL = "http://localhost:8080";

const RESTUtils = {
  sendRequest(type, path, body) {
    switch (type) {
      case 'GET':
        return axios.get(BASE_URL + path, {})
    }
  },
  sendGetRequest(path) {
    return this.sendRequest('GET', path, null);
  }
};


export default RESTUtils;
