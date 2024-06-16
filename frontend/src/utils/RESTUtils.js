import axios from "axios";

const VITE_BACKEND_URL = import.meta.env.VITE_BACKEND_URL;
const VITE_BACKEND_PORT = import.meta.env.VITE_BACKEND_PORT;
const BASE_URL = VITE_BACKEND_URL + ":" + VITE_BACKEND_PORT;

const RESTUtils = {
  sendRequest(type, path, body) {
    let options = {}
    if (body !== null) {
      options = {
        data: body
      }
    }
    switch (type) {
      case 'GET':
        return axios.get(BASE_URL + path, options)
      case 'DELETE':
        return axios.delete(BASE_URL + path, options)
      case 'POST':
        return axios.post(BASE_URL + path, body, options)
      case 'PUT':
        return axios.put(BASE_URL + path, options);
    }
  },
  sendGetRequest(path) {
    return this.sendRequest('GET', path, null)
      .catch(error =>
        this.$root.$refs.vtoast.show({
          message: 'Fehler: ' + error,
          color: 'failed'
        })
      );
  },
  sendDeleteRequest(path, body) {
    return this.sendRequest('DELETE', path, body)
      .catch(error =>
        this.$root.$refs.vtoast.show({
          message: 'Fehler: ' + error,
          color: 'failed'
        })
      );
  },
  sendPutRequest(path, body) {
    return this.sendRequest('PUT', path, body).catch(error =>
      this.$root.$refs.vtoast.show({
        message: 'Fehler: ' + error,
        color: 'failed'
      })
    );
  },
  sendPostRequest(path, body) {
    return this.sendRequest('POST', path, body).catch(error =>
      this.$root.$refs.vtoast.show({
        message: 'Fehler: ' + error,
        color: 'failed'
      })
    );
  }
};


export default RESTUtils;
